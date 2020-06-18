package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final FirebaseAuth mFirebaseAuth;
        final EditText eid, pwd;
        Button signupButton;
        Button loginButton;

        mFirebaseAuth = FirebaseAuth.getInstance();
        eid = findViewById(R.id.enter_username_or_email);
        pwd = findViewById(R.id.enter_password);
        signupButton = findViewById(R.id.sign_up_button);
        loginButton = findViewById(R.id.log_in_button);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null){
                    Toast.makeText(SignUpActivity.this, "You are logged in", Toast.LENGTH_SHORT);
                    Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Please login", Toast.LENGTH_SHORT);
                }
            }
        };

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eid.getText().toString();
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
                    Toast.makeText(SignUpActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
                }

                else if(!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "Sign up error. Please try again", Toast.LENGTH_SHORT);
                                    }
                                    else{
                                        Intent intToHome = new Intent(SignUpActivity.this, HomeActivity.class);
                                        startActivity(intToHome);
                                    }
                                }
                            });
                }

                else{
                    Toast.makeText(SignUpActivity.this, "Error occurred. Try again", Toast.LENGTH_SHORT);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intToLogin);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
