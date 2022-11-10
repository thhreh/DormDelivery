package com.DormDeliver;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.DormDeliver.entity.Customer;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//
//        // Hello
//        JSONObject customer = new JSONObject();
//        customer.put("email", "thhreh@gmail.com");
//        customer.put("first_name", "Wei");
//        customer.put("last_name", "Yan");
//        customer.put("age", 20);
//        response.getWriter().print(customer);
//    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    ObjectMapper mapper = new ObjectMapper();
    Customer customer= new Customer();
    customer.setEmail("thhreh@gmail.com");
    customer.setPassword("123456");
    customer.setFirstName("Wei");
    customer.setLastName("Yan");
    customer.setEnabled(true);

    response.getWriter().print(mapper.writeValueAsString(customer));
}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read customer information from request body
        ObjectMapper objectMapper = new ObjectMapper();
        Customer custmer = objectMapper.readValue(IOUtils.toString(request.getReader()),Customer.class);

        System.out.println(custmer.getEmail());
        System.out.println(custmer.getFirstName());

        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }

        public void destroy() {
    }
}