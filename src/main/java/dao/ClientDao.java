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

    /**
     * Load ALl Clients
     *
     * @return List of Client
     */
    public List<Client> loadAll(Integer regionId) {
        try {
            String sql = null;
            if(Objects.isNull(regionId)){
                sql = "SELECT *  FROM clients LEFT JOIN clientsHistory On clients.id = clientsHistory.clientId WHERE clientsHistory.id IN (SELECT MAX(clientsHistory.id) FROM clientsHistory GROUP BY clientsHistory.clientId)";
            }else {
                sql = "SELECT *  FROM clients LEFT JOIN clientsHistory On clients.id = clientsHistory.clientId WHERE clients.regionId = "+ regionId +" AND clientsHistory.id IN (SELECT MAX(clientsHistory.id) FROM clientsHistory GROUP BY clientsHistory.clientId)";
            }

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
    public Client loadById(@NotNull String id) {
        Objects.requireNonNull(id);
        String sql = "SELECT *  FROM clients WHERE clients.id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientMapper(clientHistoryDao), id);
    }

    @Override
    List<Client> loadAll() {
        return Collections.emptyList();
    }

    @Override
    Client loadById(Integer id) {
        return null;
    }

    /**
     * Method to insert a new Client
     * @param client Client to be inserted
     * @return true if Client is inserted
     */
    public boolean insert(Client client) {
        Objects.requireNonNull(client);
        String sql = "INSERT INTO clients(id, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId, typeId, typeNumber, sectionId, subSectionId, grsId)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getCityId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrpId(), client.getTypeId(), client.getTypeNumber(), client.getSectionId(), client.getSubSectionId(), client.getGrsId());
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
        namedParameters.put("typeId", client.getTypeId());
        namedParameters.put("sectionId", client.getSectionId());
        namedParameters.put("subSectionId", client.getSubSectionId());
        namedParameters.put("typeNumber", client.getTypeNumber());
        namedParameters.put("grsId", client.getGrsId());
        int sql = namedJdbc.update("UPDATE clients SET firstName = :firstName, lastName = :lastName, middleName = :middleName, phoneNumber = :phoneNumber, counterNumber = :counterNumber, regionId = :regionId, cityId = :cityId, streetId = :streetId, homeNumber = :homeNumber, apartmentNumber = :apartmentNumber, ashtId = :ashtId, grpId = :grpId , typeId = :typeId, typeNumber = :typeNumber, sectionId = :sectionId, subSectionId = :subSectionId, grsId = :grsId  WHERE id = :id", namedParameters);
        return sql == 1;
    }

    /**
     * Method to delete Client by given id
     * @param id Id of Client to be deleted
     * @return true if client is deleted
     */
    public boolean delete(String id){
        Objects.requireNonNull(id);
        String sql = "DELETE FROM clients WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}
