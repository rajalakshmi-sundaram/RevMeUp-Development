package in.revmeup.revmeup;

import android.os.Bundle;
import android.view.View;
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

    private TextView mTextView;
    private DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mTextView = (TextView) findViewById(R.id.text);
        final TextView message = findViewById(R.id.message_page);
        myDatabase = FirebaseDatabase.getInstance().getReference("Message");

        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                message.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                message.setText("CANCELLED");
            }
        });
    }

    public void sendMessage(View v){
        EditText text = findViewById(R.id.text);
        myDatabase.child("Message").setValue(text.getText().toString());
        text.setText("");
    }
}