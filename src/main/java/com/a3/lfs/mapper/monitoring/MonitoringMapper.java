package com.a3.lfs.mapper.monitoring;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.a3.lfs.domain.Agent;
import com.a3.lfs.domain.Dpt;
import com.a3.lfs.domain.PiFile;
import com.a3.lfs.domain.PiInspect;
import com.a3.lfs.domain.SendAgent;
import com.a3.lfs.domain.Shop;
import com.a3.lfs.support.annotation.MonitoringDb;

@MonitoringDb
public interface MonitoringMapper{

	// common truncate execute
	void truncate(@Param("tableName") String tableName);

	int insertPrivacyInspect(
			@Param("tableName") String tableName,
			@Param("inspectList") List<PiInspect> inspectList
	);

	int insertPrivacyFile(
			@Param("tableName") String tableName,
			@Param("fileList") List<PiFile> fileList
	);

	void syncMyPc(
			@Param("lastMonth") String lastMonth,
			@Param("lastDate") String lastDate);

	void syncPrivacyI(
			@Param("tempName") String tempName,
			@Param("lastMonth") String lastMonth,
			@Param("lastDate") String lastDate);

	void syncDailyStatus(
			@Param("lastMonth") String lastMonth,
			@Param("lastDate") String lastDate);

	void updateShop(List<Shop> shopList);
	void updateUsr(List<Map<String, Object>> usrList);
	void updateDpt(Dpt dpt);
	
	void detectTotalScore(String lastDate);
	void detectMyPcScore(String lastDate);
	void detectPrivacyInspect(String lastDate);
	void detectPrivacyFile(String lastDate);
	void detectShopCheck(String lastDate);

	List<Map<String, Object>> findAllOriginalUsr();
	List<String> getDetectedIpList(String lastDate);
	List<Agent> getAutoAgentList();
	void sendAgent(Set<SendAgent> sendAgentList);
}