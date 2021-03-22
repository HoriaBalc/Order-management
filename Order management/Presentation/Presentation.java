package Presentation;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Stream;

/**
 *clasa Presentation care, dupa cum ii indica numele prezinta pdf-urile de la fiecare table existent in baza de date
 * */
public class Presentation {
    /**
     *Metoda pdf creeaza pdf-ul in care se insereaza tabelul Client continut de baza de date.
     * */
    public void pdf() throws ClassNotFoundException, SQLException, DocumentException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bazadate", "root", "Ferary99");
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM bazadate.client");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("TableClient.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(2);
        PdfPCell table_cell;
        table.addCell("Clientul");
        table.addCell("Oras");
        while (query_set.next()) {
            String name = query_set.getString("nume");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String oras=query_set.getString("oras");
            table_cell=new PdfPCell(new Phrase(oras));
            table.addCell(table_cell);
        }
        document.add(table);
        document.close();
        query_set.close();
        stmt.close();
        conn.close();
    }
    /**
     *Metoda pdf1 creeaza pdf-ul in care se insereaza tabelul Product continut de baza de date.
     * */
    public void pdf1() throws ClassNotFoundException, SQLException, DocumentException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bazadate", "root", "Ferary99");
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM bazadate.product");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("TableProduct.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(3);
        PdfPCell table_cell;
        table.addCell("Produsul");
        table.addCell("Cantitate");
        table.addCell("Pret pe bucata");
        while (query_set.next()) {
            String name = query_set.getString("name");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String cant=query_set.getString("cant");
            table_cell=new PdfPCell(new Phrase(cant));
            table.addCell(table_cell);
            String pret=query_set.getString("pret");
            table_cell=new PdfPCell(new Phrase(pret));
            table.addCell(table_cell);
        }
        document.add(table);
        document.close();
        query_set.close();
        stmt.close();
        conn.close();
    }
    /**
     *Metoda pdf2 creeaza pdf-ul in care se insereaza tabelul Order continut de baza de date.
     * */
    public void pdf2() throws ClassNotFoundException, SQLException, DocumentException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bazadate", "root", "Ferary99");
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM bazadate.orders");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("TableOrders.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(3);
        PdfPCell table_cell;
        table.addCell("Numar Comanda");
        table.addCell("Clientul");
        table.addCell("Pret total");
        while (query_set.next()) {
            String cant=query_set.getString("id");
            table_cell=new PdfPCell(new Phrase(cant));
            table.addCell(table_cell);
            String name = query_set.getString("name");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String pret=query_set.getString("pret");
            table_cell=new PdfPCell(new Phrase(pret));
            table.addCell(table_cell);
        }
        document.add(table);
        document.close();
        query_set.close();
        stmt.close();
        conn.close();
    }
    /**
     *Metoda pdf3 creeaza cate un pdf pentru fiecare rand existent in tabelul OrderItem continut in baza de date.
     * */
    public void pdf3(int k) throws ClassNotFoundException, SQLException, DocumentException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bazadate", "root", "Ferary99");
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM bazadate.orderItem");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("TableOrderItem"+k+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(4);
        PdfPCell table_cell;
        for(int i=0;i<k-1;i++)
            query_set.next();
        table.addCell("Comanda");
        table.addCell("Clientul");
        table.addCell("Produsul");
        table.addCell("Cantitate ceruta");
        while (query_set.next()) {
            String cant=query_set.getString("id");
            table_cell=new PdfPCell(new Phrase(cant));
            table.addCell(table_cell);
            String name = query_set.getString("nameC");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String nameP = query_set.getString("nameP");
            table_cell=new PdfPCell(new Phrase(nameP));
            table.addCell(table_cell);
            String pret=query_set.getString("cant");
            table_cell=new PdfPCell(new Phrase(pret));
            table.addCell(table_cell);
        }
        document.add(table);
        document.close();
        query_set.close();
        stmt.close();
        conn.close();
    }

    /**
     *Metoda pdf4 creeaza cate un pdf pentru fiecare rand existent in tabelul OrderItem continut in baza de date, cele cu stoc insuficient.
     * */
    public void pdf4(int k,int k1) throws ClassNotFoundException, SQLException, DocumentException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bazadate", "root", "Ferary99");
        Statement stmt = conn.createStatement();
        ResultSet query_set = stmt.executeQuery("SELECT * FROM bazadate.orderItem");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("TableOrderItemInsuficient"+k+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.addCell("Comanda");
        table.addCell("Clientul");
        table.addCell("Produsul");
        table.addCell("Cantitate ceruta");
        table.addCell("Disponibilitate");

        for(int i=0;i<k1-1;i++)
            query_set.next();
        while (query_set.next()) {
            String cant=query_set.getString("id");
            PdfPCell table_cell = new PdfPCell(new Phrase(cant));
            table.addCell(table_cell);
            String name = query_set.getString("nameC");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String nameP = query_set.getString("nameP");
            table_cell=new PdfPCell(new Phrase(nameP));
            table.addCell(table_cell);
            String cantitate = query_set.getString("cant");
            table_cell=new PdfPCell(new Phrase(cantitate));
            table.addCell(table_cell);
        }
        table.addCell("Stock insuficient!");
        document.add(table);
        document.close();
        query_set.close();
        stmt.close();
        conn.close();
    }
}
