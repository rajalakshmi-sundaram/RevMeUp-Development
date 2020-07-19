package in.revmeup.revmeup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageActivity extends AppCompatActivity {

    private TextView mTextView, message;
    Button sendmessage;
    private DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
//        mTextView = (TextView) findViewById(R.id.type_message);
        message = (TextView) findViewById(R.id.message_page);
        final TextView message = findViewById(R.id.message_page);
        sendmessage = findViewById(R.id.send_message);
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        myDatabase = FirebaseDatabase.getInstance().getReference().child("Message");

        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    message.setText(singleSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                message.setText("CANCELLED");
            }
        });
    }

    public void sendMessage(){
        EditText text = findViewById(R.id.type_message);
        myDatabase.child("Message 1").setValue(message.getText().toString());
//        message.setText("");
    }


}