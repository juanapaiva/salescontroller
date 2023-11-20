package br.com.salescontroller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.salescontroller.jdbc.ConnectionFactory;
import br.com.salescontroller.models.ProductModel;
import br.com.salescontroller.models.SuppliersModel;
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

    public ObservableList<ProductModel> readAll() {
        try {
            ObservableList<ProductModel> products = FXCollections.observableArrayList();
            String sql = "SELECT p.id, p.productdescription, p.price, p.stock, s.suppliername FROM tb_products p "
                        + "INNER JOIN tb_suppliers s ON (p.supplierid = s.id)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                ProductModel product = new ProductModel();
                SuppliersModel supplier = new SuppliersModel();

                product.setId(rs.getInt("p.id"));
                product.setProductDescription(rs.getString("p.productdescription"));
                product.setPrice(rs.getFloat("p.price"));
                product.setStock(rs.getInt("p.stock"));

                supplier.setName(rs.getString("s.suppliername"));
                product.setSupplier(supplier);

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto: " + e);
            return null;
        }
    }

    public ObservableList<ProductModel> readByProductDescription(String productDescription, SuppliersModel supplierObj) {
        try {
            ObservableList<ProductModel> products = FXCollections.observableArrayList();
            String sql = "SELECT p.id, p.productdescription, p.price, p.stock, s.suppliername FROM tb_products p "
                        + "INNER JOIN tb_suppliers s ON (p.supplierid = s.id) "
                        + "WHERE p.productdescription LIKE coalesce(?, p.productdescription) "
                        + "AND s.suppliername = coalesce(?, s.suppliername)";
            PreparedStatement stmt = con.prepareStatement(sql);

            if (productDescription.isEmpty())
                stmt.setNull(1, 0);
            else
                stmt.setString(1, "%"+productDescription+"%");

            if (supplierObj == null)
                stmt.setNull(2, 0);
            else
                stmt.setString(2, supplierObj.getName());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                ProductModel product = new ProductModel();
                SuppliersModel supplier = new SuppliersModel();

                product.setId(rs.getInt("p.id"));
                product.setProductDescription(rs.getString("p.productdescription"));
                product.setPrice(rs.getFloat("p.price"));
                product.setStock(rs.getInt("p.stock"));

                supplier.setName(rs.getString("s.suppliername"));
                product.setSupplier(supplier);

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto: " + e);
            return null;
        }
    }

    public void updateAll(ProductModel product) {
        try {
            String sql = "UPDATE tb_products " 
                        + "SET productdescription=?, price=?, stock=?, supplierid=? " 
                        + "WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, product.getProductDescription());
            stmt.setFloat(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.setInt(4, product.getSupplier().getId());

            stmt.setInt(5, product.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto: " + e);
        }
    }

    public void delete(ProductModel product) {
        try {
            String sql = "DELETE FROM tb_products WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, product.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e);
        }
    }

}
