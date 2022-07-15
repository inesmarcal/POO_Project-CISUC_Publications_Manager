package Exercicio;

import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.DateTimeException;
import java.time.LocalDate;

//Na realização do projeto implementei a classe do Java LocalData.

/**
 * Classe onde sao implementados todos os metodos pretendidos.
 */
public class GPCISUC implements Serializable {


    private List<Investigador> investigadores;
    private List<Publicacao> publicacoes;
    private List<GrupoInvestigacao> grupos;


    /**
     * Construtor onde são inicializadas as listas pretendidas.
     */
    public GPCISUC() {
        this.investigadores = new ArrayList<>();
        this.publicacoes = new ArrayList<>();
        this.grupos = new ArrayList<>();

        //Sempre que for lançada uma excepcao da incorreta leitura dos ficheiros ou da sua inexistencia
        //o programa termina.
        if (!this.load()) {

            System.exit(0);

        }
    }

    /**
     * Permite adicionar as publicacoes.
     *
     * @param publicacao publicacao
     */
    public void addPublicacoes(Publicacao publicacao) {


        for (Publicacao p : this.publicacoes) {

            if (p.equals(publicacao)) {

                throw new IllegalArgumentException("A publicacao a inserir é repetida.");
            }
        }

        this.publicacoes.add(publicacao);


    }

    /**
     * Permite adicionar os Investigadores.
     *
     * @param investigador investigador
     */

    public void addInvestigadores(Investigador investigador) {

        for (Investigador inves : this.investigadores) {
            if (inves.getNome().equals(investigador.getNome())) {
                return;
            }

        }
        this.investigadores.add(investigador);
    }

    /**
     * Permite adicionar um grupo de Investigacao.
     *
     * @param grupo grupo de investigacao
     */

    public void addGrupos(GrupoInvestigacao grupo) {

        for (GrupoInvestigacao g : this.grupos) {

            if (grupo.getNome().equals(g.getNome())) {
                return;
            }
        }
        this.grupos.add(grupo);
    }

    /**
     * Permite realizar a chamada dos métodos que realizam a leitura dos ficheiros, lançando as respetivas excepcoes.
     * Caso não existam ficheiros de objetos, são utilizados os de texto e vice-versa.
     *
     * @return Retorna false se for lançada alguma excepcao num dos metodos.
     */
    public boolean load() {

        //Em primeiro lugar optei por ler o ficheiro GruposInvestigacao.txt, uma vez que assim se facilitaria a leitura dos
        //ficheiros seguintes. De seguida efetua-se a leitura do ficheiro Investigadores.txt e já tendo acesso aos membros efetivos poderemos
        //verificar se o investigador orientador do grupo de investigação é efetivo, colocando-o no respetivo grupo de Investigacao através da segunda leitura do ficheiro GruposInvestigacao.txt.
        //Por fim, é lido o ficheiro Publicacoes.txt.

        try {

            loadObject();

        } catch (IOException e) {

            try {
                loadTextGruposInvestigacao();
                loadTextInvestigadores();
                loadResponsaveisPorGrupo();
                loadPublicacoes();

            } catch (IOException exception) {

                System.err.println(exception.getMessage());
                return false;
            }

        } catch (ClassNotFoundException e) {

            System.err.println("Erro a converter o objeto no ficheiro \"GestorPublicacoes.obj.\"");
            return false;
        }

        return true;
    }

    /**
     * Permite guardar o conteúdo nos ficheiros de objetos.
     */

    public void save() {

        try {

            this.saveObject();

        } catch (FileNotFoundException exception) {

            System.err.println("O Ficheiro \"GestorPublicacoes.obj\" não existe.");

        } catch (IOException exception) {
            System.err.println("Erro a escrever para o ficheiro \"GestorPublicacoes.obj\"");

        }
    }

    /**
     * Permite realizar o load do ficheiro grupos de investigacao.
     *
     * @throws IOException Caso não exista ou o ficheiro seja lido incorretamente é lançada uma excepcao.
     */

