package analytics;

import Core.Models.Year;
import Core.Root;
import Core.Util;
import home.Country;
import org.primefaces.context.RequestContext;
import portfolio.Portfolio;
import portfolio.Portfoliocountry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gev on 07.04.2017.
 */
public class MapForm {
    private List<Year> years;
    private Root root;
    private String firstYear="2017";
    private String lastYear="2023";
    private String filterYearForTurist = "2017";
    private String filterYearForFinancial = "2017";
    private List<Portfolio> portfolios;
    private List<Portfolio> portfoliosForTurist;
    private List<Portfolio> portfoliosForFinancial;
    private String json;
    private String jsonForTurist;
    private String jsonForFinancial;
    private List<Portfoliocountry> portfoliocountries;
    private List<Country> countresWithCount = new ArrayList<>() ;
    private List<Core.Models.Country> countres ;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
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

    public String getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(String firstYear) {
        this.firstYear = firstYear;
    }

    public String getLastYear() {
        return lastYear;
    }

    public void setLastYear(String lastYear) {
        this.lastYear = lastYear;
    }

    public String getFilterYearForTurist() {
        return filterYearForTurist;
    }

    public void setFilterYearForTurist(String filterYearForTurist) {
        this.filterYearForTurist = filterYearForTurist;
    }

    public String getFilterYearForFinancial() {
        return filterYearForFinancial;
    }

    public void setFilterYearForFinancial(String filterYearForFinancial) {
        this.filterYearForFinancial = filterYearForFinancial;
    }

