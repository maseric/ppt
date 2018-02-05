package Template;

import java.awt.Color;
import java.io.File;
import java.util.List;

import org.apache.poi.hslf.model.Line;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.Table;
import org.apache.poi.hslf.model.TableCell;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import sax.Issue;

public class IssuesTemplate extends TemplateSlide {

	private static Double rowHeight = 0d;

	public static void addIssuesSlide(SlideShow ppt, String appli, int week,
			int year, int transactionId, List issues) throws Exception {
		Slide slide = ppt.createSlide();

		// set page size
		ppt.setPageSize(new java.awt.Dimension(slideWidth, slideHeight));
		/**
		 * add issues table
		 */
		addIssuesTable(ppt, slide, week, year, appli, transactionId, issues);
		/**
		 * add a title
		 */
		addTitle(ppt, slide, week, year, appli);

		/**
		 * add a footer to the slide
		 */
		addFooter(ppt, slide, true, true);
	}

	private static void addIssuesTable(SlideShow ppt, Slide slide, int week,
			int year, String appl, int transactionIdi, List issues)
			throws Exception {

		/**
		 * Issues
		 */
		TextBox txt = new TextBox();
		txt.setText("Issues");
		txt.setAnchor(new java.awt.Rectangle(10, 80, 320, 20));
		RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
		rt.setFontSize(14);
		rt.setFontName("Arial");
		rt.setFontColor(Color.black);
		rt.setAlignment(TextBox.AlignLeft);
		slide.addShape(txt);

		/**
		 * add background picture
		 */
		int idx = ppt.addPicture(new File("img/bg_issues.jpg"), Picture.JPEG);
		Picture pict = new Picture(idx);

		// set image position in the slide
		pict.setAnchor(new java.awt.Rectangle(10, 105, slideWidth - 20,
				slideHeight - 105));

		slide.addShape(pict);

		// List issues = getIssues(year, week, transactionIdi);

		/*
		 * String[][] data = { { "Begin", "End", "Description", "Impacts",
		 * "Actions" }, { "Mon 24/11 9:00", "26/11 12:00", "Virus Attack - Bad
		 * response times", "SAP Portal PZE (external access DMZ) will be
		 * unavailable from saturday 29/11/2008 08:00pm to Sunday 30/11/2008
		 * 12:00pm Impact on ESL access.", "" }, { "Thu 27/11 10:30", "27/11
		 * 12:30", "KDS unscheduled outage", "KDS unstable", "" }, { "Thu 27/11
		 * 17:00", "27/11 17:45", "Network issue", "Network issue, some
		 * applications were unavailable", "" }, { "Fri 28/11 9:21", "28/11
		 * 10:00", "PGI issue PGI KO", "", "" }, { "Sun 30/11 17:00", "30/11
		 * 20:00", "PTM maintenance Window", "", "" }, { "Sun 30/11 17:00",
		 * "30/11 20:00", "PHM maintenance window", "", "" } };
		 */

		Table table = new Table(issues.size(), 5);
		Table formatTable = new Table(issues.size(), 5);
		formatTable.setColumnWidth(0, 80);
		formatTable.setColumnWidth(1, 60);
		formatTable.setColumnWidth(2, 190);
		formatTable.setColumnWidth(3, 190);
		formatTable.setColumnWidth(4, 180);

		// Table table = new Table(data.length, data[0].length);

		for (int i = 0; i < issues.size(); i++) {
			// rows
			// table.setRowHeight(i, 20);

			// headers
			if (i == 0) {
				// column Begin
				String text = ((Issue) issues.get(i)).getBegin();
				makeHeader(table, text, i, 0);

				// column End
				text = ((Issue) issues.get(i)).getEnd();
				makeHeader(table, text, i, 1);

				// column description
				text = ((Issue) issues.get(i)).getDescription();
				makeHeader(table, text, i, 2);

				// column impacts
				text = ((Issue) issues.get(i)).getImpacts();
				makeHeader(table, text, i, 3);

				// column impacts
				text = ((Issue) issues.get(i)).getActions();
				makeHeader(table, text, i, 4);

				// set row height
				table.setRowHeight(i, 30);

			} else {
				// column Begin
				String text = ((Issue) issues.get(i)).getBegin();
				makeCell(table, text, i, 0, formatTable);

				// column End
				text = ((Issue) issues.get(i)).getEnd();
				makeCell(table, text, i, 1, formatTable);

				// column description
				text = ((Issue) issues.get(i)).getDescription();
				makeCell(table, text, i, 2, formatTable);

				// column impacts
				text = ((Issue) issues.get(i)).getImpacts();
				makeCell(table, text, i, 3, formatTable);

				// column impacts
				text = ((Issue) issues.get(i)).getActions();
				makeCell(table, text, i, 4, formatTable);

				// table.setRowHeight(i, ((Double) (rowHeight +
				// 15d)).intValue());
				table.setRowHeight(i, ((Double) (rowHeight)).intValue());

				logger.debug("Row #" + i + " height="
						+ ((Double) (rowHeight + 15d)).intValue());

				rowHeight = 0d;
			}
		}

		Line border = table.createBorder();
		border.setLineColor(darkBlue);
		border.setLineWidth(1.0);
		table.setAllBorders(border);
		// set width of columns
		table.setColumnWidth(0, 80);
		table.setColumnWidth(1, 60);
		table.setColumnWidth(2, 190);
		table.setColumnWidth(3, 190);
		table.setColumnWidth(4, 175);

		slide.addShape(table);
		table.moveTo(11, 105);
	}

