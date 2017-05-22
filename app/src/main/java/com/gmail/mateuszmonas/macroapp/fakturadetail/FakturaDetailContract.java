package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.DetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.List;

interface FakturaDetailContract {

    interface View extends BaseView<Presenter> {

        void showPozycjeFaktury(List<PozycjaFaktury> pozycje);

        void showDetaleFaktury(DetaleFaktury detaleFaktury);

        void setBrakPolaczeniaView(boolean visible);

        void setLoadingView(boolean visible);

    }

    interface Presenter extends BasePresenter {

        void loadDetaleFaktury(boolean forceUpdate);

    }
}
