<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        <%--如果是客户端向服务端发送消息，直接走http请求即可--%>
        var goEasy = new GoEasy({
            appkey: "BC-182b5ceb0c114296a92135ed75d92d5a",
            onConnected: function () {
                             console.log("成功连接GoEasy!");
                          },
            onDisconnected: function () {
                            console.log("与GoEasy连接断开!");
                      },
            onConnectFailed: function (error) {
                            console.log("与GoEasy连接失败,错误编码：" + error.code + "错误信息： " + error.content);
                      }
        });
        goEasy.subscribe({
            channel: "${channel}",
            onMessage: function (message) {
                if(confirm(message.content)){
                    console.log(message.content)
                    //window.location.reload();
                }else{
                    //window.location.reload();
                }
            },
            onSuccess: function () {
                //alert("Channel订阅成功。");
            },
            onFailed: function (error) {
                alert("Channel订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
            }
        });
    </script>
</head>
<body>
我是disvenk
</body>
</html>