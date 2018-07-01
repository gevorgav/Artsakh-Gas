package dao;

import Models.Client;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class ClientDao extends Dao<Client>{

    @Autowired
    private ClientHistoryDao clientHistoryDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StreetDao streetDao;

    @Autowired
    private GRPDao grpDao;

    /**
     * Load ALl Clients
     *
     * @return List of Client
     */
    public List<Client> loadAll() {
        try {
            String sql = "SELECT *  FROM clients LEFT JOIN clientsHistory On clients.id = clientsHistory.clientId ORDER BY clientsHistory.id DESC";
            return jdbcTemplate.query(sql, new ClientMapper(clientHistoryDao));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Method to load Client By Id
     *
     * @param id Id of Client
     * @return Client by given id
     */
    public Client loadById(@NotNull Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT *  FROM clients WHERE clients.id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientMapper(clientHistoryDao), id);
    }

    /**
     * Method to insert a new Client
     * @param client Client to be inserted
     * @return true if Client is inserted
     */
    public boolean insert(Client client) {
        Objects.requireNonNull(client);
        String sql = "INSERT INTO clients(id, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getCityId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrpId());
        return result == 1;
    }

    /**
     * Method to update existing Client
     * @param client Modified Client
     * @return true if Client is updated
     */
    public boolean update(Client client){
        Objects.requireNonNull(client);
        Map namedParameters = new HashMap();
        namedParameters.put("id", client.getId());
        namedParameters.put("firstName", client.getFirstName());
        namedParameters.put("lastName", client.getLastName());
        namedParameters.put("middleName", client.getMiddleName());
        namedParameters.put("phoneNumber", client.getPhoneNumber());
        namedParameters.put("counterNumber", client.getCounterNumber());
        namedParameters.put("regionId", client.getRegionId());
        namedParameters.put("cityId", client.getCityId());
        namedParameters.put("streetId", client.getStreetId());
        namedParameters.put("homeNumber", client.getHomeNumber());
        namedParameters.put("apartmentNumber",client.getApartmentNumber());
        namedParameters.put("ashtId", client.getAshtId());
        namedParameters.put("grpId", client.getGrpId());
        int sql = namedJdbc.update("UPDATE clients SET firstName = :firstName, lastName = :lastName, middleName = :middleName, phoneNumber = :phoneNumber, counterNumber = :counterNumber, regionId = :regionId, cityId = :cityId, streetId = :streetId, homeNumber = :homeNumber, apartmentNumber = :apartmentNumber, ashtId = :ashtId, grpId = :grpId WHERE id = :id", namedParameters);
        return sql == 1;
    }

    /**
     * Method to delete Client by given id
     * @param id Id of Client to be deleted
     * @return true if client is deleted
     */
    public boolean delete(Integer id){
        Objects.requireNonNull(id);
        String sql = "DELETE FROM clients WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}
