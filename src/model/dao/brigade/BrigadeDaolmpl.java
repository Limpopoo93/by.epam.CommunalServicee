package model.dao.brigade;

import model.dto.Brigade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.dao.Connect.getConnection;

public class BrigadeDaolmpl implements BrigadeDao {
    @Override
    public Brigade getBrigadeId(int idBrigade){
        String query = "SELECT * FROM brigade WHERE idBrigade = ? ";
        Brigade brigade = null;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idBrigade);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    brigade = getBrigadeFromResultSet(resultSet);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return brigade;
    }
    @Override
    public Brigade getBrigadeName(String nameBrigade){
        String query = "SELECT * FROM brigade WHERE nameBrigade = ? ";
        Brigade brigade = null;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, nameBrigade);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    brigade = getBrigadeFromResultSet(resultSet);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return brigade;
    }
    @Override
    public List<Brigade> listBrigades() {
        List<Brigade> brigades = new ArrayList<>();
        String query = "SELECT * FROM brigade WHERE brigade.idPlanWork IS NULL ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                brigades.add(getBrigadeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brigades;
    }
    @Override
    public List<Brigade> listBrigadesPlanWork() {
        List<Brigade> brigadesPlanWork = new ArrayList<>();
        String query = "SELECT request.idRequest, request.typeRequest, request.scale, request.dateFinish, request.address, user.name, user.surname, brigade.nameBrigade FROM brigade JOIN request ON brigade.idBrigade = request.idBrigade JOIN user ON request.idUser = user.idUser WHERE brigade.idPlanWork IS NOT NULL";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                brigadesPlanWork.add(getBrigadeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brigadesPlanWork;
    }
    @Override
    public List<Brigade> listBrigadesAll() {
        List<Brigade> brigadesAll = new ArrayList<>();
        String query = "SELECT * FROM brigade";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                brigadesAll.add(getBrigadeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brigadesAll;
    }
    @Override
    public boolean addBrigade(Brigade brigade){
        String query ="INSERT INTO brigade(nameBrigade) VALUE(?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, brigade.getNameBrigade());
            return statement.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteBrigadeId(int idBrigade){
        String query = "DELETE FROM brigade WHERE idbrigade = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idBrigade);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateBrigade(Brigade brigade) {
        String query = "UPDATE brigade SET nameBrigade = ? WHERE idBrigade = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, brigade.getNameBrigade());
            statement.setInt(2, brigade.getIdBrigade());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateBrigadeWork(Brigade brigade) {
        String query = "UPDATE brigade SET nameBrigade = ?, idPlanWork = ?, idRequest = ? WHERE idBrigade = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, brigade.getNameBrigade());
            statement.setInt(2, brigade.getIdPlanWork());
            statement.setInt(3, brigade.getIdRequest());
            statement.setInt(4, brigade.getIdBrigade());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateBrigadeWorkDelete(Brigade brigade) {
        String query = "UPDATE brigade SET nameBrigade = ?, idPlanWork = NULL, idRequest = NULL WHERE idBrigade = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, brigade.getNameBrigade());
            statement.setInt(2, brigade.getIdBrigade());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private Brigade getBrigadeFromResultSet(ResultSet resultSet) throws SQLException{
        Brigade brigade = new Brigade();
        brigade.setIdBrigade(resultSet.getInt("idBrigade"));
        brigade.setNameBrigade(resultSet.getString("nameBrigade"));
        brigade.setIdPlanWork(resultSet.getInt("idPlanWork"));
        brigade.setIdRequest(resultSet.getInt("idRequest"));
        return brigade;
    }
}
