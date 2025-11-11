package model;


public class Jogo {
    private int id;
    private String titulo;
    private String genero;
    private int anoLancamento;

    public Jogo(int id, String titulo, String genero, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }


    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getGenero() {
        return genero;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
          .append(" | Título: ").append(titulo)
          .append(" | Gênero: ").append(genero)
          .append(" | Ano: ").append(anoLancamento);
        return sb.toString();
    }

}
