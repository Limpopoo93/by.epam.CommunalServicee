package model.dto;

import java.util.Objects;

public class Worker {
    private int idWorker;
    private String name;
    private String surname;
    private String typeWorker;
    private int idBrigade;

    public Worker() {
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTypeWorker() {
        return typeWorker;
    }

    public void setTypeWorker(String typeWorker) {
        this.typeWorker = typeWorker;
    }

    public int getIdBrigade() {
        return idBrigade;
    }

    public void setIdBrigade(int idBrigade) {
        this.idBrigade = idBrigade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return idWorker == worker.idWorker &&
                idBrigade == worker.idBrigade &&
                name.equals(worker.name) &&
                surname.equals(worker.surname) &&
                typeWorker.equals(worker.typeWorker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWorker, name, surname, typeWorker, idBrigade);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "idWorker=" + idWorker +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", typeWorker='" + typeWorker + '\'' +
                ", idBrigade=" + idBrigade +
                '}';
    }
}
