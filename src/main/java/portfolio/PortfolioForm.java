package portfolio;

import Core.CacheForm;
import Core.Models.City;
import Models.Client;
import Models.Street;
import dao.ClientDao;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {
    private List<Client> clients;
    private List<Client> filteredClients;

    private CacheForm cache;

    public PortfolioForm() {
    }

    @Autowired
    private ClientDao clientDao;

    public List<Client> getClients() {
        if (clients == null) {
            this.clients = clientDao.loadAll();
        }
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public CacheForm getCache() {
        return cache;
    }

    public void setCache(CacheForm cache) {
        this.cache = cache;
    }

    public List<Client> getFilteredClients() {
        return filteredClients;
    }

    public void setFilteredClients(List<Client> filteredClients) {
        this.filteredClients = filteredClients;
    }

    public void editClient(Client client) {
        this.clientSnapshot = client;
        try {
            this.client = (Client) client.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        showClientEditDialog();
    }

    public void showClientEditDialog(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('clientDialog').show()");
    }

    // Client Form

    private Client clientSnapshot;

    private Client client;

    private List<City> cities;

    private List<Street> streets;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClientSnapshot() {
        return clientSnapshot;
    }

    public void setClientSnapshot(Client clientSnapshot) {
        this.clientSnapshot = clientSnapshot;
    }

    public List<City> citiesByRegionId(Integer regionId) {
        if (cities == null) {
            cities = new ArrayList<>();
            if (regionId != null) {
                for (City city : cache.getCities()) {
                    if (city.getRegionId().equals(regionId)) {
                        cities.add(city);
                    }
                }
            }
        }
        return cities;
    }

    public void resetCity() {
        if (client != null) {
            client.setCityId(null);
            client.setStreetId(null);
            streets = null;
            cities = null;
        }
    }

    public List<Street> streetsByCityId(Integer cityId) {
        if (streets == null) {
            streets = new ArrayList<>();
            if (cityId != null) {
                for (Street street : cache.getStreets()) {
                    if (street.getCityId().equals(cityId)) {
                        streets.add(street);
                    }
                }
            }
        }
        return streets;
    }

    public void resetStreet() {
        if (client != null) {
            client.setStreetId(null);
            streets = null;
        }
    }

    public void saveClient() {
        if (validate(this.client)) {
            if (this.client.isNew()) {
                clientDao.insert(this.client);
            } else {
                clientDao.update(this.client);
                this.clients.remove(this.clientSnapshot);
            }
            this.clients.add(this.client);
            closeClientDialog();
        }
    }

    public void cancelClientDialog() {
        resetClientEditForm();
        closeClientDialog();
    }

    public void closeClientDialog() {
        RequestContext.getCurrentInstance().execute("PF('clientDialog').hide()");
    }

    public void resetClientEditForm() {
        this.client = null;
        this.cities = null;
        this.streets = null;
    }

    public boolean validate(Client client) {
        return true;
    }

    public void addNewClient(){
        this.client = new Client();
        client.setNew(true);
        showClientEditDialog();
    }
}
