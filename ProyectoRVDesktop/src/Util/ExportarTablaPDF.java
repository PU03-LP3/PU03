package Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.function.IntFunction;
import javax.imageio.ImageIO;

public class ExportarTablaPDF {

    public static void exportarTablaConImagenes(JTable tabla, String ruta, String titulo, IntFunction<byte[]> obtenerImagenPorId) throws Exception {
        // Márgenes: izquierda, derecha, arriba, abajo
        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        // Fuente principal
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);

        // Encabezado general
        Paragraph tituloDoc = new Paragraph(titulo, fontTitulo);
        tituloDoc.setAlignment(Element.ALIGN_CENTER);
        document.add(tituloDoc);
        document.add(new Paragraph("Fecha: " + new java.util.Date(), fontNormal));
        document.add(Chunk.NEWLINE);

        TableModel model = tabla.getModel();

        for (int fila = 0; fila < model.getRowCount(); fila++) {
            // Agregar separador visual
            document.add(new Paragraph("Reporte #" + (fila + 1), fontSubtitulo));
            document.add(Chunk.NEWLINE);

            // Crear tabla de datos (fila)
            PdfPTable reporteTable = new PdfPTable(model.getColumnCount());
            reporteTable.setWidthPercentage(100);
            reporteTable.setSpacingBefore(5f);
            reporteTable.setSpacingAfter(10f);

            // Encabezados
            for (int col = 0; col < model.getColumnCount(); col++) {
                PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(col), fontSubtitulo));
                cell.setBackgroundColor(new BaseColor(230, 230, 250));
                cell.setPadding(5);
                reporteTable.addCell(cell);
            }

            // Datos
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object valor = model.getValueAt(fila, col);
                PdfPCell cell = new PdfPCell(new Phrase(valor != null ? valor.toString() : "", fontNormal));
                cell.setPadding(5);
                reporteTable.addCell(cell);
            }

            document.add(reporteTable);

            // Obtener imagen
            int reportId;
            try {
                reportId = Integer.parseInt(model.getValueAt(fila, 0).toString());
            } catch (Exception e) {
                document.add(new Paragraph("ID inválido para obtener imagen."));
                document.add(Chunk.NEWLINE);
                continue;
            }

            byte[] imgBytes = obtenerImagenPorId.apply(reportId);

            if (imgBytes != null) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imgBytes));
                    java.awt.Image scaledImg = bufferedImage.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
                    com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(scaledImg, null);
                    imagen.setAlignment(Element.ALIGN_CENTER);
                    imagen.scaleToFit(300, 200);

                    Paragraph imagenTitulo = new Paragraph("Imagen del reporte:", fontSubtitulo);
                    imagenTitulo.setAlignment(Element.ALIGN_LEFT);
                    document.add(imagenTitulo);
                    document.add(imagen);
                } catch (Exception e) {
                    document.add(new Paragraph("No se pudo procesar la imagen del reporte ID " + reportId, fontNormal));
                }
            } else {
                document.add(new Paragraph("No hay imagen para el reporte ID: " + reportId, fontNormal));
            }

            // Espacio entre reportes
            document.add(Chunk.NEWLINE);
            document.add(new LineSeparator());
            document.add(Chunk.NEWLINE);
        }

        document.close();
    }
}
