package br.com.petshop;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Properties properties;

    // Bloco estático: é executado apenas uma vez, quando a classe é carregada.
    // Ideal para carregar configurações.
    static {
        properties = new Properties();
        // Tenta encontrar e carregar o arquivo database.properties do classpath.
        try (InputStream input = ConnectionFactory.class.getResourceAsStream("/database.properties")) {
            if (input == null) {
                System.out.println("Erro: Não foi possível encontrar o arquivo database.properties.");
                throw new RuntimeException("Arquivo de configuração do banco não encontrado.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar as propriedades do banco de dados.", e);
        }
    }

    /**
     * Método estático que retorna uma conexão com o banco de dados.
     * Agora lê as credenciais do arquivo de propriedades.
     * @return um objeto Connection ou lança uma exceção se a conexão falhar.
     */
    public static Connection getConnection() {
        try {
            // Usa as propriedades carregadas para estabelecer a conexão
            return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}