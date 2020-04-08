package Controller;

import Model.Integers;
import Service.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "classes.Calculator", urlPatterns = "/calculator")
public class Calculator extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addIntegers(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addIntegers(request, response);
    }

    private void addIntegers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            int firstInt = Integer.parseInt(request.getParameter("first"));
            int secondInt = Integer.parseInt(request.getParameter("second"));

            Integers integers = new Integers(new int[]{ firstInt, secondInt } );
            int added = Functions.addIntegers( integers.getIntegersToAdd() );

            request.getSession().setAttribute("response", added);
            response.sendRedirect("/WebApp_1_war_exploded/");
        }catch (NumberFormatException e) {
            request.setAttribute("response", "I asked you to put ints and you didn't listen. I am disappointed");
            request.getRequestDispatcher("/").forward(request, response);
        }
    }
}
