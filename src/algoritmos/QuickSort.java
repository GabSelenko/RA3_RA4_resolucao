package algoritmos;
import model.Jogo;


public class QuickSort {

    // (ARRAY DESORDENADO, INDICE INICIAL, INDICE FINAL, CRITERIO DE ORDENACAO)
    public void quickSort(Jogo[] array, int start, int end, String criterio) {
        //Condicao de parada
        if (start < end) {

            // "PONTEIRO"
            int pivot = partition(array, start, end, criterio);

            //Recursividade para ordenar as particoes
            quickSort(array, start, pivot - 1, criterio);
            quickSort(array, pivot + 1, end, criterio);
        }
    }


    private int partition(Jogo[] arr, int first, int last, String criterio) {
        Jogo pivot = arr[last];
        int i = (first - 1);

        //Roda ate o J chegar no ultimo elemento
        for (int j = first; j < last; j++) {
            if (comparar(arr[j], pivot, criterio) <= 0) {
                i++;

                //Trocando J por I
                Jogo temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //Separa o array em duas particoes
        Jogo temp = arr[i + 1];
        arr[i + 1] = arr[last];
        arr[last] = temp;

        //Retorna o indice do pivot
        return i + 1;
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
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());
        }
    }

}
