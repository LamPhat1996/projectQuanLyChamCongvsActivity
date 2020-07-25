package tdc.edu.vn.projectquanlychamcongvsactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail, ePassword;
    private Button bLogin;
    private String email;
    private String password;
    private Cursor comprobar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        setControl();
        setEvent();
    }
    private void setEvent(){
        //chan dang nhap
        //
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                login();
//bo qua buoc dang nhap
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setControl(){
        eEmail = (EditText)findViewById(R.id.edtEmail);
        ePassword = (EditText)findViewById(R.id.edtPass);
        bLogin = (Button)findViewById(R.id.btnLogin);
//tab
        eEmail.setNextFocusDownId(R.id.edtPass);
        ePassword.setNextFocusDownId(R.id.btnLogin);
    }

    private void login() {

        if (!validate()) return;

        email = eEmail.getText().toString();
        password = ePassword.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Vui lòng chờ...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (email.equals("1") && password.equals("1")){

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                            String mesg = String.format("Login success", false);
                            Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }else{
                            progressDialog.dismiss();
                            String mesg = String.format("Login fail!!", false);
                            Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                        }
                    }
                },4000);
        eEmail.getText().clear();
        ePassword.getText().clear();

    }

    private boolean validate() {
        boolean valid = true;

        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eEmail.setError("Email không hợp lệ!");
            valid = false;
        } else {
            eEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            ePassword.setError("Mật khẩu 4 - 10 ký tự gồm chữ và số");
            valid = false;
        } else {
            ePassword.setError(null);
        }
        return valid;
    }
}
