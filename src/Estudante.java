package Exercicio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe Investigador Estudante, subclasse da classe Investigador.
 */
public class Estudante extends Investigador implements Serializable {

    private String tituloTese;
    private LocalDate dataFim;
    private Investigador orientador;

    /**
     * Construtor da classe Estudante.
     * @param nome
     * nome do Estudante
     * @param email
     * email do estudante
     * @param grupoInvestigacao
     * grupo de investigacao
     * @param tituloTese
     * titulo de tese do estudante
     * @param dataFim
     * data prevista de conclusão
     * @param orientador
     * invetsigador orientador
     */
    public Estudante(String nome, String email, GrupoInvestigacao grupoInvestigacao, String tituloTese, LocalDate dataFim, Investigador orientador ) {
        super(nome, email, grupoInvestigacao);

        //Permite verificar se o Investigador Orientador é Efetivo.
        if (orientador.getCategoria() != CategoriaInvestigador.EFETIVO) {

            throw new IllegalArgumentException("O orientador (" + orientador.getNome() +  ") deve ser membro efetivo.");
        }

        //Permite verificar se o Orientador do Estudante pertence ao mesmo grupo de Investigação que o Estudante.
        this.setOrientador(orientador);
        //Permite adicionar o estudante ao grupo de Investigação correspondente.
        grupoInvestigacao.addInvestigador(this);
        this.tituloTese = tituloTese;
        this.dataFim = dataFim;

    }

    /**
     * Permite obter o tiulo da tese do Estudante, caso seja necessario.
     * @return
     * Retorna o titulo da Tese.
     */
    public String getTituloTese() {
        return tituloTese;
    }

    /**
     * Permite realizar um set do titulo da tese do estudante, caso seja necessario.
     * @param tituloTese
     * parametro titulo da tese.
     */
    public void setTituloTese(String tituloTese) {
        this.tituloTese = tituloTese;
    }

    /**
     * Permite obter a data de conclusao do Estudante, caso seja necessario.
     * @return
     * Retorna a data de conclusao.
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * Permite realizar um set da data Final de conclusao, caso seja necessario.
     * @param dataFim
     * parametro data final de conclusao.
     */
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Permite obter o Investigador Orientador do Estudante.
     * @return
     * Retorna o Investigador Orientador do Estudante.
     */
    public Investigador getOrientador() {
        return orientador;
    }

    /**
     * Permite realizar um set do Investigador Orientador do Estudante.
     * @param orientador
     * parametro Investigador Orientador.
     */

    public void setOrientador(Investigador orientador) {

        //Quando e efetuado a segunda leitura do ficheiro, já tendo lido os Investigadores Efetivos, efetuamos um set
        //do Investigador Efetivo de cada Estudante para ao mesmo tempo podermos verificar se pertence ao mesmo grupo de Investigação.
        //Caso o Investigador não pertença ao mesmo grupo de Investigacao é lançada uma excepção.
        if (!super.getGrupoInvestigacao().equals(orientador.getGrupoInvestigacao())) {

            throw new IllegalArgumentException("O orientador deve pertencer ao mesmo grupo de investigação.");
        }

        this.orientador = orientador;
    }

    /**
     * Metodo da Categoria Investigador (classe Enumeracao implementada).
     * Permite distinguir a categoria Estudante dos outros tipos de categorias.
     * @return
     * Retorna a Categoria do Investigador.
     */
    @Override
    public CategoriaInvestigador getCategoria() {
        return CategoriaInvestigador.ESTUDANTE;
    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma string com a caraterização do Investigador Estudante.
     */
    public String toString(){

        return super.toString() + ", Titulo Tese : " + this.tituloTese + ", Data Fim : " + this.dataFim + ", Investigador Orientador : " + this.getOrientador();
    }
}
