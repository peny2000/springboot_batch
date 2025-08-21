package com.a3.lfs.service;

import com.a3.lfs.domain.PiFile;
import com.a3.lfs.domain.PiInspect;
import com.a3.lfs.mapper.monitoring.MonitoringMapper;
import com.a3.lfs.mapper.privacy.PrivacyMapper;
import com.a3.lfs.support.constants.Table;
import com.a3.lfs.support.util.BatchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivacyService{

	private static final Logger logger = LoggerFactory.getLogger(PrivacyService.class);

	@Autowired
	private PrivacyMapper privacyMapper;

	@Autowired
	private MonitoringMapper monitoringMapper;

	/**
	 * REMOTE PRIVACY-I DATA DB -> MONITORING DB(TEMP TABLE)<br/>
	 * Weekly Temp Table
	 */
	public void loadPrivacyIData(String lastDate){

		String originalInspectTable = String.format("PI_LOG_%s.dbo.PI_InspectData", lastDate);
		String originalFileTable = String.format("PI_LOG_%s.dbo.PI_FileData", lastDate);

		logger.info("REMOTE PRIVACY-I DB : Inspect -> MONITORING DB ::::: --- DATA SYNC START ---");
		loadPrivacyIInspectData(originalInspectTable);
		logger.info("REMOTE PRIVACY-I DB : Inspect -> MONITORING DB ::::: --- DATA SYNC END ---");

		logger.info("REMOTE PRIVACY-I DB : File -> MONITORING DB ::::: --- DATA SYNC START ---");
		loadPrivacyIFileData(originalFileTable);
		logger.info("REMOTE PRIVACY-I DB : File -> MONITORING DB ::::: --- DATA SYNC END ---");
	}

	@Transactional
	private void loadPrivacyIInspectData(String tableName){

		try{

			List<PiInspect> originalInspectList = privacyMapper.findAllInspect(tableName);
			int listSize = originalInspectList.size();

			List<List<PiInspect>> sliceList = sliceInspectData(originalInspectList);

			// 5번까지 재시도 가능.
			boolean result = insertPrivacyIInspectData(5, listSize, sliceList);

			if(!result){

				throw new RuntimeException("PrivacyDb-I Inspect Data Insert Fail!");
			}
		}
		catch(Exception e){

			logger.error("load inspect data error", e);
		}
	}

	@Transactional
	private void loadPrivacyIFileData(String tableName){

		try{

			List<PiFile> originalFileList = privacyMapper.findAllFile(tableName);
			int listSize = originalFileList.size();

			List<List<PiFile>> sliceList = sliceFileData(originalFileList);

			// 5번까지 재시도 가능.
			boolean result = insertPrivacyIFileData(5, listSize, sliceList);

			if(!result){

				throw new RuntimeException("PrivacyDb-I File Data Insert Fail!");
			}
		}
		catch(Exception e){

			logger.error("load file data error", e);
		}
	}

	private List<List<PiInspect>> sliceInspectData(List<PiInspect> inspectList){

		List<List<PiInspect>> sliceList = new ArrayList<>();

		int sliceCnt = 0;
		List<PiInspect> sliceTempList = new ArrayList<>();
		for(PiInspect data : inspectList){

			// per 10,000 slice
			if(sliceCnt == 10000){

				sliceList.add(sliceTempList);

				sliceCnt = 0;
				sliceTempList = new ArrayList<>();
			}

			long longIp = data.getAgentIPLong();
			String ip = BatchUtil.longToIp(longIp);

			data.setAgentIP(ip);

			sliceTempList.add(data);
			sliceCnt++;
		}

		sliceList.add(sliceTempList);
		inspectList.clear();

		return sliceList;
	}

	private List<List<PiFile>> sliceFileData(List<PiFile> fileList){

		List<List<PiFile>> sliceList = new ArrayList<>();

		int sliceCnt = 0;
		List<PiFile> sliceTempList = new ArrayList<>();
		for(PiFile data : fileList){

			// per 8,000 slice
			if(sliceCnt == 8000){

				sliceList.add(sliceTempList);

				sliceCnt = 0;
				sliceTempList = new ArrayList<>();
			}

			sliceTempList.add(data);
			sliceCnt++;
		}

		sliceList.add(sliceTempList);
		fileList.clear();

		return sliceList;
	}

	private boolean insertPrivacyIInspectData(int tryCnt, int originalCnt, List<List<PiInspect>> sliceList){

		monitoringMapper.truncate(Table.PI_INSPECT.getTemporary());

		int insertCnt = 0;
		for(List<PiInspect> slice : sliceList){

			insertCnt += monitoringMapper.insertPrivacyInspect(Table.PI_INSPECT.getTemporary(), slice);
		}

		if(originalCnt != insertCnt){

			if(tryCnt == 0){

				logger.error("insert try fail [" + Table.PI_INSPECT.getTemporary() + "]\n" +
						"listSize : " + originalCnt + " / insertCnt : " + insertCnt);
				return false;
			}

			logger.info("tryCnt : " + tryCnt + " / listSize : " + originalCnt + " / insertCnt : " + insertCnt);
			return insertPrivacyIInspectData(--tryCnt, originalCnt, sliceList);
		}
		else{

			return true;
		}
	}

	private boolean insertPrivacyIFileData(int tryCnt, int originalCnt, List<List<PiFile>> sliceList){

		monitoringMapper.truncate(Table.PI_FILE.getTemporary());

		int insertCnt = 0;
		for(List<PiFile> slice : sliceList){

			insertCnt += monitoringMapper.insertPrivacyFile(Table.PI_FILE.getTemporary(), slice);
		}

		if(originalCnt != insertCnt){

			if(tryCnt == 0){

				logger.error("insert try fail [" + Table.PI_FILE.getTemporary() + "]\n" +
						"listSize : " + originalCnt + " / insertCnt : " + insertCnt);
				return false;
			}

			logger.info("tryCnt : " + tryCnt + " / listSize : " + originalCnt + " / insertCnt : " + insertCnt);
			return insertPrivacyIFileData(--tryCnt, originalCnt, sliceList);
		}
		else{

			return true;
		}
	}
}