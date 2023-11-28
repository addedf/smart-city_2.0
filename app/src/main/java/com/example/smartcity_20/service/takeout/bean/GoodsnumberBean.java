package com.example.smartcity_20.service.takeout.bean;

import java.util.List;

public class GoodsnumberBean {

    private int total;
    private int code;
    private String msg;
    private List<RowsDTO> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        private SellerInfoDTO sellerInfo;
        private OrderInfoDTO orderInfo;

        public SellerInfoDTO getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(SellerInfoDTO sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public OrderInfoDTO getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoDTO orderInfo) {
            this.orderInfo = orderInfo;
        }

        public static class SellerInfoDTO {
            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsDTO params;
            private int id;
            private String name;
            private String address;
            private String introduction;
            private int themeId;
            private double score;
            private int saleQuantity;
            private int deliveryTime;
            private String imgUrl;
            private double avgCost;
            private Object other;
            private String recommend;
            private double distance;
            private int saleNum3hour;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsDTO getParams() {
                return params;
            }

            public void setParams(ParamsDTO params) {
                this.params = params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getThemeId() {
                return themeId;
            }

            public void setThemeId(int themeId) {
                this.themeId = themeId;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public int getSaleQuantity() {
                return saleQuantity;
            }

            public void setSaleQuantity(int saleQuantity) {
                this.saleQuantity = saleQuantity;
            }

            public int getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(int deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public double getAvgCost() {
                return avgCost;
            }

            public void setAvgCost(double avgCost) {
                this.avgCost = avgCost;
            }

            public Object getOther() {
                return other;
            }

            public void setOther(Object other) {
                this.other = other;
            }

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getSaleNum3hour() {
                return saleNum3hour;
            }

            public void setSaleNum3hour(int saleNum3hour) {
                this.saleNum3hour = saleNum3hour;
            }

            public static class ParamsDTO {
            }
        }

        public static class OrderInfoDTO {
            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsDTOX params;
            private int id;
            private String orderNo;
            private int userId;
            private int sellerId;
            private double amount;
            private Object postage;
            private String status;
            private Object paymentType;
            private Object payTime;
            private Object sendTime;
            private String receiverName;
            private String receiverPhone;
            private String receiverAddress;
            private String receiverLabel;
            private Object houseNumber;
            private List<OrderItemListDTO> orderItemList;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsDTOX getParams() {
                return params;
            }

            public void setParams(ParamsDTOX params) {
                this.params = params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSellerId() {
                return sellerId;
            }

            public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public Object getPostage() {
                return postage;
            }

            public void setPostage(Object postage) {
                this.postage = postage;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(Object paymentType) {
                this.paymentType = paymentType;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public Object getSendTime() {
                return sendTime;
            }

            public void setSendTime(Object sendTime) {
                this.sendTime = sendTime;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverLabel() {
                return receiverLabel;
            }

            public void setReceiverLabel(String receiverLabel) {
                this.receiverLabel = receiverLabel;
            }

            public Object getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(Object houseNumber) {
                this.houseNumber = houseNumber;
            }

            public List<OrderItemListDTO> getOrderItemList() {
                return orderItemList;
            }

            public void setOrderItemList(List<OrderItemListDTO> orderItemList) {
                this.orderItemList = orderItemList;
            }

            public static class ParamsDTOX {
            }

            public static class OrderItemListDTO {
                private Object searchValue;
                private Object createBy;
                private String createTime;
                private Object updateBy;
                private Object updateTime;
                private Object remark;
                private ParamsDTOXX params;
                private int id;
                private int userId;
                private String orderNo;
                private int productId;
                private String productName;
                private String productImage;
                private double productPrice;
                private int quantity;
                private double totalPrice;

                public Object getSearchValue() {
                    return searchValue;
                }

                public void setSearchValue(Object searchValue) {
                    this.searchValue = searchValue;
                }

                public Object getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(Object createBy) {
                    this.createBy = createBy;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public Object getUpdateBy() {
                    return updateBy;
                }

                public void setUpdateBy(Object updateBy) {
                    this.updateBy = updateBy;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public ParamsDTOXX getParams() {
                    return params;
                }

                public void setParams(ParamsDTOXX params) {
                    this.params = params;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getProductImage() {
                    return productImage;
                }

                public void setProductImage(String productImage) {
                    this.productImage = productImage;
                }

                public double getProductPrice() {
                    return productPrice;
                }

                public void setProductPrice(double productPrice) {
                    this.productPrice = productPrice;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(double totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public static class ParamsDTOXX {
                }
            }
        }
    }
}
