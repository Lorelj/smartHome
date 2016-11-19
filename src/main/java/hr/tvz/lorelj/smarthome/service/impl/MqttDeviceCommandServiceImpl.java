package hr.tvz.lorelj.smarthome.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hr.tvz.lorelj.smarthome.service.DeviceCommandService;

@Service
public class MqttDeviceCommandServiceImpl implements DeviceCommandService{

	@Autowired
	@Qualifier("mqttOutboundChannel")
	private MessageChannel messageChannel;
	
	private final Map<String,Map<String,String>> deviceActionMap = fillDeviceActionMessageMap();
	
	
	@Override
	public void send(String deviceName, String value) {
		String messageString = fetchActionMessageFromMap(deviceName, value);
		if(messageString!= null){
			Message<String> message = MessageBuilder.withPayload(messageString).build();
			messageChannel.send(message);
		}

	}


	private static Map<String, Map<String, String>> fillDeviceActionMessageMap() {
		Map<String, Map<String, String>> deviceAcionMap = new HashMap<>();
		Map<String, String> lampAcionMap = new HashMap<>();
		Map<String, String> heaterAcionMap = new HashMap<>();
		heaterAcionMap.put("true", "h1");
		heaterAcionMap.put("false", "h0");
		lampAcionMap.put("true", "l1");
		lampAcionMap.put("false", "l0");
		deviceAcionMap.put("lightState", lampAcionMap);
		deviceAcionMap.put("heaterState", heaterAcionMap);
		return deviceAcionMap;
	}
	
	private String fetchActionMessageFromMap(String deviceName, String deviceAction){
		String result = null;
		if(deviceActionMap.get(deviceName)!=null){
			result = deviceActionMap.get(deviceName).get(deviceAction);
		}
		return result;
	}
	

}
