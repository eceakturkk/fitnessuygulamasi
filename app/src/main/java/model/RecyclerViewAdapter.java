package model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessuygulamas.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<YemekKart> yemekListesi;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public RecyclerViewAdapter(Context context, List<YemekKart> yemekListesi) {
        this.context = context;
        this.yemekListesi = yemekListesi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yemek_kart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        YemekKart yemek = yemekListesi.get(position);
        holder.textYemekAdi.setText(yemek.getYemekAdi());
        holder.textKalori.setText(String.format("Kalori: %d", yemek.getKalori()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return yemekListesi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textYemekAdi, textKalori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textYemekAdi = itemView.findViewById(R.id.textYemekAdi);
            textKalori = itemView.findViewById(R.id.textKalori);
        }
    }
}