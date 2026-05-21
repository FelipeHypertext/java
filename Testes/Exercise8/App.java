package Exercise8;

import java.util.*;
import java.io.*;

public class App {
    static void main() throws FileNotFoundException {
        ArrayList<Funcionario> lista = extrairFuncionarios();
        float totalBytes = 0;
        for (Funcionario bytes : lista) {
            totalBytes += bytes.espacoDisco();
        }
        lista.sort(Comparator.comparing(Funcionario::espacoDisco).reversed());
        System.out.printf(
                """
                %-5s %-10s %-10s %s
                -----------------------------------------------------
                """, "Nr.", "Usuário", "Espaço utilizado", "% de uso");

        int i = 0;
        for (Funcionario funcionario : lista){
            i++;
            float espacoMb = funcionario.espacoDisco() / (1024 * 1024);
            float espacoPorcentagem = (funcionario.espacoDisco() / totalBytes) * 100;
            System.out.printf(
                    """
                    %-5s %-10s %-10.2fMB %10.2f%%
                    """, i, funcionario.nome(), espacoMb, espacoPorcentagem);
        }

        float espacoTotalMb = totalBytes / (1024 * 1024);
        float espacoMedio = espacoTotalMb / lista.size();
        System.out.printf(
                """
                -----------------------------------------------------
                Espaço total ocupado: %.2fMB
                Espaço médio ocupado: %.2fMB
                """,espacoTotalMb, espacoMedio);
    }

    private static ArrayList<Funcionario> extrairFuncionarios() throws FileNotFoundException {
        ArrayList<Funcionario> lista = new ArrayList<>();
        File arquivo = new File("Testes/ArquivosAuxilio/consumo.txt");

        /*Ler o arquivo e guardar os dados dos funcionários*/
        if (arquivo.exists()) {
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                String[] partes = linha.split(";");
                String nome = partes[0];
                /*Convertendo a parte String em Float*/
                float espacoDisco = Float.parseFloat(partes[1]);

                lista.add(new Funcionario(nome, espacoDisco));
            }
            leitor.close();
        } else {
            throw new FileNotFoundException("O arquivo de análise não foi encontrado.");
        }
        return lista;
    }
}
