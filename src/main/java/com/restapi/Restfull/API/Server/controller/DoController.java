package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.models.Test;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.TestService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class DoController implements ControllerInitialize {

    private ModelAndView modelAndView;

    @Autowired
    TestService testService;

    @Override
    public void init(String method) {
        log.info(method);
        modelAndView = new ModelAndView("home");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView HomeDo() {
        init("Get Home");
        Test test = new Test();
        test.setNo(1);
        test.setTestcol("test");
        modelAndView.addObject("test", test);
        return modelAndView;
    }

    @RequestMapping(value = "/home.ajax", method = RequestMethod.GET)
    @ResponseBody
    public String HomeAjaxTest(String object) {
        try {
            JSONObject jsonObject = new JSONObject(object);
            AjaxMessage ajaxMessage = new AjaxMessage();
            ajaxMessage.put("no", jsonObject.getInt("no"));
            ajaxMessage.put("testcol", jsonObject.getString("testcol"));
            return ajaxMessage.getJSONObject("HomeAjaxTest").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return ResMessage.INTERNAL_SERVER_ERROR;
        }
    }
}