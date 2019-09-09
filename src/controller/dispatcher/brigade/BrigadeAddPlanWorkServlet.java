package controller.dispatcher.brigade;

import com.google.gson.Gson;
import model.dto.Brigade;
import model.dto.Request;
import model.dto.Role;
import model.dto.User;
import model.service.brigade.BrigadeService;
import model.service.brigade.BrigadeServicelmpl;
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

@WebServlet(name = "BrigadeAddPlanWorkServlet", urlPatterns = "/brigadeAddPlanWorkServlet")
public class BrigadeAddPlanWorkServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        Gson gson = new Gson();
        String json = "";
        int idPlanWork = 1;
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            if(br !=null){
                json = br.readLine();
            }
            Request requestGson = gson.fromJson(json,Request.class);
            Brigade brigade = gson.fromJson(json,Brigade.class);
            Request requestGet = requestService.getRequestAddress(requestGson.getTypeRequest(),requestGson.getAddress());
            if(requestGet != null){
                Brigade brigadeGet = brigadeService.getBrigadeName(brigade.getNameBrigade());
                if (brigadeGet !=null){
                    brigadeGet.setIdPlanWork(idPlanWork);
                    requestGet.setIdPlanWork(idPlanWork);
                    brigadeGet.setIdRequest(requestGet.getIdRequest());
                    requestGet.setIdBrigade(brigadeGet.getIdBrigade());
                    Boolean res = brigadeService.updateBrigadeWork(brigadeGet);
                    Boolean resRequest = requestService.updateRequestWork(requestGet);
                    if(res == true){
                        result = "Brigade added";
                    }else {
                        result = "Brigade not added";
                    }
                    if(resRequest == true){
                        result =result+" " + "Request add";
                    }else {
                        result =result+" " + "Request not add";
                    }
                }else {
                    result = " Brigade not search ";
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
