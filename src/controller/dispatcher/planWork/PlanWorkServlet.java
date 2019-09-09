package controller.dispatcher.planWork;

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
import java.util.List;

@WebServlet(name = "PlanWorkServlet", urlPatterns = "/planWorkServlet")
public class PlanWorkServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
            List<Request> requestList = requestService.listRequestPlanWorks();
            String json = gson.toJson(requestList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
