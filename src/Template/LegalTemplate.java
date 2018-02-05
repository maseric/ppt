package Template;

import java.awt.Color;
import java.io.File;

import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class LegalTemplate extends TemplateSlide {
	public static void addLegalSlide(SlideShow ppt) throws Exception {
		Slide slide = ppt.createSlide();

		// set page size
		ppt.setPageSize(new java.awt.Dimension(slideWidth, slideHeight));

		/**
		 * disclaimer
		 */
		String legal = "© AIRBUS S.A.S. All rights reserved. Confidential and\nproprietary document.\nThis document and all information contained herein is the sole\nproperty of AIRBUS S.A.S.. No intellectual property rights are\ngranted by the delivery of this document or the disclosure of\nits content. This document shall not be reproduced or\ndisclosed to a third party without the express written consent\nof AIRBUS S.A.S. This document and its content shall not be\nused for any purpose other than that for which it is supplied.\nThe statements made herein do not constitute an offer. They\nare based on the mentioned assumptions and are expressed\nin good faith. Where the supporting grounds for these\nstatements are not shown, AIRBUS S.A.S. will be pleased to\nexplain the basis thereof.\nAIRBUS, its logo, A300, A310, A318, A319, A320, A321,\nA330, A340, A350, A380, A400M are registered trademarks.";
		TextBox txt = new TextBox();
		txt.setText(legal);
		int h = 175;
		int w = 280;

		txt.setAnchor(new java.awt.Rectangle((slideWidth - w) / 2,
				(slideHeight - h) / 2, w, h));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(9);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignJustify);
		slide.addShape(txt);

		/**
		 * add logo picture
		 */
		int idx = ppt.addPicture(new File("img/logo_airbus.jpg"), Picture.JPEG);
		Picture pict = new Picture(idx);
		int[] size = resizePicture("img/logo_airbus.jpg", 100);

		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle((slideWidth - size[1]) / 2,
				(slideHeight + h) / 2, size[1], size[0]));

		slide.addShape(pict);

		/**
		 * add a footer to the slide
		 */
		addFooter(ppt, slide, true, false);
	}
}
