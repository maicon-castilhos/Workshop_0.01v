package entities;

import java.util.ArrayList;

public class Client {
    private Integer id;
    private String name;
    private String cpf;
    private String cellphone;
    private String email;

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Client() {
    }

    public Client(Integer id, String name, String cpf, String cellphone, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.cellphone = cellphone;
        this.email = email;
    }

    public String getCpf() {return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(name).append("\n");
		sb.append("CPF: ").append(cpf).append("\n");
        sb.append("Celular: ").append(cellphone).append("\n");
        sb.append("E-mail: ").append(email).append("\n");
        sb.append("Veículos:\n");

        for (Vehicle vehicle : vehicles) {
            sb.append(vehicle).append("\n");
        }

        return sb.toString();
    }
}
