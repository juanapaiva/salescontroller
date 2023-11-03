package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.EmployeesModel;
import br.com.salescontroller.services.WebServiceCep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeesDAO {

    private Connection con;

    public EmployeesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void create(EmployeesModel employee) {
        try {
            String sql = "INSERT INTO tb_employees (employeename, rg, cpf, email, passcode, position, accesslevel, phone, cellphone, cep, address, addressnumber, complement, neighborhood, city, state) " 
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRg());
            stmt.setString(3, employee.getCpf());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPasscode());
            stmt.setString(6, employee.getPosition());
            stmt.setString(7, employee.getAccessLevel());
            stmt.setString(8, employee.getPhone());
            stmt.setString(9, employee.getCellphone());
            stmt.setString(10, employee.getCep());
            stmt.setString(11, employee.getAddress());
            stmt.setInt(12, employee.getAddressNumber());
            stmt.setString(13, employee.getComplement());
            stmt.setString(14, employee.getNeighborhood());
            stmt.setString(15, employee.getCity());
            stmt.setString(16, employee.getState());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + e);
        }
    }

    public ObservableList<EmployeesModel> readAll() {
        try {
            ObservableList<EmployeesModel> employees = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_employees";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                EmployeesModel employee = new EmployeesModel();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("employeename"));
                employee.setRg(rs.getString("rg"));
                employee.setCpf(rs.getString("cpf"));
                employee.setEmail(rs.getString("email"));
                employee.setPasscode(rs.getString("passcode"));
                employee.setPosition(rs.getString("position"));
                employee.setAccessLevel(rs.getString("accesslevel"));
                employee.setPhone(rs.getString("phone"));
                employee.setCellphone(rs.getString("cellphone"));
                employee.setCep(rs.getString("cep"));
                employee.setAddress(rs.getString("address"));
                employee.setAddressNumber(rs.getInt("addressnumber"));
                employee.setComplement(rs.getString("complement"));
                employee.setNeighborhood(rs.getString("neighborhood"));
                employee.setCity(rs.getString("city"));
                employee.setState(rs.getString("state"));

                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar funcionário: " + e);
            return null;
        }
    }

    public ObservableList<EmployeesModel> readByName(String employeeName) {
        try {
            ObservableList<EmployeesModel> employees = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_employees WHERE employeename LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employeeName);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                EmployeesModel employee = new EmployeesModel();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("employeename"));
                employee.setRg(rs.getString("rg"));
                employee.setCpf(rs.getString("cpf"));
                employee.setEmail(rs.getString("email"));
                employee.setPasscode(rs.getString("passcode"));
                employee.setPosition(rs.getString("position"));
                employee.setAccessLevel(rs.getString("accesslevel"));
                employee.setPhone(rs.getString("phone"));
                employee.setCellphone(rs.getString("cellphone"));
                employee.setCep(rs.getString("cep"));
                employee.setAddress(rs.getString("address"));
                employee.setAddressNumber(rs.getInt("addressnumber"));
                employee.setComplement(rs.getString("complement"));
                employee.setNeighborhood(rs.getString("neighborhood"));
                employee.setCity(rs.getString("city"));
                employee.setState(rs.getString("state"));

                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar funcionário: " + e);
            return null;
        }
    }

    public void updateAll(EmployeesModel employee) {
        try {
            String sql = "UPDATE tb_employees " 
                        + "SET employeename=?, rg=?, cpf=?, email=?, passcode=?, position=?, accesslevel=?, phone=?, cellphone=?, cep=?, address=?, addressnumber=?, complement=?, neighborhood=?, city=?, state=? " 
                        + "WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRg());
            stmt.setString(3, employee.getCpf());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPasscode());
            stmt.setString(6, employee.getPosition());
            stmt.setString(7, employee.getAccessLevel());
            stmt.setString(8, employee.getPhone());
            stmt.setString(9, employee.getCellphone());
            stmt.setString(10, employee.getCep());
            stmt.setString(11, employee.getAddress());
            stmt.setInt(12, employee.getAddressNumber());
            stmt.setString(13, employee.getComplement());
            stmt.setString(14, employee.getNeighborhood());
            stmt.setString(15, employee.getCity());
            stmt.setString(16, employee.getState());

            stmt.setInt(17, employee.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar funcionário: " + e);
        }
    }

    public void delete(EmployeesModel employee) {
        try {
            String sql = "DELETE FROM tb_employees WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, employee.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário: " + e);
        }
    }

    public EmployeesModel findCep(String cep) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        EmployeesModel employee = new EmployeesModel();

        if (webServiceCep.wasSuccessful()) {
            employee.setAddress(webServiceCep.getLogradouroFull());
            employee.setCity(webServiceCep.getCidade());
            employee.setNeighborhood(webServiceCep.getBairro());
            employee.setState(webServiceCep.getUf());
            return employee;
        } else {
            JOptionPane.showMessageDialog(null, "CEP não encontrado");
            return null;
        }
    }

    public void login(String email, String passcode) {
        try {
            String sql = "SELECT * FROM tb_employees WHERE email = ? AND passcode = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, passcode);

            ResultSet rs = stmt.executeQuery();

            // if login is corret
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Bem vindo(a)!");
            } else {
                JOptionPane.showMessageDialog(null, "Dados incorretos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no login: " + e);
        }
    }
}
