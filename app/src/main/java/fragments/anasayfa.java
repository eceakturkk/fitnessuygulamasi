package fragments;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class anasayfa extends Fragment {

    private TextView textViewWelcomeMessage, textViewUsername, textViewBMI, textViewGunlukKalori, textViewAlinanKalori;
    private FirebaseAuth auth;
    private DatabaseReference userReference;
    private FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R, container, false);

        textViewWelcomeMessage = view.findViewById(R);
        textViewUsername = view.findViewById(R);
        textViewBMI = view.findViewById(R);
        textViewGunlukKalori = view.findViewById(R);
        textViewAlinanKalori = view.findViewById(R);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        if (firebaseUser != null) {
            String userid = firebaseUser.getUid();
            userReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

            userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.child("username").getValue(String.class);
                    String boyStr = dataSnapshot.child("boy").getValue(String.class);
                    String kiloStr = dataSnapshot.child("kilo").getValue(String.class);
                    String cinsiyet = dataSnapshot.child("cinsiyet").getValue(String.class);

                    textViewUsername.setText(username);

                    if (boyStr != null && kiloStr != null && cinsiyet != null) {
                        double boy = Double.parseDouble(boyStr) / 100;
                        double kilo = Double.parseDouble(kiloStr);

                        double bmi = kilo / (boy * boy);
                        textViewBMI.setText(String.format("BMI: %.2f", bmi));

                        int yas = 25;
                        double bmr;
                        if (cinsiyet.equals("Erkek")) {
                            bmr = 88.36 + (13.4 * kilo) + (4.8 * (boy * 100)) - (5.7 * yas);
                        } else {
                            bmr = 447.6 + (9.2 * kilo) + (3.1 * (boy * 100)) - (4.3 * yas);
                        }

                        double gunlukKaloriIhtiyaci = bmr * 1.55; // Orta derecede aktiflik için örnek katsayı kullanıldı.
                        textViewGunlukKalori.setText(String.format("Günlük Kalori İhtiyacı: %.0f", gunlukKaloriIhtiyaci));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

        return view;
    }
    public void updateTotalCalories(int totalCalories) {
        if (textViewAlinanKalori != null) {
            textViewAlinanKalori.setText(String.format("Alınan Kalori: %d", totalCalories));
        }
    }

}