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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//test3
public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText eid, pwd;
        Button signupButton;
        final Button homepageButton;

        mFirebaseAuth = FirebaseAuth.getInstance();
        eid = findViewById(R.id.enter_username_or_email);
        pwd = findViewById(R.id.enter_password);
        signupButton =(Button) findViewById(R.id.sign_up);
        homepageButton = findViewById(R.id.sign_up);


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
            public void onClick(final View v) {

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
                                    if(task.isSuccessful()){
                                        Snackbar.make(v, "Success", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                        success();
                                    }
                                    task.getException().printStackTrace();
                                    task.getException().getMessage();
                                }
                            });
                    homepageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openHomepage();
                        }
                    });

                }


                else{
                    Toast.makeText(SignUpActivity.this, "Error occurred. Try again", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    public void success() {
        Intent intToHome = new Intent(this, HomeActivity.class);
        startActivity(intToHome);
    }

    public void openHomepage(){
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
