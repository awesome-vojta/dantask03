package com.dantask03;

import static org.junit.Assert.*;
import com.dantask03.convertor.Convertor;
import org.junit.Test;

public class XmlToJsonTest {

    private static String EXPECTED_JSON = "{\"employeeFile\":{\"employee\":{\"firstName\":\"Joe\",\"lastName\":\"Smith\",\"hireDate\":\"2009-10-12\",\"title\":\"Developer\",\"salary\":75000}}}";

    private final String XML = "<?xml version=\"1.0\"?>\n" +
            "<employeeFile>\n" +
            "  <employee>\n" +
            "    <firstName>Joe</firstName>\n" +
            "    <lastName>Smith</lastName>\n" +
            "    <title>Developer</title>\n" +
            "    <salary>75000</salary>\n" +
            "    <hireDate>2009-10-12</hireDate>\n" +
            "  </employee>\n" +
            "</employeeFile>";


    @Test
    public void test() {
        assertEquals(EXPECTED_JSON,Convertor.xmlToJson(XML));
    }
}
