package com.example.smartcity_20.service.takeout.bean;

import java.util.List;

public class TakeoutorderBean {

    private String addressDetail;
    private String label;
    private String name;
    private String phone;
    private String amount;
    private List<OrderItemListBean> orderItemList;
    private Integer sellerId;

    @Override
    public String toString() {
        return "TakeoutorderBean{" +
                "addressDetail='" + addressDetail + '\'' +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", amount='" + amount + '\'' +
                ", orderItemList=" + orderItemList +
                ", sellerId=" + sellerId +
                '}';
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<OrderItemListBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemListBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public static class OrderItemListBean {
        private Integer productId;
        private Integer quantity;

        @Override
        public String toString() {
            return "OrderItemListBean{" +
                    "productId=" + productId +
                    ", quantity=" + quantity +
                    '}';
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
