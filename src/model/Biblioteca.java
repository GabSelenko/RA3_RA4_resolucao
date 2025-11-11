package model;

import java.util.Hashtable;

public class Biblioteca {
    private Hashtable<Integer, Jogo> jogos;

    public Biblioteca() {
        this.jogos = new Hashtable<>();
    }


    public void adicionarJogo(Jogo jogo) {
        jogos.put(jogo.getId(), jogo);
    }

    public Jogo buscarJogo(int id) {
        if(jogos.get(id) == null) {
            return null;
        }
        return jogos.get(id);
    }
    public void removerJogo(int id) {
        jogos.remove(id);
    }
    public int getTamanho() {
        return jogos.size();
    }


    // Lista todos os jogos na biblioteca (Na ordem da hashtable)
    public void listarJogos() {
        for (int key : jogos.keySet()) {
            System.out.println(jogos.get(key));
        }
    }


    // Exporta os jogos na ordem da Hashtable para um vetor
    public Jogo[] exportarParaVetor() {
        Jogo[] vetor = new Jogo[jogos.size()];
        int indice = 0;
        for (Jogo jogo : jogos.values()) {
            vetor[indice++] = jogo;
        }
        return vetor;
    }

}
