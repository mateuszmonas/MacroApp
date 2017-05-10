package com.gmail.mateuszmonas.macroapp.Faktury;


import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

interface FakturyContract {

    interface View extends BaseView<Presenter>{

        void showFaktury(List<Faktura> faktury);

        void showFakturaDetails();

    }

    interface Presenter extends BasePresenter{

        void loadFaktury();

        void openFakturaDetails();

    }

}
