package com.example.smartcity_20.service.hospital.bean;

import java.util.List;

public class HouseLisetBean {

    /**
     * total : 9
     * rows : [{"id":10,"hospitalName":"大连市专科医院","brief":"大连市专科创建于 1952 年 6 月 1 日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18 岁以下儿童的医疗、预防、康复、保健任务","level":4,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/08/e5d369ab-6f50-4f06-9b8d-515ddd15d887.jpg"}]
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
         * id : 10
         * hospitalName : 大连市专科医院
         * brief : 大连市专科创建于 1952 年 6 月 1 日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18 岁以下儿童的医疗、预防、康复、保健任务
         * level : 4
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/05/08/e5d369ab-6f50-4f06-9b8d-515ddd15d887.jpg
         */

        private int id;
        private String hospitalName;
        private String brief;
        private int level;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
