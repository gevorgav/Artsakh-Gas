package Core;

import Core.Models.City;
import Core.Models.Region;
import Models.*;
import dao.*;
import login.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
    public MasterDao masterDao;

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
    public SemiAnnualDao semiAnnualDao;

    @Autowired
    public ViolationCodeDao violationCodeDao;

    @Autowired
    public LocksmithDao locksmithDao;

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
    private List<SemiAnnual> semiAnnuals;
    private Map<Integer, List<ViolationCode>> violationCodesByClientHistory;
    private List<Locksmith> locksmiths;

    public List<VisitType> getVisitTypes() {
        if (this.visitTypes == null){
            this.visitTypes = new ArrayList<>();
            visitTypes.add(new VisitType(0, "Չի այցելվել"));
            visitTypes.add(new VisitType(1, "Այցելել է"));
            visitTypes.add(new VisitType(2, "Պականագործը չի այցելել"));
            visitTypes.add(new VisitType(3, "Փակ դուռ"));
            visitTypes.add(new VisitType(4, "Այցելել է առանց պայմանագրի"));
        }

        return visitTypes;
    }

    public List<SemiAnnual> getSemiAnnuals() {
        if(this.semiAnnuals == null){
            this.semiAnnuals = semiAnnualDao.loadAll();
        }
        return semiAnnuals;
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

    public List<ViolationCode> getViolationCodesByClientHistory(Integer clientHistoryId) {
        if(violationCodesByClientHistory == null) {
            violationCodesByClientHistory = new HashMap<>();
        }
        if(violationCodesByClientHistory.get(clientHistoryId) == null){
            violationCodesByClientHistory.put(clientHistoryId, violationCodeDao.loadByClientId(clientHistoryId));
        }
        return violationCodesByClientHistory.get(clientHistoryId);
    }

    public List<Locksmith> getLocksmiths() {
        if(locksmiths == null){
            locksmiths = locksmithDao.loadAll();
            this.locksmiths.sort(new Comparator<Locksmith>() {
                @Override
                public int compare(Locksmith o1, Locksmith o2) {
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
            });
        }
        return locksmiths;
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

    public void resetMasters(){
        this.masters = null;
    }

    public void resetLocksmiths(){
        this.locksmiths = null;
    }

}
