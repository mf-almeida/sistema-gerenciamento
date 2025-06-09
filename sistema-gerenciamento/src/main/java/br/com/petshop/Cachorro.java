package br.com.petshop;

import java.util.Objects;

// Subclasse de Animal com o atributo específico 'porte'.
public class Cachorro extends Animal {

    private String porte; // 

    // Construtor vazio
    public Cachorro() {
        super();
    }

    // Construtor com todos os campos
    public Cachorro(String nome, String racaTipo, int idade, String proprietario, String porte) {
        super(nome, racaTipo, idade, proprietario); // Inicializa os atributos da classe pai
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String toString() {
        return "Cachorro [nome=" + getNome() + ", raca=" + getRacaTipo() + ", porte=" + porte + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Compara a parte 'Animal'
        Cachorro cachorro = (Cachorro) o;
        return Objects.equals(porte, cachorro.porte); // Compara a parte 'Cachorro'
    }

    @Override
    public int hashCode() {
        // Combina o hash da parte 'Animal' com o da parte 'Cachorro'
        return Objects.hash(super.hashCode(), porte);
    }
}