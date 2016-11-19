package hr.tvz.lorelj.smarthome.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.lorelj.smarthome.model.Attribute;
import hr.tvz.lorelj.smarthome.model.AttributeValue;
import hr.tvz.lorelj.smarthome.model.DeviceValue;
import hr.tvz.lorelj.smarthome.repository.AttributeRepository;
import hr.tvz.lorelj.smarthome.repository.AttributeValueRepository;
import hr.tvz.lorelj.smarthome.service.AttributeValueService;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

	@Autowired
	AttributeValueRepository attributeValueRepository;
	
	@Autowired
	AttributeRepository attributeRepository;
	
	@Override
	public List<DeviceValue> fetchLatestValues() {
		List<DeviceValue> latestDeviceValues =  convertQueryResult(attributeValueRepository.fetchLatestAttributeValues());
		return latestDeviceValues;
	}

	private List<DeviceValue> convertQueryResult(List<Object[]> latestDeviceValues) {
		List<DeviceValue> deviceValues = new ArrayList<>();
		if(latestDeviceValues !=null){
			for(Object[] latestDeviceValue : latestDeviceValues){
				DeviceValue deviceValue = new DeviceValue();
				deviceValue.setName((String)latestDeviceValue[0]);
				deviceValue.setValue((String)latestDeviceValue[1]);
				deviceValues.add(deviceValue);
			}
		}
		return deviceValues;
	}

	@Override
	public AttributeValue save(AttributeValue attributeValue) {
		return attributeValueRepository.save(attributeValue);
	}

	@Override
	@Transactional
	public List<AttributeValue> save(List<DeviceValue> attributeValues) {
		List<AttributeValue> resultList = new ArrayList<AttributeValue>();
		for(DeviceValue deviceValue: attributeValues ){
			resultList.add(save(deviceValue));
		}
		return resultList;
	}

	@Override
	@Transactional
	public AttributeValue save(DeviceValue deviceValue) {
		Attribute attribute = attributeRepository.findByName(deviceValue.getName());
		AttributeValue deviceState = new AttributeValue();
		deviceState.setAttribute(attribute);
		deviceState.setTimeStamp(new Date());
		deviceState.setValue(deviceValue.getValue());
		return attributeValueRepository.save(deviceState);
	}

}
