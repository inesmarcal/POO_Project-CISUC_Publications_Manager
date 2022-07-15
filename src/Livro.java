package Exercicio;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Livro.
 */
public class Livro extends Publicacao implements Serializable {

    private String editora;
    private String ISBN;

    /**
     * Construtor da Classe Livro.
     * @param titulo
     * titulo do livro
     * @param anoPublicacao
     * ano de Publicacao do Livro
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param editora
     * editora do livro
     * @param ISBN
     * ISBN do livro
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo do livro
     */
    public Livro(String titulo, int anoPublicacao, int dimensaoAudiencia, String editora, String ISBN,  List<String> palavraPasse, List<Investigador> autores, String resumo) {
        super(titulo, anoPublicacao, dimensaoAudiencia, palavraPasse, autores, resumo);
        this.editora = editora;
        this.ISBN = ISBN;

    }

    /**
     * Permite obter a editora do Livro.
     * @return
     * Retorna a ediora.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Permite efetuar um ser da Editora do Livro.
     * @param editora
     * parametro Editora.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Permite obter o ISBN do Livro.
     * @return
     * Retorna o ISBN.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Permite efetuar um set do ISBN do Livro.
     * @param ISBN
     * parametro ISBN.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Permite obter o fator de impacto do Livro caso seja necessario.
     * @return
     * Retorna um char com o fator de impacto correspondente.
     */
    @Override
    public char fatorImpacto() {
        if (this.getDimensaoAudiencia() >= 10000){
            return 'A';
        }else {
            if (this.getDimensaoAudiencia() >= 5000) {
                return 'B';
            } else {
                return 'C';
            }
        }
    }

    /**
     * Metodo do Tipo Publicacao (classe Enumeracao implementada).
     * Permite distinguir o Livro dos diversos tipos de publicacao.
     * @return
     * Retorna o tipo de publicacao.
     */
    @Override
    public TipoPublicacao getTipo() {
        return TipoPublicacao.Livro;
    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma string com toda a caraterizacao do Livro.
     */
    public String toString() {
        return super.toString() + "\n\t\tLivro: Editora: " + this.editora + ", ISBN: " + this.ISBN;
    }

    /**
     * Metodo que ira permitir comparar as diversas publicacoes(usado para o ficheiro nao apresentar duas publicacoes repetidas).
     * @param publicacao
     * Parametro publicacao.
     * @return
     * Retorna um booleano que podera posteriormente ser utilizado para verificar se se trata da mesma publicacao ou de uma diferente.
     */
    @Override
    public boolean equals(Publicacao publicacao)
    {

        if (publicacao == null || publicacao.getTipo() != TipoPublicacao.Livro) {

            return false;
        }

        Livro livro = (Livro) publicacao;

        return this.ISBN.equals(livro.getISBN());
    }

}
