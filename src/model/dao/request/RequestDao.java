package model.dao.request;

import model.dto.Request;

import java.util.List;

public interface RequestDao {
    Request getRequestId(int idRequest);
    Request getRequestAddress(String typeRequest, String address);
    List<Request> listRequestAll();
    List<Request> listRequestUser(int idUser);
    List<Request> listRequestPlanWorks();
    List<Request> listRequests();
    boolean addRequest(Request request);
    boolean deleteRequestId(int idRequest);
    boolean updateRequest(Request request);
    boolean updateRequestDelete(Request request);
    boolean updateRequestWork(Request request);
}
