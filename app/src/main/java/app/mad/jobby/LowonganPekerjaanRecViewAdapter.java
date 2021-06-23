package app.mad.jobby;

import android.content.Context;
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
        holder.txtName.setText(lowonganPekerjaanArrayList.get(position).getNama());
        holder.txtPenyedia.setText(lowonganPekerjaanArrayList.get(position).getPenyedia());
        holder.txtLokasi.setText(lowonganPekerjaanArrayList.get(position).getLokasi());

        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, lowonganPekerjaanArrayList.get(position).getNama() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(lowonganPekerjaanArrayList.get(position).getImage_url())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return lowonganPekerjaanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtName, txtPenyedia, txtLokasi, txtGaji;
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
