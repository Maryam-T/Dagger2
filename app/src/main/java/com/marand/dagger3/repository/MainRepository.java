package com.marand.dagger3.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.marand.dagger3.model.User;
import com.marand.dagger3.network.main.MainApi;
import com.marand.dagger3.ui.main.Resource;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MainRepository {
    private MainApi mainApi;
    private MediatorLiveData<Resource<List<User>>> users;

    public MainRepository(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<User>>> observeUsers() {
        if (users == null) {
            users = new MediatorLiveData<>();
            users.setValue(Resource.loading((List<User>) null));
            final LiveData<Resource<List<User>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getUsers().onErrorReturn(new Function<Throwable, List<User>>() {
                        @Override
                        public List<User> apply(Throwable throwable) throws Exception {
                            User user = new User();
                            user.setId(-1);
                            ArrayList<User> users = new ArrayList<>();
                            users.add(user);
                            return users;
                        }
                    }).map(new Function<List<User>, Resource<List<User>>>() {
                        @Override
                        public Resource<List<User>> apply(List<User> users) throws Exception {
                            if (users.size() > 0) {
                                if (users.get(0).getId() == -1) {
                                    return Resource.error("Some error happened!", null);
                                }
                            }
                            return Resource.success(users);
                        }
                    }).subscribeOn(Schedulers.io())
            );
            users.addSource(source, new Observer<Resource<List<User>>>() {
                @Override
                public void onChanged(Resource<List<User>> listResource) {
                    users.setValue(listResource);
                    users.removeSource(source);
                }
            });
        }
        return users;
    }

}
