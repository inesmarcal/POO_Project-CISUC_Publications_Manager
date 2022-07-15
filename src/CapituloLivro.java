package Exercicio;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Capitulo Livro, subclasse da classe Tipo Livro.
 */
public class CapituloLivro extends Livro implements Serializable {

    private String nomeCapitulo;
    private int pagInicio, pagFim;

    /**
     * Construtor da classe Capitulo Livro.
     * @param titulo
     * titulo do Capitulo Livro
     * @param anoPublicacao
     * ano de publicacao
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param editora
     * editora do Capitulo Livro
     * @param ISBN
     * ISBN do Capitulo Livro
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo do Capitulo Livro
     * @param nomeCapitulo
     * nome do Capitulo
     * @param pagFim
     * pagina final do Capitulo
     * @param pagInicio
     * pagina inicial do Capitulo
     */
    public CapituloLivro(String titulo, int anoPublicacao, int dimensaoAudiencia, String editora, String ISBN, List<String> palavraPasse, List<Investigador> autores, String resumo, String nomeCapitulo, int pagFim, int pagInicio) {
        super(titulo, anoPublicacao, dimensaoAudiencia, editora, ISBN, palavraPasse, autores, resumo);
        this.nomeCapitulo = nomeCapitulo;
        this.pagInicio = pagInicio;
        this.pagFim = pagFim;
    }

    /**
     * Permite obter o nome do capitulo.
     * @return
     * Retorna o nome do capitulo.
     */
    public String getNomeCapitulo() {
        return nomeCapitulo;
    }

    /**
     * Permite realizar o set do nome do capitulo.
     * @param nomeCapitulo
     * parametro nome do Capitulo.
     */
    public void setNomeCapitulo(String nomeCapitulo) {
        this.nomeCapitulo = nomeCapitulo;
    }

    /**
     * Permite obter a pagina do numero da pagina de Incio do Capitulo.
     * @return
     * Retorna o numero da pagina Incial.
     */
    public int getPagInicio() {
        return pagInicio;
    }

    /**
     * Permite realizar o set do numero da pagina Incial do Capitulo.
     * @param pagInicio
     * parametro pagina Incio do Capitulo.
     */
    public void setPagInicio(int pagInicio) {
        this.pagInicio = pagInicio;
    }

    /**
     * Permite obter o numero da pagina Final do Capitulo.
     * @return
     * Retorna o numero da pagina Final do Capitulo.
     */
    public int getPagFim() {
        return pagFim;
    }

    /**
     * Permite realizar o set do numero da pagina Final do Capitulo.
     * @param pagFim
     * parametro numero pagina Final de Capitulo.
     */
    public void setPagFim(int pagFim) {
        this.pagFim = pagFim;
    }


    /**
     * Permite obter o fator de impacto do Capitulo Livro caso seja necessario.
     * @return
     * Retorna um char com o fator de impacto correspondente.
     */
    @Override
    public char fatorImpacto() {
        if (this.getDimensaoAudiencia() >= 10000) {
            return 'A';
        } else {
            if (this.getDimensaoAudiencia() >= 5000) {
                return 'B';
            } else {
                return 'C';
            }
        }
    }

    /**
     * Metodo do Tipo Publicacao (classe Enumeracao implementada).
     * Permite distinguir o Capitulo Livro dos diversos tipos de publicacao.
     * @return
     * Retorna o tipo de publicacao.
     */
    @Override
    public TipoPublicacao getTipo() {
        return TipoPublicacao.CapituloLivro;
    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma string com toda a caraterizacao do Capitulo Livro.
     */
    public String toString() {
        return super.toString() + "\n\t\tCapitulo Livro: " + this.nomeCapitulo + ", Pagina Inicio : " + this.pagInicio + ", Pagina Fim : " + this.pagFim;
    }


    /**
     * Metodo que ira permitir comparar as diversas publicacoes(usado para o ficheiro nao apresentar duas publicacoes repetidas).
     * @param publicacao
     * Parametro publicacao.
     * @return
     * Retorna um booleano que podera posteriormente ser utilizado para verificar se se trata da mesma publicacao ou de uma diferente.
     */
    @Override
    public boolean equals(Publicacao publicacao) {

        if (publicacao == null || publicacao.getTipo() != TipoPublicacao.CapituloLivro) {

            return false;
        }

        CapituloLivro livro = (CapituloLivro) publicacao;

        return this.getISBN().equals(livro.getISBN());
    }
}

