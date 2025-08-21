package com.a3.lfs.service;

import com.a3.lfs.domain.Shop;
import com.a3.lfs.mapper.monitoring.MonitoringMapper;
import com.a3.lfs.mapper.shop.ShopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService{

	private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	private MonitoringMapper monitoringMapper;

	@Autowired
	private ShopMapper shopMapper;

	/**
	 * REMOTE SHOP INFO DB -> MONITORING DB<br/>
	 * DATA SYNC
	 */
	public void sync(){

		logger.info("REMOTE SHOP INFO DB -> MONITORING DB ::::: --- DATA SYNC START ---");

		List<Shop> originalShopList = shopMapper.findAllOriginalShop();

		monitoringMapper.updateShop(originalShopList);

		logger.info("REMOTE SHOP INFO DB -> MONITORING DB ::::: --- DATA SYNC END ---");
	}
}