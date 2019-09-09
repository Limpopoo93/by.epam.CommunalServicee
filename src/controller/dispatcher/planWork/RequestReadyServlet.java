package controller.dispatcher.planWork;

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

@WebServlet(name = "RequestReadyServlet",urlPatterns = "/requestReadyServlet")
public class RequestReadyServlet extends HttpServlet {
    private RequestService requestService = new RequestServicelmpl();
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Gson gson = new Gson();
        String resultRequest;
        String json = "";
        String resultBrigade;
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            if (br !=null){
                json = br.readLine();
            }
            Request requestGson = gson.fromJson(json,Request.class);
            Request requestGet = requestService.getRequestAddress(requestGson.getTypeRequest(), requestGson.getAddress());
            Brigade brigadeGet = brigadeService.getBrigadeId(requestGet.getIdBrigade());
            Boolean resRequest = requestService.updateRequestDelete(requestGet);
            Boolean resBrigade = brigadeService.updateBrigadeWorkDelete(brigadeGet);
            if(resRequest == true){
                resultRequest = "Request complete";
            }else {
                resultRequest = "Request not complete";
            }
            if(resBrigade == true){
                resultBrigade = "Brigade delete";
            }else {
                resultBrigade = "Brigade not delete";
            }
            resultRequest = resultRequest + "" + resultBrigade;
        }else {
            resultRequest = "You are not an administrator";
        }

        String js = gson.toJson(resultRequest);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(js);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
