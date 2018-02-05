package Template;

import java.awt.Color;
import java.io.File;

import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.record.SlideListWithText.SlideAtomsSet;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.record.FooterRecord;

/**
 * Class to manage title template slide
 * 
 * @author MAS
 * 
 */
public class TitleTemplate extends TemplateSlide {
	private static String report_title = " \nWeekly Operational Report W";

	public TitleTemplate() {
		super();
	}

	public TitleTemplate(Slide s) {
		super(s);
	}

	public TitleTemplate(int sheetNumber, int sheetRefId, int slideNumber) {
		super(sheetNumber, sheetRefId, slideNumber);
	}

	public TitleTemplate(org.apache.poi.hslf.record.Slide slide, Notes notes,
			SlideAtomsSet atomSet, int slideIdentifier, int slideNumber) {
		super(slide, notes, atomSet, slideIdentifier, slideNumber);

	}

	/**
	 * Generates a title slide for a PowerPoint slideshow
	 * 
	 * @param SlideShow
	 *            ppt : slideshow to edit
	 * @param String
	 *            title : title to set to the title slide
	 * @throws Exception
	 */
	public static void addTitleSlide(SlideShow ppt, String title, int week,
			int year) throws Exception {
		report_title = title + report_title + week + " " + year;

		Slide slide = ppt.createSlide();
		// set page size
		ppt.setPageSize(new java.awt.Dimension(slideWidth, slideHeight));

		// add a background picture title slide
		int idx = ppt.addPicture(new File("img/first_page.jpg"), Picture.JPEG);

		Picture pict = new Picture(idx);

		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle(0, 0, slideWidth, 253));

		slide.addShape(pict);

		// Title of the slide
		TextBox txt = new TextBox();
		txt.setText(report_title);
		txt.setAnchor(new java.awt.Rectangle(0, 350, slideWidth, 100));
		// use RichTextRun to work with the text format
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(18);
		rt.setFontName("Arial");
		rt.setBold(true);
		rt.setFontColor(Color.blue);
		rt.setAlignment(TextBox.AlignRight);

		slide.addShape(txt);

		// Presented by
		String s = "Presented by\n\nVirginie Pierrot\nService Performance Manager\n\nOCBS1 - Service & Product Management";
		txt = new TextBox();
		txt.setText(s);
		txt.setAnchor(new java.awt.Rectangle(15, 200, 300, 100));
		// use RichTextRun to work with the text format
		rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);

		slide.addShape(txt);

		addFooter(ppt, slide, false, true);

	}
}
