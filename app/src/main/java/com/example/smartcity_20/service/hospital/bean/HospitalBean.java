package com.example.smartcity_20.service.hospital.bean;

import java.util.List;

public class HospitalBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":1,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/11/921ee654-d6c3-4876-8450-16ac272e18df.jpg","hospitalId":1}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/05/11/921ee654-d6c3-4876-8450-16ac272e18df.jpg
         * hospitalId : 1
         */

        private int id;
        private String imgUrl;
        private int hospitalId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }
    }
}
