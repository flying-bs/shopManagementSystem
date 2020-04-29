package test.java.com.imooc.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.entity.Area;
import com.as.service.AreaService;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void getAreaList(){
		List<Area> areas = areaService.getAreaList();
		Assert.assertEquals(2,areas.size());
	}
}
