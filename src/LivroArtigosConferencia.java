package Exercicio;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Livro Artigo Conferencia, subclasse da Classe Livro.
 */
public class LivroArtigosConferencia extends Livro implements Serializable {

    private String nomeConferencia;
    private int fimCapitulo;
    private int inicioCapitulo;

    /**
     * Construtor do Livro Artigo Conferencia.
     * @param titulo
     * titulo do Livro Artigo Conferencia.
     * @param anoPublicacao
     * ano de Publicacao do Livro Artigo Conferencia.
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param editora
     * editora do Livro Artigo Conferencia
     * @param ISBN
     * ISBN do Livro Artigo Conferencia
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo do Livro Artigo Conferencia
     * @param nomeConferencia
     * nome da Conferencia
     * @param fimCapitulo
     * pagina final do Capitulo
     * @param inicioCapitulo
     * pagina inicail do Capitulo
     */
    public LivroArtigosConferencia(String titulo, int anoPublicacao, int dimensaoAudiencia, String editora, String ISBN,  List<String> palavraPasse, List<Investigador> autores, String resumo, String nomeConferencia, int fimCapitulo, int inicioCapitulo) {
        super(titulo, anoPublicacao, dimensaoAudiencia, editora, ISBN, palavraPasse, autores, resumo);
        this.fimCapitulo = fimCapitulo;
        this.inicioCapitulo = inicioCapitulo;
        this.nomeConferencia = nomeConferencia;
    }

    /**
     * Permite obter o nome do Livro Artigo Conferencia, caso necessario.
     * @return
     * Retorna o nome do Livro Artigo Conferencia.
     */
    public String getNomeConferencia() {
        return nomeConferencia;
    }

    /**
     * Permite efetuar um set do nome do Livro Artigo Conferencia, caso necessario.
     * @param nomeConferencia
     * parametro nome do Livro Artigo Conferencia
     */
    public void setNomeConferencia(String nomeConferencia) {
        this.nomeConferencia = nomeConferencia;
    }

    /**
     * Permite obter o numero da pagina Final do capitulo, caso seja necessario.
     * @return
     * Retorna o numero da pagina Final.
     */
    public int getFimCapitulo() {
        return fimCapitulo;
    }

    /**
     * Permite efetuar um set do numero da pagina final do capitulo, caso seja necessario.
     * @param fimCapitulo
     * parametro numero da pagina Final do Capitulo.
     */
    public void setFimCapitulo(int fimCapitulo) {
        this.fimCapitulo = fimCapitulo;
    }

    /**
     * Permite obter o numero da pagina do inicio do capitulo, caso seja necessario.
     * @return
     * Retorna o numero da pagina do fim  do capitulo.
     */
    public int getInicioCapitulo() {
        return inicioCapitulo;
    }

    /**
     * Permite efetuar um set do numero da pagina do inicio do capitulo, caso seja necessario.
     * @param inicioCapitulo
     * parametro numero da pagina Inicial do Capitulo
     */
    public void setInicioCapitulo(int inicioCapitulo) {
        this.inicioCapitulo = inicioCapitulo;
    }

    /**
     * Permite obter o fator de impacto do Livro Artigo Conferencia caso seja necessario.
     * @return
     * Retorna um char com o fator de impacto correspondente.
     */
    @Override
    public char fatorImpacto() {
        if (this.getDimensaoAudiencia() >= 7500){
            return 'A';
        }else {
            if (this.getDimensaoAudiencia() >= 2500) {
                return 'B';
            } else {
                return 'C';
            }
        }
    }

    /**
     * Metodo do Tipo Publicacao (classe Enumeracao implementada).
     * Permite distinguir o Livro Artigo Conferencia dos diversos tipos de publicacao.
     * @return
     * Retorna o tipo de publicacao.
     */
    @Override
    public TipoPublicacao getTipo() {

        return TipoPublicacao.LivroArtigosConferencia;
    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma string com toda a caraterizacao do Livro Artigo Conferencia.
     */
    public String toString() {
        return super.toString() + "\n\t\tLivro Artigo Conferencia " + this.nomeConferencia + ", Inicio Capitulo: " + this.inicioCapitulo + ", Fim Capitulo : " + this.fimCapitulo;
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

        if (publicacao == null || publicacao.getTipo() != TipoPublicacao.LivroArtigosConferencia) {

            return false;
        }

        LivroArtigosConferencia livro = (LivroArtigosConferencia) publicacao;

        return this.getISBN().equals(livro.getISBN());
    }

}
