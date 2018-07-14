package com.dantask03.convertor;


import com.dantask03.entities.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Convertor {

    private static String PATH = "/home/vojta/Documents/development/java/dantask03/src/main/java/com/dantask03/convertor/";

    // Get xml, output employee
    public static Employee parseAndInstantiate() throws IOException {
        // Get xml
        Document d = Jsoup.connect("http://localhost:8080/xml").get();
        String xml = d.body().text(); // xml version, fileName, objectName included

        // Instantiate Employee via xml
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PATH +"mapping.xml");
        Unmarshaller unmarshaller = factory.createUnmarshaller("employeeFile");
        Employee e = (Employee) unmarshaller.unmarshal(xml);

        return e;
    }

    public static String employeeToJson(Employee e) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
        String json = mapper.writeValueAsString(e);
        return json;
    }

    public static String xmlToJson(String xml) {
        JSONObject object = XML.toJSONObject(xml);
        return object.toString();
    }
}
