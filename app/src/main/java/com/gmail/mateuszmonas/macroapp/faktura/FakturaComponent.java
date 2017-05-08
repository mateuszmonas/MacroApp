package com.gmail.mateuszmonas.macroapp.faktura;

import com.gmail.mateuszmonas.macroapp.data.KontrahentRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = KontrahentRepositoryComponent.class, modules = FakturaPresenterModule.class)
public interface FakturaComponent {

    void inject(FakturaActivity activity);

}
