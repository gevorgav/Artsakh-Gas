package portfolio;

import Core.CacheForm;
import Core.Models.City;
import Core.Models.Month;
import Models.*;
import dao.*;
import login.LoginForm;
import login.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    private LoginForm loginForm;
    private List<Client> customFilteredClients = new ArrayList<>();

    private Integer master;
    private Integer master1;

    private Integer currentSemiAnnualId;

    public Integer getMaster() {
        return master;
    }

    public void setMaster(Integer master) {
        this.master = master;
    }

    public Integer getMaster1() {
        return master1;
    }

    public void setMaster1(Integer master1) {
        this.master1 = master1;
    }

    public PortfolioForm() {
    }

    public void initClientHistory() {
        if (!getLoginForm().getUser().getRole().equals(User.Role.ADMIN)) {
            this.historyRegionId = getLoginForm().getUser().getRegionId();
        }

    }

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientHistoryDao clientHistoryDao;

    @Autowired
    private ClientHistoryTmpDao clientHistoryTmpDao;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

    @Autowired
    private ViolationClientHistoryDao violationClientHistoryDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private VisitPlanDao visitPlanDao;

    public List<Client> getClients() {
        if (clients == null) {
            this.clients = clientDao.loadAll(getLoginForm().getUser().getRole().equals(User.Role.ADMIN) ? null : getLoginForm().getUser().getRegionId());
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

    public List<Client> getCustomFilteredClients() {
        return customFilteredClients;
    }

    public void setCustomFilteredClients(List<Client> customFilteredClients) {
        List<Client> newCustomFilteredClients = filter(customFilteredClients);
        this.customFilteredClients = newCustomFilteredClients;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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

    public void showClientEditDialog() {
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

    public void resetCity() {
        if (client != null) {
            client.setCityId(null);
            client.setStreetId(null);
            client.setAshtId(null);
            streets = null;
            cities = null;
            ashts = null;
            client.setSectionId(null);
            resetSubSection();
            sections = null;
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
                ClientHistory clientHistory = new ClientHistory(this.client.getId(), this.client.getRegionId(), getCurrentSemiAnnual().getId());
                clientHistoryDao.insertAndReturnId(clientHistory);
                this.client.setClientHistory(clientHistory);
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
        boolean isValid = true;
        if (client.getId() == null || client.getId().trim().isEmpty()) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Բաժանորդի համարը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:id", facesMessage);
            isValid = false;
        }
        if (client.getFirstName() == null || client.getFirstName().trim().isEmpty()) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Անունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:firstNameId", facesMessage);
            isValid = false;
        }
        if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ազգանունը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:lastNameId", facesMessage);
            isValid = false;
        }
        if (client.getRegionId() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Շրջանը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:regionId", facesMessage);
            isValid = false;
        }
        if (client.getCityId() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Քաղաքը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:cityId", facesMessage);
            isValid = false;
        }

        if (client.getStreetId() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Գյուղ/Փողոցը պարտադիր դաշտ է");
            FacesContext.getCurrentInstance().addMessage("clientEditFormId:streetId", facesMessage);
            isValid = false;
        }

        return isValid;
    }

    public void addNewClient() {
        this.client = new Client();
        client.setNew(true);
        showClientEditDialog();
    }

    //Client History

    private Boolean jtlog;

    private Boolean risk;

    private Integer historyRegionId;

    public Integer getHistoryRegionId() {
        return historyRegionId;
    }

    public void setHistoryRegionId(Integer historyRegionId) {
        this.historyRegionId = historyRegionId;
    }

    public Boolean getJtlog() {
        return Objects.equals(clientHistory.getJTLog(), "+");
    }

    public void setJtlog(Boolean jtlog) {
        this.jtlog = jtlog;
        if (jtlog) {
            clientHistory.setJTLog("+");
        } else {
            clientHistory.setJTLog("-");
        }
    }

    public Boolean getRisk() {
        return Objects.equals(clientHistory.getRisk(), "+");
    }

    public void setRisk(Boolean risk) {
        this.risk = risk;
        if (risk) {
            clientHistory.setRisk("+");
        } else {
            clientHistory.setRisk("-");
        }
    }

    public void searchClientHistory() {
        if (!Objects.isNull(clientId) && !Objects.isNull(historyRegionId) && !Objects.isNull(clientHistoryDao.loadLastClientHistory(clientId, historyRegionId))) {
            clientHistory = clientHistoryDao.loadLastClientHistory(clientId, historyRegionId);
            loadClientViolationCode(clientHistory.getId());
            clientHistory.setRegionId(historyRegionId);
        } else {
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
        for (ViolationCode violationCode : violationCodes) {
            this.violationCodes[i] = violationCode.getName();
            i++;
        }
    }

    public void cancelHistoryDialog() {
        clientId = null;
        clientHistory = null;
        historyRegionId = null;
        RequestContext.getCurrentInstance().update("clientTableId");
        RequestContext.getCurrentInstance().execute("PF('historyDialog').hide()");
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public List<String> getViolationActNumber() {
        if (Objects.isNull(clientHistory.getViolationActNumber())) {
            return Collections.emptyList();
        }
        this.violationActNumber = Arrays.asList(clientHistory.getViolationActNumber().split("\\s*,\\s*"));
        return violationActNumber;
    }

    public void setViolationActNumber(List<String> violationActNumber) {
        if (Objects.nonNull(violationActNumber)) {
            StringBuilder listString = new StringBuilder();
            for (String s : violationActNumber) {
                listString.append(s).append(",");
            }
            clientHistory.setViolationActNumber(listString.toString());
        }

        this.violationActNumber = violationActNumber;
    }

    public List<String> getStamps() {
        if (Objects.isNull(clientHistory.getStampNumbers())) {
            return Collections.emptyList();
        }
        this.stamps = Arrays.asList(clientHistory.getStampNumbers().split("\\s*,\\s*"));
        return stamps;
    }

    public void setStamps(List<String> stamps) {
        if (Objects.nonNull(stamps)) {
            StringBuilder listString = new StringBuilder();
            for (String s : stamps) {
                listString.append(s).append(", ");
            }
            clientHistory.setStampNumbers(listString.toString());
        }

        this.stamps = stamps;
    }

    public void saveHistory() {
        clientHistory.setUserId(getLoginForm().getUser().getId());
        Integer historyId = clientHistoryDao.insertAndReturnId(clientHistory);
        Client client = updateClientHistory();
        if (clientHistory.getVisitType() == 1) {
            Integer clientHistoryTmpId = saveClientHistoryTemp(historyId);
            Double debt = initDept();
            savePayment(clientHistoryTmpId, client.getId(), client.getFirstName(), client.getLastName(), client.getMiddleName(), client.getRegionId(), client.getCityId(), client.getStreetId(), clientHistory.getSemiAnnualId(), debt);
        }
        saveViolationCodes(historyId);
        cancelHistoryDialog();
    }

    private Double initDept() {
        Integer deviceCount = getClientHistoryDeviceCount();
        for (PriceList priceList : cache.getPriceLists()) {
            if (priceList.getFormula().contains("=") && !priceList.getFormula().contains("<=")) {
                Integer count = Integer.parseInt(priceList.getFormula().replace("=", ""));
                if (Objects.equals(count, deviceCount)) {
                    return priceList.getPrice();
                }
            } else if (priceList.getFormula().contains("<=")) {
                Integer count = Integer.parseInt(priceList.getFormula().replace("<=", ""));
                if (count <= deviceCount) {
                    return priceList.getPrice();
                }
            }
        }
        return 0.00;
    }

    private void savePayment(Integer clientHistoryTmpId, String clientId, String firstName, String lastName, String
            middleName, Integer regionId, Integer cityId, Integer streetId, Integer semiAnnualId, Double debt) {
        Payment lastPayment = paymentDao.loadLastPayment(clientId, regionId);
        paymentDao.insert(new Payment(clientId, clientHistoryTmpId, firstName, lastName, middleName, regionId, cityId, streetId, semiAnnualId, lastPayment != null ? lastPayment.getDebt() + debt : debt, 0.00));
    }

    private Integer saveClientHistoryTemp(Integer historyId) {
        return clientHistoryTmpDao.insertAndReturnId(new ClientHistoryTmp(clientHistory, historyId));
    }

    private Client updateClientHistory() {
        for (Client client : clients) {
            if (Objects.equals(client.getId(), clientHistory.getClientId()) && Objects.equals(client.getRegionId(), clientHistory.getRegionId())) {
                client.setClientHistory(clientHistory);
                return client;
            }
        }
        return null;
    }

    private void saveViolationCodes(Integer historyId) {
        for (ViolationCode violationCode : cache.getViolationCodes()) {
            if (Arrays.asList(violationCodes).indexOf(violationCode.getName()) != -1) {
                violationClientHistoryDao.insert(new ViolationClientHistory(violationCode.getId(), historyId));
            }
        }
    }

    public void editHistory() {
        clientHistory.setUserId(getLoginForm().getUser().getId());
        clientHistoryDao.update(clientHistory);
        editViolationCode(clientHistory.getId());
        updateClientHistory();
    }

    private void editViolationCode(Integer historyId) {
        List<ViolationCode> violationCodesOld = violationCodeDao.loadByClientId(historyId);
        for (ViolationCode violationCode : violationCodesOld) {
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


    private StreamedContent file;

    public StreamedContent getFile() throws IOException, InvalidFormatException {
        if (getSelectedClients().isEmpty() || getSelectedClients().size() > 15) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Սխալ", "Տվյալները սխալ են մուտքագրված"));
            return null;
        }
        export();
        InputStream stream = new FileInputStream(getFileUploadUrl());
        file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8", "downloaded_boromir.xlsx");
        return file;
    }

    public void export() throws IOException, InvalidFormatException {
        if (getSelectedClients().isEmpty() || getSelectedClients().size() > 15) {
            return;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(getTemaplateUrl());


        FileOutputStream out = new FileOutputStream(getFileUploadUrl());

        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheetAt(0);

        // Get Row at index 1
        Row row = null;

        for (int i = 0; i < getSelectedClients().size(); i++) {
            Client client = getSelectedClients().get(i);
            if (client != null) {
                List<ViolationCode> violationCodes = loadViolationClientHistory(client.getClientHistory().getId());
                row = sheet.getRow(i * 2 + 5);
                row.getCell(1).setCellValue(nullToEmptyString(client.getId(), false));
                row.getCell(2).setCellValue((Objects.nonNull(getStreetMap().get(client.getStreetId())) ? getStreetMap().get(client.getStreetId()).getName() : "") + " " + nullToEmptyString(client.getHomeNumber(), false) + " " + nullToEmptyString(client.getApartmentNumber(), false));
                row.getCell(3).setCellValue(client.getLastName() + " " + client.getFirstName() + " " + (Objects.isNull(client.getMiddleName()) ? "" : client.getMiddleName()));
                row.getCell(4).setCellValue(client.getPhoneNumber());
                row.getCell(5).setCellValue(client.getClientHistory().getJTLog());
                row.getCell(6).setCellValue(nullToEmptyString(client.getClientHistory().getGo1(), false));
                row.getCell(7).setCellValue(nullToEmptyString(client.getClientHistory().getGo2(), false));
                row.getCell(8).setCellValue(nullToEmptyString(client.getClientHistory().getGo3(), false));
                row.getCell(9).setCellValue(nullToEmptyString(client.getClientHistory().getGo4(), false));
                row.getCell(10).setCellValue(nullToEmptyString(client.getClientHistory().getJth(), false));
                row.getCell(11).setCellValue(nullToEmptyString(client.getClientHistory().getJtt(), false));
                row.getCell(12).setCellValue(nullToEmptyString(client.getClientHistory().getJv(), false));
                row.getCell(13).setCellValue(nullToEmptyString(client.getClientHistory().getJk(), false));
                row.getCell(14).setCellValue(nullToEmptyString(client.getClientHistory().getKet(), false));
                row.getCell(15).setCellValue(nullToEmptyString("ս - " + nullToEmptyInteger(client.getClientHistory().getJah()), false));
                row.getCell(16).setCellValue(client.getClientHistory().getPreviousVisitDate());
                row.getCell(17).setCellValue(client.getClientHistory().getNextVisitDate());
                StringBuilder listString = new StringBuilder();
                if (!violationCodes.isEmpty()) {
                    for (ViolationCode s : violationCodes) {
                        listString.append(s.getName()).append(", ");
                    }
                    listString.deleteCharAt(listString.length() - 2);
                }
                row.getCell(18).setCellValue(listString.toString());
                row.getCell(19).setCellValue(client.getClientHistory().getStampNumbers());
                row.getCell(20).setCellValue(nullToEmptyString(client.getClientHistory().getRisk(), false));

                //Second row
                row = sheet.getRow(i * 2 + 6);
                row.getCell(1).setCellValue(nullToEmptyString(client.getTypeNumber(), false));
                row.getCell(2).setCellValue(nullToEmptyString(client.getCounterNumber(), false));
                row.getCell(6).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaGo1(), true));
                row.getCell(7).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaGo2(), true));
                row.getCell(8).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaGo3(), true));
                row.getCell(9).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaGo4(), true));
                row.getCell(10).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaJth(), true));
                row.getCell(11).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaJtt(), true));
                row.getCell(12).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaJv(), true));
                row.getCell(13).setCellValue(nullToEmptyString(client.getClientHistory().getBacakaJk(), true));
                row.getCell(15).setCellValue("փ - " + nullToEmptyInteger(client.getClientHistory().getJah()));
                row.getCell(18).setCellValue(client.getClientHistory().getViolationActNumber());
            }

        }


        row = sheet.getRow(38);
        Master selectedMaster = null;
        if (Objects.nonNull(this.master)) {
            selectedMaster = cache.getMasters().stream().filter(m -> m.getId().equals(this.master)).findFirst().get();
        }
        if (Objects.nonNull(selectedMaster)) {
            row.getCell(0).setCellValue("Տեղամասի վարպետ`" + selectedMaster.toString());
        }

        row = sheet.getRow(41);
        Locksmith selectedLocksmith = null;

        if (Objects.nonNull(this.master1)) {
            selectedLocksmith = cache.getLocksmiths().stream().filter(m -> m.getId().equals(this.master1)).findFirst().get();
        }
        if (Objects.nonNull(selectedLocksmith)) {
            row.getCell(0).setCellValue("Փականագործ՝" + selectedLocksmith.toString());
        }


        // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream("template.xlsx");
        workbook.write(out);
        out.close();

        // Closing the workbook
        workbook.close();
    }

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET);
    }

    public Map<Integer, Street> getStreetMap() {
        return cache.getStreets().stream()
                .collect(Collectors.toMap(street -> street.getId(), street -> street));
    }


    public String nullToEmptyString(Object o, boolean isAbsent) {
        return Objects.isNull(o) ? "" : isAbsent ? "բ" + o.toString() : o.toString();
    }

    public Integer nullToEmptyInteger(Object o) {
        return Objects.isNull(o) ? 0 : Integer.valueOf(o.toString());
    }


    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public List<ViolationCode> loadViolationClientHistory(Integer clientHistoryId) {
        return violationCodeDao.loadByClientId(clientHistoryId);
    }


    public VisitType getVisitType(Integer id) {
        if (id != null) {
            for (VisitType visitType : cache.getVisitTypes()) {
                if (Objects.equals(visitType.getId(), id)) {
                    return visitType;
                }
            }
        }
        return null;
    }

    // Filter Form

    private Filter filter;

    private Filter filterSnapshot;

    public void addFilter() {
        closeFilterDialog();
    }

    public void cancelFilterDialog() {
        this.filter = this.filterSnapshot;
        closeFilterDialog();
    }

    public void closeFilterDialog() {
        RequestContext.getCurrentInstance().execute("PF('filterDialog').hide()");
    }

    public void closeGraficDialog() {
        this.master = null;
        RequestContext.getCurrentInstance().execute("PF('graficDialog').hide()");
    }

    public void openFilterDialog() {
        if (this.filter == null) {
            this.filter = new Filter();
        }
        try {
            this.filterSnapshot = (Filter) this.filter.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        showFilterEditDialog();
    }

    public void showFilterEditDialog() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('filterDialog').show()");
    }

    public void removeFilter() {
        this.filter = null;
    }

    public void resetFilteredCity() {
        if (filter != null) {
            filter.setCityId(null);
            filter.setStreetId(null);
            filter.setAshtId(null);
            streets = null;
            cities = null;
            ashts = null;
            filter.setSectionId(null);
            resetFilteredSubSection();
            sections = null;
        }
    }

    public void resetFilteredStreet() {
        if (filter != null) {
            filter.setStreetId(null);
            streets = null;
        }
    }

    public void resetFilteredSubSection() {
        if (filter != null) {
            filter.setSubSectionId(null);
            subSections = null;
        }
    }

    public List<Client> filter(List<Client> filteredClients) {
        List<Client> newFilteredClients = new ArrayList<>();
        List<Client> filteredClientsBySemiAnnual = new ArrayList<>();
        if (filteredClients == null) {
            filteredClients = this.clients;
        }
        for (Client client : filteredClients) {
            if(Objects.equals(getCurrentSemiAnnualId(), client.getClientHistory().getSemiAnnualId())){
                filteredClientsBySemiAnnual.add(client);
            }
        }
        if (this.filter != null) {
            for (Client client : filteredClientsBySemiAnnual) {
                if (this.filter.getRegionId() != null && !Objects.equals(client.getRegionId(), this.filter.getRegionId())) {
                    continue;
                }
                if (this.filter.getCityId() != null && !Objects.equals(client.getCityId(), this.filter.getCityId())) {
                    continue;
                }
                if (this.filter.getStreetId() != null && !Objects.equals(client.getStreetId(), this.filter.getStreetId())) {
                    continue;
                }
                if (this.filter.getAshtId() != null && !Objects.equals(client.getAshtId(), this.filter.getAshtId())) {
                    continue;
                }
                if (this.filter.getGrpId() != null && !Objects.equals(client.getGrpId(), this.filter.getGrpId())) {
                    continue;
                }
                if (this.filter.getSectionId() != null && !Objects.equals(client.getSectionId(), this.filter.getSectionId())) {
                    continue;
                }
                if (this.filter.getSubSectionId() != null && !Objects.equals(client.getSubSectionId(), this.filter.getSubSectionId())) {
                    continue;
                }
//                if(this.filter.getViolationCodes() != null && this.filter.getViolationCodes().length != 0){
//                    List<ViolationCode> violationCodes = cache.getViolationCodesByClientHistory(client.getClientHistory().getId());
//                    boolean isExpected = false;
//                    if(violationCodes != null) {
//                        for (String violationCode : this.filter.getViolationCodes()) {
//                            if (!violationCodes.contains(violationCode)) {
//                                isExpected = true;
//                                break;
//                            }
//                        }
//                        if(isExpected) {
//                            continue;
//                        }
//                    }
//                }
                newFilteredClients.add(client);
            }
            return newFilteredClients;
        } else {
            return filteredClientsBySemiAnnual;
        }
    }


    private String getFileUploadUrl() {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("fileUploadUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String getTemaplateUrl() {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("templateUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    private Integer getClientHistoryDeviceCount() {
        Integer count = 0;
        count += clientHistory.getJtt() != null ? clientHistory.getJtt() : 0;
        count += clientHistory.getJah() != null ? clientHistory.getJah() : 0;
        count += clientHistory.getGo1() != null ? clientHistory.getGo1() : 0;
        count += clientHistory.getGo2() != null ? clientHistory.getGo2() : 0;
        count += clientHistory.getGo3() != null ? clientHistory.getGo3() : 0;
        count += clientHistory.getGo4() != null ? clientHistory.getGo4() : 0;
        count += clientHistory.getGo5() != null ? clientHistory.getGo5() : 0;
        count += clientHistory.getGo6() != null ? clientHistory.getGo6() : 0;
        count += clientHistory.getJk() != null ? clientHistory.getJk() : 0;
        count += clientHistory.getJv() != null ? clientHistory.getJv() : 0;
        count += clientHistory.getJth() != null ? clientHistory.getJth() : 0;
        count += clientHistory.getKet() != null ? clientHistory.getKet() : 0;
        count += clientHistory.getPakan() != null ? clientHistory.getPakan() : 0;

        return count;
    }

    public SemiAnnual getSemiAnnualById(Integer id){
        List<SemiAnnual> semiAnnuals = cache.getSemiAnnuals();
        for(SemiAnnual semiAnnual: semiAnnuals) {
            if(Objects.equals(semiAnnual.getId(), id)){
                return semiAnnual;
            }
        }
        return null;
    }

    public Integer getCurrentSemiAnnualId(){
        if(currentSemiAnnualId == null) {
          currentSemiAnnualId = getCurrentSemiAnnual().getId();
        }
        return currentSemiAnnualId;
    }

    public void setCurrentSemiAnnualId(Integer currentSemiAnnualId) {
        this.currentSemiAnnualId = currentSemiAnnualId;
    }

    private SemiAnnual getCurrentSemiAnnual(){
        SemiAnnual currentSemiAnnual = null;
        List<SemiAnnual> semiAnnuals = cache.getSemiAnnuals();
        LocalDate currentDate = LocalDate.now();
        Integer semi = currentDate.getMonthValue() < 7 ? 1 : 2;
        for (SemiAnnual semiAnnual : semiAnnuals) {
            if (Objects.equals(semiAnnual.getId() / 10, currentDate.getYear()) && Objects.equals(semiAnnual.getId() % 10, semi)) {
                currentSemiAnnual =  semiAnnual;
                break;
            }
        }
        return currentSemiAnnual;
    }

  /* -------------- Visit Plan Form Start --------*/

    private Integer visitPlanAshtId;

    private Integer semiAnnualId;

    private Integer visitPlanRegionId;

    private List<Asht> visitPlanAshtsByRegionId;

    private List<VisitPlan> visitPlans;

    public Integer getVisitPlanAshtId() {
        return visitPlanAshtId;
    }

    public void setVisitPlanAshtId(Integer visitPlanAshtId) {
        this.visitPlanAshtId = visitPlanAshtId;
    }

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer semiAnnualId) {
        this.semiAnnualId = semiAnnualId;
    }

    public Integer getVisitPlanRegionId() {
        return visitPlanRegionId;
    }

    public void setVisitPlanRegionId(Integer visitPlanRegionId) {
        this.visitPlanRegionId = visitPlanRegionId;
    }

    public List<VisitPlan> getVisitPlans() {
        return visitPlans;
    }

    public void setVisitPlans(List<VisitPlan> visitPlans) {
        this.visitPlans = visitPlans;
    }

    public void initVisitPlan() {
        openVisitPlanDialog();
    }

    public void openVisitPlanDialog(){
        resetVisitPlan();
        if (!getLoginForm().getUser().getRole().equals(User.Role.ADMIN)) {
            setVisitPlanRegionId(getLoginForm().getUser().getRegionId());
        }
        RequestContext.getCurrentInstance().execute("PF('visitPlanDialog').show()");
    }

    public void cancelPlanVisitDialog() {
        resetVisitPlan();
        RequestContext.getCurrentInstance().execute("PF('visitPlanDialog').hide()");
    }

    public void resetVisitPlan(){
        visitPlanRegionId = null;
        semiAnnualId = null;
        visitPlans = null;
    }

    public List<Asht> getVisitPlanAshtsByRegionId(Integer regionId) {
        if(visitPlanAshtsByRegionId == null && regionId != null) {
            visitPlanAshtsByRegionId = new ArrayList<>();
            for(Asht asht: cache.getAshts()) {
                if (asht.getRegionId().equals(regionId)) {
                    visitPlanAshtsByRegionId.add(asht);
                }
            }
        }
        return visitPlanAshtsByRegionId;
    }

    public void setVisitPlanAshtsByRegionId(List<Asht> visitPlanAshtsByRegionId) {
        this.visitPlanAshtsByRegionId = visitPlanAshtsByRegionId;
    }

    public void resetVisitPlanAsht() {
        visitPlanAshtsByRegionId = null;
    }

    public void saveVisitPlan() {
    }

    public void searchVisitPlans() {
        if (validateVisitPlanSearchFields()) {
            visitPlans = new ArrayList<>();
            for (Section section : cache.getSections()) {
                if (section.getRegionId().equals(visitPlanRegionId)) {
                    for (int i = 6 * (semiAnnualId % 10) - 5; i <= 6 * (semiAnnualId % 10); i++) {
                        Integer monthId = semiAnnualId * 100 + i;
                        VisitPlan loadedVisitPlan = visitPlanByMonthId(monthId, section.getId());
                        if (loadedVisitPlan == null) {
                            visitPlans.add(new VisitPlan(section.getId(), monthId, null, semiAnnualId, section, getMonthById(monthId)));
                        } else {
                            loadedVisitPlan.setSection(section);
                            loadedVisitPlan.setMonth(getMonthById(monthId));
                            visitPlans.add(loadedVisitPlan);
                        }

                    }
                }
            }
            if(visitPlans != null && visitPlans.isEmpty()){
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Տվյալները չեն գտնվել");
                facesContext.addMessage("visitPlanSearchFormId:visitPlanList", facesMessage);
            }
        }
    }

    public VisitPlan visitPlanByMonthId(Integer monthId, Integer sectionId){
        for(VisitPlan visitPlan: cache.getVisitPlans(sectionId)){
            if(visitPlan.getMonthId().equals(monthId)){
                return visitPlan;
            }
        }
        return null;
    }

    private boolean validateVisitPlanSearchFields(){
        boolean isValid = true;
        if(visitPlanRegionId == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ընտրեք որևէ շրջան");
            FacesContext.getCurrentInstance().addMessage("visitPlanSearchFormId:visitPlanRegionId", facesMessage);
            isValid = false;
        }
        if(semiAnnualId == null){
            FacesMessage facesMessage =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ընտրեք որևէ կիսամյակ");
            FacesContext.getCurrentInstance().addMessage("visitPlanSearchFormId:visitPlanSemiAnnualId", facesMessage);
            isValid = false;
        }
        return isValid;
    }

    public void onRowEdit(RowEditEvent event) {
        VisitPlan visitPlan = (VisitPlan) event.getObject();
        if(visitPlan.getId() != null) {
            visitPlanDao.update(visitPlan);
        } else {
            visitPlanDao.insertAndReturnId(visitPlan);

        }
        cache.resetVisitPlansBySection(visitPlan.getSectionId());
    }

    public Month getMonthById(Integer id){
        for(Month month: cache.getMonths()) {
            if(Objects.equals(id, month.getId())){
                return month;
            }
        }
        return null;
    }

  /* -------------- Visit Plan Form End ----------*/

}
