package portfolio;

import Core.Models.Country;
import Core.Models.Month;
import Core.Models.Year;
import Core.Root;
import Core.Util;
import Models.Sights;
import Models.Transport;
import home.HomeForm;
import hotel.Hotel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {

    public PortfolioForm() {
    }

    private Root root;
    private List<Year> years;
    private String selectedYear;
    private Portfolio selectedPortfolio;
    private List<Portfolio> portfolios  = new ArrayList<>();
    private List<Transport> transports;
    private List<Sights> sights;
    private List<Portfoliosights> portfoliosightses;
    private Portfoliomonthly portfoliomonthly = new Portfoliomonthly();
    private Portfoliocountry portfoliocountry = new Portfoliocountry();
    private Portfoliosights portfoliosights = new Portfoliosights();
    private List<Portfoliocountry> portfoliocountries;
    final static Logger logger = Logger.getLogger(String.valueOf(PortfolioForm.class));
    private Yearlyinforamtion yearlyinforamtion;
    private List<Country> countries;
    private List<Transportsyear> transportsyears;
    private Transportsyear transportsyear = new Transportsyear();
    private List<Portfoliohotels> portfoliohotelses;
    private Portfoliohotels portfoliohotels = new Portfoliohotels();
    private List<Hotel> hotels;
    private BigDecimal totalSpent;
    private BigDecimal totalBalance;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public void init(){
    }

    public List<Year> getYears() {
        if(this.years == null){
            this.years = getRoot().getYearDao().getAll();
        }
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public List<Transport> getTransports() {
        if(transports == null){
            return getRoot().getPortfolioDao().getTransport().stream().filter(x-> !filterTransportsyears(x.getId())).sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        }
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public String getSelectedYear() {
        if(selectedYear == null || selectedYear == ""){
            selectedYear = String.valueOf(new Date().getYear() + 1900);
            changeYear();
        }
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
        this.yearlyinforamtion = null;
    }

    public void reset(){
        System.out.println("AAA");
    }

    private String console;

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public void changeYear(){
        this.portfolios = getRoot().getPortfolioDao().getAll().stream().
                        filter(x -> selectedYear.equals(x.getYear().toString())).
                        collect(Collectors.toList());
    }

    public Portfolio getSelectedPortfolio() {
        return selectedPortfolio;
    }

    public void setSelectedPortfolio(Portfolio selectedPortfolio) {
        this.selectedPortfolio = selectedPortfolio;
    }

    public void editPortfolio(Portfolio portfolio) {
        this.selectedPortfolio = portfolio;
        this.countries = null;
        this.portfoliocountries = null;
    }

    public void save(){
        if(selectedPortfolio.getArmtouristcount() + selectedPortfolio.getOthertouristcount() == selectedPortfolio.getTotaltouristcount()){
            getRoot().getPortfolioDao().update(selectedPortfolio);
            logger.info("Saved successfully " + selectedPortfolio.toString());
            RequestContext.getCurrentInstance().update("panelGroupView");
            Util.getBean("HomeForm", HomeForm.class).setJson(null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Տվյալները հաջողությամբ պահպանվել են",  "Your message: " ) );
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Սխալ", "ՀՀ զբոսաշրջիկների թիվի և Արտերկրյա զբոսաշրջիկների թիվի գումարը պիտի հավասար լինի Ընդհանուր զբոսաշրջիկների թիվին");

            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public List<Portfoliosights> getPortfoliosightses() {
        return  portfoliosightses = getRoot().getPortfolioDao().getPortfolioSights();
    }

    public void setPortfoliosightses(List<Portfoliosights> portfoliosightses) {
        this.portfoliosightses = portfoliosightses;
    }



    public List<Sights> getSights() {
        return getRoot().getPortfolioDao().getSightses().stream().filter(x-> !filterQuarterSights(x.getId())).collect(Collectors.toList());
    }

    public List<Sights> getSightsAll() {
        return getRoot().getPortfolioDao().getSightses();
    }

    public void setSights(List<Sights> sights) {
        this.sights = sights;
    }

    private Integer sightId;

    public Integer getSightId() {
        return sightId;
    }

    public void setSightId(Integer sightId) {
        this.sightId = sightId;
    }

    public void addPortfolioSight(){
        if(selectedPortfolio != null && getPortfoliosights().getStightsid() != null){
            getRoot().getPortfolioDao().insertPortfoliosights(selectedPortfolio.getId(), getPortfoliosights().getStightsid(),getPortfoliosights().getCount());
            sightId = null;
            portfoliosightses = null;
        }
    }

    public List<Portfoliosights> getPortfoliosightsesByPortfolioId(){
        return getPortfoliosightses().stream().filter(x -> selectedPortfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList());
    }

    public List<Portfoliomonthly> getPortfoliomonthlyByPortfolioId(){
        return getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> selectedPortfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList());
    }

    public Portfoliomonthly getPortfoliomonthly() {
        return portfoliomonthly;
    }

    public void setPortfoliomonthly(Portfoliomonthly portfoliomonthly) {
        this.portfoliomonthly = portfoliomonthly;
    }

    public void addPortfoliomonthly(){
        if(selectedPortfolio != null && portfoliomonthly.getMonthId() != null){
            portfoliomonthly.setPortfolioid(selectedPortfolio.getId());
            getRoot().getPortfolioDao().insertPortfoliomonthly(portfoliomonthly);
            portfoliomonthly = new Portfoliomonthly();
        }
    }

    private Map<String, List<String>> quarterMonth;

    public List<Month> getQuarterMonth() {
        return Util.getMonths().stream().filter(x -> selectedPortfolio.getQuarter().equals(x.getQuarter()) && !filterQuarterMonth(x.getId())).collect(Collectors.toList());
    }

    public void setQuarterMonth(Map<String, List<String>> quarterMonth) {
        this.quarterMonth = quarterMonth;
    }

    public boolean filterQuarterMonth(Integer monthId){
        return getPortfoliomonthlyByPortfolioId().stream().filter(x-> x.getMonthId().equals(monthId)).findAny().isPresent();
    }

    public boolean filterQuarterSights(Integer sightsId){
        return getPortfoliosightsesByPortfolioId().stream().filter(x-> x.getSights().getId().equals(sightsId)).findAny().isPresent();
    }

    public String getMonthNameById(Integer id){
        return Util.getMonths().get(id-1).getName();
    }

    public void deletePortfoliosightses(Portfoliosights portfoliosights){
        getRoot().getPortfolioDao().deletePortfoliosightses(portfoliosights.getId());
    }

    public void deletePortfoliomonthly(Portfoliomonthly portfoliomonthly){
        getRoot().getPortfolioDao().deletePortfoliomonthly(portfoliomonthly.getId());
    }

    public List<Yearlyinforamtion> getYearlyinforamtions(){
        return getRoot().getPortfolioDao().getYearlyinforamtions();
    }

    public void updatetYearlyinforamtion(){
        getRoot().getPortfolioDao().updateYearlyinforamtion(yearlyinforamtion);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Տվյալները հաջողությամբ պահպանվել են",  "Your message: " ) );
    }

    public Yearlyinforamtion getYearlyinforamtion() {
        if(yearlyinforamtion == null){
            yearlyinforamtion = getYearlyinforamtions().stream().filter(x -> selectedYear.equals(x.getYearId().toString())).findFirst().get();
        }
        return yearlyinforamtion;
    }

    public void setYearlyinforamtion(Yearlyinforamtion yearlyinforamtion) {
        this.yearlyinforamtion = yearlyinforamtion;
    }

    public List<Portfoliocountry> getPortfoliocountries(){
        if(portfoliocountries == null){
            portfoliocountries = getRoot().getPortfolioDao().getPortfolioCountry().stream().filter(x-> selectedPortfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList());
        }
        return portfoliocountries;
    }

    public List<Country> getCountries(){
        if(this.countries == null){
            this.countries =  getRoot().getCountryDao().getAll().stream().filter(x-> !filterPortfoliocountry(x.getId())).sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        }

        return this.countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Country getCountryById(Integer id){
        return getAllCountry().stream().filter(x-> id.equals(x.getId())).findFirst().get();
    }

    public Portfoliocountry getPortfoliocountry() {
        return portfoliocountry;
    }

    public void setPortfoliocountry(Portfoliocountry portfoliocountry) {
        this.portfoliocountry = portfoliocountry;
    }

    public void addPortfoliocountry(){
        if(selectedPortfolio != null && portfoliocountry.getCountryid() != null){
            portfoliocountry.setPortfolioid(selectedPortfolio.getId());
            getRoot().getPortfolioDao().insertPortfoliocountry(portfoliocountry);
            portfoliocountry = new Portfoliocountry();
            portfoliocountries = null;
            countries = null;
        }
    }

    public void deletePortfoliocountry(Portfoliocountry portfoliocountry){
        getRoot().getPortfolioDao().deletePortfoliocountry(portfoliocountry.getId());
        portfoliocountries = null;
        countries = null;
    }

    public boolean filterPortfoliocountry(Integer countryId){
        return getRoot().getPortfolioDao().getPortfolioCountry().stream().filter(x-> x.getPortfolioid().equals(selectedPortfolio.getId()) && countryId.equals(x.getCountryid())).findAny().isPresent();
    }

    public List<Country> getAllCountry(){
        return getRoot().getCountryDao().getAll();
    }

    public void updatePortfoliocountries(RowEditEvent event){
        getRoot().getPortfolioDao().updatePortfoliocountry((Portfoliocountry) event.getObject());
    }

    public void cancel(){
        this.selectedPortfolio = null;
        this.countries = null;
    }

    public Portfoliosights getPortfoliosights() {
        return portfoliosights;
    }

    public void setPortfoliosights(Portfoliosights portfoliosights) {
        this.portfoliosights = portfoliosights;
    }

    public Transportsyear getTransportsyear() {
        return transportsyear;
    }

    public void setTransportsyear(Transportsyear transportsyear) {
        this.transportsyear = transportsyear;
    }

    public List<Transportsyear> getTransportsyears() {
        return this.getRoot().getPortfolioDao().getTransportsyearByYear(getSelectedPortfolio().getId());
    }

    public void setTransportsyears(List<Transportsyear> transportsyears) {
        this.transportsyears = transportsyears;
    }

    public void deleteTransportsyear(Transportsyear transportsyear){
        getRoot().getPortfolioDao().deleteTransportsyear(transportsyear.getId());
    }

    public void addTransportsyear(){
        if(getTransportsyear().getTransportid() != null && getTransportsyear().getCountnumber() != null){
            getTransportsyear().setYearId(getSelectedPortfolio().getId());
            getRoot().getPortfolioDao().insertTransportsyear(getTransportsyear());
            this.transportsyear = new Transportsyear();
        }
    }

    public boolean filterTransportsyears(Integer transportId){
        return getTransportsyears().stream().filter(x-> x.getTransportid().equals(transportId)).findAny().isPresent();
    }

    public String getTransportNameById(Integer id){
        return getRoot().getPortfolioDao().getTransport().stream().filter(x-> x.getId().equals(id)).findFirst().get().getName();
    }

    public List<Portfoliohotels> getPortfoliohotelses() {
        return getRoot().getPortfolioDao().getPortfoliohotelsByPortfolioId(selectedPortfolio.getId());
    }

    public void setPortfoliohotelses(List<Portfoliohotels> portfoliohotelses) {
        this.portfoliohotelses = portfoliohotelses;
    }

    public Portfoliohotels getPortfoliohotels() {
        return portfoliohotels;
    }

    public void setPortfoliohotels(Portfoliohotels portfoliohotels) {
        this.portfoliohotels = portfoliohotels;
    }

    public void deletePortfoliohotel(Portfoliohotels portfoliohotels){
        getRoot().getPortfolioDao().deletePortfoliohotels(portfoliohotels.getId());
    }

    public void addPortfoliohotel(){
        if(getPortfoliohotels().getHotelid() != null && getPortfoliohotels().getTotaltouristcount() != null && getPortfoliohotels().getFinance() != null){
            getPortfoliohotels().setPortfolioid(selectedPortfolio.getId());
            getRoot().getPortfolioDao().insertPortfoliohotels(getPortfoliohotels());
            this.portfoliohotels = new Portfoliohotels();
        }
    }

    public String getHotelNameById(Integer id){
        return getRoot().getHotelDao().getById(id).getName();
    }

    public List<Hotel> getHotels() {
        return getRoot().getHotelDao().getAll().stream().filter(x-> !filterHotels(x.getId())).sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public boolean filterHotels(Integer hotelId){
        return getPortfoliohotelses().stream().filter(x-> x.getHotelid().equals(hotelId)).findAny().isPresent();
    }

    public BigDecimal getProgramsTotalByProgramNumber(Integer number){
        switch (number){
            case 1:
                return getPortfolios().stream().map(Portfolio::getProgram1).reduce(BigDecimal::add).get();
            case 2:
                return getPortfolios().stream().map(Portfolio::getProgram2).reduce(BigDecimal::add).get();
            case 3:
                return getPortfolios().stream().map(Portfolio::getProgram3).reduce(BigDecimal::add).get();
            case 4:
                return getPortfolios().stream().map(Portfolio::getProgram4).reduce(BigDecimal::add).get();
            case 5:
                return getPortfolios().stream().map(Portfolio::getProgram5).reduce(BigDecimal::add).get();
            case 6:
                return getPortfolios().stream().map(Portfolio::getProgram6).reduce(BigDecimal::add).get();
        }
        return BigDecimal.ZERO;
    }

    public String getTotalSpent() {
        totalSpent = BigDecimal.ZERO;
        for(int i=1;i<7;i++){
            totalSpent = totalSpent.add(getProgramsTotalByProgramNumber(i));
        }
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,##0.00");
        return  df.format(totalSpent);
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getTotalBalance() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,##0.00");
        return  df.format(getYearlyinforamtion().getTotalBigDecimal().subtract(totalSpent));
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getTotalFinances() {
        BigDecimal totalFinances = BigDecimal.ZERO;
        for(Portfolio portfolio: getPortfolios()){
            totalFinances = totalFinances.add(portfolio.getTotalFinanceArm()).add(portfolio.getTotalFinanceForeign());
        }
        return yearlyinforamtion.getGdp() != null ? totalFinances.divide(yearlyinforamtion.getGdp(), 4,  RoundingMode.CEILING).multiply(new BigDecimal(100)): new BigDecimal(0);
    }
}
