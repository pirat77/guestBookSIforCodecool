package com.codecool.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "guestBookScript", urlPatterns = {"/bookScript.js"}, loadOnStartup = 1)
public class StaticServlet extends HttpServlet {
    private String apiRequestScript;
    private String submitFormBehavior;
    private String postFormScript;
    private String wholeScript;
    @Override
    public void init() throws ServletException {
        super.init();
        apiRequestScript = createApiRequest();
        submitFormBehavior = defineSubmitButtonBehavior();
        postFormScript = postFormScriptBuilder();
        wholeScript = apiRequestScript + postFormScript + submitFormBehavior;
    }

    private String postFormScriptBuilder(){
        return  "let postForm = function(){" +
                "   let name = document.getElementById('name').value;" +
                "   let content = document.getElementById('content').value;" +
                "   let time = Date.now();" +
                "   apiRequest(name, content, time);" +
                "   location.reload();" +
                "};";
    }

    private String createApiRequest(){
        return  "let apiRequest = function(name, content, date){" +
                "let xhr = new XMLHttpRequest();\n" +
                "xhr.open(\"POST\", \"http://localhost:8080/guestbook\", false);\n" +
                "xhr.setRequestHeader('Content-Type', 'application/json');\n" +
                "xhr.send(JSON.stringify({\n" +
                "   'name': name,\n" +
                "   'content': content,\n" +
                "   'date': date" +
                "}));" +
                "};";
    }

    private String defineSubmitButtonBehavior(){
        return  "document.querySelector('[type=\"submit\"]').addEventListener('click', function (event){" +
                "   event.preventDefault();" +
                "   postForm();" +
                "});";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/javascript");
        response.setCharacterEncoding("UTF-8");
        out.println(wholeScript);
    }
}
