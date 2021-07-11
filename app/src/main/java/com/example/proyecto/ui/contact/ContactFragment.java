package com.example.proyecto.ui.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyecto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactFragment extends Fragment {

    private FloatingActionButton SendButton;
    //Get email values
    private EditText EditTextSubject;
    private EditText editTextEmail;
    private EditText editTextMessage;
    private EditText editTextName;
    private CheckBox checkBoxSupport;
    private CheckBox checkBoxSuggestions;

    private String textSubject;
    private String textEmail;
    private String textMessage;
    private String textName;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        SendButton = root.findViewById(R.id.floationgActionButton_updateProfile);
        EditTextSubject = root.findViewById(R.id.editText_subject);
        editTextEmail = root.findViewById(R.id.editText_email);
        editTextMessage = root.findViewById(R.id.editText_message);
        editTextName = root.findViewById(R.id.editText_name);
        checkBoxSupport = root.findViewById(R.id.checkBox_support);
        checkBoxSuggestions = root.findViewById(R.id.checkBox_suggestions);

        SendButton.setOnClickListener(myListener);

        return root;
    }


    private View.OnClickListener myListener = new View.OnClickListener() { //social
        public void onClick(View root) {
            sendEmail(root);
        }
    };

    @SuppressLint("IntentReset") //suppress alert emailIntent.setData and .setType
    private void sendEmail(View root) {
        //Obtain text variables
        textSubject = EditTextSubject.getText().toString();
        textEmail = editTextEmail.getText().toString();
        textMessage = editTextMessage.getText().toString();
        textName = editTextName.getText().toString();
        String supportEmail = "";

        if (checkBoxSupport.isChecked()) supportEmail = "support@gotogo.com";
        else if (checkBoxSuggestions.isChecked()) supportEmail = "suggestionssupport@gotogo.com";
        else supportEmail = "general@gotogo.com";


        String[] TO = {supportEmail}; // mail
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        // Asunto, cuerpo, etc
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, textSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, textMessage + "Contact email: " + textEmail + "Contact name: " + textName);

        try {
            startActivity(Intent.createChooser(emailIntent, "Sending..."));
        } catch (android.content.ActivityNotFoundException ex) {
            //If don't find any app
            Toast.makeText(getActivity(),
                    "I can't find compatible email applications", Toast.LENGTH_SHORT).show();
        }
    }
}