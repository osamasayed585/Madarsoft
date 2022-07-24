package com.madarsoft.madarsoft.mvvm.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.madarsoft.madarsoft.classes.repository.local.RepositoryImplementation;
import com.madarsoft.madarsoft.classes.utils.BaseResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import timber.log.Timber;

@Getter
@HiltViewModel
public class BaseViewModel extends ViewModel {

    @Inject
    public BaseViewModel() {
    }

    @Inject
    RepositoryImplementation repository;

    @Inject
    MutableLiveData<Boolean> onLoadingMutableLiveData;

    @Inject
    MutableLiveData<BaseResponse> onRoomErrorMutableLiveData;

    @Inject
    CompositeDisposable compositeDisposable;


    protected boolean isSuccess(BaseResponse response) {
        if (!response.isSuccess()) {
            onRoomErrorMutableLiveData.setValue(response);
            Timber.e("From new BaseViewModel %s", response.getMessage());
            return false;
        } else
            return true;

    }

    protected void onFailure(Throwable throwable) {
        onRoomErrorMutableLiveData.setValue(BaseResponse.builder().message(throwable.toString()).success(false).build());
        Timber.e(throwable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public boolean isInternetAvailable(Object object) {
        return true;
    }

}
