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
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "RequestListServlet", urlPatterns = "/requestListServlet")
public class RequestListServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole().equals(Role.DISPATCHER)){
            List<Request> requestList = requestService.listRequests();
            String json = gson.toJson(requestList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }else {
            getServletContext().getRequestDispatcher("/html/user/index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
