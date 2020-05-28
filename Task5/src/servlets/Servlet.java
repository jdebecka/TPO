package servlets;

import users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet", urlPatterns = "/hello")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        session.setMaxInactiveInterval(10);
        User user = (User) session.getAttribute("user");
        if ( user == null ) {
            user = createUser(request);
        }
        if (user != null ){
            session.setAttribute("user", user);
        }
        sendResponce(response, user);
    }

    private void sendResponce(HttpServletResponse response, User user) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("  <body>");
        writer.println("<h1>Test Sesji</h1>");
        if (user!=null && user.getFirstName() != null && user.getLastName() != null)
            writer.println("<div>Witaj " + user.getFirstName() + " " + user.getLastName() + "</div>");
        else
            writer.println("<div>Witaj nieznajomy</div>");
        writer.println("  </body>");
        writer.println("</html>");
    }

    private User createUser(HttpServletRequest request) {
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");

        if( fName == null || lName == null ){
            return null;
        }else {
            return new User(fName, lName);
        }
    }
}
