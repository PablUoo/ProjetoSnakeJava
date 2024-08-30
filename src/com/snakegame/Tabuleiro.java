package com.snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tabuleiro extends JPanel {
    //teste abaixo
//    private int largurajpanel = 1000;
//    private int alturajpanel = 500;
    //teste acima
    private int largura;
    private int altura;
    private int comidaX;
    private int comidaY;

//    ConfigsTela(){
//        random = new Random();
//        setPreferredSize(new Dimension(largurajpanel, alturajpanel));
//        setBackground(Color.darkGray);
//    }


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

    public void mostrar(Cobra cobra) {
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (cobra.estaNaPosicao(x, y)) {
                    System.out.print("0");
                } else if (x == comidaX && y == comidaY) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
