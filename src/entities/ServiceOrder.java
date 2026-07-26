package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entities.enums.OrderStatus;

public class ServiceOrder {
    private LocalDate dateService;
    private String description;
    private double price;
    private OrderStatus status;
    private Vehicle vehicle;

    public ServiceOrder() {
    }

    public ServiceOrder(LocalDate dateService, String description, double price, 
                         OrderStatus status, Vehicle vehicle) {
        this.dateService = dateService;
        this.description = description;
        setPrice(price);
        this.status = status;
        this.vehicle = vehicle;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getDateService() {
		return dateService;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
	    return "Data: " + dateService.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
	            + ", Descrição: " + description
	            + ", Preço: R$ " + String.format("%.2f", price)
	            + ", Status: " + status;
	}
}
