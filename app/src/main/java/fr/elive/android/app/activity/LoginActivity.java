package fr.elive.android.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.elive.android.app.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_identifiant) EditText _identificationText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");



        if (!validate()) {
            onLoginFailed();
            return;
        }



        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String identifiant = _identificationText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        // On complete call either onLoginSuccess or onLoginFailed
                        if((identifiant =="elive") & (password =="elive"))
                        {
                            onLoginSuccess();
                        }
                        // onLoginFailed();
                        else{
                            progressDialog.dismiss();
                            return;
                        }
                    }
                }, 3000);
        Intent intent = new Intent(this, FragmentsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {


    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Erreur de connexion", Toast.LENGTH_LONG).show();


    }

    public boolean validate() {
        boolean valid = true;

        String identifiant = _identificationText.getText().toString();
        String password = _passwordText.getText().toString();

        if (identifiant.isEmpty() || (identifiant.length()<=4 && identifiant.length()>10)) {
            _identificationText.setError("Entrer un identifiant valide");
            valid = false;
        } else {
            _identificationText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Le mot de passe contient entre 4 et 10 caractères");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
