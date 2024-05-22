package com.example.fitnessuygulamas;

import static android.os.Build.VERSION_CODES.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import fragments.anasayfa;
import fragments.egzersiz;
import fragments.yemek;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnb;
    TextView kullaniciadi, textViewAlinanKalori;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private int totalCaloriesConsumed = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R); // Doğru layout dosyasını belirtiyoruz
        bnb = findViewById(R);
        textViewAlinanKalori = findViewById(R);
        kullaniciadi = findViewById(R);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                @SuppressLint("RestrictedApi") User user = dataSnapshot.getValue(User.class);
                if (user != null) kullaniciadi.setText(user.getUid());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Veri çekme hatası: " + error.getMessage());
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R, new anasayfa()).commit();
        }

        bnb.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedFragment = null;

                if (id == R) {
                    selectedFragment = new anasayfa();
                } else if (R == id) {
                    selectedFragment = new egzersiz();
                } else if (id == R) {
                    selectedFragment = new yemek();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R, selectedFragment).commit();
                    Log.d("MainActivity", "Fragment replaced: " + selectedFragment.getClass().getSimpleName());
                } else {
                    Log.e("MainActivity", "Error replacing fragment: Fragment is null");
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void updateConsumedCalories(int calories) {
        totalCaloriesConsumed += calories;
        textViewAlinanKalori.setText(String.format("Toplam Alınan Kalori: %d", totalCaloriesConsumed));

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R);
        if (currentFragment instanceof anasayfa) {
            ((anasayfa) currentFragment).updateTotalCalories(totalCaloriesConsumed);
        }
    }
}
