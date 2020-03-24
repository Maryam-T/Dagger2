package com.marand.dagger3.di.main;

import androidx.lifecycle.ViewModel;
import com.marand.dagger3.di.ViewModelKey;
import com.marand.dagger3.ui.main.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}

