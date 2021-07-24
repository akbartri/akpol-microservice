package com.akpol.commons.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction_order_detail")
public class TransactionOrderDetail extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "transaction_order_id")
    private Long transactionOrderId;
    private Long price;
    private Long quantity;
    private Long totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionOrderId() {
        return transactionOrderId;
    }

    public void setTransactionOrderId(Long transactionOrderId) {
        this.transactionOrderId = transactionOrderId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
