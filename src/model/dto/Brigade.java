package model.dto;

import java.util.Objects;

public class Brigade {
    private int idBrigade;
    private String nameBrigade;
    private int idPlanWork;
    private int idRequest;

    public Brigade() {
    }

    public int getIdBrigade() {
        return idBrigade;
    }

    public void setIdBrigade(int idBrigade) {
        this.idBrigade = idBrigade;
    }

    public String getNameBrigade() {
        return nameBrigade;
    }

    public void setNameBrigade(String nameBrigade) {
        this.nameBrigade = nameBrigade;
    }

    public int getIdPlanWork() {
        return idPlanWork;
    }

    public void setIdPlanWork(int idPlanWork) {
        this.idPlanWork = idPlanWork;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade)) return false;
        Brigade brigade = (Brigade) o;
        return idBrigade == brigade.idBrigade &&
                idPlanWork == brigade.idPlanWork &&
                idRequest == brigade.idRequest &&
                nameBrigade.equals(brigade.nameBrigade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBrigade, nameBrigade, idPlanWork, idRequest);
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "idBrigade=" + idBrigade +
                ", nameBrigade='" + nameBrigade + '\'' +
                ", idPlanWork=" + idPlanWork +
                ", idRequest=" + idRequest +
                '}';
    }
}
