package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText getUserName;
    EditText getUserPassword;
    EditText getUserEmailAddress;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout textInputLayout;
    Button confirmButton;

    int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase database;

    String userName;
    String userPassword;
    String userCountry;
    String userEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getUserName = findViewById(R.id.userName_textField);
        getUserPassword = findViewById(R.id.userPassword_textField);
        getUserEmailAddress = findViewById(R.id.userEmail_textField);
        textInputLayout = findViewById(R.id.country_field);

        autoCompleteTextView = findViewById(R.id.userCountry_field);
        confirmButton = findViewById(R.id.confirmReg_button);


        //функционал для стран
        ArrayList<Country> countries = new ArrayList<>();
        int countryImage_list[] = new int[]{R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
        String countryName_list[] = new String[]{"Шри-Ланка", "Шри-ланка"};
        for (int i = 0; i < countryName_list.length; i++) {
            countries.add(new Country(countryName_list[i], countryImage_list[i]));
        }

        ArrayList<Country> a = new ArrayList<>();
        a.add(new Country("ada", R.drawable.ic_launcher_background));

        ArrayAdapter adapter = new CountryAdapter(getApplicationContext(), R.layout.list, R.id.list_1, countries);

        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(adapter);
//        autoCompleteTextView.setAdapter(adapter);
          Log.i("TAG","TEST");



//подключение к бд
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new User_Database(RegisterActivity.this, "CAR_USERS", null, DB_VERSION);
            database = sqLiteOpenHelper.getWritableDatabase();
            Log.i("TAG", "Database connected");
            cursor = database.query("USER", new String[]{"NAME"}, null, null, null, null, null);

        } catch (Exception exception) {
            Log.i("TAG", "Connection failed");
            exception.printStackTrace();
        }


//добавление в бд пользователя
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = getUserName.getText().toString().replaceAll(" ", "");
                userPassword = getUserPassword.getText().toString().replaceAll(" ", "");
                userEmailAddress = getUserEmailAddress.getText().toString().replaceAll(" ", "");
                userCountry = textInputLayout.getEditText().getText().toString();

                if (userName != "" && userPassword != "" && userEmailAddress != "" && userCountry != "") {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("NAME", userName);
                    contentValues.put("PASSWORD", userPassword);
                    contentValues.put("EMAIL", userEmailAddress);
                    contentValues.put("COUNTRY", userCountry);
//                    database.insert("USER", null, contentValues);

                    database.close();
                } else {
                    userName = "";
                    userPassword = "";
                    userCountry = "";
                    userEmailAddress = "";
                    Toast.makeText(getApplicationContext(), "....", Toast.LENGTH_SHORT).show();
                }

            }
            //смена активити
        });


    }
}