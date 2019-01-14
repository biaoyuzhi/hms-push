package com.example.demo.hms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuzh on 2019/1/9.
 * 华为Push消息定义
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    // 1 透传异步消息 ，3 系统通知栏异步消息 注意：2 和 4 以后为保留后续扩展使用
    private Integer type;//必填
    // 消息内容，注意：对于透传类的消息可以是字符串，不必是JSON Object
    private Body body;//必填
    // 消息点击动作
    private Action action;//非必填项

    @Data
    @Builder
    public static final class Action {
        // 1 自定义行为：行为由参数intent定义，2 打开URL：URL地址由参数url定义，3 打开APP：默认值，打开App的首页
        private Integer type;
        // 关于消息点击动作的参数
        private Param param;
    }

    @Data
    @Builder
    public static final class Param {
        //Action的type为1的时候表示自定义行为。
        private String intent;
        //Action的type为2的时候表示打开URL地址
        private String url;
        //需要拉起的应用包名，必须和注册推送的包名一致
        private String appPkgName;
    }

}
