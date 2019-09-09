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

@WebServlet(name = "UserSearchUserServlet", urlPatterns = "/userSearchUserServlet")
public class UserSearchUserServlet extends HttpServlet {
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String jsonUser = "";
        jsonUser=br.readLine();
        Gson gson = new Gson();
        User userGson = gson.fromJson(jsonUser,User.class);
        User userGet = userService.getUserName(userGson.getName(),userGson.getSurname());
        String jsons = gson.toJson(userGet);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsons);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
