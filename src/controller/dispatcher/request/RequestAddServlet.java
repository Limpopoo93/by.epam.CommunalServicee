package controller.dispatcher.request;

import com.google.gson.Gson;
import model.dto.Request;
import model.dto.Role;
import model.dto.User;
import model.service.request.RequestService;
import model.service.request.RequestServicelmpl;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@WebServlet(name = "RequestAddServlet", urlPatterns = "/requestAddServlet")
public class RequestAddServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String result;
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            String typeRequest = request.getParameter("typeRequest");
            Integer scale = Integer.valueOf(request.getParameter("scale"));
            String dateFinish = request.getParameter("dateFinish");
            String address = request.getParameter("address");
            Request requestGet = requestService.getRequestAddress(typeRequest, address);
            Boolean res;
            if(requestGet == null){
                Request requestNew = new Request();
                requestNew.setTypeRequest(typeRequest);
                requestNew.setScale(scale);
                requestNew.setDateFinish(Date.valueOf(dateFinish));
                requestNew.setIdUser(user.getIdUser());
                requestNew.setAddress(address);
            res = requestService.addRequest(requestNew);
            }else {
                    res = false;
                }
                  if (res == true){
                    result = "Request add";
                  }else {
                    result = "Request not add";
                 }
            request.getRequestDispatcher("/html/dispatcher/request/requestIndex.html").forward(request, response);
        }else {
            result = "You are not an administrator";
            request.getRequestDispatcher("/html/user/index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
