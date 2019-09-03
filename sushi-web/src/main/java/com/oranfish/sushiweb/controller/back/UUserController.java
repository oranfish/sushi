package com.oranfish.sushiweb.controller.back;

import com.oranfish.sushiservice.service.UUserService;
import com.oranfish.sushiutil.util.ConfigUtil;
import com.oranfish.sushiweb.controller.BaseController;
import com.oranfish.sushiweb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/back/user")
public class UUserController extends BaseController {

    @Autowired
    private UUserService uUserService;

    @GetMapping("/list")
    public JsonResult list(){
        return returnSuccess(uUserService.list());
    }

    @GetMapping("/list2")
    public JsonResult list2(){
        return returnSuccess(ConfigUtil.getProperty("test"));
    }

}
