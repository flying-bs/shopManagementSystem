package com.as.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.as.entity.Area;
import com.as.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = (Logger) LoggerFactory.getLogger(AreaController.class);
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listareas", method = RequestMethod.GET)
	@ResponseBody//�ιؼ��ֻὫmap������jackson��ʽ���ؿͻ��ˣ���Ҫע��pom�в���ȱ��Jackson���jar��
	private Map<String, Object> listAreas() {
		logger.info("===start");
		long startTime =System.currentTimeMillis();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		try {
			list = areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.error("Test error!");
		long endTime =System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime-startTime);
		logger.info("===end");
		return modelMap;
	}
}
