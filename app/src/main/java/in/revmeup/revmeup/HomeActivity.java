package in.revmeup.revmeup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import static in.revmeup.revmeup.R.menu.toolbar_menu;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
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

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        if (toolbar!=null)
            setSupportActionBar(toolbar);

    }

    void openFMessages(){
        Intent i = new Intent(this, FMessagesActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

//        MenuItem menuItem = menu.findItem(R.id.profile_btn);
//        View view = menuItem.getActionView();
//
//        CircleImageView profileimg = findViewById(R.id.toolbar_profile_img);
//        Glide
//                .with(this)
//                .load("https://images.unsplash.com/photo-1534308143481-c55f00be8bd7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1188&q=80")
//                .into(profileimg);
//
//        profileimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this, "Profile Image Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_btn:
                Toast.makeText(HomeActivity.this, "Search button clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.notification_btn:
                Toast.makeText(HomeActivity.this, "Notification button clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile_btn:
                Toast.makeText(HomeActivity.this, "Profile button clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
