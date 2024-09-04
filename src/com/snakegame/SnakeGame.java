package com.snakegame;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends Jogo {
    private Tabuleiro tabuleiro;
    private Cobra cobra;
    private boolean jogoAtivo;
    private boolean movimentoIniciado;
    private JFrame frame;
    private JPanel painelTabuleiro;

    public SnakeGame(Jogador jogador, Dificuldade dificuldade) {
        super(jogador, dificuldade);
        this.tabuleiro = new Tabuleiro(40, 20);
        this.cobra = new Cobra(tabuleiro.getLargura() / 2, tabuleiro.getAltura() / 2);
        this.jogoAtivo = true;
        this.movimentoIniciado = false;

        this.frame = new JFrame("Snake Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(820, 440);
        this.frame.setResizable(false);

        this.painelTabuleiro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharTabuleiro(g);
            }
        };

        this.painelTabuleiro.setBackground(Color.BLACK);
        this.frame.add(painelTabuleiro);
        this.frame.setVisible(true);

        this.painelTabuleiro.requestFocusInWindow();
        processarEntrada();
    }

    @Override
    public void iniciar() {
        System.out.println("Iniciando Jogo da Cobra para " + jogador.getNome() + " na dificuldade " + dificuldade + ".");

        while (jogoAtivo) {
            if (movimentoIniciado) {
                painelTabuleiro.repaint();
                cobra.mover();

                if (cobra.colidiuComParede(tabuleiro) || cobra.colidiuComCorpo()) {
                    jogoAtivo = false;
                    JOptionPane.showMessageDialog(frame, "Game Over! Pontuação final: " + jogador.getPontuacao());
                    frame.dispose();
                }

                if (cobra.comeuComida(tabuleiro)) {
                    adicionarPontos(dificuldade.getPontosPorComida());
                    cobra.crescer();
                    tabuleiro.gerarNovaComida();
                }

                try {
                    Thread.sleep(400 / dificuldade.getPontosPorComida());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void processarEntrada() {
        painelTabuleiro.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (!movimentoIniciado) {
                    movimentoIniciado = true;
                }
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_W:
                        cobra.setDirecao(Direcao.CIMA);
                        break;
                    case java.awt.event.KeyEvent.VK_A:
                        cobra.setDirecao(Direcao.ESQUERDA);
                        break;
                    case java.awt.event.KeyEvent.VK_S:
                        cobra.setDirecao(Direcao.BAIXO);
                        break;
                    case java.awt.event.KeyEvent.VK_D:
                        cobra.setDirecao(Direcao.DIREITA);
                        break;
                }
            }
        });
        painelTabuleiro.setFocusable(true);
    }

    private void desenharTabuleiro(Graphics g) {
        int tileSize = 20;
        int largura = tabuleiro.getLargura();
        int altura = tabuleiro.getAltura();

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, largura * tileSize, altura * tileSize);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (cobra.estaNaPosicao(x, y)) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                } else if (x == tabuleiro.getComidaX() && y == tabuleiro.getComidaY()) {
                    g.setColor(Color.RED);
                    g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }
}
