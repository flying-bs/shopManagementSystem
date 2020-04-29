package test.java.com.imooc.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.entity.PersonInfo;
import com.as.service.PersonInfoService;

public class PersonInfoServiceTest extends BaseTest{
	@Autowired
	private PersonInfoService personInfoService;
	@Test
	@Ignore
	public void getAreaList(){
		List<PersonInfo> users = personInfoService.getUserList();
		Assert.assertEquals(3,users.size());
	}
	@Test
	public void getPersonInfo(){
		PersonInfo user = personInfoService.getUserByName("test");
		System.out.println(user.getGender());
	}
}
