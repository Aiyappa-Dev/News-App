package com.aimright.admin.demoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.adimoro.business.adimorosdk.model.ActionReport;
import com.adimoro.business.adimorosdk.model.ActionResponse;
import com.adimoro.business.adimorosdk.network.ApiService;
import com.adimoro.business.adimorosdk.network.ServiceGenerator;
import com.adimoro.business.adimorosdk.provider.PermissionManager;
import com.adimoro.business.adimorosdk.utils.LogE;
import com.aimright.admin.demoapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*import com.thedascapital.www.newsapp.R;*/

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent user=new  Intent(WelcomeScreen.this, UserProfile.class);
            startActivity(user);
            }
        });

       // checkMobileForPermission();
       // checkAutoStart();
        String manufacturer = android.os.Build.MANUFACTURER;
        if ("oppo".equalsIgnoreCase(manufacturer)) {
            autoStartdialog();
        }

        next.setOnTouchListener((view, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                next.setBackgroundColor(getResources().getColor(R.color.color_blue));
            } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                next.setBackgroundColor(getResources().getColor(R.color.konvers_incoming_call));
            }
            return false;
        });

    }



    private void autoStartdialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Click Okay to Process");

        // set dialog message
        alertDialogBuilder
                .setMessage("Please Enable the Autostart in  settings!")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                        checkAutoStart();
                    }
                });
               /* .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });*/

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }



    private void checkAutoStart() {
        try {
            Intent intent = new Intent();
            String manufacturer = android.os.Build.MANUFACTURER;
            if ("oppo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            }

            /*if ("xiaomi".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            }
            else if ("oppo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            }
            else if ("vivo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
            }
            else if("oneplus".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListAct‌​ivity"));
            }*/

            List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if  (list.size() > 0) {
                startActivity(intent);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }



    private void checkMobileForPermission() {
        try {
            Intent intent = new Intent();
            if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
                dialog();
              /*  intent.setComponent(new ComponentName("com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                startActivity(intent);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Click Okay to Process");

        // set dialog message
        alertDialogBuilder
                .setMessage("Please Enable the start in background in other permission!")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                        appSetting();
                    }
                });
               /* .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });*/

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void appSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);


    }

}
