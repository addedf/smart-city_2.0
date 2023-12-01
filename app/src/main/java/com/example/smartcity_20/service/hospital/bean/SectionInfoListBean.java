package com.example.smartcity_20.service.hospital.bean;

import java.util.List;

public class SectionInfoListBean {

    /**
     * total : 10
     * rows : [{"id":1,"orderNo":"1603537663212","patientName":"王大卫","categoryId":5,"type":"1","money":5,"reserveTime":"2021-06-02 09:00","status":"1","categoryName":"胸外科","userId":2}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * orderNo : 1603537663212
         * patientName : 王大卫
         * categoryId : 5
         * type : 1
         * money : 5
         * reserveTime : 2021-06-02 09:00
         * status : 1
         * categoryName : 胸外科
         * userId : 2
         */

        private int id;
        private String orderNo;
        private String patientName;
        private int categoryId;
        private String type;
        private int money;
        private String reserveTime;
        private String status;
        private String categoryName;
        private int userId;

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

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getReserveTime() {
            return reserveTime;
        }

        public void setReserveTime(String reserveTime) {
            this.reserveTime = reserveTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
