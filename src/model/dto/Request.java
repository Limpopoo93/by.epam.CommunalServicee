package model.dto;

import java.util.Date;
import java.util.Objects;

public class Request {
    private int idRequest;
    private String typeRequest;
    private int scale;
    private Date dateFinish;
    private int idUser;
    private String address;
    private int idPlanWork;
    private int idBrigade;
    private User user;
    private Brigade brigade;

    public Request() {
    }

    public Request(User user, Brigade brigade) {
        this.user = user;
        this.brigade = brigade;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdPlanWork() {
        return idPlanWork;
    }

    public void setIdPlanWork(int idPlanWork) {
        this.idPlanWork = idPlanWork;
    }

    public int getIdBrigade() {
        return idBrigade;
    }

    public void setIdBrigade(int idBrigade) {
        this.idBrigade = idBrigade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request1 = (Request) o;
        return idRequest == request1.idRequest &&
                scale == request1.scale &&
                idUser == request1.idUser &&
                idPlanWork == request1.idPlanWork &&
                idBrigade == request1.idBrigade &&
                typeRequest.equals(request1.typeRequest) &&
                dateFinish.equals(request1.dateFinish) &&
                address.equals(request1.address) &&
                user.equals(request1.user) &&
                brigade.equals(request1.brigade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest, typeRequest, scale, dateFinish, idUser, address, idPlanWork, idBrigade, user, brigade);
    }

    @Override
    public String toString() {
        return "Request{" +
                "idRequest=" + idRequest +
                ", typeRequest='" + typeRequest + '\'' +
                ", scale=" + scale +
                ", dateFinish=" + dateFinish +
                ", idUser=" + idUser +
                ", address='" + address + '\'' +
                ", idPlanWork=" + idPlanWork +
                ", idBrigade=" + idBrigade +
                ", user=" + user +
                ", brigade=" + brigade +
                '}';
    }
}
