package dao;

import Models.Client;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
            String sql = "SELECT * FROM clients;";
            return jdbcTemplate.query(sql, new ClientMapper(clientHistoryDao, cityDao, streetDao, grpDao));
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
        String sql = "SELECT * FROM clients WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientMapper(clientHistoryDao, cityDao, streetDao, grpDao), id);
    }

    /**
     * Method to insert a new Client
     * @param client Client to be inserted
     * @return true if Client is inserted
     */
    public boolean insert(Client client) {
        Objects.requireNonNull(client);
        String sql = "INSERT INTO clients(id, currentVersionId, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getCity().getRegion().getId(), client.getCity().getId(), client.getStreet().getId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrp().getId());
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
                "SET id = ?, currentVersionId = ?, firstName = ?, lastName = ?, middleName = ?, phoneNumber = ?, counterNumber = ?, regionId = ?, cityId = ?, streetId = ?, homeNumber = ?, apartmentNumber = ?, ashtId = ?, grpId = ?\n" +
                "WHERE id = ?";
        int result = jdbcTemplate.update(sql, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(),
                client.getPhoneNumber(), client.getCounterNumber(), client.getCity().getRegion().getId(), client.getCity().getId(), client.getStreet().getId(), client.getHomeNumber(),
                client.getApartmentNumber(), client.getAshtId(), client.getGrp().getId(), client.getId());
        return result == 1;
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
