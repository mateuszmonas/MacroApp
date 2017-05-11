package com.gmail.mateuszmonas.macroapp.data.remote;


import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

public class ServerResponseFaktury {

    /**
     * q1 : {"size":10,"data":[{"BRUTTO":60.83,"NAZ0002":"DOK","NETTO":0,"NAZ":"***","TZ":"2003-07-20","UL":"","VAT":60.83,"_":0},{"BRUTTO":-44,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-44,"NAZ":"***","TZ":"2003-08-27","UL":"","VAT":0,"_":1},{"BRUTTO":-96,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-96,"NAZ":"***","TZ":"2003-06-21","UL":"","VAT":0,"_":2},{"BRUTTO":-96,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-96,"NAZ":"***","TZ":"2003-06-21","UL":"","VAT":0,"_":3},{"BRUTTO":-313.94,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-293.4,"NAZ":"***","TZ":"2004-03-22","UL":"","VAT":-20.54,"_":4},{"BRUTTO":-100,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-100,"NAZ":"***","TZ":"2004-02-17","UL":"","VAT":0,"_":5},{"BRUTTO":-13,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-13,"NAZ":"***","TZ":"2004-02-17","UL":"","VAT":0,"_":6},{"BRUTTO":-48.5,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-48.5,"NAZ":"***","TZ":"2004-01-08","UL":"","VAT":0,"_":7},{"BRUTTO":3742.46,"NAZ0002":"DOK","NETTO":3677.48,"NAZ":"***","TZ":"2003-06-28","UL":"","VAT":64.98,"_":8},{"BRUTTO":240,"NAZ0002":"DOK","NETTO":240,"NAZ":"***","TZ":"2003-06-03","UL":"","VAT":0,"_":9}],"status":0}
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
         * size : 10
         * data : [{"BRUTTO":60.83,"NAZ0002":"DOK","NETTO":0,"NAZ":"***","TZ":"2003-07-20","UL":"","VAT":60.83,"_":0},{"BRUTTO":-44,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-44,"NAZ":"***","TZ":"2003-08-27","UL":"","VAT":0,"_":1},{"BRUTTO":-96,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-96,"NAZ":"***","TZ":"2003-06-21","UL":"","VAT":0,"_":2},{"BRUTTO":-96,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-96,"NAZ":"***","TZ":"2003-06-21","UL":"","VAT":0,"_":3},{"BRUTTO":-313.94,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-293.4,"NAZ":"***","TZ":"2004-03-22","UL":"","VAT":-20.54,"_":4},{"BRUTTO":-100,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-100,"NAZ":"***","TZ":"2004-02-17","UL":"","VAT":0,"_":5},{"BRUTTO":-13,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-13,"NAZ":"***","TZ":"2004-02-17","UL":"","VAT":0,"_":6},{"BRUTTO":-48.5,"NAZ0002":"OPOLSKIE-Bożena Lika","NETTO":-48.5,"NAZ":"***","TZ":"2004-01-08","UL":"","VAT":0,"_":7},{"BRUTTO":3742.46,"NAZ0002":"DOK","NETTO":3677.48,"NAZ":"***","TZ":"2003-06-28","UL":"","VAT":64.98,"_":8},{"BRUTTO":240,"NAZ0002":"DOK","NETTO":240,"NAZ":"***","TZ":"2003-06-03","UL":"","VAT":0,"_":9}]
         * status : 0
         */

        private int size;
        private int status;
        private List<Faktura> data;

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

        public List<Faktura> getData() {
            return data;
        }

        public void setData(List<Faktura> data) {
            this.data = data;
        }
    }
}

