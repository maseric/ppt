package sax;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day in the calendar.<br>
 * Each col<i>x</i> field represents an hour of the day, and is associated
 * with a color.<br>
 * 
 * @see Template.CalendarTemplate
 * @author mas
 * 
 */
public class Day {
	private String col0;
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
	private String col7;
	private String col8;
	private String col9;
	private String col10;
	private String col11;
	private String col12;
	private String col13;
	private String col14;
	private String col15;
	private String col16;
	private String col17;
	private String col18;
	private String col19;
	private String col20;
	private String col21;
	private String col22;
	private String col23;
	private String col24;

	public String getCol0() {
		return col0;
	}

	public void setCol0(String col0) {
		this.col0 = col0;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCol10() {
		return col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public String getCol11() {
		return col11;
	}

	public void setCol11(String col11) {
		this.col11 = col11;
	}

	public String getCol12() {
		return col12;
	}

	public void setCol12(String col12) {
		this.col12 = col12;
	}

	public String getCol13() {
		return col13;
	}

	public void setCol13(String col13) {
		this.col13 = col13;
	}

	public String getCol14() {
		return col14;
	}

	public void setCol14(String col14) {
		this.col14 = col14;
	}

	public String getCol15() {
		return col15;
	}

	public void setCol15(String col15) {
		this.col15 = col15;
	}

	public String getCol16() {
		return col16;
	}

	public void setCol16(String col16) {
		this.col16 = col16;
	}

	public String getCol17() {
		return col17;
	}

	public void setCol17(String col17) {
		this.col17 = col17;
	}

	public String getCol18() {
		return col18;
	}

	public void setCol18(String col18) {
		this.col18 = col18;
	}

	public String getCol19() {
		return col19;
	}

	public void setCol19(String col19) {
		this.col19 = col19;
	}

	public String getCol20() {
		return col20;
	}

	public void setCol20(String col20) {
		this.col20 = col20;
	}

	public String getCol21() {
		return col21;
	}

	public void setCol21(String col21) {
		this.col21 = col21;
	}

	public String getCol22() {
		return col22;
	}

	public void setCol22(String col22) {
		this.col22 = col22;
	}

	public String getCol23() {
		return col23;
	}

	public void setCol23(String col23) {
		this.col23 = col23;
	}

	public String getCol24() {
		return col24;
	}

	public void setCol24(String col24) {
		this.col24 = col24;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "\nDay :";

		output += (col0 == null) ? "" : "\ncol0: " + col0;
		output += (col1 == null) ? "" : "\ncol1: " + col1;
		output += (col2 == null) ? "" : "\ncol2: " + col2;
		output += (col3 == null) ? "" : "\ncol3: " + col3;
		output += (col4 == null) ? "" : "\ncol4: " + col4;
		output += (col5 == null) ? "" : "\ncol5: " + col5;
		output += (col6 == null) ? "" : "\ncol6: " + col6;
		output += (col7 == null) ? "" : "\ncol7: " + col7;
		output += (col8 == null) ? "" : "\ncol8: " + col8;
		output += (col9 == null) ? "" : "\ncol9: " + col9;
		output += (col10 == null) ? "" : "\ncol10: " + col10;
		output += (col11 == null) ? "" : "\ncol11: " + col11;
		output += (col12 == null) ? "" : "\ncol12: " + col12;
		output += (col13 == null) ? "" : "\ncol13: " + col13;
		output += (col14 == null) ? "" : "\ncol14: " + col14;
		output += (col15 == null) ? "" : "\ncol15: " + col15;
		output += (col16 == null) ? "" : "\ncol16: " + col16;
		output += (col17 == null) ? "" : "\ncol17: " + col17;
		output += (col18 == null) ? "" : "\ncol18: " + col18;
		output += (col19 == null) ? "" : "\ncol19: " + col19;
		output += (col20 == null) ? "" : "\ncol20: " + col20;
		output += (col21 == null) ? "" : "\ncol21: " + col21;
		output += (col22 == null) ? "" : "\ncol22: " + col22;
		output += (col23 == null) ? "" : "\ncol23: " + col23;
		output += (col24 == null) ? "" : "\ncol24: " + col24;
		return output;
	}

	/**
	 * @return List day : ArrayList of String fields col<i>x</i>
	 */
	public List toArray() {
		List day = new ArrayList();
		day.add(col0);
		day.add(col1);
		day.add(col2);
		day.add(col3);
		day.add(col4);
		day.add(col5);
		day.add(col6);
		day.add(col7);
		day.add(col8);
		day.add(col9);
		day.add(col10);
		day.add(col11);
		day.add(col12);
		day.add(col13);
		day.add(col14);
		day.add(col15);
		day.add(col16);
		day.add(col17);
		day.add(col18);
		day.add(col19);
		day.add(col20);
		day.add(col21);
		day.add(col22);
		day.add(col23);
		day.add(col24);
		return day;
	}
}
