package br.com.petshop;

import java.util.Objects;

// Subclasse concreta que representa o serviço de Banho.
public class Banho extends Servico {

    // Construtor que define o nome e o preço base do serviço.
    public Banho() {
        super("Banho", 40.0); // Define "Banho" como nome e 40.0 como preço base.
    }

    /**
     * Implementação do método abstrato para calcular o preço do banho.
     * O preço varia conforme o porte do cachorro.
     */
    @Override
    public double calcularPreco(Animal animal) {
        double precoFinal = getPrecoBase(); // Começa com o preço base do serviço.

        // Verifica se o animal é uma instância de Cachorro.
        if (animal instanceof Cachorro) {
            Cachorro c = (Cachorro) animal; // Converte a variável para o tipo Cachorro.

            // Adiciona um valor extra dependendo do porte.
            // Esta é a regra de negócio específica para o banho em cães.
            if ("GRANDE".equalsIgnoreCase(c.getPorte())) {
                precoFinal += 20.0; // Adicional para porte grande.
            } else if ("MEDIO".equalsIgnoreCase(c.getPorte())) {
                precoFinal += 10.0; // Adicional para porte médio.
            }
        }
        // Futuramente, poderíamos adicionar outras regras, ex: para gatos de pelo longo.

        return precoFinal;
    }

    // Métodos equals e hashCode para garantir a unicidade do serviço.
    // Dois serviços de Banho são considerados iguais.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todos os objetos Banho são iguais.
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass()); // Hash com base na classe.
    }
}