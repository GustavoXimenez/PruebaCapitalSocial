package com.androidavanzado.capitalsocial.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidavanzado.capitalsocial.R;
import com.androidavanzado.capitalsocial.common.GetProgressDialog;
import com.androidavanzado.capitalsocial.common.Preferences;
import com.androidavanzado.capitalsocial.retrofit.CapitalSocialClient;
import com.androidavanzado.capitalsocial.retrofit.CapitalSocialService;
import com.androidavanzado.capitalsocial.retrofit.request.RequestLogin;
import com.androidavanzado.capitalsocial.retrofit.response.ResponseAuth;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    // var Scanner
    private ZXingScannerView scannerView;

    //Views
    private EditText edtUser;
    private EditText edtPassword;

    //Var Retrofit
    private CapitalSocialClient capitalSocialClient;
    private CapitalSocialService capitalSocialService;

    //Var
    private MainActivity context;
    private SharedPreferences sharedPreferences;
    private Preferences preferences = new Preferences();
    private GetProgressDialog getProgressDialog = new GetProgressDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        retrofitInit();
        initView();
    }

    private void retrofitInit() {
        capitalSocialClient = CapitalSocialClient.getInstance();
        capitalSocialService = capitalSocialClient.getCapitalSocialService();
    }

    private void initView(){
        edtUser = (EditText) findViewById(R.id.editTextUser);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void ScannerQR(View view){
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para la camara!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            /* Log.i("Mensaje", "Tienes permiso para usar la camara.");
            scannerView = new ZXingScannerView(context);
            setContentView(scannerView);
            scannerView.setResultHandler(context);
            scannerView.startCamera(); */

            Intent intent = new Intent(MainActivity.this, ScannerQRActivity.class);
            startActivity(intent);
        }
    }

    public void LoginUser(View view){
        if(isValidEmail(edtUser.getText())){
            String user = edtUser.getText().toString();
            String password = edtPassword.getText().toString();
            if(password == null || password.equals("")){
                edtPassword.setError(getString(R.string.login_password_incorrect));
            } else {
                // realizamos el llamado login a la API REST
                goToLogin(user, password);
            }
        } else {
            edtUser.setError(getString(R.string.login_email_incorrect));
        }
    }

    private void goToLogin(final String user, final String password) {
        getProgressDialog.startProgressDialog(context);
        RequestLogin requestLogin = new RequestLogin(user, password);
        Call<ResponseAuth> call = capitalSocialService.doLogin(requestLogin);
        call.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                getProgressDialog.stopProgressDialog(context);
                if(response.isSuccessful()){
                    ResponseAuth userAuth = response.body();
                    saveSharedPreference(user, password, userAuth.getId());
                    Toast.makeText(context, "Sesion iniciada", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, PromotionsActivity.class);
                    startActivity(intent);

                    // Destruimos este Activity, no permite retornar al login
                    finish();
                } else {
                    Toast.makeText(context, "Problemas de acceso: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                getProgressDialog.stopProgressDialog(context);
                Toast.makeText(context, "Problemas de conexion. Intentelo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveSharedPreference(String user, String pass, int id){
        sharedPreferences = context.getSharedPreferences("Login", MODE_PRIVATE);
        preferences.setLoginPreferences(sharedPreferences, user, pass, id);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(scannerView!=null){
            scannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        edtPassword.setText(result.getText());
        //scannerView.resumeCameraPreview(context);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
