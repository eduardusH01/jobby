package app.mad.jobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LowonganPekerjaanActivity extends AppCompatActivity {

    private RecyclerView LowonganPekerjaanRecView;
    private ArrayList<LowonganPekerjaan> lowonganPekerjaanArrayList;
    private LowonganPekerjaanRecViewAdapter adapter;

    private static final String BASE_URL = "http://192.168.100.9/android/getProducts.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowongan_pekerjaan);

        ImageView logoJobby = findViewById(R.id.lpjobbylogo);
        logoJobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LowonganPekerjaanRecView = findViewById(R.id.lpRecView);
        LowonganPekerjaanRecView.setLayoutManager(new LinearLayoutManager(this));

        lowonganPekerjaanArrayList = new ArrayList<>();

        getProducts();
    }

    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);

                                String nama = object.getString("nama");
                                String lokasi = object.getString("lokasi");
                                String penyedia = object.getString("penyedia");
                                int gaji = object.getInt("gaji");
                                int umur_min = object.getInt("umur_min");
                                int umur_max = object.getInt("umur_max");
                                String pendidikan_terakhir = object.getString("pendidikan_terakhir");
                                String image_url = object.getString("image_url");
                                String description = object.getString("description");
                                String requirement = object.getString("requirement");

                                LowonganPekerjaan lowonganPekerjaan = new LowonganPekerjaan(nama, lokasi, penyedia, gaji, umur_min, umur_max, pendidikan_terakhir, image_url, description, requirement);
                                lowonganPekerjaanArrayList.add(lowonganPekerjaan);
                            }

                        }catch (Exception e){

                        }

                        adapter = new LowonganPekerjaanRecViewAdapter(LowonganPekerjaanActivity.this);
                        adapter.setLowonganPekerjaanArrayList(lowonganPekerjaanArrayList);
                        LowonganPekerjaanRecView.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LowonganPekerjaanActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(LowonganPekerjaanActivity.this).add(stringRequest);
    }
}