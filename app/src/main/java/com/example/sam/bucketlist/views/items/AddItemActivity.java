package com.example.sam.bucketlist.views.items;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.ItemPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 1/17/18.
 */


public class AddItemActivity extends Activity {

    private EditText itemName;
    private Button addItem;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        itemName = findViewById(R.id.itemName);
        addItem = findViewById(R.id.addItem);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle extras = getIntent().getExtras();
                String bucketListId = (String) extras.get("id");
                APIManager apiManager = new APIManager(getApplicationContext());

                Call<ItemPost> call = apiManager.addItems(itemName.getText().toString(), bucketListId);

                call.enqueue(new Callback<ItemPost>() {
                    @Override
                    public void onResponse(Call<ItemPost> call,
                                           Response<ItemPost> response) {
                        Toast.makeText(context, "Item Added ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<ItemPost> call, Throwable t) {

                        Toast.makeText(context, "Not Added ", Toast.LENGTH_SHORT).show();

                    }
                });


                Intent intent = new Intent(context, ItemsActivity.class);
                startActivity(intent);

            }
        });


    }
}
