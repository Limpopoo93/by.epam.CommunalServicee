package controller.dispatcher.request;

import com.google.gson.Gson;
import model.dto.Request;
import model.dto.Role;
import model.dto.User;
import model.service.request.RequestService;
import model.service.request.RequestServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "RequestSearchTwoServlet", urlPatterns = "/requestSearchTwoServlet")
public class RequestSearchTwoServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br !=null){
                json = br.readLine();
            }
            Request requestGson = gson.fromJson(json,Request.class);
            Request requestGet = requestService.getRequestAddress(requestGson.getTypeRequest(),requestGson.getAddress());
            String res = gson.toJson(requestGet);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(res);
        }else {
            getServletContext().getRequestDispatcher("/html/user/index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
