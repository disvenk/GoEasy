package com.socket.goeasy.emq;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Created by disvenk.dai on 2018-11-30 16:02
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    //qos重发次数
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic,@Header(MqttHeaders.QOS)int qos);
}
