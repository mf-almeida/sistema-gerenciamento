package br.com.petshop;

import java.util.Objects;

// Classe abstrata que representa um animal do petshop
public abstract class Animal {

    private int id; 
    
    private String nome; // 
    private String racaTipo; // 
    private int idade; // 
    private String proprietario; // 

    public Animal() {
    }

    public Animal(String nome, String racaTipo, int idade, String proprietario) {
        this.nome = nome;
        this.racaTipo = racaTipo;
        this.idade = idade;
        this.proprietario = proprietario;
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRacaTipo() {
        return racaTipo;
    }

    public void setRacaTipo(String racaTipo) {
        this.racaTipo = racaTipo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    // --- Métodos toString, equals e hashCode --- 

    @Override
    public String toString() {
        return "Animal [nome=" + nome + ", racaTipo=" + racaTipo + ", proprietario=" + proprietario + "]";
    }
    /**
     * Compara dois objetos Animal. Consideramos dois animais iguais se tiverem o mesmo nome e o mesmo proprietário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(nome, animal.nome) && Objects.equals(proprietario, animal.proprietario);
    }
    /**
     * Gera um código hash para o objeto, baseado no nome e no proprietário.
     * Importante para o funcionamento correto de coleções como HashMap e HashSet.
     * Tive que gerar essa parte final com i.a :/ não consegui resolver esse erro sem pesquisar */
     
    @Override
    public int hashCode() {
        return Objects.hash(nome, proprietario);
    }
}