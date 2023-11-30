package br.com.salescontroller.models;

public class SaleItensModel {
    
    private Integer id;
    private String product;
    private Integer quantity;
    private Float price;
    private Float subtotal;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return this.product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setSubtotal() {
        this.subtotal = this.price * this.quantity;
    }

    public Float getSubtotal() {
        return this.subtotal;
    }
}
