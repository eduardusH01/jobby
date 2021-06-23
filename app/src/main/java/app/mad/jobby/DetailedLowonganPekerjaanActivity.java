package app.mad.jobby;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedLowonganPekerjaanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_lowongan_pekerjaan);

        Button btnBack = findViewById(R.id.dlpbtnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView txtNama = findViewById(R.id.detailTxtName);
        TextView txtLokasi = findViewById(R.id.detailTxtLokasi);
        TextView txtPenyedia = findViewById(R.id.detailTxtPenyedia);
        TextView txtGaji = findViewById(R.id.detailTxtGaji);
        TextView txtUmur_min = findViewById(R.id.detailTxtUmurMin);
        TextView txtUmur_max = findViewById(R.id.detailTxtUmurMax);
        TextView txtPendidikan_terakhir = findViewById(R.id.detailTxtPendidikan);
        ImageView image = findViewById(R.id.detailImage);
        TextView txtDescription = findViewById(R.id.detailTxtDescription);
        TextView txtRequirement = findViewById(R.id.detailTxtRequirement);

        String nama = getIntent().getStringExtra("nama");
        String lokasi = getIntent().getStringExtra("lokasi");
        String penyedia = getIntent().getStringExtra("penyedia");
        String gaji = getIntent().getStringExtra("gaji");
        String umur_min = getIntent().getStringExtra("umur_min");
        String umur_max = getIntent().getStringExtra("umur_max");
        String pendidikan_terakhir = getIntent().getStringExtra("pendidikan_terakhir");
        String image_url = getIntent().getStringExtra("image_url");
        String description = getIntent().getStringExtra("description");
        String requirement = getIntent().getStringExtra("requirement");

        txtNama.setText(nama);
        txtLokasi.setText(lokasi);
        txtPenyedia.setText(penyedia);
        txtGaji.setText(gaji);
        txtUmur_min.setText(umur_min);
        txtUmur_max.setText(umur_max);
        txtPendidikan_terakhir.setText(pendidikan_terakhir);
        txtDescription.setText(description);
        txtRequirement.setText(requirement);
        Glide.with(DetailedLowonganPekerjaanActivity.this)
                .asBitmap()
                .load(image_url)
                .into(image);

    }
}