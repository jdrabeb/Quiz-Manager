package com.epita.quizmanager.services;

import java.io.FileOutputStream;
import java.util.Date;

import com.epita.quizmanager.entities.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PDFFormatter {
	
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);

    public Document CreatePdf(String fileName)
    {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
    
    public void closeDocument(Document document)
    {
        try {
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }	
    }

    public void addTitlePage(Document document, String title, String studentName)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(title, catFont));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Quiz taken by: " + studentName + ", " + new Date(), smallBold));
        preface.add(new LineSeparator());
        addEmptyLine(preface, 2);
        document.add(preface);
    }

    public void addQuestion(Document document, String content) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
    	paragraph.add(new Paragraph(content, subFont));
        addEmptyLine(paragraph, 1);
        document.add(paragraph);
    }
    
    public void addAnswer(Document document, String content) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
    	paragraph.add(new Paragraph("Your answer: ", smallBold));
        addEmptyLine(paragraph, 1);
    	paragraph.add(new Paragraph(content, subFont));
        addEmptyLine(paragraph, 1);
        paragraph.add(new LineSeparator());
        document.add(paragraph);
    }
    
    public void addGrade(Document document, int grade) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 3);
    	paragraph.add(new Paragraph("Your final grade is: ", catFont));
        addEmptyLine(paragraph, 1);
    	paragraph.add(new Paragraph(Integer.toString(grade), smallBold));
        addEmptyLine(paragraph, 2);
        document.add(paragraph);
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


}
