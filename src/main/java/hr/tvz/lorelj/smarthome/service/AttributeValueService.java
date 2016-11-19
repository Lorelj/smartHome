package hr.tvz.lorelj.smarthome.service;

import java.util.List;

import hr.tvz.lorelj.smarthome.model.AttributeValue;
import hr.tvz.lorelj.smarthome.model.DeviceValue;

public interface AttributeValueService {
     List<DeviceValue>	fetchLatestValues();
     
     AttributeValue save(AttributeValue attributeValue);
     
     List<AttributeValue> save(List<DeviceValue> attributeValues);
     
     AttributeValue save(DeviceValue attributeValue);
}
