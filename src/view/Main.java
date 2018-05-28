package view;

import controller.Controller;
import java.io.IOException;
import model.*;
import util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller ctr = new Controller();
        System.out.println("Bem-Vindo ao Black Jack");
        System.out.println("");
        System.out.println("Para Iniciar o jogo Cadastre pelo menos um Jogador:");
        System.out.println("Digite um nome de usuario:");
        String nomeDeUsuario = Console.readString();
        System.out.println("Digite uma Senha para este usuario:");
        String senhaUsuario = Console.readString();
        ctr.cadastroJogadores(nomeDeUsuario, senhaUsuario);

        int repeteMenu;
        do {
            repeteMenu = 0;
            System.out.println("||================ MENU ================||");
            System.out.println("||   [1]- Cadastrar Jogador             ||");
            System.out.println("||   [2]- Iniciar Partida               ||");
            System.out.println("||   [3]- Listar Jogadores Cadastrados  ||");
            System.out.println("||   [4]- Sair                          ||");
            System.out.println("||======================================||");
            int get = Console.readInt();
            Console.clear();
            switch (get) {
                case 1:
                    System.out.println("Digite um nome de usuario:");
                    nomeDeUsuario = Console.readString();
                    System.out.println("Digite uma Senha para este usuario:");
                    senhaUsuario = Console.readString();
                    ctr.cadastroJogadores(nomeDeUsuario, senhaUsuario);
                    Console.clear();
                    repeteMenu = 1;

                    break;
                case 2:

                    ILista novaListaJogadores = new ListaEncadeada();
                    int seleciona,
                     count1 = 0;
                    boolean nenhumJogador;

                    do {
                        nenhumJogador = false;
                        boolean digitoNulo;
                        int cancela = 0;

                        do {
                            do {
                                digitoNulo = false;
                                System.out.println("Selecione at� 5 dos jogadores cadastrados que ir�o jogar");
                                System.out.println("PS :Para selecionar digite o numero correspondente do jogador");
                                System.out.println("");
                                System.out.println(count1 + " jogadores selecionados");
                                System.out.println("");
                                ctr.listarJogadoresCadastrados();
                                System.out.println("");
                                System.out.println("");
                                System.out.println("[0]- Caso deseje confirmar os jogadores j� selecionados");
                                seleciona = Console.readInt();
                                Console.clear();
                                ILista jogadores = ctr.getJogadores();

                                if (seleciona > ctr.getJogadores().tamanho()) {
                                    digitoNulo = true;
                                    System.out.println("Op��o Inv�lida!");
                                    System.out.println("Digite Novamente!");
                                }
                                if (seleciona != 0 && seleciona <= ctr.getJogadores().tamanho()) {
                                    count1++;
                                    novaListaJogadores.insereFinal(jogadores.recupera(seleciona - 1));
                                }
                            } while (digitoNulo);
                        } while (novaListaJogadores.tamanho() < 5 && seleciona != 0);

//                        Console.clear();
                        ctr.iniciarPartida(novaListaJogadores);
                        Partida partida = ctr.getPartida();
                        if (partida.getJogadores().estaVazia()) {
                            System.out.println("Voc� N�o selecionou nenhum Jogador");
                            System.out.println("Escolha pelo menos 1 para iniciar a partida!");
                            System.out.println("");
                            nenhumJogador = true;
                        }

                    } while (nenhumJogador);

                    Console.clear();
                    System.out.println("O jogo ocorre por turnos. Cada Jogador ter� a sua vez de jogar.");
                    System.out.println("");
                    boolean repeteMenuzinho;

                    Iterador itrJogadoresPartida = ctr.getPartida().getJogadores().iterador();
                    while (itrJogadoresPartida.temProximo()) {
                        repeteMenuzinho = false;

                        Jogador jogadorPartida = (Jogador) itrJogadoresPartida.proximo();
                        do {
                            System.out.println("O " + ctr.getPartida().getCroupier().getUsuario() + " tem:");
                            System.out.println(ctr.getPartida().getCroupier().getMao().getCarta(0) + " e uma carta escondida");
                            System.out.println("");
                            System.out.println("");

                            if (repeteMenuzinho == false) {
                                System.out.println(jogadorPartida.getUsuario() + ", � a sua vez,\nVoc� tem:");
                            } else if (repeteMenuzinho) {
                                System.out.println("Ainda � sua vez, " + jogadorPartida.getUsuario() + "\nVoc� tem:");
                            }
                            jogadorPartida.getMao().printaMao();
                            System.out.println("Ao total tem: " + jogadorPartida.getMao().valorMao() + " pontos");

                            System.out.println("||===========MENU============||");
                            System.out.println("||  [1]- Pegar Carta         ||");
                            System.out.println("||  [2]- Terminar Jogada     ||");
                            System.out.println("||===========================||");
                            int lerMenuzinho = Console.readInt();
                            Console.clear();
                            switch (lerMenuzinho) {
                                case 1:
                                    IStack b = ctr.getBaralho().getPilhaBaralho();
                                    ctr.puxaCarta(b, jogadorPartida);
                                    repeteMenuzinho = true;
                                    break;
                                case 2:
                                    repeteMenuzinho = false;
                                    break;
                                default:
                                    System.out.println("Op�ao Inv�lida!");
                                    System.out.println("Digite Novamente!");
                                    repeteMenuzinho = true;
                                    break;
                            }
                        } while (repeteMenuzinho);
                    }
                    ctr.regra17(); //Seguindo a regra do 17 no black jack, o croupier vai puxar cartas ate que o valor
                    //em sua mao seja menor ou igual a 17

                    ILista listaVencedores = ctr.verificarVencedor();
                    if (!listaVencedores.estaVazia()) {
                        if (listaVencedores.tamanho() == 1) {
                            MaoDeCarta maoJogador = ((Jogador) listaVencedores.recupera(0)).getMao();
                            MaoDeCarta maoCroupier = ctr.getPartida().getCroupier().getMao();
                            if (maoJogador.valorMao() == maoCroupier.valorMao()) {
                                System.out.println("O jogador " + listaVencedores.recupera(0).toString() + " empatou o jogo com o croupier.");
                                System.out.println("");
                                System.out.println("M�o do Croupier:");
                                maoCroupier.printaMao();
                                System.out.println("TOTAL:" + maoCroupier.valorMao() + " pontos.");
                                System.out.println("");
                                System.out.println("M�o do Jogador:");
                                maoJogador.printaMao();
                                System.out.println("TOTAL:" + maoJogador.valorMao() + " pontos.");
                            } else if ((maoCroupier.valorMao() < maoJogador.valorMao()) && (maoJogador.valorMao() <= 21)) {
                                System.out.println("O jogador " + listaVencedores.recupera(0).toString() + " venceu o Jogo com a M�o:");
                                maoJogador.printaMao();
                                System.out.println("TOTAL:" + maoJogador.valorMao() + " pontos.");

                                System.out.println("");
                                System.out.println("A m�o do Croupier foi:");
                                maoCroupier.printaMao();
                                System.out.println("TOTAL:" + maoCroupier.valorMao() + " pontos.");
                            } else if (maoCroupier.valorMao() > 21) {
                                System.out.println("O Croupier Estorou !");
                                System.out.println("A m�o do Croupier foi:");
                                maoCroupier.printaMao();
                                System.out.println("TOTAL:" + maoCroupier.valorMao() + " pontos.");
                                System.out.println("");

                                System.out.println("Ent�o o jogador " + listaVencedores.recupera(0).toString() + " venceu o Jogo com a M�o:");
                                maoJogador.printaMao();
                                System.out.println("TOTAL:" + maoJogador.valorMao() + " pontos.");
                            } else if ((maoCroupier.valorMao() > maoJogador.valorMao()) && (maoCroupier.valorMao() <= 21)) {
                                Iterador itr = listaVencedores.iterador();
                                while (itr.temProximo()) {
                                    Jogador j = (Jogador) itr.proximo();
                                    System.out.println(j);
                                }

                                System.out.println("O croupier Venceu a partida com a mao:");
                                maoCroupier.printaMao();
                                System.out.println("TOTAL:" + maoCroupier.valorMao() + " pontos.");
                            }
                        } else {
                            Iterador itr = listaVencedores.iterador();
                            while (itr.temProximo()) {
                                System.out.println(listaVencedores.toString());
                            }

                        }
                    } else {
                        MaoDeCarta maoCroupier = ctr.getPartida().getCroupier().getMao();
                        if (maoCroupier.valorMao() <= 21) {
                            System.out.println("O croupier Venceu a partida com a mao:");
                            maoCroupier.printaMao();
                            System.out.println("TOTAL:" + maoCroupier.valorMao() + " pontos.");
                        } else {
                            System.out.println("Todos os Jogadores,incluindo o Croupier Estouraram.");
                        }
                    }
                    boolean repeteMenuzinho2;
                    do {
                        repeteMenuzinho2 = false;
                        System.out.println("||===================MENU===================||");
                        System.out.println("||  [1]- Voltar ao Menu Principal           ||");
                        System.out.println("||  [2]- Sair                               ||");
                        System.out.println("||  [3]- Listar Cartas restantes            ||");
                        System.out.println("||  [4]- Listar Cartas restantes ordenadas  ||");
                        System.out.println("||==========================================||");
                        int lerMenuzinho2 = Console.readInt();
                        Console.clear();
                        switch (lerMenuzinho2) {
                            case 1:
                                repeteMenu = 1;
                                ctr.limparMaos();
                                break;
                            case 2:
                                System.out.println("Obrigado Por Jogar!");
                                System.out.println("Saindo...");
                                break;
                            case 3:
                                ctr.printarCartasRestantes();
                                System.out.println("");
                                System.out.println("");
                                repeteMenuzinho2 = true;
                                break;
                            case 4:
                                //Botar aqui dentro a op��o para listar as cartas ordenadas
                                ctr.ordenaBaralho();
                                repeteMenuzinho2 = true;
                                break;

                            default:
                                System.out.println("Op�ao Inv�lida!");
                                System.out.println("Digite Novamente!");
                                repeteMenuzinho2 = true;
                                break;
                        }
                    } while (repeteMenuzinho2);
                    break;
                case 3:
                    System.out.println("Os Jogadores Cadastrados s�o:");
                    ctr.listarJogadoresCadastrados();
                    System.out.println("");
                    System.out.println("Op��es:");
                    System.out.println("[0]- Sair");
                    System.out.println("[1]- Voltar ao Menu");

                    repeteMenu = Console.readInt();
                    while (repeteMenu < 0 || repeteMenu > 1) {
                        System.out.println("Op��o Inv�lida!");
                        System.out.print("Digite Novamente:");
                        repeteMenu = Console.readInt();
                    }
                    Console.clear();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;

                //FAZER CASO PARA MOSTRAR O PLACAR DOS JOGADORES CADASTRADOS
                // ORDENANDO POR MAIOR PONTUA��O,CONTENDO OS DADOS: USER, PONTUA��O TOTAL DE CADA JOGADOR E A
                // QUANTIDADE DE JOGOS VENCIDAS POR CADA JOGADOR.
                default:
                    System.out.println("Opcao Inv�lida!");
                    break;
            }
        } while (repeteMenu == 1);
    }

}
