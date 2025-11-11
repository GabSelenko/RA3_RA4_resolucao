import algoritmos.BubbleSort;
import algoritmos.InsertionSort;
import algoritmos.QuickSort;
import model.Biblioteca;
import model.Jogo;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Adicionando jogos à biblioteca
        biblioteca.adicionarJogo(new Jogo(1 , "Jogo 1", "Ação", 2020));
        biblioteca.adicionarJogo(new Jogo(2 , "Jogo 3", "Aventura", 2019));
        biblioteca.adicionarJogo(new Jogo(3 , "Jogo 2", "RPG", 2021));
        biblioteca.adicionarJogo(new Jogo(4 , "Jogo 5", "Aventura", 2018));

        // Listando jogos na biblioteca
        System.out.println("\nJogos na biblioteca:");
        biblioteca.listarJogos();

        // Exportando jogos para um vetor (Nao estao orfenados)
        Jogo[] jogos = biblioteca.exportarParaVetor();

        //Removendo um jogo
        biblioteca.removerJogo(2);
        jogos = biblioteca.exportarParaVetor();     //Declarar um novo array apos cada mudanca
        System.out.println("\nJogos na biblioteca:");
        biblioteca.listarJogos();

        //Buscando um jogo
        if(biblioteca.buscarJogo(2) != null){
            System.out.println("\njogo com ID 2: " + biblioteca.buscarJogo(2));
        }
        else{
            System.out.println("\nJogo com ID 2 nao encontrado.");
        }


        //=================QUICKSORT=================

        // Ordenando jogos usando QuickSort por título
        jogos = biblioteca.exportarParaVetor(); // Recarrega o vetor desordenado
        QuickSort qs = new QuickSort();
        qs.quickSort(jogos, 0, jogos.length - 1, "titulo");
        System.out.println("\nJogos ordenados por título (QuickSort):");
        for(Jogo jogo : jogos) {
            System.out.println(jogo);
        }

        // Ordenando jogos usando QuickSort por gênero
       jogos = biblioteca.exportarParaVetor(); // Recarrega o vetor desordenado
       qs.quickSort(jogos, 0, jogos.length - 1, "genero");
       System.out.println("\nJogos ordenados por genero (QuickSort):");
       for(Jogo jogo : jogos) {
           System.out.println(jogo);
       }


        //=================BUBBLESORT=================

       jogos = biblioteca.exportarParaVetor(); // Recarrega o vetor desordenado
       BubbleSort bs = new BubbleSort();
       bs.bubbleSort(jogos, "titulo");
       System.out.println("\nJogos ordenados por título (BubbleSort):");
       for(Jogo jogo : jogos) {
           System.out.println(jogo);
       }


        //=================INSERTIONSORT=================

       jogos = biblioteca.exportarParaVetor(); // Recarrega o vetor desordenado
       InsertionSort is = new InsertionSort();
       is.insertionSort(jogos, "titulo");
       System.out.println("\nJogos ordenados por título (InsertionSort):");
       for(Jogo jogo : jogos) {
           System.out.println(jogo);
       }

    }
}
