package com.example.s72505_lab2_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 1) Declare view references
    private ImageView imgLogo;
    private EditText edtName;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private CheckBox cbNewsletter;
    private Switch switchNotify;
    private Spinner spnCountry;
    private Button btnSubmit;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // IMPORTANT

        // 2) Initialize references using findViewById
        imgLogo = findViewById(R.id.imgLogo);
        edtName = findViewById(R.id.edtName);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        cbNewsletter = findViewById(R.id.cbNewsletter);
        switchNotify = findViewById(R.id.switchNotify);
        spnCountry = findViewById(R.id.spnCountry);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtResult = findViewById(R.id.txtResult);

        // 3) Spinner adapter (from strings.xml)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCountry.setAdapter(adapter);

        // 4) Button click listener (old style)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSubmit();
            }
        });

        // 5) Optional: react to gender change (RadioGroup)
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Change image for fun (requires drawable resources)
                if (checkedId == R.id.rbMale) {
                    // imgLogo.setImageResource(R.drawable.ic_male);
                } else if (checkedId == R.id.rbFemale) {
                    // imgLogo.setImageResource(R.drawable.ic_female);
                }
            }
        });
    }

    private void handleSubmit() {
        // Read EditText
        String name = edtName.getText().toString().trim();

        // Validate
        if (name.isEmpty()) {
            edtName.setError("Name required");
            edtName.requestFocus();
            return;
        }

        // Read RadioGroup
        String gender = "";
        int selectedId = rgGender.getCheckedRadioButtonId();
        if (selectedId == R.id.rbMale) gender = "Male";
        else if (selectedId == R.id.rbFemale) gender = "Female";
        else gender = "Unspecified";

        // Read CheckBox / Switch / Spinner
        boolean wantsNewsletter = cbNewsletter.isChecked();
        boolean notifyEnabled = switchNotify.isChecked();
        String country = spnCountry.getSelectedItem().toString();

        // Compose summary
        String summary = "Name: " + name +
                "\nGender: " + gender +
                "\nCountry: " + country +
                "\nNewsletter: " + (wantsNewsletter ? "Yes" : "No") +
                "\nNotifications: " + (notifyEnabled ? "On" : "Off");

        // Update UI & show toast
        txtResult.setText(summary);
        Toast.makeText(this, "Submitted!", Toast.LENGTH_SHORT).show();
    }
}
