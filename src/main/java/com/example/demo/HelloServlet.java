package com.example.demo;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "searchServlet", value = "/search")
public class HelloServlet extends HttpServlet {
    private String message;
    public DB db= new DB();
    public void init() {
        message = "Krya";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String reqeest = request.getParameter("id");
        reqeest= reqeest.toUpperCase(Locale.ROOT);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("        <head>\r\n")
                .append("            <title>Welcome message</title>\r\n")
                .append("        </head>\r\n")
                .append("        <body>\r\n");
        try {
            writer.append("<table>");
            List<WordInfo> resalt = db.searchWords(reqeest);
            List<WordInfo> sortedList = resalt.stream()
                    .sorted(Comparator.comparingInt(WordInfo::getCount))
                    .collect(Collectors.toList());
            writer.append("<tr>");
            writer.append("<td><b>Имя файла</b></td>");
            writer.append("<td><b>Слово</b></td>");
            writer.append("<td><b>Релевантность</b></td>");
            writer.append("<td><b>Путь до файла</b></td>");
            writer.append("</tr>");
            for(int i = sortedList.size()-1; i >= 0; i--) {
                writer.append("<tr>");
                writer.append("<td>"+sortedList.get(i).getFilename()+"</td>");
                writer.append("<td>"+sortedList.get(i).getWord()+"</td>");
                writer.append("<td>"+sortedList.get(i).getCount()+"</td>");
                writer.append("<td>"+sortedList.get(i).getPatch()+"</td>");
                writer.append("</tr>");
            }
            writer.append("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // create HTML response



        writer.append("        </body>\r\n")
                .append("</html>\r\n");
    }

    public void destroy() {
    }
}