package Main;

import java.io.*;
import java.util.Scanner;

import Dados.DadosInseridos;

public class FileMain {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        String path = "atividade.txt";
        File arquivo = new File(path);
        char escolha = 'S';
        char inicio = 'S';
        String opcao;

        while (validarAcao(inicio)) {// menu de opções

            System.out.println("O que deseja fazer com o arquivo atvidade.txt?\n1-Adicionar\n2-listar\n3-Excluir arquivo\n4-Sair");
            opcao = ler.nextLine();

            switch (opcao) {
                case "1":
                    adicionar(arquivo, ler, escolha);
                    break;
                case "2":
                    listar(path);
                    break;
                case "3":
                    excluir(arquivo, ler);
                    break;
                case "4":
                    System.out.println("\nFinalizando....");
                    inicio = 'F';
                    break;
                default:
                    System.out.println("\nOpção invalida!");
                    break;
            }
        }
    }

    public static void adicionar(File arquivo, Scanner ler, char escolha) {  // escrever no arquivo

        try {
            while (validarAcao(escolha)) {

                String[] dados = new String[3];  // vetor de string para armazenar os dados

                FileWriter escrita = new FileWriter(arquivo.getName(), true);
                System.out.println("\n----------Criando arquivo e inserir dados-------------\n\nSeu arquivo foi criado neste caminho --> " + arquivo.getAbsolutePath() + "\n");
                System.out.println("\nInforme o nome:");
                dados[0] = ler.nextLine();

                System.out.println("\nInforme a idade: ");
                dados[1] = ler.nextLine();

                System.out.println("\nInforme a altura: ");
                dados[2] = ler.nextLine();

                escrita.write(dados[0] + ";" + dados[2] + ";" + dados[1] + ";" + "\n");// escrevendo os dados recebidos no  arquivo
                escrita.close();

                System.out.println("\nAdicionado com sucesso!\n");

                System.out.println("\nDeseja adicionar mais dados? \n S/N");
                escolha = ler.nextLine().charAt(0);

            }

        } catch (IOException ex) {
            System.out.println("Não foi possível criar o arquivo no PC!\n");
        }
    }

    public static void listar(String path) {    // listar todos os itens do arquivo

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {     // leitura com buffer
            System.out.println("\n-----------Sua lista de Dados---------\n");
            String linha = br.readLine();
            while (linha != null) {
                String[] vetor = linha.split(";");
                DadosInseridos dados = new DadosInseridos(vetor[0], Float.parseFloat(vetor[1]), Integer.parseInt(vetor[2]));// converti os valores em string para int e float
                linha = br.readLine();
                System.out.println(dados.toString());
            }
        } catch (IOException e) { // report de error ao tentar ler o arquivo
            System.out.println("Arquivo corrompido! ou Arquivo não existe! " + e.getMessage());
        }
    }

    public static void excluir(File arquivo, Scanner ler) {  // excluir o arquivo

        if (validarAcao(arquivo)) {    // verificando se o arquivo existe

            System.out.println("\n--------------Excluir Arquivo----------\n\nDeseja realmente excluir o arquivo --> " + arquivo.getName() + " [S/N]?\n");
            char escolha = ler.nextLine().charAt(0);
            System.out.println(validarAcao(arquivo, escolha));

        } else
            System.out.println("Não exixte arquivos para serem excluidos!");
    }

    public static boolean validarAcao(char escolha) {  //verifica se a escolha e true , caso não seja retorna um false
        return escolha == 'S' || escolha == 's';
    }

    public static boolean validarAcao(File arquivo) {   // verifiar arquivo
        return arquivo.exists();
    }
    
    public static String validarAcao(File arquivo, char escolha) {   // validar exclução do arquivo recebido
        if (validarAcao(escolha)) {
            arquivo.delete();
            return "\nArquivo deletado com sucesso!\n";
        } else
            return "\nOperação cancelada com sucesso!\n";
    }
}