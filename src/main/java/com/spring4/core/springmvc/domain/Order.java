package com.spring4.core.springmvc.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "order_table")
public class Order implements DomainObject {
    public enum  OrderStatus {NEW, ALLOCATED, SHIPPED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Temporal(TemporalType.DATE)
    private Date shippingDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderLine> lines = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public void addOrderLines(OrderLine line) {
        this.lines.add(line);
        line.setOrder(this);
    }

    public void removeOrderLine(OrderLine line) {
        line.setOrder(null);
        this.lines.remove(line);
    }
}
