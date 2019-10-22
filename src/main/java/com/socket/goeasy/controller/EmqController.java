package com.socket.goeasy.controller;

import com.socket.goeasy.emq.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by disvenk.dai on 2018-11-30 16:03
 */
@Controller
@RequestMapping("/emq")
public class EmqController {
    @Autowired
    private MqttGateway mqttGateway;

    @RequestMapping("index")
    @ResponseBody
    public String index(String topic, HttpServletRequest request, ModelMap modelMap) {
        //modelMap.addAttribute("topic", topic);
        return "emq";
    }

    @RequestMapping("/sendMqtt")
    @ResponseBody
    public String sendMqtt(String topic) throws InterruptedException {
        //100条消息睡眠1毫秒
        //1000条消息睡眠4毫秒
        for(int i =1;i<=10000;i++){
            //Thread.sleep(4);//只要睡眠1毫秒就能保证消息不丢失
            mqttGateway.sendToMqtt(i+"",topic,1);
        }

        return "OK";
    }

    @RequestMapping("/sendMqttAll")
    @ResponseBody
    public String sendMqttAll(){
        mqttGateway.sendToMqtt("发给所有人","",1);
        return "OK";
    }
}
