package com.example.proyecto.ui.emergency;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.proyecto.R;
import com.example.proyecto.R.color;
import com.example.proyecto.ui.emergency.activities.ActivityAddContact;
import com.example.proyecto.ui.home.FragmentHome;

import java.util.Objects;


public class FragmentEmergency extends Fragment {
    private static final int REQUEST_CALL = 1;

    private LinearLayout LinearLayoutPlusAdd1;
    private LinearLayout LinearLayoutVisibilityCardAdd1;
    private LinearLayout LinearLayoutPlusAdd2;
    private LinearLayout LinearLayoutVisibilityCardAdd2;

    private static CardView cardViewEmergency;
    private static CardView cardViewPolice;
    private static CardView cardViewFireFighters;
    private static CardView cardViewSocialServices;
    private static CardView cardViewAddContact1;
    private static CardView cardViewAddContact2;

    private  ImageView imageViewEmergency;
    private  ImageView imageViewPolice;
    private  ImageView imageViewFireFighters;
    private  ImageView imageViewSocialServices;
    private  ImageView imageViewAddContact1;
    private  ImageView imageViewAddContact2;

    private  TextView textViewTitle;
    private  RelativeLayout relativeLayoutTitle;

    private  TextView textViewEmergency;
    private  TextView textViewPolice;
    private  TextView textViewFireFighters;
    private  TextView textViewSocialServices;
    private  TextView textViewAddContact1;
    private  TextView textViewAddContact2;

    private  Button ButtonPermissionsAlert;

    private  RelativeLayout RelativeLayoutPermission;
    private  RelativeLayout RelativeLayoutButtons;

    private static int image1 = 0;
    private static int image2 = 0;
    private static String name1 = null;
    private static String name2 = null;
    private static String phone1 = null;
    private static String phone2 = null;
    private static String email1 = null;
    private static String email2 = null;
    private static String message1 = null;
    private static String message2 = null;
    private static boolean sendEmail1, sendEmail2;
    private static boolean sendSMS1, sendSMS2;

