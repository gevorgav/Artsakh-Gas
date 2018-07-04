package hotel;

import Core.CacheForm;
import Core.FileUpload;
import Core.Interface.Form;
import Core.Models.City;
import Core.Models.Region;
import Core.Root;
import Core.Util;
import Models.*;
import org.primefaces.context.RequestContext;
import portfolio.Portfoliohotels;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class SettingsForm implements Serializable {

    private static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category(1, "ԱՇՏ"));
        categories.add(new Category(2, "Քաղաք/Գյուղ"));
        categories.add(new Category(3, "ԳԿԿ"));
        categories.add(new Category(4, "ԳՐՍ"));//
        categories.add(new Category(5, "Սարքերի գներ")); // TODO -
        categories.add(new Category(6, "Շրջան"));
        categories.add(new Category(7, "Մաս"));
        categories.add(new Category(8, "Ենթատեղամաս"));
        categories.add(new Category(9, "Փողոց"));
        categories.add(new Category(10, "Գ/Հ տիպ"));
        categories.add(new Category(11, "Violation code"));
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
            }
        }
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
