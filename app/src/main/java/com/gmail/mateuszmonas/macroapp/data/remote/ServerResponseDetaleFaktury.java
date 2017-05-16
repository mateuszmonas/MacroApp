package com.gmail.mateuszmonas.macroapp.data.remote;


import com.gmail.mateuszmonas.macroapp.data.DetaleFaktury;

import java.util.List;

public class ServerResponseDetaleFaktury {

    /**
     * q1 : {"size":1,"data":[{"MIASTO":"Piaseczno","NIP":"123-000-99-04","NAZ":"Stanmed Włodzimierz Stańczyk","UL":"Kajki 18","KPOCZ":"05-500","_":0}],"status":0}
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
         * size : 1
         * data : [{"MIASTO":"Piaseczno","NIP":"123-000-99-04","NAZ":"Stanmed Włodzimierz Stańczyk","UL":"Kajki 18","KPOCZ":"05-500","_":0}]
         * status : 0
         */

        private int size;
        private int status;
        private List<DetaleFaktury> data;

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

        public List<DetaleFaktury> getData() {
            return data;
        }

        public void setData(List<DetaleFaktury> data) {
            this.data = data;
        }
    }
}
