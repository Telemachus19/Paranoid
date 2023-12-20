package com.example.paranoid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExercisesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance(String param1, String param2) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        setupExerciseClickListener(view, R.id.cardio_img, R.id.cardio, R.layout.fragment_cardio);
        setupExerciseClickListener(view, R.id.chest_img, R.id.chest, R.layout.fragment_chest);
        setupExerciseClickListener(view, R.id.back_img, R.id.back, R.layout.fragment_back);
        setupExerciseClickListener(view, R.id.shoulder_img, R.id.shoulder, R.layout.fragment_shoulder);
        setupExerciseClickListener(view, R.id.forearms_img, R.id.forearms, R.layout.fragment_forearms);
        setupExerciseClickListener(view, R.id.legs_img, R.id.legs, R.layout.fragment_legs);

        return view;
    }

    private void setupExerciseClickListener(View parentView, int imageId, int textId, int layoutResId) {
        ImageView exerciseImage = parentView.findViewById(imageId);
        TextView exerciseText = parentView.findViewById(textId);

        // Set OnClickListener for the image
        exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(layoutResId);
            }
        });

        // Set OnClickListener for the text
        exerciseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(layoutResId);
            }
        });
    }

    private void navigateToFragment(int layoutResId) {
        View fragmentView = LayoutInflater.from(requireContext()).inflate(layoutResId, null);
        ViewGroup currentFragmentView = (ViewGroup) requireView();
        currentFragmentView.removeAllViews();
        currentFragmentView.addView(fragmentView);
    }
}
