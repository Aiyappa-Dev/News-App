package com.aimright.admin.demoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.adimoro.business.adimorosdk.interfaces.NotifyOperationComplete;
import com.adimoro.business.adimorosdk.utils.CommonUtils;
import com.adimoro.business.adimorosdk.utils.InfomoConstants;
import com.adimoro.business.adimorosdk.utils.SharedPreference;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.aimright.admin.demoapp.Models.Word;
/*import com.thedascapital.www.newsapp.R;*/
import com.aimright.admin.demoapp.viewModel.WordViewModel;
import com.aimright.admin.demoapp.R;


import java.util.ArrayList;

import static com.adimoro.business.adimorosdk.utils.InfomoConstants.PARAMS_REPO.apiKey;

public class UserProfile extends AppCompatActivity implements NotifyOperationComplete  {
    boolean check = false;
    private static final String TAG = "GTag";
    private WordViewModel mWordViewModel;
   // private SignInButton googleSignInButton;
    Button next;
    final int RC_SIGN_IN = 101;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    public static InfomoSDK infomoSDK;
    private SharedPreference pref;
    private String deviceID;
    private String fcmToken;
    private ArrayList<String> allpermissions;
    private ArrayList<String> requestpermissions;
    private NotifyOperationComplete notifyOperationComplet;
    private TextInputEditText Username, age;
    //  private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   googleSignInButton = findViewById(R.id.googleSignInButton);
       // loginInButton = findViewById(R.id.loginInButton);

        //userName=findViewById(R.id.userName);
        Username=findViewById(R.id.name_edit);
        //initialiseInfomo();
       // askforPermissions();
        next=findViewById(R.id.next);
        RadioGroup rbg=(RadioGroup) findViewById(R.id.radioGroup1);
        age=findViewById(R.id.age_edit);
       /* RadioButton rb  = (RadioButton) findViewById(R.id.radioButton1);
        Typeface font = Typeface.createFromAsset(getAssets(), "SF_Cartoonist_Hand_Bold.ttf");
        rb.setTypeface(font);
        RadioButton rb2  = (RadioButton) findViewById(R.id.radioButton2);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "SF_Cartoonist_Hand_Bold.ttf");
        rb2.setTypeface(font1);
        RadioButton rb3  = (RadioButton) findViewById(R.id.radioButton3);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "SF_Cartoonist_Hand_Bold.ttf");
        rb3.setTypeface(font3);*/

        /*radio button*/
        /*
        int selected=rbg.getCheckedRadioButtonId();
        RadioButton gender=(RadioButton) findViewById(selected);
        Toast.makeText(getApplicationContext(),gender.getText(),5000).show();*/
        next.setText("DONE");

