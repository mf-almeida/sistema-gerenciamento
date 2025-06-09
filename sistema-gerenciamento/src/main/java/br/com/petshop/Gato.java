package br.com.petshop;

import java.util.Objects;

// Subclasse de Animal com o atributo específico 'corOlhos'. 
public class Gato extends Animal {

    private String corOlhos; // 

    // Construtor vazio
    public Gato() {
        super();
    }

    // Construtor com todos os campos
    public Gato(String nome, String racaTipo, int idade, String proprietario, String corOlhos) {
        super(nome, racaTipo, idade, proprietario); // Inicializa os atributos da classe pai
        this.corOlhos = corOlhos;
    }

    public String getCorOlhos() {
        return corOlhos;
    }

    public void setCorOlhos(String corOlhos) {
        this.corOlhos = corOlhos;
    }

    @Override
    public String toString() { // 
        return "Gato [nome=" + getNome() + ", raca=" + getRacaTipo() + ", corOlhos=" + corOlhos + "]";
    }

    @Override
    public boolean equals(Object o) { // 
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Compara a parte 'Animal'
        Gato gato = (Gato) o;
        return Objects.equals(corOlhos, gato.corOlhos); // Compara a parte 'Gato'
    }

    @Override
    public int hashCode() { // 
        // Combina o hash da parte 'Animal' com o da parte 'Gato'
        return Objects.hash(super.hashCode(), corOlhos);
    }
}