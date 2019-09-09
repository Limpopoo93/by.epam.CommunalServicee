package model.dao.request;

import model.dto.Brigade;
import model.dto.Request;
import model.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.dao.Connect.getConnection;

public class RequestDaolmpl implements RequestDao {
    @Override
    public Request getRequestId(int idRequest) {
        String query = "SELECT * FROM request WHERE idRequest = ?";
        Request request = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idRequest);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    request =  getRequestFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request;
    }
    @Override
    public Request getRequestAddress(String typeRequest, String address) {
        String query = "SELECT * FROM request WHERE (typeRequest, address) = (?,?)";
        Request request = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, typeRequest);
            statement.setString(2, address);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    request =  getRequestFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request;
    }
    @Override
    public List<Request> listRequestAll() {
        List<Request> requestsAll = new ArrayList<>();

        String query = "SELECT * FROM request";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                requestsAll.add(getRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestsAll;
    }
    @Override
    public List<Request> listRequestUser(int idUser) {
        List<Request> requestsUser = new ArrayList<>();

        String query = "SELECT * FROM request WHERE idUser = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    requestsUser.add(getRequestFromResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestsUser;
    }
    @Override
    public List<Request> listRequests() {
        List<Request> requests = new ArrayList<>();

        String query = "SELECT * FROM request WHERE request.idPlanWork IS NULL ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                requests.add(getRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    @Override
    public List<Request> listRequestPlanWorks() {
        List<Request> requestsPlanWork = new ArrayList<>();

        String query = "SELECT request.idRequest, request.typeRequest, request.scale, request.dateFinish, request.address,request.idPlanWork, user.name, user.surname, brigade.idBrigade, brigade.nameBrigade FROM request JOIN user ON request.idUser = user.idUser JOIN brigade ON request.idBrigade = brigade.idBrigade WHERE request.idPlanWork IS NOT NULL";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                requestsPlanWork.add(getRequestBrigadeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestsPlanWork;
    }
    @Override
    public boolean addRequest(Request request) {

        String query = "INSERT INTO request(typeRequest, scale, dateFinish, idUser, address) VALUES(?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, request.getTypeRequest());
            statement.setInt(2, request.getScale());
            statement.setDate(3, (java.sql.Date) request.getDateFinish());
            statement.setInt(4, request.getIdUser());
            statement.setString(5, request.getAddress());


            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteRequestId(int idRequest) {
        String query = "DELETE FROM request WHERE idRequest = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idRequest);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateRequest(Request request) {
        String query = "UPDATE request SET typeRequest = ?, scale = ?, dateFinish = ?, idUser = ?, address = ? WHERE idRequest = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getTypeRequest());
            statement.setInt(2, request.getScale());
            statement.setDate(3, Date.valueOf(String.valueOf(request.getDateFinish())));
            statement.setInt(4, request.getIdUser());
            statement.setString(5, request.getAddress());
            statement.setInt(6, request.getIdRequest());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateRequestDelete(Request request) {
        String query = "UPDATE request SET typeRequest = ?, scale = ?, dateFinish = ?, idUser = ?, address = ?, idPlanWork = NULL, idBrigade = NULL WHERE idRequest = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getTypeRequest());
            statement.setInt(2, request.getScale());
            statement.setDate(3, Date.valueOf(String.valueOf(request.getDateFinish())));
            statement.setInt(4, request.getIdUser());
            statement.setString(5, request.getAddress());
            statement.setInt(6, request.getIdRequest());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateRequestWork(Request request) {
        String query = "UPDATE request SET typeRequest = ?, scale = ?, dateFinish = ?, idUser = ?, address = ?, idPlanWork = ?, idBrigade = ? WHERE idRequest = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getTypeRequest());
            statement.setInt(2, request.getScale());
            statement.setDate(3, Date.valueOf(String.valueOf(request.getDateFinish())));
            statement.setInt(4, request.getIdUser());
            statement.setString(5, request.getAddress());
            statement.setInt(6, request.getIdPlanWork());
            statement.setInt(7, request.getIdBrigade());
            statement.setInt(8, request.getIdRequest());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private Request getRequestFromResultSet(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setIdRequest(resultSet.getInt("idRequest"));
        request.setTypeRequest(resultSet.getString("typeRequest"));
        request.setScale(resultSet.getInt("scale"));
        request.setDateFinish(resultSet.getDate("dateFinish"));
        request.setIdUser(resultSet.getInt("idUser"));
        request.setAddress(resultSet.getString("address"));
        request.setIdPlanWork(resultSet.getInt("idPlanWork"));
        request.setIdBrigade(resultSet.getInt("idBrigade"));
        return request;
    }
    private Request getRequestBrigadeFromResultSet(ResultSet resultSet) throws SQLException {
        Request request = new Request(new User(), new Brigade());
        request.setIdRequest(resultSet.getInt("idRequest"));
        request.setTypeRequest(resultSet.getString("typeRequest"));
        request.setScale(resultSet.getInt("scale"));
        request.setDateFinish(resultSet.getDate("dateFinish"));
        request.setAddress(resultSet.getString("address"));
        request.setIdPlanWork(resultSet.getInt("idPlanWork"));
        request.getBrigade().setIdBrigade(resultSet.getInt("idBrigade"));
        request.getBrigade().setNameBrigade(resultSet.getString("nameBrigade"));
        request.getUser().setName(resultSet.getString("name"));
        request.getUser().setSurname(resultSet.getString("surname"));
        return request;
    }
}
