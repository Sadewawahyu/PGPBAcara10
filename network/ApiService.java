package com.example.pgpb10.network;

import com.example.pgpb10.model.Data;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {
    @GET("3327")
    Call<List<Data>> getAllUsers();
}
