package br.com.salescontroller.models;

public class ProductModel {
    private Integer id;
    private String productDescription;
    private Float price;
    private Integer stock;

    private SuppliersModel supplier;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public SuppliersModel getSupplier() {
        return this.supplier;
    }

    public void setSupplier(SuppliersModel supplier) {
        this.supplier = supplier;
    }
}
