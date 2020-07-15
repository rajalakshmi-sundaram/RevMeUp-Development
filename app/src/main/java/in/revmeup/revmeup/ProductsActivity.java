package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsActivity extends AppCompatActivity {
    TextView pname;
    FloatingActionButton create;
    ArrayList<String> productList;
    HashMap<String,String> product;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //reff=database.getReference("asus1");
        Query productQuery = database.getInstance().getReference("asus1");

//
        productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Product product1 = dataSnapshot.getValue(Product.class);
                        pname.setText(product1.getProduct_name());
                    //}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

                create = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, UploadActivity.class));
            }
        });
    }
 }

