package portfolio;

import Core.CacheForm;
import Models.Section;
import Models.SubSection;
import dao.ClientDao;
import dao.ClientHistoryDao;
import dao.VisitPlanDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by astghik.mamunc on 8/21/2018.
 */
public class VisitGraficExcelParser {

	private PortfolioForm portfolioForm;

	public VisitGraficExcelParser(PortfolioForm portfolioForm) {
		this.portfolioForm = portfolioForm;
	}

	public void exportVisitGrafic() throws IOException, InvalidFormatException {
		Integer regionId = portfolioForm.getVisitGraficRegionId();
		Integer monthId = portfolioForm.getVisitGraficMonthId();
		ClientDao clientDao = portfolioForm.clientDao;
		VisitPlanDao visitPlanDao = portfolioForm.getCache().visitPlanDao;
		ClientHistoryDao clientHistoryDao = portfolioForm.clientHistoryDao;
		String regionName = portfolioForm.getCache().regionDao.loadById(regionId).getName();

		Integer semiAnnualId = portfolioForm.getVisitGraficSemiAnnualId();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(portfolioForm.getTemaplate2Url());
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));

		// Get Sheet at index 0
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Get Row at index 1
		Row row;

		Integer clients = zeroOrNumber(clientDao.countOfClientsByRegion(regionId, false));
		Integer companies = zeroOrNumber(clientDao.countOfClientsByRegion(regionId, true));
		// For Month
		Integer plannedC = zeroOrNumber(visitPlanDao.sumPlannedByRegion(regionId, monthId));
		Integer plannedCCompanies = zeroOrNumber(visitPlanDao.sumPlannedCompaniesByRegion(regionId, monthId));
		Integer visitedClientsCount = zeroOrNumber(clientHistoryDao.getVisitedCountByMonth(regionId, semiAnnualId, monthId % 10, false));
		Integer visitedCompaniesCount = zeroOrNumber(clientHistoryDao.getVisitedCountByMonth(regionId, semiAnnualId, monthId % 10, true));
		Float percent = plannedC != 0 ? (visitedClientsCount / plannedC) * 100 : 0f;
		Float percentCompanies = plannedCCompanies != 0 ? (visitedCompaniesCount / plannedCCompanies) * 100 : 0f;
		Integer notVisitedClientsCount = plannedC - visitedClientsCount;
		Integer notVisitedCompaniesCount = plannedCCompanies - visitedCompaniesCount;

		Integer violationCodesCount = zeroOrNumber(clientHistoryDao.getViolationCodesCountByMonth(regionId, semiAnnualId, monthId % 10, false));
		Integer violationCodesCountCompanies = zeroOrNumber(clientHistoryDao.getViolationCodesCountByMonth(regionId, semiAnnualId, monthId % 10, true));

		// For SemiANnual
		Integer plannedCSemi = zeroOrNumber(visitPlanDao.sumPlannedByRegionAndSemiAnnual(regionId, semiAnnualId));
		Integer plannedCSemiCompanies = zeroOrNumber(visitPlanDao.sumPlannedCompaniesByRegionAndSemiAnnual(regionId, semiAnnualId));
		Integer visitedClientsCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnual(regionId, semiAnnualId, 1, false));
		Integer visitedCompaniesCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnual(regionId, semiAnnualId, 1, true));
		Float percentSemi = plannedCSemi != 0 ? (visitedClientsCountSemi / plannedCSemi) * 100 : 0f;
		Float percentSemiCompanies = plannedCSemiCompanies != 0 ? (visitedCompaniesCountSemi / plannedCSemiCompanies) * 100 : 0f;
		Integer notVisitedClientsCountSemi = plannedCSemi - visitedClientsCountSemi;
		Integer notVisitedCompaniesCountSemi = plannedCSemiCompanies - visitedCompaniesCountSemi;

		Integer closedDoorClientsCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnual(regionId, semiAnnualId, 4, false));
		Integer closedDoorCompaniesCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnual(regionId, semiAnnualId, 4, true));

		Integer violationCodesCountSemi = zeroOrNumber(clientHistoryDao.getViolationCodesCountBySemiAnnual(regionId, semiAnnualId, false));
		Integer violationCodesCountSemiCompanies = zeroOrNumber(clientHistoryDao.getViolationCodesCountBySemiAnnual(regionId, semiAnnualId, true));

		row = sheet.getRow(3);
		row.getCell(13).setCellValue(regionName + "ի ԱՇՏ");

		row = sheet.getRow(9);
		row.getCell(0).setCellValue(regionName);
		row.getCell(2).setCellValue(clients + companies);
		row.getCell(3).setCellValue(plannedC + plannedCCompanies);
		row.getCell(4).setCellValue(visitedClientsCount + visitedCompaniesCount);
		row.getCell(5).setCellValue(percent + percentCompanies);
		row.getCell(6).setCellValue(notVisitedClientsCount + notVisitedCompaniesCount);
		row.getCell(7).setCellValue(plannedCSemi + plannedCSemiCompanies);
		row.getCell(8).setCellValue(visitedClientsCountSemi + visitedCompaniesCountSemi);
		row.getCell(9).setCellValue(percentSemi + percentSemiCompanies);
		row.getCell(10).setCellValue(notVisitedClientsCountSemi + notVisitedCompaniesCountSemi);
		row.getCell(11).setCellValue(closedDoorClientsCountSemi + closedDoorCompaniesCountSemi);
		row.getCell(12).setCellValue(0); // piti gumarvi dzernarkurtyun@ TODO
		row.getCell(13).setCellValue(violationCodesCount + violationCodesCountCompanies);
		row.getCell(14).setCellValue(violationCodesCountSemi + violationCodesCountSemiCompanies);
		row.setHeight((short) 400);

		row = sheet.getRow(10);
		row.setHeight((short) 400);
		row.getCell(0).setCellValue("Բնակչություն");

		row.getCell(2).setCellValue(clients);
		row.getCell(3).setCellValue(plannedC);
		row.getCell(4).setCellValue(visitedClientsCount);
		row.getCell(5).setCellValue(percent);
		row.getCell(6).setCellValue(notVisitedClientsCount);
		row.getCell(7).setCellValue(plannedCSemi);
		row.getCell(8).setCellValue(visitedClientsCountSemi);
		row.getCell(9).setCellValue(percentSemi);
		row.getCell(10).setCellValue(notVisitedClientsCountSemi);
		row.getCell(11).setCellValue(closedDoorClientsCountSemi);
		row.getCell(12).setCellValue(0); //TODO
		row.getCell(13).setCellValue(violationCodesCount);
		row.getCell(14).setCellValue(violationCodesCountSemi);
