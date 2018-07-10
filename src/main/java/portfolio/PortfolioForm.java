package portfolio;

import Core.CacheForm;
import Core.Models.City;
import Models.*;
import dao.ClientDao;
import dao.ClientHistoryDao;
import dao.ViolationClientHistoryDao;
import dao.ViolationCodeDao;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {
    private List<Client> clients;
    private List<Client> filteredClients;
    private Date date9;
    private ClientHistory clientHistory;
    private Integer clientId;
    private List<String> stamps;
    private List<String> violationActNumber;
    private String[] violationCodes;
    private List<Client> selectedClients;
    private CacheForm cache;
    private String[] paidStatus;
    public PortfolioForm() {
    }

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientHistoryDao clientHistoryDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

    @Autowired
    private ViolationClientHistoryDao violationClientHistoryDao;

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

    public Date getDate9() {
        return date9;
    }

    public void setDate9(Date date9) {
        this.date9 = date9;
    }

    public ClientHistory getClientHistory() {
        return clientHistory;
    }

    public void setClientHistory(ClientHistory clientHistory) {
        this.clientHistory = clientHistory;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
        resetClientEditForm();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('clientDialog').show()");
    }

    // Client Form

    private Client clientSnapshot;

    private Client client;

    private List<City> cities;

    private List<Street> streets;

    private List<SubSection> subSections;

    private List<Section> sections;

    private List<Asht> ashts;

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

    public List<Section> sectionsByCityId(Integer cityId) {
        if (sections == null) {
            sections = new ArrayList<>();
            if (cityId != null) {
                for (Section section : cache.getSections()) {
                    if (section.getCityId().equals(cityId)) {
                        sections.add(section);
                    }
                }
            }
        }
        return sections;
    }

    public void resetCity() {
        if (client != null) {
            client.setCityId(null);
            client.setStreetId(null);
            client.setAshtId(null);
            streets = null;
            cities = null;
            ashts = null;
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
            client.setSectionId(null);
            client.setSubSectionId(null);
            resetSubSection();
            sections = null;
        }
    }

    public void resetSubSection() {
        if (client != null) {
            client.setSubSectionId(null);
             subSections = null;
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
//        resetClientEditForm();
        closeClientDialog();
    }

    public void closeClientDialog() {
        RequestContext.getCurrentInstance().execute("PF('clientDialog').hide()");
    }

    public void resetClientEditForm() {
//        this.client = null;
        this.cities = null;
        this.streets = null;
        this.sections = null;
        this.subSections = null;
    }

    public boolean validate(Client client) {
        return true;
    }

    public void addNewClient(){
        this.client = new Client();
        client.setNew(true);
        showClientEditDialog();
    }

    //Client History

    public void searchClientHistory(){
        if (!Objects.isNull(clientId) && !Objects.isNull(clientHistoryDao.loadLastClientHistory(clientId))){
            clientHistory = clientHistoryDao.loadLastClientHistory(clientId);
            loadClientViolationCode(clientHistory.getId());
        }else {
            clientHistory = null;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Incorrect clientID.");
            facesContext.addMessage(null, facesMessage);
        }
    }

    private void loadClientViolationCode(Integer clientId) {
        List<ViolationCode> violationCodes = violationCodeDao.loadByClientId(clientId);
        int i = 0;
        this.violationCodes = new String[violationCodes.size()];
        for (ViolationCode violationCode:violationCodes) {
            this.violationCodes[i]=violationCode.getName();
            i++;
        }
    }

    public void cancelHistoryDialog(){
        clientId = null;
        clientHistory = null;
        RequestContext.getCurrentInstance().execute("PF('historyDialog').hide()");
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public List<String> getViolationActNumber() {
        if(Objects.isNull(clientHistory.getViolationActNumber())){
            return Collections.emptyList();
        }
        this.violationActNumber = Arrays.asList(clientHistory.getViolationActNumber().split("\\s*,\\s*"));
        return violationActNumber;
    }

    public void setViolationActNumber(List<String> violationActNumber) {
        if (Objects.nonNull(violationActNumber)){
            StringBuilder listString = new StringBuilder();
            for (String s : violationActNumber)
            {
                listString.append(s).append(",");
            }
            clientHistory.setViolationActNumber(listString.toString());
        }

        this.violationActNumber = violationActNumber;
    }

    public List<String> getStamps() {
        if(Objects.isNull(clientHistory.getStampNumbers())){
            return Collections.emptyList();
        }
        this.stamps = Arrays.asList(clientHistory.getStampNumbers().split("\\s*,\\s*"));
        return stamps;
    }

    public void setStamps(List<String> stamps) {
        if (Objects.nonNull(stamps)){
            StringBuilder listString = new StringBuilder();
            for (String s : stamps)
            {
                listString.append(s).append(",");
            }
            clientHistory.setStampNumbers(listString.toString());
        }

        this.stamps = stamps;
    }

    public void saveHistory(){
        Integer historyId = clientHistoryDao.insertAndReturnId(clientHistory);
        saveViolationCodes(historyId);
        cancelHistoryDialog();
    }

    private void saveViolationCodes(Integer historyId) {
        for (ViolationCode violationCode:cache.getViolationCodes()) {
            if (Arrays.asList(violationCodes).indexOf(violationCode.getName()) != -1){
                violationClientHistoryDao.insert(new ViolationClientHistory(violationCode.getId(), historyId));
            }
        }
    }

    public void editHistory(){
        clientHistoryDao.update(clientHistory);
        editViolationCode(clientHistory.getId());
    }

    private void editViolationCode(Integer historyId) {
        List<ViolationCode> violationCodesOld = violationCodeDao.loadByClientId(historyId);
        for (ViolationCode violationCode:violationCodesOld) {
            violationClientHistoryDao.delete(violationCode.getId(), historyId);
        }
        saveViolationCodes(historyId);
    }

    public String[] getViolationCodes() {
        return violationCodes;
    }

    public void setViolationCodes(String[] violationCodes) {
        this.violationCodes = violationCodes;
    }

    public ViolationCodeDao getViolationCodeDao() {
        return violationCodeDao;
    }

    public void setViolationCodeDao(ViolationCodeDao violationCodeDao) {
        this.violationCodeDao = violationCodeDao;
    }

    public List<Client> getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(List<Client> selectedClients) {
        this.selectedClients = selectedClients;
    }

    public String[] getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String[] paidStatus) {
        this.paidStatus = paidStatus;
    }
}
