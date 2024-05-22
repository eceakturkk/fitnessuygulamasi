package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessuygulamas.MainActivity;
import com.example.fitnessuygulamas.R;

import java.util.ArrayList;
import java.util.List;

import model.RecyclerViewAdapter;
import model.YemekKart;

public class yemek extends Fragment {
    private List<YemekKart> yemekListesi = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yemek, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_yemek);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), yemekListesi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Yemek listesine örnek veriler ekleyin
        yemekListesi.add(new YemekKart("Elma", 52));
        yemekListesi.add(new YemekKart("Muz", 89));
        yemekListesi.add(new YemekKart("Çilek", 32));

        adapter.notifyDataSetChanged();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), yemekListesi);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_yemek);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                YemekKart selectedMeal = yemekListesi.get(position);
                ((MainActivity) getActivity()).updateConsumedCalories(selectedMeal.getKalori());
            }
        });


    }
}