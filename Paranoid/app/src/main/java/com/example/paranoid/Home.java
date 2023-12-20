package com.example.paranoid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paranoid.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    Button button;
    TextView textView;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseUser fireuser;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        fireuser = auth.getCurrentUser();

        if (fireuser == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
            return; // Add return to exit the method after starting LoginActivity
        }

        // Check if the Intent contains the required data
        if (getIntent() != null && getIntent().hasExtra("UserId")) {
            String userId = getIntent().getStringExtra("UserId");
            showMeasurementsFragment(userId);

            // Reference the "Users" node with the user's UID
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

            userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot contains the data at the specified location
                        Users user = dataSnapshot.getValue(Users.class);
                        if (user != null) {
                            // Assuming there is a getName() method in your Users class
                            textView.setText(user.getName());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that may occur
                }
            });

        } else {
            // Provide feedback to the user that the data is missing
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
        }

        if (fireuser != null) {
            showProfileFragment(fireuser.getUid());
        } else {
            Toast.makeText(this, "User data not available", Toast.LENGTH_SHORT).show();
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Profile) {
                if (fireuser != null) {
                    showProfileFragment(fireuser.getUid());
                } else {
                    Toast.makeText(this, "User data not available", Toast.LENGTH_SHORT).show();
                }
            } else if (item.getItemId() == R.id.Exercises) {
                replaceFragment(new ExercisesFragment());
            } else if (item.getItemId() == R.id.Measurements) {
                // Check if user data is available before switching to MeasurementsFragment
                if (fireuser != null) {
                    showMeasurementsFragment(fireuser.getUid());
                } else {
                    Toast.makeText(this, "User data not available", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showProfileFragment(String userId) {
        ProfileFragment fragment = ProfileFragment.newInstance(userId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void showMeasurementsFragment(String userId) {
        MeasurementsFragment fragment = MeasurementsFragment.newInstance(userId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
