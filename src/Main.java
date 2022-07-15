package Exercicio;

import java.util.Scanner;

/**
 * Classe Main
 */
public class Main {
    /**
     * Classe onde se encontra implementado o meu.
     * @param args
     * argumentos de linha de comandos
     */
    public static void main(String[] args) {

        System.out.println("*".repeat(100) +"\n" +
                "*" + " ".repeat(40) + "Seja bem vindo à CISUC" + " ".repeat(36) + "*\n" +
                "*" + " ".repeat(20) + "(Centro de Informática e Sistemas da Universidade de Coimbra)" + " ".repeat(17) + "*\n" +
                "*".repeat(100) + "\n\n");

        GPCISUC gestor = new GPCISUC();

        String  opcao, opcao3, opcao4;
        String nome;
        Scanner teclado = new Scanner(System.in);

        System.out.println("Para usufruir da aplicação escolha uma das seguintes opções:\n");

        do {

            System.out.println("(S)air");
            System.out.println("(1) Apresentar os indicadores gerais do CISUC.");
            System.out.println("(2) Listar as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por\n" +
                    "ano, por tipo de publicação e por fator de impacto");
            System.out.println("(3) Listar os membros de um grupo de investigação agrupados por categoria");
            System.out.println("(4) Listar as publicações de um investigador agrupadas por ano, tipo de publicação e fator\n" +
                    "de impacto");
            System.out.println("(5) Listar todos os grupos de investigação");

            System.out.print("\nOpção: ");

            opcao = teclado.nextLine();

            switch (opcao.toLowerCase()) {

                case "1":

                    System.out.println("\nExercicio 1:\n");
                    System.out.println("Alinea a):\nTotal de membros: " + gestor.getNumTotalInvestigadores());
                    System.out.println("\nAlinea b): \nNúmero de membros de cada categoria:\n" +  gestor.membrosPorCategoria());
                    System.out.println("\nAlinea c): \nTotal de publicações dos último 5 anos: " + gestor.totalPublicacoesUltimosCincoAnos());
                    System.out.println("\nAlinea d): " + gestor.publicacoesTipo() + "\n\n");

                    break;

                case "2":

                    System.out.println("\nPara qual grupo pretende?: \n");
                    System.out.println("(AC)Adaptive Computation");
                    System.out.println("(IS)Information Systems");
                    System.out.println("(LCT)Communications and Telematics");
                    System.out.println("(CMS)Cognitive and Media Systems");
                    System.out.println("(ECOS)Evolutionary and Complex");
                    System.out.println("(SSE)Software and Systems Engineering");

                    do {

                        System.out.print("\nOpção: ");
                        opcao3 = teclado.nextLine();

                        if (gestor.encontrarGrupoInvestigacao(opcao3.toUpperCase()) == null) {
                            System.out.println("O nome inserido não é de um grupo de investigação registado");
                        }

                    } while(gestor.encontrarGrupoInvestigacao(opcao3.toUpperCase()) == null && !opcao3.equalsIgnoreCase("cancelar"));

                    if (!opcao3.equalsIgnoreCase("cancelar")) {

                        System.out.println(gestor.listarPublicacoes(opcao3.toUpperCase()));
                    }

                    break;

                case "3":

                    System.out.println("\nPara qual grupo pretende?: \n");
                    System.out.println("(AC)Adaptive Computation");
                    System.out.println("(IS)Information Systems");
                    System.out.println("(LCT)Communications and Telematics");
                    System.out.println("(CMS)Cognitive and Media Systems");
                    System.out.println("(ECOS)Evolutionary and Complex");
                    System.out.println("(SSE)Software and Systems Engineering");

                    do {
                        System.out.print("\nOpção: ");
                        opcao4 = teclado.nextLine();

                        if (gestor.encontrarGrupoInvestigacao(opcao4.toUpperCase()) == null) {
                            System.out.println("O nome inserido não é de um grupo de investigação registado");
                        }
                    } while(gestor.encontrarGrupoInvestigacao(opcao4.toUpperCase()) == null && !opcao4.equalsIgnoreCase("cancelar"));

                    if (!opcao4.equalsIgnoreCase("cancelar")) {

                        System.out.println(gestor.listarInvestigadoresCategoria(opcao4.toUpperCase()));
                    }
                    break;

                case "4":

                        do {
                            System.out.println("\nNome do Investigador:");
                            nome = teclado.nextLine();

                            if (gestor.encontrarInvestigador(nome) == null) {
                                System.out.println("O nome inserido não é de um investigador registado");
                            }
                            if(!gestor.verificarInvestigadorPublicacao(nome)){
                                System.out.println("O investigador inserido não apresenta publicações.");
                            }
                        } while(gestor.encontrarInvestigador(nome) == null && !nome.equalsIgnoreCase("cancelar"));


                    if (!nome.equalsIgnoreCase("cancelar") && gestor.listarPublicacoesInvestigador(nome) != null) {
                        System.out.println(gestor.listarPublicacoesInvestigador(nome));
                    }
                    break;

                case "5":

                    System.out.println("\nExercicio 5:\n");
                    System.out.println("Total de membros de cada grupo:\n" + gestor.numeroMembrosGruposInvestigacao());
                    System.out.println("Número de membros de cada categoria para cada grupo:\n" + gestor.membrosPorCategoriaGrupo());
                    System.out.println(gestor.totalPublicacoesUltimos5anos());
                    System.out.println("Número de publicações, dos últimos 5 anos, agrupadas por ano, tipo de publicação\n" +
                            "e fator de impacto para cada grupo:\n" + gestor.listarPublicacoesAgrupadas());

                    break;


                case "s":
                    break;

                default:

                    System.out.println("\nErro: Opção inválida\n");
                    break;

            }

        }while(!opcao.equals("s") && !opcao.equals("S"));


        gestor.save();

    }

}
