package com.tabuyos.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author Tabuyos
 * @Time 2020/4/13 23:13
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@WebServlet(name = "customer", urlPatterns = "/customer")
public class Customer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getSession().getAttribute("name");
        System.out.println("customer");
        System.out.println(name);
        String encode = URLEncoder.encode(name.toString(), "utf-8");
        resp.sendRedirect("test?name="+ encode);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
