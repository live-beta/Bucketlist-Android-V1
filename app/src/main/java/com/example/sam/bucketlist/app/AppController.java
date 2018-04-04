package com.example.sam.bucketlist.app;

import android.app.Application;
import android.content.Context;

import com.example.sam.bucketlist.api.services.ApiFactory;
import com.example.sam.bucketlist.api.services.BucketListService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class AppController extends Application {

    private BucketListService bucketListServices;
    private Scheduler scheduler;
    private  Context appContext;

    public AppController(Context context){
        this.appContext = context;
    }

    public  AppController(){}


    private static AppController get(Context context){

        return (AppController) context.getApplicationContext();
    }
    public static AppController create(Context context) {
        return  AppController.get(context);
    }
    public BucketListService getBucketListService(){

        if(bucketListServices == null){
            bucketListServices = ApiFactory.create(appContext);
        }
        return bucketListServices;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null){
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
    public void setBucketListServices(BucketListService bucketListServices) {
        this.bucketListServices =  bucketListServices;
    }
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
