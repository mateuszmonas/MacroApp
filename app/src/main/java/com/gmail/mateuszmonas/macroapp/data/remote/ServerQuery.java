package com.gmail.mateuszmonas.macroapp.data.remote;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class ServerQuery {

    @SerializedName("exec")
    private List<sqlBean> sql;

    ServerQuery(String sql) {
        ArrayList<sqlBean> bean = new ArrayList<>();
        bean.add(new sqlBean(sql));
        this.sql = bean;
    }

    public List<sqlBean> getSql() {
        return sql;
    }

    public void setSql(List<sqlBean> sql) {
        this.sql = sql;
    }

    private static class sqlBean {
        /**
         * "@id" : q1
         * sql : select REFERENCE, KOD,NAZ,NIP,KOLOR from KH
         */

        @SerializedName("@id")
        private String id = "q1";
        private String sql;

        sqlBean(String sql) {
            this.sql = sql;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }
    }
}
