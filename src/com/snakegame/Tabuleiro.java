package com.snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tabuleiro {
    private int largura;
    private int altura;
    private int comidaX;
    private int comidaY;

    public Tabuleiro(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        gerarNovaComida();
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getComidaX() {
        return comidaX;
    }

    public int getComidaY() {
        return comidaY;
    }

    public void gerarNovaComida() {
        Random random = new Random();
        comidaX = random.nextInt(largura);
        comidaY = random.nextInt(altura);
    }
}
