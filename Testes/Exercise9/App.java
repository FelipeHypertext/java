package Exercise9;

import java.io.*;
import java.util.*;

public class App {
    static void main(String[] args) throws FileNotFoundException {
        ArrayList<TempMes> lista = new ArrayList<>();
        File arquivo = new File("Testes/ArquivosAuxilio/temperaturas.txt");
        float maiorTemp = 0, tempTotal = 0;
        String maiorMes = "";


        if (arquivo.exists()) {
            Scanner leitor = new Scanner(arquivo);
            while(leitor.hasNextLine()) {
                String tempMes = leitor.nextLine();

                String[] partes = tempMes.split(";");
                String mes = partes[0];
                float temperatura = Float.parseFloat(partes[1]);

                if (temperatura > maiorTemp) {
                    maiorTemp = temperatura;
                    maiorMes = mes;
                }

                tempTotal += temperatura;

                lista.add(new TempMes(mes, temperatura));
            }
            leitor.close();
        } else{
            throw new FileNotFoundException();
        }

        System.out.printf(
            """
            -----------------------------------------------------
            %-15s %-10s %12s
            -----------------------------------------------------
            """, "Mês", "Temperatura", "Diferença"
        );

        /*Organiza os meses no padrão da variável 'meses'*/
        List<String> meses = List.of(
                "Janeiro", "Fevereiro", "Marco",
                "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro",
                "Outubro", "Novembro", "Dezembro"
        );

        /*Comparando pelo valor no índice criado em meses*/
        lista.sort(Comparator.comparingInt(m -> meses.indexOf(m.mes())));
        for (TempMes tempMes : lista) {
            System.out.printf(
                """
                %-12s %+10.1f °C %+10.1f °C
                """, tempMes.mes(), tempMes.temperatura(), tempMes.temperatura() - (tempTotal / lista.size())
            );
        }

        System.out.printf(
            """
            -----------------------------------------------------
            Temperatura média anual: %+.1f °C
            Mês com maior temperatura: %s
            Maior temperatura: %+.1f °C
            """, tempTotal / lista.size(), maiorMes, maiorTemp);
    }
}