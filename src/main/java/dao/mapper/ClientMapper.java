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

    public ClientMapper(ClientHistoryDao clientHistoryDao, CityDao cityDao, StreetDao streetDao, GRPDao grpDao) {
        this.clientHistoryDao = clientHistoryDao;
        this.cityDao = cityDao;
        this.streetDao = streetDao;
        this.grpDao = grpDao;
    }

    private CityDao cityDao;

    private StreetDao streetDao;

    private GRPDao grpDao;

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setFirstName(resultSet.getString("firstName"));
        client.setLastName(resultSet.getString("lastName"));
        client.setMiddleName(resultSet.getString("middleName"));
        client.setPhoneNumber(resultSet.getString("phoneNumber"));
        client.setCounterNumber(resultSet.getString("counterNumber"));
        client.setCity(cityDao != null && resultSet.getObject("cityId") != null ? cityDao.loadById(resultSet.getInt("cityId")) : null);
        client.setStreet(streetDao != null && resultSet.getObject("streetId") != null ? streetDao.loadById(resultSet.getInt("streetId")) : null);
        client.setHomeNumber(resultSet.getObject("homeNumber") != null ? resultSet.getInt("homeNumber") : null);
        client.setApartmentNumber(resultSet.getObject("apartmentNumber") != null ? resultSet.getInt("apartmentNumber") : null);
        client.setAshtId(resultSet.getObject("ashtId") != null ? resultSet.getInt("ashtId") : null);
        client.setGrp(grpDao != null && resultSet.getObject("grpId") != null ? grpDao.loadById(resultSet.getInt("grpId")) : null);
        client.setClientHistory(clientHistoryDao.loadLastClientHistory(client.getId()));
        return client;
    }
}
