package com.kh.calendar.model.vo;

import java.util.Date;

public class Calendar {
	
	private String Title;   // CAL_NO NUMBER PRIMARY KEY,
	private String StartEnd;        // CAL_DATE DATE DEFAULT SYSDATE NOT NULL
	
	public Calendar() {
		super();
	}
	
	public Calendar(String title) {
		super();
		Title = title;
	}

	public Calendar(String title, String startEnd) {
		super();
		Title = title;
		StartEnd = startEnd;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getStartEnd() {
		return StartEnd;
	}

	public void setStartEnd(String startEnd) {
		StartEnd = startEnd;
	}

	@Override
	public String toString() {
		return "Calendar [Title=" + Title + ", StartEnd=" + StartEnd + "]";
	}
	

	

}