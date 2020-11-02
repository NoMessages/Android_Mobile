package com.lenovo.smarttraffic.bean;

import java.io.Serializable;
import java.util.List;

public class Fm4_Emp implements Serializable {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":2441,"userWorkId":1,"stageId":5,"productionLineId":2,"type":0,"position":1,"isAI":0},{"id":2440,"userWorkId":1,"stageId":25,"productionLineId":1,"type":0,"position":0,"isAI":0},{"id":2439,"userWorkId":1,"stageId":5,"productionLineId":2,"type":0,"position":3,"isAI":0},{"id":2438,"userWorkId":1,"stageId":26,"productionLineId":1,"type":0,"position":2,"isAI":1}]
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
         * id : 2441
         * userWorkId : 1
         * stageId : 5
         * productionLineId : 2
         * type : 0
         * position : 1
         * isAI : 0
         */
        private int id;
        private int position;
        private List<Person> personList;

        public List<Person> getPersonList() {
            return personList;
        }

        public void setPersonList(List<Person> personList) {
            this.personList = personList;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public static class Person implements Serializable{
                private String peopleName;
                private int status;
                private int peopleId;
                private int hp;

            public int getHp() {
                return hp;
            }

            public void setHp(int hp) {
                this.hp = hp;
            }

            public Person(int peopleId) {
                this.peopleId = peopleId;
            }

            public int getPeopleId() {
                return peopleId;
            }

            public void setPeopleId(int peopleId) {
                this.peopleId = peopleId;
            }

            public String getPeopleName() {
                return peopleName;
            }

            public void setPeopleName(String peopleName) {
                this.peopleName = peopleName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

    }



}