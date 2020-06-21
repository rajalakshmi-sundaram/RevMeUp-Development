package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
    EditText eid, pwd;
    Button loginButton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        eid = (EditText) findViewById(R.id.enter_username_or_email);
        pwd = (EditText) findViewById(R.id.enter_password);

        loginButton = (Button) findViewById(R.id.log_in);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = eid.getText().toString();
                String password = pwd.getText().toString();

                if(email.isEmpty())
                {
                    eid.setError("Enter username or email");
                    eid.requestFocus();
                }

                if(password.isEmpty())
                {
                    pwd.setError("Enter password");
                    pwd.requestFocus();
                }

                if(email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
                }

                else if(!(email.isEmpty() && password.isEmpty()))
                {

                    Toast.makeText(LoginActivity.this,"Registered Successfully "+email,Toast.LENGTH_SHORT).show();
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    }
                                    task.getException().printStackTrace();


                                    Toast.makeText(LoginActivity.this,"In else part"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                }
                            });
                }

                else{
                    Toast.makeText(LoginActivity.this, "Error occurred. Try again", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}
