package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.ProductModel;
import br.com.salescontroller.services.WebServiceCep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsDAO {

    private Connection con;

    public ProductsDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void create(ProductModel product) {
        try {
            String sql = "INSERT INTO tb_products (productdescription, price, stock, supplierid) " 
                        + "VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, product.getProductDescription());
            stmt.setFloat(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.setInt(4, product.getSupplier().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e);
        }
    }

    
}