    public String getJson() {
        if (json == null){
            this.firstYear = "2017";
            this.lastYear = "2023";
            getFilterForMap();
        }
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<Portfoliocountry> getPortfoliocountries() {
        if (this.portfoliocountries==null){
            this.portfoliocountries = getRoot().getPortfolioDao().getPortfolioCountry();
        }
        return portfoliocountries;
    }

    public void setPortfoliocountries(List<Portfoliocountry> portfoliocountries) {
        this.portfoliocountries = portfoliocountries;
    }

    public List<Core.Models.Country> getCountres() {
        if (this.countres == null) {
            this.countres = getRoot().getCountryDao().getAll();
        }
        return countres;
    }

    public void setCountres(List<Core.Models.Country> countres) {
        this.countres = countres;
    }

    public List<Portfolio> getPortfolios() {
        if (this.portfolios == null) {
            this.portfolios = getRoot().getPortfolioDao().getAll().stream().
                    filter(x -> (Integer.valueOf(this.firstYear) <= x.getYear() && x.getYear() <=Integer.valueOf(this.lastYear))).
                    collect(Collectors.toList());
        }
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Country> getCountresWithCount() {
        if(this.countresWithCount==null || this.countresWithCount.size()==0){
            initCountryWithCountList();
        }
        return countresWithCount;
    }

    public void setCountresWithCount(List<Country> countresWithCount) {
        this.countresWithCount = countresWithCount;
    }

    public void initCountryWithCountList(){
        ArrayList<Portfoliocountry> newgetPortfoliocountries = new ArrayList<>();
        for (Portfolio portfolio :this.getPortfolios()){
            newgetPortfoliocountries.addAll(this.getPortfoliocountries().stream().filter(x->portfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList()));
        }
        this.countresWithCount= new ArrayList<>();
        for (Core.Models.Country country : this.getCountres()){

            Integer count = 0;
            for (Portfoliocountry pc : newgetPortfoliocountries){
                if (pc.getCountryid().equals(country.getId())){
                    count+= pc.getCount();
                }
            }
            this.countresWithCount.add(new Country(country.getId(),country.getName(),country.getName_ENG(),count));
        }
    }

    public void getFilterForMap() {
            this.setPortfolios(null);
            initCountryWithCountList();
            this.json = "{\n" +
                    "tooltip : {\n" +
                    "    trigger: 'item',\n" +
                    "   formatter : function (params) {\n" +
                    "var value = (params.value + '').split('.'); value = value[0].replace(/(d{1,3})(?=(?:d{3})+(?!d))/g, '$1,')\n" +
                    "+ '.' + value[1];\n" +
                    "return  params.data.hname + ' - ' + params.value;\n" +
                    "}\n" +
                    "},\n" +
                    "dataRange: {\n" +
                    "min: 0,\n" +
                    "    max: 10000,\n" +
                    "   text:['Շատ','Քիչ'],\n" +
                    "realtime: false,\n" +
                    "   calculable : true,\n" +
                    "   color: ['#0d8878','#61BBA8','#B8DFD7']\n" +
                    "},\n" +
                    "series : [\n" +
                    "{\n" +
                    "name: 'Մարդկանց թիվը',\n" +
                    "    type: 'map',\n" +
                    "mapType: 'world',\n" +
                    "roam: true,\n" +
                    "mapLocation: {\n" +
                    "y : 10\n" +
                    "},\n" +
                    "itemStyle:{\n" +
                    "emphasis:{label:{show:true}}\n" +
                    "},\n" +
                    "data:[\n";
            String country = "";
            for (Country countresWithCount : this.getCountresWithCount()) {
                country += "{name : '" + countresWithCount.getName_ENG() + "',hname: '" + countresWithCount.getName() + "', value : " + countresWithCount.getCount() + "},\n";
            }
            country = country.substring(0, country.length() - 2) + "\n";
            this.json = json + country + "]\n" +
                    "                  }\n" +
                    "              ]\n" +
                    "            }";



        RequestContext.getCurrentInstance().update("table2");
        RequestContext.getCurrentInstance().execute("reload_js('scripts/app.js')");
    }

    //tourist count


    public String getJsonForTourist() {
        if (jsonForTurist == null){
            this.filterYearForTurist = "2017";
            getTotalTuristChartData();
        }
        return jsonForTurist;
    }

    public void setJsonForTurist(String jsonForFinancial) {
        this.jsonForTurist = jsonForTurist;
    }

    public void getTotalTuristChartData() {
        this.setPortfoliosForTurist(null);
        this.jsonForTurist = "\n" +
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
        RequestContext.getCurrentInstance().update("table2");
        RequestContext.getCurrentInstance().execute("reload_js('scripts/app.js')");
    }

    public String getCurrentYearPortfolioTonthliesCountString() {
        String s = "[";
        for (Integer doubleMap : getCurrentYearMonthliesOfTotal().keySet()) {
            s = s + "[" + doubleMap + ", " + getCurrentYearMonthliesOfTotal().get(doubleMap) + "],";
        }
        return s + "],";
    }

    public Map<Integer, Integer> getCurrentYearMonthliesOfTotal() {
        Map<Integer, Integer> doubleList = Util.initMapForTurCount();
        getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> filterPortfolioByIdT(x.getPortfolioid())).collect(Collectors.toList()).forEach(portfoliomonthly -> doubleList.put(portfoliomonthly.getMonthId(), portfoliomonthly.getTotaltouristcount()));
        return doubleList;
    }

    public boolean filterPortfolioByIdT(Integer id) {
        return getPortfoliosForTurist().stream().filter(x -> x.getId().equals(id)).findAny().isPresent();
    }

    public List<Portfolio> getPortfoliosForTurist() {
        if (this.portfoliosForTurist == null) {
            this.portfoliosForTurist = getRoot().getPortfolioDao().getAll().stream().filter(x -> x.getYear().equals(Integer.valueOf(getFilterYearForTurist()))).collect(Collectors.toList());
        }
        return this.portfoliosForTurist;
    }

    public void setPortfoliosForTurist(List<Portfolio> portfoliosForTurist) {
        this.portfoliosForTurist = portfoliosForTurist;
    }

    //financial

    public String getJsonForFinancial() {
        if (jsonForFinancial == null){
            this.filterYearForFinancial = "2017";
            getTotalFinancialChartData();
        }
        return jsonForFinancial;
    }

    public void setJsonForFinancial(String jsonForFinancial) {
        this.jsonForFinancial = jsonForFinancial;
    }

    public void getTotalFinancialChartData() {
        this.setPortfoliosForFinancial(null);
        this.jsonForFinancial = "\n" +
                "        [\n" +
                "          {\n" +
                "            data: " + getCurrentYearPortfolioTonthliesCountStringForFinancial() + "\n" +
                "            points: { show: true, radius: 5},\n" +
                "            splines: { show: true, tension: 0.45, lineWidth: 4, fill: 0.1}\n" +
                "          },\n" +
                "          { data: " + getCurrentYearPortfolioTonthliesCountStringForFinancial() + "\n" +
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
        RequestContext.getCurrentInstance().update("table2");
        RequestContext.getCurrentInstance().execute("reload_js('scripts/app.js')");
    }

    public String getCurrentYearPortfolioTonthliesCountStringForFinancial() {
        String s = "[";
        for (Integer doubleMap : getCurrentYearMonthliesOfTotalForFinancial().keySet()) {
            s = s + "[" + doubleMap + ", " + getCurrentYearMonthliesOfTotalForFinancial().get(doubleMap) + "],";
        }
        return s + "],";
    }

    public Map<Integer, Double> getCurrentYearMonthliesOfTotalForFinancial() {
        Map<Integer, Double> doubleList = Util.initMap();
        getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList()).forEach(portfoliomonthly -> doubleList.put(portfoliomonthly.getMonthId(), portfoliomonthly.getFinances().doubleValue()));
        return doubleList;
    }

    public boolean filterPortfolioById(Integer id) {
        return getPortfoliosForFinancial().stream().filter(x -> x.getId().equals(id)).findAny().isPresent();
    }

    public List<Portfolio> getPortfoliosForFinancial() {
        if (this.portfoliosForFinancial == null) {
            this.portfoliosForFinancial = getRoot().getPortfolioDao().getAll().stream().filter(x -> x.getYear().equals(Integer.valueOf(getFilterYearForFinancial()))).collect(Collectors.toList());
        }
        return this.portfoliosForFinancial;
    }

    public void setPortfoliosForFinancial(List<Portfolio> portfoliosForFinancial) {
        this.portfoliosForFinancial = portfoliosForFinancial;
    }

}
