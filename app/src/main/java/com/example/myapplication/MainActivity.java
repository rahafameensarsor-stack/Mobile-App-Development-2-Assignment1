package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnNameSubmitListener,
        Fragment3.OnConfirmationChangeListener {

    private ActivityMainBinding binding;

    private int currentFragment = 1;
    private String name = "";

    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        fragment2 = Fragment2.newInstance(null, null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {

            if (currentFragment == 1) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();

                currentFragment = 2;

            } else if (currentFragment == 2) {

                fragment2.submitName();

            } else if (currentFragment == 3) {

                Toast.makeText(
                        this,
                        "Finished successfully!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    public void onNameSubmit(String name) {

        this.name = name;

        Fragment3 fragment3 = Fragment3.newInstance(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        currentFragment = 3;

        binding.buttonContinue.setEnabled(false);
        binding.buttonContinue.setText("Continue");
    }

    @Override
    public void onConfirmationChanged(boolean checked) {

        binding.buttonContinue.setEnabled(checked);

        if (checked) {
            binding.buttonContinue.setText("Finish");
        } else {
            binding.buttonContinue.setText("Continue");
        }
    }
}