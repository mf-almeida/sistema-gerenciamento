package br.com.petshop;

import java.util.Objects;

// Subclasse concreta que representa o serviço de Tosa.
public class Tosa extends Servico {

    // Construtor que define o nome e o preço base do serviço.
    public Tosa() {
        super("Tosa", 55.0); // Define "Tosa" como nome e 55.0 como preço base.
    }

    /**
     * Implementação do método abstrato para calcular o preço da tosa.
     * O preço varia conforme a raça do animal. 
     */
    @Override
    public double calcularPreco(Animal animal) {
        double precoFinal = getPrecoBase(); // Começa com o preço base do serviço.

        // Regra de negócio específica para a tosa: preço adicional para certas raças.
        String raca = animal.getRacaTipo();
        if (raca != null) {
            if (raca.equalsIgnoreCase("Poodle")) {
                precoFinal += 15.0; // Adicional para Poodle.
            } else if (raca.equalsIgnoreCase("Shih Tzu")) {
                precoFinal += 20.0; // Adicional para Shih Tzu.
            }
        }

        return precoFinal;
    }

    // Métodos equals e hashCode para garantir a unicidade do serviço.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todos os objetos Tosa são iguais.
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass()); // Hash com base na classe.
    }
}