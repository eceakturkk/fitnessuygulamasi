package com.example.fitnessuygulamas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;

public class Kaydol extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;

    EditText username, email, password, boy, kilo, yas;
    Button btn_kayit;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        boy = findViewById(R.id.boy);
        kilo = findViewById(R.id.kilo);
        yas = findViewById(R.id.yas);
        btn_kayit = findViewById(R.id.btn_kayit);
        auth = FirebaseAuth.getInstance();

        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_boy = boy.getText().toString();
                String txt_kilo = kilo.getText().toString();
                String txt_yas = yas.getText().toString();

                String gender = "";
                if (radioMale.isChecked()) {
                    gender = "Erkek";
                } else if (radioFemale.isChecked()) {
                    gender = "Kadın";
                }

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_boy) || TextUtils.isEmpty(txt_kilo) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(txt_yas)) {
                    Toast.makeText(Kaydol.this, "Boş alan bırakmayınız!", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(Kaydol.this, "Şifre uzunluğu 6 karakterden az olamaz!", Toast.LENGTH_SHORT).show();
                } else {
                    kayitol(txt_username, txt_email, txt_password, txt_boy, txt_kilo, gender, txt_yas);
                }
            }
        });
    }

    private void kayitol(final String username, final String email, final String password, final String boy, final String kilo, final String gender, final String yas) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            User user = new User(username, email, password, boy, kilo, gender, yas);

                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Kaydol.this, Baslangic.class);
                                        Toast.makeText(Kaydol.this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Kaydol.this, "Geçerli bir email adresi giriniz!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}