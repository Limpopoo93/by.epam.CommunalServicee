package controller.user;

import com.google.gson.Gson;
import model.dto.Request;
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

@WebServlet(name = "RequestAddUserServlet", urlPatterns = "/requestAddUserServlet")
public class RequestAddUserServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    private UserService userService = new UserServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        User user = (User) request.getSession().getAttribute("user");
        String typeRequest = request.getParameter("typeRequest");
        Integer scale = Integer.valueOf(request.getParameter("scale"));
        String dateFinish = request.getParameter("dateFinish");
        String address = request.getParameter("address");
        Request request1 = requestService.getRequestAddress(typeRequest, address);
        Boolean res;
        if(request1 == null){
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
        request.setAttribute("result", result);
        request.getRequestDispatcher("/html/user/index.html").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
