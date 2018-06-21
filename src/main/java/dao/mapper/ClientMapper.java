package dao.mapper;

import Models.Client;
import dao.CityDao;
import dao.ClientHistoryDao;
import dao.GRPDao;
import dao.StreetDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class ClientMapper  implements RowMapper<Client> {

    private ClientHistoryDao clientHistoryDao;

    public ClientMapper(ClientHistoryDao clientHistoryDao) {
        this.clientHistoryDao = clientHistoryDao;
    }


    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setFirstName(resultSet.getString("firstName"));
        client.setLastName(resultSet.getString("lastName"));
        client.setMiddleName(resultSet.getString("middleName"));
        client.setPhoneNumber(resultSet.getString("phoneNumber"));
        client.setCounterNumber(resultSet.getString("counterNumber"));
        client.setRegionId(resultSet.getInt("regionId"));
        client.setCityId(resultSet.getInt("cityId"));
        client.setStreetId(resultSet.getInt("streetId"));
        client.setHomeNumber(resultSet.getObject("homeNumber") != null ? resultSet.getInt("homeNumber") : null);
        client.setApartmentNumber(resultSet.getObject("apartmentNumber") != null ? resultSet.getInt("apartmentNumber") : null);
        client.setAshtId(resultSet.getObject("ashtId") != null ? resultSet.getInt("ashtId") : null);
        client.setGrpId(resultSet.getObject("grpId") != null ? resultSet.getInt("grpId") : null);
        client.setClientHistory(new ClientHistoryMapper().mapRow(resultSet, i));
        return client;
    }
}
