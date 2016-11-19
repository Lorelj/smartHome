package hr.tvz.lorelj.smarthome.service;

import org.springframework.stereotype.Component;


public interface DeviceCommandService {

	void send(String deviceName, String deviceAction);
}
