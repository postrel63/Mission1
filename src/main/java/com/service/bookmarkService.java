package com.service;

import com.dao.bookmarkDAO;
import com.dto.bookMarkDTO;
import com.dto.bookMarkGroupDTO;

import java.sql.Connection;
import java.util.List;
import static com.db.jdbcUtil.*;

public class bookmarkService {

    public List<bookMarkDTO> showBookmark(){
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        List<bookMarkDTO> list = dao.showBookmark();
        close(con);
        return list;
    }
    public int addBookmarkGroup(String name, int seqence){
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        int result = dao.addBookmarkGroup(name,seqence);
        close(con);
        return result;


    }


    public int deleteBookmarkGroup(int nid) {
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        int result = dao.deleteBookmarkGroup(nid);
        close(con);
        return result;
    }

    public List<bookMarkGroupDTO> bookmarkGroupList() {
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        List<bookMarkGroupDTO> list = dao.bookmarkGroupList();
        close(con);
        return list;

    }

    public int addBookmark(int bId, String wName) {
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        int result = dao.addBookmark(bId,wName);

        return result;
    }

    public void deleteBookmark(int dId) {
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        int result = dao.deleteBookmark(dId);
    }

    public List<bookMarkGroupDTO> showBookmarkgroup() {
        bookmarkDAO dao = bookmarkDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);

        List<bookMarkGroupDTO> list = dao.showBookmarkgroup();
        close(con);
        return list;
    }
}
