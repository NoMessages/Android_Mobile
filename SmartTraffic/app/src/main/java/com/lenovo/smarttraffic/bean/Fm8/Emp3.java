package com.lenovo.smarttraffic.bean.Fm8;

import java.util.List;

public class Emp3 {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":"2","carName":"MPV汽车","content":"MPV汽车标准型"}]
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
         * id : 2
         * carName : MPV汽车
         * content : MPV汽车标准型
         */

        private String id;
        private String carName;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
