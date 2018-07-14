package com.dantask03;

import com.dantask03.entities.Employee;
import com.dantask03.convertor.Convertor;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class EmployeeToJsonTest {

    private static String EXPECTED_JSON = "{\"firstName\":\"Joe\",\"lastName\":\"Smith\",\"title\":\"Developer\",\"salary\":75000,\"hireDate\":\"2009-10-12\"}";

    @Test
    public void test() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2009-10-12");

        Employee e = new Employee();
        e.setFirstName("Joe");
        e.setLastName("Smith");
        e.setTitle("Developer");
        e.setSalary(75000);
        e.setHireDate(date);

        assertEquals(EXPECTED_JSON,Convertor.employeeToJson(e));
    }
}
