package ru.itis.service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface PDFProducer {
    void produce(String title, String body, String code);
}
