package com.example.themecolour;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner mLanguagesSpinner;
    private Spinner mThemesSpinner;
    private Button mApplyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        initViews();
        initSpinnerLanguages();
        initSpinnerThemes();
        initChangeLocalisationListener();
        initApplyButtonClickListener();
    }

    public void initViews() {
        mLanguagesSpinner = findViewById(R.id.spnLangs);
        mThemesSpinner = findViewById(R.id.spnThemes);
        mApplyBtn = findViewById(R.id.btnApply);
    }

    public void initSpinnerLanguages() {
        ArrayAdapter<CharSequence> adapterLanguages = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguagesSpinner.setAdapter(adapterLanguages);
        mLanguagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void initSpinnerThemes() {
        ArrayAdapter<CharSequence> adapterThemes = ArrayAdapter.createFromResource(this, R.array.themes, android.R.layout.simple_spinner_item);
        adapterThemes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mThemesSpinner.setAdapter(adapterThemes);
        mThemesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initChangeLocalisationListener() {
        mApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedLang = mLanguagesSpinner.getSelectedItem().toString();
                changeLang(selectedLang);
            }
        });
    }

    private void changeLang(String selectedLang) {
        Configuration config = new Configuration();
        switch (selectedLang) {
            case "Русский":
                Locale locale = new Locale("ru");
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                break;
            case "English":
                Locale locale1 = new Locale("en");
                config.setLocale(locale1);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                break;
            case "Deutsch":
                Locale locale2 = new Locale("de");
                config.setLocale(locale2);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                break;
        }
    }

    public void initApplyButtonClickListener() {
        mApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTheme = mThemesSpinner.getSelectedItemPosition();
                changeActivityTheme(selectedTheme);

                String selectedLang = mLanguagesSpinner.getSelectedItem().toString();
                changeLang(selectedLang);
            }
        });
    }

    private void changeActivityTheme(int selectedTheme) {
        switch (selectedTheme) {
            case 0:
                Utils.changeToTheme(MainActivity.this, Utils.THEME_BLACK);
                break;
            case 1:
                Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                break;
            case 2:
                Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                break;
        }
    }
}