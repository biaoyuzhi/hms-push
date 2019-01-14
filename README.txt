本项目只用来测试华为PUSH服务的实现。PUSH服务的实现包括APP端的代码开发和服务端的代码开发，
该项目只是服务端的代码，主要实现消息体的编写和获得一个有过期时间的access_token后向deviceToken推送。deviceToken即APP端传过来的PUSH_TOKEN（目标设备Token），它用于识别某一手机上的某一APP。

本项目使用的主要环境如下：
JDK1.8，SpringBoot2.1.2.RELEASE，lombok插件
本项目主要包括的内容：
hms-push
    |
    |-  src
    |    |-  main
    |          |- java
    |               |-  demo
    |                     |- hms：主要包括三部分：实体类（用于拼装最终的Payload类），工具类HmsUtils.java（用于http的发送），消息推送的实现类SendClient.java。
    |                     |
    |                     |- DemoApplication.java：SpringBoot的启动类，本项目直接将其中的main方法作为业务实现的入口。实际项目中可以将相关代码移到实际的service层中。
    |
    |-  pom.xml：本项目中用到的几个依赖：spring-boot-starter-web，lombok，fastjson，commons-io

