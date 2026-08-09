package entities;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	private Integer id;
	private String plate;
	private String model;
	private String mark;
	private int year;
	private Client client;

	public Vehicle() {
	}

	public Vehicle(Integer id, String plate, String model, String mark, int year) {
		this.id = id;
		this.plate = plate;
		this.model = model;
		this.mark = mark;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
	}
	public String getPlate() {
		return plate;
	}

	public String getModel() {
		return model;
	}

	public String getMark() {
		return mark;
	}

	public int getYear() {
		return year;
	}
	private List<ServiceOrder> serviceOrders = new ArrayList<>();

	public void addServiceOrder(ServiceOrder order) {
	    serviceOrders.add(order);
	}

	public List<ServiceOrder> getServiceOrders() {
	    return serviceOrders;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Placa: ").append(plate)
	      .append(", Modelo: ").append(model)
	      .append(", Marca: ").append(mark)
	      .append(", Ano: ").append(year);
	    
	    if (!serviceOrders.isEmpty()) {
	        sb.append("\nOrdens de Serviço:\n");
	        for (ServiceOrder order : serviceOrders) {
	            sb.append("  - ").append(order).append("\n");
	        }
	    }
	    return sb.toString();
	}
}
