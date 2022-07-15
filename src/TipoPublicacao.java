package Exercicio;

import java.io.Serializable;

/**
 * Classe Enumeracao utilizada para diferenciar os tipos de investigadores, sendo posteriormente tambem utilizada
 * para estabelecer a ordem com que é organizado o tipo de publicacao (um dos fatores de ordem pretendidos) na listagem das publicacoes.
 */
public enum TipoPublicacao implements Serializable {

    ArtigoConferencia, ArtigoRevista, Livro, CapituloLivro, LivroArtigosConferencia

}
