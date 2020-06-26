package in.revmeup.revmeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button msgButton = findViewById(R.id.gotoMessages);
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFMessages();
            }
        });
    }

    void openFMessages(){
        Intent i = new Intent(this, FMessagesActivity.class);
        startActivity(i);
    }
}
