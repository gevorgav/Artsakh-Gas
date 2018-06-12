package home;

import Core.Root;
import portfolio.Portfolio;
import portfolio.Portfoliocountry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gev on 15.03.2017.
 */
public class HomeForm {
    private Root root;
    private String selectedYear;
    private List<Portfolio> portfolios ;
    private List<Core.Models.Country> countres ;
    private List<Country> countresWithCount = new ArrayList<>() ;
    private List<Portfoliocountry> portfoliocountries;
    private String json;

    private Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public String getJson() {
        if (this.json==null){
            this.initJson();
        }
        return json;
    }

    public void setJson(String json) {
        if (json==null){
            this.setPortfoliocountries(null);
            this.setPortfolios(null);
            setCountresWithCount(null);

        }
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

    public List<Country> getCountresWithCount() {
        if(this.countresWithCount==null){
            initCountryWithCountList();
        }
        return countresWithCount;
    }

    public void setCountresWithCount(List<Country> countresWithCount) {
        this.countresWithCount = countresWithCount;
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

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public List<Portfolio> getPortfolios() {
        if (this.portfolios == null) {
            getSelectedYear();
            this.portfolios = getRoot().getPortfolioDao().getAll().stream().
                    filter(x -> selectedYear.equals(x.getYear().toString())).
                    collect(Collectors.toList());
        }
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public String getSelectedYear() {
        if(selectedYear == null || selectedYear == ""){
            selectedYear = String.valueOf(new Date().getYear() + 1900);
            changeYear();
        }
        return selectedYear;
    }

    public void changeYear(){
        initCountryWithCountList();
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

    public void initJson(){

        this.json= "{\n" +
        "tooltip : {\n" +
                "    trigger: 'item',\n" +
                "   formatter : function (params) {\n" +
                "var value = (params.value + '').split('.'); value = value[0].replace(/(d{1,3})(?=(?:d{3})+(?!d))/g, '$1,')\n"+
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
                "data:[\n" ;
        String country="";
        for (Country countresWithCount : this.getCountresWithCount()) {
            country += "{name : '" + countresWithCount.getName_ENG() + "',hname: '" + countresWithCount.getName() + "', value : " + countresWithCount.getCount() + "},\n" ;
        }
        country = country.substring(0, country.length()-2) + "\n";
        this.json=json+country +"]\n" +
                "                  }\n" +
                "              ]\n" +
                "            }";


    }

    public void resetData(){
        this.portfolios = null;
        this.countresWithCount = null;
        this.portfoliocountries = null;
        this.json = null;
    }
}
