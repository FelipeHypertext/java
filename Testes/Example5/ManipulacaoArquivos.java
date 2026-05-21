package Example5;

/* Biblioteca para leitura de dados do teclado e arquivos */
import java.util.Scanner;
/* Biblioteca para usar listas dinâmicas (ArrayList) */
import java.util.ArrayList;
/* Biblioteca para representar e manipular arquivos */
import java.io.File;
/* Biblioteca para escrever dados em arquivos */
import java.io.FileWriter;
/*Biblioteca para comparar e ordenar*/
import java.util.Comparator;

public class ManipulacaoArquivos {

    /*public static void salvarArquivo() {
        try {
            FileWriter writer = new FileWriter("contatos.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void main(String[] args) throws Exception {

        /* Scanner para ler dados digitados pelo usuário */
        Scanner scanner = new Scanner(System.in);

        /* Lista que armazenará os contatos em memória */
        ArrayList<Contato> lista = new ArrayList<>();

        /* Representa o arquivo contatos.txt */
        File arquivo = new File("Testes/ArquivosAuxilio/contatos.txt");

        /*Append true - é possível adicionar texto durante a execução*/
        FileWriter writer = new FileWriter("contatos.txt", true);

        /* Verifica se o arquivo existe */
        if (arquivo.exists()) {

            /* Scanner usado para ler o arquivo */
            Scanner leitor = new Scanner(arquivo);

            /* Enquanto houver linhas no arquivo */
            while (leitor.hasNextLine()) {

                /* Lê uma linha completa */
                String linha = leitor.nextLine();

                /* Divide a linha usando ";" */
                String[] partes = linha.split(";");

                /* Guarda cada parte em variáveis */
                String nome = partes[0];
                String telefone = partes[1];
                String email = partes[2];

                /* Cria um objeto Contato e adiciona ao ArrayList */
                lista.add(new Contato(nome, telefone, email));
            }

            /* Fecha o leitor do arquivo */
            leitor.close();
        }


        /* Menu principal */
        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n==== AGENDA ====\n");
            System.out.println("[1] Adicionar");
            System.out.println("[2] Listar");
            System.out.println("[3] Sair");

            System.out.print("\nEscolha uma opção: ");

            /* Lê a opção digitada */
            opcao = scanner.nextInt();

            /* Limpa o ENTER do teclado */
            scanner.nextLine();

            /* Adicionar contato */
            if (opcao == 1) {

                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                Contato c = new Contato(nome, telefone, email);

                /* Adiciona um novo contato na lista */
                lista.add(c);

                writer.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail() + "\n");
                System.out.println("Contato adicionado e salvo nos contatos!");
                /*Salva no arquivo no momento que é chamado*/
                writer.flush();
            }

            /* Listar contatos */
            if (opcao == 2) {

                lista.sort(Comparator.comparing(Contato::getNome));
                int i = 0;

                System.out.printf(
                        """
                        %-5s| %-10s| %-15s| %-20s
                        """, "ID", "Nome", "Telefone", "E-mail");
                /* Percorre toda a lista */
                for (Contato c : lista) {
                    i++;
                    /* Mostra os dados do contato */
                    System.out.printf(
                            """
                            %-5d| %-10s| %-15s| %-20s \n
                            """, i, c.getNome(), c.getTelefone(), c.getEmail());
                }
            }
        }

        writer.close();
        /*Salvar os dados no arquivo 'contatos.txt'*/

        /*Cria e escreve no arquivo contatos.txt*/
        FileWriter writerFinal = new FileWriter(arquivo);

        lista.sort(Comparator.comparing(Contato::getNome));
        /*Percorre todos os contatos da lista*/
        for (Contato c : lista) {
             /*Salva no formato 'nome;telefone;email'*/
            writerFinal.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail() + "\n");
        }

        /*Fecha o arquivo*/
        writerFinal.close();

        System.out.println("Dados salvos!");

        /* Fecha o scanner do teclado */
        scanner.close();
    }
}
