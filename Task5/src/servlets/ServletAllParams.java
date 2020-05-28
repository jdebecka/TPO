package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ServletAllParams", urlPatterns = "/map")
public class ServletAllParams extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<html>");
        for(String key: parameterMap.keySet()){
            printWriter.println("<div>");
            printWriter.println(key);
            printWriter.println("</div>");
            for(String value: parameterMap.get(key)){
                printWriter.println("<div>");
                printWriter.println("&nbsp&nbsp-" + value);
                printWriter.println("</div>");
            }
        }
        printWriter.println("</html>");
    }
}
