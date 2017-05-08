package com.gmail.mateuszmonas.macroapp.kontrahenci;

import com.gmail.mateuszmonas.macroapp.data.KontrahentRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = KontrahentRepositoryComponent.class, modules = KontrahenciPresenterModule.class)
public interface KontrahenciComponent {

    void inject(KontrahenciActivity activity);

}
