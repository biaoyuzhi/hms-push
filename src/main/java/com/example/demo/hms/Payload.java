package com.example.demo.hms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuzh on 2019/1/9.
 * 华为PUSH的payload
 * Payload字段样式如下：
 *  {
 *       "hps": {
 *              "msg" : {
 *                      "type" : 3,
 *                      "body" : {
 *                              "content" : "Push message content",
 *                              "title" : "Push message content"
 *                      },
 *                      "action" : {
 *                              "type" : 1,
 *                              "param" : {
 *                                      "intent":"#Intent;compo=com.rvr/.Activity;S.W=U;end"
 *                              }
 *                      }
 *               },
 * 			    "ext" : {
 * 				    "biTag" : "Trump"
 *              }
 *        }
 *  }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    //华为PUSH消息总结构体
    private Hps hps;//必填
}
