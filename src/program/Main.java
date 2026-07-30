package program;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.ServiceOrder;
import entities.Vehicle;
import entities.enums.OrderStatus;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			System.out.println("Dados do cliente:");
			System.out.print("Nome: ");
			String name = sc.nextLine();

			System.out.print("Celular: ");
			String cellphone = sc.nextLine();

			System.out.print("E-mail: ");
			String email = sc.nextLine();

			Client client = new Client(name, cellphone, email);

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

			Vehicle vehicle = new Vehicle(plate, model, brand, year);

			client.addVehicle(vehicle);

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
			boolean valid = false;
			OrderStatus status = null;
			do {

				System.out.print("Status (1.Open, 2.In progress, 3.Finished): ");
				int changeStatus = sc.nextInt();

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

			ServiceOrder serviceOrder = new ServiceOrder(dateService, description, price, status, vehicle);

			vehicle.addServiceOrder(serviceOrder);

			System.out.println("\n========== DADOS FINAIS ==========");
			System.out.println(client);
		} catch (

		NumberFormatException e) {
			System.out.println("Erro: não é um número válido!");
		} catch (DateTimeParseException e) {
			System.out.println("Erro: não é uma data válida!");
		} catch (InputMismatchException e) {
			System.out.println("Erro: formato inválido");
		}
		sc.close();
	}
}
