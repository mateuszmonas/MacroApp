package com.gmail.mateuszmonas.macroapp.kontrahenci;

import com.gmail.mateuszmonas.macroapp.BasePresenter;
import com.gmail.mateuszmonas.macroapp.BaseView;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.List;

interface KontrahenciContract {

    interface View extends BaseView<Presenter>{

        void showKontrachenci(List<Kontrahent> kontrachenci, boolean forceUpdate);

        void showFaktury(String kontrahentReference, String kontrahentName);

        void setBrakKontrahentowView(boolean visible);

        void setBrakPolaczeniaView(boolean visible);

        void setLoadingView(boolean visible);

        void setNazwa(String nazwa);

    }

    interface Presenter extends BasePresenter{

        void loadKontrachenci(int offset, String nazwa);

        void openFaktury(Kontrahent kontrahent);

        void setNazwa(String nazwa);

    }

}
