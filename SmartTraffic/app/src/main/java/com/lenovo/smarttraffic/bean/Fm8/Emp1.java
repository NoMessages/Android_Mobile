package com.lenovo.smarttraffic.bean.Fm8;

import java.util.List;

public class Emp1 {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":3401,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3395,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":2},{"id":3394,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3393,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3386,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3388,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3391,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3418,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3412,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3408,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3404,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3416,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3415,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3413,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3414,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3411,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3409,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3403,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3402,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3398,"userWorkId":1,"userProductionLineId":1,"carId":1,"num":1},{"id":3410,"userWorkId":1,"userProductionLineId":1,"carId":3,"num":1},{"id":3400,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1},{"id":3399,"userWorkId":1,"userProductionLineId":1,"carId":2,"num":1}]
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
         * id : 3401
         * userWorkId : 1
         * userProductionLineId : 1
         * carId : 2
         * num : 1
         */

        private int id;
        private int userWorkId;
        private int userProductionLineId;
        private int carId;
        private int num;

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

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
