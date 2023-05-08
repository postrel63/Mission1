package com.service;

import com.dao.wifiDAO;
import com.dto.historyDTO;
import com.dto.wifiDTO;
import java.sql.Connection;
import java.util.List;
import static com.db.jdbcUtil.*;

public class wifiService {
    public List<wifiDTO> locateWifi(float LAT, float LNT) {
        wifiDAO dao = wifiDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        List<wifiDTO> list = dao.locateWifi(LAT,LNT);
        close(con);
        return list;
    }

    public List<historyDTO> showHistory()  {
        wifiDAO dao = wifiDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        List<historyDTO> list = dao.showHistory();
        close(con);
        return list;
    }

    public void SaveHistory(float LAT, float LNT)  {
        wifiDAO dao = wifiDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        dao.saveHistory(LAT, LNT);
        close(con);

    }

    public int deleteHistory(int id)  {
        wifiDAO dao = wifiDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        int result = dao.deleteHistory(id);
        close(con);

        return result;
    }

    public wifiDTO wifiDetail(String wId){
        wifiDAO dao = wifiDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        wifiDTO dto = dao.wifiDetail(wId);
        close(con);
        return dto;
    }

}
