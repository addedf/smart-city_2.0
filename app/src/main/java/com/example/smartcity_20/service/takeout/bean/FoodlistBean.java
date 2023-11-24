package com.example.smartcity_20.service.takeout.bean;

import java.util.List;

public class FoodlistBean {

    private Integer total;
    private List<RowsBean> rows;
    private Integer code;
    private String msg;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RowsBean {
        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private Integer id;
        private String name;
        private String address;
        private String introduction;
        private Integer themeId;
        private Double score;
        private Integer saleQuantity;
        private Integer deliveryTime;
        private String imgUrl;
        private Double avgCost;
        private Object other;
        private String recommend;
        private Double distance;
        private Integer saleNum3hour;

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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Integer getThemeId() {
            return themeId;
        }

        public void setThemeId(Integer themeId) {
            this.themeId = themeId;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Integer getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(Integer saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public Integer getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Integer deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Double getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(Double avgCost) {
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

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Integer getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(Integer saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }

        public static class ParamsBean {
        }
    }
}
