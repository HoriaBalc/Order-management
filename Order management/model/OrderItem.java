package model;
/**
 *Aceasta clasa contine patru variabile private care reprezinta coloanele tabelului OrderItem.
 */

public class OrderItem {
    private int id;
    private String nameC;
    private String nameP;
    private int cant;
    /**
     * constructorul clasei OrderItem
     * */
    public OrderItem(int id, String name, String produs, int cant) {
        super();
        this.id = id;
        this.nameC = name;
        this.nameP = produs;
        this.cant = cant;
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
     * Getter pentru NameC
     * */
    public String getNameC() {
        return nameC;
    }
    /**
     * Setter pentru NameC
     * */
    public void setNameC(String name) {
        this.nameC = name;
    }
    /**
     * Getter pentru NameP
     * */
    public String getNameP() {
        return nameP;
    }
    /**
     * Setter pentru NameP
     * */
    public void setNameP(String name) {
        this.nameP = name;
    }
    /**
     * Getter pentru Cant
     * */
    public int getCant() {
        return cant;
    }
    /**
     * Setter pentru Cant
     * */
    public void setCant(int cant) {
        this.cant = cant;
    }

    /**
     *  Metoda toString are rolul de a afisa datele din  aceasta clasa.
     * */
    @Override
    public String toString() {
        return "Student [id=" + id + ", client=" + nameC + ", produs=" + nameP + ", cantitate=" + cant + "]";
    }

}

