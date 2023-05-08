package com.dao;

import com.db.jdbcUtil;
import com.dto.historyDTO;
import com.dto.wifiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.db.jdbcUtil.*;

public class wifiDAO {

    private static wifiDAO dao;
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    private wifiDAO() {
    }

    ;

    public static wifiDAO getInstance() {
        if (dao == null) {
            dao = new wifiDAO();
        }
        return dao;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }


    public int saveDB(List<wifiDTO> dtoArray) {


        int batchSize = 1000;
        int count = 0;

        try {
            con.setAutoCommit(false); // Auto Commit 해제

            String sql = "insert into wifi_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);

            for (wifiDTO wifidto : dtoArray) {
                pstmt.setString(1, wifidto.getX_SWIFI_MGR_NO());
                pstmt.setString(2, wifidto.getX_SWIFI_WRDOFC());
                pstmt.setString(3, wifidto.getX_SWIFI_MAIN_NM());
                pstmt.setString(4, wifidto.getX_SWIFI_ADRES1());
                pstmt.setString(5, wifidto.getX_SWIFI_ADRES2());
                pstmt.setString(6, wifidto.getX_SWIFI_INSTL_FLOOR());
                pstmt.setString(7, wifidto.getX_SWIFI_INSTL_TY());
                pstmt.setString(8, wifidto.getX_SWIFI_INSTL_MBY());
                pstmt.setString(9, wifidto.getX_SWIFI_SVC_SE());
                pstmt.setString(10, wifidto.getX_SWIFI_CMCWR());
                pstmt.setString(11, wifidto.getX_SWIFI_CNSTC_YEAR());
                pstmt.setString(12, wifidto.getX_SWIFI_INOUT_DOOR());
                pstmt.setString(13, wifidto.getX_SWIFI_REMARS3());
                pstmt.setFloat(14, wifidto.getLat());
                pstmt.setFloat(15, wifidto.getLnt());
                pstmt.setString(16, wifidto.getWork_DTTM());

                pstmt.addBatch(); // Batch에 SQL 작업 추가
                count++;

                if (count % batchSize == 0) {
                    pstmt.executeBatch(); // Batch 실행
                }
            }
            int[] result = pstmt.executeBatch(); // Batch 실행 및 성공 개수 반환
            con.commit(); // Transaction Commit

            if (Arrays.stream(result).allMatch(r -> r >= 0)) { // 모두 성공한 경우
                System.out.println("저장 성공");
                return 1;
            } else { // 실패한 경우
                System.out.println("저장 실패");
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);


        }
    }

    public List<wifiDTO> locateWifi(float LAT, float LNT) {
        List<wifiDTO> list = new ArrayList<>();
        try {

            String sql = " select abs(((LAT-?)*(LAT-?))+((LNT-?)*(LNT-?))) as dis, * from wifi_Info  ORDER by dis asc LIMIT 20";

            System.out.println(LAT);
            System.out.println(LNT);
            pstmt = con.prepareStatement(sql);
            pstmt.setFloat(1, LAT);
            pstmt.setFloat(2, LAT);
            pstmt.setFloat(3, LNT);
            pstmt.setFloat(4, LNT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                float distance = rs.getFloat("dis");
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                float X_LAT = rs.getFloat("LAT");
                float X_LNT = rs.getFloat("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");
                wifiDTO dto = new wifiDTO(distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1
                        , X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY
                        , X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3
                        , X_LAT, X_LNT, WORK_DTTM);
                list.add(dto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }

        return list;
    }

    public List<historyDTO> showHistory() {
        List<historyDTO> list = new ArrayList<>();

        try {
            String sql = "select * from locate_history";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String x = rs.getString("x");
                String y = rs.getString("y");
                String date = rs.getString("check_date");
                historyDTO dto = new historyDTO(id, x, y, date);
                list.add(dto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }

        return list;
    }

    public void saveHistory(float LAT, float LNT) {

        int result = 0;

        try {
            String sql = "INSERT INTO locate_history(x,y,check_date) VALUES(?,?,datetime('now'))";
            pstmt = con.prepareStatement(sql);
            pstmt.setFloat(1, LAT);
            pstmt.setFloat(2, LNT);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }
    }

    public int deleteHistory(int id) {

        int result = 0;

        try {
            String sql = "delete from locate_history where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }

        return 0;
    }

    public wifiDTO wifiDetail(String wId) {
        wifiDTO dto = null;
        try {
            String sql = " select * from wifi_Info where X_SWIFI_MGR_NO = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, wId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                float distance = 0;
                String x_swifi_mgr_no = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                float X_LAT = rs.getFloat("LAT");
                float X_LNT = rs.getFloat("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");
                dto = new wifiDTO(distance, x_swifi_mgr_no, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1
                        , X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY
                        , X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3
                        , X_LAT, X_LNT, WORK_DTTM);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(con);
            close(pstmt);

        }


        return dto;
    }


}
