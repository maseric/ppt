package sax;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a page of the reporting.
 * 
 * @author mas
 * 
 */
public class Page {
	/**
	 * type of page :
	 * <ul>
	 * <li>title
	 * <li>graphs
	 * <li>calendar
	 * <li>issues
	 * <li>legal
	 * </ul>
	 */
	private String type;
	private String title;
	private String week;
	private int year;
	private int transaction;
	private int reporting;
	private float availability;
	private float prevAv;
	private float responseTime;
	private float prevRt;
	private float rtMax;
	private float rtMin;
	private float avMax;
	private float avMin;
	private List calendar;
	private List issues;
	private List summary;

	public static final String LEGAL = "legal";
	public static final String TITLE = "title";
	public static final String GRAPHS = "graphs";
	public static final String ISSUES = "issues";
	public static final String CALENDAR = "calendar";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTransaction() {
		return transaction;
	}

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	public int getReporting() {
		return reporting;
	}

	public void setReporting(int reporting) {
		this.reporting = reporting;
	}

	public float getAvailability() {
		return availability;
	}

	public void setAvailability(float availability) {
		this.availability = availability;
	}

	public float getPrevAv() {
		return prevAv;
	}

	public void setPrevAv(float prevAv) {
		this.prevAv = prevAv;
	}

	public float getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(float responseTime) {
		this.responseTime = responseTime;
	}

	public float getPrevRt() {
		return prevRt;
	}

	public void setPrevRt(float prevRt) {
		this.prevRt = prevRt;
	}

	public float getRtMax() {
		return rtMax;
	}

	public void setRtMax(float rtMax) {
		this.rtMax = rtMax;
	}

	public float getRtMin() {
		return rtMin;
	}

	public void setRtMin(float rtMin) {
		this.rtMin = rtMin;
	}

	public float getAvMax() {
		return avMax;
	}

	public void setAvMax(float avMax) {
		this.avMax = avMax;
	}

	public float getAvMin() {
		return avMin;
	}

	public void setAvMin(float avMin) {
		this.avMin = avMin;
	}

	public List getCalendar() {
		return calendar;
	}

	public void setCalendar(List calendar) {
		this.calendar = calendar;
	}

	public List getIssues() {
		return issues;
	}

	public void setIssues(List issues) {
		this.issues = issues;
	}

	public String toString() {
		String output = "### PAGE ###";
		output += (type == null) ? "" : "\ntype: " + type;
		output += (title == null) ? "" : "\ntitle: " + title;
		output += (week == null) ? "" : "\nweek: " + week;
		output += (year == 0) ? "" : "\nyear: " + year;
		output += (transaction == 0) ? "" : "\ntransaction: " + transaction;
		output += (reporting) == 0 ? "" : "\nreporting: " + reporting;
		output += (availability == 0) ? "" : "\navailability: " + availability;
		output += (prevAv == 0) ? "" : "\nprevAv: " + prevAv;
		output += (responseTime == 0) ? "" : "\nresponseTime: " + responseTime;
		output += (prevRt == 0) ? "" : "\nprevRt: " + prevRt;
		output += (rtMax == 0) ? "" : "\nrtMax: " + rtMax;
		output += (rtMin == 0) ? "" : "\nrtMin: " + rtMin;
		output += (avMax == 0) ? "" : "\navMax: " + avMax;
		output += (avMin == 0) ? "" : "\navMin: " + avMin;

		// calendar ?
		if (hasCalendar()) {
			output += "\ncalendar: ";
			Iterator iter = calendar.iterator();
			while (iter.hasNext()) {
				Day day = (Day) iter.next();
				output += day.toString();
			}
		}

		// issues ?
		if (hasIssues()) {
			output += "\nissues: ";
			Iterator iter = issues.iterator();
			while (iter.hasNext()) {
				Issue issue = (Issue) iter.next();
				output += issue.toString();
			}
		}

		// Summary status ?
		if (hasSummary()) {
			output += "\nSummary : ";
			Iterator iter = summary.iterator();
			while (iter.hasNext()) {
				String status = (String) iter.next();
				output += "\nStatus : " + status;
			}
		}

		return output;
	}

	public boolean hasCalendar() {
		return (calendar == null) ? false : true;
	}

	public boolean hasIssues() {
		return (issues == null) ? false : true;
	}

	public boolean hasSummary() {
		return (summary == null) ? false : true;
	}

	public List getSummary() {
		return summary;
	}

	public void setSummary(List summary) {
		this.summary = summary;
	}
}
