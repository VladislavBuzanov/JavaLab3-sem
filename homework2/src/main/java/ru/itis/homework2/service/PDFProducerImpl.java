package ru.itis.homework2.service;


import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PDFProducerImpl implements PDFProducer {
    @SneakyThrows
    @Override
    public void produce(String title, String body, String code) {
        InputStream employeeReportStream
                = getClass().getResourceAsStream("/report.jrxml");
        JasperReport jasperReport
                = JasperCompileManager.compileReport(employeeReportStream);

        JRPdfExporter exporter = new JRPdfExporter();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", title);
        parameters.put("body", body);
        parameters.put("date", new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE));

        JasperPrint jasperPrint
                = JasperFillManager.fillReport(jasperReport, parameters);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput(code + "-" + UUID.randomUUID() + ".pdf"));

        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }
}
