package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private OnNameSubmitListener listener;

    public interface OnNameSubmitListener {
        void onNameSubmit(String name);
    }

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String param1, String param2) {
        return new Fragment2();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNameSubmitListener) {
            listener = (OnNameSubmitListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnNameSubmitListener"
            );
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void submitName() {
        if (binding == null) return;

        String name = binding.editText.getText().toString().trim();

        if (name.isEmpty()) {
            binding.editText.setError("Please enter your name");
            return;
        }

        if (listener != null) {
            listener.onNameSubmit(name);
        }
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