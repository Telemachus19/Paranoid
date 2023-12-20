package com.example.paranoid;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeasurementsFragment extends Fragment {

    TextInputEditText editTextHeight, editTextWeight, editTextAge, editTextBody_Fats, editTextMuscle, editTextCalories_per_day;
    TextView ChestOverloadTextView,AbsOverloadTextView,LegsOverloadTextView,BackOverloadTextView,ForearmsOverloadTextView;
    DatabaseReference userReference;
    Users user;
    String UserId;
    ImageView button;

    public MeasurementsFragment() {
        // Required empty public constructor
    }


    public static MeasurementsFragment newInstance(String userId) {
        MeasurementsFragment fragment = new MeasurementsFragment();
        Bundle args = new Bundle();
        args.putString("UserId", userId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String chestOverload = sh.getString("chestOverload", "0");
        String backOverload = sh.getString("backOverloadEdit", "0");
        String legsOverload = sh.getString("legsOverloadEdit", "0");
        String absOverload = sh.getString("absOverloadEdit", "0");
        String armOverload = sh.getString("forearmOverloadEdit", "0");
        String chest = "Chest Progressive Overload " + chestOverload;
        String arms  = "Forearms Progressive Overload " + armOverload;
        String legs  = "Legs Progressive Overload " +legsOverload;
        String abs   = "Abs Progressive Overload " + absOverload;
        String back  = "Back Progressive Overload " + backOverload;
        // Setting the fetched data in the EditTexts
        ChestOverloadTextView.setText(chest);
        AbsOverloadTextView.setText(abs);
        BackOverloadTextView.setText(back);
        ForearmsOverloadTextView.setText(arms);
        LegsOverloadTextView.setText(legs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_measurements, container, false);

        editTextHeight = view.findViewById(R.id.Height);
        editTextWeight = view.findViewById(R.id.Weight);
        editTextAge = view.findViewById(R.id.Age);
        editTextBody_Fats = view.findViewById(R.id.BodyFats);
        editTextMuscle = view.findViewById(R.id.Muscle);
        editTextCalories_per_day = view.findViewById(R.id.CaloriesPerDay);
        ChestOverloadTextView = view.findViewById(R.id.ChestOverloadTextView);
        AbsOverloadTextView = view.findViewById(R.id.AbsOverloadTextView);
        BackOverloadTextView = view.findViewById(R.id.BackOverloadTextView);
        ForearmsOverloadTextView = view.findViewById(R.id.ForearmsOverloadTextView);
        LegsOverloadTextView = view.findViewById(R.id.LegsOverloadTextView);
        button = view.findViewById(R.id.help);

        if (getArguments() != null) {
            UserId = getArguments().getString("UserId");
        }

        FirebaseDatabase.getInstance();

        userReference = FirebaseDatabase.getInstance().getReference("Users").child(UserId);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot contains the data at the specified location
                    user = dataSnapshot.getValue(Users.class);

                    // Set the values to the TextInputEditText views
                    if (user != null) {
                        editTextHeight.setText(String.valueOf(user.getHeight()));
                        editTextWeight.setText(String.valueOf(user.getWeight()));
                        editTextAge.setText(String.valueOf(user.getAge()));
                        editTextBody_Fats.setText(String.valueOf(user.getBody_Fats()));
                        editTextMuscle.setText(String.valueOf(user.getMuscle()));
                        editTextCalories_per_day.setText(String.valueOf(user.getCalories_per_day()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur
            }
        });

        // Add TextChangedListeners to automatically save data
        addTextChangedListener(editTextHeight, "height");
        addTextChangedListener(editTextWeight, "weight");
        addTextChangedListener(editTextAge, "age");
        addTextChangedListener(editTextBody_Fats, "bodyFats");
        addTextChangedListener(editTextMuscle, "muscle");
        addTextChangedListener(editTextCalories_per_day, "caloriesPerDay");

        button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@hanyrambod_FST7"));
                startActivity(intent);
            }
        });

        return view;
    }

    private void addTextChangedListener(final TextInputEditText editText, final String fieldName) {
        if (editText == null) {
            Log.e("EditText", "EditText is null");
            return;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed in this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed in this case
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (getView() == null) {
                    Log.e("Fragment", "View is null");
                    return;
                }
                if (user == null) {
                    Log.e("Firebase", "User object is null");
                    return;
                }

                try {
                    String newText = editable.toString();
                    String currentValue = null;

                    // Inside afterTextChanged method
                    switch (fieldName) {
                        case "height":
                            currentValue = String.valueOf(user.getHeight());
                            user.setHeight(Integer.parseInt(newText));
                            break;
                        case "weight":
                            currentValue = String.valueOf(user.getWeight());
                            user.setWeight(Float.parseFloat(newText));
                            break;
                        case "age":
                            currentValue = String.valueOf(user.getAge());
                            user.setAge(Integer.parseInt(newText));
                            break;
                        case "bodyFats":
                            currentValue = String.valueOf(user.getBody_Fats());
                            user.setBody_Fats(Integer.parseInt(newText));
                            break;
                        case "muscle":
                            currentValue = String.valueOf(user.getMuscle());
                            user.setMuscle(Integer.parseInt(newText));
                            break;
                        case "caloriesPerDay":
                            currentValue = String.valueOf(user.getCalories_per_day());
                            user.setCalories_per_day(Integer.parseInt(newText));
                            break;
                        default:
                            break;
                    }

                    if (userReference == null) {
                        Log.e("Firebase", "User reference or View is null");
                        return; // or handle it appropriately
                    }

                    // Save the Users object to Firebase database only if the data is different
                    if (!newText.equals(currentValue)) {
                        userReference.setValue(user)
                                .addOnSuccessListener(aVoid -> {
                                    Log.d("Firebase", "Data written successfully");
                                    // Use Toast only if the data is different
                                    Toast.makeText(requireContext(), fieldName + " Inserted", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> Log.e("Firebase", "Error writing data: " + e.getMessage()));
                    }
                } catch (NumberFormatException e) {
                    Log.e("Firebase", "Error parsing " + fieldName + ": " + e.getMessage());
                }
            }
        });
    }
}