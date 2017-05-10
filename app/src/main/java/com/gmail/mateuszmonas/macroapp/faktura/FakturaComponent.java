package com.gmail.mateuszmonas.macroapp.faktura;

import com.gmail.mateuszmonas.macroapp.data.DataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = DataRepositoryComponent.class, modules = FakturaPresenterModule.class)
public interface FakturaComponent {

    void inject(FakturaActivity activity);

}
