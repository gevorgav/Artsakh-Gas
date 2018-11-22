package dao.mapper;

import Models.PaymentReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 11/18/2018.
 */
public class PaymentReportMapper  implements RowMapper<PaymentReport> {
	@Override
	public PaymentReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentReport paymentReport = new PaymentReport();
		paymentReport.setRegionId(rs.getInt("regionId"));
		paymentReport.setBankId(rs.getInt("bankId"));
		paymentReport.setPay(rs.getBigDecimal("pay"));
		return paymentReport;
	}
}
