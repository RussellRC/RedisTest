package com.russell.test.redis.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.util.Assert;

import com.russell.test.redis.bo.ServiceUser;
import com.russell.test.redis.bo.ServiceUserImpl;
import com.russell.test.redis.service.HelloService;
import com.russell.test.redis.service.TService;

public class App {

    ApplicationContext ctx;
    
    public App() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Assert.notNull(ctx);
    }
    
    public void hello() {
        
        HelloService helloService = ctx.getBean(HelloService.class);
        
        for (int i = 1; i <= 20; i++) {
            System.out.println("message #" + i + "...");
            System.out.println(helloService.getMessage(String.format("Russell %d", i)));
            System.out.println(helloService.getMessage(String.format("Russell %d", i)));
        }

        RedisCacheManager rcm = ctx.getBean(RedisCacheManager.class);
        System.out.println(rcm.getCacheNames());
        
        System.out.println("Exit");
    }
    
    public void saveUser() {
        ServiceUserImpl user = new ServiceUserImpl();
        user.setEmail("russellrc@gmail.com");
        user.setPassword("secret");
        user.setUsername("Krad");
        
        TService service = ctx.getBean(TService.class);
        service.save(user);
        
        System.out.println("user saved successfully");
    }
    
    public void getUser() {
        TService service = ctx.getBean(TService.class);
        ServiceUser user = service.findByEmail("russellrc@gmail.com");
        
        String str = new ToStringBuilder(user, ToStringStyle.SHORT_PREFIX_STYLE).append("id", user.getId()).append("username", user.getUsername())
                                              .append("email", user.getEmail()).append("password", user.getPassword())
                                              .build();
        
        System.out.println(str);
    }
    
    public static void main(String[] args) {
        App app = new App(); 
        
        app.saveUser();
        app.getUser();
    }
}
