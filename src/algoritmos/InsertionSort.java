package algoritmos;

import model.Jogo;

public class InsertionSort {

    // (ARRAY DESORDENADO, CRITERIO DE ORDENACAO)
    public void insertionSort(Jogo[] array, String criterio) {
        int n = array.length;
        // Começa do segundo elemento, pois o primeiro já está "ordenado"
        for (int i = 1; i < n; ++i) {
            Jogo key = array[i];
            int j = i - 1;

            // Move os elementos do array[0..i-1] que são maiores que a chave
            // para uma posição à frente de sua posição atual
            while (j >= 0 && comparar(array[j], key, criterio) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    private int comparar(Jogo jogo1, Jogo jogo2, String criterio) {
        switch (criterio.toLowerCase()) {
            case "titulo":
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());
            case "genero":
                return jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero());
            case "ano":
                return Integer.compare(jogo1.getAnoLancamento(), jogo2.getAnoLancamento());
            case "id":
                return Integer.compare(jogo1.getId(), jogo2.getId());
            default:
                // Critério padrão é título
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());
        }
    }
}
