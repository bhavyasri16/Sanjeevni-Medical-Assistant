package com.finalproject.it.sanjeevni.activities.ui.login;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.finalproject.it.sanjeevni.R;
import com.finalproject.it.sanjeevni.fragment.Confirm_dr_dialog;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements Confirm_dr_dialog.Confirm_dr_dialogListener {

    EditText firstname, lastname, emailid, phone, password, confirm_password, dob, city;
    RadioGroup gender;
    RadioButton sel_gen;
    Button registerButton,dr_register;
    Date date_of_birth;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=\\S+$)" + ".{5,}" + "$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^" + "(?=\\S+$)" + ".{10,}" + "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = (EditText) findViewById(R.id.first_name);
        lastname = (EditText) findViewById(R.id.last_name);
        emailid = (EditText) findViewById(R.id.email_id);
        phone = (EditText) findViewById(R.id.phone_no);
        password = (EditText) findViewById(R.id.create_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        gender =(RadioGroup) findViewById(R.id.gender_radio);
        sel_gen=(RadioButton) findViewById(gender.getCheckedRadioButtonId());
        registerButton = (Button) findViewById(R.id.register_button);
        dr_register=(Button) findViewById(R.id.register_dr_button);
        city = (EditText) findViewById(R.id.city);

        dob= (EditText) findViewById(R.id.dob);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(dob,myCalendar);
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFirstname() | !validateLastname() | !validateEmail() | !validatePhone() | !validateDate() | !validatePassword() | !validateConfirmPassword())
                {
                    return;
                }
                Toast.makeText(RegisterActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        });

        this.dr_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFirstname() | !validateLastname() | !validateEmail() | !validatePhone() | !validateDate() | !validatePassword() | !validateConfirmPassword())
                {
                    return;
                }
                openDialog();
            }
        });
    }
    public void openDialog(){
        Confirm_dr_dialog confirmdialog = new Confirm_dr_dialog();
        confirmdialog.show(getSupportFragmentManager(),"example");
    }


    private void updateLabel( EditText dob, Calendar myCal) {
        String myFormat = "mm/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCal.getTime()));
    }

    private boolean validateFirstname() {
        String usernameInput = firstname.getEditableText().toString().trim();
        String[] arr= usernameInput.split(" ");
        if (usernameInput.isEmpty()) {
            firstname.setError("Field can't be empty");
            return false;
        } else if (arr.length>1) {
            firstname.setError("Only one word required");
            return false;
        } else {
            firstname.setError(null);
            return true;
        }
    }

    private boolean validateLastname() {
        String usernameInput = lastname.getEditableText().toString().trim();
        String[] arr= usernameInput.split(" ");
        if (usernameInput.isEmpty()) {
            lastname.setError(null);
            return true;
        } else if (arr.length>1) {
            lastname.setError("Only one word required");
            return false;
        } else {
            lastname.setError(null);
            return true;
        }
    }

    private boolean validateCity() {
        String cityname = city.getEditableText().toString().trim();
        String[] arr= cityname.split(" ");
        if (cityname.isEmpty()) {
            city.setError(null);
            return true;
        }
        else {
            city.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        String emailInput = emailid.getEditableText().toString().trim();
        if (emailInput.isEmpty()) {
            emailid.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailid.setError("Please enter a valid email address");
            return false;
        } else {
            emailid.setError(null);
            return true;
        }
    }
    private boolean validatePhone() {
        String Input = phone.getEditableText().toString().trim();
        if (Input.isEmpty()) {
            phone.setError("Field can't be empty");
            return false;
        } else if (!PHONE_PATTERN.matcher(Input).matches()) {
            phone.setError("Please enter a valid Mobile Number");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = password.getEditableText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Min. 5 characters required(No Spaces Allowed)");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validateConfirmPassword() {
        String passwordInput = confirm_password.getEditableText().toString().trim();
        if (passwordInput.isEmpty()) {
            confirm_password.setError("Field can't be empty");
            return false;
        } else if (!(password.getEditableText().toString().trim().equals(passwordInput))) {
            confirm_password.setError("Password does not match");
            return false;
        } else {
            confirm_password.setError(null);
            return true;
        }
    }
    private boolean validateDate() {
        String Input = dob.getEditableText().toString().trim();
        if (Input.isEmpty()) {
            dob.setError("Date of Birth Required");
            return false;
        } else {
            dob.setError(null);
            return true;
        }
    }
}
