package hr.tvz.lorelj.smarthome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttOutboundConfig {
	
	
	 @Bean
	    public MqttPahoClientFactory mqttClientFactory() {
	        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
	        factory.setServerURIs("tcp://localhost:1883");

	        return factory;
	    }

	    @Bean
	    @ServiceActivator(inputChannel = "mqttOutboundChannel")
	    public MessageHandler mqttOutbound() {
	        MqttPahoMessageHandler messageHandler =
	                       new MqttPahoMessageHandler("client", mqttClientFactory());
	        messageHandler.setAsync(true);
	        messageHandler.setDefaultTopic("Server2Device");
	        return messageHandler;
	    }

	    @Bean
	    public MessageChannel mqttOutboundChannel() {
	        return new DirectChannel();
	    }

	    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
	    public interface MyGateway {

	        void sendToMqtt(String data);

	    }

}
