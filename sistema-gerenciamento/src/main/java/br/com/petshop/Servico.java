package br.com.petshop;

// Classe abstrata que serve como modelo para todos os serviços. 
public abstract class Servico {

    private String nome; // 
    private double precoBase; // 

    // Construtor para inicializar os atributos comuns
    public Servico(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    // --- Método Abstrato ---
    // As subclasses serão obrigadas a implementar este método. 
    public abstract double calcularPreco(Animal animal);

    // --- Getters ---
    // Métodos para acessar os atributos.
    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    @Override
    public String toString() { // 
        return "Servico [nome=" + nome + ", precoBase=" + precoBase + "]";
    }
}