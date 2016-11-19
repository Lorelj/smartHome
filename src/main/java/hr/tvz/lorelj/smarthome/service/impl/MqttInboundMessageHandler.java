package hr.tvz.lorelj.smarthome.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.tvz.lorelj.smarthome.model.DeviceValue;
import hr.tvz.lorelj.smarthome.repository.AttributeRepository;
import hr.tvz.lorelj.smarthome.service.AttributeValueService;
import hr.tvz.lorelj.smarthome.service.InboundMessageHandler;

@Service
public class MqttInboundMessageHandler implements InboundMessageHandler {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	AttributeRepository attributeRepository;
	
	@Autowired
	AttributeValueService attributeValueService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) {
		DeviceValue deviceValue = new DeviceValue();
		try {
			deviceValue = objectMapper.readValue((String)message.getPayload(),DeviceValue.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Send values to websockets
		attributeValueService.save(deviceValue);
		messagingTemplate.convertAndSend("/topic/message", (String)message.getPayload());
		System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + ":"
				+ message.getPayload());
		
	}

}
