package com.diveno.fastandroid.data.remote;

import com.diveno.fastandroid.data.model.Repo;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Diego on 15/06/2016.
 */
public interface Services {
    String URL = "https://api.github.com/";

    @GET("orgs/karumi/repos")
    Observable<List<Repo>> getRepos();

    class Creator {
        public static Services newService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(Services.class);
        }
    }
}
