package com.a3.lfs.domain;

public class PiInspect{

	private String InspectGuid;
	private String InspectLogTime;
	private int InspectInfo;
	private String InspectStartTime;
	private String InspectEndTime;
	private int InspectCount;
	private int InspectEtcCount;
	private String UserName;
	private String ComputerName;
	private String AgentIP;
	private long AgentIPLong;
	private String UserID;
	private String PolicyName;
	private String TaskName;
	private String DeptName;
	private String DeptFullName;

	public String getInspectGuid(){

		return InspectGuid;
	}

	public void setInspectGuid(String inspectGuid){

		InspectGuid = inspectGuid;
	}

	public String getInspectLogTime(){

		return InspectLogTime;
	}

	public void setInspectLogTime(String inspectLogTime){

		InspectLogTime = inspectLogTime;
	}

	public int getInspectInfo(){

		return InspectInfo;
	}

	public void setInspectInfo(int inspectInfo){

		InspectInfo = inspectInfo;
	}

	public String getInspectStartTime(){

		return InspectStartTime;
	}

	public void setInspectStartTime(String inspectStartTime){

		InspectStartTime = inspectStartTime;
	}

	public String getInspectEndTime(){

		return InspectEndTime;
	}

	public void setInspectEndTime(String inspectEndTime){

		InspectEndTime = inspectEndTime;
	}

	public int getInspectCount(){

		return InspectCount;
	}

	public void setInspectCount(int inspectCount){

		InspectCount = inspectCount;
	}

	public int getInspectEtcCount(){

		return InspectEtcCount;
	}

	public void setInspectEtcCount(int inspectEtcCount){

		InspectEtcCount = inspectEtcCount;
	}

	public String getUserName(){

		return UserName;
	}

	public void setUserName(String userName){

		UserName = userName;
	}

	public String getComputerName(){

		return ComputerName;
	}

	public void setComputerName(String computerName){

		ComputerName = computerName;
	}

	public String getAgentIP(){

		return AgentIP;
	}

	public void setAgentIP(String agentIP){

		AgentIP = agentIP;
	}

	public long getAgentIPLong(){

		return AgentIPLong;
	}

	public void setAgentIPLong(long agentIPLong){

		AgentIPLong = agentIPLong;
	}

	public String getUserID(){

		return UserID;
	}

	public void setUserID(String userID){

		UserID = userID;
	}

	public String getPolicyName(){

		return PolicyName;
	}

	public void setPolicyName(String policyName){

		PolicyName = policyName;
	}

	public String getTaskName(){

		return TaskName;
	}

	public void setTaskName(String taskName){

		TaskName = taskName;
	}

	public String getDeptName(){

		return DeptName;
	}

	public void setDeptName(String deptName){

		DeptName = deptName;
	}

	public String getDeptFullName(){

		return DeptFullName;
	}

	public void setDeptFullName(String deptFullName){

		DeptFullName = deptFullName;
	}
}