package com.mingsoft.people.bean;

import java.util.Date;

import com.mingsoft.people.entity.PeopleUserEntity;

public class PeopleBean extends PeopleUserEntity{

	private String  peopleDateTimes;
	
	private String startTime;
	
	private String endTime;

	public String getPeopleDateTimes() {
		return peopleDateTimes;
	}

	public void setPeopleDateTimes(String peopleDateTimes) {
		this.peopleDateTimes = peopleDateTimes;
	}

	public String getStartTime() {
		if(peopleDateTimes != null && peopleDateTimes != "" ){
			return peopleDateTimes.split("至")[0];
		}
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		if(peopleDateTimes != null && peopleDateTimes != "" ){
			return peopleDateTimes.split("至")[1];
		}
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
