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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "BrigadeDeleteServlet", urlPatterns = "/brigadeDeleteServlet")
public class BrigadeDeleteServlet extends HttpServlet {
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        Gson gson = new Gson();
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br !=null){
                json = br.readLine();
            }
            Brigade brigadeGson = gson.fromJson(json,Brigade.class);
            Brigade brigade = brigadeService.getBrigadeName(brigadeGson.getNameBrigade());
            if(brigade !=null){
                Boolean res = brigadeService.deleteBrigadeId(brigade.getIdBrigade());
                if (res == true){
                    result = "Brigade delete";
                }else {
                    result = "Brigade not delete";
                }
            }else {
                result = "Brigade  not search";
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
