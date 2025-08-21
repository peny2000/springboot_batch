package com.a3.lfs.mapper.shop;

import com.a3.lfs.domain.Shop;
import com.a3.lfs.support.annotation.ShopDb;

import java.util.List;

@ShopDb
public interface ShopMapper{

	List<Shop> findAllOriginalShop();
}