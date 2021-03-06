package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author rumman
 * @since 10/26/16
 */
public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByNamePassword(userName, password);
        if (user != null){
            HttpSession session = request.getSession(true);
//            session.setAttribute("currentSessionUser",user);
            session.setAttribute("name",user.getUsername());
            response.sendRedirect("/idea-jsp-servlet-tomcat-example/user-list");
        } else {
            response.sendRedirect("/idea-jsp-servlet-tomcat-example/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/login.jsp").forward(request, response);
    }

}
