package model.service.worker;

import model.dao.worker.WorkerDao;
import model.dao.worker.WorkerDaolmpl;
import model.dto.Worker;

import java.util.List;

public class WorkerServicelmpl implements WorkerService {
    WorkerDao workerDao = new WorkerDaolmpl();
    @Override
    public Worker getWorkerId(int idWorker){return workerDao.getWorkerId(idWorker);}
    @Override
    public Worker getWorkerName(String name, String surname){return  workerDao.getWorkerName(name,surname);}
    @Override
    public List<Worker> listWorkers() {return workerDao.listWorkers();}
    @Override
    public List<Worker> listWorkersBusy() {return workerDao.listWorkersBusy();}
    @Override
    public List<Worker> listBrigadeWorkers(int idBrigade){return workerDao.listBrigadeWorkers(idBrigade);}
    @Override
    public boolean addWorker(Worker worker){return workerDao.addWorker(worker);}
    @Override
    public boolean deleteWorkerId(int idWorker){return workerDao.deleteWorkerId(idWorker);}
    @Override
    public boolean updateWorker(Worker worker){return workerDao.updateWorker(worker);}
    @Override
    public boolean updateWorkerBrigade(Worker worker){return workerDao.updateWorkerBrigade(worker);}
    @Override
    public boolean updateWorkerBrigadeDelete(Worker worker){return workerDao.updateWorkerBrigadeDelete(worker);}
}
