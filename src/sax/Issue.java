package sax;

/**
 * Represents an issue in the issues list of the week the reporting is
 * performed.
 * 
 * @see Template.IssuesTemplate
 * @author mas
 * 
 */
public class Issue {
	/**
	 * begin date of the issue
	 */
	private String begin;
	/**
	 * end date of issue
	 */
	private String end;
	/**
	 * description of the issue
	 */
	private String description;
	/**
	 * impacts of the issue
	 */
	private String impacts;
	/**
	 * actions of the issue
	 */
	private String actions;

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImpacts() {
		return impacts;
	}

	public void setImpacts(String impacts) {
		this.impacts = impacts;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String output = "\nissue :";
		output += (begin == null) ? "" : "\nbegin: " + begin;
		output += (end == null) ? "" : "\nend: " + end;
		output += (end == null) ? "" : "\ndescription: " + description;
		output += (end == null) ? "" : "\nimpacts: " + impacts;
		output += (end == null) ? "" : "\nactions: " + actions;

		return output;
	}
}
