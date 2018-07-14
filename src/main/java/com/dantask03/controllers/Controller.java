package com.dantask03.controllers;

import com.dantask03.entities.Employee;
import com.dantask03.convertor.Convertor;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;


@RestController
public class Controller {
    private final String string = "<?xml version=\"1.0\"?>\n" +
            "<employeeFile>\n" +
            "  <employee>\n" +
            "    <firstName>Joe</firstName>\n" +
            "    <lastName>Smith</lastName>\n" +
            "    <title>Developer</title>\n" +
            "    <salary>75000</salary>\n" +
            "    <hireDate>2009-10-12</hireDate>\n" +
            "  </employee>\n" +
            "</employeeFile>";

    @RequestMapping(value = "/xml")
    public ModelAndView postXML(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("xml",StringEscapeUtils.escapeHtml(string));
        return new ModelAndView("xml");
    }

    @RequestMapping(value = "/instance")
    public ModelAndView showIntance(ModelMap modelMap) throws IOException {
        Employee e = Convertor.parseAndInstantiate();
        modelMap.addAttribute("firstName", e.getFirstName());
        modelMap.addAttribute("lastName", e.getLastName());
        modelMap.addAttribute("salary", e.getSalary());
        modelMap.addAttribute("hireDate", e.getHireDate());
        modelMap.addAttribute("title", e.getTitle());
        return new ModelAndView("instance");
    }


}
