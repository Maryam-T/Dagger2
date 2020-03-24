package com.marand.dagger3.di;

import com.marand.dagger3.di.main.MainModule;
import com.marand.dagger3.di.main.MainScope;
import com.marand.dagger3.di.main.MainViewModelsModule;
import com.marand.dagger3.ui.main.MainActivity;
import com.marand.dagger3.ui.main.MainViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainViewModelsModule.class,
            MainModule.class
    })
    abstract MainActivity contributeMainActivity();

}
