package controller.user;

import com.google.gson.Gson;
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

@WebServlet(name = "UserUpdateUserServlet", urlPatterns = "/userUpdateUserServlet")
public class UserUpdateUserServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        User user = (User) request.getSession().getAttribute("user");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        User userGson = gson.fromJson(json,User.class);
        User userGet = userService.getUserId(user.getIdUser());
        if(userGet != null){
            userGet.setLogin(userGson.getLogin());
            userGet.setPassword(userGson.getPassword());
            userGet.setName(userGson.getName());
            userGet.setSurname(userGson.getSurname());
            Boolean res = userService.updateUser(userGet);
            if(res == true){
                result = "User update";
            }else {
                result = "User not update";
            }
        }else {
            result = "User not search";
        }
        String rest = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(rest);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
