package com.example.pgpb10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.pgpb10.model.Data;
import com.example.pgpb10.network.ApiClient;
import com.example.pgpb10.network.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the listview by Id
        ListView listView = findViewById(R.id.tv_nama);

        //Create Service
        ApiService client = ApiClient.getInstance();

        //Panggil API untuk mendapatkan users
        Call<List<Data>> response = client.getAllUsers();

        //List to hold user details
        List<String> dataUser = new ArrayList<>();

        //Enqueue the call
        response.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful() && response.body() !=null){
                    for (Data data :response.body()){
                        String userDetails = "Surveyor: " + data.getNamaSurveyor() +
                                "\n" + "Jenis kelamin: Laki-laki"  + "\n" +
                                "Pekerjaan: " + data.getPekerjaan() + "\n" +
                                "Usia: " + data.getUsia() + "\n" +
                                "ID Desa: " + data.getIdDesa() + "\n" +
                                "Nama Desa: " + data.getNamaDesa() + "\n" +
                                "Latitude :" + data.getLatitude() + "\n" +
                                "Longitude: " + data.getLongitude() + "\n" +
                                "Indeks: " + data.getIndeksMulti();
                        dataUser.add(userDetails);
                    }
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                            MainActivity.this, android.R.layout.simple_list_item_1, dataUser
                    );
                    listView.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }
}