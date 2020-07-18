package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ListView productNameList;
    FloatingActionButton create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        final ArrayList<String> productList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reff;
        productNameList = (ListView) findViewById(R.id.productListView);

        reff = database.getReference();

//
        reff.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Product product1 = singleSnapshot.getValue(Product.class);
                    productList.add(product1.getProduct_name());
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Toast.makeText(ProductsActivity.this, productList.get(0), Toast.LENGTH_LONG).show();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productList);
        // Set The Adapter
        productNameList.setAdapter(arrayAdapter);
        arrayAdapter.getFilter();
                create = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, UploadActivity.class));
            }
        });
    }
 }

