package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.ClientsModel;
import br.com.salescontroller.services.WebServiceCep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientsDAO {

    private Connection con;

    public ClientsDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void create(ClientsModel client) {
        try {
            String sql = "INSERT INTO tb_clients (clientname, rg, cpf, email, phone, cellphone, cep, address, addressnumber, complement, neighborhood, city, state) " 
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getRg());
            stmt.setString(3, client.getCpf());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhone());
            stmt.setString(6, client.getCellphone());
            stmt.setString(7, client.getCep());
            stmt.setString(8, client.getAddress());
            stmt.setInt(9, client.getAddressNumber());
            stmt.setString(10, client.getComplement());
            stmt.setString(11, client.getNeighborhood());
            stmt.setString(12, client.getCity());
            stmt.setString(13, client.getState());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e);
        }
    }

    public ObservableList<ClientsModel> readAll() {
        try {
            ObservableList<ClientsModel> clients = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_clients";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                ClientsModel client = new ClientsModel();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("clientname"));
                client.setRg(rs.getString("rg"));
                client.setCpf(rs.getString("cpf"));
                client.setEmail(rs.getString("email"));
                client.setPhone(rs.getString("phone"));
                client.setCellphone(rs.getString("cellphone"));
                client.setCep(rs.getString("cep"));
                client.setAddress(rs.getString("address"));
                client.setAddressNumber(rs.getInt("addressnumber"));
                client.setComplement(rs.getString("complement"));
                client.setNeighborhood(rs.getString("neighborhood"));
                client.setCity(rs.getString("city"));
                client.setState(rs.getString("state"));

                clients.add(client);
            }

            return clients;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente: " + e);
            return null;
        }
    }

    public ObservableList<ClientsModel> readByName(String clientName) {
        try {
            ObservableList<ClientsModel> clients = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_clients WHERE clientname LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, clientName);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                ClientsModel client = new ClientsModel();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("clientname"));
                client.setRg(rs.getString("rg"));
                client.setCpf(rs.getString("cpf"));
                client.setEmail(rs.getString("email"));
                client.setPhone(rs.getString("phone"));
                client.setCellphone(rs.getString("cellphone"));
                client.setCep(rs.getString("cep"));
                client.setAddress(rs.getString("address"));
                client.setAddressNumber(rs.getInt("addressnumber"));
                client.setComplement(rs.getString("complement"));
                client.setNeighborhood(rs.getString("neighborhood"));
                client.setCity(rs.getString("city"));
                client.setState(rs.getString("state"));

                clients.add(client);
            }

            return clients;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente: " + e);
            return null;
        }
    }

    public void updateAll(ClientsModel client) {
        try {
            String sql = "UPDATE tb_clients " 
                        + "SET clientname=?, rg=?, cpf=?, email=?, phone=?, cellphone=?, cep=?, address=?, addressnumber=?, complement=?, neighborhood=?, city=?, state=? " 
                        + "WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getRg());
            stmt.setString(3, client.getCpf());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhone());
            stmt.setString(6, client.getCellphone());
            stmt.setString(7, client.getCep());
            stmt.setString(8, client.getAddress());
            stmt.setInt(9, client.getAddressNumber());
            stmt.setString(10, client.getComplement());
            stmt.setString(11, client.getNeighborhood());
            stmt.setString(12, client.getCity());
            stmt.setString(13, client.getState());

            stmt.setInt(14, client.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar cliente: " + e);
        }
    }

    public void delete(ClientsModel client) {
        try {
            String sql = "DELETE FROM tb_clients WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, client.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e);
        }
    }

    public ClientsModel findCep(String cep) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        ClientsModel client = new ClientsModel();

        if (webServiceCep.wasSuccessful()) {
            client.setAddress(webServiceCep.getLogradouroFull());
            client.setCity(webServiceCep.getCidade());
            client.setNeighborhood(webServiceCep.getBairro());
            client.setState(webServiceCep.getUf());
            return client;
        } else {
            JOptionPane.showMessageDialog(null, "CEP não encontrado");
            //JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
}
