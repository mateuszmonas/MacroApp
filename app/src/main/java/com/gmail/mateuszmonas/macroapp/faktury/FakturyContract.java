package com.gmail.mateuszmonas.macroapp.faktury;


import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

interface FakturyContract {

    interface View extends BaseView<Presenter> {

        void showFaktury(List<Faktura> faktury, boolean forceUpdate);

        void showFakturaDetails(String fakturaReference);

        void setBrakFakturView(boolean visible);

        void setBrakPolaczeniaView(boolean visible);

        void setLoadingView(boolean visible);

    }

    interface Presenter extends BasePresenter {

        void loadFaktury(int offset);

        void openFakturaDetails(String fakturaReference);

    }
}
