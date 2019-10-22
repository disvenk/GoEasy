package com.socket.goeasy.emq;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by disvenk.dai on 2018-11-30 16:37
 */
public class MqttPushClient {
    private MqttClient client;

    private static volatile MqttPushClient mqttPushClient = null;

    public static MqttPushClient getInstance(){

        if(null == mqttPushClient){
            synchronized (MqttPushClient.class){
                if(null == mqttPushClient){
                    mqttPushClient = new MqttPushClient();
                }
            }

        }
        return mqttPushClient;

    }

    private MqttPushClient() {
        connect();
    }

    private void connect(){
        try {
            client = new MqttClient("tcp://192.168.84.128:1883", "mqttjs_e8022a4d0b", new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName("admin");
            options.setPassword("public".toCharArray());
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            try {
                client.setCallback(new PushCallback());
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布，默认qos为0，非持久化
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic,String pushMessage){
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos,boolean retained,String topic,String pushMessage){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if(null == mTopic){
            System.out.println("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     * @param topic
     */
    public void subscribe(String topic){
        subscribe(topic,0);
    }

    /**
     * 订阅某个主题
     * @param topic
     * @param qos
     */
    public void subscribe(String topic,int qos){
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String kdTopic = "good";
        MqttPushClient.getInstance().publish(0, false, kdTopic, "我是大蚊子");
    }
}
