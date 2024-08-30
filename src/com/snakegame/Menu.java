package com.snakegame;

import com.snakegame.Excecao.JogadorJaExistenteException;
import java.util.Scanner;

public class Menu {

    private GerenciadorDePontuacao gerenciadorDePontuacao;
    private Scanner scanner;

    public Menu() {
        gerenciadorDePontuacao = new GerenciadorDePontuacao();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("1. Cadastrar Jogador");
            System.out.println("2. Selecionar Jogador");
            System.out.println("3. Ver Pontuações");
            System.out.println("4. Iniciar Jogo");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (escolha) {
                case 1:
                    cadastrarJogador();
                    break;
                case 2:
                    selecionarJogador();
                    break;
                case 3:
                    gerenciadorDePontuacao.mostrarPontuacoes();
                    break;
                case 4:
                    iniciarJogo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarJogador() {
        System.out.print("Nome do Jogador: ");
        String nome = scanner.nextLine();
        try {
            gerenciadorDePontuacao.adicionarJogador(nome);
            System.out.println("Jogador " + nome + " cadastrado com sucesso.");
        } catch (JogadorJaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private Jogador selecionarJogador() {
        System.out.print("Nome do Jogador: ");
        String nome = scanner.nextLine();
        Jogador jogador = gerenciadorDePontuacao.getJogador(nome);
        if (jogador == null) {
            System.out.println("Jogador não encontrado.");
        }
        return jogador;
    }

    private void iniciarJogo() {
        Jogador jogador = selecionarJogador();
        if (jogador == null) {
            return;
        }

        System.out.println("Selecione a Dificuldade: 1. Fácil, 2. Médio, 3. Difícil");
        int escolhaDificuldade = scanner.nextInt();

        Dificuldade dificuldade = Dificuldade.FACIL;
        switch (escolhaDificuldade) {
            case 1:
                dificuldade = Dificuldade.FACIL;
                break;
            case 2:
                dificuldade = Dificuldade.MEDIO;
                break;
            case 3:
                dificuldade = Dificuldade.DIFICIL;
                break;
            default:
                System.out.println("Opção inválida. Iniciando no modo fácil.");
        }

        SnakeGame jogo = new SnakeGame(jogador, dificuldade);
        jogo.iniciar();
    }
}
