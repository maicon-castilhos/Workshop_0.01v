package program;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;

import entities.Client;
import entities.ServiceOrder;
import entities.Vehicle;
import entities.enums.OrderStatus;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Client> clients = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n1-Novo cliente | 2-Novo veículo | 3-Nova OS | 4-Buscar | 5-Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    Client novoCliente = cadastrarCliente(sc);
                    clients.add(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    try {
                        cadastrarVeiculo(sc, clients);
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: formato inválido!");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    try {
                        cadastrarOrdemServico(sc, clients);
                    } catch (DateTimeParseException e) {
                        System.out.println("Erro: data inválida!");
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: formato inválido!");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Optional<Client> resultado = clients.stream()
                            .filter(c -> c.getCpf().equals(cpf))
                            .findFirst();
                    if (resultado.isPresent()) {
                        System.out.print("Encontrado! \n" + resultado.get());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        sc.close();
    }

    // ===================== CADASTROS =====================

    static Client cadastrarCliente(Scanner sc) {
        System.out.println("\nDados do cliente:");
        System.out.print("Nome: ");
        String name = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Celular: ");
        String cellphone = sc.nextLine();

        System.out.print("E-mail: ");
        String email = sc.nextLine();

        return new Client(null, name, cpf, cellphone, email);
    }

    static void cadastrarVeiculo(Scanner sc, List<Client> clients) {
        Client client = selecionarCliente(sc, clients);
        if (client == null) {
            return;
        }

        System.out.println("\nDados do veículo:");
        System.out.print("Placa: ");
        String plate = sc.nextLine();

        System.out.print("Modelo: ");
        String model = sc.nextLine();

        System.out.print("Marca: ");
        String brand = sc.nextLine();

        System.out.print("Ano: ");
        int year = sc.nextInt();
        sc.nextLine();

        Vehicle vehicle = new Vehicle(null, plate, model, brand, year);
        vehicle.setClient(client);
        client.addVehicle(vehicle);

        System.out.println("Veículo cadastrado para " + client.getName() + "!");
    }

    static void cadastrarOrdemServico(Scanner sc, List<Client> clients) {
        Client client = selecionarCliente(sc, clients);
        if (client == null) {
            return;
        }

        Vehicle vehicle = selecionarVeiculo(sc, client);
        if (vehicle == null) {
            return;
        }

        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy")
                .toFormatter();

        System.out.println("\nDados da ordem de serviço:");
        System.out.print("Data do serviço (dd/MM/yyyy): ");
        LocalDate dateService = LocalDate.parse(sc.nextLine(), fmt);

        System.out.print("Descrição: ");
        String description = sc.nextLine();

        System.out.print("Preço: ");
        double price;
        do {
            price = sc.nextDouble();
            if (price < 0) {
                System.out.println("O preço não pode ser negativo. Tente novamente.");
                System.out.print("Preço: ");
            }
        } while (price < 0);
        sc.nextLine();

        OrderStatus status = selecionarStatus(sc);

        ServiceOrder serviceOrder = new ServiceOrder(null, dateService, description, price, status, vehicle);
        vehicle.addServiceOrder(serviceOrder);

        System.out.println("Ordem de serviço cadastrada para o veículo " + vehicle.getPlate() + "!");
    }

    // ===================== SELEÇÃO (listas numeradas) =====================

    static Client selecionarCliente(Scanner sc, List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado ainda. Cadastre um cliente primeiro (opção 1).");
            return null;
        }

        System.out.println("\nEscolha o cliente:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + " - " + clients.get(i).getName());
        }

        System.out.print("Número: ");
        int indice = sc.nextInt();
        sc.nextLine();

        if (indice < 1 || indice > clients.size()) {
            System.out.println("Cliente inválido!");
            return null;
        }

        return clients.get(indice - 1);
    }

    static Vehicle selecionarVeiculo(Scanner sc, Client client) {
        List<Vehicle> vehicles = client.getVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("Esse cliente ainda não tem veículo cadastrado (opção 2).");
            return null;
        }

        System.out.println("\nEscolha o veículo:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + " - " + vehicles.get(i).getPlate() + " (" + vehicles.get(i).getModel() + ")");
        }

        System.out.print("Número: ");
        int indice = sc.nextInt();
        sc.nextLine();

        if (indice < 1 || indice > vehicles.size()) {
            System.out.println("Veículo inválido!");
            return null;
        }

        return vehicles.get(indice - 1);
    }

    static OrderStatus selecionarStatus(Scanner sc) {
        boolean valid = false;
        OrderStatus status = null;

        do {
            System.out.print("Status (1.Open, 2.In progress, 3.Finished): ");
            int changeStatus = sc.nextInt();
            sc.nextLine();

            switch (changeStatus) {
                case 1:
                    status = OrderStatus.OPEN;
                    valid = true;
                    break;
                case 2:
                    status = OrderStatus.IN_PROGRESS;
                    valid = true;
                    break;
                case 3:
                    status = OrderStatus.FINISHED;
                    valid = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!valid);

        return status;
    }

    // ===================== LISTAGEM =====================

    static void listarTudo(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado ainda.");
            return;
        }
        clients.forEach(System.out::println);
    }
}