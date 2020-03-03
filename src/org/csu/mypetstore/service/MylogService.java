package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Mylog;
import org.csu.mypetstore.persistence.MylogDAO;
import org.csu.mypetstore.persistence.impl.MylogDAOlmpl;

import java.util.List;

public class MylogService {
    private MylogDAO mylogDAO;

    public MylogService(){
        mylogDAO = new MylogDAOlmpl();
    }

    public List<Mylog> getMylogList() { return mylogDAO.getMylogList(); }

    public Mylog getMylog(String username) { return mylogDAO.getMylog(username); }

}
