package model.service.brigade;

import model.dao.brigade.BrigadeDao;
import model.dao.brigade.BrigadeDaolmpl;
import model.dto.Brigade;

import java.util.List;

public class BrigadeServicelmpl implements BrigadeService {
    private BrigadeDao brigadeDao = new BrigadeDaolmpl();
    @Override
    public Brigade getBrigadeId(int idBrigade){return brigadeDao.getBrigadeId(idBrigade);}
    @Override
    public Brigade getBrigadeName(String nameBrigade){return brigadeDao.getBrigadeName(nameBrigade);}
    @Override
    public List<Brigade> listBrigades(){return brigadeDao.listBrigades();}
    @Override
    public List<Brigade> listBrigadesPlanWork() {return brigadeDao.listBrigadesPlanWork();}
    @Override
    public List<Brigade> listBrigadesAll() {return brigadeDao.listBrigadesAll();}
    @Override
    public boolean addBrigade(Brigade brigade){return brigadeDao.addBrigade(brigade);}
    @Override
    public boolean deleteBrigadeId(int idBrigade){return brigadeDao.deleteBrigadeId(idBrigade);}
    @Override
    public boolean updateBrigade(Brigade brigade){return brigadeDao.updateBrigade(brigade);}
    @Override
    public boolean updateBrigadeWork(Brigade brigade){return brigadeDao.updateBrigadeWork(brigade);}
    @Override
    public boolean updateBrigadeWorkDelete(Brigade brigade){return brigadeDao.updateBrigadeWorkDelete(brigade);}
}
