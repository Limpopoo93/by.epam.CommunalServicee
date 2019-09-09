package controller.dispatcher.brigade;

import com.google.gson.Gson;
import model.dto.Brigade;
import model.dto.Role;
import model.dto.User;
import model.service.brigade.BrigadeService;
import model.service.brigade.BrigadeServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BrigadeListServlet", urlPatterns = "/brigadeListServlet")
public class BrigadeListServlet extends HttpServlet {
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Gson gson = new Gson();
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole().equals(Role.DISPATCHER)){
            List<Brigade> brigadeList = brigadeService.listBrigades();
            String json = gson.toJson(brigadeList);
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
