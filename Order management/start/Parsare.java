package start;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import Presentation.Presentation;
import com.itextpdf.text.DocumentException;
import dao.ClientDAO;
import dao.ProductDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import model.Client;
import model.Product;
import model.Order;
import Presentation.Presentation;
import model.OrderItem;
/**
 * In aceasta clasa se utilzeaza mai multe metode care practic formeaza o singura metoda ce citeste din fisier si in functie de cuvintele gasite se apeleaza diferite metode.
 */
public class Parsare {
    /**
     *Metoda inserare primeste ca parametrii sirul de stringuri un obiect de tip ClientDAO si altul de tip ProductDAO
     * Aceasta metoda insereaza in client sau in product depinde de valoarea din al doile string
     */
public void inserare(String splitString[],ClientDAO c,ProductDAO p) {
    if (splitString[1].equals("client:")) {
        Client client = new Client(splitString[2] + " " + splitString[3], splitString[4]);
        c.insert(client);
    } else {
        if (splitString[1].equals("product:")) {
            int cant = Integer.parseInt(splitString[3]);
            double pret = Double.parseDouble(splitString[4]);
            if (p.findById(splitString[2]) == null) {
                Product product = new Product(splitString[2], cant, pret);
                System.out.println(product);
                p.insert(product);
            } else {
                Product product = p.findById(splitString[2]);
                p.update(product, cant);
            }
        }
    }
}
    /**
     *Metoda stergere primeste ca parametrii sirul de stringuri un obiect de tip ClientDAO ,OrderItemDAO, OrderDAO si altul de tip ProductDAO
     * Aceasta metoda sterge din client sau din product depinde de valoarea din al doile string, avad ca si consecinte si stergerea din Order sau OrderItem
     */
public void stergere(String splitString[],OrderDAO ord,OrderItemDAO o,ClientDAO c,ProductDAO p){
    if (splitString[1].equals("client:")) {
        Client client = new Client(splitString[2] +" "+ splitString[3], splitString[4]);
        OrderItem ot=o.findByName(splitString[2] +" "+ splitString[3]);
        Order or=ord.findByName(splitString[2] +" "+ splitString[3]);
        if(or!=null)
            ord.delete(or);
        while(ot!=null) {
            o.delete(ot);
            o.findByName(splitString[2] +" "+ splitString[3]);
        }
        c.delete(client);
    } else {
        if (splitString[1].equals("produs:")) {
            int cant = Integer.parseInt(splitString[3]);
            double pret = Double.parseDouble(splitString[4]);
            Product product = new Product(splitString[2], cant, pret);
            OrderItem ot=o.findByName(splitString[2]);
            if(ot!=null)
                o.delete(ot);
            p.delete(product);
            o.findByName(splitString[2]);
        }
    }
}
    /**
     *Metoda modificare primeste ca parametrii sirul de stringuri un obiect de tip OrderItemDAO, OrderDAO si altul de tip ProductDAO
     * Aceasta metoda insereaza in client sau in product depinde de valoarea din al doile string
     */
public void modificare(String splitString[],OrderDAO ord,OrderItemDAO o,ProductDAO p) throws DocumentException, SQLException, ClassNotFoundException {
    id=++i;
    int k=1;
    int cant=Integer.parseInt(splitString[4]);
    OrderItem orderItem=new OrderItem(id,splitString[1] + " " + splitString[2],splitString[3],cant);
    o.insert(orderItem);
    Presentation presentation=new Presentation();

    Product pr=p.findById(splitString[3]);
    if(pr!=null){
        if(orderItem.getCant()<pr.getCant()){
            p.update(pr,-cant);
            presentation.pdf3(id);
        }
        else  { presentation.pdf4(k,id);
                k++;
        }
    }
    Order order=ord.findByName(orderItem.getNameC());
    if(order!=null){
        Product prod=p.findById(splitString[3]);
        order.setPret(order.getPret()+prod.getPret()*cant);
        System.out.println(order.getPret());
        ord.update(order);
    }else{
        idc=++j;
        Product prod=p.findById(splitString[3]);
        order=new Order(idc,orderItem.getNameC(),prod.getPret()*cant);
        if(orderItem.getCant()<prod.getCant())
            ord.insert(order);
    }
}
    /**
     *Metoda report primeste ca parametru sirul de stringuri.
     *Aici se instantiaza un obiect de tip Presentation prin intermediul careia se apeleaza metoda pdf daca al doilea string din sir este client, pdf1 daca al doilea string din sir este product, pdf2 daca al doilea string din sir este order.
     */
public void report(String splitString[],OrderDAO ord,OrderItemDAO o,ProductDAO p) throws DocumentException, SQLException, ClassNotFoundException {
    Presentation presentation=new Presentation();
    if (splitString[1].equals("client")) {
        presentation.pdf();
    }
    if (splitString[1].equals("product")) {
        presentation.pdf1();
    }
    if (splitString[1].equals("order")) {
        presentation.pdf2();
    }
}

int idc=0;
int id=0;
int i=0;
int j=0;

/**
 * Metoda citeste din fisier comenzile, iar in functie de acestea se alege ce functie de mai sus se alege.
 * */
public void citire(String text) throws DocumentException, SQLException, ClassNotFoundException {
    Scanner myReader = null;
    i = 0;j=0;id=0;idc=0;
    File f = new File(text);
    try {
        myReader = new Scanner(f);
    } catch (
            FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data=data.replaceAll("\\,", "");
        String[] splitString = (data.split("\\ "));
        ClientDAO c=null;ProductDAO p=null;OrderItemDAO o=null;OrderDAO ord=null;
        if(splitString[0].equals("Insert")) {
          this.inserare(splitString,c,p);
        }
        if (splitString[0].equals("Delete")) {
            this.stergere(splitString,ord,o,c,p);
        }
        if (splitString[0].equals("Order:")) {
            this.modificare(splitString, ord, o, p);
        }
        if (splitString[0].equals("Report")) {
            this.report(splitString, ord, o, p);
        }
    }
    myReader.close();
}
}
