package com.example.proyecto.ui.emergency.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.proyecto.R;
import com.example.proyecto.ui.emergency.FragmentEmergency;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityAddContact extends AppCompatActivity {


    private static CardView cardViewProfileImage;
    private static ImageView imageViewProfileImage;

    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextMessage;

    private CheckBox checkableSendEmail;
    private CheckBox checkableSendSMS;

    private static Boolean editTextNameBoolean = false;
    private static Boolean editTextPhoneBoolean = false;

    private static FloatingActionButton btnUpdateProfile;
    private static FloatingActionButton btnRemoveProfile;

    private int contactSelected;
    private static int imageSelected;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //Instantiate view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        cardViewProfileImage = findViewById(R.id.cardView_profileImage);
        cardViewProfileImage.setOnClickListener(onClickCardViewProfileImage);

        imageViewProfileImage = findViewById(R.id.imageView_profileImage);
        setProfileImage(R.drawable.avatar01); //important to initialize a default image to avoid null errors

        editTextName = findViewById(R.id.editText_name);
        editTextPhone = findViewById(R.id.editText_email);
        editTextEmail = findViewById(R.id.editText_subject);
        editTextMessage = findViewById(R.id.editText_message);

        checkableSendEmail = findViewById(R.id.checkBox_sendEmail);
        checkableSendEmail.setEnabled(false);
        checkableSendSMS = findViewById(R.id.checkBox_sendSMS);
        checkableSendSMS.setEnabled(false);

        btnUpdateProfile = findViewById(R.id.floationgActionButton_updateProfile);
        btnUpdateProfile.setOnClickListener(onClickAddProfile);
        setButtonState(btnUpdateProfile, false);

        btnRemoveProfile = findViewById(R.id.floatingActionButton_removeProfile);
        btnRemoveProfile.setOnClickListener(onClickRemoveProfile);


        //only start if the fragment or before embedded data (Not NULL)
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            //in case of longClick (edit) the data is received and then set in the editText and
            if (extras != null) {
                try {
                    //set variables
                    if (extras.getString("name") != null) {
                        //set required variables
                        setProfileImage(Integer.parseInt(extras.getString("image")));
                        editTextName.setText(extras.getString("name"));
                        editTextPhone.setText(extras.getString("phone"));
                        setButtonState(btnUpdateProfile, true);
                        if (extras.getString("email") != null && checkableSendEmail != null) {
                            //set optional variables
                            editTextEmail.setText(extras.getString("email"));
                            checkableSendEmail.setEnabled(true);
                            checkableSendEmail.setChecked(Boolean.parseBoolean(extras.getString("checkEmail")));
                        }
                        if (extras.getString("message") != null && checkableSendSMS != null) {
                            //set optional variables
                            editTextMessage.setText(extras.getString("message"));
                            checkableSendSMS.setEnabled(true);
                            checkableSendSMS.setChecked(Boolean.parseBoolean(extras.getString("checkSMS")));
                        }
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    finish();
                }
            }
        }


        //Watcher TextView Name
        editTextName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0 && s.toString().length() < 15) {
                    editTextNameBoolean = true;
                    if (checkAllTrue())
                        setButtonState(btnUpdateProfile, true);
                } else {
                    editTextNameBoolean = false;
                    setButtonState(btnUpdateProfile, false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Watcher TextView Phone
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 9) {
                    editTextPhoneBoolean = true;
                    if (checkAllTrue()) {
                        checkableSendSMS.setEnabled(true);
                        setButtonState(btnUpdateProfile, true);

                    } else {
                        editTextPhoneBoolean = false;
                        checkableSendSMS.setEnabled(false);
                        setButtonState(btnUpdateProfile, false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Whatcher TextView Email
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkEmail(s.toString()))
                    checkableSendEmail.setEnabled(true);
                else
                    checkableSendEmail.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        //return to emergency and cancel or remove process
        removeProfile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //get data from Intent
        super.onActivityResult(requestCode, resultCode, data);
        contactSelected = requestCode;
    }

    private void setButtonState(FloatingActionButton button, boolean bool) {
        //Validations for button
        if (bool) {
            button.setEnabled(true);
            button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorComplementary1)));

        } else {
            button.setEnabled(false);
            button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));

        }

    }

    private boolean checkAllTrue() {
        //responsible for checking that the name and telephone number have correct values
        if (editTextNameBoolean && editTextPhoneBoolean)
            return true;
        else
            return false;
    }

    private boolean checkEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /*-------------------------------------------------------LISTENERS---------------------------------------------------------------------*/

    private View.OnClickListener onClickCardViewProfileImage = new View.OnClickListener() {
        public void onClick(View root) {
            Intent activity = new Intent(ActivityAddContact.this, ActivityProfilePhotos.class);
            startActivity(activity);
        }
    };

    private View.OnClickListener onClickAddProfile = new View.OnClickListener() {
        public void onClick(View root) {
            //Starting the previous Intent
            Intent intent = new Intent(getApplicationContext(), FragmentEmergency.class);
            //Sending the data to Fragment
            intent.putExtra("action", Boolean.toString(true)); //action == create
            intent.putExtra("image", Integer.toString(imageSelected));
            intent.putExtra("name", editTextName.getText().toString());
            intent.putExtra("phone", editTextPhone.getText().toString());
            intent.putExtra("email", editTextEmail.getText().toString());
            intent.putExtra("checkEmail", Boolean.toString(checkableSendEmail.isChecked()));
            intent.putExtra("message", editTextMessage.getText().toString());
            intent.putExtra("checkSMS", Boolean.toString(checkableSendSMS.isChecked()));
            setResult(contactSelected, intent);
            finish();
        }
    };

    private View.OnClickListener onClickRemoveProfile = new View.OnClickListener() {
        public void onClick(View root) {
            removeProfile();
        }
    };

    /*--------------------------------------------------FINISH-LISTENERS---------------------------------------------------------------------*/

    public static void setProfileImage(Integer profileImage) {
        imageSelected = profileImage;
        imageViewProfileImage.setImageResource(profileImage);
    }

    private void removeProfile() {
        //Starting the previous Intent
        Intent emergencyFragment = new Intent(getApplicationContext(), FragmentEmergency.class);
        //Sending the data to Fragment
        emergencyFragment.putExtra("action", Boolean.toString(false)); //action == remove
        setResult(contactSelected, emergencyFragment);
        finish();
    }


}