        next.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    next.setBackgroundColor(getResources().getColor(R.color.color_blue));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    next.setBackgroundColor(getResources().getColor(R.color.konvers_incoming_call));
                }
                return false;
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = true;
                if (Username.getText().toString().isEmpty())
                {
                    Username.setError("Please Enter a valid Name");
                }
               else if (rbg.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                    Toast.makeText(UserProfile.this, "Please Select the Gender", Toast.LENGTH_SHORT).show();
                }
               else if (age.getText().toString().isEmpty())
                {
                    age.setError("Please Enter the age");
                }

                else
                {

                    /*To insert the data*/
                    Word word = new Word(0, Username.getText().toString(),"", age.getText().toString());
                    // Get a new or existing ViewModel from the ViewModelProvider.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mWordViewModel = new ViewModelProvider(UserProfile.this).get(WordViewModel.class);

                                       // ViewModelProviders.of(UserProfile.this).get(WordViewModel.class);
                                mWordViewModel.insert(word);
                                mWordViewModel.getAllWords().observe(UserProfile.this, words -> {
                                    // Update the cached copy of the words in the adapter.
                                    Log.e("value",words.toString());
                                });

                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }});

                    ArrayList<Word> List=new ArrayList<>();



                    /*To start the ads*/
                 /*   PackageManager pm  = UserProfile.this.getPackageManager();
                    ComponentName componentName = new ComponentName(UserProfile.this, CallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);*/

                   /* Intent i = new Intent(UserProfile.this, NewsActivity.class);
                    startActivity(i);
                    finish();*/
                    Intent i = new Intent(UserProfile.this, NewsActivity.class);
                    startActivity(i);
                    finish();

                }


               // Toast.makeText(getApplicationContext(),"Please Do Google Sign-In", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public static InfomoSDK getReference()
    {
        return  infomoSDK;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Authentication Success.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UserProfile.this, NewsActivity.class);
                            startActivity(i);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"Authentication Failed.", Toast.LENGTH_SHORT).show();
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }







    private void initialiseInfomo() {
        deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        infomoSDK = InfomoSDK.instance().init(getApplicationContext());
        infomoSDK.setPublisherKey("17a75d7742bcc60b207c178e4eeeec4714b3c767");
       // infomoSDK.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");
        infomoSDK.setDomain(InfomoConstants.INFOMO_URL.INFOMO_INDONESIA);
        pref = SharedPreference.getInstance(UserProfile.this);
        infomoSDK.checkAppUpdateAndRegistration();
        infomoSDK.setOperationCompleteCallback(this);





//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                   infomoSDK.register(deviceID, fcmToken);
//            }
//        }, 3000);

       /* infomoSDK.setOperationCompleteCallback(this);
        infomoSDK.register(deviceID, fcmToken);*/
        // infomoSDK!!.showAd()

    }

    private void registerTheDevice() {
        if (pref.getString(SharedPreference.KEY_APIKEY, null) == null) {
             deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//
//                    infomoSDK.setOperationCompleteCallback(this);
                    infomoSDK.register(deviceID, fcmToken);
                }
            }, 3000);
/*
            if (infomoSDK != null)
                infomoSDK.showAd();*/

        } else {
            infomoSDK.sync();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    /*
                    if (infomoSDK != null)
                        infomoSDK.showAd();*/
                }

            }, 5000);




        }
        CommonUtils.enableDebugMode(UserProfile.this);
    }



    private void  askforPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 && apiKey != null) {
            ArrayList<String> allpermissions = new ArrayList<String>();
            allpermissions.add(android.Manifest.permission.CALL_PHONE);
            allpermissions.add(android.Manifest.permission.READ_PHONE_STATE);
            allpermissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            allpermissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            allpermissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
            allpermissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            allpermissions.add(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);

            ArrayList<String> requestpermissions = new ArrayList<String>();

            for (String permission : allpermissions) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) == PackageManager.PERMISSION_DENIED) {
                    requestpermissions.add(permission);
                }
            }
            if (requestpermissions.size() > 0) {
                ActivityCompat.requestPermissions(this, requestpermissions.toArray(new String[requestpermissions.size()]), 11);
            } else {
                boolean prefUpdated = pref.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
                if (!prefUpdated) {
                    pref.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                    CommonUtils.setUserAdSelection(pref, UserProfile.this);
                }
            }
        } else {
            boolean prefUpdated = pref.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
            if (!prefUpdated) {
                pref.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                CommonUtils.setUserAdSelection(pref, UserProfile.this);
            }
        }

        fcmToken = pref.getString(SharedPreference.FCM_SERVER_TOKEN, null);
        if (fcmToken == null) {
            FirebaseApp.initializeApp(UserProfile.this);
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    fcmToken = instanceIdResult.getToken();
                    registerTheDevice();
                }
            });
            FirebaseInstanceId.getInstance().getInstanceId().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (fcmToken == null) {
                        fcmToken = "InvalidToken";
                    }
                }
            });


                //If it has failed this time to get the Firebase token, then it has to updated later
                //Once you get it by using
                //sdk.updateDeviceToken(fcmToken);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            SharedPreference adSettings = SharedPreference.getInstance(getApplicationContext());
            boolean prefUpdated = adSettings.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
            int counter = 0;
            if (!prefUpdated) {
                adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                CommonUtils.setUserAdSelection(adSettings, UserProfile.this);
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            } else {
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                            CommonUtils.setUserAdSelection(adSettings, UserProfile.this);
                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            }

            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED_TO_SERVER, false);
        }
    }


    @Override
    public void onOperationComplete(String s, Object o, int i) {
//        notifyOperationComplet= (NotifyOperationComplete) o;
    }
}
