package Exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe Artigo Revista.
 */
public class ArtigoRevista extends Publicacao implements Serializable {

    private String nome;
    private LocalDate data;
    private int numeroRevista;

    /**
     *Construtor da classe Artigo Revista.
     * @param titulo
     * titulo do Artigo Revista
     * @param anoPublicacao
     * ano do Artigo Revista
     * @param dimensaoAudiencia
     * dimensao da audiencia
     * @param palavraPasse
     * lista de palavra-passes
     * @param autores
     * lista de autores
     * @param resumo
     * resumo do Artigo Revista
     * @param nome
     * nome do Artigo Revista
     * @param data
     * data do Artigo Revista
     * @param numeroRevista
     * numero do Artigo Revista
     */
    public ArtigoRevista(String titulo, int anoPublicacao, int dimensaoAudiencia, List<String> palavraPasse, List<Investigador> autores, String resumo, String nome, LocalDate data, int numeroRevista) {
        super(titulo, anoPublicacao, dimensaoAudiencia, palavraPasse, autores, resumo);
        this.nome = nome;
        this.data = data;
        this.numeroRevista = numeroRevista;
    }

    /**
     * Permite obetr o nome do Artigo Revista case seja necessario.
     * @return
     * Retorna o nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite realizar o set do nome do Artigo Revista caso seja necessario.
     * @param nome
     * parametro nome do Artigo Revista.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter a data do Artigo Revista caso seja necessario.
     * @return
     * Retorna a data.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Pdermite realizar o set da data do ARtigo Revista caso seja necessario.
     * @param data
     * parametro da data do Artigo Revista.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Pemrite obter o numero da Revista caso seja necessario.
     * @return
     * Retorna o numero da Revista.
     */
    public int getNumeroRevista() {
        return numeroRevista;
    }

    /**
     * Permite realizar o set do numero da Revista caso seja necessario.
     * @param numeroRevista
     * parametro numero da Revista.
     */
    public void setNumeroRevista(int numeroRevista) {
        this.numeroRevista = numeroRevista;
    }


    /**
     * Permite obter o fator de impacto do Artigo Revista caso seja necessario.
     * @return
     * Retorna um char com o fator de impacto correspondente.
     */
    @Override
    public char fatorImpacto() {
        if (this.getDimensaoAudiencia() >= 1000) {
            return 'A';
        } else {
            if (this.getDimensaoAudiencia() >= 500) {
                return 'B';
            } else {
                return 'C';
            }
        }
    }

    /**
     * Metodo do Tipo Publicacao (Enumeracao implementada).
     * Permite distinguir o Artigo de Revista dos diversos tipos de publicacao.
     * @return
     * Retorna o tipo de publicacao.
     */
    @Override
    public TipoPublicacao getTipo() {
        return TipoPublicacao.ArtigoRevista;
    }

    /**
     * Método toString.
     * @return
     * Retorna a caraterizacao do Artigo Revista.
     */
    public String toString() {
        return super.toString() + "\n\t\tArtigo Revista: " + this.nome + " Data: " + this.data + ", Numero da Revista : " + this.numeroRevista;
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

        if (publicacao == null || publicacao.getTipo() != TipoPublicacao.ArtigoRevista) {

            return false;
        }

        ArtigoRevista artigo = (ArtigoRevista) publicacao;

        return this.getTitulo().equals(artigo.getTitulo()) && this.getNome().equals(artigo.getNome()) && this.getNumeroRevista() == artigo.getNumeroRevista();
    }


}
