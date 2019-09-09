package controller.dispatcher.user;

import com.google.gson.Gson;
import model.dto.Role;
import model.dto.User;
import model.service.user.UserService;
import model.service.user.UserServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "UserAddServlet", urlPatterns = "/userAddServlet")
public class UserAddServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String jsonUser = "";
            if(br !=null){
                jsonUser = br.readLine();
            }
            User user = gson.fromJson(jsonUser,User.class);
            User userGet = userService.getUserLogin(user.getLogin(), user.getPassword());
            Boolean res;
            if(userGet == null){
                user.setRole(Role.USER);
                res =  userService.addUser(user);
            }else {
                res = false;
            }
            if(res == true){
                result = "User add";
            }else {
                result = "User not add";
            }
        String rest = gson.toJson(result);
       response.setContentType("application/json");
       response.setCharacterEncoding("UTF-8");
       response.getWriter().write(rest);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/dispatcher/user/userAdd.html").forward(request, response);
    }
}
