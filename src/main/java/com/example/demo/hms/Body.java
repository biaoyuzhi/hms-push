package com.example.demo.hms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuzh on 2019/1/9.
 * 华为PUSH的消息内容
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Body {
    // 消息内容体
    private String content;//必填
    // 消息标题
    private String title;//必填
}
