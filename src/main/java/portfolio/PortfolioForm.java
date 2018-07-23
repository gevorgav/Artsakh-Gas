package portfolio;

import Core.CacheForm;
import Core.Models.City;
import Core.Models.Region;
import Models.*;
import dao.ClientDao;
import dao.ClientHistoryDao;
import dao.UserDao;
import dao.ViolationClientHistoryDao;
import dao.ViolationCodeDao;
import login.LoginForm;
import login.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
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

    private Boolean jtlog;

    private Integer historyRegionId;

    public Integer getHistoryRegionId() {
        return historyRegionId;
    }

    public void setHistoryRegionId(Integer historyRegionId) {
        this.historyRegionId = historyRegionId;
    }

    public Boolean getJtlog() {
        return Objects.equals(clientHistory.getJTLog(),"+");
    }

    public void setJtlog(Boolean jtlog) {
        this.jtlog = jtlog;
        if (jtlog) {
            clientHistory.setJTLog("+");
        } else {
            clientHistory.setJTLog("-");
        }
    }

    public void searchClientHistory(){
        if (!Objects.isNull(clientId) && !Objects.isNull(historyRegionId) && !Objects.isNull(clientHistoryDao.loadLastClientHistory(clientId, historyRegionId))){
            clientHistory = clientHistoryDao.loadLastClientHistory(clientId, historyRegionId);
            loadClientViolationCode(clientHistory.getId());
            clientHistory.setRegionId(historyRegionId);
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
                listString.append(s).append(", ");
            }
            clientHistory.setStampNumbers(listString.toString());
        }

        this.stamps = stamps;
    }

    public void saveHistory(){
        Integer historyId = clientHistoryDao.insertAndReturnId(clientHistory);
        updateClientHistory();
        saveViolationCodes(historyId);
        cancelHistoryDialog();
    }

    private void updateClientHistory(){
        for (Client client : clients){
            if (Objects.equals(client.getId(), clientHistory.getClientId()) && Objects.equals(client.getRegionId(), clientHistory.getRegionId())){
                client.setClientHistory(clientHistory);
            }
        }
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
        updateClientHistory();
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



    private StreamedContent file;

    public StreamedContent getFile() throws IOException, InvalidFormatException {
        export();
        InputStream stream = new FileInputStream("C:/Users/arshak.askaryan/Desktop/GIT/Artsakh-Gas/src/main/resources/testFile2.xlsx");
        file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8", "downloaded_boromir.xlsx");
        return file;
    }

    public void export() throws IOException, InvalidFormatException {



        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("template.xlsx").getFile());


        String data = "Test data";
        FileOutputStream out = new FileOutputStream("C:/Users/arshak.askaryan/Desktop/GIT/Artsakh-Gas/src/main/resources/testFile2.xlsx");

        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheetAt(0);

        // Get Row at index 1
        Row row = sheet.getRow(3);
//        for(int i=4; i<35;){
//            row = sheet.getRow(i);
//        }

        for(int i=0; i< getSelectedClients().size();i++){
            Client client = getSelectedClients().get(i);
            row = sheet.getRow(i*2+5);
            row.getCell(1).setCellValue(client.getId());
            row.getCell(2).setCellValue(getStreetMap().get(client.getStreetId()).getName() + " " + client.getHomeNumber() + " " + nullToEmptyString(client.getApartmentNumber()));
            row.getCell(3).setCellValue(client.getLastName()+" " +client.getFirstName() + " " + (Objects.isNull(client.getMiddleName()) ? "" : client.getMiddleName()));
            row.getCell(4).setCellValue(client.getPhoneNumber());
            row.getCell(5).setCellValue(client.getClientHistory().getJTLog());
            row.getCell(6).setCellValue(Objects.isNull(client.getClientHistory().getGo1()) ? "": client.getClientHistory().getGo1().toString());
            row.getCell(7).setCellValue(Objects.isNull(client.getClientHistory().getGo2()) ? "": client.getClientHistory().getGo2().toString());
            row.getCell(8).setCellValue(Objects.isNull(client.getClientHistory().getGo3()) ? "": client.getClientHistory().getGo3().toString());
            row.getCell(9).setCellValue(Objects.isNull(client.getClientHistory().getGo4()) ? "": client.getClientHistory().getGo4().toString());
            row.getCell(10).setCellValue(Objects.isNull(client.getClientHistory().getJth()) ? "": client.getClientHistory().getJth().toString());
            row.getCell(11).setCellValue(Objects.isNull(client.getClientHistory().getJtt()) ? "": client.getClientHistory().getJtt().toString());
            row.getCell(12).setCellValue(Objects.isNull(client.getClientHistory().getJv()) ? "": client.getClientHistory().getJv().toString());
            row.getCell(13).setCellValue(Objects.isNull(client.getClientHistory().getJk()) ? "": client.getClientHistory().getJk().toString());
            row.getCell(14).setCellValue(Objects.isNull(client.getClientHistory().getKet()) ? "": client.getClientHistory().getKet().toString());
            row.getCell(15).setCellValue(nullToEmptyString("ս - " + nullToEmptyInteger(client.getClientHistory().getJah())));
            row.getCell(16).setCellValue(client.getClientHistory().getPreviousVisitDate());
            row.getCell(17).setCellValue(client.getClientHistory().getNextVisitDate());
            row.getCell(19).setCellValue(client.getClientHistory().getStampNumbers());

        }
        // Get the Cell at index 2 from the above row
        Cell cell = row.getCell(10);

        // Create the cell if it doesn't exist
        if (cell == null)
            cell = row.createCell(10);


        cell = sheet.getRow(6).getCell(1);
        // Update the cell's value
        cell.setCellType(CellType.STRING);
        cell.setCellValue("Updated Value");


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

    public Map<Integer, Street> getStreetMap(){
        return cache.getStreets().stream()
                .collect(Collectors.toMap(street -> street.getId(), street -> street));
    }


    public String nullToEmptyString(Object o){
        return Objects.isNull(o) ? "": o.toString();
    }

    public Integer nullToEmptyInteger(Object o){
        return Objects.isNull(o) ? 0: Integer.valueOf(o.toString());
    }


    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
