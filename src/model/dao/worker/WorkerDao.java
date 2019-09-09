package model.dao.worker;

import model.dto.Worker;

import java.util.List;

public interface WorkerDao {
    Worker getWorkerId(int idWorker);
    Worker getWorkerName(String name, String surname);
    List<Worker> listWorkers();
    List<Worker> listBrigadeWorkers(int idBrigade);
    List<Worker> listWorkersBusy();
    boolean addWorker(Worker worker);
    boolean deleteWorkerId(int idWorker);
    boolean updateWorker(Worker worker);
    boolean updateWorkerBrigade(Worker worker);
    boolean updateWorkerBrigadeDelete(Worker worker);
}
