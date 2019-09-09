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

@WebServlet(name = "UserUpdateServlet", urlPatterns = "/userUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        User user = (User) request.getSession().getAttribute("user");
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br != null){
                json = br.readLine();
            }
            User userGson = gson.fromJson(json,User.class);
            User userGet = userService.getUserId(userGson.getIdUser());
            if (userGet != null){
                userGet.setLogin(user.getLogin());
                userGet.setPassword(user.getPassword());
                userGet.setName(user.getName());
                userGet.setSurname(user.getSurname());
                Boolean res = userService.updateUser(userGet);
                if(res == true){
                    result = "User update";
                }else {
                    result = "User not update";
                }
            }else {
                result = "User not search";
            }
        }else {
            result = "You are not an administrator";
        }
        String rest = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(rest);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
