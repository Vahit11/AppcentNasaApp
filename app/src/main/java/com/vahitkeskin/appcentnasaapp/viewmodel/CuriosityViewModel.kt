package com.vahitkeskin.appcentnasaapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vahitkeskin.appcentnasaapp.model.Model
import com.vahitkeskin.appcentnasaapp.model.Photo
import com.vahitkeskin.appcentnasaapp.sevice.NasaAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

//Model View View Model => Clean code
class CuriosityViewModel : ViewModel() {

    private val nasaApiService = NasaAPIService()
    val photos = MutableLiveData<List<Photo>>()
    private val disposable = CompositeDisposable()

    //ViewModel created for use with LiveData
    fun getDataCuriosityAPI() {
        disposable.add(
            nasaApiService.getCuriosityService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model>() {
                    override fun onSuccess(t: Model) {
                        photos.value = t.photos
                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage
                    }

                })

        )
    }
}