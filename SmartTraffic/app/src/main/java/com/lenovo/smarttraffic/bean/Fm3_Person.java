package com.lenovo.smarttraffic.bean;

import java.io.Serializable;
import java.util.List;

public class Fm3_Person implements Serializable {


    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"peopleName":"李刚","icon":null,"status":0,"talentMarketId":1,"gold":200,"hp":100,"content":"汽车工程师"},{"id":2,"peopleName":"丁运生","icon":null,"status":1,"talentMarketId":1,"gold":50,"hp":100,"content":"汽车厂工人"},{"id":3,"peopleName":"方华高","icon":null,"status":2,"talentMarketId":1,"gold":300,"hp":100,"content":"汽车工厂技术人员"},{"id":30,"peopleName":"朱云贵","icon":null,"status":1,"talentMarketId":1,"gold":789,"hp":100,"content":"汽车厂工人"},{"id":5,"peopleName":"邹辉","icon":null,"status":0,"talentMarketId":1,"gold":150,"hp":100,"content":"汽车工程师"},{"id":6,"peopleName":"杨文","icon":null,"status":1,"talentMarketId":1,"gold":80,"hp":100,"content":"汽车厂工人"},{"id":7,"peopleName":"朱元元","icon":null,"status":2,"talentMarketId":1,"gold":200,"hp":100,"content":"汽车工厂技术人员"},{"id":8,"peopleName":"周正发","icon":null,"status":3,"talentMarketId":1,"gold":140,"hp":100,"content":"汽车质检员"},{"id":9,"peopleName":"张伟","icon":null,"status":0,"talentMarketId":1,"gold":300,"hp":100,"content":"汽车工程师"},{"id":10,"peopleName":"周丽","icon":null,"status":1,"talentMarketId":1,"gold":60,"hp":100,"content":"汽车厂工人"},{"id":12,"peopleName":"陈天云","icon":null,"status":3,"talentMarketId":1,"gold":200,"hp":100,"content":"汽车质检员"},{"id":11,"peopleName":"陈敏","icon":null,"status":2,"talentMarketId":1,"gold":140,"hp":100,"content":"技术人员"},{"id":13,"peopleName":"王百年","icon":null,"status":0,"talentMarketId":1,"gold":300,"hp":100,"content":"汽车工程师"},{"id":14,"peopleName":"王莉","icon":null,"status":1,"talentMarketId":1,"gold":90,"hp":100,"content":"汽车厂工人"},{"id":15,"peopleName":"杨保俊","icon":null,"status":2,"talentMarketId":1,"gold":400,"hp":100,"content":"汽车工厂技术人员"},{"id":16,"peopleName":"张大伟","icon":null,"status":3,"talentMarketId":1,"gold":120,"hp":100,"content":"汽车质检员"},{"id":17,"peopleName":"徐超","icon":null,"status":0,"talentMarketId":1,"gold":351,"hp":100,"content":"汽车工程师"},{"id":18,"peopleName":"于少明","icon":null,"status":1,"talentMarketId":1,"gold":130,"hp":100,"content":"汽车厂工人"},{"id":19,"peopleName":"吴雪平","icon":null,"status":2,"talentMarketId":1,"gold":456,"hp":100,"content":"汽车工厂技术人员"},{"id":22,"peopleName":"崔鹏","icon":null,"status":1,"talentMarketId":1,"gold":123,"hp":100,"content":"汽车厂工人"},{"id":23,"peopleName":"David","icon":null,"status":2,"talentMarketId":1,"gold":145,"hp":100,"content":"汽车工厂技术人员"},{"id":24,"peopleName":"张先龙","icon":null,"status":3,"talentMarketId":1,"gold":457,"hp":100,"content":"汽车质检员"},{"id":25,"peopleName":"邓宁","icon":null,"status":0,"talentMarketId":1,"gold":999,"hp":100,"content":"汽车工程师"},{"id":26,"peopleName":"钟华国","icon":null,"status":1,"talentMarketId":1,"gold":489,"hp":100,"content":"汽车厂工人"},{"id":27,"peopleName":"罗梅","icon":null,"status":2,"talentMarketId":1,"gold":888,"hp":100,"content":"汽车工厂技术人员"},{"id":29,"peopleName":"张锋","icon":null,"status":0,"talentMarketId":1,"gold":666,"hp":100,"content":"汽车工程师"},{"id":28,"peopleName":"王琪","icon":null,"status":3,"talentMarketId":1,"gold":777,"hp":100,"content":"汽车质检员"},{"id":32,"peopleName":"李芳","icon":null,"status":3,"talentMarketId":1,"gold":459,"hp":100,"content":"汽车质检员"},{"id":31,"peopleName":"李冰","icon":null,"status":2,"talentMarketId":1,"gold":479,"hp":100,"content":"技术"},{"id":4,"peopleName":"省均","icon":null,"status":3,"talentMarketId":1,"gold":150,"hp":100,"content":"汽车质检员"},{"id":21,"peopleName":"张旭","icon":null,"status":0,"talentMarketId":1,"gold":458,"hp":100,"content":"汽车工程师"},{"id":20,"peopleName":"杨庆春","icon":null,"status":3,"talentMarketId":1,"gold":365,"hp":100,"content":"汽车质检员"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * peopleName : 李刚
         * icon : null
         * status : 0
         * talentMarketId : 1
         * gold : 200
         * hp : 100
         * content : 汽车工程师
         */

        private int id;
        private String peopleName;
        private Object icon;
        private int status;
        private int talentMarketId;
        private int gold;
        private int hp;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTalentMarketId() {
            return talentMarketId;
        }

        public void setTalentMarketId(int talentMarketId) {
            this.talentMarketId = talentMarketId;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
