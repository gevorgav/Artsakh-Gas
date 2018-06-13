package dao.mapper;

import Models.ClientHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class ClientHistoryMapper  implements RowMapper<ClientHistory> {
    @Override
    public ClientHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientHistory clientHistory = new ClientHistory();
        clientHistory.setId(rs.getInt("id"));
        clientHistory.setClientId(rs.getInt("clientId"));
        clientHistory.setViolationActNumber(rs.getInt("violationActNumber"));
        clientHistory.setUpdateDate(rs.getDate("updateDate"));
        clientHistory.setPreviousVisitDate(rs.getDate("previousVisitDate"));
        clientHistory.setNextVisitDate(rs.getDate("nextVisitDate"));
        clientHistory.setCodeViolation(rs.getInt("codeViolation"));
        clientHistory.setStampNumbers(rs.getString("stampNumbers"));
        clientHistory.setGo(rs.getInt("go"));
        clientHistory.setJth(rs.getInt("jth"));
        clientHistory.setJtt(rs.getInt("jtt"));
        clientHistory.setKet(rs.getInt("ket"));
        clientHistory.setJah(rs.getInt("jah"));
        clientHistory.setJk(rs.getInt("jk"));
        clientHistory.setJv(rs.getInt("jv"));
        clientHistory.setGo3(rs.getInt("go3"));
        clientHistory.setGo2(rs.getInt("go2"));
        clientHistory.setRisk(rs.getInt("risk"));
        return clientHistory;
    }
}
