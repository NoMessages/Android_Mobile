package com.lenovo.smarttraffic.bean;

import java.io.Serializable;
import java.util.List;

public class Fm7_Emp implements Serializable {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":270,"userWorkId":2,"carId":1,"userProductionLineId":1770},{"id":839,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":838,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":837,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":836,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":835,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":834,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":833,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":832,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":831,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":830,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":829,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":828,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":827,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":826,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":825,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":824,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":823,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":822,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":821,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":820,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":819,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":818,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":817,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":816,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":815,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":814,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":813,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":812,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":811,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":810,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":809,"userWorkId":1,"carId":1,"userProductionLineId":2438},{"id":808,"userWorkId":1,"carId":2,"userProductionLineId":2439},{"id":807,"userWorkId":1,"carId":1,"userProductionLineId":2438}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 270
         * userWorkId : 2
         * carId : 1
         * userProductionLineId : 1770
         */
        private int id;
        private int userWorkId;
        private int carId;
        private int userProductionLineId;
        private String productionLineName;
        private int gold;
        private String carType;
        private String content;
        private int productionLineId;

        public int getProductionLineId() {
            return productionLineId;
        }

        public void setProductionLineId(int productionLineId) {
            this.productionLineId = productionLineId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getProductionLineName() {
            return productionLineName;
        }

        public void setProductionLineName(String productionLineName) {
            this.productionLineName = productionLineName;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(int userWorkId) {
            this.userWorkId = userWorkId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }
    }
}
