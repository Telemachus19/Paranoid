package com.example.paranoid;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    Button musicPlayerStartBtn, musicPlayerStopBtn, recordWorkoutBtn;
    TextInputEditText chestOverloadEditText, backOverloadEditText, legsOverloadEditText, absOverloadEditText, forearmOverloadEditText;
    DatabaseReference userReference;
    Users user;
    String UserId;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String userId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString("UserId", userId);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//            UserId = getArguments().getString("UserId");
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("chestOverload", chestOverloadEditText.getText().toString());
        myEdit.putString("backOverloadEdit", backOverloadEditText.getText().toString());
        myEdit.putString("legsOverloadEdit", legsOverloadEditText.getText().toString());
        myEdit.putString("absOverloadEdit", absOverloadEditText.getText().toString());
        myEdit.putString("forearmOverloadEdit", forearmOverloadEditText.getText().toString());
        myEdit.apply();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        musicPlayerStartBtn = v.findViewById(R.id.musicPlayerStartBtn);
        musicPlayerStopBtn = v.findViewById(R.id.musicPlayerStopBtn);
        recordWorkoutBtn = v.findViewById(R.id.RecordWorkoutBtn);
        musicPlayerStartBtn.setOnClickListener(v1 -> {
            Log.d("Music Player Start Button","Pushed");
            requireActivity().startService(new Intent(getContext(), MusicPlayer.class));
        });
        musicPlayerStopBtn.setOnClickListener(v1 -> {
            Log.d("Music Player Stop Button","Pushed");
            requireActivity().stopService(new Intent(getContext(), MusicPlayer.class));
        });

        chestOverloadEditText = v.findViewById(R.id.chestOverload);
        backOverloadEditText = v.findViewById(R.id.BackOverload);

        legsOverloadEditText = v.findViewById(R.id.legsOverload);
        absOverloadEditText = v.findViewById(R.id.absOverload);
        forearmOverloadEditText = v.findViewById(R.id.forearmOverload);

        if (getArguments() != null) {
            UserId = getArguments().getString("UserId");
        }

        FirebaseDatabase.getInstance();

        userReference = FirebaseDatabase.getInstance().getReference("Users").child(UserId);
//        userReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // dataSnapshot contains the data at the specified location
//                    user = dataSnapshot.getValue(Users.class);
//
//                    // Set the values to the TextInputEditText views
//                    if (user != null) {
//                        chestOverloadEditText.setText(String.valueOf(user.getChestOverload()));
//                        backOverloadEditText.setText(String.valueOf(user.getBackOverload()));
//                        legsOverloadEditText.setText(String.valueOf(user.getLegsOverload()));
//                        absOverloadEditText.setText(String.valueOf(user.getAbsOverload()));
//                        forearmOverloadEditText.setText(String.valueOf(user.getForearmsOverload()));
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle any errors that may occur
//            }
//        });
        // Add TextChangedListeners to automatically save data
//        addTextChangedListener(chestOverloadEditText, "chest");
//        addTextChangedListener(backOverloadEditText, "back");
//        addTextChangedListener(legsOverloadEditText, "legs");
//        addTextChangedListener(absOverloadEditText, "abs");
//        addTextChangedListener(forearmOverloadEditText, "forearms");
        recordWorkoutBtn.setOnClickListener(v1 -> {
            Log.d("Workout","Recorded");
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("chestOverload", chestOverloadEditText.getText().toString());
            myEdit.putString("backOverloadEdit", backOverloadEditText.getText().toString());
            myEdit.putString("legsOverloadEdit", legsOverloadEditText.getText().toString());
            myEdit.putString("absOverloadEdit", absOverloadEditText.getText().toString());
            myEdit.putString("forearmOverloadEdit", forearmOverloadEditText.getText().toString());
            myEdit.apply();
            // Add TextChangedListeners to automatically save data
            addTextChangedListener(chestOverloadEditText, "chest");
            addTextChangedListener(backOverloadEditText, "back");
            addTextChangedListener(legsOverloadEditText, "legs");
            addTextChangedListener(absOverloadEditText, "abs");
            addTextChangedListener(forearmOverloadEditText, "forearms");
//            try {
//                user.setChestOverload(Integer.parseInt(sharedPreferences.getString("chestOverload","0")));
//                user.setBackOverload(Integer.parseInt(sharedPreferences.getString("backOverloadEdit","0")));
//                user.setLegsOverload(Integer.parseInt(sharedPreferences.getString("legsOverloadEdit","0")));
//                user.setAbsOverload(Integer.parseInt(sharedPreferences.getString("absOverloadEdit","0")));
//                user.setForearmsOverload(Integer.parseInt(sharedPreferences.getString("forearmOverloadEdit","0")));
//            }catch (NumberFormatException e) {
//                Log.e("Firebase", "Error parsing " + ": " + e.getMessage());
//            }

        });
        return v;
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
                        case "chest":
                            currentValue = String.valueOf(user.getChestOverload());
                            user.setChestOverload(Integer.parseInt(newText));
                            break;
                        case "back":
                            currentValue = String.valueOf(user.getBackOverload());
                            user.setBackOverload(Integer.parseInt(newText));
                            break;
                        case "abs":
                            currentValue = String.valueOf(user.getAbsOverload());
                            user.setAbsOverload(Integer.parseInt(newText));
                            break;
                        case "legs":
                            currentValue = String.valueOf(user.getLegsOverload());
                            user.setLegsOverload(Integer.parseInt(newText));
                            break;
                        case "forearms":
                            currentValue = String.valueOf(user.getForearmsOverload());
                            user.setForearmsOverload(Integer.parseInt(newText));
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