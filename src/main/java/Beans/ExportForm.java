package Beans;

import Models.Client;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.*;
import java.util.List;

public class ExportForm {

    private StreamedContent file;

    public StreamedContent getFile(List<Client> selectedClients) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:/Users/arsha/Desktop/Projects/Artsakh-Gas/src/main/resources/testFile2.xlsx");
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
}
