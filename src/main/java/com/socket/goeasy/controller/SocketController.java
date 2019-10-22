package com.socket.goeasy.controller;

import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by disvenk.dai on 2018-11-12 14:21
 */

@Controller
@RequestMapping("/socket")
public class SocketController {

    @RequestMapping("index")
    public String index(String channel,HttpServletRequest request, ModelMap modelMap){
        modelMap.addAttribute("channel", channel);
        return "socket";
    }

    @RequestMapping("/push")
    @ResponseBody
    public String socket1(String channel,String msg) {
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io", "BC-182b5ceb0c114296a92135ed75d92d5a");
        goEasy.publish(channel, msg, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("消息发布成功。");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.print("消息发布失败, 错误编码：" + error.getCode() + " 错误信息： " + error.getContent());
            }
        });
        return null;
    }

}
