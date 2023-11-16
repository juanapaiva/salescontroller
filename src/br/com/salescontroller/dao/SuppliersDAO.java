package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.SuppliersModel;
import br.com.salescontroller.services.WebServiceCep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SuppliersDAO {

    private Connection con;

    public SuppliersDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void create(SuppliersModel supplier) {
        try {
            String sql = "INSERT INTO tb_suppliers (suppliername, cnpj, email, phone, cellphone, cep, address, addressnumber, complement, neighborhood, city, state) " 
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getCnpj());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getPhone());
            stmt.setString(5, supplier.getCellphone());
            stmt.setString(6, supplier.getCep());
            stmt.setString(7, supplier.getAddress());
            stmt.setInt(8, supplier.getAddressNumber());
            stmt.setString(9, supplier.getComplement());
            stmt.setString(10, supplier.getNeighborhood());
            stmt.setString(11, supplier.getCity());
            stmt.setString(12, supplier.getState());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor: " + e);
        }
    }

    public ObservableList<SuppliersModel> readAll() {
        try {
            ObservableList<SuppliersModel> suppliers = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_suppliers";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SuppliersModel supplier = new SuppliersModel();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("suppliername"));
                supplier.setCnpj(rs.getString("cnpj"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setCellphone(rs.getString("cellphone"));
                supplier.setCep(rs.getString("cep"));
                supplier.setAddress(rs.getString("address"));
                supplier.setAddressNumber(rs.getInt("addressnumber"));
                supplier.setComplement(rs.getString("complement"));
                supplier.setNeighborhood(rs.getString("neighborhood"));
                supplier.setCity(rs.getString("city"));
                supplier.setState(rs.getString("state"));

                suppliers.add(supplier);
            }

            return suppliers;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar fornecedor: " + e);
            return null;
        }
    }

    public ObservableList<SuppliersModel> readByName(String supplierName) {
        try {
            ObservableList<SuppliersModel> suppliers = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tb_suppliers WHERE suppliername LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, supplierName);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SuppliersModel supplier = new SuppliersModel();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("suppliername"));
                supplier.setCnpj(rs.getString("cnpj"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setCellphone(rs.getString("cellphone"));
                supplier.setCep(rs.getString("cep"));
                supplier.setAddress(rs.getString("address"));
                supplier.setAddressNumber(rs.getInt("addressnumber"));
                supplier.setComplement(rs.getString("complement"));
                supplier.setNeighborhood(rs.getString("neighborhood"));
                supplier.setCity(rs.getString("city"));
                supplier.setState(rs.getString("state"));

                suppliers.add(supplier);
            }

            return suppliers;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar fornecedor: " + e);
            return null;
        }
    }

    public void updateAll(SuppliersModel supplier) {
        try {
            String sql = "UPDATE tb_suppliers " 
                        + "SET suppliername=?, cnpj=?, email=?, phone=?, cellphone=?, cep=?, address=?, addressnumber=?, complement=?, neighborhood=?, city=?, state=? " 
                        + "WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getCnpj());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getPhone());
            stmt.setString(5, supplier.getCellphone());
            stmt.setString(6, supplier.getCep());
            stmt.setString(7, supplier.getAddress());
            stmt.setInt(8, supplier.getAddressNumber());
            stmt.setString(9, supplier.getComplement());
            stmt.setString(10, supplier.getNeighborhood());
            stmt.setString(11, supplier.getCity());
            stmt.setString(12, supplier.getState());

            stmt.setInt(13, supplier.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar fornecedor: " + e);
        }
    }

    public void delete(SuppliersModel supplier) {
        try {
            String sql = "DELETE FROM tb_suppliers WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, supplier.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + e);
        }
    }

    public SuppliersModel findCep(String cep) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        SuppliersModel supplier = new SuppliersModel();

        if (webServiceCep.wasSuccessful()) {
            supplier.setAddress(webServiceCep.getLogradouroFull());
            supplier.setCity(webServiceCep.getCidade());
            supplier.setNeighborhood(webServiceCep.getBairro());
            supplier.setState(webServiceCep.getUf());
            return supplier;
        } else {
            JOptionPane.showMessageDialog(null, "CEP não encontrado");
            return null;
        }

    }
}