	private static TableCell makeCell(Table table, String text, int i, int j,
			Table formatTable) {
		TableCell cell = table.getCell(i, j);
		// cell.setText(data[i][j]);
		cell.setText(text);
		RichTextRun rt = cell.getTextRun().getRichTextRuns()[0];
		rt.setFontName("Arial");
		rt.setFontSize(7);
		rt.setFontColor(Color.black);
		rt.setTextOffset(0);

		cell.setWordWrap(TextBox.WrapSquare);
		cell.setVerticalAlignment(TextBox.AnchorTop);
		cell.setHorizontalAlignment(TextBox.AlignLeft);

		// Row height processing
		TableCell cell2 = formatTable.getCell(i, j);
		cell2.setText(text);
		rt = cell2.getTextRun().getRichTextRuns()[0];
		rt.setFontName("Arial");
		rt.setFontSize(7);
		rt.setFontColor(Color.black);
		cell2.setWordWrap(TextBox.WrapSquare);
		cell2.setVerticalAlignment(TextBox.AnchorTop);
		cell2.setHorizontalAlignment(TextBox.AlignLeft);
		cell2.resizeToFitText();
		Double h = cell2.getAnchor().getHeight();

		/*
		 * if height of this cell is bigger than current row height, we increase
		 * row height
		 */
		rowHeight = (rowHeight < h) ? h : rowHeight;

		// set table borders
		/*
		 * Line border = table.createBorder(); border.setLineColor(darkBlue);
		 * border.setLineWidth(1.0); cell.setBorderBottom(border);
		 */
		return cell;
	}

	private static TableCell makeHeader(Table table, String text, int i, int j) {
		TableCell cell = table.getCell(i, j);

		cell.setText(text);
		RichTextRun rt = cell.getTextRun().getRichTextRuns()[0];
		rt.setFontName("Arial");
		rt.setFontColor(Color.white);
		rt.setBold(true);
		rt.setFontSize(10);
		cell.setWordWrap(TextBox.WrapTopBottom);
		cell.setVerticalAlignment(TextBox.AlignCenter);
		cell.setHorizontalAlignment(TextBox.AlignCenter);
		cell.setFillColor(blueTitle);

		// set cell borders
		/*
		 * Line border = table.createBorder(); border.setLineColor(darkBlue);
		 * border.setLineWidth(1.0); cell.setBorderBottom(border);
		 * 
		 * cell.setBorderLeft(border); cell.setBorderRight(border);
		 * cell.setBorderTop(border);
		 */
		return cell;
	}
}
