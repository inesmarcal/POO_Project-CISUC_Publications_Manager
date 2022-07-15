package Exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe Artigo Conferencia.
 */
public class ArtigoConferencia extends Publicacao implements Serializable {

    private String nome;
    private LocalDate data;
    private String localConferencia;


    /**
     *Construtor da classe Artigo Conferencia.
     * @param titulo
     * titulo do Artigo Conferencia
     * @param anoPublicacao
     * ano do Artigo Conferencia
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo do Artigo Conferencia
     * @param nome
     * nome do Artigo de Conferencia
     * @param data
     * data do Artigo de Conferencia
     * @param localConferencia
     * local da Conferencia
     */
    public ArtigoConferencia(String titulo, int anoPublicacao, int dimensaoAudiencia, List<String> palavraPasse, List<Investigador> autores, String resumo, String nome, LocalDate data, String localConferencia) {
        super(titulo, anoPublicacao, dimensaoAudiencia, palavraPasse, autores, resumo);
        this.nome = nome;
        this.data = data;
        this.localConferencia = localConferencia;
    }

    /**
     * Permite obter o nome do artgo de conferencia caso seja necessario.
     * @return
     * Retorna o nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite realizar o set do nome caso seja necessario.
     * @param nome
     * parametro nome do Artigo Conferencia.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter a data do artigo de conferencia caso seja necessario.
     * @return
     * Retorna a data.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Permite realizar o set da data do artigo de conferencia caso seja necesario.
     * @param data
     * parametro data do Artigo Conferencia.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Permite obter a localizacao da Conferencia caso seja necesario.
     * @return
     * Retorna a localizacao.
     */
    public String getLocalizacaoConferencia() {
        return localConferencia;
    }

    /**
     * Permite realizar o set da localizacao da Conferencia caso seja necesario.
     * @param localConferencia
     * parametro local da Conferencia.
     */
    public void setLocalizacaoConferencia(String localConferencia) {
        this.localConferencia = localConferencia;
    }

    /**
     * Permite obter o fator de impacto do Artigo de Conferencia caso seja necessario.
     * @return
     * Retorna um char com o fator de impacto correspondente.
     */
    @Override
    public char fatorImpacto() {
        if (this.getDimensaoAudiencia() >= 500) {
            return 'A';
        } else {
            if (this.getDimensaoAudiencia() >= 200) {
                return 'B';
            } else {
                return 'C';
            }
        }
    }

    /**
     * Metodo do Tipo Publicacao (classe Enumeracao implementada).
     * Permite distinguir o Artigo de Conferencia dos diversos tipos de publicacao.
     * @return
     * Retorna o tipo de publicacao.
     */
    @Override
    public TipoPublicacao getTipo() {
        return TipoPublicacao.ArtigoConferencia;
    }

    /**
     * Método toString.
     * @return
     * Retorna a caraterizacao do Artigo Conferencia.
     */
    public String toString() {
        return super.toString() + "\n\t\tArtigo Conferencia: " + this.nome + ", Data: " + this.data + ", LocalConf: " + this.localConferencia;
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

        if (publicacao == null || publicacao.getTipo() != TipoPublicacao.ArtigoConferencia) {

            return false;
        }

        ArtigoConferencia artigo = (ArtigoConferencia) publicacao;

        return this.getTitulo().equals(artigo.getTitulo()) && this.getNome().equals(artigo.getNome());
    }

}