//        sheet.addMergedRegion(CellRangeAddress.valueOf("A11:B11"));

		row = sheet.getRow(11);
//        createCell(row, 0);
		row.getCell(0).setCellValue("Հիմնարկ ձեռնարկություն");
		row.setHeight((short) 400);
		row.getCell(2).setCellValue(companies);
		row.getCell(3).setCellValue(plannedCCompanies);
		row.getCell(7).setCellValue(plannedCSemiCompanies);
		row.getCell(3).setCellValue(plannedCCompanies);
		row.getCell(4).setCellValue(visitedCompaniesCount);
		row.getCell(5).setCellValue(percentCompanies);
		row.getCell(6).setCellValue(notVisitedCompaniesCount);
		row.getCell(7).setCellValue(plannedCSemiCompanies);
		row.getCell(8).setCellValue(visitedCompaniesCountSemi);
		row.getCell(9).setCellValue(percentSemiCompanies);
		row.getCell(10).setCellValue(notVisitedCompaniesCountSemi);
		row.getCell(11).setCellValue(closedDoorCompaniesCountSemi);
		row.getCell(12).setCellValue(0); //TODO
		row.getCell(13).setCellValue(violationCodesCountCompanies);
		row.getCell(14).setCellValue(violationCodesCountSemiCompanies);


