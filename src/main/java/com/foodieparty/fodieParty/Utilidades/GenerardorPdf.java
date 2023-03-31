package com.foodieparty.fodieParty.Utilidades;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class GenerardorPdf {
    public void generarPdf(List<FilaTabla> filas, String total){
        try {
            //Create Document instance.
            Document document = new Document();
            document.setMargins(20,20,40,40);
            //Create OutputStream instance.
            OutputStream outputStream = new FileOutputStream(new File("TestTableFile.pdf"));
            //Create PDFWriter instance.
            PdfWriter.getInstance(document, outputStream);
            //Open the document.
            document.open();
            //Create Table object, Here 4 specify the no. of columns
            PdfPTable pdfPTable = new PdfPTable(4);
            pdfPTable.setWidths(new float[] { 1, 3,3,3 });
            //Create a header table
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font font2 =  FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD,BaseColor.BLACK);
            Chunk chunk = new Chunk("DELICATEZZA",font);
            PdfPCell cell = new PdfPCell(new Paragraph(chunk));
            cell.setBackgroundColor(new BaseColor(245,245,245));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(cell);
            //Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(new Chunk("CANT",font)));
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(new Chunk("PRODUCTO",font)));
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(new Chunk("PRECIO",font)));
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(new Chunk("IMPORTE",font)));
            pdfPCell1.setBackgroundColor(new BaseColor(245,245,245));
            pdfPCell2.setBackgroundColor(new BaseColor(245,245,245));
            pdfPCell3.setBackgroundColor(new BaseColor(245,245,245));
            pdfPCell4.setBackgroundColor(new BaseColor(245,245,245));
            pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            //Add cells to table
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            //Add content to the document using Table objects.
            document.add(pdfPTable);
            for(FilaTabla fila:filas){
                pdfPTable = new PdfPTable(4);
                pdfPTable.setWidths(new float[] { 1, 3,3,3 });
                pdfPCell1 = new PdfPCell(new Paragraph(new Chunk(fila.getCantidad(),font)));
                pdfPCell2 = new PdfPCell(new Paragraph(new Chunk(fila.getDescripcion(),font)));
                pdfPCell3 = new PdfPCell(new Paragraph(new Chunk("$"+fila.getPrecio(),font)));
                pdfPCell4 = new PdfPCell(new Paragraph(new Chunk("$"+fila.getTotal(),font)));
                pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdfPCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdfPTable.addCell(pdfPCell1);
                pdfPTable.addCell(pdfPCell2);
                pdfPTable.addCell(pdfPCell3);
                pdfPTable.addCell(pdfPCell4);
                document.add(pdfPTable);
            }
            pdfPTable = new PdfPTable(2);
            pdfPTable.setWidths(new float[] { 7, 3});
            pdfPCell1 = new PdfPCell(new Paragraph(new Chunk("TOTAL A PAGAR",font)));
            pdfPCell2 = new PdfPCell(new Paragraph(new Chunk("$"+total,font)));
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            document.add(pdfPTable);
            //Close document and outputStream.
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
