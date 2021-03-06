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
                sql = "SELECT *\n" +
                        "                        FROM clients \n" +
                        "                          LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId \n" +
                        "                          \n" +
                        "                        WHERE  clientsHistory.id IN (SELECT MAX(clientsHistory.id) \n" +
                        "                                                                              FROM clientsHistory \n" +
                        "                                                                              GROUP BY clientsHistory.clientId, \n" +
                        "                                                                                clientsHistory.regionId, clientsHistory.isCompany) AND isDeleted = 0 \n" +
                        "                        GROUP BY clients.id, clients.regionId, clients.isCompany";
            }else {
                sql = "SELECT *\n" +
                        "                        FROM clients \n" +
                        "                          LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId \n" +
                        "                          \n" +
                        "WHERE  clients.regionId = " + regionId +" AND clientsHistory.id IN (SELECT MAX(clientsHistory.id) \n" +
                        "                                                                              FROM clientsHistory \n" +
                        "                                                                              GROUP BY clientsHistory.clientId, \n" +
                        "                                                                                clientsHistory.regionId, clientsHistory.isCompany) AND isDeleted = 0 \n" +
                        "                        GROUP BY clients.id, clients.regionId, clients.isCompany";
            }


            return jdbcTemplate.query(sql, new ClientMapper(clientHistoryDao));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }


    public List<Client> loadAllBySemiAnnual(Integer regionId) {
        try {
            String sql = null;
            if(Objects.isNull(regionId)){
                sql = "SELECT *\n" +
                        "                        FROM clients \n" +
                        "                          LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId \n" +
                        "                          \n" +
                        "                        WHERE  clientsHistory.id IN (SELECT MAX(clientsHistory.id) \n" +
                        "                                                                              FROM clientsHistory \n" +
                        "                                                                              GROUP BY clientsHistory.semiAnnualId, clientsHistory.clientId, \n" +
                        "                                                                                clientsHistory.regionId, clientsHistory.isCompany) AND isDeleted = 0 \n" +
                        "                        GROUP BY clients.id, clients.regionId, clientsHistory.semiAnnualId, clients.isCompany";
            }else {
                sql = "SELECT *\n" +
                        "                        FROM clients \n" +
                        "                          LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId \n" +
                        "                          \n" +
                        "WHERE  clients.regionId = " + regionId +" AND clientsHistory.id IN (SELECT MAX(clientsHistory.id) \n" +
                        "                                                                              FROM clientsHistory \n" +
                        "                                                                              GROUP BY clientsHistory.semiAnnualId,clientsHistory.clientId, \n" +
                        "                                                                                clientsHistory.regionId, clientsHistory.isCompany) AND isDeleted = 0 \n" +
                        "                        GROUP BY clients.id, clients.regionId, clientsHistory.semiAnnualId, clients.isCompany";
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
    public List<Client> loadAll() {
        return Collections.emptyList();
    }

    @Override
    public Client loadById(Integer id) {
        return null;
    }

    /**
     * Method to insert a new Client
     * @param client Client to be inserted
     * @return true if Client is inserted
     */
    public boolean insert(Client client) {
        Objects.requireNonNull(client);
        String sql = "INSERT INTO clients(id, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId, typeId, typeNumber, sectionId, subSectionId, grsId, isCompany, license, isDeleted)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getRegionId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrpId(), client.getTypeId(), client.getTypeNumber(), client.getSectionId(), client.getSubSectionId(), client.getGrsId(), client.isCompany(), client.getLicense(), client.isDeleted());
        return result == 1;
    }

    /**
     * Method to update existing Client
     * @param client Modified Client
     * @return true if Client is updated
     */
    public boolean update(Client client){
        Objects.requireNonNull(client);
        String sql = "UPDATE clients\n" +
            "SET firstName = ?, lastName = ?, middleName = ?, phoneNumber = ?, counterNumber = ?, regionId = ?, cityId = ?, streetId = ?, homeNumber = ?, apartmentNumber = ?, ashtId = ?, grpId = ?,\n" +
            "typeId = ?, sectionId = ?, subSectionId = ?, typeNumber = ?, grsId = ?, isCompany = ?, license = ?, isDeleted = ? " +
            "WHERE id = ? AND regionId = ? AND isCompany = ?";
        int result = jdbcTemplate.update(sql,client.getFirstName(), client.getLastName(), client.getMiddleName(),
            client.getPhoneNumber(), client.getCounterNumber(), client.getRegionId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
            client.getApartmentNumber(), client.getAshtId(), client.getGrpId(), client.getTypeId(), client.getSectionId(), client.getSubSectionId(),
            client.getTypeNumber(), client.getGrsId(), client.isCompany(), client.getLicense(), client.isDeleted(), client.getId(), client.getRegionId(), client.isCompany());
        return result == 1;
    }

    /**
     * Method to delete Client by given id
     * @param clientId Id of Client to be deleted
     * @return true if client is deleted
     */
    public boolean delete(String clientId, Integer regionId, boolean isCompany){
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(regionId);

        Map namedParameters = new HashMap();
        namedParameters.put("isDeleted", true);
        namedParameters.put("deletedOn", new Date());
        namedParameters.put("id", clientId);
        namedParameters.put("regionId", regionId);
        namedParameters.put("isCompany", isCompany ? 1 : 0);
        String sql = "UPDATE clients\n" +
            "SET isDeleted = :isDeleted, deletedOn = :deletedOn " +
            "WHERE id = :id AND regionId = :regionId AND isCompany = :isCompany";
        int result = namedJdbc.update(sql, namedParameters);
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public Integer countOfClientsByRegion(Integer regionId, boolean isCompany) {
        String sql = "SELECT COUNT(id) FROM clients WHERE regionId = ? AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, regionId, isCompany);
    }

    public Integer countOfClientsBySection(Integer sectionId, boolean isCompany) {
        String sql = "SELECT COUNT(id) FROM clients WHERE sectionId = ?  AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, sectionId, isCompany);
    }
}
