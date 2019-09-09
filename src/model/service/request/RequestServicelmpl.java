package model.service.request;

import model.dao.request.RequestDao;
import model.dao.request.RequestDaolmpl;
import model.dto.Request;

import java.util.List;

public class RequestServicelmpl implements RequestService {
    private RequestDao requestDao = new RequestDaolmpl();
    @Override
    public Request getRequestId(int idRequest){return requestDao.getRequestId(idRequest);}
    @Override
    public Request getRequestAddress(String typeRequest, String  address){ return requestDao.getRequestAddress(typeRequest, address);}
    @Override
    public List<Request> listRequestUser(int idUser){ return requestDao.listRequestUser(idUser);}
    @Override
    public List<Request> listRequestAll() {return requestDao.listRequestAll();}
    @Override
    public List<Request> listRequestPlanWorks(){return requestDao.listRequestPlanWorks();}
    @Override
    public List<Request> listRequests() { return requestDao.listRequests(); };
    @Override
    public boolean addRequest(Request request){return requestDao.addRequest(request);}
    @Override
    public boolean deleteRequestId(int idRequest){return requestDao.deleteRequestId(idRequest);}
    @Override
    public boolean updateRequest(Request request){return requestDao.updateRequest(request);}
    @Override
    public boolean updateRequestWork(Request request){return requestDao.updateRequestWork(request);}
    @Override
    public boolean updateRequestDelete(Request request){return requestDao.updateRequestDelete(request);}
}
