package br.com.petshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para a entidade Animal.
 * Encapsula toda a lógica de acesso ao banco de dados para os animais.
 */
public class AnimalDAO {

    /**
     * Salva um objeto Animal (Cachorro, Gato ou OutroAnimal) no banco de dados.
     * @param animal O objeto animal a ser salvo.
     */
    public void salvar(Animal animal) {
        String sql = "INSERT INTO animais (nome, raca_tipo, idade, proprietario, tipo_animal, porte, cor_olhos, tipo_especifico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // ... (código do método salvar que já fizemos)
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getRacaTipo());
            stmt.setInt(3, animal.getIdade());
            stmt.setString(4, animal.getProprietario());

            if (animal instanceof Cachorro) {
                Cachorro c = (Cachorro) animal;
                stmt.setString(5, "CACHORRO");
                stmt.setString(6, c.getPorte());
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
            } else if (animal instanceof Gato) {
                Gato g = (Gato) animal;
                stmt.setString(5, "GATO");
                stmt.setNull(6, Types.VARCHAR);
                stmt.setString(7, g.getCorOlhos());
                stmt.setNull(8, Types.VARCHAR);
            } else if (animal instanceof OutroAnimal) {
                OutroAnimal o = (OutroAnimal) animal;
                stmt.setString(5, "OUTRO");
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setString(8, o.getTipoEspecifico());
            }

            stmt.executeUpdate();
            System.out.println("Animal salvo com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar animal no banco de dados", e);
        }
    }

    /**
     * Retorna uma lista com todos os animais cadastrados no banco de dados.
     * @return Uma lista de objetos Animal.
     */
    public List<Animal> listarTodos() {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM animais";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre cada linha do resultado da consulta.
            while (rs.next()) {
                Animal animal = null;

                // Lê a coluna 'tipo_animal' para decidir qual objeto criar.
                String tipoAnimal = rs.getString("tipo_animal");

                if ("CACHORRO".equalsIgnoreCase(tipoAnimal)) {
                    Cachorro c = new Cachorro();
                    c.setPorte(rs.getString("porte")); // Define o atributo específico
                    animal = c;
                } else if ("GATO".equalsIgnoreCase(tipoAnimal)) {
                    Gato g = new Gato();
                    g.setCorOlhos(rs.getString("cor_olhos")); // Define o atributo específico
                    animal = g;
                } else if ("OUTRO".equalsIgnoreCase(tipoAnimal)) {
                    OutroAnimal o = new OutroAnimal();
                    o.setTipoEspecifico(rs.getString("tipo_especifico")); // Define o atributo específico
                    animal = o;
                }

                // Se um animal foi criado, define os atributos comuns.
                if (animal != null) {
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setRacaTipo(rs.getString("raca_tipo"));
                    animal.setIdade(rs.getInt("idade"));
                    animal.setProprietario(rs.getString("proprietario"));
                    animais.add(animal); // Adiciona o objeto populado à lista.
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais do banco de dados", e);
        }
        return animais;
    }
}