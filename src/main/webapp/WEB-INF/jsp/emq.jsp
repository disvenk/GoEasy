<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.min.js" type="text/javascript"></script>--%>
    <script src="/assets/js/mqttws31.min.js" type="text/javascript"></script>
    <title>websocket</title>

    <script type="text/javascript">
        client = new Paho.MQTT.Client("192.168.84.128", Number(8083), "");//建立客户端实例

            client.connect({onSuccess:onConnect});//连接服务器并注册连接成功处理事件
            function onConnect() {   // 连接成功的回调函数
                console.log("onConnected");
                client.subscribe("${topic}");//订阅主题
            }




        client.onConnectionLost = onConnectionLost;//注册连接断开处理事件
        function onConnectionLost(responseObject) {
            if (responseObject.errorCode !== 0) {
                console.log("onConnectionLost:"+responseObject.errorMessage);
                console.log("连接已断开");
            }
        }

        //接收消息
        client.onMessageArrived = onMessageArrived;//注册消息接收处理事件
        function onMessageArrived(message) {
            //console.log("收到消息:"+message.payloadString);
            //console.log("收到消息:"+message.payloadBytes[0]);
            console.log("收到消息:"+message.payloadString);
        }
        // //发送消息
        // message = new Paho.MQTT.Message("hello");
        // message.destinationName = "disvenk";
        // client.send(message);
    </script>

</head>
<body>
<div id="sse">
    <a href="javascript:MqttConnect()">运行WebSocket</a>
</div>

</body>
</html>