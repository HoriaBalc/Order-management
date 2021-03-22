package model;
/**
 * Aceasta clasa contine trei variabile private care reprezinta coloanele tabelului Product. De asemenea, clasa are getteri pentru cele trei variabile.
 * */
public class Product {

    private String nume;
    private int cant;
    private double pret;
    /**
     * constructorul clasei Product
     * */
    public Product(String nume, int cant,double pret) {
        super();
        this.cant = cant;
        this.nume = nume;
        this.pret = pret;
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
     * Getter pentru Name
     * */
    public String getName() {
        return nume;
    }
    /**
     * Setter pentru Name
     * */
    public void setName(String name) {
        this.nume = name;
    }

    /**
     * Getter pentru cant
     * */
    public int getCant() {
        return cant;
    }
    /**
     * Setter pentru cant
     * */
    public void setCant(int cant) {
        this.cant = cant;
    }

    /**
     *  Metoda toString are rolul de a afisa datele din  aceasta clasa.
     * */
    @Override
    public String toString() {
        return "Product [name=" + nume + ", cant=" + cant + ", pret=" + pret + "]";
    }

}

