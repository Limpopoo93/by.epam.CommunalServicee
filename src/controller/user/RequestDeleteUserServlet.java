package controller.user;

import com.google.gson.Gson;
import model.dto.Request;
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

@WebServlet(name = "RequestDeleteUserServlet", urlPatterns = "/requestDeleteUserServlet")
public class RequestDeleteUserServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        Request requestGson =gson.fromJson(json,Request.class);
        Request requestGet = requestService.getRequestAddress(requestGson.getTypeRequest(),requestGson.getAddress());
        if(requestGet != null){
            Boolean res = requestService.deleteRequestId(requestGet.getIdRequest());
            if (res == true){
                result = "Request delete";
            }else {
                result = "Request  not delete";
            }
        }else {
            result = "Request not search";
        }
        String js = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(js);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
