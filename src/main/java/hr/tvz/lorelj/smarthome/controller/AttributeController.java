package hr.tvz.lorelj.smarthome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.lorelj.smarthome.service.AttributeValueService;

@RestController
public class AttributeController {
	
	@Autowired
	AttributeValueService attributeValueService;
	
	@RequestMapping("/attribute/fetchLatest")
	@ResponseBody
	public Map<String,Object> fetchLatestAttributesValues(){
		Map<String,Object> result = new HashMap<>();
		result.put("success","true");
		result.put("attributeValues", attributeValueService.fetchLatestValues());
		return result;
	}
}
