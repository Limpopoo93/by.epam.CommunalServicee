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
import java.sql.Date;

@WebServlet(name = "RequestUpdateServlet", urlPatterns = "/requestUpdateServlet")
public class RequestUpdateServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.DISPATCHER)) {
            Integer idRequest = Integer.valueOf(request.getParameter("idRequest"));
            String typeRequest = request.getParameter("typeRequest");
            Integer scale = Integer.valueOf(request.getParameter("scale"));
            String dateFinish = request.getParameter("dateFinish");
            String address = request.getParameter("address");
            Request requestGet = requestService.getRequestId(idRequest);
            Boolean res;
            if (requestGet != null) {
                requestGet.setTypeRequest(typeRequest);
                requestGet.setScale(scale);
                requestGet.setDateFinish(Date.valueOf(dateFinish));
                requestGet.setIdUser(user.getIdUser());
                requestGet.setAddress(address);
                res = requestService.updateRequest(requestGet);
            } else {
                res = false;
            }
            if (res == true) {
                result = "Request update";
            } else {
                result = "Request not update";
            }
            request.getRequestDispatcher("/html/dispatcher/request/requestIndex.html").forward(request, response);
        } else {
            result = "You are not an administrator";
           request.getRequestDispatcher("/html/user/index.html").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
