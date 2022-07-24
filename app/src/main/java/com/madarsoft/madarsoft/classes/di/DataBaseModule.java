package com.madarsoft.madarsoft.classes.di;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.classes.datebase.userDao.UserDao;
import com.madarsoft.madarsoft.classes.datebase.userDatabase.UserDatabase;
import com.madarsoft.madarsoft.classes.utils.BaseResponse;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.CompositeDisposable;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static UserDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application,
                UserDatabase.class, "MadarSoft")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public static UserDao provideUserDao(UserDatabase appDatabase) {
        return appDatabase.userDoa();
    }

    @Provides
    public MutableLiveData<List<User>> provideUserMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<Long> longMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<BaseResponse> baseResponseMutableLiveData(){
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<Boolean> provideBooleanMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
