package com.snakegame;

public enum Dificuldade {
    FACIL(1), MEDIO(2), DIFICIL(3);

    private int pontosPorComida;

    Dificuldade(int pontosPorComida) {
        this.pontosPorComida = pontosPorComida;
    }

    public int getPontosPorComida() {
        return pontosPorComida;
    }
}
