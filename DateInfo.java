package term_project;

import java.io.*;

public class DateInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6494103764635465275L;
	private int startMonth;
	private int startYear;
	private int endMonth;	

	public DateInfo(int startMonth, int startYear, int endMonth) {
		super();
		this.startMonth = startMonth;
		this.startYear = startYear;
		this.endMonth = endMonth;
	}
	//getters and setters needed for after
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	@Override
	public String toString() {
		return "DateInfo [startMonth=" + startMonth + ", startYear=" + startYear + ", endMonth=" + endMonth + "]";
	}
}
