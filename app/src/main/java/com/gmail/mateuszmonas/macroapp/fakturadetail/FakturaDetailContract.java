package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.List;

interface FakturaDetailContract {

    interface View extends BaseView<Presenter> {

        void showPozycjeFaktury(List<PozycjaFaktury> pozycje);

        void showBrakPolaczenia();

        void hideBrakPolaczenia();

        void showLoadingView();

        void hideLoadingView();

    }

    interface Presenter extends BasePresenter {

        void loadFakturaDetails();

    }
}
