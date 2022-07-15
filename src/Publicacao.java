package Exercicio;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Publicacao.
 */
public abstract class Publicacao implements Serializable {


    private List<Investigador> autores;
    private String titulo;
    private List<String> palavraPasse;
    private int anoPublicacao;
    private int dimensaoAudiencia;
    private String resumo;

    /**
     * Construtor da Classe Publicacao.
     * @param titulo
     * titulo da publicacao
     * @param anoPublicacao
     * ano da publicacao
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo da publicacao
     */
    public Publicacao(String titulo, int anoPublicacao, int dimensaoAudiencia, List<String> palavraPasse, List<Investigador> autores, String resumo) {

        this.autores = autores;
        this.titulo = titulo;
        this.palavraPasse = palavraPasse;
        this.anoPublicacao = anoPublicacao;
        this.dimensaoAudiencia = dimensaoAudiencia;
        this.resumo = resumo;

        //Permite que a publicação seja adicionada ao grupo e investigador respetivos.
        for (Investigador investigador : autores) {

            investigador.getGrupoInvestigacao().addPublicacoes(this);
            investigador.addPublicacao(this);
        }
    }

    /**
     * Permite obter a lista de autores, caso se necessario.
     * @return
     * retorna a lista de autores.
     */
    public List<Investigador> getAutores() {
        return autores;
    }

    /**
     * Permite obter a lista de autores da publicacao, caso seja necessario.
     * @param autores
     * parametro Lista de autores.
     */
    public void setAutores(List<Investigador> autores) {
        this.autores = autores;
    }

    /**
     * Permite obter o resumo da publicacao, caso seja necessario.
     * @return
     * Retorna o resumo da publicacao.
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * Permite efetuar o set do Resumo da publicacao, caso seja necessario.
     * @param resumo
     * parametro resumo da publicacao.
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     * Permite adicionar autores, caso se necessario.
     * @param investigador
     * parametro Investigador que queremos adicionar.
     */
    public void addAutores(Investigador investigador){
        this.autores.add(investigador);
    }

    /**
     * Permite adcionar uma palavra-passe, caso se necessario.
     * @param palavraPasse
     * parametro Palabra-Passe
     */
    public void addPalavraPasses(String palavraPasse){
        this.palavraPasse.add(palavraPasse);
    }

    /**
     * Permite obter o titulo da publicacao, caso se necessario.
     * @return
     * Retorna o titulo da publicacao.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Permite efetuar o set do titulo da publicacao, caso se necessario.
     * @param titulo
     * parametro titulo da publicacao.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Permite obter a lista de palavra-passes, caso se necessario.
     * @return
     * Retorna a lista de palavra-passes.
     */
    public List<String> getPalavraPasse() {
        return palavraPasse;
    }

    /**
     * Permite efetuar o set da lista de palavra-passes, caso se necessario.
     * @param palavraPasse
     * parametro lista de palavra-passes.
     */
    public void setPalavraPasse(List<String> palavraPasse) {
        this.palavraPasse = palavraPasse;
    }

    /**
     * Permite obter o ano da publicacao.
     * @return
     * Retorna o ano da publicacao.
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * Permite eftuar o set do ano de publicacao.
     * @param anoPublicacao
     * parametro ano de publicacao
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Permite obter a dimensao da audiencia.
     * @return
     * Retorna a dimensao da audiencia.
     */
    public int getDimensaoAudiencia() {
        return dimensaoAudiencia;
    }

    /**
     * Permiete efetuar o set da dimensao da audiencia
     * @param dimensaoAudiencia
     * parametro dimensao da audiencia
     */
    public void setDimensaoAudiencia(int dimensaoAudiencia) {
        this.dimensaoAudiencia = dimensaoAudiencia;
    }


    /**
     * Permite que todos as subclasses da Publicacao apresentem este metodo.
     * @return
     * Retorna um char que carateriza o fator de impacto.
     */
    public abstract char fatorImpacto();

    /**
     * Permite que todas as subclasses da Publicacao apresentem este metodo para as distinguir.
     * @return
     * Retorna o correspondente tipo de Publicacao.
     */
    public abstract TipoPublicacao getTipo();

    /**
     * Metodo toString.
     * @return
     * Retorna uma string com a caraterizacao da publicacao.
     */
    public String toString(){

        String s = "\n\t\tAutores:\n";

        for (Investigador autor : this.autores){

            String nome = "";
            String[] nomes = autor.getNome().split(" ");

            if (autor.getCategoria() == CategoriaInvestigador.EFETIVO) {
                nome = "Professor " + nomes[0] + " " + nomes[nomes.length-1];
            } else {
                nome = nomes[0].charAt(0) + ". " + nomes[nomes.length-1];
            }

            s += "\t\t\t" + nome + "\n";
        }

        s += "\t\tPalavra-Chave:\n";

        for (String palavraPasse : this.palavraPasse){
            s += "\t\t\t" + palavraPasse + "\n";
        }

        return s + "\t\tTitulo: " + this.titulo + "\n\t\tAno de Pub: "  + this.anoPublicacao
                + "\n\t\tFator Impacto: " + this.fatorImpacto() + "\n\t\tResumo: " + this.resumo;
    }

    /**
     * Permite que todas as subclasses da Publicacao apresentem este metodo, que é utilizado para comparar
     * as diversas publicacoes (assim o ficheiro nao apresenta duas publicacoes repetidas).
     * @param publicacao
     * parametro publicacao
     * @return
     * Retorna um booleano que podera posteriormente ser utilizado para verificar se se trata da mesma publicacao ou de uma diferente.
     */
    public abstract boolean equals(Publicacao publicacao);
}
