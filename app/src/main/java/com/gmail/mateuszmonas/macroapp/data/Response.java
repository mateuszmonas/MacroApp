package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    /**
     * q1 : {"size":4,"data":[{"KOD":"00000","NIP":"","NAZ":"Kontrahent jednorazowy","_":0},{"KOD":"00001","NIP":"1111111111111","NAZ":"Dostawca 1","_":1},{"KOD":"00002","NIP":"2222222222222","NAZ":"Dostawca 2","_":2},{"KOD":"00003","NIP":"22222222711","NAZ":"macrologic SA","_":3}],"status":0}
     */

    private Q1Bean q1;

    public Q1Bean getQ1() {
        return q1;
    }

    public void setQ1(Q1Bean q1) {
        this.q1 = q1;
    }

    public static class Q1Bean {
        /**
         * size : 4
         * data : [{"KOD":"00000","NIP":"","NAZ":"Kontrahent jednorazowy","_":0},{"KOD":"00001","NIP":"1111111111111","NAZ":"Dostawca 1","_":1},{"KOD":"00002","NIP":"2222222222222","NAZ":"Dostawca 2","_":2},{"KOD":"00003","NIP":"22222222711","NAZ":"macrologic SA","_":3}]
         * status : 0
         */

        private int size;
        private int status;
        private List<DataBean> data;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * KOD : 00000
             * NIP :
             * NAZ : Kontrahent jednorazowy
             * _ : 0
             */

            private String KOD;
            private String NIP;
            private String NAZ;
            @SerializedName("_")
            private int id;

            public String getKOD() {
                return KOD;
            }

            public void setKOD(String KOD) {
                this.KOD = KOD;
            }

            public String getNIP() {
                return NIP;
            }

            public void setNIP(String NIP) {
                this.NIP = NIP;
            }

            public String getNAZ() {
                return NAZ;
            }

            public void setNAZ(String NAZ) {
                this.NAZ = NAZ;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
