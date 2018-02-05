package Template;

import java.awt.Color;
import java.io.File;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hslf.model.Line;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class GraphTemplate extends TemplateSlide {

	private static String report_title = " Service Performance Board\nWeek ";

	public GraphTemplate(int sheetNumber, int sheetRefId, int slideNumber) {
		super(sheetNumber, sheetRefId, slideNumber);
		// TODO Auto-generated constructor stub
	}

	public GraphTemplate() {
		// TODO Auto-generated constructor stub
	}

	public GraphTemplate(org.apache.poi.hslf.model.Slide s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * generates a slide with perf graphs
	 * 
	 * @param ppt
	 * @param appli
	 * @param week
	 * @param year
	 * @throws Exception
	 */
	public static void addGraphsSlide(SlideShow ppt, String title, int week,
			int year, float av, float rt, float avPrev, float rtPrev,
			float slaAvMax, float slaAvMin, float slaRtMax, float slaRtMin,
			int transactionId, int nbDays, List summary, String inputPath)
			throws Exception {

		Slide slide = ppt.createSlide();
		// set page size
		ppt.setPageSize(new java.awt.Dimension(slideWidth, slideHeight));

		/**
		 * add a background picture for graph slide
		 */

		int idx = ppt.addPicture(new File("img/bg_graph.jpg"), Picture.JPEG);
		Picture pict = new Picture(idx);
		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle(10, 90, 632, 412));
		slide.addShape(pict);

		/**
		 * Title of the slide
		 */
		addTitle(ppt, slide, week, year, title);

		/**
		 * Title of the boxes
		 */
		addBoxesTitles(slide);

		/**
		 * Status of the week
		 */
		addStatus(ppt, slide, rt, rtPrev, av, avPrev, slaAvMin, slaAvMax,
				slaRtMax, slaRtMin, summary);
		/**
		 * Add legend to the graphs
		 */
		addLegend(ppt, slide, slaRtMax, slaRtMin, slaAvMax, slaAvMin);
		/**
		 * add graphs
		 */
		addGraphs(ppt, slide, week, year, transactionId, nbDays, inputPath);

		/**
		 * add a footer to the slide
		 */
		addFooter(ppt, slide, true, true);
	}

	/**
	 * Generates titles of the boxes for the graphs slide
	 * 
	 * @param slide
	 */
	private static void addBoxesTitles(Slide slide) {
		// Summary Status
		TextBox txt = new TextBox();
		txt.setText("Summary Status");
		txt.setAnchor(new java.awt.Rectangle(10, 90, 290, 20));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(darkBlue);
		rt.setAlignment(TextBox.AlignCenter);

		slide.addShape(txt);

		// Availability
		txt = new TextBox();
		txt.setText("Availability");
		txt.setAnchor(new java.awt.Rectangle(320, 90, 320, 20));
		rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(darkBlue);
		rt.setAlignment(TextBox.AlignCenter);
		slide.addShape(txt);

		// Response Time
		txt = new TextBox();
		txt.setText("Response Time");
		txt.setAnchor(new java.awt.Rectangle(320, 305, 320, 20));
		rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(darkBlue);
		rt.setAlignment(TextBox.AlignCenter);
		slide.addShape(txt);
	}

	/**
	 * Generates the legend for the graphs on the slide
	 * 
	 * @param slide
	 */
	private static void addLegend(SlideShow ppt, Slide slide, float rt_max,
			float rt_min, float av_max, float av_min) throws Exception {
		/**
		 * Legend
		 */
		TextBox txt = new TextBox();
		txt.setText("Legend");
		txt.setAnchor(new java.awt.Rectangle(10, 410, 320, 20));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(15);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);

		/**
		 * traffic lights
		 */
		// green
		int idx = ppt.addPicture(new File(greenFile), Picture.JPEG);
		Picture pict = new Picture(idx);
		int[] size = resizePicture(greenFile, 75);
		pict.setAnchor(new java.awt.Rectangle(5, 432, size[1], size[0]));
		slide.addShape(pict);

		// yellow
		idx = ppt.addPicture(new File(yellowFile), Picture.JPEG);
		pict = new Picture(idx);
		size = resizePicture(yellowFile, 75);
		pict.setAnchor(new java.awt.Rectangle(5, 454, size[1], size[0]));
		slide.addShape(pict);

		// red
		idx = ppt.addPicture(new File(redFile), Picture.JPEG);
		pict = new Picture(idx);
		size = resizePicture(redFile, 75);
		pict.setAnchor(new java.awt.Rectangle(5, 479, size[1], size[0]));
		slide.addShape(pict);

		/**
		 * SLAs
		 */
		txt = new TextBox();
		txt.setText(av_max + "% <= Availability	Response times <=" + rt_min
				+ "s\n\n" + av_max + "% < Availability < " + av_min + "%	"
				+ rt_min + "s < Response times < " + rt_max + "s\n\n" + av_min
				+ "% > Availability	Response times >" + rt_max + "s");
		txt.setAnchor(new java.awt.Rectangle(20, 430, 340, 70));
		rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(10);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);
	}

	/**
	 * Adds averages statistics
	 * 
	 * @param ppt
	 * @param slide
	 * @param rt
	 * @param rt_prev
	 * @param av
	 * @param av_prev
	 * @param sla_av_ko
	 * @param sla_av_ok
	 * @param sla_rt_ko
	 * @param sla_rt_ok
	 * @throws Exception
	 */
	private static void addStatus(SlideShow ppt, Slide slide, float rt,
			float rt_prev, float av, float av_prev, float sla_av_ko,
			float sla_av_ok, float sla_rt_ko, float sla_rt_ok, List summary)
			throws Exception {
		/**
		 * Current week
		 */
		TextBox txt = new TextBox();
		txt
				.setText("Availability ................................................... "
						+ av
						+ "%\n\n\nResponse time ............................................ "
						+ rt + "s\n\n\nStatus summary :");
		txt.setAnchor(new java.awt.Rectangle(10, 110, 250, 100));
		RichTextRun rtx = txt.getTextRun().getRichTextRuns()[0];
		rtx.setFontSize(10);
		rtx.setFontName("Arial");
		rtx.setFontColor(Color.black);
		rtx.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);

		/**
		 * Previous week
		 */
		txt = new TextBox();
		txt.setText("[ Previous week Avg = " + av_prev
				+ "% ]\n\n\n[ Previous week Avg = " + rt_prev + "s ]");
		txt.setAnchor(new java.awt.Rectangle(10, 125, 250, 70));
		rtx = txt.getTextRun().getRichTextRuns()[0];
		rtx.setFontSize(10);
		rtx.setFontName("Arial");
		rtx.setFontColor(lightBlue);
		rtx.setAlignment(TextBox.AlignRight);
		slide.addShape(txt);

		/**
		 * Status summary
		 */
		String s = "";
		if (summary != null) {
			// List l = Arrays.asList(summary);
			Iterator iter = summary.iterator();
			s = (String) iter.next();
			String s2;
			while (iter.hasNext()) {
				s2 = (String) iter.next();
				if (!"".equals(s2)) {
					/*
					 * remove new line character at end of field 
					 * (causes apache POI error)
					 */

					if ('\n' == (s2.charAt(s2.length() - 1))) {
						logger.warn("REMOVE END LINE CHAR");
						s2 = s2.substring(0, s2.length() - 1);
					}
					s += "\r" + s2;
				}
			}
		}

		txt = new TextBox();
		rtx = txt.getTextRun().getRichTextRuns()[0];
		txt.setText(s);
		txt.setAnchor(new java.awt.Rectangle(20, 200, 290, 70));
		rtx.setFontSize(10);
		rtx.setFontColor(lightBlue);
		rtx.setBullet(true);
		rtx.setBulletOffset(10); // bullet offset
		rtx.setTextOffset(20); // text offset (should be greater than bullet
		// offset)
		rtx.setBulletChar('\u2022'); // bullet character
		slide.addShape(txt);

		/**
		 * process traffic lights color
		 */
		String availLight = redFile;
		String rtLight = redFile;

		// availability
		if (av < sla_av_ko) {
			// red
			availLight = redFile;

		} else {
			if (av >= sla_av_ok) {
				// green
				availLight = greenFile;
			} else {
				// orange
				availLight = yellowFile;
				;
			}
		}

		// response time
		if (rt >= sla_rt_ko) {
			// red
			rtLight = redFile;

		} else {
			if (rt <= sla_rt_ok) {
				// green
				rtLight = greenFile;
			} else {
				// orange
				rtLight = yellowFile;
			}

		}

		/**
		 * add traffic lights
		 */
		// Average availability
		int idx = ppt.addPicture(new File(availLight), Picture.JPEG);
		Picture pict = new Picture(idx);
		int[] size = resizePicture(availLight, 75);
		pict.setAnchor(new java.awt.Rectangle(270, 115, size[1], size[0]));
		slide.addShape(pict);

		// Average response time
		idx = ppt.addPicture(new File(rtLight), Picture.JPEG);
		pict = new Picture(idx);
		size = resizePicture(rtLight, 75);
		pict.setAnchor(new java.awt.Rectangle(270, 150, size[1], size[0]));
		slide.addShape(pict);
	}

	/**
	 * Add availability and response time graphs
	 * 
	 * @param ppt
	 * @param slide
	 */
	private static void addGraphs(SlideShow ppt, Slide slide, int week,
			int year, int transaction, int nbDays, String inputPath)
			throws Exception {

		String path = (week - 1) + "-" + year + "_" + transaction;
		String av = inputPath + path + "_availability.png";
		String rt = inputPath + path + "_response_time.png";
		logger.debug("availability path :" + av);
		logger.debug("response time path :" + av);

		// $this->Image('../../../reporting/'.$this->week.'-'.$this->year.'_'.$this->reporting.'/'.$this->week.'-'.$this->year.'_'.$transaction.'_availability.png',
		// 130,40,130);
		// $this->Image('../../../reporting/'.$this->week.'-'.$this->year.'_'.$this->reporting.'/'.$this->week.'-'.$this->year.'_'.$transaction.'_response_time.png',
		// 130,125,130);
		/**
		 * availability
		 */
		int idx = ppt.addPicture(new File(av), Picture.PNG);

		Picture pict = new Picture(idx);
		int[] size = resizePicture(av, 43);

		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle(320, 120, size[1], size[0]));

		slide.addShape(pict);

		/**
		 * response times
		 */
		idx = ppt.addPicture(new File(rt), Picture.PNG);

		pict = new Picture(idx);
		size = resizePicture(rt, 43);

		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle(320, 335, size[1], size[0]));

		slide.addShape(pict);

		/**
		 * add week days grid
		 */
		TextBox txt;
		RichTextRun rtx;
		// Get day names
		String[] weekdays_moche = new DateFormatSymbols().getShortWeekdays();
		ArrayList weekdays = new ArrayList(Arrays.asList(weekdays_moche));
		weekdays.add(8, weekdays.get(1));
		// weekdays[8]=weekdays[1];//put Sun at the end of the array not ot be
		// stupid !

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.WEEK_OF_YEAR, week);
		cal.set(GregorianCalendar.YEAR, year);

		int x = 345;
		int y = 348;
		int w = 245;
		int h = 125;

		int x_ofset = w / nbDays;

		// response times
		for (int i = 1; i < nbDays; i++) {
			txt = new TextBox();
			txt.setText((String) weekdays.get(i + 1));

			txt.setAnchor(new java.awt.Rectangle(x, 455, 40, 20));
			rtx = txt.getTextRun().getRichTextRuns()[0];
			rtx.setFontSize(10);
			rtx.setFontName("Arial");
			rtx.setFontColor(Color.black);
			rtx.setAlignment(TextBox.AlignCenter);
			slide.addShape(txt);

			x += x_ofset;
			Line line = new Line();
			line.setAnchor(new java.awt.Rectangle(x, y, 0, h));
			line.setLineColor(Color.black);
			line.setLineStyle(Line.LINE_SIMPLE);
			slide.addShape(line);

		}

		txt = new TextBox();
		cal.set(GregorianCalendar.DAY_OF_WEEK, nbDays);
		txt.setText((String) weekdays.get(nbDays + 1));

		txt.setAnchor(new java.awt.Rectangle(x, 455, 40, 20));
		rtx = txt.getTextRun().getRichTextRuns()[0];
		rtx.setFontSize(10);
		rtx.setFontName("Arial");
		rtx.setFontColor(Color.black);
		rtx.setAlignment(TextBox.AlignCenter);
		slide.addShape(txt);

		x = 345;
		y = 133;
		// availability
		for (int i = 1; i < nbDays; i++) {
			txt = new TextBox();
			txt.setText((String) weekdays.get(i + 1));
			txt.setAnchor(new java.awt.Rectangle(x, 240, 40, 20));
			rtx = txt.getTextRun().getRichTextRuns()[0];
			rtx.setFontSize(10);
			rtx.setFontName("Arial");
			rtx.setFontColor(Color.black);
			rtx.setAlignment(TextBox.AlignCenter);
			slide.addShape(txt);

			x += x_ofset;
			Line line = new Line();
			line.setAnchor(new java.awt.Rectangle(x, y, 0, h));
			line.setLineColor(Color.black);
			line.setLineStyle(Line.LINE_SIMPLE);
			slide.addShape(line);

		}

		txt = new TextBox();
		cal.set(GregorianCalendar.DAY_OF_WEEK, nbDays);
		txt.setText((String) weekdays.get(nbDays + 1));

		txt.setAnchor(new java.awt.Rectangle(x, 240, 40, 20));
		rtx = txt.getTextRun().getRichTextRuns()[0];
		rtx.setFontSize(10);
		rtx.setFontName("Arial");
		rtx.setFontColor(Color.black);
		rtx.setAlignment(TextBox.AlignCenter);
		slide.addShape(txt);
	}
}
