package com.a3.lfs.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService{

	@Autowired
	private PrivacyService privacyService;

	@Autowired
	private MonitoringService monitoringService;

	@Autowired
	private ShopService shopService;

	public void makeDailyStatus(){

		String lastMonth = new DateTime().minusDays(1).toString("yyyyMM");
		String lastDate = new DateTime().minusDays(1).toString("yyyyMMdd");

		privacyService.loadPrivacyIData(lastDate);

		monitoringService.syncMyPc(lastMonth, lastDate);
		monitoringService.syncPrivacyI(lastMonth, lastDate);
		monitoringService.syncDailyStatus(lastMonth, lastDate);
		
	}

	public void scenarioDetect(){

		String lastDate = new DateTime().minusDays(1).toString("yyyyMMdd");

		monitoringService.detectTotalScore(lastDate);
		monitoringService.detectMyPcScore(lastDate);
		monitoringService.detectPrivacyInspect(lastDate);
		monitoringService.detectPrivacyFile(lastDate);
		monitoringService.detectShopCheck(lastDate);

		monitoringService.sendAutoAgent(lastDate);
	}

	public void syncShopInfo(){

		shopService.sync();
	}
	
	public void syncUsrDptInfo(){

		monitoringService.usrDptInfoSync();
	}
}