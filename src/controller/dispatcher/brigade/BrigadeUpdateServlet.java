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

@WebServlet(name = "BrigadeUpdateServlet", urlPatterns = "/brigadeUpdateServlet")
public class BrigadeUpdateServlet extends HttpServlet {
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        User user = (User) request.getSession().getAttribute("user");
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if (br !=null){
                json = br.readLine();
            }
            Brigade brigade = gson.fromJson(json,Brigade.class);
            Brigade brigadeGet = brigadeService.getBrigadeId(brigade.getIdBrigade());
            if(brigadeGet != null){
                brigadeGet.setNameBrigade(brigade.getNameBrigade());
                Boolean res = brigadeService.updateBrigade(brigadeGet);
                if (res == true){
                    result = "Brigade update";
                }else {
                    result = "Brigade not update";
                }
            } else {
                result = "Brigade not search";
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
