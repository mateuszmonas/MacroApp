package com.gmail.mateuszmonas.macroapp.faktury;


import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

interface FakturyContract {

    interface View extends BaseView<Presenter>{

        void showFaktury(List<Faktura> faktury);

        void showFakturaDetails();

        void showBrakFakturView();

        void hideBrakFakturView();

        void showBrakPolaczenia();

        void hideBrakPolaczenia();

        void showLoadingView();

        void hideLoadingView();

    }

    interface Presenter extends BasePresenter{

        void loadFaktury();

        void openFakturaDetails();

    }
}
