package br.com.petshop;

import java.util.Objects;

// Subclasse de Animal para outros tipos, com o atributo 'tipoEspecifico'.
public class OutroAnimal extends Animal {

    private String tipoEspecifico; //

    // Construtor vazio
    public OutroAnimal() {
        super();
    }

    // Construtor com todos os campos
    public OutroAnimal(String nome, String racaTipo, int idade, String proprietario, String tipoEspecifico) {
        super(nome, racaTipo, idade, proprietario); // Inicializa os atributos da classe pai
        this.tipoEspecifico = tipoEspecifico;
    }

    public String getTipoEspecifico() {
        return tipoEspecifico;
    }

    public void setTipoEspecifico(String tipoEspecifico) {
        this.tipoEspecifico = tipoEspecifico;
    }

    @Override
    public String toString() { //
        return "OutroAnimal [nome=" + getNome() + ", tipo=" + tipoEspecifico + "]";
    }

    @Override
    public boolean equals(Object o) { //
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Compara a parte 'Animal'
        OutroAnimal that = (OutroAnimal) o;
        return Objects.equals(tipoEspecifico, that.tipoEspecifico); // Compara a parte 'OutroAnimal'
    }

    @Override
    public int hashCode() { //
        // Combina o hash da parte 'Animal' com o da parte 'OutroAnimal'
        return Objects.hash(super.hashCode(), tipoEspecifico);
    }
}