//        sheet.addMergedRegion(CellRangeAddress.valueOf("A12:B12"));
		List<Section> sections = portfolioForm.visitGraficSectionsByRegionId(regionId);
		for (int i = 0; i < sections.size(); i++) {
			int rowIndex = i * 3 + 13;
			Section section = sections.get(i);

			clients = zeroOrNumber(clientDao.countOfClientsBySection(section.getId(), false));
			companies = zeroOrNumber(clientDao.countOfClientsBySection(section.getId(), true));
			// for month
			plannedC = zeroOrNumber(visitPlanDao.sumPlannedBySection(section.getId(), monthId));
			plannedCCompanies = zeroOrNumber(visitPlanDao.sumPlannedCompaniesBySection(section.getId(), monthId));
			visitedClientsCount = zeroOrNumber(clientHistoryDao.getVisitedCountByMonthAndSection(regionId, semiAnnualId, monthId % 10, section.getId(), false));
			visitedCompaniesCount = zeroOrNumber(clientHistoryDao.getVisitedCountByMonthAndSection(regionId, semiAnnualId, monthId % 10, section.getId(), true));
			percent = plannedC != 0 ? (visitedClientsCount / plannedC) * 100 : 0f;
			percentCompanies = plannedCCompanies != 0 ? (visitedCompaniesCount / plannedCCompanies) * 100 : 0f;
			notVisitedClientsCount = plannedC - visitedClientsCount;
			notVisitedCompaniesCount = plannedCCompanies - visitedCompaniesCount;

			violationCodesCount = zeroOrNumber(clientHistoryDao.getViolationCodesCountByMonthAndSection(regionId, semiAnnualId, monthId % 10, section.getId(), false));
			violationCodesCountCompanies = zeroOrNumber(clientHistoryDao.getViolationCodesCountByMonthAndSection(regionId, semiAnnualId, monthId % 10, section.getId(), true));

			// For Semi
			plannedCSemi = zeroOrNumber(visitPlanDao.sumPlannedBySectionAndSemiAnnual(section.getId(), semiAnnualId));
			plannedCSemiCompanies = zeroOrNumber(visitPlanDao.sumPlannedCompaniesBySectionAndSemiAnnual(section.getId(), semiAnnualId));
			visitedClientsCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), 1, false));
			visitedCompaniesCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), 1, true));
			percentSemi = plannedCSemi != 0 ? (visitedClientsCountSemi / plannedCSemi) * 100 : 0f;
			percentSemiCompanies = plannedCSemiCompanies != 0 ? (visitedCompaniesCountSemi / plannedCSemiCompanies) * 100 : 0f;
			notVisitedClientsCountSemi = plannedCSemi - visitedClientsCountSemi;
			notVisitedCompaniesCountSemi = plannedCSemiCompanies - visitedCompaniesCountSemi;

			closedDoorClientsCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), 4, false));
			closedDoorCompaniesCountSemi = zeroOrNumber(clientHistoryDao.getVisitedCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), 4, true));

			violationCodesCountSemi = zeroOrNumber(clientHistoryDao.getViolationCodesCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), false));
			violationCodesCountSemiCompanies = zeroOrNumber(clientHistoryDao.getViolationCodesCountBySemiAnnualAndSection(regionId, semiAnnualId, section.getId(), true));

			row = createRow(sheet, rowIndex);
			row.setHeight((short) 400);
			createCell(row, 0);
			row.getCell(0).setCellValue(i + 1);
			setCellStyle(row.getCell(0), workbook, (short) 11);
			createCell(row, 1);
			row.getCell(1).setCellValue(section.getName());
			setCellStyle(row.getCell(1), workbook, (short) 11);
			createCell(row, 2);
			setCellStyle(row.getCell(2), workbook, (short) 10);
			row.getCell(2).setCellValue(clients + companies);
			createCell(row, 3);
			setCellStyle(row.getCell(3), workbook, (short) 10);
			row.getCell(3).setCellValue(plannedC + plannedCCompanies);
			createCell(row, 4);
			setCellStyle(row.getCell(4), workbook, (short) 10);
			row.getCell(4).setCellValue(visitedClientsCount + visitedCompaniesCount);
			createCell(row, 5);
			setCellStyle(row.getCell(5), workbook, (short) 10);
			row.getCell(5).setCellValue(percent + percentCompanies);
			setCellStyleFont(row.getCell(5).getCellStyle(), workbook);
			createCell(row, 6);
			setCellStyle(row.getCell(6), workbook, (short) 10);
			row.getCell(6).setCellValue(notVisitedClientsCount + notVisitedCompaniesCount);
			createCell(row, 7);
			setCellStyle(row.getCell(7), workbook, (short) 10);
			row.getCell(7).setCellValue(plannedCSemi + plannedCSemiCompanies);
			createCell(row, 8);
			setCellStyle(row.getCell(8), workbook, (short) 10);
			row.getCell(8).setCellValue(visitedClientsCountSemi + visitedCompaniesCountSemi);
			createCell(row, 9);
			setCellStyle(row.getCell(9), workbook, (short) 10);
			row.getCell(9).setCellValue(percentSemi + percentSemiCompanies);
			setCellStyleFont(row.getCell(9).getCellStyle(), workbook);
			createCell(row, 10);
			setCellStyle(row.getCell(10), workbook, (short) 10);
			row.getCell(10).setCellValue(notVisitedClientsCountSemi + notVisitedCompaniesCountSemi);
			createCell(row, 11);
			setCellStyle(row.getCell(11), workbook, (short) 10);
			row.getCell(11).setCellValue(closedDoorClientsCountSemi + closedDoorCompaniesCountSemi);
			createCell(row, 12);
			setCellStyle(row.getCell(12), workbook, (short) 10);
			row.getCell(12).setCellValue(0);  // petq e gumarvi dzernarkutyun@ TODO
			createCell(row, 13);
			setCellStyle(row.getCell(13), workbook, (short) 10);
			row.getCell(13).setCellValue(violationCodesCount + violationCodesCountCompanies);
			createCell(row, 14);
			setCellStyle(row.getCell(14), workbook, (short) 10);
			row.getCell(14).setCellValue(violationCodesCountSemi + violationCodesCountSemiCompanies);
			createCell(row, 15);
			setCellStyle(row.getCell(15), workbook, (short) 10);
			row.getCell(15).setCellValue(0); //TODO
			createCell(row, 16);
			setCellStyle(row.getCell(16), workbook, (short) 10);
			row.getCell(16).setCellValue(0); //TODO
			createCell(row, 17);
			setCellStyle(row.getCell(17), workbook, (short) 10);
			row.getCell(17).setCellValue(0); //TODO

			row = createRow(sheet, rowIndex + 1);
			row.setHeight((short) 400);
			createCell(row, 0);
			setCellStyle(row.getCell(0), workbook, (short) 10);
			createCell(row, 1);
			setCellStyle(row.getCell(1), workbook, (short) 10);
			row.getCell(0).setCellValue("Բնակչություն");

			createCell(row, 2);
			setCellStyle(row.getCell(2), workbook, (short) 10);
			row.getCell(2).setCellValue(clients);
			createCell(row, 3);
			setCellStyle(row.getCell(3), workbook, (short) 10);
			row.getCell(3).setCellValue(plannedC);
			createCell(row, 4);
			setCellStyle(row.getCell(4), workbook, (short) 10);
			row.getCell(4).setCellValue(visitedClientsCount);
			createCell(row, 5);
			setCellStyle(row.getCell(5), workbook, (short) 10);
			row.getCell(5).setCellValue(percent);
			setCellStyleFont(row.getCell(5).getCellStyle(), workbook);
			createCell(row, 6);
			setCellStyle(row.getCell(6), workbook, (short) 10);
			row.getCell(6).setCellValue(notVisitedClientsCount);
			createCell(row, 7);
			setCellStyle(row.getCell(7), workbook, (short) 10);
			row.getCell(7).setCellValue(plannedCSemi);
			createCell(row, 8);
			setCellStyle(row.getCell(8), workbook, (short) 10);
			row.getCell(8).setCellValue(visitedClientsCountSemi);
			createCell(row, 9);
			setCellStyle(row.getCell(9), workbook, (short) 10);
			row.getCell(9).setCellValue(percentSemi);
			setCellStyleFont(row.getCell(9).getCellStyle(), workbook);
			createCell(row, 10);
			setCellStyle(row.getCell(10), workbook, (short) 10);
			row.getCell(10).setCellValue(notVisitedClientsCountSemi);
			createCell(row, 11);
			setCellStyle(row.getCell(11), workbook, (short) 10);
			row.getCell(11).setCellValue(closedDoorClientsCountSemi);
			createCell(row, 12);
			setCellStyle(row.getCell(12), workbook, (short) 10);
			row.getCell(12).setCellValue(0); //TODO
			createCell(row, 13);
			setCellStyle(row.getCell(13), workbook, (short) 10);
			row.getCell(13).setCellValue(violationCodesCount);
			createCell(row, 14);
			setCellStyle(row.getCell(14), workbook, (short) 10);
			row.getCell(14).setCellValue(violationCodesCountSemi);
			createCell(row, 15);
			setCellStyle(row.getCell(15), workbook, (short) 10);
			row.getCell(15).setCellValue(0); //TODO
			createCell(row, 16);
			setCellStyle(row.getCell(16), workbook, (short) 10);
			row.getCell(16).setCellValue(0); //TODO
			createCell(row, 17);
			setCellStyle(row.getCell(17), workbook, (short) 10);
			row.getCell(17).setCellValue(0); //TODO


			String mergeIndex = "A%s:B%s";
			sheet.addMergedRegion(CellRangeAddress.valueOf(String.format(mergeIndex, rowIndex + 2, rowIndex + 2)));

			row = createRow(sheet, rowIndex + 2);
			row.setHeight((short) 400);
			createCell(row, 0);
			setCellStyle(row.getCell(0), workbook, (short) 10);
			createCell(row, 1);
			setCellStyle(row.getCell(1), workbook, (short) 10);
			row.getCell(0).setCellValue("Հիմնարկ ձեռնարկություն");
			sheet.addMergedRegion(CellRangeAddress.valueOf(String.format(mergeIndex, rowIndex + 3, rowIndex + 3)));

			createCell(row, 2);
			setCellStyle(row.getCell(2), workbook, (short) 10);
			row.getCell(2).setCellValue(companies);
			createCell(row, 3);
			setCellStyle(row.getCell(3), workbook, (short) 10);
			row.getCell(3).setCellValue(plannedCCompanies);
			createCell(row, 7);
			setCellStyle(row.getCell(7), workbook, (short) 10);
			row.getCell(7).setCellValue(plannedCSemiCompanies);
			createCell(row, 3);
			setCellStyle(row.getCell(3), workbook, (short) 10);
			row.getCell(3).setCellValue(plannedCCompanies);
			createCell(row, 4);
			setCellStyle(row.getCell(4), workbook, (short) 10);
			row.getCell(4).setCellValue(visitedCompaniesCount);
			createCell(row, 5);
			setCellStyle(row.getCell(5), workbook, (short) 10);
			row.getCell(5).setCellValue(percentCompanies);
			setCellStyleFont(row.getCell(5).getCellStyle(), workbook);
			createCell(row, 6);
			setCellStyle(row.getCell(6), workbook, (short) 10);
			row.getCell(6).setCellValue(notVisitedCompaniesCount);
			createCell(row, 7);
			setCellStyle(row.getCell(7), workbook, (short) 10);
			row.getCell(7).setCellValue(plannedCSemiCompanies);
			createCell(row, 8);
			setCellStyle(row.getCell(8), workbook, (short) 10);
			row.getCell(8).setCellValue(visitedCompaniesCountSemi);
			createCell(row, 9);
			setCellStyle(row.getCell(9), workbook, (short) 10);
			row.getCell(9).setCellValue(percentSemiCompanies);
			setCellStyleFont(row.getCell(9).getCellStyle(), workbook);
			createCell(row, 10);
			setCellStyle(row.getCell(10), workbook, (short) 10);
			row.getCell(10).setCellValue(notVisitedCompaniesCountSemi);
			createCell(row, 11);
			setCellStyle(row.getCell(11), workbook, (short) 10);
			row.getCell(11).setCellValue(closedDoorCompaniesCountSemi);
			createCell(row, 12);
			setCellStyle(row.getCell(12), workbook, (short) 10);
			row.getCell(12).setCellValue(0); //TODO
			createCell(row, 13);
			setCellStyle(row.getCell(13), workbook, (short) 10);
			row.getCell(13).setCellValue(violationCodesCountCompanies);
			createCell(row, 14);
			setCellStyle(row.getCell(14), workbook, (short) 10);
			row.getCell(14).setCellValue(violationCodesCountSemiCompanies);
			createCell(row, 15);
			setCellStyle(row.getCell(15), workbook, (short) 10);
			row.getCell(15).setCellValue(0); //TODO
			createCell(row, 16);
			setCellStyle(row.getCell(16), workbook, (short) 10);
			row.getCell(16).setCellValue(0); //TODO
			createCell(row, 17);
			setCellStyle(row.getCell(17), workbook, (short) 10);
			row.getCell(17).setCellValue(0); //TODO
		}

		int rowIndex = sections.size()*3 + 14;
		row = createRow(sheet,  rowIndex);
		row.setHeight((short) 400);
		createCell(row, 6);
		setFooterCellStyle(row.getCell(6), workbook, (short) 10);
		row.getCell(6).setCellValue(regionName + "ի ԱՇՏ պետ");
		sheet.addMergedRegion(CellRangeAddress.valueOf(String.format("G%s:J%s", rowIndex, rowIndex)));

		createCell(row, 12);
		setFooterCellStyle(row.getCell(12), workbook, (short) 10);
		row.getCell(12).setCellValue("");
		sheet.addMergedRegion(CellRangeAddress.valueOf(String.format("M%s:P%s", rowIndex, rowIndex)));

		rowIndex ++;
		row = createRow(sheet,  rowIndex );
		row.setHeight((short) 400);
		createCell(row, 6);
		setFooterCellStyle(row.getCell(6), workbook, (short) 10);
		row.getCell(6).setCellValue("Կատարեց");
		sheet.addMergedRegion(CellRangeAddress.valueOf(String.format("G%s:J%s", rowIndex, rowIndex)));

		rowIndex++;
		row = createRow(sheet,  rowIndex );
		row.setHeight((short) 400);
		createCell(row, 1);
		setFooterCellStyle(row.getCell(1), workbook, (short) 10);
		row.getCell(1).setCellValue(" <<---------->> -------------------------------- 201_ թ․");
		sheet.addMergedRegion(CellRangeAddress.valueOf(String.format("B%s:D%s", rowIndex, rowIndex)));


		// Write the output to the file
		FileOutputStream out = new FileOutputStream(portfolioForm.getFile2UploadUrl());
		workbook.write(out);

		// Closing the workbook
		workbook.close();
	}

	public Row createRow(Sheet sheet, int index) {
		return sheet.createRow(index);
	}

	public Cell createCell(Row row, int index) {
		return row.createCell(index);
	}

	public Integer zeroOrNumber(Integer i) {
		return i == null ? 0 : i;
	}

	public void setCellStyle(Cell cell, XSSFWorkbook workbook, short fontSize) {
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		font.setFontName("Arial Unicode");

		//Set font into style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setFont(font);
		cell.setCellStyle(style);
	}

	public void setFooterCellStyle(Cell cell, XSSFWorkbook workbook, short fontSize) {
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		font.setFontName("Arial Unicode");

		//Set font into style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		cell.setCellStyle(style);
	}

	public void setCellStyleFont(CellStyle cellStyleFont, XSSFWorkbook workbook) {
		cellStyleFont.setDataFormat(workbook.createDataFormat().getFormat("0.0%"));
	}

}
