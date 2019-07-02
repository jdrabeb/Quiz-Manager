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

/**
 * PDFFormatter - Formates the pdf with the questions and answers of the quiz. 
 * @author rabeb
 *
 */
public class PDFFormatter {
	
    Document document;
	
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);
    
    /**
     * Constructor of PDFFormatter
     */
    public PDFFormatter() {
    	document = new Document();
    }

    /**
     * Create a pdf file.
     * @param fileName - The filename of the pdf.
     */
    public void createPdf(String fileName)
    {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    /**
     * Closes the pdf document.
     * @param document
     */
    public void closeDocument()
    {
        try {
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }	
    }

    /**
     * Writes title part of the pdf.
     * @param document
     * @param title - The title to add to the pdf.
     * @param studentName - The student taking the quiz.
     * @throws DocumentException
     */
    public void addTitlePage(String title, String studentName)
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

    /**
     * Add question to the pdf.
     * @param document
     * @param content - A formatted question.
     * @throws DocumentException
     */
    public void addQuestion(String content) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
    	paragraph.add(new Paragraph(content, subFont));
        addEmptyLine(paragraph, 1);
        document.add(paragraph);
    }
    
    /**
     * Add the user's answer to the pdf.
     * @param document
     * @param content - Formatted answer.
     * @throws DocumentException
     */
    public void addAnswer(String content) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
    	paragraph.add(new Paragraph("Your answer: ", smallBold));
        addEmptyLine(paragraph, 1);
    	paragraph.add(new Paragraph(content, subFont));
        addEmptyLine(paragraph, 1);
        paragraph.add(new LineSeparator());
        document.add(paragraph);
    }
    
    /**
     * Add the grade of the user to the pdf.
     * @param document
     * @param grade - The grade of the user at the quiz. 
     * @throws DocumentException
     */
    public void addGrade(int grade) throws DocumentException
    {
    	Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 3);
    	paragraph.add(new Paragraph("Your final grade is: ", catFont));
        addEmptyLine(paragraph, 1);
    	paragraph.add(new Paragraph(Integer.toString(grade), smallBold));
        addEmptyLine(paragraph, 2);
        document.add(paragraph);
    }

    /**
     *  Adds configurable number of empty lines to a paragraph.
     * @param paragraph - Paragraph to add empty lines to.
     * @param number - Number of empty lines.
     */
    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
