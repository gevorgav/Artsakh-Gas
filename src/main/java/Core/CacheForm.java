package Core;

import Core.Models.City;
import Core.Models.Region;
import Models.*;
import dao.*;
import login.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
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
    private SectionDao sectionDao;

    @Autowired
    private SubSectionDao subSectionDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

    private List<City> cities;
    private List<Region> regions;
    private List<Street> streets;
    private List<User> users;
    private List<GRP> grps;
    private List<Master> masters;
    private List<ViolationCode> violationCodes;
    private List<Section> sections;
    private List<SubSection> subSections;
    private List<Type> types;

    public List<City> getCities() {
        if(this.cities == null){
            this.cities = cityDao.loadAll();
        }
        return cities;
    }

    public List<Region> getRegions() {
        if(this.regions == null){
            this.regions = regionDao.loadAll();
            this.regions.sort(new Comparator<Region>() {
                @Override
                public int compare(Region o1, Region o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
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


    public List<Section> getSections() {
        if(this.sections == null){
            this.sections = sectionDao.loadAll();
            this.sections.sort(new Comparator<Section>() {
                @Override
                public int compare(Section o1, Section o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.sections;
    }

    public List<SubSection> getSubSections() {
        if(this.subSections == null){
            this.subSections = subSectionDao.loadAll();
            this.subSections.sort(new Comparator<SubSection>() {
                @Override
                public int compare(SubSection o1, SubSection o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.subSections;
    }

    public List<Type> getTypes() {
        if(this.types == null){
            this.types = typeDao.loadAll();
            this.types.sort(new Comparator<Type>() {
                @Override
                public int compare(Type o1, Type o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.types;
    }
}
