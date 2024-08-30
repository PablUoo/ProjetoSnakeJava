package com.snakegame;

import java.util.Scanner;

public class SnakeGame extends Jogo {
    private Tabuleiro tabuleiro;
    private Cobra cobra;
    private boolean jogoAtivo;
    private Scanner scanner;

    public SnakeGame(Jogador jogador, Dificuldade dificuldade) {
        super(jogador, dificuldade);
        this.tabuleiro = new Tabuleiro(20, 10);
        this.cobra = new Cobra(tabuleiro.getLargura() / 2, tabuleiro.getAltura() / 2);
        this.jogoAtivo = true;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void iniciar() {
        System.out.println("Iniciando Jogo da Cobra para " + jogador.getNome() + " na dificuldade " + dificuldade + ".");

        while (jogoAtivo) {
            tabuleiro.mostrar(cobra);
            processarEntrada();
            cobra.mover();

            if (cobra.colidiuComParede(tabuleiro) || cobra.colidiuComCorpo()) {
                jogoAtivo = false;
                System.out.println("Game Over! Pontuação final: " + jogador.getPontuacao());
            }

            if (cobra.comeuComida(tabuleiro)) {
                adicionarPontos(dificuldade.getPontosPorComida());
                cobra.crescer();
                tabuleiro.gerarNovaComida();
            }

            try {
                Thread.sleep(200 / dificuldade.getPontosPorComida());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processarEntrada() {
        String direcao = scanner.nextLine().toUpperCase();

        switch (direcao) {
            case "W":
                cobra.setDirecao(Direcao.CIMA);
                break;
            case "A":
                cobra.setDirecao(Direcao.ESQUERDA);
                break;
            case "S":
                cobra.setDirecao(Direcao.BAIXO);
                break;
            case "D":
                cobra.setDirecao(Direcao.DIREITA);
                break;
        }
    }
}
