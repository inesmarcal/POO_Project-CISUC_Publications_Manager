package Exercicio;

import java.io.Serializable;

/**
 * Classe Investigador Efetivo, subclasse da classe Investigador.
 */
public class Efetivo extends Investigador implements Serializable {

    private String numeroGabinete;
    private int numeroTelefone;

    /**
     * Construtor da classe do Investigador Efetivo.
     * @param nome
     * nome do efetivo
     * @param email
     * email do efetivo
     * @param grupoInvestigacao
     * grupo de investigacao
     * @param numeroGabinete
     * numero de gabinete do efetivo
     * @param numeroTelefone
     * numero de telefone do efetivo
     */
    public Efetivo(String nome, String email, GrupoInvestigacao grupoInvestigacao, String numeroGabinete, int numeroTelefone) {

        super(nome, email, grupoInvestigacao);
        this.numeroGabinete = numeroGabinete;
        this.numeroTelefone = numeroTelefone;
        //Adiciona um investigador ao grupo de Investigacao correspondente.
        grupoInvestigacao.addInvestigador(this);

    }

    /**
     * Permite obter o numero de gabinete do investigador Efetivo caso seja necessario.
     * @return
     * retorna uma string com o numero do Gabinete.
     */
    public String getNumeroGabinete() {
        return numeroGabinete;
    }

    /**
     * Permite realizar um set do numero de gabinete do investigador Efetivo, caso seja necessario.
     * @param numeroGabinete
     * parametro numero de Gabinete
     */
    public void setNumeroGabinete(String numeroGabinete) {
        this.numeroGabinete = numeroGabinete;
    }

    /**
     * Permite obter o numero de Telefone caso seja necessario.
     * @return
     * Retorna o numero de telefone.
     */
    public int getNumeroTelefone() {
        return numeroTelefone;
    }

    /**
     * Permite realizar um set do numero de telefone caso seja necessario.
     * @param numeroTelefone
     * parametro numero de Telefone.
     */
    public void setNumeroTelefone(int numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }


    /**
     * Metodo da Categoria Investigador (classe Enumeracao implementada).
     * Permite distinguir a categoria Efetivo dos outros tipos de categorias.
     * @return
     * Retorna a Categoria do Investigador.
     */
    @Override
    public CategoriaInvestigador getCategoria() {
        return CategoriaInvestigador.EFETIVO;
    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma string que permite caraterizar a Categoria Efetivo.
     */
    public String toString(){

        return super.toString() + ", Numero de Gabinete : " + this.numeroGabinete + ", Numero de Telefone: " + this.numeroTelefone;
    }
}
