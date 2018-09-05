package dao.mapper;

import Models.ClientHistory;
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
        clientHistory.setId(rs.getInt("clientsHistory.id"));
        clientHistory.setSemiAnnualId(rs.getObject("semiAnnualId") != null ? rs.getInt("semiAnnualId") : null);
        clientHistory.setClientId(rs.getString("clientId"));
        clientHistory.setViolationActNumber(rs.getString("violationActNumber"));
        clientHistory.setVisitType(rs.getObject("visitType") != null ? rs.getInt("visitType") : null);
        clientHistory.setVisitDescription(rs.getString("visitDescription"));
        clientHistory.setUpdateDate(rs.getDate("updateDate"));
        clientHistory.setPreviousVisitDate(rs.getDate("previousVisitDate"));
        clientHistory.setNextVisitDate(rs.getDate("nextVisitDate"));
        clientHistory.setStampNumbers(rs.getString("stampNumbers"));
        clientHistory.setGo1(rs.getObject("go1") != null ? rs.getInt("go1") : null);
        clientHistory.setGo2(rs.getObject("go2") != null ? rs.getInt("go2") : null );
        clientHistory.setGo3(rs.getObject("go3") != null ? rs.getInt("go3") : null);
        clientHistory.setGo4(rs.getObject("go4") != null ? rs.getInt("go4") : null);
        clientHistory.setGo5(rs.getObject("go5") != null ? rs.getInt("go5") : null);
        clientHistory.setGo6(rs.getObject("go6") != null ? rs.getInt("go6") : null);
        clientHistory.setBacakaGo1(rs.getObject("bacakaGo1") != null ? rs.getInt("bacakaGo1") : null);
        clientHistory.setBacakaGo2(rs.getObject("bacakaGo2") != null ? rs.getInt("bacakaGo2") : null );
        clientHistory.setBacakaGo3(rs.getObject("bacakaGo3") != null ? rs.getInt("bacakaGo3") : null);
        clientHistory.setBacakaGo4(rs.getObject("bacakaGo4") != null ? rs.getInt("bacakaGo4") : null);
        clientHistory.setBacakaGo5(rs.getObject("bacakaGo5") != null ? rs.getInt("bacakaGo5") : null);
        clientHistory.setBacakaGo6(rs.getObject("bacakaGo6") != null ? rs.getInt("bacakaGo6") : null);
        clientHistory.setJth(rs.getObject("jth") != null ? rs.getInt("jth") : null);
        clientHistory.setJtt(rs.getObject("jtt") != null ? rs.getInt("jtt") : null);
        clientHistory.setKet(rs.getObject("ket") != null ? rs.getInt("ket") : null);
        clientHistory.setJah(rs.getObject("jah") != null ? rs.getInt("jah") : null);
        clientHistory.setJk(rs.getObject("jk") != null ? rs.getInt("jk") : null);
        clientHistory.setJv(rs.getObject("jv") != null ? rs.getInt("jv") : null);
        clientHistory.setPakan(rs.getObject("pakan") != null ? rs.getInt("pakan") : null);
        clientHistory.setBacakaJth(rs.getObject("bacakaJth") != null ? rs.getInt("bacakaJth") : null);
        clientHistory.setBacakaJtt(rs.getObject("bacakaJtt") != null ? rs.getInt("bacakaJtt") : null);
        clientHistory.setBacakaKet(rs.getObject("bacakaKet") != null ? rs.getInt("bacakaKet") : null);
        clientHistory.setBacakaJk(rs.getObject("bacakaJk") != null ? rs.getInt("bacakaJk") : null);
        clientHistory.setBacakaJv(rs.getObject("bacakaJv") != null ? rs.getInt("bacakaJv") : null);
        clientHistory.setJTLog(rs.getString("JTLog"));
        clientHistory.setRisk(rs.getString("risk"));
        clientHistory.setMasterId(rs.getObject("masterId") != null ? rs.getInt("masterId") : null);
        clientHistory.setRegionId(rs.getObject("regionId") != null ? rs.getInt("regionId") : null);
        clientHistory.setUserId(rs.getObject("userId") != null ? rs.getInt("userId") : null);
        if(rs.getMetaData().getColumnCount() > 40){ //TODO
            clientHistory.setPaid(rs.getObject("debt") != null ? rs.getDouble("debt") <= 0.0 : false);
        }
        return clientHistory;
    }

}
