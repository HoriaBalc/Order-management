package model;
/**
 * Aceasta clasa contine trei variabile private care reprezinta coloanele tabelului Order.
 * */
public class Order {
    private int id;
    private String nameClient;
    private double pret;
    /**
     * constructorul clasei Order
     * */
    public Order(int id, String name, double pret) {
        super();
        this.id = id;
        this.nameClient = name;
        this.pret = pret;
    }

/**
 * Getter pentru Id
 * */
    public int getId() {
        return id;
    }
    /**
     * Setter pentru Id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru NameClient
     * */
    public String getNameClient() {
        return nameClient;
    }

    /**
     * Setter pentru NameClient
     * */
    public void setNameClient(String name) {
        this.nameClient = name;
    }

    /**
     * Getter pentru Pret
     * */
    public double getPret() {
        return pret;
    }
    /**
     * Setter pentru Pret
     * */
    public void setPret(double pret) {
        this.pret = pret;
    }
/**
 *  Metoda toString are rolul de a afisa datele din  aceasta clasa.
 * */
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + nameClient + ", pret=" + pret + "]";
    }

}

