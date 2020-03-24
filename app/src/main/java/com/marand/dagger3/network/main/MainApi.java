package com.marand.dagger3.network.main;

import com.marand.dagger3.model.User;
import java.util.List;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MainApi {
    @GET("users")
    Flowable<List<User>> getUsers();
}
