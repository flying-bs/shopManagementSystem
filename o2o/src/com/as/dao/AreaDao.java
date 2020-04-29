package com.as.dao;

import java.util.List;

import com.as.entity.Area;



public interface AreaDao {

	List<Area> queryArea();

	/**
	 * 
	 * @param area
	 * @return
	 */
	int insertArea(Area area);

	/**
	 * 
	 * @param area
	 * @return
	 */
	int updateArea(Area area);

	/**
	 * 
	 * @param areaId
	 * @return
	 */
	int deleteArea(long areaId);

	/**
	 * 
	 * @param areaIdList
	 * @return
	 */
	int batchDeleteArea(List<Long> areaIdList);
}
