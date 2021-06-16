package app.mad.jobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class LowonganPekerjaanActivity extends AppCompatActivity {

    private RecyclerView LowonganPekerjaanRecView;

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.lpjobbylogo:
//                finish();
//                break;
//            case R.id.lpbtnFilter:
//                break;
//            default:
//                break;
//        }
//
//    }

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

        ArrayList<LowonganPekerjaan> lowonganPekerjaanArrayList = new ArrayList<>();
        lowonganPekerjaanArrayList.add(new LowonganPekerjaan("Driver Taksi", "Jakarta Barat", "SMA", "PT Blue Bird", "https://kisikisi-root.nos.jkt-1.neo.id/assets/images/logo/blue_bird_ka_cab_cengkareng_tbk_pt_1568202749.jpg", 4500000, 23, 100));
        lowonganPekerjaanArrayList.add(new LowonganPekerjaan("Administrasi Umum", "Jakarta Utara", "SMA", "PT Indomaret", "https://kisikisi-root.nos.jkt-1.neo.id/assets/images/logo/indomarco-prismatama-pt.jpg", 0, 0, 25));

        LowonganPekerjaanRecViewAdapter adapter = new LowonganPekerjaanRecViewAdapter(this);
        adapter.setLowonganPekerjaanArrayList(lowonganPekerjaanArrayList);

        LowonganPekerjaanRecView.setAdapter(adapter);
        LowonganPekerjaanRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}