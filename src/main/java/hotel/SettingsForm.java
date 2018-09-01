package hotel;

import Core.CacheForm;
import Core.Models.City;
import Core.Models.Region;
import Models.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class SettingsForm implements Serializable {

    private static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category(1, "ԱՇՏ"));
        categories.add(new Category(2, "Քաղաք"));
        categories.add(new Category(3, "ԳԿԿ"));
        categories.add(new Category(4, "ԳՐՍ"));//
        categories.add(new Category(5, "Սարքերի գներ"));
        categories.add(new Category(6, "Շրջան"));
        categories.add(new Category(7, "Մաս"));
        categories.add(new Category(8, "Ենթատեղամաս"));
        categories.add(new Category(9, "Գյուղ/Փողոց"));
        categories.add(new Category(10, "Գ/Հ տիպ"));
        categories.add(new Category(11, "Խախտման կոդեր"));
        categories.add(new Category(12, "Վարպետ"));
        categories.add(new Category(13, "Փականագործ"));
        categories.add(new Category(14, "Կիսամյակի կարգավորում"));
    }

    private Integer categoryTypeId;

    private CacheForm cache;

    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        SettingsForm.categories = categories;
    }

    public CacheForm getCache() {
        return cache;
    }

    public void setCache(CacheForm cache) {
        this.cache = cache;
    }

    private void reloadPage(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeCategoryType() {
        if(this.categoryTypeId != null){
            switch (categoryTypeId){
                case 1:
                    this.editedAsht = cache.getAshts().isEmpty() ? new Asht() : cache.getAshts().get(0);
                    break;
                case 2:
                    this.editedCity = cache.getCities().isEmpty() ? new City() : cache.getCities().get(0);
                    break;
                case 3:
                    this.editedGrp = cache.getGrps().isEmpty() ? new GRP() : cache.getGrps().get(0);
                    break;
                case 4:
                    this.editedGrs = cache.getGrss().isEmpty() ? new GRS() : cache.getGrss().get(0);
                    break;
                case 6:
                    this.editedRegion = cache.getRegions().isEmpty() ? new Region() : cache.getRegions().get(0);
                    break;
                case 7:
                    this.editedSection = cache.getSections().isEmpty() ? new Section() : cache.getSections().get(0);
                    break;
                case 8:
                    this.editedSubSection = cache.getSubSections().isEmpty() ? new SubSection() : cache.getSubSections().get(0);
                    break;
                case 9:
                    this.editedStreet = cache.getStreets().isEmpty() ? new Street() : cache.getStreets().get(0);
                    break;
                case 10:
                    this.editedType = cache.getTypes().isEmpty() ? new Type() : cache.getTypes().get(0);
                    break;
                case 11:
                    this.editedViolationCode = cache.getViolationCodes().isEmpty() ? new ViolationCode() : cache.getViolationCodes().get(0);
                    break;
                case 12:
                    this.editedMaster = cache.getMasters().isEmpty() ? new Master() : cache.getMasters().get(0);
                    break;
                case 13:
                    this.editedLocksmith = cache.getLocksmiths().isEmpty() ? new Locksmith() : cache.getLocksmiths().get(0);
                    break;
                case 14:
                    this.editedSemiAnnualConfig = cache.getSemiAnnualConfig();
                    break;
            }
        }
    }

    // ---------- SemiAnnual Region Form

    private SemiAnnualConfig editedSemiAnnualConfig;

    public SemiAnnualConfig getEditedSemiAnnualConfig() {
        return editedSemiAnnualConfig;
    }

    public void setEditedSemiAnnualConfig(SemiAnnualConfig editedSemiAnnualConfig) {
        this.editedSemiAnnualConfig = editedSemiAnnualConfig;
    }

    public void editSemiAnnualRegion(SemiAnnualConfig semiAnnualConfig){
        try {
            this.editedSemiAnnualConfig = (SemiAnnualConfig) semiAnnualConfig.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.editedSemiAnnualConfig = semiAnnualConfig;
    }

    public void saveSemiAnnualRegion(){
        if (validateSemiAnnualRegion(this.editedSemiAnnualConfig)){
            if (this.cache.clientHistoryDao.moveHistoryToSemiAnnualForAllRegions(this.editedSemiAnnualConfig.getSemiAnnualId())){
                this.cache.setSemiAnnualConfig(null);
                saveCurrentSemiAnnual(this.editedSemiAnnualConfig.getSemiAnnualId());
            }

        }

    }

    private void saveCurrentSemiAnnual(Integer semiAnnualId) {
        this.cache.semiAnnualConfigDao.update(semiAnnualId);
    }

    private boolean validateSemiAnnualRegion(SemiAnnualConfig semiAnnualConfig) {
        boolean isValid = true;
        if(semiAnnualConfig.getSemiAnnualId() == null ){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("semiAnnualRegionIdForm:sregionId", facesMessage);
            isValid = false;
        }
        if(semiAnnualConfig.getSemiAnnualId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Կիսամյակը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("semiAnnualRegionIdForm:semiAnnualId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- City Form

    private City editedCity;

    private City citySnapshot;

    public City getEditedCity() {
        if(editedCity == null){
            this.editedCity = new City();
        }
        return editedCity;
    }

    public void setEditedCity(City editedCity) {
        this.editedCity = editedCity;
    }

    public void editCity(City city) {
        try {
            this.editedCity = (City) city.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.citySnapshot = city;
    }

    public void addNewCity() {
        this.editedCity = new City();
    }

    public void deleteCity(City city) {
        if(Objects.equals(this.editedCity.getId(), city.getId())){
            this.editedCity = new City();
        }
        if (cache.cityDao.delete(city.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getCities().remove(city);
        }
        this.reloadPage();
    }

    public void saveCity(){
        if(validateCity(this.editedCity)) {
            if (this.editedCity.getId() != null) {
                if (cache.cityDao.update(this.editedCity)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.cityDao.insert(this.editedCity)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetCities();  // TODO load anel kam jnjel u avelacnel mej@
            this.editedCity = new City();
            this.reloadPage();
        }
    }

    public boolean validateCity(City city) {
        boolean isValid = true;
        if(city.getName() == null || city.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Քաղաքը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("cityFormId:nameId", facesMessage);
            isValid = false;
        }
        if(city.getRegionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("cityFormId:regionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }


    // --------- Asht Form

    private Asht editedAsht;

    private Asht ashtSnapshot;

    public Asht getEditedAsht() {
        if(this.editedAsht == null){
            this.editedAsht = new Asht();
        }
        return this.editedAsht;
    }

    public void setEditedAsht(Asht editedAsht) {
        this.editedAsht = editedAsht;
    }

    public void editAsht(Asht asht) {
        try {
            this.editedAsht = (Asht) asht.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.ashtSnapshot = asht;
    }

    public void addNewAsht() {
        this.editedAsht = new Asht();
    }

    public void deleteAsht(Asht asht) {
        if(Objects.equals(this.editedAsht.getId(), asht.getId())){
            this.editedAsht = new Asht();
        }
        if (cache.ashtDao.delete(asht.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getAshts().remove(asht);
        }
        this.reloadPage();
    }

    public void saveAsht(){
        if(validateAsht(this.editedAsht)) {
            if (this.editedAsht.getId() != null) {
                if (cache.ashtDao.update(this.editedAsht)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.ashtDao.insert(this.editedAsht)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetAshts();  // TODO load anel kam jnjel u avelacnel mej@
            this.editedAsht = new Asht();
            this.reloadPage();
        }
    }

    public boolean validateAsht(Asht asht) {
        boolean isValid = true;
        if(asht.getName() == null || asht.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ԱՇՏը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("ashtFormId:ashtNameId", facesMessage);
            isValid = false;
        }
        if(asht.getRegionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("ashtFormId:ashtRegionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }
    // --------- GRP Form

    private GRP editedGrp;

    private GRP grpSnapshot;

    public GRP getEditedGrp() {
        if(this.editedGrp == null){
            this.editedGrp = new GRP();
        }
        return this.editedGrp;
    }

    public void setEditedGrp(GRP editedGrp) {
        this.editedGrp = editedGrp;
    }

    public void editGrp(GRP grp) {
        try {
            this.editedGrp = (GRP) grp.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.grpSnapshot = grp;
    }

    public void addNewGrp() {
        this.editedGrp = new GRP();
    }

    public void deleteGrp(GRP grp) {
        if(Objects.equals(this.editedGrp.getId(), grp.getId())){
            this.editedGrp = new GRP();
        }
        if (cache.grpDao.delete(grp.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getGrps().remove(grp);
        }
        this.reloadPage();
    }

    public void saveGrp(){
        if(validateGrp(this.editedGrp)) {
            if (this.editedGrp.getId() != null) {
                if (cache.grpDao.update(this.editedGrp)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.grpDao.insert(this.editedGrp)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetGrps(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedGrp = new GRP();
            this.reloadPage();
        }
    }

    public boolean validateGrp(GRP grp) {
        boolean isValid = true;
        if(grp.getName() == null || grp.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ԳԿԿն պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("grpFormId:grpNameId", facesMessage);
            isValid = false;
        }
        if(grp.getCityId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Քաղաքը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("grpFormId:cityId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- GRS Form

    private GRS editedGrs;

    private GRS grsSnapshot;

    public GRS getEditedGrs() {
        if(this.editedGrs == null){
            this.editedGrs = new GRS();
        }
        return this.editedGrs;
    }

    public void setEditedGrs(GRS editedGrs) {
        this.editedGrs = editedGrs;
    }

    public void editGrs(GRS grs) {
        try {
            this.editedGrs = (GRS) grs.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.grsSnapshot = grs;
    }

    public void addNewGrs() {
        this.editedGrs = new GRS();
    }

    public void deleteGrs(GRS grs) {
        if(Objects.equals(this.editedGrs.getId(), grs.getId())){
            this.editedGrs = new GRS();
        }
        if (cache.grsDao.delete(grs.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getGrss().remove(grs);
        }
        this.reloadPage();
    }

    public void saveGrs(){
        if(validateGrs(this.editedGrs)) {
            if (this.editedGrs.getId() != null) {
                if (cache.grsDao.update(this.editedGrs)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.grsDao.insert(this.editedGrs)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetGrss(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedGrs = new GRS();
            this.reloadPage();
        }
    }

    public boolean validateGrs(GRS grs) {
        boolean isValid = true;
        if(grs.getName() == null || grs.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ԳՐՍն պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("grsFormId:grsNameId", facesMessage);
            isValid = false;
        }
        return isValid;
    }

    // --------- Price List Form

    private PriceList editedPrice;

    private PriceList priceSnapshot;

    public PriceList getEditedPrice() {
        if(this.editedPrice == null){
            this.editedPrice = new PriceList();
        }
        return this.editedPrice;
    }

    public void setEditedPrice(PriceList editedGrs) {
        this.editedPrice = editedPrice;
    }

    public void editPrice(PriceList editedPrice) {
        try {
            this.editedPrice = (PriceList) editedPrice.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.priceSnapshot = editedPrice;
    }



    public void savePrice(){
        if(validatePriceList(this.editedPrice)) {
            if (this.editedPrice.getId() != null) {
                if (cache.priceListDao.update(this.editedPrice)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.priceListDao.insert(this.editedPrice)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetPriceList(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedPrice = new PriceList();
            this.reloadPage();
        }
    }

    public boolean validatePriceList(PriceList priceList) {
        boolean isValid = true;
        if(priceList.getName() == null || priceList.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Գինը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("priceFormId:editedPriceId", facesMessage);
            isValid = false;
        }
        return isValid;
    }

    // --------- Region Form

    private Region editedRegion;

    private Region regionSnapshot;

    public Region getEditedRegion() {
        if(this.editedRegion == null){
            this.editedRegion = new Region();
        }
        return this.editedRegion;
    }

    public void setEditedRegion(Region editedRegion) {
        this.editedRegion = editedRegion;
    }

    public void editRegion(Region region) {
        try {
            this.editedRegion = (Region) region.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.regionSnapshot = region;
    }

    public void addNewRegion() {
        this.editedRegion = new Region();
    }

    public void deleteRegion(Region region) {
        if(Objects.equals(this.editedRegion.getId(), region.getId())){
            this.editedRegion = new Region();
        }
        if (cache.regionDao.delete(region.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getRegions().remove(region);
        }
        this.reloadPage();
    }

    public void saveRegion(){
        if(validateRegion(this.editedRegion)) {
            if (this.editedRegion.getId() != null) {
                if (cache.regionDao.update(this.editedRegion)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.regionDao.insert(this.editedRegion)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetRegions(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedRegion = new Region();
            this.reloadPage();
        }
    }

    public boolean validateRegion(Region region) {
        boolean isValid = true;
        if(region.getName() == null || region.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("regionFormId:regionNameId", facesMessage);
            isValid = false;
        }
        return isValid;
    }

    // --------- Section Form

    private Section editedSection;

    private Section sectionSnapshot;

    public Section getEditedSection() {
        if(this.editedSection == null){
            this.editedSection = new Section();
        }
        return this.editedSection;
    }

    public void setEditedSection(Section editedSection) {
        this.editedSection = editedSection;
    }

    public void editSection(Section section) {
        try {
            this.editedSection = (Section) section.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.sectionSnapshot = section;
    }

    public void addNewSection() {
        this.editedSection = new Section();
    }

    public void deleteSection(Section section) {
        if(Objects.equals(this.editedSection.getId(), section.getId())){
            this.editedSection = new Section();
        }
        if (cache.sectionDao.delete(section.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getSections().remove(section);
        }
        this.reloadPage();
    }

    public void saveSection(){
        if(validateSection(this.editedSection)) {
            if (this.editedSection.getId() != null) {
                if (cache.sectionDao.update(this.editedSection)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.sectionDao.insert(this.editedSection)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetSections(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedSection = new Section();
            this.reloadPage();
        }
    }

    public boolean validateSection(Section section) {
        boolean isValid = true;
        if(section.getName() == null || section.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Մասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("sectionFormId:sectionNameId", facesMessage);
            isValid = false;
        }
        if(section.getRegionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("sectionFormId:sectionRegionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- SubSection Form

    private SubSection editedSubSection;

    private SubSection subSectionSnapshot;

    public SubSection getEditedSubSection() {
        if(this.editedSubSection == null){
            this.editedSubSection = new SubSection();
        }
        return this.editedSubSection;
    }

    public void setEditedSubSection(SubSection editedSubSection) {
        this.editedSubSection = editedSubSection;
    }

    public void editSubSection(SubSection subSection) {
        try {
            this.editedSubSection = (SubSection) subSection.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.subSectionSnapshot = subSection;
    }

    public void addNewSubSection() {
        this.editedSubSection = new SubSection();
    }

    public void deleteSubSection(SubSection subSection) {
        if(Objects.equals(this.editedSubSection.getId(), subSection.getId())){
            this.editedSubSection = new SubSection();
        }
        if (cache.subSectionDao.delete(subSection.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getSubSections().remove(subSection);
        }
        this.reloadPage();
    }

    public void saveSubSection(){
        if(validateSubSection(this.editedSubSection)) {
            if (this.editedSubSection.getId() != null) {
                if (cache.subSectionDao.update(this.editedSubSection)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.subSectionDao.insert(this.editedSubSection)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetSubSections(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedSubSection = new SubSection();
            this.reloadPage();
        }
    }

    public boolean validateSubSection(SubSection subSection) {
        boolean isValid = true;
        if(subSection.getName() == null || subSection.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ենթատեղամասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("subSectionFormId:subSectionNameId", facesMessage);
            isValid = false;
        }
        if(subSection.getSectionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Մասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("subSectionFormId:subSectionSectionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- Street Form

    private Street editedStreet;

    private Street streetSnapshot;

    public Street getEditedStreet() {
        if(this.editedStreet == null){
            this.editedStreet = new Street();
        }
        return this.editedStreet;
    }

    public void setEditedStreet(Street editedStreet) {
        this.editedStreet = editedStreet;
    }

    public void editStreet(Street street) {
        try {
            this.editedStreet = (Street) street.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.streetSnapshot = street;
    }

    public void addNewStreet() {
        this.editedStreet = new Street();
    }

    public void deleteStreet(Street street) {
        if(Objects.equals(this.editedStreet.getId(), street.getId())){
            this.editedStreet = new Street();
        }
        if (cache.streetDao.delete(street.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getStreets().remove(street);
        }
        this.reloadPage();
    }

    public void saveStreet(){
        if(validateStreet(this.editedStreet)) {
            if (this.editedStreet.getId() != null) {
                if (cache.streetDao.update(this.editedStreet)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.streetDao.insert(this.editedStreet)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetStreets(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedStreet = new Street();
            this.reloadPage();
        }
    }

    public boolean validateStreet(Street street) {
        boolean isValid = true;
        if(street.getName() == null || street.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Գյուղ/Փողոցը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("streetFormId:streetNameId", facesMessage);
            isValid = false;
        }
        if(street.getCityId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Քաղաքը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("streetFormId:streetCityId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- Type Form

    private Type editedType;

    private Type typeSnapshot;

    public Type getEditedType() {
        if(this.editedType == null){
            this.editedType = new Type();
        }
        return this.editedType;
    }

    public void setEditedType(Type editedType) {
        this.editedType = editedType;
    }

    public void editType(Type type) {
        try {
            this.editedType = (Type) type.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.typeSnapshot = type;
    }

    public void addNewType() {
        this.editedType = new Type();
    }

    public void deleteType(Type type) {
        if(Objects.equals(this.editedType.getId(), type.getId())){
            this.editedType = new Type();
        }
        if (cache.typeDao.delete(type.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getTypes().remove(type);
        }
        this.reloadPage();
    }

    public void saveType(){
        if(validateType(this.editedType)) {
            if (this.editedType.getId() != null) {
                if (cache.typeDao.update(this.editedType)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.typeDao.insert(this.editedType)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetTypes(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedType = new Type();
            this.reloadPage();
        }
    }

    public boolean validateType(Type type) {
        boolean isValid = true;
        if(type.getName() == null || type.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Գ/Հ տիպը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("typeFormId:typeNameId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- ViolationCode Form

    private ViolationCode editedViolationCode;

    private ViolationCode violationCodeSnapshot;

    public ViolationCode getEditedViolationCode() {
        if(this.editedViolationCode == null){
            this.editedViolationCode = new ViolationCode();
        }
        return this.editedViolationCode;
    }

    public void setEditedViolationCode(ViolationCode editedViolationCode) {
        this.editedViolationCode = editedViolationCode;
    }

    public void editViolationCode(ViolationCode violationCode) {
        try {
            this.editedViolationCode = (ViolationCode) violationCode.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.violationCodeSnapshot = violationCode;
    }

    public void addNewViolationCode() {
        this.editedViolationCode = new ViolationCode();
    }

    public void deleteViolationCode(ViolationCode violationCode) {
        if(Objects.equals(this.editedViolationCode.getId(), violationCode.getId())){
            this.editedViolationCode = new ViolationCode();
        }
        if (cache.violationCodeDao.delete(violationCode.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getViolationCodes().remove(violationCode);
        }
        this.reloadPage();
    }

    public void saveViolationCode(){
        if(validateViolationCodeNumber(this.editedViolationCode)) {
            if (this.editedViolationCode.getId() != null) {
                if (cache.violationCodeDao.update(this.editedViolationCode)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.violationCodeDao.insert(this.editedViolationCode)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetViolationCodes(); // TODO load anel kam jnjel u avelacnel mej@
            this.editedViolationCode = new ViolationCode();
            this.reloadPage();
        }
    }

    public boolean validateViolationCodeNumber(ViolationCode violationCode) {
        boolean isValid = true;
        if(violationCode.getName() == null || violationCode.getName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Խախտման կոդը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("violationCodeFormId:violationCodeNameId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- Master Form

    private Master editedMaster;

    private Master masterSnapshot;

    public Master getEditedMaster() {
        if(this.editedMaster == null){
            this.editedMaster = new Master();
        }
        return editedMaster;
    }

    public void setEditedMaster(Master editedMaster) {
        this.editedMaster = editedMaster;
    }

    public void editMaster(Master master) {
        try {
            this.editedMaster = (Master) master.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.masterSnapshot = master;
    }

    public void addNewMaster() {
        this.editedMaster = new Master();
    }

    public void deleteMaster(Master master) {
        if(Objects.equals(this.editedMaster.getId(), master.getId())){
            this.editedMaster = new Master();
        }
        if (cache.masterDao.delete(master.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getMasters().remove(master);
        }
        this.reloadPage();
    }

    public void saveMaster(){
        if(validateMaster(this.editedMaster)) {
            if (this.editedMaster.getId() != null) {
                if (cache.masterDao.update(this.editedMaster)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.masterDao.insert(this.editedMaster)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetMasters();  // TODO load anel kam jnjel u avelacnel mej@
            this.editedMaster = new Master();
            this.reloadPage();
        }
    }

    public boolean validateMaster(Master master) {
        boolean isValid = true;
        if(master.getFirstName() == null || master.getFirstName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Անունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterFirstNameId", facesMessage);
            isValid = false;
        }
        if(master.getLastName() == null || master.getLastName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ազգանունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterLastNameId", facesMessage);
            isValid = false;
        }
        if(master.getRegionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterRegionId", facesMessage);
            isValid = false;
        }
        if(master.getAshtId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ԱՇՏը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterAshtId", facesMessage);
            isValid = false;
        }
        if(master.getSectionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Մասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterSectionId", facesMessage);
            isValid = false;
        }
        if(master.getSubSectionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ենթատեղամասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("masterFormId:masterSubSectionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    // --------- Locksmith Form

    private List<Asht> ashts;

    private Locksmith editedLocksmith;

    private Locksmith locksmithSnapshot;

    private List<Section> sections;

    private List<SubSection> subSections;

    public Locksmith getEditedLocksmith() {
        if(this.editedLocksmith == null){
            this.editedLocksmith = new Locksmith();
        }
        return editedLocksmith;
    }

    public void setEditedLocksmith(Locksmith editedLocksmith) {
        this.editedLocksmith = editedLocksmith;
    }

    public void editLocksmith(Locksmith locksmith) {
        try {
            this.editedLocksmith = (Locksmith) locksmith.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.locksmithSnapshot = locksmith;
    }

    public void addNewLocksmith() {
        this.editedLocksmith = new Locksmith();
    }

    public void deleteLocksmith(Locksmith locksmith) {
        if(Objects.equals(this.editedLocksmith.getId(), locksmith.getId())){
            this.editedLocksmith = new Locksmith();
        }
        if (cache.locksmithDao.delete(locksmith.getId())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
            facesContext.addMessage(null, facesMessage);
            cache.getLocksmiths().remove(locksmith);
        }
        this.reloadPage();
    }

    public void saveLocksmith(){
        if(validateLocksmith(this.editedLocksmith)) {
            if (this.editedLocksmith.getId() != null) {
                if (cache.locksmithDao.update(this.editedLocksmith)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            } else {
                if (cache.locksmithDao.insert(this.editedLocksmith)) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                    facesContext.addMessage(null, facesMessage);
                }
            }
            cache.resetLocksmiths();  // TODO load anel kam jnjel u avelacnel mej@
            this.editedLocksmith = new Locksmith();
            this.reloadPage();
        }
    }

    public boolean validateLocksmith(Locksmith locksmith) {
        boolean isValid = true;
        if(locksmith.getFirstName() == null || locksmith.getFirstName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Անունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithFirstNameId", facesMessage);
            isValid = false;
        }
        if(locksmith.getLastName() == null || locksmith.getLastName().trim().isEmpty()){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ազգանունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithLastNameId", facesMessage);
            isValid = false;
        }
        if(locksmith.getRegionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithRegionId", facesMessage);
            isValid = false;
        }
        if(locksmith.getAshtId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ԱՇՏը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithAshtId", facesMessage);
            isValid = false;
        }
        if(locksmith.getSectionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Մասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithSectionId", facesMessage);
            isValid = false;
        }
        if(locksmith.getSubSectionId() == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ենթատեղամասը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("locksmithFormId:locksmithSubSectionId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    public List<Asht> ashtsByRegionId(Integer regionId) {
        if (ashts == null) {
            ashts = new ArrayList<>();
            if (regionId != null) {
                for (Asht asht : cache.getAshts()) {
                    if (asht.getRegionId().equals(regionId)) {
                        ashts.add(asht);
                    }
                }
            }
        }
        return ashts;
    }

    public void resetMasterAsht() {
        if (editedMaster != null) {
            editedMaster.setAshtId(null);
            ashts = null;
            editedMaster.setSectionId(null);
            resetMasterSubSection();
            sections = null;
        }
    }

    public void resetLocksmithAsht() {
        if (editedLocksmith != null) {
            editedLocksmith.setAshtId(null);
            ashts = null;
            editedLocksmith.setSectionId(null);
            resetLocksmithSubSection();
            sections = null;
        }
    }

    public List<Section> sectionsByRegionId(Integer regionId) {
        if (sections == null) {
            sections = new ArrayList<>();
            if (regionId != null) {
                for (Section section : cache.getSections()) {
                    if (section.getRegionId().equals(regionId)) {
                        sections.add(section);
                    }
                }
            }
        }
        return sections;
    }

    public List<SubSection> subSectionsBySectionId(Integer sectionId) {
        if (subSections == null) {
            subSections = new ArrayList<>();
            if (sectionId != null) {
                for (SubSection subSection : cache.getSubSections()) {
                    if (subSection.getSectionId().equals(sectionId)) {
                        subSections.add(subSection);
                    }
                }
            }
        }
        return subSections;
    }

    public void resetLocksmithSubSection() {
        if (editedLocksmith != null) {
            editedLocksmith.setSubSectionId(null);
            subSections = null;
        }
    }

    public void resetMasterSubSection() {
        if (editedMaster != null) {
            editedMaster.setSubSectionId(null);
            subSections = null;
        }
    }
}
