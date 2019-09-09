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

@WebServlet(name = "RequestDeleteServlet", urlPatterns = "/requestDeleteServlet")
public class RequestDeleteServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        Gson gson = new Gson();
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br != null){
                json = br.readLine();
            }
            Request requestGson =gson.fromJson(json,Request.class);
            Request requestGet = requestService.getRequestAddress(requestGson.getTypeRequest(),requestGson.getAddress());
            if(requestGet != null){
                Boolean res = requestService.deleteRequestId(requestGet.getIdRequest());
                if (res == true){
                    result = "Request delete";
                }else {
                    result = "Request not delete";
                }
            }else {
                result = "Request not search";
            }
        }else {
            result = "You are not an administrator";
        }
        String js = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(js);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
