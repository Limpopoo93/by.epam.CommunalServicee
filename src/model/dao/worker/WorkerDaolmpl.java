package model.dao.worker;

import model.dto.Worker;

import javax.lang.model.type.NullType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.dao.Connect.getConnection;

public class WorkerDaolmpl implements WorkerDao {
    @Override
    public Worker getWorkerId(int idWorker) {
        String query = "SELECT * FROM worker WHERE idWorker = ?";
        Worker worker = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idWorker);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    worker = getWorkerFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return worker;
    }
    @Override
    public Worker getWorkerName(String name, String surname) {
        String query = "SELECT * FROM worker WHERE (name, surname) = (?,?)";
        Worker worker = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, surname);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    worker = getWorkerFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return worker;
    }

    @Override
    public List<Worker> listWorkers() {
        List<Worker> workers = new ArrayList<>();

        String query = "SELECT * FROM worker ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                workers.add(getWorkerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public List<Worker> listBrigadeWorkers(int idBrigade) {
        List<Worker> brigadeWorkers = new ArrayList<>();
        String query = "SELECT * FROM worker WHERE idBrigade = ?";
        Worker worker = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idBrigade);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    while (resultSet != null){
                        worker = getWorkerFromResultSet(resultSet);
                        brigadeWorkers.add(worker);
                        resultSet.next();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return brigadeWorkers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brigadeWorkers;
    }
    @Override
    public List<Worker> listWorkersBusy() {
        List<Worker> workersBusy = new ArrayList<>();

        String query = "SELECT * FROM worker WHERE idBrigade IS NULL ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                workersBusy.add(getWorkerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workersBusy;
    }
    @Override
    public boolean addWorker(Worker worker) {

        String query = "INSERT INTO worker(name, surname, typeWorker) VALUES(?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, worker.getName());
            statement.setString(2, worker.getSurname());
            statement.setString(3, worker.getTypeWorker());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteWorkerId(int idWorker) {
        String query = "DELETE FROM worker WHERE idworker = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idWorker);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateWorker(Worker worker) {
        String query = "UPDATE worker SET name = ?, surname = ?, typeWorker = ? WHERE idWorker = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, worker.getName());
            statement.setString(2, worker.getSurname());
            statement.setString(3, worker.getTypeWorker());
            statement.setInt(4, worker.getIdWorker());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateWorkerBrigade(Worker worker) {
            String query = "UPDATE worker SET name = ?, surname = ?, typeWorker = ?, idBrigade = ? WHERE idWorker = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, worker.getName());
                statement.setString(2, worker.getSurname());
                statement.setString(3, worker.getTypeWorker());
                statement.setInt(4, worker.getIdBrigade());
                statement.setInt(5, worker.getIdWorker());
                return statement.executeUpdate() > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
    }
    @Override
    public boolean updateWorkerBrigadeDelete(Worker worker) {
        String query = "UPDATE worker SET name = ?, surname = ?, typeWorker = ?, idBrigade = NULL WHERE idWorker = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, worker.getName());
            statement.setString(2, worker.getSurname());
            statement.setString(3, worker.getTypeWorker());
            statement.setInt(4, worker.getIdWorker());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private Worker getWorkerFromResultSet(ResultSet resultSet) throws SQLException {
        Worker worker = new Worker();
        worker.setIdWorker(resultSet.getInt("idWorker"));
        worker.setName(resultSet.getString("name"));
        worker.setSurname(resultSet.getString("surname"));
        worker.setTypeWorker(resultSet.getString("typeWorker"));
        worker.setIdBrigade(resultSet.getInt("idBrigade"));
        return worker;
    }
}
