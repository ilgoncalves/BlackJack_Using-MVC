package model;

import util.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Baralho {

    private ILista baralho;

    public IStack getPilhaBaralho() {
        return embaralhar();
    }

    public Baralho() {
        baralho = new ListaEncadeada();
        cartasBaralho();
        embaralhar();

    }

    public void cartasBaralho() {
        this.baralho.insereFinal(new Carta("Paus", "A", 1));
        this.baralho.insereFinal(new Carta("Paus", "2", 2));
        this.baralho.insereFinal(new Carta("Paus", "3", 3));
        this.baralho.insereFinal(new Carta("Paus", "4", 4));
        this.baralho.insereFinal(new Carta("Paus", "5", 5));
        this.baralho.insereFinal(new Carta("Paus", "6", 6));
        this.baralho.insereFinal(new Carta("Paus", "7", 7));
        this.baralho.insereFinal(new Carta("Paus", "8", 8));
        this.baralho.insereFinal(new Carta("Paus", "9", 9));
        this.baralho.insereFinal(new Carta("Paus", "10", 10));
        this.baralho.insereFinal(new Carta("Paus", "J", 11));
        this.baralho.insereFinal(new Carta("Paus", "Q", 12));
        this.baralho.insereFinal(new Carta("Paus", "K", 13));

        this.baralho.insereFinal(new Carta("Copas", "A", 14));
        this.baralho.insereFinal(new Carta("Copas", "2", 15));
        this.baralho.insereFinal(new Carta("Copas", "3", 16));
        this.baralho.insereFinal(new Carta("Copas", "4", 17));
        this.baralho.insereFinal(new Carta("Copas", "5", 18));
        this.baralho.insereFinal(new Carta("Copas", "6", 19));
        this.baralho.insereFinal(new Carta("Copas", "7", 20));
        this.baralho.insereFinal(new Carta("Copas", "8", 21));
        this.baralho.insereFinal(new Carta("Copas", "9", 22));
        this.baralho.insereFinal(new Carta("Copas", "10", 23));
        this.baralho.insereFinal(new Carta("Copas", "J", 24));
        this.baralho.insereFinal(new Carta("Copas", "Q", 25));
        this.baralho.insereFinal(new Carta("Copas", "K", 26));

        this.baralho.insereFinal(new Carta("Ouros", "A", 27));
        this.baralho.insereFinal(new Carta("Ouros", "2", 28));
        this.baralho.insereFinal(new Carta("Ouros", "3", 29));
        this.baralho.insereFinal(new Carta("Ouros", "4", 30));
        this.baralho.insereFinal(new Carta("Ouros", "5", 31));
        this.baralho.insereFinal(new Carta("Ouros", "6", 32));
        this.baralho.insereFinal(new Carta("Ouros", "7", 33));
        this.baralho.insereFinal(new Carta("Ouros", "8", 34));
        this.baralho.insereFinal(new Carta("Ouros", "9", 35));
        this.baralho.insereFinal(new Carta("Ouros", "10", 36));
        this.baralho.insereFinal(new Carta("Ouros", "J", 37));
        this.baralho.insereFinal(new Carta("Ouros", "Q", 38));
        this.baralho.insereFinal(new Carta("Ouros", "K", 39));

        this.baralho.insereFinal(new Carta("Espada", "A", 40));
        this.baralho.insereFinal(new Carta("Espada", "2", 41));
        this.baralho.insereFinal(new Carta("Espada", "3", 42));
        this.baralho.insereFinal(new Carta("Espada", "4", 43));
        this.baralho.insereFinal(new Carta("Espada", "5", 44));
        this.baralho.insereFinal(new Carta("Espada", "6", 45));
        this.baralho.insereFinal(new Carta("Espada", "7", 46));
        this.baralho.insereFinal(new Carta("Espada", "8", 47));
        this.baralho.insereFinal(new Carta("Espada", "9", 48));
        this.baralho.insereFinal(new Carta("Espada", "10", 49));
        this.baralho.insereFinal(new Carta("Espada", "J", 50));
        this.baralho.insereFinal(new Carta("Espada", "Q", 51));
        this.baralho.insereFinal(new Carta("Espada", "K", 52));
    }

    public IStack embaralhar() {
        int[] array = randomizar();
        Iterador itr = baralho.iterador();
        IStack pilha = new Stack();
        for (int i = 0; i < array.length; i++) {

            Carta c = (Carta) baralho.recupera(array[i] - 1);
            if (!(c == null)) {
                pilha.push(c);
            }
        }
        return pilha;
    }

    public int[] randomizar() {
        int find = 0;
        int c, i = 0;
        int[] num = new int[52];
        Random r = new Random();
        for (i = 0; i < num.length; i++) {
            find = r.nextInt(52) + 1;
            if (i == 0) {
                num[i] = find;
            } else {
                c = 0;
                while (c < i) {
                    if (num[c] == find) {
                        find = r.nextInt(52) + 1;
                        c = 0;
                    } else {
                        c++;
                    }
                }
                num[i] = find;
            }
        }
        return num;
    }
}
