package controller.dispatcher.brigade;

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
import java.util.List;

@WebServlet(name = "BrigadeListWorkingServlet", urlPatterns = "/brigadeListWorkingServlet")
public class BrigadeListWorkingServlet extends HttpServlet {
    private WorkerService workerService = new WorkerServicelmpl();
    private BrigadeService brigadeService = new BrigadeServicelmpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Gson gson = new Gson();
        if(user.getRole().equals(Role.DISPATCHER)){
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = "";
            if (br !=null){
                json = br.readLine();
            }
            Brigade brigade = gson.fromJson(json,Brigade.class);
            Brigade brigadeGet = brigadeService.getBrigadeName(brigade.getNameBrigade());
            List<Worker> workers = workerService.listBrigadeWorkers(brigadeGet.getIdBrigade());
            String result = gson.toJson(workers);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
        }else {
            getServletContext().getRequestDispatcher("/html/user/index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
