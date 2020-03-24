package com.marand.dagger3.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marand.dagger3.model.User;
import com.marand.dagger3.network.main.MainApi;
import com.marand.dagger3.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;

    @Inject
    public MainViewModel(MainApi mainApi) {
        mainRepository = new MainRepository(mainApi);
    }

    public LiveData<Resource<List<User>>> getUsers() {
        return mainRepository.observeUsers();
    }
}
