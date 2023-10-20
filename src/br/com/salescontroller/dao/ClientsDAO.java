package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.ClientsModel;

public class ClientsDAO {

    private Connection con;

    public ClientsDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void create(ClientsModel client) {
        try {
            String sql = "INSERT INTO tb_clients VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, client.getClientName());
            stmt.setInt(2, client.getRg());
            stmt.setInt(3, client.getCpf());
            stmt.setString(4, client.getEmail());
            stmt.setInt(5, client.getPhone());
            stmt.setInt(6, client.getCellphone());
            stmt.setInt(7, client.getCep());
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
            JOptionPane.showMessageDialog(null, "Erro no cadastro: " + e);
        }
    }
}
