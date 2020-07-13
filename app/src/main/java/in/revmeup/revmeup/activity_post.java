package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class activity_post extends AppCompatActivity {
    ListView productNameList;
    SearchView searchView;
    Button next, cancel;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        final ArrayList<String> productList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reff;
        productNameList = (ListView) findViewById(R.id.productListView);
        searchView = (SearchView) findViewById(R.id.searchView);

            reff = database.getReference();

//
            reff.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Product product1 = singleSnapshot.getValue(Product.class);
                        productList.add(product1.getProduct_name());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
         arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productList);
        // Set The Adapter

        productNameList.setAdapter(arrayAdapter);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if (productList.contains(query)) {
                        arrayAdapter.getFilter().filter(query);
                    } else {
                        Toast.makeText(activity_post.this, "No Match found", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            next = (Button) findViewById(R.id.button3);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(activity_post.this, AdvActivity.class));
                }
            });
            cancel = (Button) findViewById(R.id.button2);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(activity_post.this, HomeActivity.class));
                }
            });
        }
}