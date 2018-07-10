package Core;

import Core.Models.City;
import Core.Models.Region;
import Models.*;
import dao.*;
import login.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CacheForm {
    @Autowired
    public CityDao cityDao;

    @Autowired
    public RegionDao regionDao;

    @Autowired
    public StreetDao streetDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public GRPDao grpDao;

    @Autowired
    private MasterDao masterDao;

    @Autowired
    public SectionDao sectionDao;

    @Autowired
    public SubSectionDao subSectionDao;

    @Autowired
    public TypeDao typeDao;

    @Autowired
    public AshtDao ashtDao;

    @Autowired
    public GRSDao grsDao;

    @Autowired
    public ViolationCodeDao violationCodeDao;

    @Autowired
    public PriceListDao priceListDao;

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
    private List<Asht> ashts;
    private List<GRS> grss;
    private List<VisitType> visitTypes;
    private List<PriceList> priceLists;

    public List<VisitType> getVisitTypes() {
        if (this.visitTypes == null){
            this.visitTypes = new ArrayList<>();
            visitTypes.add(new VisitType(1, "Այցելել է"));
            visitTypes.add(new VisitType(2, "Չի այցելել"));
            visitTypes.add(new VisitType(3, "Փակ դուռ"));
        }

        return visitTypes;
    }

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

    public List<Asht> getAshts() {
        if(this.ashts == null){
            this.ashts = ashtDao.loadAll();
            this.ashts.sort(new Comparator<Asht>() {
                @Override
                public int compare(Asht o1, Asht o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.ashts;
    }

    public List<GRS> getGrss() {
        if(this.grss == null){
            this.grss = grsDao.loadAll();
            this.grss.sort(new Comparator<GRS>() {
                @Override
                public int compare(GRS o1, GRS o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.grss;
    }

    public List<PriceList> getPriceLists() {
        if(this.priceLists == null){
            this.priceLists = priceListDao.loadAll();
            this.priceLists.sort(new Comparator<PriceList>() {
                @Override
                public int compare(PriceList o1, PriceList o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return this.priceLists;
    }

    public void resetCities(){
        this.cities = null;
    }

    public void resetAshts(){
        this.ashts = null;
    }

    public void resetGrps(){
        this.grps = null;
    }

    public void resetGrss(){
        this.grss = null;
    }

    public void resetRegions(){
        this.regions = null;
    }

    public void resetSections(){
        this.sections = null;
    }

    public void resetStreets(){
        this.streets = null;
    }

    public void resetSubSections(){
        this.subSections = null;
    }

    public void resetTypes(){
        this.types = null;
    }
    public void resetViolationCodes(){
        this.violationCodes = null;
    }

    public void resetPriceList(){
        this.priceLists = null;
    }

}
