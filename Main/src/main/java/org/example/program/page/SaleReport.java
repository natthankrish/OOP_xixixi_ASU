package org.example.program.page;

import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.bills.Time;



import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleReport {
    public static void export() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory == null) {
                return;
            }

            String path = selectedDirectory.getPath() + File.separator + "SalesReport.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            float col = 280.f;
            float columnWidth[] = {col, col};
            Table table = new Table(columnWidth);
            table.setBackgroundColor(new DeviceRgb(213, 180, 180))
                    .setFontColor(new DeviceRgb(0, 0, 0));

            table.addCell(new Cell().add("SALES REPORT")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER)
            );
            table.addCell(new Cell().add("Nama Perusahaan \n1234 Alamat Perusahaan\n+62 34567890")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setMarginRight(10f)
                    .setBorder(Border.NO_BORDER)
            );


            float itemInfoColWidth[] = {140, 140, 140, 140};
            Table itemInfoTable = new Table(itemInfoColWidth);
            itemInfoTable.addCell(new Cell()
                    .add("Date")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Bill ID")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Client ID")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Total Price")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            List<Bill> bills = Manager.getInstance()
                                .getTransactionContainer()
                                .getBuffer()
                                .stream()
                                .sorted(Comparator.comparing(Bill::getTransactionTime).reversed())
                                .collect(Collectors.toList());
            double total = 0;
            for(Bill bill: bills) {
                if (!bill.getIsFixedBill()) continue;
                itemInfoTable.addCell(new Cell().add(bill.getTransactionTime().getStringTime()));
                itemInfoTable.addCell(new Cell().add(bill.getIdBill().toString()));
                itemInfoTable.addCell(new Cell().add(bill.getIdClient().toString()));
                itemInfoTable.addCell(new Cell().add(bill.getTotalPrice().toString()));
                total += bill.getTotalPrice();
            }
            itemInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER)).addCell(new Cell().setBorder(Border.NO_BORDER));
            itemInfoTable.addCell(new Cell()
                    .add("Total Revenue")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add(total + "")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );

            Table bottom = new Table(new float[]{140,140,140,140});


            document.add(table);
            document.add(new Paragraph("\n\n\n"));
            document.add(itemInfoTable);
            document.add(new Paragraph("\n\n"));
            Time currentTime = new Time();
            currentTime.updateCurrentTime();;
            bottom.addCell(new Cell().add("Published Time: ").setBorder(Border.NO_BORDER))
                    .addCell(new Cell().add(currentTime.getStringTime()).setBorder(Border.NO_BORDER))
                    .addCell(new Cell().setBorder(Border.NO_BORDER))
                    .addCell(new Cell().add("Authorised Signatory").setBorder(Border.NO_BORDER));
            document.add(bottom);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
