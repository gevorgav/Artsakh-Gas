package Beans;

import Core.Models.Region;
import Models.Client;
import dao.ClientDao;
import dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestForm {
    @Autowired
    private RegionDao regionDao;

    @Autowired
    private ClientDao clientDao;

    private List<Region> regionList;

    private List<Client> clients;

    public List<Region> getRegionList() {
        if(regionList == null){
            regionList = this.regionDao.loadAll();
        }
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public List<Client> getClients() {
        if(clients == null){
            clients = this.clientDao.loadAll();
        }
        return clients;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void create() {
    }

    public void update() {
        clients = null;
    }

    public void delete() {
        clientDao.delete(4);
        clients = null;
    }
}
