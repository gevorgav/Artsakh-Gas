package portfolio;

import Core.Interface.Dao;
import Models.Sights;
import Models.Transport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioDao implements Dao<Portfolio>{
    @Override
    public List<Portfolio> getAll() {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM Portfolio";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while ( rs.next() ) {
                Portfolio portfolio = new Portfolio();
                portfolio.setId(rs.getInt("id"));
                portfolio.setQuarter(rs.getInt("quarter"));
                portfolio.setTotaltouristcount(rs.getInt("totalTouristCount"));
                portfolio.setArmtouristcount(rs.getInt("armTouristCount"));
                portfolio.setOthertouristcount(rs.getInt("otherTouristCount"));
                portfolio.setFinances(rs.getBigDecimal("finances"));
                portfolio.setIctouristcount(rs.getInt("icTouristCount"));
                portfolio.setIcmalecount(rs.getInt("icMaleCount"));
                portfolio.setIcfemalecount(rs.getInt("icFemaleCount"));
                portfolio.setIcvisitdecription(rs.getString("icVisitDecription"));
                portfolio.setSocialpackagecount(rs.getInt("socialPackageCount"));
                portfolio.setColumn_12(rs.getInt("column_12"));
                portfolio.setIstouroperator(rs.getInt("isTourOperator"));
                portfolio.setAge15(rs.getInt("age15"));
                portfolio.setAge30(rs.getInt("age30"));
                portfolio.setAge50(rs.getInt("age50"));
                portfolio.setAge51(rs.getInt("age51"));
                portfolio.setYear(rs.getInt("year"));
                portfolio.setProgram1(rs.getBigDecimal("program1"));
                portfolio.setProgram2(rs.getBigDecimal("program2"));
                portfolio.setProgram3(rs.getBigDecimal("program3"));
                portfolio.setProgram4(rs.getBigDecimal("program4"));
                portfolio.setProgram5(rs.getBigDecimal("program5"));
                portfolio.setProgram6(rs.getBigDecimal("program6"));
                portfolio.setFinanceArm(rs.getBigDecimal("financeArm"));
                portfolio.setFinanceForeign(rs.getBigDecimal("financeForeign"));
                portfolio.setTotalFinanceArm(rs.getBigDecimal("totalFinanceArm"));
                portfolio.setTotalFinanceForeign(rs.getBigDecimal("totalFinanceForeign"));
                portfolio.setIcStepanakert(rs.getInt("icStepanakert"));
                portfolio.setIcShushi(rs.getInt("icShushi"));
                portfolio.setIcTigranakert(rs.getInt("icTigranakert"));
                portfolio.setIcTsaxkashat(rs.getInt("icTsaxkashat"));
                portfolio.setProvidedProgram1(rs.getBigDecimal("providedProgram1"));
                portfolio.setProvidedProgram2(rs.getBigDecimal("providedProgram2"));
                portfolio.setProvidedProgram3(rs.getBigDecimal("providedProgram3"));
                portfolio.setProvidedProgram4(rs.getBigDecimal("providedProgram4"));
                portfolio.setProvidedProgram5(rs.getBigDecimal("providedProgram5"));
                portfolio.setProvidedProgram6(rs.getBigDecimal("providedProgram6"));

                portfolios.add(portfolio);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return portfolios;
    }

    @Override
    public Portfolio getById(Integer id) {
        String sql = "SELECT * FROM Portfolio WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Portfolio portfolio = new Portfolio();
                portfolio.setId(rs.getInt("id"));
                portfolio.setQuarter(rs.getInt("quarter"));
                portfolio.setTotaltouristcount(rs.getInt("totalTouristCount"));
                portfolio.setArmtouristcount(rs.getInt("armTouristCount"));
                portfolio.setOthertouristcount(rs.getInt("otherTouristCount"));
                portfolio.setFinances(rs.getBigDecimal("finances"));
                portfolio.setIctouristcount(rs.getInt("icTouristCount"));
                portfolio.setIcmalecount(rs.getInt("icMaleCount"));
                portfolio.setIcfemalecount(rs.getInt("icFemaleCount"));
                portfolio.setIcvisitdecription(rs.getString("icVisitDecription"));
                portfolio.setSocialpackagecount(rs.getInt("socialPackageCount"));
                portfolio.setColumn_12(rs.getInt("column_12"));
                portfolio.setIstouroperator(rs.getInt("isTourOperator"));
                portfolio.setAge15(rs.getInt("age15"));
                portfolio.setAge30(rs.getInt("age30"));
                portfolio.setAge50(rs.getInt("age50"));
                portfolio.setAge51(rs.getInt("age51"));
                portfolio.setYear(rs.getInt("year"));
                portfolio.setProgram1(rs.getBigDecimal("program1"));
                portfolio.setProgram2(rs.getBigDecimal("program2"));
                portfolio.setProgram3(rs.getBigDecimal("program3"));
                portfolio.setProgram4(rs.getBigDecimal("program4"));
                portfolio.setProgram5(rs.getBigDecimal("program5"));
                portfolio.setProgram6(rs.getBigDecimal("program6"));
                portfolio.setFinanceArm(rs.getBigDecimal("financeArm"));
                portfolio.setFinanceForeign(rs.getBigDecimal("financeForeign"));
                portfolio.setTotalFinanceArm(rs.getBigDecimal("totalFinanceArm"));
                portfolio.setTotalFinanceForeign(rs.getBigDecimal("totalFinanceForeign"));
                portfolio.setIcStepanakert(rs.getInt("icStepanakert"));
                portfolio.setIcShushi(rs.getInt("icShushi"));
                portfolio.setIcTigranakert(rs.getInt("icTigranakert"));
                portfolio.setIcTsaxkashat(rs.getInt("icTsaxkashat"));
                portfolio.setProvidedProgram1(rs.getBigDecimal("providedProgram1"));
                portfolio.setProvidedProgram2(rs.getBigDecimal("providedProgram2"));
                portfolio.setProvidedProgram3(rs.getBigDecimal("providedProgram3"));
                portfolio.setProvidedProgram4(rs.getBigDecimal("providedProgram4"));
                portfolio.setProvidedProgram5(rs.getBigDecimal("providedProgram5"));
                portfolio.setProvidedProgram6(rs.getBigDecimal("providedProgram6"));

                return portfolio;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insert(Portfolio item) {
        String sql = "INSERT INTO Portfolio(quarter,totaltouristcount,armtouristcount,othertouristcount,finances,ictouristcount,icmalecount," +
                "icfemalecount,icvisitdecription,socialpackagecount,column_12,istouroperator,age15,age30,age50,age51,year,program1,program2,program3,program4,program5,program6,financeArm,financeForeign,totalFinanceArm,totalFinanceForeign,icStepanakert,icShushi,icTigranakert,icTsaxkashat,providedProgram1,providedProgram2,providedProgram3,providedProgram4,providedProgram5,providedProgram6) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getQuarter());
            pstmt.setInt(2, item.getTotaltouristcount());
            pstmt.setInt(3, item.getArmtouristcount());
            pstmt.setInt(4, item.getOthertouristcount());
            pstmt.setBigDecimal(5, item.getFinances());
            pstmt.setInt(6, item.getIctouristcount());
            pstmt.setInt(7, item.getIcmalecount());
            pstmt.setInt(8, item.getIcfemalecount());
            pstmt.setString(9, item.getIcvisitdecription());
            pstmt.setInt(10, item.getSocialpackagecount());
            pstmt.setInt(11, item.getColumn_12());
            pstmt.setInt(12, item.getIstouroperator());
            pstmt.setInt(13, item.getAge15());
            pstmt.setInt(14, item.getAge30());
            pstmt.setInt(15, item.getAge50());
            pstmt.setInt(16, item.getAge51());
            pstmt.setInt(17, item.getYear());
            pstmt.setBigDecimal(18, item.getProgram1());
            pstmt.setBigDecimal(19, item.getProgram2());
            pstmt.setBigDecimal(20, item.getProgram3());
            pstmt.setBigDecimal(21, item.getProgram4());
            pstmt.setBigDecimal(22, item.getProgram5());
            pstmt.setBigDecimal(23, item.getProgram6());
            pstmt.setBigDecimal(24, item.getFinanceArm());
            pstmt.setBigDecimal(25, item.getFinanceForeign());
            pstmt.setBigDecimal(26, item.getTotalFinanceArm());
            pstmt.setBigDecimal(27, item.getTotalFinanceForeign());
            pstmt.setInt(28, item.getIcStepanakert());
            pstmt.setInt(29, item.getIcShushi());
            pstmt.setInt(30, item.getIcTigranakert());
            pstmt.setInt(31, item.getIcTsaxkashat());
            pstmt.setBigDecimal(32, item.getProvidedProgram1());
            pstmt.setBigDecimal(33, item.getProvidedProgram2());
            pstmt.setBigDecimal(34, item.getProvidedProgram3());
            pstmt.setBigDecimal(35, item.getProvidedProgram4());
            pstmt.setBigDecimal(36, item.getProvidedProgram5());
            pstmt.setBigDecimal(37, item.getProvidedProgram6());


            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Portfolio item) {
        String sql = "UPDATE Portfolio SET quarter = ?, totaltouristcount = ?, armtouristcount = ?, othertouristcount = ?, finances = ?," +
                " ictouristcount = ?, icmalecount = ?, icfemalecount = ?, icvisitdecription = ?, socialpackagecount = ?, column_12 = ?," +
                " istouroperator = ?, age15=?,age30=?,age50=?,age51=?,year=?, program1=?, program2 =?, program3 =?, program4 =? , program5 =?, program6 =?, financeArm =?, financeForeign =?, totalFinanceArm =?, totalFinanceForeign =?, icStepanakert =? ,icShushi =?, icTigranakert =?, icTsaxkashat =?,providedProgram1 =?, providedProgram2 = ?, providedProgram3 = ?, providedProgram4 = ?,providedProgram5 = ?, providedProgram =? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getQuarter());
            pstmt.setInt(2, item.getTotaltouristcount());
            pstmt.setInt(3, item.getArmtouristcount());
            pstmt.setInt(4, item.getOthertouristcount());
            pstmt.setBigDecimal(5, item.getFinances());
            pstmt.setInt(6, item.getIctouristcount());
            pstmt.setInt(7, item.getIcmalecount());
            pstmt.setInt(8, item.getIcfemalecount());
            pstmt.setString(9, item.getIcvisitdecription());
            pstmt.setInt(10, item.getSocialpackagecount());
            pstmt.setInt(11, item.getColumn_12());
            pstmt.setInt(12, item.getIstouroperator());
            pstmt.setInt(13, item.getAge15());
            pstmt.setInt(14, item.getAge30());
            pstmt.setInt(15, item.getAge50());
            pstmt.setInt(16, item.getAge51());
            pstmt.setInt(17, item.getYear());
            pstmt.setBigDecimal(18, item.getProgram1());
            pstmt.setBigDecimal(19, item.getProgram2());
            pstmt.setBigDecimal(20, item.getProgram3());
            pstmt.setBigDecimal(21, item.getProgram4());
            pstmt.setBigDecimal(22, item.getProgram5());
            pstmt.setBigDecimal(23, item.getProgram6());
            pstmt.setBigDecimal(24, item.getFinanceArm());
            pstmt.setBigDecimal(25, item.getFinanceForeign());
            pstmt.setBigDecimal(26, item.getTotalFinanceArm());
            pstmt.setBigDecimal(27, item.getTotalFinanceForeign());
            pstmt.setInt(28, item.getIcStepanakert());
            pstmt.setInt(29, item.getIcShushi());
            pstmt.setInt(30, item.getIcTigranakert());
            pstmt.setInt(31, item.getIcTsaxkashat());
            pstmt.setBigDecimal(32, item.getProvidedProgram1());
            pstmt.setBigDecimal(33, item.getProvidedProgram2());
            pstmt.setBigDecimal(34, item.getProvidedProgram3());
            pstmt.setBigDecimal(35, item.getProvidedProgram4());
            pstmt.setBigDecimal(36, item.getProvidedProgram5());
            pstmt.setBigDecimal(37, item.getProvidedProgram6());
            pstmt.setInt(38, item.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public List<Portfoliocountry> getPortfolioCountry(){

        List<Portfoliocountry> portfoliocountries = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioCountry";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliocountry portfoliocountry = new Portfoliocountry();
                portfoliocountry.setId(res.getInt("id"));
                portfoliocountry.setCountryid(res.getInt("countryId"));
                portfoliocountry.setPortfolioid(res.getInt("portfolioId"));
                portfoliocountry.setCount(res.getInt("countNumber"));
                portfoliocountries.add(portfoliocountry);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliocountries;
    }

    public List<Portfoliomonthly> getPortfolioMonthly(){

        List<Portfoliomonthly> portfoliomonthlies = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioMonthly";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliomonthly portfoliomonthly = new Portfoliomonthly();
                portfoliomonthly.setId(res.getInt("id"));
                portfoliomonthly.setPortfolioid(res.getInt("portfolioId"));
                portfoliomonthly.setTotaltouristcount(res.getInt("totalTouristCount"));
                portfoliomonthly.setArmtouristcount(res.getInt("armTouristCount"));
                portfoliomonthly.setOthertouristcount(res.getInt("otherTouristCount"));
                portfoliomonthly.setFinances(res.getBigDecimal("finances"));
                portfoliomonthly.setMonthId(res.getInt("monthId"));
                portfoliomonthlies.add(portfoliomonthly);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliomonthlies;
    }

    public List<Portfoliosights> getPortfolioSights(){

        List<Portfoliosights> portfoliosightses = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioSights";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliosights portfoliosights = new Portfoliosights();
                portfoliosights.setId(res.getInt("id"));
                portfoliosights.setPortfolioid(res.getInt("portfolioId"));
                portfoliosights.setStightsid(res.getInt("sightsId"));
                portfoliosights.setCount(res.getInt("countNumber"));
                portfoliosightses.add(portfoliosights);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliosightses;
    }

    public List<Transport> getTransport(){

        List<Transport> transports = new ArrayList<>();
        String sql = "SELECT * FROM Transport";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Transport transport = new Transport();
                transport.setId(res.getInt("id"));
                transport.setName(res.getString("name"));
                transports.add(transport);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return transports;
    }

    public List<Sights> getSightses(){

        List<Sights> sightses = new ArrayList<>();
        String sql = "SELECT * FROM Sights";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Sights sights = new Sights();
                sights.setId(res.getInt("id"));
                sights.setName(res.getString("name"));
                sightses.add(sights);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return sightses;
    }

    public boolean insertPortfoliosights(Integer portfolioId, Integer sightsId, Integer count) {
        String sql = "INSERT INTO PortfolioSights(portfolioId,sightsId, countNumber) VALUES (?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfolioId);
            pstmt.setInt(2, sightsId);
            pstmt.setInt(3, count);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updatePortfoliosights(Portfoliosights item) {
        String sql = "UPDATE PortfolioSights SET portfolioId = ?, sightsId = ?, countNumber = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getPortfolioid());
            pstmt.setInt(2, item.getStightsid());
            pstmt.setInt(3, item.getCount());
            pstmt.setInt(4, item.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Portfoliosights> getPortfoliosightsByPortfolioId(Integer id){

        String sql = "SELECT * FROM PortfolioSights WHERE portfolioId = ?";
        ResultSet rs = null;
        List<Portfoliosights> portfoliosightses = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Portfoliosights portfoliosights = new Portfoliosights();
                portfoliosights.setId(rs.getInt("id"));
                portfoliosights.setPortfolioid(rs.getInt("portfolioId"));
                portfoliosights.setStightsid(rs.getInt("sightsId"));
                portfoliosights.setCount(rs.getInt("countNumber"));
                portfoliosightses.add(portfoliosights);
            }
            return portfoliosightses;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean insertPortfoliomonthly(Portfoliomonthly portfoliomonthly) {
        String sql = "INSERT INTO PortfolioMonthly(portfolioId,totalTouristCount,finances, monthId) VALUES (?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfoliomonthly.getPortfolioid());
            pstmt.setInt(2, portfoliomonthly.getTotaltouristcount());
            pstmt.setBigDecimal(3, portfoliomonthly.getFinances());
            pstmt.setInt(4,portfoliomonthly.getMonthId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deletePortfoliosightses(Integer id){
        String sql = "DELETE FROM PortfolioSights WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deletePortfoliomonthly(Integer id){
        String sql = "DELETE FROM PortfolioMonthly WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Yearlyinforamtion> getYearlyinforamtions(){
        List<Yearlyinforamtion> yearlyinforamtions = new ArrayList<>();
        String sql = "SELECT * FROM YearlyInforamtion";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Yearlyinforamtion yearlyinforamtion = new Yearlyinforamtion();
                yearlyinforamtion.setId(res.getInt("id"));
                yearlyinforamtion.setGdp(res.getBigDecimal("GDP"));
                yearlyinforamtion.setOvernightduration(res.getInt("overnightDuration"));
                yearlyinforamtion.setAncaket1(res.getInt("ancaket1"));
                yearlyinforamtion.setAncaket2(res.getInt("ancaket2"));
                yearlyinforamtion.setAncaket3(res.getInt("ancaket3"));
                yearlyinforamtion.setAmcaket4(res.getInt("amcaket4"));
                yearlyinforamtion.setAncaket5(res.getInt("ancaket5"));
                yearlyinforamtion.setYearId(res.getInt("yearId"));
                yearlyinforamtion.setProgram1(res.getBigDecimal("program1"));
                yearlyinforamtion.setProgram2(res.getBigDecimal("program2"));
                yearlyinforamtion.setProgram3(res.getBigDecimal("program3"));
                yearlyinforamtion.setProgram4(res.getBigDecimal("program4"));
                yearlyinforamtion.setProgram5(res.getBigDecimal("program5"));
                yearlyinforamtion.setProgram6(res.getBigDecimal("program6"));
                yearlyinforamtions.add(yearlyinforamtion);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return yearlyinforamtions;
    }

    public boolean updateYearlyinforamtion(Yearlyinforamtion yearlyinforamtion) {
        String sql = "UPDATE YearlyInforamtion SET GDP = ?, overnightDuration = ?, ancaket1 =? ,ancaket2 =?, ancaket3 =?, amcaket4 =? , ancaket5 = ?, yearId =?, program1 =?, program2 =?, program3 =?, program4 =?, program5 =?, program6 =? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, yearlyinforamtion.getGdp());
            pstmt.setInt(2, yearlyinforamtion.getOvernightduration());
            pstmt.setInt(3, yearlyinforamtion.getAncaket1());
            pstmt.setInt(4, yearlyinforamtion.getAncaket2());
            pstmt.setInt(5, yearlyinforamtion.getAncaket3());
            pstmt.setInt(6, yearlyinforamtion.getAmcaket4());
            pstmt.setInt(7, yearlyinforamtion.getAncaket5());
            pstmt.setInt(8,yearlyinforamtion.getYearId());
            pstmt.setBigDecimal(9, yearlyinforamtion.getProgram1());
            pstmt.setBigDecimal(10, yearlyinforamtion.getProgram2());
            pstmt.setBigDecimal(11, yearlyinforamtion.getProgram3());
            pstmt.setBigDecimal(12, yearlyinforamtion.getProgram4());
            pstmt.setBigDecimal(13, yearlyinforamtion.getProgram5());
            pstmt.setBigDecimal(14, yearlyinforamtion.getProgram6());
            pstmt.setInt(15, yearlyinforamtion.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updatePortfoliocountry(Portfoliocountry portfoliocountry) {
        String sql = "UPDATE PortfolioCountry SET countryId = ?, portfolioId = ?, countNumber =? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfoliocountry.getCountryid());
            pstmt.setInt(2, portfoliocountry.getPortfolioid());
            pstmt.setInt(3, portfoliocountry.getCount());
            pstmt.setInt(4, portfoliocountry.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insertPortfoliocountry(Portfoliocountry portfoliocountry) {
        String sql = "INSERT INTO PortfolioCountry(countryId,portfolioId,countNumber) VALUES (?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfoliocountry.getCountryid());
            pstmt.setInt(2, portfoliocountry.getPortfolioid());
            pstmt.setInt(3, portfoliocountry.getCount());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deletePortfoliocountry(Integer id){
        String sql = "DELETE FROM PortfolioCountry WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Transportsyear> getTransportsyearByYear(Integer year){

        String sql = "SELECT * FROM TransportsYear WHERE portfolioId = ?";
        ResultSet rs = null;
        List<Transportsyear> transportsyears = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, year);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Transportsyear transportsyear = new Transportsyear();
                transportsyear.setId(rs.getInt("id"));
                transportsyear.setYearId(rs.getInt("portfolioId"));
                transportsyear.setTransportid(rs.getInt("transportId"));
                transportsyear.setCountnumber(rs.getInt("countNumber"));
                transportsyears.add(transportsyear);
            }
            return transportsyears;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean deleteTransportsyear(Integer id){
        String sql = "DELETE FROM TransportsYear WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insertTransportsyear(Transportsyear transportsyear) {
        String sql = "INSERT INTO TransportsYear(portfolioId,transportId,countNumber) VALUES (?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transportsyear.getYearId());
            pstmt.setInt(2, transportsyear.getTransportid());
            pstmt.setInt(3, transportsyear.getCountnumber());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Portfoliohotels> getPortfoliohotelsByPortfolioId(Integer id){

        String sql = "SELECT * FROM PortfolioHotels WHERE portfolioId = ?";
        ResultSet rs = null;
        List<Portfoliohotels> portfoliohotelses = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Portfoliohotels portfoliohotels = new Portfoliohotels();
                portfoliohotels.setId(rs.getInt("id"));
                portfoliohotels.setPortfolioid(rs.getInt("portfolioId"));
                portfoliohotels.setHotelid(rs.getInt("hotelId"));
                portfoliohotels.setFinance(rs.getDouble("finance"));
                portfoliohotels.setTotaltouristcount(rs.getInt("totalTouristCount"));
                portfoliohotelses.add(portfoliohotels);
            }
            return portfoliohotelses;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Portfoliohotels> getPortfoliohotels(){

        List<Portfoliohotels> portfoliohoteles = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioHotels";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliohotels portfoliohotels = new Portfoliohotels();
                portfoliohotels.setId(res.getInt("id"));
                portfoliohotels.setPortfolioid(res.getInt("portfolioId"));
                portfoliohotels.setHotelid(res.getInt("hotelId"));
                portfoliohotels.setFinance(res.getDouble("finance"));
                portfoliohotels.setTotaltouristcount(res.getInt("totalTouristCount"));
                portfoliohoteles.add(portfoliohotels);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliohoteles;
    }

    public boolean deletePortfoliohotels(Integer id){
        String sql = "DELETE FROM PortfolioHotels WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insertPortfoliohotels(Portfoliohotels portfoliohotels) {
        String sql = "INSERT INTO PortfolioHotels(portfolioId,totalTouristCount,finance, hotelId) VALUES (?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfoliohotels.getPortfolioid());
            pstmt.setInt(2, portfoliohotels.getTotaltouristcount());
            pstmt.setDouble(3, portfoliohotels.getFinance());
            pstmt.setInt(4, portfoliohotels.getHotelid());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }
}
