package dashboard;

import Core.Models.Country;
import Core.Root;
import Core.Util;
import portfolio.Portfolio;
import portfolio.Portfoliocountry;
import portfolio.Yearlyinforamtion;

import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class DashboardForm {

    public DashboardForm() {
    }

    private Root root;
    private List<Portfoliocountry> topTenPortfolioCountries;
    private LocalDate currentDate = LocalDate.now();
    private List<Portfolio> portfolios;
    private Map<Integer, Double> currentYearPortfoliomonthlies;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
        getCurrentYearPortfoliomonthlies();
    }

    public List<Portfoliocountry> getTopTenPortfolioCountries() {
        if (topTenPortfolioCountries == null) {
            topTenPortfolioCountries = getRoot().getPortfolioDao().getPortfolioCountry().stream().filter(x -> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList());
        }
        return topTenPortfolioCountries;
    }

    public void setTopTenPortfolioCountries(List<Portfoliocountry> topTenPortfolioCountries) {
        this.topTenPortfolioCountries = topTenPortfolioCountries;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public List<Portfolio> getPortfolios() {
        if (this.portfolios == null) {
            this.portfolios = getRoot().getPortfolioDao().getAll().stream().filter(x -> x.getYear().equals(currentDate.getYear())).collect(Collectors.toList());
        }
        return this.portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public boolean filterPortfolioById(Integer id) {
        return getPortfolios().stream().filter(x -> x.getId().equals(id)).findAny().isPresent();
    }

    public Country getCountryById(Integer id) {
        return getRoot().getCountryDao().getAll().stream().filter(x -> id.equals(x.getId())).findFirst().get();
    }

    private Map<Integer, Integer> topTenPortfolioCountriesCalculated;

    public Map<Integer, Integer> getTopTenPortfolioCountriesCalculated() {
        return getTopTenPortfolioCountries().
                stream().
                collect(Collectors.groupingBy(Portfoliocountry::getCountryid, Collectors.summingInt(Portfoliocountry::getCount))).
                entrySet().
                stream().
                limit(10).
                sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
    public Integer getCountriesCount() {
        return getTopTenPortfolioCountries().
                stream().
                collect(Collectors.groupingBy(Portfoliocountry::getCountryid, Collectors.summingInt(Portfoliocountry::getCount))).
                size();
    }

    public void setTopTenPortfolioCountriesCalculated(Map<Integer, Integer> topTenPortfolioCountriesCalculated) {
        this.topTenPortfolioCountriesCalculated = topTenPortfolioCountriesCalculated;
    }

    public Map<Integer, Double> getCurrentYearPortfoliomonthlies() {
        Map<Integer, Double> doubleList = Util.initMap();
        getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList()).forEach(portfoliomonthly -> doubleList.put(portfoliomonthly.getMonthId(), portfoliomonthly.getFinances().doubleValue()));
        return doubleList;
    }

    public void setCurrentYearPortfoliomonthlies(Map<Integer, Double> currentYearPortfoliomonthlies) {
        this.currentYearPortfoliomonthlies = currentYearPortfoliomonthlies;
    }

    public String getCurrentYearPortfoliomonthliesString() {
        String s = "[";
        for (Integer doubleMap : getCurrentYearPortfoliomonthlies().keySet()) {
            s = s + "[" + doubleMap + ", " + getCurrentYearPortfoliomonthlies().get(doubleMap) + "],";
        }
        s = s + "],";
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return s;
    }


    public String getFinaniceChartData() {
        return "\n" +
                "        [\n" +
                "          {\n" +
                "            data: " + getCurrentYearPortfoliomonthliesString() + "\n" +
                "            points: { show: true, radius: 5},\n" +
                "            splines: { show: true, tension: 0.45, lineWidth: 4, fill: 0.1}\n" +
                "          },\n" +
                "          { data: " + getCurrentYearPortfoliomonthliesString() + "\n" +
                "            bars: { show: true, barWidth: 0.05, align: 'center', lineWidth: 0, fillColor: { colors: [{ opacity: 0.1 }, { opacity: 0.1}] } }\n" +
                "          }\n" +
                "        ],\n" +
                "        {\n" +
                "          colors: ['#0cc2aa','#0cc2aa'],\n" +
                "          series: { shadowSize: 3 },\n" +
                "          xaxis: { show: true, font: { color: '#0cc2aa' }, position: 'bottom' },\n" +
                "          yaxis:{ show: false, font: { color: '#ccc' }},\n" +
                "          grid: { hoverable: true, clickable: true, borderWidth: 0, color: 'transparent' },\n" +
                "          tooltip: true,\n" +
                "          tooltipOpts: { content: '%x.0 is %y.2',  defaultTheme: false, shifts: { x: 0, y: -40 } }\n" +
                "        }\n" +
                "      ";
    }

    public String getPeopleByGender() {
        return "{\n" +
                "  tooltip : {\n" +
                "      trigger: 'axis'\n" +
                "  },\n" +
                "  legend: {\n" +
                "      data:['Տղամարդ','Կին']\n" +
                "  },\n" +
                "  calculable : false,\n" +
                "  xAxis : [\n" +
                "      {\n" +
                "          type : 'category',\n" +
                "          data : ['Քառորդ 1','Քառորդ 2','Քառորդ 3','Քառորդ 4']\n" +
                "      }\n" +
                "  ],\n" +
                "  yAxis : [\n" +
                "      {\n" +
                "          type : 'value'\n" +
                "      }\n" +
                "  ],\n" +
                "  series : [\n" +
                "      {\n" +
                "          name:'Տղամարդ',\n" +
                "          type:'bar',\n" +
                "          data:" + getPeopleByGenderMale() + "\n" +
                "      },\n" +
                "      {\n" +
                "          name:'Կին',\n" +
                "          type:'bar',\n" +
                "          data:" + getPeopleByGenderFmale() + "\n" +
                "      }\n" +
                "  ]\n" +
                "}";
    }

    public String getPeopleByGenderFmale() {
        String s = "[";
        for (Portfolio portfolio : getPortfolios()) {
            s = s + portfolio.getIcfemalecount().toString() + ",";
        }
        return s + "],";
    }

    public String getPeopleByGenderMale() {
        String s = "[";
        for (Portfolio portfolio : getPortfolios()) {
            s = s + portfolio.getIcmalecount().toString() + ",";
        }
        return s + "],";
    }

    public void resetData() {
        this.portfolios = null;
        this.topTenPortfolioCountries = null;
    }

    public Map<Integer, Integer> getCurrentYearMonthliesOfTotal() {
        Map<Integer, Integer> doubleList = Util.initMapForTurCount();
        getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList()).forEach(portfoliomonthly -> doubleList.put(portfoliomonthly.getMonthId(), portfoliomonthly.getTotaltouristcount()));
        return doubleList;
    }

    public String getCurrentYearPortfolioTonthliesCountString() {
        String s = "[";
        for (Integer doubleMap : getCurrentYearMonthliesOfTotal().keySet()) {
            s = s + "[" + doubleMap + ", " + getCurrentYearMonthliesOfTotal().get(doubleMap) + "],";
        }
        return s + "],";
    }

    public String getTotalTuristChartData() {
        return "\n" +
                "        [\n" +
                "          {\n" +
                "            data: " + getCurrentYearPortfolioTonthliesCountString() + "\n" +
                "            points: { show: true, radius: 5},\n" +
                "            splines: { show: true, tension: 0.45, lineWidth: 4, fill: 0.1}\n" +
                "          },\n" +
                "          { data: " + getCurrentYearPortfolioTonthliesCountString() + "\n" +
                "            bars: { show: true, barWidth: 0.05, align: 'center', lineWidth: 0, fillColor: { colors: [{ opacity: 0.1 }, { opacity: 0.1}] } }\n" +
                "          }\n" +
                "        ],\n" +
                "        {\n" +
                "          colors: ['#0cc2aa','#0cc2aa'],\n" +
                "          series: { shadowSize: 3 },\n" +
                "          xaxis: { show: true, font: { color: '#0cc2aa' }, position: 'bottom' },\n" +
                "          yaxis:{ show: false, font: { color: '#ccc' }},\n" +
                "          grid: { hoverable: true, clickable: true, borderWidth: 0, color: 'transparent' },\n" +
                "          tooltip: true,\n" +
                "          tooltipOpts: { content: '%x.0 is %y.2',  defaultTheme: false, shifts: { x: 0, y: -40 } }\n" +
                "        }\n" +
                "      ";
    }

    public BigDecimal getGdpByYear(){
        return getRoot().getPortfolioDao().getYearlyinforamtions().stream().filter(x-> x.getYearId() == getCurrentDate().getYear()).findFirst().get().getGdp();
    }

    public BigDecimal getFinanceByYear(){
        BigDecimal result = BigDecimal.ZERO;
        for(Portfolio portfolio: getPortfolios()){
            result = result.add(portfolio.getFinances() == null ? BigDecimal.ZERO : portfolio.getFinances());
        }
        return result;
    }

    public Integer getCountTouristByYear(){
        Integer result = 0;
        for(Portfolio portfolio: getPortfolios()){
            result += portfolio.getTotaltouristcount();
        }
        return result;
    }

    public boolean isAdmin() {
        InetAddress thisIp = null;
        try {
            thisIp = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        String url = "";
        InputStream input = getClass().getClassLoader().getResourceAsStream("access.properties");
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Util.logMessage(String.valueOf(thisIp.getHostAddress()));
//        return prop.getProperty(String.valueOf(thisIp.getHostAddress())) != null ? true : false;
        return true;
    }

    public Yearlyinforamtion getYearlyinforamtion (){
        return getRoot().getPortfolioDao().getYearlyinforamtions().stream().filter(x-> x.getYearId().equals(currentDate.getYear())).findFirst().get();
    }
}
