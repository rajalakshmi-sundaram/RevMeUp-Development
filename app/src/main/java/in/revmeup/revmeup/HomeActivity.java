package in.revmeup.revmeup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import in.revmeup.revmeup.ui.main.contests;


public class HomeActivity extends AppCompatActivity {
    FloatingActionButton prod;
    FloatingActionButton post;
    FloatingActionButton message;
    FloatingActionButton contest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        prod = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductsActivity.class));
            }
        });
        post = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, activity_post.class));
            }
        });
        message = (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FMessagesActivity.class));
            }
        });

        contest = (FloatingActionButton) findViewById(R.id.floatingActionButton6);
        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, contests.class));
            }
        });

    }
}




