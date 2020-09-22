package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "guestBookScript", urlPatterns = {"/bookScript.js"}, loadOnStartup = 1)
public class StaticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/javascript");
        response.setCharacterEncoding("UTF-8");
        StringBuffer buffer = new StringBuffer();

        buffer.append("let apiRequest = function(name, content, date){" +
                "var xhr = new XMLHttpRequest();\n" +
                "xhr.open(\"POST\", \"http://localhost:8080/guestbook\", true);\n" +
                "xhr.setRequestHeader('Content-Type', 'application/json');\n" +
                "xhr.send(JSON.stringify({\n" +
                "   'name': name,\n" +
                "   'content': content" +
                "   'date': date" +
                "}));" +
                "};");
        out.println(buffer);
    }
}
