package ru.itis.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class PDFProducerImpl implements PDFProducer {
    @Override
    public void produce(String title, String body, String code) {
        try {
            System.out.println("Begin producing");
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Document-" + code + "-" + UUID.randomUUID() + ".pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
            Chunk titleChunk = new Chunk(title, font);
            Chunk bodyChunk = new Chunk(body, FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK));


            Phrase titlePhrase = new Phrase(titleChunk);
            Phrase bodyPhrase = new Phrase(bodyChunk);

            Paragraph paragraph = new Paragraph();
            paragraph.add(titlePhrase);
            paragraph.add(bodyPhrase);
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);
            document.close();
            System.out.println("File created.");
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
}
