package br.com.salescontroller.models;

public class SuppliersModel extends ClientsModel {

    private String cnpj;

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
