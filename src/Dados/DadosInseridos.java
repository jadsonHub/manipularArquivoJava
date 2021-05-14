package Dados;

public class DadosInseridos {

    private String nome;
    private float altura;
    private int idade;


    public DadosInseridos(String nome, float altura, int idade) {

        this.nome = nome;
        this.altura = altura;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Informações: " + " Nome = " + nome + " Altura = " + altura+" métro(s)"+ " Idade = " + idade +" Anos"+"\n";
    }
}
