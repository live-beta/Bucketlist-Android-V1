package com.example.sam.bucketlist.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;

import com.example.sam.bucketlist.api.services.BucketListService;
import com.example.sam.bucketlist.app.AppController;
import com.example.sam.bucketlist.model.BucketListFields;
import com.example.sam.bucketlist.views.bucketlists.CompletedListener;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;




public class MainViewModel extends Observable implements CompletedListener {

    public ObservableInt bucketListRecycler;

    public ObservableInt contentViewVisibility;
    public ObservableInt progressBarVisibility;

    public ArrayList<BucketListFields> bucketLists;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Context context;

    public MainViewModel(Context context) {

        this.context = context;
        this.bucketLists = new ArrayList<>();
        progressBarVisibility = new ObservableInt(View.GONE);
        bucketListRecycler = new ObservableInt(View.GONE);
        initView();
        getBucketLists();

    }

    public void initView() {

        bucketListRecycler.set(View.GONE);
    }

    private void getBucketLists() {

        Timber.d("executing");

        AppController appController = new AppController(context);
        BucketListService bucketListService = appController.getBucketListService();

        Disposable disposable = bucketListService.fetchBucketLists()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bucketListResponse -> {
                    updateBucketList(bucketListResponse);
                    bucketListRecycler.set(View.VISIBLE);
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        bucketListRecycler.set(View.GONE);
                    }

                });

        compositeDisposable.add(disposable);
    }

    private void updateBucketList(ArrayList<BucketListFields> bucketListData) {

        bucketLists.addAll(bucketListData);
        Timber.d(String.valueOf(bucketListData));


        setChanged();
        notifyObservers();

    }

    private void unsubscribeFromObservables() {

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }

    }

    public void reset() {
        unsubscribeFromObservables();
        compositeDisposable = null;
        context = null;
    }

    @Override
    public void onCompleted() {

    }


}
