package com.lin.community;

import com.lin.community.utils.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailTest {

    @Autowired
    private MailClient client;

    //调用模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    //在测试中使用thymeLeaf
    @Test
    public void test(){
        //存入数据
        Context context=new Context();
        context.setVariable("username","hello");
        //处理模板
        String process = templateEngine.process("/mail/demo", context);
        System.out.println(process);
        //将处理后的模板作为参数传递
        client.sendMail("2592000936@qq.com","thymeLeaf",process);
    }

}
