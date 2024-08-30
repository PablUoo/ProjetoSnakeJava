package com.snakegame;

public abstract class Jogo {
    protected Jogador jogador;
    protected Dificuldade dificuldade;

    public Jogo(Jogador jogador, Dificuldade dificuldade) {
        this.jogador = jogador;
        this.dificuldade = dificuldade;
    }

    public abstract void iniciar();

    protected void adicionarPontos(int pontos) {
        jogador.adicionarPontuacao(pontos);
    }
}
