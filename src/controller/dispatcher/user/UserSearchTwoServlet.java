package controller.dispatcher.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

@WebServlet(name = "UserSearchTwoServlet", urlPatterns = "/userSearchTwoServlet")
public class UserSearchTwoServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user3 = (User) request.getSession().getAttribute("user");
            if(user3.getRole().equals(Role.DISPATCHER)){
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                String jsonUser = "";
                jsonUser=br.readLine();
                Gson gson = new Gson();
                User userGson = gson.fromJson(jsonUser,User.class);
                User user = userService.getUserName(userGson.getName(),userGson.getSurname());
                String jsons = gson.toJson(user);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsons);
            }else {
                getServletContext().getRequestDispatcher("/html/user/index.html").forward(request, response);
            }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
