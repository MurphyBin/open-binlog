package com.base.noob.ray.noob.controller;


import com.base.noob.ray.noob.NoobThread.NoobUpdate;
import com.base.noob.ray.noob.database.dao.NoobConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoobApp {

    @Autowired
    private NoobUpdate noobUpdate ;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String HelloApp () {
        return "Hello Noob-App";
    }
}