    private void loadTextGruposInvestigacao() throws IOException {

        //Neste método o ficheiro GruposInvestigacao.txt é lido sem o investigador responsável.

        File file = new File("GruposInvestigacao.txt");

        int counter = 1;

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");


                if (s.length != 3) {
                    throw new IOException("Numero errado de parametros em \"GruposInvestigacao.txt\" na linha numero " + counter);
                }

                for (String aux : s) {
                    if (aux == null || aux.length() == 0) {
                        throw new IOException("Null String em \"GruposInvestigacao.txt\" na linha numero " + counter);
                    }
                }


                addGrupos(new GrupoInvestigacao(s[0], s[1]));

                counter++;
            }

            br.close();
            fr.close();


        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"GrupoInvestigacao.txt\"");
        } catch (IOException exception) {
            throw new IOException("Erro a ler o ficheiro \"GrupoInvestigacao.\" na linha numero " + counter);
        }


    }


    /**
     * Depois de terem sido lido os investigadores com o método loadInvestigadores(), irá ser lido o responsavel de cada grupo.
     *
     * @throws IOException Caso o ficheiro seja lido incorretamente ou não exista é lancada uma excepcao.
     */
    private void loadResponsaveisPorGrupo() throws IOException {

        //Quando já se efetuou a leitura dos investigadores do ficheiro Investigadores.txt é efetuada uma segunda leitura
        //no ficheiro GruposInvestigacao.txt para se poder obter o investigador responsável de cada grupo. Este procedimento permite que à medida que sejam colocados
        //os investigadores responsaveis seja verificado se são membros efetivos.

        File file = new File("GruposInvestigacao.txt");
        int counter = 1;

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");

                GrupoInvestigacao grupo = encontrarGrupoInvestigacao(s[1]);
                Investigador responsavel = encontrarInvestigador(s[2]);


                if (grupo != null && responsavel != null) {

                    //Caso o investigador responsavel não seja efetivo será lancada uma excepção.
                    //Esta excepcão é implementada se for lançada a excepcao no metodo setResponsavel() na classe GrupoInvestigacao.
                    try {
                        grupo.setResponsavel(responsavel);
                    } catch (IllegalArgumentException exception) {
                        System.err.println(exception.getMessage());
                        System.exit(0);
                    }

                }
            }

            br.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"GrupoInvestigacao.txt\"");
        } catch (IOException e) {
            throw new IOException("Erro a ler o ficheiro \"GrupoInvestigacao.\" na linha numero " + counter);
        }

    }

    /**
     * Permite realizar a leitura do ficheiro de investigadores.
     *
     * @throws IOException Caso o ficheiro seja lido incorretamente ou não exista é lancada uma excepcao.
     */
    private void loadTextInvestigadores() throws IOException {

        //Em primeiro lugar são lidos todos os efetivos e só posteriormente na segunda leitura do ficheiro
        //é que que serão lidos os estudantes. Assim conseguiremos garantir que o orientador do estudante é efetivo e pertence ao mesmo grupo de investigação
        //que o estudante.

        File file = new File("Investigadores.txt");
        int counter = 1;

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Investigador efetivo;

            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");

                if (s.length > 0 && s[0].toLowerCase().equals("e")) {

                    if (s.length != 6) {
                        throw new IOException("Numero incorreto de parametros em \"Investigadores.txt\" na linha numero " + counter);
                    }

                    for (String aux : s) {
                        if (aux == null || aux.length() == 0) {
                            throw new IOException("String null em investigadores.txt na linha numero " + counter);
                        }
                    }

                    try {

                        efetivo = new Efetivo(s[1], s[2], encontrarGrupoInvestigacao(s[3]), s[4], Integer.parseInt(s[5]));
                        addInvestigadores(efetivo);
                        encontrarGrupoInvestigacao(s[3]).addInvestigador(efetivo);

                    } catch (NullPointerException exception) {

                        System.err.println("O grupo de Investigação na linha " + counter + " não existe na base de dados");
                        System.exit(0);

                    } catch (NumberFormatException exception) {

                        System.err.println("\nErro: conversão Integer na linha " + counter + ".\n");
                        System.exit(0);

                    }


                }
                counter++;

            }

            br.close();
            fr.close();

        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"Investigadores.txt\"");
        } catch (IOException exception) {
            throw new IOException(exception.getMessage() + "\nErro a ler o ficheiro \"Investigadores.txt\" na linha numero " + counter);
        }

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            counter = 1;


            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");
                LocalDate data;

                if (s.length > 0 && s[0].toLowerCase().equals("s")) {

                    if (s.length != 7) {
                        throw new IOException("Numero incoreto de parametros em \"Investigadores.txt\" na linha numero " + counter);
                    }

                    for (String aux : s) {
                        if (aux == null || aux.length() == 0) {
                            throw new IOException("String null em \"Investigadores.txt\" na linha numero " + counter);
                        }
                    }

                    try {

                        String[] d = s[5].split("/");

                        Investigador orientador;
                        Investigador estudante;

                        data = LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0]));
                        orientador = encontrarInvestigador(s[6]);
                        estudante = new Estudante(s[1], s[2], encontrarGrupoInvestigacao(s[3]), s[4], data, orientador);
                        addInvestigadores(estudante);

                    } catch (NumberFormatException exception) {

                        System.err.println("\nErro: conversão Integer na linha " + counter);
                        System.exit(0);

                    } catch (NullPointerException exception) {

                        System.err.println("O orientador (" + s[6] + ") da linha " + counter + " deve ser um membro efetivo.");
                        System.exit(0);

                    } catch (IllegalArgumentException exception) {

                        System.err.println(exception.getMessage() + " - o estudante da linha " + counter + " não foi adicionado.");
                        System.exit(0);

                    } catch (DateTimeException exception) {

                        System.err.println(exception.getMessage() + " - erro na linha " + counter);
                        System.exit(0);

                    } catch (ArrayIndexOutOfBoundsException exception) {

                        System.err.println("Erro: formatação na data da linha " + counter);
                        System.exit(0);
                    }
                }
                counter++;
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"Investigadores.txt\"");
        } catch (IOException exception) {
            throw new IOException(exception.getMessage() + "\nError a ler o ficheiro \"Investigadores.txt\" na linha numero " + counter);
        }

    }

    /**
     * Permite realizar a leitura do ficheiro de publicacoes.
     *
     * @throws IOException Caso o ficheiro seja lido incorretamente ou não exista é lancada uma excepcao.
     */
    private void loadPublicacoes() throws IOException {
        //Conforme o tipo indicado como o parametro s[0], obtido depois de realizar o split da linha do ficheiro,
        //o ficheiro é lido de acordo com a formatacao estabelecida para cada tipo de publicacao.

        File file = new File("Publicacoes.txt");

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            int lineCounter = 1;

            while ((line = br.readLine()) != null) {

                int numLinhasResumo = 0;
                String[] s = line.split("\\|");

                String[] p = s[4].split(",");
                List<String> palavraPasses = new ArrayList<>();

                for (String palavraPasse : p) {
                    if (palavraPasse != null) {
                        palavraPasses.add(palavraPasse);
                    }
                }

                String[] sa = s[5].split(",");

                List<Investigador> autores = new ArrayList<>();

                for (String autor : sa) {

                    if (encontrarInvestigador(autor) != null) {

                        autores.add(encontrarInvestigador(autor));
                    }

                }

                String total = "";
                line = br.readLine().trim();
                lineCounter++;

                // Detetar início de resumo
                if (line.substring(0, "<resumo>".length()).equals("<resumo>")) {

                    line = line.replace("<resumo>", "");

                    while (!line.contains("</resumo>")) {

                        String resumo = line;

                        total += resumo + " ";
                        line = br.readLine();
                        numLinhasResumo++;
                    }

                    total += line.replace("</resumo>", "");
                } else {

                    throw new IOException("Erro: Falta o resumo na linha " + lineCounter);

                }
                lineCounter += numLinhasResumo;

                switch (s[0]) {

                    case "L":

                        Livro publicacao;

                        if (s.length != 8) {
                            throw new IOException("Numero incorreto de parametros em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                        }

                        for (String aux : s) {
                            if (aux == null || aux.length() == 0) {
                                throw new IOException("String null em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                            }
                        }

                        try {

                            publicacao = new Livro(s[1], Integer.parseInt(s[2].trim()), Integer.parseInt(s[3]), s[6], s[7], palavraPasses, autores, total);

                            addPublicacoes(publicacao);


                        } catch (NumberFormatException exception) {

                            System.err.println("\nErro: conversão Integer.\n");
                            System.exit(0);

                        } catch (IllegalArgumentException exception) {

                            System.err.println("\nA publicação a inserir é repetida na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);


                        }

                        break;


                    case "CP":

                        CapituloLivro cp;
                        if (s.length != 11) {
                            throw new IOException("Numero incorreto de parametros em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                        }

                        for (String aux : s) {
                            if (aux == null || aux.length() == 0) {
                                throw new IOException("String null em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                            }
                        }

                        try {

                            cp = new CapituloLivro(s[1], Integer.parseInt(s[2].trim()), Integer.parseInt(s[3]), s[6], s[7], palavraPasses, autores, total, s[8], Integer.parseInt(s[9].trim()), Integer.parseInt(s[10].trim()));

                            addPublicacoes(cp);


                        } catch (NumberFormatException e) {

                            System.err.println("\nErro: conversão Integer.\n");
                            System.exit(0);

                        } catch (IllegalArgumentException exception) {

                            System.err.println("\nA publicação a inserir é repetida na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        }
                        break;

                    case "AR":
                        ArtigoRevista ar;

                        if (s.length != 9) {
                            throw new IOException("Numero incorreto de parametros em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                        }

                        for (String aux : s) {
                            if (aux == null || aux.length() == 0) {
                                throw new IOException("String null em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                            }
                        }

                        LocalDate dataAR;
                        String[] dAR = s[7].split("/");

                        try {

                            dataAR = LocalDate.of(Integer.parseInt(dAR[2]), Integer.parseInt(dAR[1]), Integer.parseInt(dAR[0]));
                            ar = new ArtigoRevista(s[1], Integer.parseInt(s[2].trim()), Integer.parseInt(s[3]), palavraPasses, autores, total, s[6], dataAR, Integer.parseInt(s[8].trim()));

                            addPublicacoes(ar);


                        } catch (NumberFormatException exception) {

                            System.err.println("\nErro: conversão Integer.\n");
                            System.exit(0);

                        } catch (IllegalArgumentException exception) {

                            System.err.println("\nA publicação a inserir é repetida na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        } catch (DateTimeException exception) {

                            System.err.println(exception.getMessage() + " - erro na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        } catch (ArrayIndexOutOfBoundsException exception) {

                            System.err.println("Erro: formatação na data da linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);
                        }

                        break;

                    case "AC":

                        if (s.length != 9) {
                            throw new IOException("Numero incorreto de parametros em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                        }

                        for (String aux : s) {
                            if (aux == null || aux.length() == 0) {
                                throw new IOException("String null em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                            }
                        }

                        LocalDate data;
                        ArtigoConferencia ac;

                        String[] d = s[7].split("/");

                        try {

                            data = LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0]));

                            ac = new ArtigoConferencia(s[1], Integer.parseInt(s[2].trim()), Integer.parseInt(s[3]), palavraPasses, autores, total, s[6], data, s[7]);


                            addPublicacoes(ac);


                        } catch (NumberFormatException exception) {

                            System.err.println("\nErro: conversão Integer.\n");
                            System.exit(0);

                        } catch (IllegalArgumentException exception) {

                            System.err.println("\nA publicação a inserir é repetida na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        } catch (DateTimeException exception) {

                            System.err.println(exception.getMessage() + " - erro na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        } catch (ArrayIndexOutOfBoundsException exception) {

                            System.err.println("Erro: formatação na data da linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);
                        }
                        break;
                    case "LAC":

                        LivroArtigosConferencia lac;
                        if (s.length != 11) {
                            throw new IOException("Numero incorreto de parametros em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                        }

                        for (String aux : s) {
                            if (aux == null || aux.length() == 0) {
                                throw new IOException("String null em \"Publicacoes.txt\" na linha numero " + (lineCounter - numLinhasResumo - 1));
                            }
                        }

                        try {

                            lac = new LivroArtigosConferencia(s[1], Integer.parseInt(s[2].trim()), Integer.parseInt(s[3].trim()), s[6], s[7], palavraPasses, autores, total, s[8], Integer.parseInt(s[9].trim()), Integer.parseInt(s[10].trim()));

                            addPublicacoes(lac);


                        } catch (NumberFormatException exception) {

                            System.err.println("\nErro: conversão Integer.\n");
                            System.exit(0);

                        } catch (IllegalArgumentException exception) {

                            System.err.println("\nA publicação a inserir é repetida na linha " + (lineCounter - numLinhasResumo - 1));
                            System.exit(0);

                        }

                        break;
                }
                lineCounter++;
            }


            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"Publicacoes.txt\"");
        } catch (IOException e) {
            throw new IOException("Error a ler o ficheiro \"Publicacoes.txt\": " + e.getMessage());
        }

    }

    /**
     * Permite guardar os dados no ficheiro de objetos.
     *
     * @throws IOException Caso a escrita no ficheiro seja realizada incorretamente ou o mesmo este não exista é lançada uma excepção
     *                     que se encontra no método save().
     */
    private void saveObject() throws IOException {

        File file = new File("GestorPublicacoes.obj");

        FileOutputStream fos = new FileOutputStream(file);

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();

    }

    /**
     * Permite realizar a leitura a partir do ficheiro de objetos.
     *
     * @throws IOException            Caso ocorra algum erro na leitura do ficheiro é lançada uma excepção.
     * @throws ClassNotFoundException Caso ocorra um erro no cast do objeto é lançado um erro.
     */
    private void loadObject() throws IOException, ClassNotFoundException {

        try {

            File file = new File("GestorPublicacoes.obj");

            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois = new ObjectInputStream(fis);

            GPCISUC g = (GPCISUC) ois.readObject();

            this.grupos = g.grupos;
            this.investigadores = g.investigadores;
            this.publicacoes = g.publicacoes;

            ois.close();
            fis.close();

        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException("Não foi possível abrir o ficheiro \"Publicacoes.obj\"");
        } catch (IOException exception) {
            throw new IOException("Error reading \"Publicacoes.obj\" at line number ");
        }


    }

    /**
     * Usado para se poder verificar se o investigador existe ao ler o ficheiro e ao mesmo tempo ira
     * permitir que usemos o objeto investigador ao longo do programa em vez da String nome apenas.
     *
     * @param nomeInvestigador nome do Investigador
     * @return Retorna um investigador caso exista.
     */
    public Investigador encontrarInvestigador(String nomeInvestigador) {

        for (Investigador investigador : this.investigadores) {

            if (investigador.getNome().equals(nomeInvestigador)) {

                return investigador;
            }
        }

        return null;

    }

    /**
     * Usado para se poder verificar se o Grupo de Investigacao existe ao ler o ficheiro e ao mesmo tempo ira
     * permitir que usemos o objeto Grupo de investigacao ao longo do programa em vez da String acronimo apenas.
     *
     * @param acronimo acronimo do grupo de investigacao
     * @return Retorna um Grupo de Investigacao caso exista.
     */
    public GrupoInvestigacao encontrarGrupoInvestigacao(String acronimo) {

        for (GrupoInvestigacao grupoInvestigacao : this.grupos) {

            if (grupoInvestigacao.getAcronimo().equals(acronimo)) {

                return grupoInvestigacao;
            }
        }

        return null;
    }

    /**
     * Metodo usado no menu, mais propriamente na opcao4, para verificar se um investigador apresenta publicacoes e assim poder avisar
     * caso não existam.
     *
     * @param nome parametro nome Investigador
     * @return Retorna um booleano que permite indicar se o investigador apresenta publicacoes.
     */
    public boolean verificarInvestigadorPublicacao(String nome) {
        for (Publicacao publicacao : this.publicacoes) {

            if (publicacao.getAutores().contains(encontrarInvestigador(nome))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permite obter o numero total de investigadores existentes.
     *
     * @return Retorna o numero total de investigadores.
     */
    public int getNumTotalInvestigadores() {

        return investigadores.size();
    }

    /**
     * Permite apresentar o numero total de investigadores estudantes e efetivos.
     *
     * @return Retorna uma string com o numero total de investigadores efetivos e estudantes.
     */
    public String membrosPorCategoria() {

        int efetivo = 0, estudante = 0;

        for (Investigador investigador : this.investigadores) {

            if (investigador.getCategoria().equals(CategoriaInvestigador.ESTUDANTE)) {
                estudante++;
            } else {

                efetivo++;
            }
        }

        String s = "";

        s += "O número de Investigadores Estudantes é: " + estudante + "\nO numero de Investigadores efetivos é: "
                + efetivo;

        return s;


    }

    /**
     * Permite apresentar o numero total de cada publicacao tipo.
     *
     * @return Retorna uma string com o numero total de cada publicacao tipo.
     */
    public String publicacoesTipo() {

        int artigoconferencia = 0, artigoRevista = 0, capituloLivro = 0, livro = 0, livroArtigoConferencia = 0;


        for (Publicacao publicacao : this.publicacoes) {

            if (publicacao.getTipo() == TipoPublicacao.ArtigoConferencia) {
                artigoconferencia++;
            } else if (publicacao.getTipo() == TipoPublicacao.ArtigoRevista) {

                artigoRevista++;
            } else if (publicacao.getTipo() == TipoPublicacao.CapituloLivro) {

                capituloLivro++;
            } else if (publicacao.getTipo() == TipoPublicacao.LivroArtigosConferencia) {

                livroArtigoConferencia++;
            } else if (publicacao.getTipo() == TipoPublicacao.Livro) {
                livro++;
            }
        }

        //Como os livros apresentam duas subclasses considerei que o total de livros seria a soma do numero destes com as suas subclasses.

        String s = "\nNúmero de Publicações do tipo :\n";

        s += "Artigo Conferencia : " + artigoconferencia
                + "\nArtigo Revista : " + artigoRevista
                + "\nLivro : " + (livro + capituloLivro + livroArtigoConferencia)
                + "\t----> Capitulo Livro : " + capituloLivro
                + "\n\t\t\t----> Livro Artigo Conferencia : " + livroArtigoConferencia;

        return s;

    }

    /**
     * Permite informar acerca do numero total de publicacoes dos ultimos cinco anos.
     *
     * @return Retorna o numero total de publicacoes dos ultimos cincoo anos.
     */
    public int totalPublicacoesUltimosCincoAnos() {
        int contador = 0;

        for (Publicacao publicacao : this.publicacoes) {

            if (LocalDate.now().getYear() - publicacao.getAnoPublicacao() <= 5) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Permite listar as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por
     * ano, por tipo de publicação e por fator de impacto.
     *
     * @param acronimo acronimo do grupo de investigacao
     * @return Retorna uma lista de publicacoes com a respetiva organizacao estabelecida.
     */
    public List<Publicacao> listaPublicacoes(String acronimo) {
        List<Publicacao> pubs = new ArrayList<>();


        for (Publicacao publicacao : this.publicacoes) {

            for (Investigador autor : publicacao.getAutores()) {

                if (autor.getGrupoInvestigacao().getAcronimo().equals(acronimo) && (LocalDate.now().getYear() - publicacao.getAnoPublicacao() <= 5)
                        && !pubs.contains(publicacao)) {
                    pubs.add(publicacao);

                }
            }
        }

        //Em primeiro lugar organiza por ano, caso o mesmo seja igual passa a tentar ordenar pelo tipo de publicação
        //e ordena em último caso(se todos os outros parametros estabelecidos sejam iguais) pelo fator de impacto.
        pubs.sort(new Comparator<Publicacao>() {
            @Override
            public int compare(Publicacao o1, Publicacao o2) {

                /*

                o1 < o2  => < 0
                o1 == o2 => 0
                o1 > o2 => > 0


                 */
                if (o1.getAnoPublicacao() == o2.getAnoPublicacao()) {

                    if (o1.getTipo().equals(o2.getTipo())) {

                        return o1.fatorImpacto() - o2.fatorImpacto();


                    } else {

                        return o1.getTipo().compareTo(o2.getTipo());
                    }


                } else {

                    return o1.getAnoPublicacao() - o2.getAnoPublicacao();
                }
            }
        });

        return pubs;

    }

    /**
     * Permite visualizar as publicacoes que se encontram na lista retornada pelo método acima, listaPublicacoes().
     *
     * @param acronimo acronimo do grupo de investigacao
     * @return Retorna
     */
    public String listarPublicacoes(String acronimo) {

        String s = "";

        for (Publicacao pub : listaPublicacoes(acronimo)) {

            s += pub + "\n";
        }
        return s;
    }

    /**
     * Permite informar acerca do numero de membros que cada grupo de investigacao existente apresenta.
     *
     * @return Retorna uma string com o numero total de mebros de cada grupo de investigacao.
     */
    public String numeroMembrosGruposInvestigacao() {

        String s = "";

        for (GrupoInvestigacao grupoInvest : this.grupos) {

            s += "O grupo " + grupoInvest.getNome() + " apresenta " + grupoInvest.getMembros().size() + " membros.\n";
        }


        return s;
    }

    /**
     * Permite listar os membros agrupados por categoria do grupo de investigacao pretendido.
     *
     * @param acronimo acronimo do grupo de investigacao
     * @return Retorna os membros agrupados por categoria caso existam.
     */
    public String listarInvestigadoresCategoria(String acronimo) {

        for (GrupoInvestigacao grupo : this.grupos) {

            if (grupo.getAcronimo().equals(acronimo)) {

                //Método implementado na classe Grupo de Investigacao.
                return grupo.listarInvestigadores();
            }
        }
        return null;
    }

    /**
     * Permite listar as publicações de um investigador agrupadas por ano, tipo de publicacao e fator
     * de impacto;
     *
     * @param nome nome do investigador
     * @return Retorna uma lista de publicacoes cujo metodo que a retorna se encontra implementado na classe Investigador.
     */
    public String listarPublicacoesInvestigador(String nome) {

        for (Investigador inves : this.investigadores) {

            if (inves.getNome().equals(nome)) {

                return inves.listarPublicacoes();
            }
        }
        return null;
    }

    /**
     * Permite visualizar o numero total de investigadores efetivos e estudantes de cada grupo de Investigacao existente.
     *
     * @return Retorna uma string com o numero total de investigadores efetivos e estudantes de cada grupo de Investigacao existente.
     */
    public String membrosPorCategoriaGrupo() {

        String s = "";

        for (GrupoInvestigacao grupo : this.grupos) {


            s += "O grupo " + grupo.getNome() + " apresenta " + grupo.getNumEfetivo() + " membros Efetivos e " + grupo.getNumEstudantes()
                    + " membros Estudantes.\n";

        }
        return s;
    }

    /**
     * Permite informar acerca do numero total de publicacoes dos últimos 5 anos.
     *
     * @return Retorna o numero total de publicações dos últimos 5 anos
     */
    public String totalPublicacoesUltimos5anos() {

        int contador;
        String s = "Número total de publicacoes dos últimos 5 anos:\n";

        for (GrupoInvestigacao grupo : this.grupos) {

            contador = 0;

            s += "Grupo " + grupo.getAcronimo() + ": ";

            for (Publicacao pub : grupo.getPublicacoes()) {

                if (LocalDate.now().getYear() - pub.getAnoPublicacao() <= 5) {

                    contador += 1;

                }

            }
            s += contador + " publicações\n";
        }
        return s;

    }

    /**
     * Permite informar acerca do numero de publicacoes, dos ultimos 5 anos, agrupadas por ano, tipo de publicaçao e fator de impacto
     * para cada grupo de investigacao.
     *
     * @return Retorna uma string com o numero de publicacoes que seguem os critérios estabelecidos.
     */
    public String listarPublicacoesAgrupadas() {

        String s = "";

        for (GrupoInvestigacao grupo : this.grupos) {

            s += " O grupo " + grupo.getAcronimo() + " apresenta " + listaPublicacoes(grupo.getAcronimo()).size() + " publicações\n";
        }

        return s;
    }


}









