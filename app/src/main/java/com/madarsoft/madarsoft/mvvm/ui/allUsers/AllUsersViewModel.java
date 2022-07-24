package com.madarsoft.madarsoft.mvvm.ui.allUsers;

import androidx.lifecycle.MutableLiveData;

import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.mvvm.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

@Getter
@HiltViewModel
public class AllUsersViewModel extends BaseViewModel {

    @Inject
    public AllUsersViewModel() {
    }

    @Inject
    MutableLiveData<List<User>> requestUserResponseMutableLiveData;

    public void requestUsers() {
        getCompositeDisposable().add(Observable.just("")
                .doOnNext(__ -> getOnLoadingMutableLiveData().setValue(true))
                .observeOn(Schedulers.io())
                .takeWhile(this::isInternetAvailable)
                .flatMap(object -> getRepository().fetchUsers())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getOnLoadingMutableLiveData().setValue(false))
                .subscribe(response -> requestUserResponseMutableLiveData.setValue(response), this::onFailure));
    }
}
