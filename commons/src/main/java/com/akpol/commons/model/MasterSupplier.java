package com.akpol.commons.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "master_supplier")
public class MasterSupplier extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @Column(name = "supplier_link")
    private String supplierLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
