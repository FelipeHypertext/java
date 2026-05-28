package Exercise10;

import java.util.*;
import java.io.*;
import java.text.*;

public class App {

    public static void main(String[] args) throws IOException {
        ArrayList<Carro> lista = extrairCarros();
        Scanner scanner = new Scanner(System.in);
        float precoGas = defPreco(scanner);

        lista.sort(Comparator.comparing(Carro::fabricante));
        FileWriter writer = new FileWriter("Testes/ArquivosAuxilio/relatorio.txt");

        for (Carro carro : lista) {
            DecimalFormat df = new DecimalFormat("#.00"); // sempre 2 casas decimais
            float milLitros = 1000 / carro.quilometragemLitro();
            String stringMilLitro = df.format(milLitros);

            float milGas = milLitros * precoGas;
            String stringMilGas = df.format(milGas);
            writer.write(carro.fabricante() + ";" + carro.modelo() + ";" + stringMilLitro + ";" + stringMilGas + "\n");
        }
        System.out.println("Programa executado com sucesso. Verifique relatorio.txt !");
        writer.close();
        scanner.close();
    }

    /*Extrair as informações da lista*/
    private static ArrayList<Carro> extrairCarros() throws FileNotFoundException {
        ArrayList<Carro> lista = new ArrayList<>();
        File arquivoCarros = new File("Testes/ArquivosAuxilio/carros.txt");

        if (arquivoCarros.exists()) {
            Scanner leitor = new Scanner(arquivoCarros);

            while (leitor.hasNextLine()) {
                String carro = leitor.nextLine();
                String[] partes = carro.split(";");

                String fabricante = partes[0];
                String marca = partes[1];
                float quilometragemLitro = Float.parseFloat(partes[2]);

                lista.add(new Carro(fabricante, marca, quilometragemLitro));
            }
            leitor.close();
        } else {
            throw new FileNotFoundException("Arquivo não encontrado na pasta de auxílio.");
        }
        return lista;
    }

    /*Define o preço da gasolina por litro*/
    public static float defPreco(Scanner scanner){
        System.out.println("Digite o preço da gasolina por litro:");
        return scanner.nextFloat();
    }
}
