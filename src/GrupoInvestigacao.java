package Exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe Grupo de Investigacao.
 */
public class GrupoInvestigacao implements Serializable {

    private String nome;
    private String acronimo;
    private Investigador responsavel;
    private List<Investigador> membros;
    private List<Publicacao> publicacoes;

    /**
     * Construtor da classe Grupo de Investigacao.
     * @param nome
     * nome do grupo de investigacao
     * @param acronimo
     * acronimo do grupo de investigacao
     */
    public GrupoInvestigacao(String nome, String acronimo) {

        //Este primeiro construtor é utilizado para se poder efetuar a leitura do ficheiro GrupoInvestigacao.txt na primeira vez,
        //ou seja sem ainda ler o Investigador Responsavel do mesmo.
        this.nome = nome;
        this.acronimo = acronimo;
        this.membros = new ArrayList<>();
        this.responsavel = null;
        this.publicacoes = new ArrayList<>();
    }

    /**
     * Construtor da classe Grupo Investigacao.
     * @param nome
     * nome do grupo de investigacao
     * @param acronimo
     * acronimo do grupo de investigacao
     * @param responsavel
     * investigador responsavel do grupo de investigacao.
     */
    public GrupoInvestigacao(String nome, String acronimo, Investigador responsavel) {

        //Construtor utilizado para posteriormente se poder efetuar um set com o Investigador Responsavel, na segunda leitura do ficheiro.
        //E assim se poder verificar se o Investigador é Efetivo.
        this(nome, acronimo);
        this.setResponsavel(responsavel);
    }

    /**
     * Permite adicionar as publicacoes do grupo de Investigacao.
     * @param publicacao
     * parametro publicacao
     */
    public void addPublicacoes(Publicacao publicacao){

        if(!publicacoes.contains(publicacao)) {
            this.publicacoes.add(publicacao);
        }
    }

    /**
     * Permite obter o nome do Gupo de Investigacao.
     * @return
     * Retorna o nome do Grupo de Investigacao.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite realizar um set do nome do Grupo de investigacao.
     * @param nome
     * parametro nome do Grupo de Investigacao.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter o acronimo do Grupo de Investigacao.
     * @return
     * Retorna o acronimo do Grupo de Investigacao.
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * Permite realizar um set do acronimo do Grupo de Investigacao.
     * @param acronimo
     * parametro acronimo do Grupo de Investigacao.
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Permite obter o Investigador Responsavel.
     * @return
     * Retorna o Investigador Responsavel.
     */
    public Investigador getResponsavel() {
        return responsavel;
    }

    /**
     * Permite realizar um set do Invetsigador Responsavel.
     * @param responsavel
     * parametro Investigador Responsavel
     */
    public void setResponsavel(Investigador responsavel){

        //Permite verificar se o Investiogador Responsavel pelo Grupo de Investigação é Efetivo, caso contrario é lançada uma excepção.
        if (responsavel.getCategoria() != CategoriaInvestigador.EFETIVO){

            throw new IllegalArgumentException("O investigador responsável pelo grupo " + responsavel.getGrupoInvestigacao().getNome() + " deverá ser efetivo.");
        }

        this.responsavel = responsavel;
    }



    /**
     * Permite obter a listagem dos membros do Grupo de Investigacao.
     * @return
     * Retorna uma lista com os membros do grupo de Investigacao.
     */
    public List<Investigador> getMembros() {
        return membros;
    }

    /**
     * Permite efetuar um set da listagem dos membros do Grupo de Investigacao.
     * @param membros
     * parametro lista com os membros do Grupo de Investigacao.
     */
    public void setMembros(List<Investigador> membros) {
        this.membros = membros;
    }

    /**
     * Permite listar as publicacoes do Grupo de Investigacao.
     * @return
     * Retorna uma lista com as publicacoes do Grupo de Investigacao.
     */
    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    /**
     * Permite efetuar o set da listagem das publicacoes do Grupo de Imvestigacao.
     * @param publicacoes
     * parametro lista de publicacoes do Grupo de Investigacao
     */
    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    /**
     * Permite verificar se o Grupo de Invetsigacao ja apresenta um membro, não permitindo assim adicionar membros
     * repetidos.
     * @param nome
     * parametro nome do Investigador
     * @return
     * Retorna um booleano que permite indicar se o Grupo de Investigacao ja apresenta o membro que pretendemos inserir.
     */
    public boolean hasMembro(String nome)
    {
        for (Investigador investigador : this.membros) {

            if (investigador.getNome().equals(nome)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Utilizando assim o metodo hasMembro() permite que seja adicionado um membro que ainda nao esteja no Grupo de Investigacao.
     * @param investigador
     * parametro Invetsigador a adcionar
     */
    public void addInvestigador(Investigador investigador){

        if(!hasMembro(investigador.getNome())){

            membros.add(investigador);
        }
    }

    /**
     * Pemrite listar os Investigadores de acordo com a Categoria dos mesmos.
     * @return
     * Retorna uma String com os Investigadores organizados por Categoria.
     */
    public String listarInvestigadores()
    {

        String s = "";

        //Realiza o sort da lista de membros do Grupo de Investigacao. A ordem com que se encontram apresentados os tipos
        //de Investigador na Classe Enumeração (Classe CategoriaInvestigador) é utilizada para os organizar por Categoria.
        this.membros.sort(new Comparator<Investigador>() {
            @Override
            public int compare(Investigador o1, Investigador o2) {
                return o1.getCategoria().ordinal() - o2.getCategoria().ordinal();
            }
        });

        String categoria = "";

        for (Investigador investigador : this.membros) {

            if (!categoria.equals(investigador.getCategoria().name())) {

                categoria = investigador.getCategoria().name();

                s += categoria + ":\n";

            }

            s += investigador.getNome() + "\n";

        }

        return s;
    }


    /**
     * Permite obter o numero de Investigadores Estudantes num Grupo de Investigacao.
     * @return
     * Retorna o numero de Investigadores Estudantes.
     */
    public int getNumEstudantes(){

        int contador = 0;

        for (Investigador investigador : this.membros){

            if ( investigador.getCategoria().equals(CategoriaInvestigador.ESTUDANTE)){

                contador += 1;
            }
        }
        return contador;
    }

    /**
     * Permite obter o numero de Investigadores Efetivos num Grupo de Investigacao.
     * @return
     * Retorna o numero de Investigadores Efetivos.
     */
    public int getNumEfetivo(){

        int contador = 0;

        for (Investigador investigador : this.membros){

            if ( investigador.getCategoria().equals(CategoriaInvestigador.EFETIVO)){

                contador += 1;
            }
        }
        return contador;

    }

    /**
     * Metodo toString.
     * @return
     * Retorna uma String que carateriza o Grupo de Investigacao.
     */
    @Override
    public String toString(){
        String s = "Grupo de investigação: " + this.getNome() + ", " + this.acronimo;

        if(this.responsavel != null) {
            s += ", " + this.getResponsavel().getNome();
        }

        return s;
    }
}
