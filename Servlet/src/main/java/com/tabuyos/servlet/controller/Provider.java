package com.tabuyos.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Tabuyos
 * @Time 2020/4/13 22:14
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@WebServlet(name = "provider", urlPatterns = "/provider")
public class Provider extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        System.out.println("provider");
        System.out.println(name);
        resp.sendRedirect("customer");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
