package br.com.petshop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que executa o menu interativo para gerenciar o Petshop.
 */
public class GerenciadorPetshop {

    // Scanner para ler a entrada do usuário e DAO para acessar o banco.
    // Declarados como 'static final' para serem acessíveis por todos os métodos estáticos.
    private static final Scanner scanner = new Scanner(System.in);
    private static final AnimalDAO animalDAO = new AnimalDAO();

    public static void main(String[] args) {
        // Laço de repetição principal que mantém o menu ativo.
        while (true) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha pendente.

                switch (opcao) {
                    case 1:
                        cadastrarAnimal();
                        break;
                    case 2:
                        listarAnimais();
                        break;
                    case 3:
                        calcularPrecoServico();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até logo!");
                        return; // Encerra o programa.
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                scanner.nextLine(); // Limpa o buffer do scanner para evitar um loop infinito.
            }
        }
    }

    /**
     * Exibe o menu principal de opções para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL DO PETSHOP ---");
        System.out.println("1. Cadastrar Novo Animal");
        System.out.println("2. Listar Todos os Animais");
        System.out.println("3. Calcular Preço de Serviço");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Conduz o processo de cadastro de um novo animal, solicitando os dados ao usuário.
     */
    private static void cadastrarAnimal() {
        System.out.println("\n--- Cadastro de Novo Animal ---");
        System.out.println("Qual tipo de animal deseja cadastrar?");
        System.out.println("1. Cachorro");
        System.out.println("2. Gato");
        System.out.println("3. Outro");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha.

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Raça/Tipo: ");
        String racaTipo = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do Proprietário: ");
        String proprietario = scanner.nextLine();

        Animal novoAnimal = null;

        switch (tipo) {
            case 1:
                System.out.print("Porte (Pequeno, Medio, Grande): ");
                String porte = scanner.nextLine();
                novoAnimal = new Cachorro(nome, racaTipo, idade, proprietario, porte);
                break;
            case 2:
                System.out.print("Cor dos Olhos: ");
                String corOlhos = scanner.nextLine();
                novoAnimal = new Gato(nome, racaTipo, idade, proprietario, corOlhos);
                break;
            case 3:
                System.out.print("Tipo Específico (ex: Hamster, Papagaio): ");
                String tipoEspecifico = scanner.nextLine();
                novoAnimal = new OutroAnimal(nome, racaTipo, idade, proprietario, tipoEspecifico);
                break;
            default:
                System.out.println("Tipo de animal inválido.");
                return;
        }

        animalDAO.salvar(novoAnimal);
    }

    /**
     * Busca e exibe todos os animais cadastrados no banco de dados em um formato de tabela.
     */
    private static void listarAnimais() {
        System.out.println("\n--- Lista de Animais Cadastrados ---");
        List<Animal> animais = animalDAO.listarTodos();
        
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado no momento.");
            return;
        }

        // Imprime o cabeçalho da tabela
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-10s | %-20s | %-5s | %-15s | %-20s%n", 
                          "ID", "NOME", "TIPO", "RAÇA/TIPO", "IDADE", "PROPRIETÁRIO", "DETALHE ESPECÍFICO");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        // Itera sobre a lista de animais para imprimir cada linha da tabela
        for (Animal animal : animais) {
            String tipo = "";
            String detalhe = "";

            // Verifica o tipo do animal para preencher as colunas "TIPO" e "DETALHE"
            if (animal instanceof Cachorro) {
                tipo = "Cachorro";
                detalhe = "Porte: " + ((Cachorro) animal).getPorte();
            } else if (animal instanceof Gato) {
                tipo = "Gato";
                detalhe = "Cor Olhos: " + ((Gato) animal).getCorOlhos();
            } else if (animal instanceof OutroAnimal) {
                tipo = "Outro";
                detalhe = "Tipo: " + ((OutroAnimal) animal).getTipoEspecifico();
            }

            // Imprime a linha formatada com os dados do animal
            System.out.printf("%-5d | %-15s | %-10s | %-20s | %-5d | %-15s | %-20s%n",
                              animal.getId(),
                              animal.getNome(),
                              tipo,
                              animal.getRacaTipo(),
                              animal.getIdade(),
                              animal.getProprietario(),
                              detalhe);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    /**
     * Conduz o processo de cálculo de preço de um serviço para um animal específico.
     */
    private static void calcularPrecoServico() {
        System.out.println("\n--- Cálculo de Preço de Serviço ---");
        List<Animal> animais = animalDAO.listarTodos();
        if (animais.isEmpty()) {
            System.out.println("É necessário cadastrar um animal primeiro.");
            return;
        }

        // Mostra a lista de animais para o usuário escolher
        for (Animal animal : animais) {
            System.out.println("ID: " + animal.getId() + " - " + animal.getNome());
        }
        System.out.print("Digite o ID do animal para o serviço: ");
        int idAnimal = scanner.nextInt();
        scanner.nextLine();

        // Encontra o animal escolhido na lista
        Animal animalEscolhido = null;
        for (Animal animal : animais) {
            if (animal.getId() == idAnimal) {
                animalEscolhido = animal;
                break;
            }
        }

        if (animalEscolhido == null) {
            System.out.println("Animal com o ID informado não encontrado.");
            return;
        }

        System.out.println("Escolha o serviço:");
        System.out.println("1. Banho");
        System.out.println("2. Tosa");
        System.out.println("3. Consulta Veterinária");
        System.out.print("Opção: ");
        int tipoServico = scanner.nextInt();
        scanner.nextLine();

        Servico servico = null;
        switch (tipoServico) {
            case 1: servico = new Banho(); break;
            case 2: servico = new Tosa(); break;
            case 3: servico = new ConsultaVeterinaria(); break;
            default: System.out.println("Serviço inválido."); return;
        }
        
        double preco = servico.calcularPreco(animalEscolhido);
        System.out.printf("O preço do serviço '%s' para o animal '%s' é: R$ %.2f\n", servico.getNome(), animalEscolhido.getNome(), preco);
    }
}