package com.a3.lfs.mapper.privacy;

import com.a3.lfs.domain.PiFile;
import com.a3.lfs.domain.PiInspect;
import com.a3.lfs.support.annotation.PrivacyDb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@PrivacyDb
public interface PrivacyMapper{

	List<PiInspect> findAllInspect(@Param("tableName") String tableName);
	List<PiFile> findAllFile(@Param("tableName") String tableName);
}