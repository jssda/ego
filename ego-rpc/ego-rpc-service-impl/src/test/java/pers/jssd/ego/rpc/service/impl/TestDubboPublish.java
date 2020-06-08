package pers.jssd.ego.rpc.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 发布Dubbo服务
 *
 * @author jssdjing@gmail.com
 */
public class TestDubboPublish {

    @Test
    @Ignore
    public void publishDubboServer() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-dubbo.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-tx.xml",
                "spring/applicationContext-redis.xml"
        );

        applicationContext.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
