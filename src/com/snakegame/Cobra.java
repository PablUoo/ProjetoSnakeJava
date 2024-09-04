package com.snakegame;

import java.util.LinkedList;

public class Cobra {
    private LinkedList<int[]> corpo;
    private Direcao direcao;

    public Cobra(int xInicial, int yInicial) {
        corpo = new LinkedList<>();
        corpo.add(new int[]{xInicial, yInicial});
        direcao = Direcao.CIMA;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public void mover() {
        int[] cabeca = corpo.getFirst();
        int novoX = cabeca[0];
        int novoY = cabeca[1];

        switch (direcao) {
            case CIMA:
                novoY--;
                break;
            case BAIXO:
                novoY++;
                break;
            case ESQUERDA:
                novoX--;
                break;
            case DIREITA:
                novoX++;
                break;
        }

        corpo.addFirst(new int[]{novoX, novoY});
        corpo.removeLast();
    }

    public void crescer() {
        corpo.addLast(corpo.getLast());
    }

    public boolean estaNaPosicao(int x, int y) {
        for (int[] segmento : corpo) {
            if (segmento[0] == x && segmento[1] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean colidiuComParede(Tabuleiro tabuleiro) {
        int[] cabeca = corpo.getFirst();
        return cabeca[0] < 0 || cabeca[0] >= tabuleiro.getLargura() || cabeca[1] < 0 || cabeca[1] >= tabuleiro.getAltura();
    }

    public boolean colidiuComCorpo() {
        int[] cabeca = corpo.getFirst();
        for (int i = 1; i < corpo.size(); i++) {
            if (cabeca[0] == corpo.get(i)[0] && cabeca[1] == corpo.get(i)[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean comeuComida(Tabuleiro tabuleiro) {
        int[] cabeca = corpo.getFirst();
        return cabeca[0] == tabuleiro.getComidaX() && cabeca[1] == tabuleiro.getComidaY();
    }

}
