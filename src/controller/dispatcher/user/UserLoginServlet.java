package controller.dispatcher.user;

import model.dto.Role;
import model.dto.User;
import model.service.user.UserService;
import model.service.user.UserServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", urlPatterns = "/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.getUserLogin(login,password);
        if(user != null){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
              if(user.getRole().equals(Role.DISPATCHER)){
                  request.getSession().setAttribute("user", user);
                  request.getRequestDispatcher("/html/dispatcher/index.html").forward(request, response);
              }else {
                  if(user.getRole().equals(Role.USER)){
                      request.getSession().setAttribute("user", user);
                      request.getRequestDispatcher("/html/user/index.html").forward(request, response);
                  }
              }
            }else {
                request.getRequestDispatcher("/html/dispatcher/user/userLogin.html").forward(request, response);
            }
        }else {
            request.getRequestDispatcher("/html/dispatcher/user/userLogin.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/dispatcher/user/userLogin.html").forward(request, response);
    }
}