    private static String emergency = "112";
    private static String police = "092";
    private static String fireFighters = "080";
    private static String socialServices = "936197311";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_emergency, container, false);


        RelativeLayoutPermission = root.findViewById(R.id.relativeLayout_Alert);
        RelativeLayoutButtons = root.findViewById(R.id.relativeLayout_ButtonsEmergency);

        textViewTitle = root.findViewById(R.id.textView_Titulo);
        relativeLayoutTitle = root.findViewById(R.id.relativeLayout_Title);

        cardViewEmergency = root.findViewById(R.id.cardView_Emergency);
        imageViewEmergency = root.findViewById(R.id.imageView_Emergency);
        textViewEmergency = root.findViewById(R.id.textView_Emergency);
        imageViewEmergency.setImageResource(R.drawable.avatar61);
        textViewEmergency.setText(R.string.emergency);
        cardViewEmergency.setOnClickListener(onClickEmergency);

        cardViewPolice = root.findViewById(R.id.cardView_Police);
        imageViewPolice = root.findViewById(R.id.imageView_Police);
        textViewPolice = root.findViewById(R.id.textView_Police);
        imageViewPolice.setImageResource(R.drawable.avatar31);
        textViewPolice.setText(R.string.police);
        cardViewPolice.setOnClickListener(onClickPolice);

        cardViewFireFighters = root.findViewById(R.id.cardView_FireFighter);
        imageViewFireFighters = root.findViewById(R.id.imageView_FireFighter);
        textViewFireFighters = root.findViewById(R.id.textView_FireFighter);
        imageViewFireFighters.setImageResource(R.drawable.avatar10);
        textViewFireFighters.setText(R.string.fireFighters);
        cardViewFireFighters.setOnClickListener(onClickFireFighter);

        cardViewSocialServices = root.findViewById(R.id.cardView_SocialServices);
        imageViewSocialServices = root.findViewById(R.id.imageView_SocialServices);
        textViewSocialServices = root.findViewById(R.id.textView_SocialServices);
        imageViewSocialServices.setImageResource(R.drawable.avatar47);
        textViewSocialServices.setText(R.string.socialServices);
        cardViewSocialServices.setOnClickListener(onClickSocialServices);

        cardViewAddContact1 = root.findViewById(R.id.cardView_AddContact1);
        LinearLayoutPlusAdd1 = root.findViewById(R.id.Linearlayout_PlusAdd1);
        LinearLayoutVisibilityCardAdd1 = root.findViewById(R.id.LinearLayout_CardAdd1);
        imageViewAddContact1 = root.findViewById(R.id.imageView_AddContact1);
        textViewAddContact1 = root.findViewById(R.id.textView_AddContact1);
        cardViewAddContact1.setOnClickListener(onClickCardViewAddContact1);
        cardViewAddContact1.setOnLongClickListener(onLongClickCardViewAddContact1);

        cardViewAddContact2 = root.findViewById(R.id.cardView_AddContact2);
        LinearLayoutPlusAdd2 = root.findViewById(R.id.LinearLayout_PlusAdd2);
        LinearLayoutVisibilityCardAdd2 = root.findViewById(R.id.LinearLayout_CardAdd2);
        imageViewAddContact2 = root.findViewById(R.id.imageView_AddContact2);
        textViewAddContact2 = root.findViewById(R.id.textView_AddContact2);
        cardViewAddContact2.setOnClickListener(onClickCardViewAddContact2);
        cardViewAddContact2.setOnLongClickListener(onLongClickCardViewAddContact2);

        ButtonPermissionsAlert = root.findViewById(R.id.button_permissionsAlert);
        ButtonPermissionsAlert.setOnClickListener(onClickPermissionsAlert);

        setLayoutsPermissions();


        return root;


    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            if (!FragmentHome.checkInDangerousArea()) {
                textViewTitle.setText(R.string.unSecureZone);
                relativeLayoutTitle.setBackgroundColor(getResources().getColor(color.colorAlert));
            } else {
                textViewTitle.setText(R.string.secureZone);
                relativeLayoutTitle.setBackgroundColor(getResources().getColor(color.colorSecundary));
            }
        } catch (NullPointerException e) {
            textViewTitle.setText(R.string.locationFailed);
            relativeLayoutTitle.setBackgroundColor(getResources().getColor(color.colorSecundaryDark));
        }
    }

    /*-----------------------------------------------------LISTENERS---------------------------------------------------------------*/

    private View.OnClickListener onClickSocialServices = new View.OnClickListener() {
        public void onClick(View root) {
            Toast.makeText(getContext(), "calling...", Toast.LENGTH_SHORT).show();
            call(socialServices);
        }
    };

    private View.OnClickListener onClickFireFighter = new View.OnClickListener() {
        public void onClick(View root) {
            call(fireFighters);
        }
    };

    private View.OnClickListener onClickPolice = new View.OnClickListener() {
        public void onClick(View root) {
            call(police);
        }
    };

    private View.OnClickListener onClickEmergency = new View.OnClickListener() {
        public void onClick(View root) {
            call(emergency);
        }
    };

    private View.OnClickListener onClickPermissionsAlert = new View.OnClickListener() {
        public void onClick(View root) {
            setLayoutsPermissions();

        }
    };

    private View.OnClickListener onClickCardViewAddContact1 = new View.OnClickListener() {
        public void onClick(View root) {
            if (phone1 != null) {
                call(phone1);
            } else {
                Intent activity = new Intent(FragmentEmergency.this.getContext(), ActivityAddContact.class);
                startActivityForResult(activity, 1); //RequestCode 1 == CardView 1
            }
        }
    };

    private View.OnLongClickListener onLongClickCardViewAddContact1 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Intent activity = new Intent(FragmentEmergency.this.getContext(), ActivityAddContact.class);
            //Validate code
            if (!textViewAddContact1.getText().toString().equals("") && phone1 != null) {
                //Add variables
                activity.putExtra("image", Integer.toString(image1));
                activity.putExtra("name", name1);
                activity.putExtra("phone", phone1);
                activity.putExtra("checkSMS", Boolean.toString(sendSMS1));
                if (email1 != null && !email1.equals("")) {
                    //Add optional variables
                    activity.putExtra("email", email1);
                    activity.putExtra("checkEmail", Boolean.toString(sendEmail1));
                }
                if (message1 != null && !message1.equals("")) {
                    //Add optional variables
                    activity.putExtra("message", message1);
                }
            }
            startActivityForResult(activity, 1); //RequestCode 1 == CardView 1
            return false;
        }
    };

    private View.OnClickListener onClickCardViewAddContact2 = new View.OnClickListener() {//CardView 2
        public void onClick(View root) {
            if (phone2 != null) {
                call(phone2);
            } else {
                Intent activity = new Intent(FragmentEmergency.this.getContext(), ActivityAddContact.class);
                startActivityForResult(activity, 2); //RequestCode 2 == CardView 2
            }
        }
    };

    private View.OnLongClickListener onLongClickCardViewAddContact2 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Intent activity = new Intent(FragmentEmergency.this.getContext(), ActivityAddContact.class);
            //Validators
            if (!textViewAddContact2.getText().toString().equals("") && phone2 != null) {
                //Add required variables
                activity.putExtra("image", Integer.toString(image2));
                activity.putExtra("name", name2);
                activity.putExtra("phone", phone2);
                activity.putExtra("checkSMS", Boolean.toString(sendSMS2));
                if (email1 != null && !email1.equals("")) {
                    //Add optional variables
                    activity.putExtra("email", email2);
                    activity.putExtra("checkEmail", Boolean.toString(sendEmail2));
                }
                if (message2 != null && !message2.equals("")) {
                    //Add optional variables
                    activity.putExtra("message", message2);

                }
            }
            startActivityForResult(activity, 2); //RequestCode 2 == CardView 2
            return false;
        }
    };

    /*--------------------------------------------------------FINISH-LISTENERS------------------------------------------------*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Receive data from Intent when finalize
        super.onActivityResult(requestCode, resultCode, data);
        boolean action, checkEmail, checkSMS;
        int image;
        String name, phone, email, message;

        assert data != null;
        action = Boolean.parseBoolean(data.getStringExtra("action"));

        if (action) {
            //Obtain variables from intent
            image = Integer.parseInt(Objects.requireNonNull(data.getStringExtra("image")));
            name = data.getStringExtra("name");
            phone = data.getStringExtra("phone");
            email = data.getStringExtra("email");
            message = data.getStringExtra("message");
            checkEmail = Boolean.parseBoolean(data.getStringExtra("checkEmail"));
            checkSMS = Boolean.parseBoolean(data.getStringExtra("checkSMS"));

        } else {
            //Not data in Intent? Set null
            image = 0;
            name = null;
            phone = null;
            email = null;
            message = null;
            checkEmail = false;
            checkSMS = false;


        }
        //Save data and set cardView Contact
        saveContact(requestCode, action, name, phone, email, message, image, checkEmail, checkSMS);
    }

    private void call(String number) {
        // call number
        if (setLayoutsPermissions()) {
            // Permission has already been granted
            String telephone = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(telephone)));
        }
    }

    private void saveContact(int cardView, boolean action, @Nullable String name, @Nullable String phone, @Nullable String email, @Nullable String message, @Nullable Integer imageProfile, boolean sendEmail, boolean sendSMS) {

        if (action) {
            if (cardView == 1) {
                //Edit Cardview1
                imageViewAddContact1.setImageResource(imageProfile);
                textViewAddContact1.setText(name);
                LinearLayoutPlusAdd1.setVisibility(View.GONE);
                LinearLayoutVisibilityCardAdd1.setVisibility(View.VISIBLE);
                image1 = imageProfile;
                name1 = name;
                phone1 = phone;
                email1 = email;
                message1 = message;
                sendEmail1 = sendEmail;
                sendSMS1 = sendSMS;
            } else if (cardView == 2) {
                //Edit CardView 2
                imageViewAddContact2.setImageResource(imageProfile);
                textViewAddContact2.setText(name);
                LinearLayoutPlusAdd2.setVisibility(View.GONE);
                LinearLayoutVisibilityCardAdd2.setVisibility(View.VISIBLE);
                image2 = imageProfile;
                name2 = name;
                phone2 = phone;
                email2 = email;
                message2 = message;
                sendEmail2 = sendEmail;
                sendSMS2 = sendSMS;
            }
        } else {
            if (cardView == 1) {
                //Remove CardView 1
                imageViewAddContact1.setImageResource(0);
                textViewAddContact1.setText(null);
                LinearLayoutVisibilityCardAdd1.setVisibility(View.GONE);
                LinearLayoutPlusAdd1.setVisibility(View.VISIBLE);
                image1 = 0;
                name1 = null;
                phone1 = null;
                email1 = null;
                message1 = null;
                sendEmail1 = false;
                sendSMS1 = false;
            } else if (cardView == 2) {
                //Remove CardView 2
                imageViewAddContact2.setImageResource(0);
                textViewAddContact2.setText(null);
                LinearLayoutVisibilityCardAdd2.setVisibility(View.GONE);
                LinearLayoutPlusAdd1.setVisibility(View.VISIBLE);
                image2 = 0;
                name2 = null;
                phone2 = null;
                email2 = null;
                message2 = null;
                sendEmail2 = false;
                sendSMS2 = false;
            }
        }
    }


    private boolean setLayoutsPermissions() {
        //Permissions to call have been granted? Set visibility according to permissions
        boolean permission = false;
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            RelativeLayoutPermission.setVisibility(View.GONE);
            RelativeLayoutButtons.setVisibility(View.VISIBLE);
            permission = true;
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            RelativeLayoutButtons.setVisibility(View.GONE);
            RelativeLayoutPermission.setVisibility(View.VISIBLE);

        }
        return permission;
    }
}