package model;

public class Carta implements Comparable {

    private final String naipe;
    private final String valor;
    private final Integer indice;

    public Carta(String naipe, String valor, int indice) {
        this.naipe = naipe;
        this.valor = valor;
        this.indice = indice;

    }

    public String getNaipe() {
        return naipe;
    }

    public int getValor() {
        int val = 0;
        if (valor.equals("A")) {
            val = 1;
        }
        if (valor.equals("2")) {
            val = 2;
        }
        if (valor.equals("3")) {
            val = 3;
        }
        if (valor.equals("4")) {
            val = 4;
        }
        if (valor.equals("5")) {
            val = 5;
        }
        if (valor.equals("6")) {
            val = 6;
        }
        if (valor.equals("7")) {
            val = 7;
        }
        if (valor.equals("8")) {
            val = 8;
        }
        if (valor.equals("9")) {
            val = 9;
        }
        if (valor.equals("10") || valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
            val = 10;
        }
        return val;
    }

    @Override
    public String toString() {
        return this.valor + " de " + this.naipe;
    }

    @Override
    public int compareTo(Object o) {
        return this.indice.compareTo(((Carta) o).indice);
    }
}
