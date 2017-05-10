package com.gmail.mateuszmonas.macroapp.Faktury;


import com.gmail.mateuszmonas.macroapp.data.DataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = DataRepositoryComponent.class, modules = FakturyPresenterModule.class)
public interface FakturyComponent {

    void inject(FakturyActivity activity);

}
