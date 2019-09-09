package controller.dispatcher.worker;

import com.google.gson.Gson;
import model.dto.Role;
import model.dto.User;
import model.dto.Worker;
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

@WebServlet(name = "WorkerDeleteServlet", urlPatterns = "/workerDeleteServlet")
public class WorkerDeleteServlet extends HttpServlet {
    private WorkerService workerService = new WorkerServicelmpl();
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
            Worker workerGson = gson.fromJson(json,Worker.class);
            Worker worker = workerService.getWorkerName(workerGson.getName(),workerGson.getSurname());
            if (worker !=null){
                Boolean res = workerService.deleteWorkerId(worker.getIdWorker());
                if (res == true){
                    result = "Worker delete";
                }else {
                    result = "Worker not delete";
                }
            }else {
                result = "Worker not delete";
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
