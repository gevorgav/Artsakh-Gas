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
                        "FROM clients\n" +
                        "  LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId\n" +
                        "  LEFT JOIN payment\n" +
                        "    ON payment.clientId = clientsHistory.clientId AND payment.semiAnnualId = clientsHistory.semiAnnualId AND\n" +
                        "       clientsHistory.regionId = payment.regionId AND payment.Id in (SELECT payment.id\n" +
                        "                                                                     FROM payment\n" +
                        "                                                                       LEFT  join clients on\n" +
                        "                                                                                            payment.clientId = clients.id\n" +
                        "                                                                     GROUP BY payment.clientId,\n" +
                        "                                                                       clients.regionId, payment.semiAnnualId)\n" +
                        "WHERE  clientsHistory.id IN (SELECT MAX(clientsHistory.id)\n" +
                        "                                                      FROM clientsHistory\n" +
                        "                                                      GROUP BY clientsHistory.id, clientsHistory.clientId,\n" +
                        "                                                        clientsHistory.regionId)\n" +
                        "GROUP BY clients.id, clients.regionId, payment.semiAnnualId";
            }else {
                sql = "SELECT *\n" +
                        "FROM clients\n" +
                        "  LEFT JOIN clientsHistory ON clients.id = clientsHistory.clientId AND clients.regionId = clientsHistory.regionId\n" +
                        "  LEFT JOIN payment\n" +
                        "    ON payment.clientId = clientsHistory.clientId AND payment.semiAnnualId = clientsHistory.semiAnnualId AND\n" +
                        "       clientsHistory.regionId = payment.regionId AND payment.Id in (SELECT payment.id\n" +
                        "                                                                     FROM payment\n" +
                        "                                                                       LEFT  join clients on\n" +
                        "                                                                                            payment.clientId = clients.id\n" +
                        "                                                                     GROUP BY payment.clientId,\n" +
                        "                                                                       clients.regionId, payment.semiAnnualId)\n" +
                        "WHERE  clients.regionId = " + regionId +" AND clientsHistory.id IN (SELECT MAX(clientsHistory.id)\n" +
                        "                                                      FROM clientsHistory\n" +
                        "                                                      GROUP BY clientsHistory.id, clientsHistory.clientId,\n" +
                        "                                                        clientsHistory.regionId)\n" +
                        "GROUP BY clients.id, clients.regionId, payment.semiAnnualId";
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
        String sql = "INSERT INTO clients(id, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId, typeId, typeNumber, sectionId, subSectionId, grsId, isCompany, license)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getCityId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrpId(), client.getTypeId(), client.getTypeNumber(), client.getSectionId(), client.getSubSectionId(), client.getGrsId(), client.isCompany(), client.getLicense());
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
            "typeId = ?, sectionId = ?, subSectionId = ?, typeNumber = ?, grsId = ?, isCompany = ?, license = ? " +
            "WHERE id = ? AND regionId = ?";
        int result = jdbcTemplate.update(sql,client.getFirstName(), client.getLastName(), client.getMiddleName(),
            client.getPhoneNumber(), client.getCounterNumber(), client.getCityId(), client.getCityId(), client.getStreetId(), client.getHomeNumber(),
            client.getApartmentNumber(), client.getAshtId(), client.getGrpId(), client.getTypeId(), client.getSectionId(), client.getSubSectionId(),
            client.getTypeNumber(), client.getGrsId(), client.isCompany(), client.getLicense(), client.getId(), client.getRegionId());
        return result == 1;
    }

    /**
     * Method to delete Client by given id
     * @param clientId Id of Client to be deleted
     * @return true if client is deleted
     */
    public boolean delete(String clientId, Integer regionId){
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(regionId);
        String sql = "DELETE FROM clients WHERE id = ? AND regionId = ?";
        int result = jdbcTemplate.update(sql, clientId, regionId);
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public Integer countOfClientsByRegion(Integer regionId, boolean isCompany) {
        String sql = "SELECT COUNT(id) FROM clients WHERE regionId = ? AND isCompany = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, regionId, isCompany);
    }

    public Integer countOfClientsBySection(Integer sectionId, boolean isCompany) {
        String sql = "SELECT COUNT(id) FROM clients WHERE sectionId = ?  AND isCompany = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sectionId, isCompany);
    }
}
