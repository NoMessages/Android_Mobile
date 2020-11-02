package com.lenovo.smarttraffic.bean;

import java.util.List;

public class Emp1 {

    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"suppierId":1,"partId":26,"gold":1100,"num":2}]
     */

    private int status;
    private String message;
    private List<DataEntity> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 1
         * suppierId : 1
         * partId : 26
         * gold : 1100
         * num : 2
         */

        private int id;
        private int suppierId;
        private int partId;
        private int gold;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSuppierId() {
            return suppierId;
        }

        public void setSuppierId(int suppierId) {
            this.suppierId = suppierId;
        }

        public int getPartId() {
            return partId;
        }

        public void setPartId(int partId) {
            this.partId = partId;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
