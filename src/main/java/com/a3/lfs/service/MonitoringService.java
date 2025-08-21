package com.a3.lfs.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a3.lfs.domain.Agent;
import com.a3.lfs.domain.Dpt;
import com.a3.lfs.domain.SendAgent;
import com.a3.lfs.mapper.monitoring.MonitoringMapper;
import com.a3.lfs.support.constants.Table;
import com.a3.lfs.support.util.BatchUtil;

@Service
public class MonitoringService{

	private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

	@Autowired
	private MonitoringMapper monitoringMapper;

	// -------------------------- 통계 용 --------------------------
	/**
	 * 내 PC 지킴이 원본(안랩에서 넣어주는 데이터)테이블에서 내 PC지킴이 로그테이블(monthly)로 일별로 로그 쌓음.
	 */
	public void syncMyPc(String lastMonth, String lastDate){

		logger.info("MONITORING DB : syncMyPc ::::: --- DATA SYNC START ---");
		monitoringMapper.syncMyPc(lastMonth, lastDate);
		logger.info("MONITORING DB : syncMyPc ::::: --- DATA SYNC END ---");
	}

	/**
	 * Privacy-I 원격에서 가져온 임시테이블에서 검출정보 테이블과 검출파일 테이블(daily)에 각각 로그 쌓음
	 */
	public void syncPrivacyI(String lastMonth, String lastDate){

		String tempName = Table.getTemporaryName();

		logger.info("MONITORING DB : syncPrivacyI ::::: --- DATA SYNC START ---");
		monitoringMapper.syncPrivacyI(tempName, lastMonth, lastDate);
		logger.info("MONITORING DB : syncPrivacyI ::::: --- DATA SYNC END ---");
	}

	/**
	 * 내 PC 지킴이와 Privacy-I 에서 쌓은 데이터로 매장/개인정보취급자/전사 일일 통계 테이블로 통계 데이터 쌓음
	 */
	public void syncDailyStatus(String lastMonth, String lastDate){

		logger.info("MONITORING DB : syncDailyStatus ::::: --- DATA SYNC START ---");
		monitoringMapper.syncDailyStatus(lastMonth, lastDate);
		logger.info("MONITORING DB : syncDailyStatus ::::: --- DATA SYNC END ---");
	}


	// -------------------------- 시나리오 탐지 용 --------------------------

	/**
	 * 종합보안점수 시나리오 탐지 프로시져 실행
	 */
	public void detectTotalScore(String lastDate){

		logger.info("MONITORING DB : detectTotalScore ::::: --- DATA SYNC START ---");
		monitoringMapper.detectTotalScore(lastDate);
		logger.info("MONITORING DB : detectTotalScore ::::: --- DATA SYNC END ---");
	}

	/**
	 * 내피씨지킴이 시나리오 탐지 프로시져 실행
	 */
	public void detectMyPcScore(String lastDate){

		logger.info("MONITORING DB : detectMyPcScore ::::: --- DATA SYNC START ---");
		monitoringMapper.detectMyPcScore(lastDate);
		logger.info("MONITORING DB : detectMyPcScore ::::: --- DATA SYNC END ---");
	}

	/**
	 * 프라이버시아이 전일대비 검출건수 시나리오 탐지 프로시져 실행
	 */
	public void detectPrivacyInspect(String lastDate){

		logger.info("MONITORING DB : detectPrivacyInspect ::::: --- DATA SYNC START ---");
		monitoringMapper.detectPrivacyInspect(lastDate);
		logger.info("MONITORING DB : detectPrivacyInspect ::::: --- DATA SYNC END ---");
	}

	/**
	 * 프라이버시아이 검출된 파일 생성일자 기준 시나리오 탐지 프로시져 실행
	 */
	public void detectPrivacyFile(String lastDate){

		logger.info("MONITORING DB : detectPrivacyFile ::::: --- DATA SYNC START ---");
		monitoringMapper.detectPrivacyFile(lastDate);
		logger.info("MONITORING DB : detectPrivacyFile ::::: --- DATA SYNC END ---");
	}

