package com.example.covid19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD =
            Pattern.compile("^" +
                    "(?=.*[A-Z])" +
                    "(?=.*[a-z])" +
                    "(?=.*[0-9])" +
                    "(?=.*[@#$%^&*+=,.?;:])" +
                    ".{8,}" + "$");


    RadioGroup rg;
    RadioButton rb;
    RadioGroup rg1;


    DatePickerDialog.OnDateSetListener setListener;


//    EditText etNameDepan, etNamaBelakang, etTempalLahir, etTanggalLahir, etAlamat, etTelepon, etEmail, etPassword, etConfirmPassword;
//    Button btSubmit;

    AwesomeValidation awesomeValidation;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    TextView tv_NamaDepan, tv_NamaBelakang, tv_TTL, tv_Alamat,
            tv_JK, tv_Agama, tv_NoTelp, tv_Email;
    EditText et_namadpn, et_namablkng, et_tmpt, et_tgl,
            et_alamat, et_telp, et_email, et_pwd, et_pwd2;
    String namaDpn, namaBlkng, tempat, tanggal, alamat, telp, email;
    RadioGroup rg_jk, rg_agama1, rg_agama2;
    Button btn_kembali, btn_daftar;
    AwesomeValidation validation;
    private Button showDialogButton;

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_tgl.setText(sdf.format(myCalendar.getTime()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_namadpn = (EditText) findViewById(R.id.namaDepan);
        et_namablkng = (EditText) findViewById(R.id.namaBelakang);
        et_tmpt = (EditText) findViewById(R.id.tempatLahir);
        et_tgl = (EditText) findViewById(R.id.tanggalLahir);
        et_alamat = (EditText) findViewById(R.id.alamat);
        et_telp = (EditText) findViewById(R.id.telepon);
        et_email = (EditText) findViewById(R.id.email);
        et_pwd = (EditText) findViewById(R.id.pass);
        et_pwd2 = (EditText) findViewById(R.id.passReturn);

        btn_daftar = findViewById(R.id.daftar);
        btn_kembali = findViewById(R.id.kembali);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        et_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_DeviceDefault_DayNight, setListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "-" + month + "-" + year;
                et_tgl.setText(date);
            }
        };

        et_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "-" + month + "-" + year;
                        et_tgl.setText(date);
                    }
                }, year, month, day
                );
                datePickerDialog.show();
            }
        });
        validation = new AwesomeValidation(ValidationStyle.BASIC);

        validation.addValidation(this, R.id.namaDepan,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        validation.addValidation(this, R.id.namaBelakang,
                RegexTemplate.NOT_EMPTY, R.string.invalid_nameBelakang);
        validation.addValidation(this, R.id.tempatLahir,
                RegexTemplate.NOT_EMPTY, R.string.invalid_tempat);
        validation.addValidation(this, R.id.tanggalLahir,
                RegexTemplate.NOT_EMPTY, R.string.invalid_tanggal);
        validation.addValidation(this, R.id.alamat,
                RegexTemplate.NOT_EMPTY, R.string.invalid_alamat);
        validation.addValidation(this, R.id.telepon,
                RegexTemplate.NOT_EMPTY, R.string.invalid_telepon);
        validation.addValidation(this, R.id.email,
                RegexTemplate.NOT_EMPTY, R.string.invalid_email);
        validation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        validation.addValidation(this, R.id.pass,
                RegexTemplate.NOT_EMPTY, R.string.invalid_password);
        validation.addValidation(this, R.id.passReturn,
                PASSWORD, R.string.invalid_confirm_password);


        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog();
                if (validation.validate()) {
                    showDialog();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Mohon Isi Data Yang Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            private void showDialog() {
            }
        });


//        showDialogButton = (Button) findViewById(R.id.btnOk);
//
//        showDialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCustomDialog();
//            }
//        });
//    }
//
//    private void showCustomDialog() {
//        final Dialog dialog = new Dialog(this);
//
//
//        dialog.setTitle("Detail Pendaftaran");
//
//
//        dialog.setContentView(R.layout.activity_main2);
//
//
//        dialog.setCanceledOnTouchOutside(false);
//
//
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        int width = metrics.widthPixels;
//        dialog.getWindow().setLayout((10 * width) / 30, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        Button OKButton = (Button) dialog.findViewById(R.id.btnOk);
//        Button OutButton = (Button) dialog.findViewById(R.id.btnKeluar);
//
//        OKButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                Toast.makeText(MainActivity.this, "Pendaftaran Selesai", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
////                startActivity(i);
//            }
//        });
//
//        OutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.this.finish();
//            }
//        });
//
//        //Menampilkan Cutom Dialog
//        dialog.show();
    }

    //        myCalendar      = Calendar.getInstance();
