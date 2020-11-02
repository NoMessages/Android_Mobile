package com.lenovo.smarttraffic.bean.Fm8;

import java.util.List;

public class Emp4 {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":3,"productionLineName":"SUV车型生产线","content":"生产SUV汽车","carId":3}]
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
         * id : 3
         * productionLineName : SUV车型生产线
         * content : 生产SUV汽车
         * carId : 3
         */

        private int id;
        private String productionLineName;
        private String content;
        private int carId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductionLineName() {
            return productionLineName;
        }

        public void setProductionLineName(String productionLineName) {
            this.productionLineName = productionLineName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }
    }
}
