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

@WebServlet(name = "UserDeleteServlet", urlPatterns = "/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        Gson gson = new Gson();
        User user3 = (User) request.getSession().getAttribute("user");
        if(user3.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br != null){
                json = br.readLine();
            }
            User userGson = gson.fromJson(json,User.class);
            User user = userService.getUserName(userGson.getName(),userGson.getSurname());
            if( user != null){
                Boolean res = userService.deleteUserId(user.getIdUser());
                if(res == true){
                    result = "User delete";
                }else {
                    result = "User not delete";
                }
            }else {
                result = "User not search";
            }
        }else {
            result = "You are not an administrator";
        }
        String results = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(results);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
