package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import in.revmeup.revmeup.ui.main.contests;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.nav_feed:
                            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                            break;

                        case R.id.nav_contest:
                            startActivity(new Intent(HomeActivity.this, contests.class));
                            break;

                        case R.id.nav_add_post:
                            startActivity(new Intent(HomeActivity.this, activity_post.class));
                            break;

                        case R.id.nav_messages:
                            startActivity(new Intent(HomeActivity.this, FMessagesActivity.class));
                            break;

                        case R.id.nav_products:
                            startActivity(new Intent(HomeActivity.this, ProductsActivity.class));
                            break;

                        case R.id.searchButton:
                            selected = new SearchFragment();
                            break;

                        case R.id.notifButton:
                            selected = new NotificationFragment();
                            break;
                    }
                    if (selected != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
                    }

                    return true;
                }
            };
}