	/**
	 * 매장 체크리스트 시나리오 탐지 프로시져 실행
	 */
	public void detectShopCheck(String lastDate){

		logger.info("MONITORING DB : detectShopCheck ::::: --- DATA SYNC START ---");
		monitoringMapper.detectShopCheck(lastDate);
		logger.info("MONITORING DB : detectShopCheck ::::: --- DATA SYNC END ---");
	}


	private boolean isMatchedIp(String rangeIpStart, String rangeIpEnd, String checkIp){

		try{

			long rangeLongIpStart = BatchUtil.ipToLong(InetAddress.getByName(rangeIpStart));
			long rangeLongIpEnd = BatchUtil.ipToLong(InetAddress.getByName(rangeIpEnd));
			long checkLongIp = BatchUtil.ipToLong(InetAddress.getByName(checkIp));

			return ((checkLongIp >= rangeLongIpStart) && (checkLongIp <= rangeLongIpEnd));
		}
		catch(UnknownHostException e){
			logger.error("ip validate error", e);
		}

		return false;
	}

	/**
	 * 시나리오 탐지된 컴퓨터로 에이전트 통제 실행
	 */
	public void sendAutoAgent(String lastDate){

		logger.info("MONITORING DB : sendAutoAgent ::::: --- DATA SYNC START ---");

		List<Agent> autoAgentList = monitoringMapper.getAutoAgentList();
		List<String> detectedIpList = monitoringMapper.getDetectedIpList(lastDate);

		Set<SendAgent> sendAgentList = new HashSet<>();

		for(Agent agent : autoAgentList){

			String rangeIpStart = agent.getRangeIpStart();
			String rangeIpEnd = agent.getRangeIpEnd();

			for(String checkIp : detectedIpList){

				if(isMatchedIp(rangeIpStart, rangeIpEnd, checkIp)){

					int controlType = agent.getControlType();
					String message = "[긴급 확인요청]\\n정보보호 점검 결과 조치항목이 발생하여 관련 메일을\\n발송하였으니 확인하시고 빠른 조치하시기 바랍니다.";

					SendAgent sendAgent = new SendAgent();

					sendAgent.setTargetIp(checkIp);
					sendAgent.setControlType(controlType);
					sendAgent.setStopTime(agent.getStopTime());
					sendAgent.setMessage((controlType == 0) ? message : "");

					sendAgentList.add(sendAgent);
				}
			}
		}
		
		if(sendAgentList.size() > 0){
			
			monitoringMapper.sendAgent(sendAgentList);
		}

		logger.info("MONITORING DB : sendAutoAgent ::::: --- DATA SYNC END ---");
	}
	
	/**
	 * 개인정보 취급자 정보 추출
	 */
	public void usrDptInfoSync(){
		logger.info("REMOTE USR INFO DB -> MONITORING DB ::::: --- DATA SYNC START ---");
		Dpt dpt = new Dpt();
		List<Map<String, Object>> listMap = monitoringMapper.findAllOriginalUsr();
		
		for(int i=0; i<listMap.size(); i++) {
			String [] strArr = listMap.get(i).get("dpt_nm").toString().split("/");
			dpt.setDptNm(strArr[strArr.length-1]);
			monitoringMapper.updateDpt(dpt);
			
			// 부서ID
			listMap.get(i).put("dpt_id", dpt.getDptId());
			
			// 개인정보 암호화
			listMap.get(i).put("usr_tel", BatchUtil.encAES(listMap.get(i).get("usr_tel").toString(), "dbInfo"));
			listMap.get(i).put("usr_eml", BatchUtil.encAES(listMap.get(i).get("usr_eml").toString(), "dbInfo"));
		}
		
		monitoringMapper.updateUsr(listMap);
		logger.info("REMOTE USR INFO DB -> MONITORING DB ::::: --- DATA SYNC END ---");
	}
}