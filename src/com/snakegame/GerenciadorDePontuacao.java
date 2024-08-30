package com.snakegame;

import java.util.HashMap;
import java.util.Map;
import com.snakegame.Excecao.JogadorJaExistenteException;

public class GerenciadorDePontuacao {
    private Map<String, Jogador> jogadores;

    public GerenciadorDePontuacao() {
        jogadores = new HashMap<>();
    }

    public void adicionarJogador(String nome) throws JogadorJaExistenteException {
        if (jogadores.containsKey(nome)) {
            throw new JogadorJaExistenteException("Jogador já existe: " + nome);
        }
        jogadores.put(nome, new Jogador(nome));
    }

    public Jogador getJogador(String nome) {
        return jogadores.get(nome);
    }

    public void mostrarPontuacoes() {
        for (Jogador jogador : jogadores.values()) {
            System.out.println(jogador.getNome() + ": " + jogador.getPontuacao());
        }
    }
}
