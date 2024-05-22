package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessuygulamas.R;

import java.util.List;

public class EgzersizRecyclerViewAdapter extends RecyclerView.Adapter<EgzersizRecyclerViewAdapter.ViewHolder> {
    private List<EgzersizKart> egzersizListesi;
    private Context context;
    private OnItemClickListener listener;

    public EgzersizRecyclerViewAdapter(Context context, List<EgzersizKart> egzersizListesi) {
        this.context = context;
        this.egzersizListesi = egzersizListesi;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.egzersiz_kart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EgzersizKart egzersiz = egzersizListesi.get(position);
        holder.textEgzersizAdi.setText(egzersiz.getEgzersizAdi());
        holder.textYakilanKalori.setText(String.format("Yakılan Kalori: %d", egzersiz.getYakilanKalori()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return egzersizListesi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textEgzersizAdi, textYakilanKalori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textEgzersizAdi = itemView.findViewById(R.id.textEgzersizAdi);
            textYakilanKalori = itemView.findViewById(R.id.textYakilanKalori);
        }
    }
}