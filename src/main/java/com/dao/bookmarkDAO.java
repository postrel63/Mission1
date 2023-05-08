package com.dao;

import com.dto.bookMarkDTO;
import com.dto.bookMarkGroupDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.db.jdbcUtil.*;

public class bookmarkDAO {

    private static bookmarkDAO dao;
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;


    private bookmarkDAO() {
    }

    ;

    public static bookmarkDAO getInstance() {
        if (dao == null) {
            dao = new bookmarkDAO();
        }
        return dao;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public List<bookMarkDTO> showBookmark() {
        List<bookMarkDTO> list = new ArrayList<>();
        try {
            String sql = "select * from bookmark";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String wifiName = rs.getString("wifiname");
                String Date = rs.getString("addDate");
                bookMarkDTO dto = new bookMarkDTO(id, name, wifiName, Date);
                list.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return list;
    }
    public List<bookMarkGroupDTO> showBookmarkgroup() {
        List<bookMarkGroupDTO> list = new ArrayList<>();
        try {
            String sql = "select * from bookmark_group";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sequence= rs.getInt("sequence");
                String addDate = rs.getString("addDate");
                String modifyDate = rs.getString("modifyDate");
                bookMarkGroupDTO dto = new bookMarkGroupDTO(id, name, sequence,addDate,modifyDate);
                list.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return list;
    }
    public int addBookmarkGroup(String name, int seqence) {
        int result = 0;

        try {
            String sql = "INSERT into bookmark_group(name,sequence,addDate) VALUES( ? , ? ,datetime('now'))";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, seqence);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);
        }
        return result;
    }

    public int deleteBookmarkGroup(int nid) {
        int result = 0;

        try {
            String sql = "delete from bookmark_group where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, nid);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }

        return result;
    }

    public List<bookMarkGroupDTO> bookmarkGroupList() {
        List<bookMarkGroupDTO> list = new ArrayList<>();
        try {
            String sql = "select id,name from bookmark_group";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                bookMarkGroupDTO dto = new bookMarkGroupDTO(id,name);
                list.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return list;
    }

    public int addBookmark(int bId, String wName) {
        int result = 0;

        try {
            String sql = "INSERT INTO bookmark(name, wifiname, addDate) " +
                    "VALUES ((SELECT name FROM bookmark_group WHERE id = ?), ?, datetime('now'));";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bId);
            pstmt.setString(2, wName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);
        }

        if (result > 0){
            System.out.println("add성공");
        }else{
            System.out.println("add실패");
        }

        return result;
    }

    public int deleteBookmark(int dId) {
        int result = 0;

        try {
            String sql = "delete from bookmark where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dId);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }
        if (result > 0){
            System.out.println("del성공");
        }else{
            System.out.println("del실패");
        }

        return result;
    }


}
