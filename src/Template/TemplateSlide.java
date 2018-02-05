package Template;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.record.SlideListWithText.SlideAtomsSet;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class TemplateSlide extends Slide {

	public static final Color lightGreen = new Color(102, 255, 102);
	public static final Color lightRed = new Color(204, 0, 0);
	public static final Color lightOrange = new Color(255, 163, 0);
	public static final Color blueLine = new Color(100, 162, 208);
	public static final Color darkLine = new Color(47, 57, 96);
	public static final Color blueTitle = new Color(103, 163, 185);
	public static final Color darkBlue = new Color(21, 61, 102);
	public static final Color lightBlue = new Color(85, 131, 249);
	public static final Color yellow = new Color(255, 243, 53);
	public static final Color orange = new Color(255, 150, 0);
	public static final Color violet = new Color(166, 64, 200);
	public static final Color brown = new Color(146, 77, 31);
	public static final Color red = new Color(225, 0, 0);
	public static final Color dodgerblue = new Color(45, 124, 255);
	public static final Color green = new Color(136, 255, 127);
	public static final Color noteColor = new Color(176, 197, 225);
	public static final Color cyan = new Color(224, 235, 255);
	private static String report_title = " \nWeek ";

	public static final String greenFile = "img/green.jpg";
	public static final String yellowFile = "img/yellow.jpg";
	public static final String redFile = "img/red.jpg";

	public static final int slideWidth = 720;
	public static final int slideHeight = 540;

	private static String footer = "Service Performance Management - OCBS1";

	// Define a static logger variable so that it references the
	// Logger instance named "TemplateSlide".
	static Logger logger = Logger.getLogger(TemplateSlide.class);

	public TemplateSlide(int sheetNumber, int sheetRefId, int slideNumber) {
		super(sheetNumber, sheetRefId, slideNumber);
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
	}

	public TemplateSlide(org.apache.poi.hslf.record.Slide slide, Notes notes,
			SlideAtomsSet atomSet, int slideIdentifier, int slideNumber) {
		super(slide, notes, atomSet, slideIdentifier, slideNumber);
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
	}

	public TemplateSlide() {

		super(0, 0, 0);
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
	}

	public TemplateSlide(Slide s) {
		super(s._getSheetNumber(), s._getSheetRefId(), s.getSlideNumber());
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
	}

	public static void addFooter(SlideShow ppt, Slide slide, boolean pageNum,
			boolean logo) throws Exception {

		if (logo) {
			/**
			 * add a footer picture
			 */
			int idx = ppt.addPicture(new File("img/logo_footer_front.jpg"),
					Picture.JPEG);
			Picture pict = new Picture(idx);
			int[] size = resizePicture("img/logo_footer_front.jpg", 40);

			// set image position in the slide
			pict.setAnchor(new java.awt.Rectangle(slideWidth - size[1],
					slideHeight - size[0], size[1], size[0]));

			slide.addShape(pict);
		}

		/**
		 * set footer text
		 */
		TextBox txt = new TextBox();
		txt.setText(footer);
		txt.setAnchor(new java.awt.Rectangle(20, slideHeight - 20, slideWidth,
				15));
		// use RichTextRun to work with the text format
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(10);
		rt.setFontName("Arial");
		rt.setItalic(true);
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);

		slide.addShape(txt);

		/**
		 * set footer page number
		 */
		if (pageNum) {
			txt = new TextBox();
			txt.setText("Page " + slide.getSlideNumber());
			txt.setAnchor(new java.awt.Rectangle(0, slideHeight - 20,
					slideWidth, 15));
			// use RichTextRun to work with the text format
			rt = txt.getTextRun().getRichTextRuns()[0];
			rt.setFontSize(10);
			rt.setFontName("Arial");
			rt.setItalic(true);
			rt.setFontColor(Color.black);
			rt.setAlignment(TextBox.AlignCenter);

			slide.addShape(txt);
		}

	}

	/**
	 * Return heigth and width of the resized image
	 * 
	 * @param filename
	 *            of the picture
	 * @param percents
	 *            to resize the image (0-100 or above...)
	 * @return int[h,w]
	 */
	public static int[] resizePicture(String filename, int percents) {
		Image img = new ImageIcon(filename).getImage();
		int h = img.getHeight(null);
		int w = img.getWidth(null);

		h = h * percents / 100;
		w = w * percents / 100;

		int[] ret = { h, w };

		return ret;
	}

	public static void addTitle(SlideShow ppt, Slide slide, int week, int year,
			String title) {
		/**
		 * Title of the slide
		 */
		title += report_title + week + "-" + year;

		TextBox txt = new TextBox();
		txt.setText(title);
		txt.setAnchor(new java.awt.Rectangle(0, 0, slideWidth, 80));
		txt.setFillColor(blueTitle);
		RichTextRun rtx = txt.getTextRun().getRichTextRuns()[0];
		rtx.setFontSize(18);
		rtx.setFontName("Arial");
		rtx.setBold(true);
		rtx.setFontColor(Color.white);
		rtx.setAlignment(TextBox.AlignLeft);

		slide.addShape(txt);
	}
}
