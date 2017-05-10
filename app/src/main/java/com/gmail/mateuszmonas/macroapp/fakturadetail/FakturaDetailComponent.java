package com.gmail.mateuszmonas.macroapp.fakturadetail;

import com.gmail.mateuszmonas.macroapp.data.DataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = DataRepositoryComponent.class, modules = FakturaDetailPresenterModule.class)
public interface FakturaDetailComponent {

    void inject(FakturaDetailActivity activity);

}
