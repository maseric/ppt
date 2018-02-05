package sax;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Default SAX handler for XML parsing ppt reports file reporting.xml
 * @author mas
 *
 */
public class PageHandler extends DefaultHandler {
	// résultats de notre parsing
	private List report;
	private Page page;
	private List calendar;
	private List issues;
	private List summary;
	private Issue issue;
	private Day day;
	// flags nous indiquant la position du parseur
	private boolean inReport, inPage, inTitle, inWeek, inYear, inTransaction,
			inReporting, inAvailability, inPrevAv, inResponseTime, inPrevRt,
			inRtMax, inRtMin, inAvMax, inAvMin, inCalendar, inIssues, inIssue,
			inBegin, inEnd, inDescription, inImpacts, inActions, inDay, inCol0,
			inCol1, inCol2, inCol3, inCol4, inCol5, inCol6, inCol7, inCol8,
			inCol9, inCol10, inCol11, inCol12, inCol13, inCol14, inCol15,
			inCol16, inCol17, inCol18, inCol19, inCol20, inCol21, inCol22,
			inCol23, inCol24, inSummary, inStatus;
	// buffer nous permettant de récupérer les données
	private StringBuffer buffer;

	// Define a static logger variable so that it references the
	// Logger instance named "PageHandler".
	static Logger logger = Logger.getLogger(PageHandler.class);

	public PageHandler() {

		super();
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("log4j.properties");
	}

	// détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("report")) {
			report = new LinkedList();
			inReport = true;
		} else if (qName.equals("page")) {
			page = new Page();
			inPage = true;
			try {
				String type = attributes.getValue("type");
				page.setType(type);
			} catch (Exception e) {
				logger.error(e, e);
				throw new SAXException(e);
			}

		} else {
			buffer = new StringBuffer();
			if (qName.equals("week")) {
				inWeek = true;
			} else if (qName.equals("year")) {
				inYear = true;
			} else if (qName.equals("calendar")) {
				calendar = new LinkedList();
				inCalendar = true;
			} else if (qName.equals("summary")) {
				summary = new LinkedList();
				inSummary = true;
			} else if (qName.equals("issues")) {
				issues = new LinkedList();
				inIssues = true;
			} else if (qName.equals("title")) {
				inTitle = true;
			} else if (qName.equals("transaction")) {
				inTransaction = true;
			} else if (qName.equals("reporting")) {
				inReporting = true;
			} else if (qName.equals("availability")) {
				inAvailability = true;
			} else if (qName.equals("prev_av")) {
				inPrevAv = true;
			} else if (qName.equals("response_time")) {
				inResponseTime = true;
			} else if (qName.equals("prev_rt")) {
				inPrevRt = true;
			} else if (qName.equals("rt_max")) {
				inRtMax = true;
			} else if (qName.equals("rt_min")) {
				inRtMin = true;
			} else if (qName.equals("av_max")) {
				inAvMax = true;
			} else if (qName.equals("av_min")) {
				inAvMin = true;
			} else {
				// buffer = new StringBuffer();
				// buffer2 = new StringBuffer();
				if (qName.equals("day")) {
					day = new Day();
				} else if (qName.equals("issue")) {
					issue = new Issue();
				} else if (qName.equals("status")) {
					inStatus = true;
				} else {
					if (qName.equals("begin")) {
						inBegin = true;
					} else if (qName.equals("end")) {
						inEnd = true;
					} else if (qName.equals("description")) {
						inDescription = true;
					} else if (qName.equals("impacts")) {
						inImpacts = true;
					} else if (qName.equals("actions")) {
						inActions = true;
					} else if (qName.equals("col0")) {
						inCol0 = true;
					} else if (qName.equals("col1")) {
						inCol1 = true;
					} else if (qName.equals("col2")) {
						inCol2 = true;
					} else if (qName.equals("col3")) {
						inCol3 = true;
					} else if (qName.equals("col4")) {
						inCol4 = true;
					} else if (qName.equals("col5")) {
						inCol5 = true;
					} else if (qName.equals("col6")) {
						inCol6 = true;
					} else if (qName.equals("col7")) {
						inCol7 = true;
					} else if (qName.equals("col8")) {
						inCol8 = true;
					} else if (qName.equals("col9")) {
						inCol9 = true;
					} else if (qName.equals("col10")) {
						inCol10 = true;
					} else if (qName.equals("col11")) {
						inCol11 = true;
					} else if (qName.equals("col12")) {
						inCol12 = true;
					} else if (qName.equals("col13")) {
						inCol13 = true;
					} else if (qName.equals("col14")) {
						inCol14 = true;
					} else if (qName.equals("col15")) {
						inCol15 = true;
					} else if (qName.equals("col16")) {
						inCol16 = true;
					} else if (qName.equals("col17")) {
						inCol17 = true;
					} else if (qName.equals("col18")) {
						inCol18 = true;
					} else if (qName.equals("col19")) {
						inCol19 = true;
					} else if (qName.equals("col20")) {
						inCol20 = true;
					} else if (qName.equals("col21")) {
						inCol21 = true;
					} else if (qName.equals("col22")) {
						inCol22 = true;
					} else if (qName.equals("col23")) {
						inCol23 = true;
					} else if (qName.equals("col24")) {
						inCol24 = true;
					}

					else {
						// erreur, on peut lever une exception
						throw new SAXException("Balise " + qName + " inconnue.");
					}
				}

			}
		}
	}

	// détection fin de balise
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("report")) {
			inReport = false;
		} else if (qName.equals("page")) {
			report.add(page);
			page = null;
			inPage = false;

		} else if (qName.equals("week")) {
			page.setWeek(buffer.toString());
			buffer = null;
			inWeek = false;
		} else if (qName.equals("year")) {
			page.setYear(Integer.parseInt((buffer.toString())));
			buffer = null;
			inYear = false;

		} else if (qName.equals("title")) {
			page.setTitle(buffer.toString());
			buffer = null;
			inTitle = false;
		} else if (qName.equals("transaction")) {
			page.setTransaction(Integer.parseInt((buffer.toString())));
			buffer = null;
			inTransaction = false;
		} else if (qName.equals("reporting")) {
			page.setReporting(Integer.parseInt(buffer.toString()));
			buffer = null;
			inReporting = false;
		} else if (qName.equals("availability")) {
			page.setAvailability((Float.parseFloat((buffer.toString()))));
			buffer = null;
			inAvailability = false;
		} else if (qName.equals("prev_av")) {
			page.setPrevAv(((Float.parseFloat((buffer.toString())))));
			buffer = null;
			inPrevAv = false;
		} else if (qName.equals("response_time")) {
			page.setResponseTime(((Float.parseFloat((buffer.toString())))));
			buffer = null;
			inResponseTime = false;
		} else if (qName.equals("prev_rt")) {
			page.setPrevRt(((Float.parseFloat((buffer.toString())))));
			buffer = null;
			inPrevRt = false;
		} else if (qName.equals("rt_max")) {
			page.setRtMax((Float.parseFloat((buffer.toString()))));
			buffer = null;
			inRtMax = false;
		} else if (qName.equals("rt_min")) {
			page.setRtMin((Float.parseFloat((buffer.toString()))));
			buffer = null;
			inRtMin = false;
		} else if (qName.equals("av_max")) {
			page.setAvMax((Float.parseFloat((buffer.toString()))));
			buffer = null;
			inAvMax = false;
		} else if (qName.equals("av_min")) {
			page.setAvMin((Float.parseFloat((buffer.toString()))));
			buffer = null;
			inAvMin = false;
		} else if (qName.equals("calendar")) {
			page.setCalendar(calendar);
			buffer = null;

			inCalendar = false;
		} else if (qName.equals("day")) {
			calendar.add(day);
			buffer = null;
			day = null;
			inDay = false;
		} else if (qName.equals("col0")) {
			day.setCol0(buffer.toString());
			buffer = null;
			inCol0 = false;

		} else if (qName.equals("issues")) {
			page.setIssues(issues);
			buffer = null;

			inIssues = false;
		} else if (qName.equals("issue")) {
			issues.add(issue);
			issue = null;
			inIssue = false;
			buffer = null;
		} else if (qName.equals("description")) {
			issue.setDescription(buffer.toString());
			buffer = null;
			inDescription = false;
		} else if (qName.equals("begin")) {
			issue.setBegin(buffer.toString());
			buffer = null;
			inBegin = false;
		} else if (qName.equals("end")) {
			issue.setEnd(buffer.toString());
			buffer = null;
			inEnd = false;
		} else if (qName.equals("description")) {
			issue.setDescription(buffer.toString());
			buffer = null;
			inDescription = false;
		} else if (qName.equals("impacts")) {
			issue.setImpacts(buffer.toString());
			buffer = null;
			inImpacts = false;
		} else if (qName.equals("actions")) {
			issue.setActions(buffer.toString());
			buffer = null;
			inActions = false;
		} else if (qName.equals("col0")) {
			day.setCol0(buffer.toString());
			buffer = null;
			inCol0 = false;
		} else if (qName.equals("col1")) {
			day.setCol1(buffer.toString());
			buffer = null;
			inCol1 = false;
		} else if (qName.equals("col2")) {
			day.setCol2(buffer.toString());
			buffer = null;
			inCol2 = false;
		} else if (qName.equals("col3")) {
			day.setCol3(buffer.toString());
			buffer = null;
			inCol3 = false;
		} else if (qName.equals("col4")) {
			day.setCol4(buffer.toString());
			buffer = null;
			inCol4 = false;
		} else if (qName.equals("col5")) {
			day.setCol5(buffer.toString());
			buffer = null;
			inCol5 = false;
		} else if (qName.equals("col6")) {
			day.setCol6(buffer.toString());
			buffer = null;
			inCol6 = false;
		} else if (qName.equals("col7")) {
			day.setCol7(buffer.toString());
			buffer = null;
			inCol7 = false;
		} else if (qName.equals("col8")) {
			day.setCol8(buffer.toString());
			buffer = null;
			inCol8 = false;
		} else if (qName.equals("col9")) {
			day.setCol9(buffer.toString());
			buffer = null;
			inCol9 = false;
		} else if (qName.equals("col10")) {
			day.setCol10(buffer.toString());
			buffer = null;
			inCol10 = false;
		} else if (qName.equals("col11")) {
			day.setCol11(buffer.toString());
			buffer = null;
			inCol11 = false;
		} else if (qName.equals("col12")) {
			day.setCol12(buffer.toString());
			buffer = null;
			inCol12 = false;
		} else if (qName.equals("col13")) {
			day.setCol13(buffer.toString());
			buffer = null;
			inCol13 = false;
		} else if (qName.equals("col14")) {
			day.setCol14(buffer.toString());
			buffer = null;
			inCol14 = false;
		} else if (qName.equals("col15")) {
			day.setCol15(buffer.toString());
			buffer = null;
			inCol15 = false;
		} else if (qName.equals("col16")) {
			day.setCol16(buffer.toString());
			buffer = null;
			inCol16 = false;
		} else if (qName.equals("col17")) {
			day.setCol17(buffer.toString());
			buffer = null;
			inCol17 = false;
		} else if (qName.equals("col18")) {
			day.setCol18(buffer.toString());
			buffer = null;
			inCol18 = false;
		} else if (qName.equals("col19")) {
			day.setCol19(buffer.toString());
			buffer = null;
			inCol19 = false;
		} else if (qName.equals("col20")) {
			day.setCol20(buffer.toString());
			buffer = null;
			inCol20 = false;
		} else if (qName.equals("col21")) {
			day.setCol21(buffer.toString());
			buffer = null;
			inCol21 = false;
		} else if (qName.equals("col22")) {
			day.setCol22(buffer.toString());
			buffer = null;
			inCol22 = false;
		} else if (qName.equals("col23")) {
			day.setCol23(buffer.toString());
			buffer = null;
			inCol23 = false;
		} else if (qName.equals("col24")) {
			day.setCol24(buffer.toString());
			buffer = null;
			inCol24 = false;
		} else if (qName.equals("summary")) {
			page.setSummary(summary);
			summary = null;
			buffer = null;
			inSummary = false;
		} else if (qName.equals("status")) {
			summary.add(buffer.toString());
			buffer = null;
			inStatus = false;
		} else {
			// erreur, on peut lever une exception
			throw new SAXException("Balise " + qName + " inconnue.");
		}
	}

	// détection de caractères
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String lecture = new String(ch, start, length);
		if (buffer != null) {
			buffer.append(lecture);
		}
		/*
		 * if (buffer2 != null) { buffer2.append(lecture); } if (buffer3 !=
		 * null) { buffer3.append(lecture); }
		 */
	}

	// début du parsing
	public void startDocument() throws SAXException {
		logger.debug("Start parsing report");
	}

	// fin du parsing
	public void endDocument() throws SAXException {
		logger.debug("End parsing");

		logger.debug("Resultats du parsing");
		Iterator iter = report.iterator();
		String output = "";
		while (iter.hasNext()) {
			Page p = (Page) iter.next();
			output += p + "\n-----------------------\n";
			/*
			 * // calendar ? if (p.hasCalendar()) { Iterator iter2 =
			 * p.getCalendar().iterator(); while (iter2.hasNext()) { Day day =
			 * (Day) iter2.next(); output += day.toString(); } } // issues ? if
			 * (p.hasIssues()) { Iterator iter2 = p.getIssues().iterator();
			 * while (iter2.hasNext()) { Issue issue = (Issue) iter2.next();
			 * output += issue.toString(); } }
			 */
		}
		logger.debug(output);

	}

	public List getReport() {
		return report;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#resolveEntity(java.lang.String, java.lang.String)
	 */
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException {
		try {
			if (!systemId.startsWith("file:")) {
				return null;
			}
			String pathDtd = systemId.substring(5);

			if (!pathDtd.startsWith("/")) {
				pathDtd = "/" + pathDtd;
			}

			InputStream is = getClass().getResourceAsStream(pathDtd);
			if (null == is) {
				return null;
			}

			return new InputSource(is);
		} catch (Exception e) {
			logger.error(e, e);
			return null;
		}
	}
}
