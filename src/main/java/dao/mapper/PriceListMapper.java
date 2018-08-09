package dao.mapper;

import Models.PriceList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
public class PriceListMapper implements RowMapper<PriceList> {
    @Override
    public PriceList mapRow(ResultSet rs, int rowNum) throws SQLException {
        PriceList priceList = new PriceList();
        priceList.setId(rs.getInt("id"));
        priceList.setName(rs.getString("name"));
        priceList.setPrice(rs.getDouble("price"));
        priceList.setFormula(rs.getString("formula"));
        return priceList;
    }
}
