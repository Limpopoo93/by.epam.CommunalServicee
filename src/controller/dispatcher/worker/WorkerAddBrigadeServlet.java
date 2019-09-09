package controller.dispatcher.worker;

import com.google.gson.Gson;
import model.dto.Brigade;
import model.dto.Role;
import model.dto.User;
import model.dto.Worker;
import model.service.brigade.BrigadeService;
import model.service.brigade.BrigadeServicelmpl;
import model.service.worker.WorkerService;
import model.service.worker.WorkerServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "WorkerAddBrigadeServlet", urlPatterns = "/workerAddBrigadeServlet")
public class WorkerAddBrigadeServlet extends HttpServlet {
    private WorkerService workerService = new WorkerServicelmpl();
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String result;
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if(br !=null){
                json = br.readLine();
            }
            Brigade brigade = gson.fromJson(json,Brigade.class);
            Worker worker = gson.fromJson(json,Worker.class);
            Brigade brigadeGet = brigadeService.getBrigadeName(brigade.getNameBrigade());
            Worker workerGet = workerService.getWorkerName(worker.getName(),worker.getSurname());
            if(brigadeGet != null){
            if(workerGet != null){
                workerGet.setIdBrigade(brigadeGet.getIdBrigade());
                Boolean res = workerService.updateWorkerBrigade(workerGet);
                if (res == true){
                    result = "Worker add in brigade";
                }else {
                    result = "Worker not add in brigade";
                }
            }else {
                result = "Worker not exists";
            }
            }else {
                result = "Brigade not exists";
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
