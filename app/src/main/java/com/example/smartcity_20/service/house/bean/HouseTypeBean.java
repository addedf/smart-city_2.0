package com.example.smartcity_20.service.house.bean;

import java.util.List;

public class HouseTypeBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":2,"sourceName":"西安路 08 年框架电梯房 70 年LOFT 一室一厅封闭小区","address":"西安路 08 年框架电梯房 70 年 LOFT 一室一厅封闭小区","areaSize":88,"tel":"18546474547","price":"28300/㎡","houseType":"租房","pic":"/profile/xweb.jpg","description":"第五郡经典一室小房好楼层自住保持非常好小区一共36 栋楼,8 栋小高,28 栋洋,小区绿树"}]
     * total : 1
     */

    private int code;
    private String msg;
    private int total;
    private List<RowsBean> rows;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 2
         * sourceName : 西安路 08 年框架电梯房 70 年LOFT 一室一厅封闭小区
         * address : 西安路 08 年框架电梯房 70 年 LOFT 一室一厅封闭小区
         * areaSize : 88
         * tel : 18546474547
         * price : 28300/㎡
         * houseType : 租房
         * pic : /profile/xweb.jpg
         * description : 第五郡经典一室小房好楼层自住保持非常好小区一共36 栋楼,8 栋小高,28 栋洋,小区绿树
         */

        private int id;
        private String sourceName;
        private String address;
        private int areaSize;
        private String tel;
        private String price;
        private String houseType;
        private String pic;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaSize() {
            return areaSize;
        }

        public void setAreaSize(int areaSize) {
            this.areaSize = areaSize;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
