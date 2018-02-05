package Template;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hslf.model.Line;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.Table;
import org.apache.poi.hslf.model.TableCell;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import sax.Day;

/**
 * Template slide for calendar page in the reports.
 * @author mas
 *
 */
public class CalendarTemplate extends TemplateSlide {

	public CalendarTemplate(int sheetNumber, int sheetRefId, int slideNumber) {
		super(sheetNumber, sheetRefId, slideNumber);
		// TODO Auto-generated constructor stub
	}

	public CalendarTemplate() {
		// TODO Auto-generated constructor stub
	}

	public CalendarTemplate(org.apache.poi.hslf.model.Slide s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public static void addCalendarSlide(SlideShow ppt, String appli, int week,
			int year, int transactionId, List calendar) throws Exception {

		Slide slide = ppt.createSlide();

		// set page size
		ppt.setPageSize(new java.awt.Dimension(slideWidth, slideHeight));

		/**
		 * add title to slide
		 */
		addTitle(ppt, slide, week, year, appli);

		/**
		 * add calendar table
		 */
		addCalendar(slide, year, week, transactionId, calendar);

		/**
		 * add legend of cells colors
		 */
		addLegend(ppt, slide);

		/**
		 * add a footer to the slide
		 */
		addFooter(ppt, slide, true, true);
	}

	private static void addLegend(SlideShow ppt, Slide slide) {
		/**
		 * Legend
		 */
		TextBox txt = new TextBox();
		txt.setText("Legend");
		txt.setAnchor(new java.awt.Rectangle(20, 350, 320, 20));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);

		/**
		 * add colors cells
		 */
		int x = 25;
		int y = 380;
		int y_offset = 15;

		// j'aime pas les boucles :-(
		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(Color.white);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(green);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(noteColor);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(red);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(orange);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(yellow);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(dodgerblue);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(violet);
		txt.setLineColor(Color.black);
		slide.addShape(txt);
		y += y_offset;

		txt = new TextBox();
		txt.setAnchor(new java.awt.Rectangle(x, y, 20, 10));
		txt.setFillColor(brown);
		txt.setLineColor(Color.black);
		slide.addShape(txt);

		/**
		 * add descriptions
		 */
		txt = new TextBox();
		txt
				.setText("Application available (Out of SP opening hours)\nApplication available\nNote\nTotal unavailability\nPartial unavailability\nMaintenance\nUpdate\nTraining\nTest");
		txt.setAnchor(new java.awt.Rectangle(45, 373, 320, 150));
		rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(12);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		rt.setLineSpacing(105);
		slide.addShape(txt);

	}

	/**
	 * @param slide
	 * @param year
	 * @param week
	 * @param transactionId
	 * @param calendar
	 * @throws Exception
	 */
	private static void addCalendar(Slide slide, int year, int week,
			int transactionId, List calendar) throws Exception {

		/**
		 * Calendar
		 */
		TextBox txt = new TextBox();
		txt.setText("Calendar");
		txt.setAnchor(new java.awt.Rectangle(20, 80, 320, 20));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.WEEK_OF_YEAR, week);
		cal.set(GregorianCalendar.YEAR, year);

		String[] days_moche = { "", "", "", "", "", "", "", "" };

		for (int i = 0; i < 7; i++) {
			cal.set(GregorianCalendar.DAY_OF_WEEK, i + 1);
			days_moche[i] = cal.get(GregorianCalendar.DAY_OF_MONTH) + "/"
					+ cal.get(GregorianCalendar.MONTH);
		}

		ArrayList days = new ArrayList(Arrays.asList(days_moche));
		days.add(7, days.get(0));// sets sunday as the last day of the week

		HashMap colors = new HashMap();
		colors.put("yellow", yellow);
		colors.put("orange", orange);
		colors.put("violet", violet);
		colors.put("brown", brown);
		colors.put("red", red);
		colors.put("dodgerblue", dodgerblue);
		colors.put("green", lightGreen);
		colors.put("white", Color.white);
		colors.put("lightblue", noteColor);
		colors.put("#E1EDF7", noteColor);

		// List calendar = getColors(year, week, transactionId);

		String[][] data = {
				{ "Day/Hour", "0h", "1h", "2h", "3h", "4h", "5h", "6h", "7h",
						"8h", "9h", "10h", "11h", "12h", "13h", "14h", "15h",
						"16h", "17h", "18h", "19h", "20h", "21h", "22h", "23h" },
				{ "Monday " + days.get(1), "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"" },
				{ "Tuesday " + days.get(2), "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"" },
				{ "Wednesday " + days.get(3), "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "" },
				{ "Thursday " + days.get(4), "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "" },
				{ "Friday " + days.get(5), "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"" },
				{ "Saturday " + days.get(6), "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "" },
				{ "Sunday " + days.get(7), "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"" } };

		List day = new ArrayList();
		Table table = new Table(data.length, data[0].length);
		for (int i = 0; i < data.length; i++) {
			// lignes
			if (i > 0) {
				day = ((Day) calendar.get(i - 1)).toArray();
			}

			for (int j = 0; j < data[i].length; j++) {
				// colonnes
				TableCell cell = table.getCell(i, j);
				cell.setText(data[i][j]);
				rt = cell.getTextRun().getRichTextRuns()[0];
				rt.setFontName("Arial");
				rt.setFontSize(10);
				rt.setFontColor(Color.black);
				cell.setWordWrap(TextBox.WrapNone);
				cell.setVerticalAlignment(TextBox.AnchorMiddle);
				cell.setHorizontalAlignment(TextBox.AlignCenter);
				table.setColumnWidth(j, 25);

				// headers
				if (i == 0) {
					cell.setFillColor(blueTitle);
					rt.setFontColor(Color.white);
					rt.setBold(true);
				}
				// first column
				if (j == 0 && i > 0) {
					cell.setFillColor(cyan);
				}

				// content cells
				if (i > 0 && j > 0) {
					cell.setFillColor((Color) colors.get(day.get(j)));
					// cell.setFillColor((Color) colors.get(colors_s[i - 1][j -
					// 1]));
				}
			}
			table.setRowHeight(i, 20);
		}

		// set width of the 1st column
		table.setColumnWidth(0, 90);

		// set table borders
		Line border = table.createBorder();
		border.setLineColor(darkBlue);
		border.setLineWidth(1.0);
		table.setAllBorders(border);

		slide.addShape(table);
		table.moveTo(15, 110);
	}

	// private static List getColors(int year, int week, int
	// transactionId,String dir)
	// throws Exception {
	// SAXParserFactory fabrique = SAXParserFactory.newInstance();
	// SAXParser parseur = fabrique.newSAXParser();
	//
	// File fichier = new File("input/" + week + "-" + year + "_"
	// + transactionId + "/colors.xml");
	// DefaultHandler gestionnaire = new DayHandler();
	// parseur.parse(fichier, gestionnaire);
	// return ((DayHandler) gestionnaire).getCalendar();
	// }

}
