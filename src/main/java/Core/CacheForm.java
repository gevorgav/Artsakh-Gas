package Core;

import Core.Models.City;
import Core.Models.Region;
import Models.GRP;
import Models.Master;
import Models.Street;
import Models.ViolationCode;
import dao.*;
import login.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CacheForm {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private StreetDao streetDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GRPDao grpDao;

    @Autowired
    private MasterDao masterDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

    private List<City> cities;
    private List<Region> regions;
    private List<Street> streets;
    private List<User> users;
    private List<GRP> grps;
    private List<Master> masters;
    private List<ViolationCode> violationCodes;

    public List<City> getCities() {
        if(this.cities == null){
            this.cities = cityDao.loadAll();
        }
        return cities;
    }

    public List<Region> getRegions() {
        if(this.regions == null){
            this.regions = regionDao.loadAll();
        }
        return regions;
    }

    public List<Street> getStreets() {
        if(this.streets == null){
            this.streets = streetDao.loadAll();
        }
        return streets;
    }

    public List<User> getUsers() {
        if(this.users == null){
            this.users = userDao.loadAll();
        }
        return users;
    }

    public List<GRP> getGrps() {
        if(this.grps == null){
            this.grps = grpDao.loadAll();
        }
        return grps;
    }

    public List<Master> getMasters() {
        if(this.masters == null){
            this.masters = masterDao.loadAll();
        }
        return masters;
    }

    public List<ViolationCode> getViolationCodes() {
        if(this.violationCodes == null){
            this.violationCodes = violationCodeDao.loadAll();
        }
        return violationCodes;
    }
}
