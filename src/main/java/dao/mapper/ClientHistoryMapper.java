package dao.mapper;

import Models.ClientHistory;
import dao.ClientDao;
import dao.ViolationCodeDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class ClientHistoryMapper  implements RowMapper<ClientHistory> {

    public ClientHistoryMapper() {
    }

    @Override
    public ClientHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientHistory clientHistory = new ClientHistory();
        clientHistory.setId(rs.getString("id"));
        clientHistory.setClientId(rs.getInt("clientId"));
        clientHistory.setViolationActNumber(rs.getObject("violationActNumber") != null ? rs.getInt("violationActNumber") : null);
        clientHistory.setUpdateDate(rs.getDate("updateDate"));
        clientHistory.setPreviousVisitDate(rs.getDate("previousVisitDate"));
        clientHistory.setNextVisitDate(rs.getDate("nextVisitDate"));
        clientHistory.setStampNumbers(rs.getString("stampNumbers"));
        clientHistory.setGo(rs.getObject("go") != null ? rs.getInt("go") : null);
        clientHistory.setJth(rs.getObject("jth") != null ? rs.getInt("jth") : null);
        clientHistory.setJtt(rs.getObject("jtt") != null ? rs.getInt("jtt") : null);
        clientHistory.setKet(rs.getObject("ket") != null ? rs.getInt("ket") : null);
        clientHistory.setJah(rs.getObject("jah") != null ? rs.getInt("jah") : null);
        clientHistory.setJk(rs.getObject("jk") != null ? rs.getInt("jk") : null);
        clientHistory.setJv(rs.getObject("jv") != null ? rs.getInt("jv") : null);
        clientHistory.setGo3(rs.getObject("go3") != null ? rs.getInt("go3") : null);
        clientHistory.setGo2(rs.getObject("go2") != null ? rs.getInt("go2") : null );
        clientHistory.setRisk(rs.getObject("risk") != null ? rs.getInt("risk") : null);
        return clientHistory;
    }

}
