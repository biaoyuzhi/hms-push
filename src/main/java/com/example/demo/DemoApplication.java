package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.hms.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        //以下代码可以根据实际需要移到相应的Service层中
        //拼装Payload消息体
        Payload payload = Payload.builder().hps(
                Hps.builder()
                        .msg(Message.builder()
                                .type(3)
                                .body(Body.builder().content("Push message content").title("Push message title").build())
                                .action(Message.Action.builder()
                                        .type(3)
                                        .param(Message.Param.builder()
                                                .appPkgName("com.example.wuzh.myapplication").build()).build()).build())
                        .ext(Hps.Ext.builder().biTag("Trump").build())
                        .build()).build();
        //添加需要被推送的目标设备，可以是一个，也可以是多个。此项目直接写死，实际开发中可以先将该值从APP端获得，再存入数据库，需要时直接数据库中读取
        JSONArray deviceTokens = new JSONArray();
        deviceTokens.add("0862119039555367300003035700CN01");//此值来自于APP端获得的PUSH_TOKEN。目标设备Token，来自app客户端的HuaweiPushRevicer类的onToken方法获得的tokenIn，需保存在服务端
        //调用自定义的推送类，完成消息的推送过程
        SendClient sender = new SendClient();
        String pushResult = sender.push(payload, deviceTokens);
        System.err.println("PUSH推送结果：" + pushResult);
    }

}

