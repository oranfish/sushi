package com.oranfish.sushiweb.controller;

import com.oranfish.sushiweb.vo.JsonResult;

public class BaseController {
    public JsonResult returnSuccess(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(JsonResult.SUCCESS);
        jsonResult.setMessage("ok");
        jsonResult.setData(data);
        return jsonResult;
    }

    public JsonResult returnError(String code, String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(JsonResult.ERROR);
        jsonResult.setMessage(msg);
        return jsonResult;
    }
}
