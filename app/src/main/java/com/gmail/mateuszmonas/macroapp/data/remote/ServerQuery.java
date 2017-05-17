package com.gmail.mateuszmonas.macroapp.data.remote;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServerQuery {

    @SerializedName("exec")
    private List<sqlBean> sql;

    ServerQuery(String id, String sql) {
        ArrayList<sqlBean> bean = new ArrayList<>();
        bean.add(new sqlBean(id, sql));
        this.sql = bean;
    }

    public List<sqlBean> getSql() {
        return sql;
    }

    public void setSql(List<sqlBean> sql) {
        this.sql = sql;
    }

    public static class sqlBean {
        /**
         * @id : q1
         * sql : select REFERENCE, KOD,NAZ,NIP,KOLOR from KH
         */

        @SerializedName("@id")
        private String id;
        private String sql;

        public sqlBean(String id, String sql) {
            this.id = id;
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
