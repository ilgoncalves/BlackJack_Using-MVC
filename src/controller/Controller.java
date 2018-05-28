package controller;

import model.*;
import util.*;

public class Controller {

    private ILista jogadores = new ListaEncadeada();
    private Baralho baralho;
    private Partida partida;
    private IStack pilhaBaralhoOficial;

    public void cadastroJogadores(String usuario, String senha) {
        Jogador j = new Jogador(usuario, senha);
        this.jogadores.insereFinal(j);
    }

    public void iniciarPartida(ILista listaJogadores) {
        this.baralho = new Baralho();
        partida = new Partida(listaJogadores, baralho);
        pilhaBaralhoOficial = baralho.getPilhaBaralho();

        puxaCarta(pilhaBaralhoOficial, partida.getCroupier());
        puxaCarta(pilhaBaralhoOficial, partida.getCroupier());
        Iterador itr = listaJogadores.iterador();
        while (itr.temProximo()) {
            Jogador jogador = (Jogador) itr.proximo();
            puxaCarta(pilhaBaralhoOficial, jogador);
            puxaCarta(pilhaBaralhoOficial, jogador);
        }
    }

    public void puxaCartasCroupier() {
        partida.getCroupier().getMao().adicionaCarta((Carta) pilhaBaralhoOficial.pop());
    }

    public void regra17() {
        while (partida.getCroupier().getMao().valorMao() < 17) {
            puxaCartasCroupier();
        }
    }

    public void printaCartasJogadores() {
        Iterador itrJogador = partida.getJogadores().iterador();

        while (itrJogador.temProximo()) {
            Jogador jogador = (Jogador) itrJogador.proximo();
            System.out.println(jogador.getUsuario() + ", você tem :");
            jogador.getMao().printaMao();
        }
    }

    public ILista verificarVencedor() {
        Iterador itr = partida.getJogadores().iterador();
        Croupier croupier = partida.getCroupier();
        ILista listaEmpate = new ListaEncadeada();
        Jogador jogadorMaiorValor = (Jogador) partida.getJogadores().recupera(0);

        while (itr.temProximo()) {
            Jogador jogador = (Jogador) itr.proximo();
            if ((jogador.getMao().valorMao() > jogadorMaiorValor.getMao().valorMao()) && jogador.getMao().valorMao() <= 21) {
                jogadorMaiorValor = jogador;
            }
        }
        itr = partida.getJogadores().iterador();
        while (itr.temProximo()) {
            Jogador jogador = (Jogador) itr.proximo();
            if ((jogador.getMao().valorMao() == jogadorMaiorValor.getMao().valorMao()) && jogador.getMao().valorMao() <= 21) {
                listaEmpate.insereFinal(jogador);
            }
        }
        return listaEmpate;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public Partida getPartida() {
        return partida;
    }

    public ILista getJogadores() {
        return jogadores;
    }

    public void setJogadores(ILista jogadores) {
        this.jogadores = jogadores;
    }

    public void listarJogadoresCadastrados() {
        Iterador itr = jogadores.iterador();
        int count = 1;
        while (itr.temProximo()) {
            System.out.println("[" + count + "]-" + itr.proximo().toString());
            count++;
        }
    }

    public void puxaCarta(IStack baralho, Jogador j) {
        j.getMao().adicionaCarta((Carta) baralho.pop());
    }

    public void printarCartasRestantes() {
        Carta[] cartasRestantes = this.barallhoPilhaParaArray();
        this.barallhoPilhaParaArray();//Gambiarra para inverte o baralho novamente
        int i;
        for (i = 0; i < cartasRestantes.length; i++) {
            System.out.println(cartasRestantes[i].toString());
        }
        System.out.println("Cartas Restantes: " + i);
    }

    public void ordenaBaralho() {
        Carta[] cartasRestantes = this.barallhoPilhaParaArray();
        this.barallhoPilhaParaArray();//Gambiarra para inverte o baralho novamente
        int i;
        for (i = 0; i < cartasRestantes.length; i++) {
            System.out.println("ordenado: " + cartasRestantes[i].toString());
        }
        System.out.println("Cartas Restantes: " + i);

    }

    public Carta[] barallhoPilhaParaArray() {

        int tamanhoBaralho = pilhaBaralhoOficial.getSize();
        Carta[] arrayCarta = new Carta[tamanhoBaralho];
        for (int i = 0; i < tamanhoBaralho; i++) {
            arrayCarta[i] = (Carta) this.pilhaBaralhoOficial.pop();
        }

        for (Carta c : arrayCarta) {
            this.pilhaBaralhoOficial.push(c);
        }
        return arrayCarta;
    }

    public void limparMaos() {
        Iterador itr = partida.getJogadores().iterador();
        while (itr.temProximo()) {
            Jogador jogador = (Jogador) itr.proximo();
            jogador.setMao(new MaoDeCarta());
        }
        partida.getCroupier().setMao(new MaoDeCarta());
    }
}
