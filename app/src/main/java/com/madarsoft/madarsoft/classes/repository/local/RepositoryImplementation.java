package com.madarsoft.madarsoft.classes.repository.local;

import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.classes.datebase.userDatabase.UserDatabase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.Observable;
import timber.log.Timber;

@ViewModelScoped
public class RepositoryImplementation {

    private final UserDatabase db;


    @Inject
    public RepositoryImplementation(UserDatabase db) {
        this.db = db;
    }

    public Observable<List<User>> fetchUsers() {
        Timber.d("Osama DB getAll: db.getAll");
        return Observable.fromCallable(() -> db.userDoa().fetchUsers());
    }


    public Observable<Long> saveUser(User user) {
        Timber.d("Osama DB to date db -> Insert user");
        return Observable.fromCallable(() -> db.userDoa().saveUser(user));
    }


}
