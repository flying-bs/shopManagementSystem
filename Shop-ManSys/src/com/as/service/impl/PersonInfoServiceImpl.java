package com.as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.dao.AreaDao;
import com.as.dao.PersonInfoDao;
import com.as.entity.Area;
import com.as.entity.PersonInfo;
import com.as.service.AreaService;
import com.as.service.PersonInfoService;
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public List<PersonInfo> getUserList() {
		// TODO Auto-generated method stub
		return  personInfoDao.queryPersonInfo();
	}

	@Override
	public PersonInfo getUserByName(String name) {
		// TODO Auto-generated method stub
		return personInfoDao.queryPersonInfoByName(name);
	}
	
}
