package com.restapi.Restfull.API.Server.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class UccController {
    @RequestMapping(value = "/ucc.do", method = RequestMethod.GET)
    public ModelAndView UccLandingPageUser(){
        return new ModelAndView("ucc-user");
    }

    @RequestMapping(value = "/ucc-artist.do", method = RequestMethod.GET)
    public ModelAndView UccLandingPageArtist(){
        return new ModelAndView("ucc-provider");
    }
}
