package hr.tvz.lorelj.smarthome.service;

import org.springframework.messaging.Message;

public interface InboundMessageHandler {
	
	public void handleMessage(Message<?> message);

}
