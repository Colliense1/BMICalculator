package com.example.colliensepodder.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BodyMassIndex extends AppCompatActivity {

    android.widget.EditText editTextHeightFeet;
    EditText editTextHeightInches;
    EditText editTextWeightKg;
    TextView textViewBmiCalculate;
    TextView textViewBmiHeight;
    TextView textViewBmiWeight;
    android.widget.LinearLayout lLayoutCalculateBodymi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_mass_index);

        editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
        editTextHeightInches = findViewById(R.id.editTextHeightInches);
        editTextWeightKg = findViewById(R.id.editTextWeightKg);
        textViewBmiCalculate = findViewById(R.id.textViewBmiCalculate);
        textViewBmiHeight = findViewById(R.id.textViewBmiHeight);
        textViewBmiWeight = findViewById(R.id.textViewBmiWeight);
        lLayoutCalculateBodymi = findViewById(R.id.lLayoutCalculateBodymi);


        editTextHeightFeet.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                final CharSequence[] charSequenceArr = new CharSequence[]{com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8"};
                new android.support.v7.app.AlertDialog.Builder(BodyMassIndex.this).setTitle((CharSequence) "Select height in feet").setItems(charSequenceArr, new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialogInterface, int i) {
                        BodyMassIndex.this.editTextHeightFeet.setText(charSequenceArr[i]);
                    }
                }).create().show();

            }
        });

        editTextHeightInches.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                final CharSequence[] charSequenceArr = new CharSequence[]{com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
                new android.support.v7.app.AlertDialog.Builder(BodyMassIndex.this).setTitle((CharSequence) "Select height in inches").setItems(charSequenceArr, new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialogInterface, int i) {
                        BodyMassIndex.this.editTextHeightInches.setText(charSequenceArr[i]);
                    }
                }).create().show();
            }
        });

        lLayoutCalculateBodymi.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
                BMICalculator.Bmi bmi = BMICalculator.getBmi(BodyMassIndex.this.editTextHeightFeet.
                        getText().toString().trim(), BodyMassIndex.this.editTextHeightInches.
                        getText().toString().trim(), BodyMassIndex.this.editTextWeightKg.
                        getText().toString().trim());
                android.view.View inflate = getLayoutInflater().inflate(R.layout.dialog_bmi_calculation_results, null);
                final android.support.v7.app.AlertDialog create = new android.support.v7.app.AlertDialog.Builder(BodyMassIndex.this).setView(inflate).create();
                ((TextView) inflate.findViewById(R.id.textViewBmiIndex)).setText(bmi.bmiIndex);
                ((TextView) inflate.findViewById(R.id.textViewBmiStatus)).setText(bmi.healthStatusText);
                TextView textView = (TextView) inflate.findViewById(R.id.textViewBmiIndex);
                int i = bmi.healthStatusIndex;
                int i2 = R.color.REDlight;
                textView.setTextColor(android.support.v4.content.ContextCompat.getColor(BodyMassIndex.this, i == 1 ?
                        R.color.green :
                        R.color.REDlight));
                textView = (TextView) inflate.findViewById(R.id.textViewBmiStatus);
                if (bmi.healthStatusIndex == 1) {
                    i2 = R.color.green;
                }
                textView.setTextColor(android.support.v4.content.ContextCompat.getColor(BodyMassIndex.this, i2));
                inflate.findViewById(R.id.textViewOkay).setOnClickListener(new android.view.View.OnClickListener() {
                    public void onClick(android.view.View view) {
                        create.dismiss();
                    }
                });
                create.show();
            }
        });

    }

    public static class BMICalculator {

        public static String[] HEALTH_STATUS = new String[]{"Underweight", "Healthy Weight", "Overweight"};

        public static class Bmi {
            public String bmiIndex;
            public int healthStatusIndex;
            public String healthStatusText;
        }

        public static Bmi getBmi(String height, String feet, String kg) {

            double height_feet = Double.parseDouble(height);
            double height_inch = Double.parseDouble(feet);
            double weight_kg = Double.parseDouble(kg);
            double height_total_inch = (height_feet * 12) + height_inch;
            double height_in_cm = (height_total_inch * 2.54);
            double bmi;
            bmi = ((weight_kg / (height_in_cm * height_in_cm)) * 10000);

            Bmi str23 = new Bmi();
            str23.bmiIndex = String.format("%.1f", new Object[]{Double.valueOf(bmi)});

            if (bmi < 18.5) {
                str23.healthStatusIndex = 0;
            } else if (bmi > 24.9) {
                str23.healthStatusIndex = 2;
            } else {
                str23.healthStatusIndex = 1;
            }
            str23.healthStatusText = HEALTH_STATUS[str23.healthStatusIndex];
            return str23;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(android.view.View view) {
        this.finish();
    }

}