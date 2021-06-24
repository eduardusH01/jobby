package app.mad.jobby;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LowonganPekerjaanRecViewAdapter extends RecyclerView.Adapter<LowonganPekerjaanRecViewAdapter.ViewHolder> {
    private ArrayList<LowonganPekerjaan> lowonganPekerjaanArrayList = new ArrayList<>();
    private Context context;

    public LowonganPekerjaanRecViewAdapter(Context context) {
        this.context = context;
    }

    public void setLowonganPekerjaanArrayList(ArrayList<LowonganPekerjaan> lowonganPekerjaanArrayList) {
        this.lowonganPekerjaanArrayList = lowonganPekerjaanArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lowongan_pekerjaan_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nama = lowonganPekerjaanArrayList.get(position).getNama();
        String lokasi = lowonganPekerjaanArrayList.get(position).getLokasi();
        String penyedia = lowonganPekerjaanArrayList.get(position).getPenyedia();
        String gaji;
        if(lowonganPekerjaanArrayList.get(position).getGaji() == 0){
            gaji = "Dirahasiakan";
        }
        else{
            gaji = String.valueOf(lowonganPekerjaanArrayList.get(position).getGaji());
        }

        String umur_min;
        if(lowonganPekerjaanArrayList.get(position).getUmur_min() == 99){
            umur_min = "Tidak Ada";
        }
        else{
            umur_min = String.valueOf(lowonganPekerjaanArrayList.get(position).getUmur_min());
        }

        String umur_max;
        if(lowonganPekerjaanArrayList.get(position).getUmur_max() == 0){
            umur_max = "Tidak Ada";
        }
        else{
            umur_max = String.valueOf(lowonganPekerjaanArrayList.get(position).getUmur_max());
        }

        String pendidikan_terakhir = lowonganPekerjaanArrayList.get(position).getPendidikan_terakhir();
        String image_url = lowonganPekerjaanArrayList.get(position).getImage_url();
        String description = lowonganPekerjaanArrayList.get(position).getDescription();
        String requirement = lowonganPekerjaanArrayList.get(position).getRequirement();

        holder.txtName.setText(nama);
        holder.txtPenyedia.setText(penyedia);
        holder.txtLokasi.setText(lokasi);
        Glide.with(context)
                .asBitmap()
                .load(image_url)
                .into(holder.image);

        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedLowonganPekerjaanActivity.class);

                intent.putExtra("nama", nama);
                intent.putExtra("lokasi", lokasi);
                intent.putExtra("penyedia", penyedia);
                intent.putExtra("gaji", gaji);
                intent.putExtra("umur_min", umur_min);
                intent.putExtra("umur_max", umur_max);
                intent.putExtra("pendidikan_terakhir", pendidikan_terakhir);
                intent.putExtra("image_url", image_url);
                intent.putExtra("description", description);
                intent.putExtra("requirement", requirement);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lowonganPekerjaanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtName, txtPenyedia, txtLokasi;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.itemParent);
            txtName = itemView.findViewById(R.id.itemTxtName);
            txtPenyedia = itemView.findViewById(R.id.itemTxtPenyedia);
            txtLokasi = itemView.findViewById(R.id.itemTxtLokasi);
            image = itemView.findViewById(R.id.itemImage);
        }
    }


}
