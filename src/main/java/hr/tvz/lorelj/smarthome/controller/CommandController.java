package hr.tvz.lorelj.smarthome.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.lorelj.smarthome.model.Device;
import hr.tvz.lorelj.smarthome.model.DeviceValue;
import hr.tvz.lorelj.smarthome.repository.DeviceRepository;
import hr.tvz.lorelj.smarthome.service.AttributeValueService;
import hr.tvz.lorelj.smarthome.service.DeviceCommandService;
import hr.tvz.lorelj.smarthome.service.impl.MqttDeviceCommandServiceImpl;

@RestController()
public class CommandController {

	@Autowired
	private DeviceCommandService deviceCommandService;

	@Autowired
	private AttributeValueService attributeValueService;
	

	@RequestMapping(value="/command",method = RequestMethod.POST)
	public Map<String, Object> sendCommand(@RequestBody DeviceValue deviceValue) {
		attributeValueService.save(deviceValue);
		deviceCommandService.send(deviceValue.getName(),deviceValue.getValue());
		
		return Collections.EMPTY_MAP;
	}

}
