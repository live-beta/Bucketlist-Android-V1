package com.example.sam.bucketlist.views.bucketlists;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.databinding.ActivityMainBinding;
import com.example.sam.bucketlist.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;


/* API manager: implemnents the application programming interface
* contracts that are defined in the Network service
* */


public class BucketlistActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding bucketlistActivityBinding;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfBucketlistView(bucketlistActivityBinding.listBucketlists);
        setUpObserver(mainViewModel);

    }

    private void initDataBinding(){
        bucketlistActivityBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        bucketlistActivityBinding.setMainViewModel(mainViewModel);

    }

    private void setUpListOfBucketlistView(RecyclerView listBucketList){

        BucketListAdapter bucketListAdapter = new BucketListAdapter(getApplicationContext());
        listBucketList.setAdapter(bucketListAdapter);
        listBucketList.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setUpObserver(Observable observable){

        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg){

        if (o instanceof MainViewModel){
            BucketListAdapter bucketListAdapter = (BucketListAdapter) bucketlistActivityBinding
                    .listBucketlists.getAdapter();

            MainViewModel bucketListViewModel = (MainViewModel) o;
            bucketListAdapter.setBucketList(bucketListViewModel.bucketLists);

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mainViewModel.reset();
    }

}



