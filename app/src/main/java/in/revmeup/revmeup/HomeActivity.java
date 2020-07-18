package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import in.revmeup.revmeup.ui.main.contests;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selected = null;

    RecyclerView postRecyclerView ;
    PostAdapter postAdapter ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference ;
    List<Advertisement> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        postRecyclerView  = findViewById(R.id.postRV);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        postRecyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Advertisement");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postList = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    Advertisement post = postsnap.getValue(Advertisement.class);
                    postList.add(post) ;



                }

                postAdapter = new PostAdapter(HomeActivity.this,postList);
                postRecyclerView.setAdapter(postAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                            //startActivity(new Intent(HomeActivity.this, contests.class));
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
                    }

                    return true;
                }
            };
}


