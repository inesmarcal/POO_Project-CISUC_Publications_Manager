package Exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe Investigador.
 */
public abstract class Investigador implements Serializable {

    private String nome;
    private String email;
    private GrupoInvestigacao grupoInvestigacao;
    private List<Publicacao> publicacoes;


    /**
     * Construtor da Classe Investigador.
     * @param nome
     * nome do Investigador
     * @param email
     * email do Investigador
     * @param grupoInvestigacao
     * grupo de investigacao do Investigador
     */
    public Investigador(String nome, String email, GrupoInvestigacao grupoInvestigacao)
    {
        this.nome = nome;
        this.email = email;
        this.grupoInvestigacao = grupoInvestigacao;
        this.publicacoes = new ArrayList<>();;

    }

    /**
     * Permite obter o nome do Investigador.
     * @return
     * Retorna o nome do Investigador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite obter o set do nome do Investigador.
     * @param nome
     * parametro nome do Investigador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter o email do Investigador, caso sej necessario.
     * @return
     * Retorna o email do Investigador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permite efetuar um set do email do Investigador, caso seja necessario.
     * @param email
     * parametro email do Investigador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Pemrite obter o Grupo de Investigacao do Investigador, caso seja necessario.
     * @return
     * Retorna o Grupo de Investigacao.
     */
    public GrupoInvestigacao getGrupoInvestigacao() {
        return grupoInvestigacao;
    }

    /**
     * Permite efetuar um set do Grupo de Investigacao, caso seja necessario.
     * @param grupoInvestigacao
     * parametro Grupo de Investigacao
     */
    public void setGrupoInvestigacao(GrupoInvestigacao grupoInvestigacao) {
        this.grupoInvestigacao = grupoInvestigacao;
    }

    /**
     * Permite adicionar uma publicacao ao Grupo de Investigacao.
     * @param publicacao
     * parametro Publicacao pretendida
     */
    public void addPublicacao(Publicacao publicacao) {

        if (!publicacoes.contains(publicacao)) {

            this.publicacoes.add(publicacao);
        }
    }

    /**
     * Permite listar as publicacoes de um Investigador.
     * @return
     * Retorna a listagem das publicacoes de um Investigador.
     */
    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    /**
     * Permite efetuar o set da lista de publicacoes do Investigador.
     * @param publicacoes
     * parametro lista de Publicacoes.
     */
    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    /**
     * Pemite listar as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto.
     * @return
     * Retorna uma String com a lista de publicacoes organizada.
     */
    public String listarPublicacoes()
    {

        String s = "";

        //Permite organizar as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto.
        this.publicacoes.sort(new Comparator<Publicacao>() {
            @Override
            public int compare(Publicacao o1, Publicacao o2) {
                return o1.getAnoPublicacao() - o2.getAnoPublicacao();
            }
        }.thenComparing(new Comparator<Publicacao>() {
            @Override
            public int compare(Publicacao o1, Publicacao o2) {
                return o1.getTipo().ordinal() - o2.getTipo().ordinal();
            }
        }.thenComparing(new Comparator<Publicacao>() {
            @Override
            public int compare(Publicacao o1, Publicacao o2) {
                return o1.fatorImpacto() - o2.fatorImpacto();
            }
        })));

        int ano = 0;
        String tipo = "";
        char fator = ' ';

        /*
            Ano: 2020
                Tipo: ArtigoConferencia (por exemplo)
                    Fator: A
                           Publicacao
                    Fator: B
                           Publicacao
                    Fator: C
                           Publicacao

                Tipo:

            Ano: 2021
                    ...
             */

        for (Publicacao publicacao : this.publicacoes) {

            if (ano != publicacao.getAnoPublicacao()) {

                ano = publicacao.getAnoPublicacao();
                tipo = publicacao.getTipo().name();
                fator = publicacao.fatorImpacto();
                s += "\nAno: " + ano +"\n" ;
                s += "\tTipo: " + tipo + "\n";
                s += "\t\tFator de Impacto: " + fator + "\n";
            }

            if (!tipo.equals(publicacao.getTipo().name())) {

                tipo = publicacao.getTipo().name();
                fator = publicacao.fatorImpacto();
                s += "\tTipo: " + tipo + "\n";
                s += "\t\tFator de Impacto: " + fator + "\n";
            }

            if (fator != publicacao.fatorImpacto()) {

                fator = publicacao.fatorImpacto();
                s += "\t\tFator de Impacto: " + fator + "\n";
            }

            s+= "\n\t\tCaraterísticas da publicação:" + publicacao + "\n\n";

        }

        return s;

    }

    /**
     * Metodo Abstrato que permite ser utlizado para distinguir os diversos tipos de Investigadores.
     * @return
     * retorna a Categoria do Investigador.
     */
    public abstract CategoriaInvestigador getCategoria();

    /**
     * Metodo toString.
     * @return
     * Retorna uma String com a caraterização do Investigador.
     */
    @Override
    public String toString(){
        return this.nome + ", Email: " + this.email + ", " + this.grupoInvestigacao.getNome();
    }

}
