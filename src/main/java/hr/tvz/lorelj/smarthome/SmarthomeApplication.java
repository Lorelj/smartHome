package hr.tvz.lorelj.smarthome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import hr.tvz.lorelj.smarthome.config.MqttOutboundConfig.MyGateway;

@SpringBootApplication

public class SmarthomeApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(SmarthomeApplication.class, args);
	}
}
