package com.madarsoft.madarsoft.mvvm.ui.addUser;

import androidx.lifecycle.MutableLiveData;

import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.mvvm.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

@Getter
@HiltViewModel
public class AddUserViewModel extends BaseViewModel {

    @Inject
    public AddUserViewModel() {
    }

    @Inject
    MutableLiveData<Long> saveUserResponseMutableLiveData;

    public void requestAddToCart(User user) {
        getCompositeDisposable().add(Observable.just(user)
                .doOnNext(__ -> getOnLoadingMutableLiveData().setValue(true))
                .observeOn(Schedulers.io())
                .takeWhile(this::isInternetAvailable)
                .flatMap(object -> getRepository().saveUser(object))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getOnLoadingMutableLiveData().setValue(false))
                .subscribe(response -> saveUserResponseMutableLiveData.setValue(response), this::onFailure));
    }
}
