package com.as.dao;

import java.util.List;

import com.as.entity.PersonInfo;

public interface PersonInfoDao {
	List<PersonInfo> queryPersonInfo();
	PersonInfo queryPersonInfoByName(String name);
}
