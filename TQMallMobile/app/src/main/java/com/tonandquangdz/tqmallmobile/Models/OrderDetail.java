package com.tonandquangdz.tqmallmobile.Models;

public class OrderDetail {
    private int ID;
    private int IDOrder;
    private int IDProductDetails;
    private int Quantity;
    private  Order Order;
    private  ProductDetail ProductDetail;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(int IDOrder) {
        this.IDOrder = IDOrder;
    }

    public int getIDProductDetails() {
        return IDProductDetails;
    }

    public void setIDProductDetails(int IDProductDetails) {
        this.IDProductDetails = IDProductDetails;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public com.tonandquangdz.tqmallmobile.Models.Order getOrder() {
        return Order;
    }

    public void setOrder(com.tonandquangdz.tqmallmobile.Models.Order order) {
        Order = order;
    }

    public com.tonandquangdz.tqmallmobile.Models.ProductDetail getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(com.tonandquangdz.tqmallmobile.Models.ProductDetail productDetail) {
        ProductDetail = productDetail;
    }

    public OrderDetail(int ID, int IDOrder, int IDProductDetails, int quantity, com.tonandquangdz.tqmallmobile.Models.Order order, com.tonandquangdz.tqmallmobile.Models.ProductDetail productDetail) {
        this.ID = ID;
        this.IDOrder = IDOrder;
        this.IDProductDetails = IDProductDetails;
        Quantity = quantity;
        Order = order;
        ProductDetail = productDetail;
    }
}
