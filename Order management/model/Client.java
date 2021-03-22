package model;
/**
 * 		Aceasta clasa contine doua variabile private care reprezinta coloanele tabelului Client.
 * */
public class Client {
    private String name;
    private String oras;
    /**
     * constructorul clasei Client
     * */
    public Client(String name, String oras) {
        super();

        this.name = name;
        this.oras = oras;
    }
/**
 * Getter pentru variabila name
 * */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru variabila name
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter pentru variabila oras
     * */
    public String getOras() {
        return oras;
    }

    /**
     * setter pentru variabila oras
     * */
    public void setOras(String oras) {
        this.oras = oras;
    }

    /**
     * Metoda toString are rolul de a afisa datele din  aceasta clasa.
     * */
    @Override
    public String toString() {
        return "Client [ name=" + name + ", oras=" + oras + "]";
    }

}

