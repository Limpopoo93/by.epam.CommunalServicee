package model.dao.brigade;

import model.dto.Brigade;

import java.util.List;

public interface BrigadeDao {
    Brigade getBrigadeId(int idBrigade);
    Brigade getBrigadeName(String nameBrigade);
    List<Brigade> listBrigades();
    List<Brigade> listBrigadesPlanWork();
    List<Brigade> listBrigadesAll();
    boolean addBrigade(Brigade brigade);
    boolean deleteBrigadeId(int idBrigade);
    boolean updateBrigade(Brigade brigade);
    boolean updateBrigadeWork(Brigade brigade);
    boolean updateBrigadeWorkDelete(Brigade brigade);
}
