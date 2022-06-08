package com.jam.unfinished.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: SpringCloudStudy
 * @description: 邮件发送实体类
 * @author: Mr.Pu
 * @create: 2022-02-11 00:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailSender implements Serializable {

    private String[] to;

    private String subject;

    private String content;
}
