package com.akpol.commons.model.dto;

public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String supplierLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSupplierLink() {
        return supplierLink;
    }

    public void setSupplierLink(String supplierLink) {
        this.supplierLink = supplierLink;
    }
}
