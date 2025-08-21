package com.a3.lfs.domain;

public class SendAgent{

	private int controlType;
	private String targetIp;
	private int stopTime;
	private String message;

	@Override
	public boolean equals(Object o){

		if(this == o){
			return true;
		}
		if(o == null || getClass() != o.getClass()){
			return false;
		}

		SendAgent sendAgent = (SendAgent)o;

		return targetIp.equals(sendAgent.targetIp);
	}

	@Override
	public int hashCode(){

		return targetIp.hashCode();
	}


	// getter & setter function
	public int getControlType(){

		return controlType;
	}

	public void setControlType(int controlType){

		this.controlType = controlType;
	}

	public String getTargetIp(){

		return targetIp;
	}

	public void setTargetIp(String targetIp){

		this.targetIp = targetIp;
	}

	public int getStopTime(){

		return stopTime;
	}

	public void setStopTime(int stopTime){

		this.stopTime = stopTime;
	}

	public String getMessage(){

		return message;
	}

	public void setMessage(String message){

		this.message = message;
	}
}