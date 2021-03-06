package com.codecool.servlet;

import com.codecool.model.GuestBook;
import com.codecool.model.GuestEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "guestBook", urlPatterns = {"/guestbook"}, loadOnStartup = 1)
public class GuestBookServlet extends HttpServlet {
    private GuestBook guestBook;
    private String header;
    private String bottomForm;
    private String topBanner;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        this.objectMapper = new ObjectMapper();
        this.guestBook = new GuestBook();
        header = createHeader();
        topBanner = createTopBanner();
        bottomForm = createBottomForm();
    }

    private String createTopBanner(){
        return  "<h1>Welcome to GuestBook</h1>" +
                "<br/>";
    }

    private String createHeader(){
        return "<head>" +
                "<title>Welcome to GuestBook</title>" +
                "<script src=\"bookScript.js\" defer></script>" +
                "</head>\n";
    }

    private String createBottomForm(){
        return  "<div class='bottomForm'>" +
                " <form>" +
                "  <label for='name'>Name:</label><br>" +
                "  <input type='text' id='name' name='name'><br>" +
                "  <label for='content'>Content:</label><br>" +
                "  <input type='text' id='content' name='content'><br>" +
                "  <input type='submit' value='Submit!'>" +
                " </form>" +
                "</div>";
    }

    private String createBookContainer(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div class='bookContainer'>");
        buffer.append("<ul class='book'>");
        guestBook.getGuestBook().forEach(entry -> {
            buffer.append("<li class='entry'>");
            buffer.append("<ul>");
            buffer.append("<li class='name'>");
            buffer.append(entry.getName());
            buffer.append("</li>");
            buffer.append("<li class='date'>");
            buffer.append(entry.getDate());
            buffer.append("</li>");
            buffer.append("<li class='content'>");
            buffer.append(entry.getContent());
            buffer.append("</li>");
            buffer.append("</ul>");
            buffer.append("</li>");
        });
        buffer.append("</ul>");
        buffer.append("</div>");
        return buffer.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GuestEntry guestEntry = objectMapper.readValue(req.getReader(), GuestEntry.class);
        System.out.println(guestEntry.getName());
        System.out.println(guestEntry.getContent());
        System.out.println(guestEntry.getDate().toString());
        guestBook.addEntry(guestEntry);
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String bookContainer = createBookContainer();
        System.out.println(bookContainer);
        out.println(
                "<html>\n" +
                        header +
                        topBanner +
                        "<body>\n" +
                        bookContainer +
                        bottomForm +
                        "</body></html>"
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String bookContainer = createBookContainer();
        out.println(
                "<html>\n" +
                        header +
                        topBanner +
                        "<body>\n" +
                        bookContainer +
                        bottomForm +
                        "</body></html>"
        );
    }
}