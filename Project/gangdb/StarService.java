package db;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.StarDto;
public class StarService {

    public StarService() {
    }

    private static StarService empService = null;

    public static StarService getInstance() {
        if (empService == null) {
            empService = new StarService();
        }
        return empService;
    }

    public List<StarDto> getStarDtoList() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("       PRO_IDX,");  
        sb.append("       MEM_IDX,");
       sb.append("       COM_DATE,");
       sb.append("       COMMENTS,");
       sb.append("       STAR_RATING ");
        sb.append("FROM shop.REVIEW");


        Connection connect = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StarDto> StarDtoList = new ArrayList<StarDto>();

        try {
            connect = DB.open();
            pstmt = connect.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StarDto tempEmpDto = new StarDto(
                        rs.getInt("PRO_IDX"),
                        rs.getInt("MEM_IDX"),
                        rs.getTimestamp("COM_DATE"),
                        rs.getString("COMMENTS"),
                        rs.getInt("STAR_RATING"));

                StarDtoList.add(tempEmpDto);
            }

        } catch (SQLException e) {
            System.out.println("쿼리 중 에러가 발생하였습니다.");
            throw e; // 예외를 호출한 메소드에 던지도록 처리합니다.
        } finally {
            DB.close(connect, pstmt, rs);
        }

        return StarDtoList;
    }
    






}
