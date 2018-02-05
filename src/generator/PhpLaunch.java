package generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.xml.sax.helpers.DefaultHandler;

import sax.Page;
import sax.PageHandler;
import Template.CalendarTemplate;
import Template.GraphTemplate;
import Template.IssuesTemplate;
import Template.LegalTemplate;
import Template.TitleTemplate;

/**
 * Java class to be launched by <b>PhP</b>,<br>
 * that generate weekly reports at PowerPoint format.
 * 
 * @author mas
 */
public class PhpLaunch {
	/**
	 * Home dir of the tool. Set by propertie file <i><b>ppt.properties</i></b>
	 */
	private static String BASE_DIR;

	/**
	 * Path to input files (set by main(Sting[]) method).
	 * For each PowerPoint:<br>
	 * ex.: <b>INPUT_PATH</b>=/u001/dvp/www/spi_jer/reporting/50-2008_6/<br>
	 * Then the following parameters will be set automatically :
	 * <ul>
	 * <li><b>report conf file</b> : /u001/dvp/www/spi_jer/reporting/50-2008_6/reporting.xml
	 * <li><b>availability picture file</b> : /u001/dvp/www/spi_jer/reporting/50-2008_6/50-2008_6_availability.png
	 * <li><b>response time picture file</b> : /u001/dvp/www/spi_jer/reporting/50-2008_6/50-2008_6_response_time.png
	 * </ul>
	 */
	private static String INPUT_PATH;
	/**
	 * Week of the report
	 */
	private int w;
	/**
	 * Year of the report
	 */
	private int y;
	/**
	 * Title of the report
	 */
	private String title;
	/**
	 * Transaction of the report (DB id)
	 */
	private int transac;

	// Define a static logger variable so that it references the
	// Logger instance named "PhpLaunch".
	static Logger logger = Logger.getLogger(PhpLaunch.class);

	/**
	 * Generates a ppt from parameters previously set
	 * @throws Exception
	 */
	public PhpLaunch() throws Exception {

		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
		logger
				.info("###--------------------------------------------------###\n-----------------------");
		logger.info("### Starting powerpoint generation");
		logger.info("### BEGIN ###");

		/**
		 * @todo récupérer ce param dynamiquement
		 */
		getproperties();
		// String file = REPORT;
		String file = INPUT_PATH + "reporting.xml";
		logger.info("### Parsing XML file : " + file + " ###");

		List report = this.parse(file);

		/**
		 * create report ppt
		 */
		SlideShow ppt = new SlideShow();

		Iterator iter = report.iterator();
		while (iter.hasNext()) {
			Page page = (Page) iter.next();
			String type = page.getType();

			/*
			 * set year & week
			 */
			if (Page.TITLE.equals(type)) {
				w = Integer.parseInt(page.getWeek());
				y = page.getYear();
				title = page.getTitle();
				// slide title
				logger.info("### Title slide");
				TitleTemplate.addTitleSlide(ppt, title, w, y);
			} else if (Page.GRAPHS.equals(type)) {

				float av = page.getAvailability();
				float avMax = page.getAvMax();
				float avMin = page.getAvMin();
				float prevAv = page.getPrevAv();
				float prevRt = page.getPrevRt();
				float rt = page.getResponseTime();
				float rtMax = page.getRtMax();
				float rtMin = page.getRtMin();
				transac = page.getTransaction();
				List summary = page.getSummary();
				// slide 2=graphs
				logger.info("### Graph slide");

				/*
				 * GraphTemplate.addGraphsSlide(ppt, title, w, y, av, rt,
				 * prevAv, prevRt, avMax, avMin, rtMax, rtMin, transac, 5, new
				 * String[] { "status 1", "status 1" });
				 */
				GraphTemplate.addGraphsSlide(ppt, title, w, y, av, rt, prevAv,
						prevRt, avMax, avMin, rtMax, rtMin, transac, 5,
						summary, INPUT_PATH);

			} else if (Page.CALENDAR.equals(type)) {
				// slide calendar
				logger.info("### Calendar slide");
				List calendar = page.getCalendar();
				CalendarTemplate.addCalendarSlide(ppt, title, w, y, transac,
						calendar);
			} else if (Page.ISSUES.equals(type)) {
				// slide issues
				logger.info("### Issues slide");
				List issues = page.getIssues();
				IssuesTemplate
						.addIssuesSlide(ppt, title, w, y, transac, issues);
			} else if (Page.LEGAL.equals(type)) {
				// slide legal
				logger.info("### Legal slide");
				LegalTemplate.addLegalSlide(ppt);
			}

		}

		/**
		 * save changes in a file
		 */

		String newReport = INPUT_PATH + y + "-W" + w + "_"
				+ title.replace(' ', '_') + ".ppt";
		// String newReport = PPT + w + "-" + y + "_" + title + ".ppt";
		// $_SERVER['DOCUMENT_ROOT'].'/spi_jer/reporting/'.$week.'-'.$year.'_'.$reporting.'/'.($week+1).'-'.$year.'_'.$titre.'.pdf'
		FileOutputStream out = new FileOutputStream(newReport);

		logger.info("### Saving new slideshow");
		logger.info("Output file : \"" + newReport + "\"");
		ppt.write(out);
		out.close();

		logger.info("### END ###");
	}

	/**
	 * Method called by PhP.
	 * 
	 * @param args :
	 *            sets the global variable <i>INPUT_PATH</i>.<br>
	 *            This variable is the absolute path to the reporting files
	 *            (report.xml and pictures files)
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		try {

			INPUT_PATH = args[0];
			logger.info("INPUT_PATH : " + args[0]);
			new PhpLaunch();

		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	private List parse(String file) throws Exception {
		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parseur = fabrique.newSAXParser();
		DefaultHandler gestionnaire = new PageHandler();
		parseur.parse(file, gestionnaire);
		List report = ((PageHandler) gestionnaire).getReport();

		return report;
	}

	/**
	 * Reads properties from properties file <i><b>ppt.properties</b></i>.<br>
	 * In this class, the only property used is <i><b>BASE_DIR</b></i>
	 * 
	 * @throws Exception
	 */
	private void getproperties() throws Exception {
		/*
		 * Read properties file
		 */
		Properties properties = new Properties();
		logger.info("### parsing properties file");
		properties.load(new FileInputStream("ppt.properties"));

		BASE_DIR = properties.getProperty("BASE_DIR");
		logger.info("### Base dir=\"" + BASE_DIR + "\"");

	}
}
