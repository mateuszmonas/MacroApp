package com.gmail.mateuszmonas.macroapp.data.remote;


import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.List;

public class ServerResponseKontrahenci {

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
        private List<Kontrahent> data;

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

        public List<Kontrahent> getData() {
            return data;
        }

        public void setData(List<Kontrahent> data) {
            this.data = data;
        }
    }
}
