package com.marand.dagger3.di.main;

import com.marand.dagger3.network.main.MainApi;
import com.marand.dagger3.ui.main.UsersAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static UsersAdapter provideAdapter(){
        return new UsersAdapter();
    }

}

