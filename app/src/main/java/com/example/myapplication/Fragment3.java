package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private Fragment3Binding binding;
    private OnConfirmationChangeListener listener;

    private static final String ARG_NAME = "user_name";

    public interface OnConfirmationChangeListener {
        void onConfirmationChanged(boolean checked);
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnConfirmationChangeListener) {
            listener = (OnConfirmationChangeListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = Fragment3Binding.inflate(inflater, container, false);

        String name = getArguments() != null
                ? getArguments().getString(ARG_NAME)
                : "";

        binding.textViewName.setText("Welcome " + name);

        binding.checkBoxConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onConfirmationChanged(isChecked);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}