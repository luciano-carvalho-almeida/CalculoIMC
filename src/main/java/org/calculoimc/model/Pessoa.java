package org.calculoimc.model;

public class Pessoa {
    private int id;
    private String nome;
    private double altura;
    private double peso;
    private double imc;

    public Pessoa() {
    }

    public Pessoa(String nome, double altura, double peso, double imc) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.imc = imc;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", imc=" + imc +
                '}';
    }

    public double calcularIMC() {
        this.imc = Math.round((this.peso / (this.altura * this.altura)) * 100.0) / 100.0;
        return this.imc;
    }

    public String classificacaoIMC() {
        if (this.imc < 18.5)
            return "Abaixo do Peso";
        else if (this.imc < 25)
            return "Peso Normal";
        else if (this.imc < 30)
            return "Sobrepeso";
        else if (this.imc < 35)
            return "Obesidade Grau 1";
        else if (this.imc < 39.9)
            return "Obesidade Grau 2";
        else
            return "Obesidade Grau 3";
    }
}

