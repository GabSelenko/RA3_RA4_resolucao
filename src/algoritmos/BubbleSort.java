package algoritmos;

import model.Jogo;

public class BubbleSort {

    // (ARRAY DESORDENADO, CRITERIO DE ORDENACAO)
    public void bubbleSort(Jogo[] array, String criterio) {
        int n = array.length;
        // Loop para percorrer o array
        for (int i = 0; i < n - 1; i++) {
            // Loop para comparar elementos adjacentes
            for (int j = 0; j < n - i - 1; j++) {
                // Compara e troca se o elemento atual for maior que o próximo
                if (comparar(array[j], array[j + 1], criterio) > 0) {
                    // Troca
                    Jogo temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
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
