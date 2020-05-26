package com.oranfish.sushiweb.controller.back;

import com.oranfish.sushidao.dto.UUserDTO;
import com.oranfish.sushiservice.service.UUserService;
import com.oranfish.sushiutil.util.ConfigUtil;
import com.oranfish.sushiweb.controller.BaseController;
import com.oranfish.sushiweb.vo.JsonResult;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/back/user")
public class UUserController extends BaseController {

    @Autowired
    private UUserService uUserService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/list")
    public JsonResult list(@RequestBody UUserDTO uUserDTO){
        UUserDTO dto = uUserService.list().get(0);
        dto.setId(Long.parseLong(String.valueOf(System.currentTimeMillis())+ RandomUtils.nextInt(1, 999)));
        mongoTemplate.insert(dto, "LogCapture");
        return returnSuccess(dto);
    }

    @GetMapping("/list2")
    public JsonResult list2(){
        String a = null;
        try {
            UUserDTO dto = new UUserDTO();
            dto.setId(123123123131231L);
            a = restTemplate.postForObject("http://localhost:8080/sushi/back/user/list", dto, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnSuccess(ConfigUtil.getProperty("test"));
    }

    public static void main(String[] args){
        System.out.println(RandomUtils.nextInt(1, 999));
    }

}
