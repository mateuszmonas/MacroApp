package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.annotation.NonNull;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View kontrahenciView;

    KontrahenciPresenter(@NonNull KontrahenciContract.View kontrahenciView) {
        this.kontrahenciView = kontrahenciView;
        kontrahenciView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