//
//        date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//        };
//
//        et_tgl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                new DatePickerDialog(MainActivity.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//        validation = new AwesomeValidation(ValidationStyle.BASIC);
//
//        validation.addValidation(this,R.id.namaDepan,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
//        validation.addValidation(this,R.id.namaBelakang,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_nameBelakang);
//        validation.addValidation(this,R.id.tempatLahir,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_tempat);
//        validation.addValidation(this,R.id.tanggalLahir,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_tanggal);
//        validation.addValidation(this,R.id.alamat,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_alamat);
//        validation.addValidation(this,R.id.telepon,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_telepon);
//        validation.addValidation(this,R.id.email,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_email);
//        validation.addValidation(this,R.id.email,
//                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
//        validation.addValidation(this,R.id.pass,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_password);
//        validation.addValidation(this,R.id.passReturn,
//                PASSWORD,R.string.invalid_confirm_password);

//        btn_daftar.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                //showDialog();
//                if (validation.validate()){
//                    showDialog();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),
//                            "Mohon Isi Data Yang Kosong",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            private void showDialog() {
//            }
//        });

//        btn_kembali.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                finish();
//                System.exit(0);
//            }
//        });
//}
//        validation.addValidation(this,R.id.addConfirmPassword,
//                RegexTemplate.NOT_EMPTY,R.string.empty_confirmPassword);
//        validation.addValidation(this,R.id.addConfirmPassword,
//                R.id.addPassword,R.string.invalid_confirmpassword);

//        tvDate = findViewById(R.id.tanggalLahir);
//        etDate = findViewById(R.id.tanggalLahir);
//
//        Calendar calendar = Calendar.getInstance();
//        final int year = calendar.get(Calendar.YEAR);
//        final int month = calendar.get(Calendar.MONTH);
//        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        tvDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        MainActivity.this, android.R.style.Theme_DeviceDefault_DayNight, setListener, year, month, day);
//
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//        setListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//                String date = " " + day + "-" + month + "-" + year;
//                tvDate.setText(date);
//            }
//        };
//
//        etDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month = month + 1;
//                        String date = " " + day + "-" + month + "-" + year;
//                        etDate.setText(date);
//                    }
//                }, year, month, day
//                );
//                datePickerDialog.show();
//            }
//        });


//        rg = (RadioGroup) findViewById(R.id.radioGroup1);
//        rg1 = (RadioGroup) findViewById(R.id.radioGroup2);


    // }

    public void rbClick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);

        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void rbClick2(View v) {
        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_SHORT).show();
    }
//
//        int radiobuttonid = rg1.getCheckedRadioButtonId();
//        rb = (RadioButton) findViewById(radiobuttonid);


//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        et_namadpn = findViewById(R.id.namaDepan);
//        et_namablkng = findViewById(R.id.namaBelakang);
//        et_tmpt = findViewById(R.id.tempatLahir);
//        et_tgl = findViewById(R.id.tanggalLahir);
//        et_alamat = findViewById(R.id.alamat);
//        et_telp = findViewById(R.id.telepon);
//        et_email = findViewById(R.id.email);
//        et_pwd= findViewById(R.id.pass);
//        et_pwd2 = findViewById(R.id.passReturn);
//        btn_daftar = findViewById(R.id.daftar);
//
//
//        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
//        awesomeValidation.addValidation(this, R.id.namaDepan,
//                RegexTemplate.NOT_EMPTY, R.string.invalid_name);
//        awesomeValidation.addValidation(this, R.id.namaBelakang,
//                RegexTemplate.NOT_EMPTY, R.string.invalid_nameBelakang);
//        awesomeValidation.addValidation(this, R.id.telepon,
//                "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$",
//                R.string.invalid_telepon);
//        awesomeValidation.addValidation(this, R.id.tanggalLahir,
//                RegexTemplate.NOT_EMPTY, R.string.invalid_tanggal);
//        awesomeValidation.addValidation(this, R.id.tempatLahir,
//                RegexTemplate.NOT_EMPTY, R.string.invalid_tempat);
//        awesomeValidation.addValidation(this, R.id.alamat,
//                RegexTemplate.NOT_EMPTY, R.string.invalid_alamat);
//        awesomeValidation.addValidation(this, R.id.email,
//                Patterns.EMAIL_ADDRESS, R.string.invalid_email);
//        awesomeValidation.addValidation(this, R.id.pass,
//                ".{6,}", R.string.invalid_password);
//        awesomeValidation.addValidation(this, R.id.passReturn,
//                R.id.pass, R.string.invalid_confirm_password);
//
//        btn_daftar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (awesomeValidation.validate()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Form Validate Successfully...", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "Validation Falid.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }


}

