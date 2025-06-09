package br.com.petshop;

import java.util.Objects;

// Subclasse concreta que representa o serviço de Consulta Veterinária. 
public class ConsultaVeterinaria extends Servico {

    // Construtor que define o nome e o preço base do serviço.
    public ConsultaVeterinaria() {
        super("Consulta Veterinária", 120.0); // Preço fixo para a consulta.
    }

    /**
     * Implementação do método para calcular o preço da consulta. 
     * Neste caso, o preço é sempre o valor base.
     */
    @Override
    public double calcularPreco(Animal animal) {
        // A regra de negócio para este serviço é um preço fixo.
        return getPrecoBase();
    }

    // Métodos equals e hashCode para garantir a unicidade do serviço. 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todos os objetos ConsultaVeterinaria são iguais.
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass()); // Hash com base na classe.
    }
}