package com.a3.lfs.domain;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent{

	private int controlType;
	private String ipRange;
	private int stopTime;

	private String rangeIpStart = "";
	private String rangeIpEnd = "";

	/**
	 * Use ipRange setter function
	 * ipRange setter function called by mybatis
 	 */
	private void calculateRange(){

		String range = StringUtils.trimAllWhitespace(this.ipRange);

		if(range.endsWith(".")){

			range = range.substring(0, range.length() - 1);
		}

		List<String> rangeIpArray = new ArrayList<>(Arrays.asList(StringUtils.delimitedListToStringArray(range, ".")));

		int size = rangeIpArray.size();
		if(size != 4){

			for(int i=size; i<4; i++){

				rangeIpArray.add("*");
			}
		}

		for(String unit : rangeIpArray){

			if(unit.equals("*")){

				this.rangeIpStart += ("0.");
				this.rangeIpEnd += ("255.");
			}
			else{

				this.rangeIpStart += (unit + ".");
				this.rangeIpEnd += (unit + ".");
			}
		}

		this.rangeIpStart = this.rangeIpStart.substring(0, (this.rangeIpStart.length() - 1));
		this.rangeIpEnd = this.rangeIpEnd.substring(0, (this.rangeIpEnd.length() - 1));
	}

	// getter & setter
	public int getControlType(){

		return controlType;
	}

	public void setControlType(int controlType){

		this.controlType = controlType;
	}

	public String getIpRange(){

		return ipRange;
	}

	public void setIpRange(String ipRange){

		this.ipRange = ipRange;
		calculateRange();
	}

	public int getStopTime(){

		return stopTime;
	}

	public void setStopTime(int stopTime){

		this.stopTime = stopTime;
	}

	public String getRangeIpStart(){

		return rangeIpStart;
	}

	public void setRangeIpStart(String rangeIpStart){

		this.rangeIpStart = rangeIpStart;
	}

	public String getRangeIpEnd(){

		return rangeIpEnd;
	}

	public void setRangeIpEnd(String rangeIpEnd){

		this.rangeIpEnd = rangeIpEnd;
	}
}