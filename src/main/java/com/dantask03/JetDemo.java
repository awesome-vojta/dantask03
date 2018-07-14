package com.dantask03;

import com.dantask03.convertor.Convertor;
import com.dantask03.count.JetCounter;
import com.dantask03.entities.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JetDemo {

    private static String JSON = "{\"firstName\":\"Joe\",\"lastName\":\"Smith\",\"title\":\"Developer\",\"salary\":75000,\"hireDate\":\"2009-10-12\"}";
    private static String XML = "<?xml version=\"1.0\"?>\n" +
            "<employeeFile>\n" +
            "  <employee>\n" +
            "    <firstName>Joe</firstName>\n" +
            "    <lastName>Smith</lastName>\n" +
            "    <title>Developer</title>\n" +
            "    <salary>75000</salary>\n" +
            "    <hireDate>2009-10-12</hireDate>\n" +
            "  </employee>\n" +
            "</employeeFile>";

    public static void main(String[] args) throws ParseException, JsonProcessingException {

        JetCounter.countWords(JSON);
        JetCounter.countWords(XML);
    }
}
