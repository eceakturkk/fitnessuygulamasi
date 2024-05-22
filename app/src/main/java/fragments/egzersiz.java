package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessuygulamas.MainActivity;
import com.example.fitnessuygulamas.R;

import java.util.ArrayList;

import model.EgzersizKart;
import model.EgzersizRecyclerViewAdapter;

public class egzersiz extends Fragment {

    private RecyclerView recyclerView;
    private EgzersizRecyclerViewAdapter egzersizAdapter;
    private ArrayList<EgzersizKart> egzersizList;

    public egzersiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_egzersiz, container, false);

        recyclerView = view.findViewById(R.id.recycler_egzersiz);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the list of exercises
        egzersizList = new ArrayList<>();
        egzersizList.add(new EgzersizKart("egzersiz1", "1 saat koşu", 100));
        egzersizList.add(new EgzersizKart("egzersiz2", "1 saat bisiklet", 120));
        egzersizList.add(new EgzersizKart("egzersiz3", "1 saat yüzme", 150));

        // Set up the adapter
        egzersizAdapter = new EgzersizRecyclerViewAdapter(getContext(), egzersizList);
        recyclerView.setAdapter(egzersizAdapter);

        // Set item click listener
        egzersizAdapter.setOnItemClickListener(new EgzersizRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EgzersizKart selectedExercise = egzersizList.get(position);
                int caloriesBurned = selectedExercise.getYakilanKalori();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.updateConsumedCalories(-caloriesBurned); // Yakılan kalorileri çıkar
                } else {
                    Log.e("EgzersizFragment", "MainActivity is null");
                }
            }
        });

        return view;
    }
}