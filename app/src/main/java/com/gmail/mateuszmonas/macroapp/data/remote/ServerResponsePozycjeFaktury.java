package com.gmail.mateuszmonas.macroapp.data.remote;


import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.List;

public class ServerResponsePozycjeFaktury {

    /**
     * q1 : {"size":2,"data":[{"IL":2,"NAZ":"sztuka","WN":131.78,"NX":"Zestaw do kaniulacji dużych naczyń III-kanałowy 7F/15","POZ":1,"CN":65.89,"WB":142.32,"WV":10.54,"_":0},{"IL":250,"NAZ":"sztuka","WN":85,"NX":"Przedłużacz do pompy infuzyjnej dł.1500 mm","POZ":2,"CN":0.34,"WB":91.8,"WV":6.8,"_":1}],"status":0}
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
         * size : 2
         * data : [{"IL":2,"NAZ":"sztuka","WN":131.78,"NX":"Zestaw do kaniulacji dużych naczyń III-kanałowy 7F/15","POZ":1,"CN":65.89,"WB":142.32,"WV":10.54,"_":0},{"IL":250,"NAZ":"sztuka","WN":85,"NX":"Przedłużacz do pompy infuzyjnej dł.1500 mm","POZ":2,"CN":0.34,"WB":91.8,"WV":6.8,"_":1}]
         * status : 0
         */

        private int size;
        private int status;
        private List<PozycjaFaktury> data;

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

        public List<PozycjaFaktury> getData() {
            return data;
        }

        public void setData(List<PozycjaFaktury> data) {
            this.data = data;
        }
    }
}
