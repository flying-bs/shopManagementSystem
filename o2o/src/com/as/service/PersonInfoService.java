package com.as.service;

import java.util.List;

import com.as.entity.PersonInfo;

public interface PersonInfoService {
	List<PersonInfo> getUserList();
	PersonInfo getUserByName(String name);
}
