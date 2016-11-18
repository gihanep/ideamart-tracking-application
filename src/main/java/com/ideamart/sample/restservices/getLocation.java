package com.ideamart.sample.restservices;

import com.google.gson.Gson;
import com.ideamart.sample.lbs.LBS;
import com.ideamart.sample.lbs.ResponseBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tharinda on 11/18/16.
 */
public class getLocation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = request.getParameter("pin");
        ResponseBean responseBean;
        LBS lbs = new LBS();
        responseBean = lbs.getLatLonByPin(pin);
        Gson gson = new Gson();
        String result = gson.toJson(responseBean);
        response.setContentType("application/json");
        response.getWriter().write(result);

    }
}
