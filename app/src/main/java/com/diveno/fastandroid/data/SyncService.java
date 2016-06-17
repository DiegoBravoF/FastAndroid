package com.diveno.fastandroid.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.diveno.fastandroid.App;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.utils.AndroidComponentUtils;
import com.diveno.fastandroid.utils.NetworkUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by Diego on 15/06/2016.
 */
public class SyncService extends Service {

    @Inject
    DataManager dataManager;
    private Subscription subscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtils.isServiceRunning(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.get(this).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            AndroidComponentUtils.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = dataManager.syncRepos()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Repo>() {
                    @Override
                    public void onCompleted() {
                        stopSelf(startId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        stopSelf(startId);

                    }

                    @Override
                    public void onNext(Repo repos) {

                    }
                });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtils.isNetworkConnected(context)) {
                AndroidComponentUtils.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }
}
