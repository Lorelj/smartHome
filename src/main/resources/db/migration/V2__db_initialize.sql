INSERT INTO DEVICE VALUES 
(1,'Motion detection sensor'),
(2,'Sound detection sensor'),
(3,'Light detection sensor'),
(4,'Gass detection sensor'),
(5,'Temperature and humidity sensor'),
(6,'Thermostat'),
(7,'Alarm'),
(8,'Heater'),
(9,'Light');

INSERT INTO ATTRIBUTE (DEV_ID,ATTR_NAME ,ATTR_VALUE_TYPE) 
VALUES (1,'motionDetection','boolean' ), 
(2,'soundDetection','boolean' ),
(3,'lightDetection','boolean' ), 
(4,'gassDetection','boolean'), 
(5,'humidity','double'), 
(5,'temperature','double'), 
(6,'targetTemperature','double'), 
(6,'thermostat','boolean'), 
(7,'alarmState','boolean'),
(8,'heaterState','boolean'),
(7,'lightState','boolean');