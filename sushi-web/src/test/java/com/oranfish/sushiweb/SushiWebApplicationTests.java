package com.oranfish.sushiweb;

import com.oranfish.sushiservice.service.UUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SushiWebApplication.class})
public class SushiWebApplicationTests {

    @Autowired
    private UUserService uUserService;

    @Test
    public void contextLoads() {
        System.out.println(uUserService.list());
    }

}
