package com.diveno.fastandroid.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diveno.fastandroid.data.model.Repo;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Diego on 15/06/2016.
 */
public class DatabaseHelper {
    private final BriteDatabase db;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        db = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper);
    }

    public BriteDatabase getBriteDb() {
        return db;
    }

    public Observable<Repo> setRepos(final Collection<Repo> newRepos) {
        return Observable.create(new Observable.OnSubscribe<Repo>() {
            @Override
            public void call(Subscriber<? super Repo> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                BriteDatabase.Transaction transaction = db.newTransaction();
                try {
                    db.delete(Db.RepoTable.TABLE_NAME, null);
                    for (Repo repo : newRepos) {
                        long result = db.insert(Db.RepoTable.TABLE_NAME, Db.RepoTable.toContentValues(repo), SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) {
                            subscriber.onNext(repo);
                        }
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Repo>> getRepos() {
        return db.createQuery(Db.RepoTable.TABLE_NAME, "SELECT * FROM " + Db.RepoTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Repo>() {
                    @Override
                    public Repo call(Cursor cursor) {
                        return Db.RepoTable.parseCursor(cursor);
                    }
                });
    }
}
