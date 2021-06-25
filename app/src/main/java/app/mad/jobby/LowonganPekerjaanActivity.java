package app.mad.jobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LowonganPekerjaanActivity extends AppCompatActivity implements FilterBottomSheetDialog.BottomSheetListener {

    private RecyclerView LowonganPekerjaanRecView;
    private ArrayList<LowonganPekerjaan> lowonganPekerjaanArrayList;
    private LowonganPekerjaanRecViewAdapter adapter;
    private SearchView searchView;
    private ArrayList<String> lokasiArraylist;
    private String selectedLokasi = "All";
    private String currentSearchText = "";

    private static final String BASE_URL = "http://192.168.100.9/android/getProducts.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowongan_pekerjaan);

        initSearchWidgets();
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

        lokasiArraylist = new ArrayList<>();
        lokasiArraylist.add("All");

        getProducts();
        initFilter();
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
                                String job_url = object.getString("job_url");

                                LowonganPekerjaan lowonganPekerjaan = new LowonganPekerjaan(nama, lokasi, penyedia, gaji, umur_min, umur_max, pendidikan_terakhir, image_url, description, requirement, job_url);
                                lowonganPekerjaanArrayList.add(lowonganPekerjaan);
                                if(!lokasiArraylist.contains(lokasi)){
                                    lokasiArraylist.add(lokasi);
                                }
//                                System.out.println(lokasiArraylist);
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

    public void initFilter(){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("listLokasi", lokasiArraylist);

        FilterBottomSheetDialog bottomSheet = new FilterBottomSheetDialog();
        bottomSheet.setArguments(bundle);

        Button btnFilter = findViewById(R.id.lpbtnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
                bottomSheet.show(getSupportFragmentManager(), "filterBottomSheet");
            }
        });


//        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragment();
//        bottomSheetDialogFragment.setArguments(bundle);
    }

    private void initSearchWidgets(){
        searchView = (SearchView) findViewById(R.id.lpSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<LowonganPekerjaan> filteredLowonganPekerjaan = new ArrayList<LowonganPekerjaan>();
                for(int i = 0; i < lowonganPekerjaanArrayList.size(); i++){
                    LowonganPekerjaan lowonganPekerjaan = lowonganPekerjaanArrayList.get(i);
                    if(lowonganPekerjaan.getNama().toLowerCase().contains(s.toLowerCase().trim())){
                        if(selectedLokasi.equals("All")){
                            filteredLowonganPekerjaan.add(lowonganPekerjaan);
                        }
                        else{
                            if(lowonganPekerjaan.getLokasi().equals(selectedLokasi)){
                                filteredLowonganPekerjaan.add(lowonganPekerjaan);
                            }
                        }
                    }
                }
                LowonganPekerjaanRecViewAdapter sadapter = new LowonganPekerjaanRecViewAdapter(LowonganPekerjaanActivity.this);
                sadapter.setLowonganPekerjaanArrayList(filteredLowonganPekerjaan);
                LowonganPekerjaanRecView.setAdapter(sadapter);

                return false;
            }
        });

    }

    @Override
    public void onSpinnerItemSelected(String text) {
        selectedLokasi = text;
        ArrayList<LowonganPekerjaan> filteredLowonganPekerjaan = new ArrayList<LowonganPekerjaan>();

        if(selectedLokasi.equals("All")){
            for(int i = 0; i < lowonganPekerjaanArrayList.size(); i++){
                LowonganPekerjaan lowonganPekerjaan = lowonganPekerjaanArrayList.get(i);
                if(currentSearchText.equals("")){
                    filteredLowonganPekerjaan.add(lowonganPekerjaan);
                }
                else{
                    if(lowonganPekerjaan.getNama().toLowerCase().contains(currentSearchText.toLowerCase().trim())){
                        filteredLowonganPekerjaan.add(lowonganPekerjaan);
                    }
                }
            }
            LowonganPekerjaanRecViewAdapter sadapter = new LowonganPekerjaanRecViewAdapter(LowonganPekerjaanActivity.this);
            sadapter.setLowonganPekerjaanArrayList(filteredLowonganPekerjaan);
            LowonganPekerjaanRecView.setAdapter(sadapter);
        }

        else{
            for(int i = 0; i < lowonganPekerjaanArrayList.size(); i++){
                LowonganPekerjaan lowonganPekerjaan = lowonganPekerjaanArrayList.get(i);
                if(lowonganPekerjaan.getLokasi().equals(selectedLokasi)){
                    if(currentSearchText.equals("")){
                        filteredLowonganPekerjaan.add(lowonganPekerjaan);
                    }
                    else{
                        if(lowonganPekerjaan.getNama().toLowerCase().contains(currentSearchText.toLowerCase().trim())){
                            filteredLowonganPekerjaan.add(lowonganPekerjaan);
                        }
                    }
                }
            }
            LowonganPekerjaanRecViewAdapter sadapter = new LowonganPekerjaanRecViewAdapter(LowonganPekerjaanActivity.this);
            sadapter.setLowonganPekerjaanArrayList(filteredLowonganPekerjaan);
            LowonganPekerjaanRecView.setAdapter(sadapter);
        }
    }
}