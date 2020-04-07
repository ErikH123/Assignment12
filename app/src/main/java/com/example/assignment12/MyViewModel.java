package com.example.assignment12;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private RequestQueue queue = Volley.newRequestQueue(getApplication().getApplicationContext());
    private MutableLiveData<List<User>> users;

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<User>> getUsers(){
        if (users == null){
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        ArrayList<User> lista = new ArrayList<>();
        String url = "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object = response.getJSONObject(i);

                            lista.add(new User(object.getInt("id"),
                                    object.getString("name"),
                                    object.getString("username"),
                                    object.getString("email"),
                                    object.getJSONObject("address").getString("street"),
                                    object.getJSONObject("address").getString("city")));

                            //Log.d("MyViewModel", "Users: name ja username: " + object.getString("name") +
                            //        ", " + object.getString("username"));
                        }
                        users.postValue(lista);
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        queue.add(request);
    }
}
