package com.example.demo.hms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuzh on 2019/1/9.
 * 华为PUSH消息总结构体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hps {
    //Push消息定义，包括：消息类型、消息内容、消息动作
    private Message msg;//必填
    //扩展信息，含BI消息统计，特定展示风格，消息折叠
    private Ext ext;//非必填项

    @Data
    @Builder
    public static final class Ext {
        // 设置消息标签，如果带了这个标签，会在回执中推送给 CP 用于检测某种类型消息的到达率和状态。
        private String biTag;
        //自定义推送消息在通知栏的图标,value为一个公网可以访问的URL
        private String icon;
        // 扩展样例：[{"season":"Spring"},{"weather":"raining"}]说明：这个字段类型必须是 JSON Array，里面是key-value 的一组扩展信息。
        private String[] customize;

    }

}
