package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsActivity extends AppCompatActivity {
    TextView pname;
    FloatingActionButton create;
    String name;
    String json;
    String TAG = ProductsActivity.class.getSimpleName();
    ListView lv;
    ArrayList<HashMap<String,String>> productList;
    HashMap<String,String> product;

    final FirebaseDatabase database = FirebaseDatabase.getInstance("https://console.firebase.google.com/u/1/project/revmeup-ecb05/database/revmeup-ecb05/data/~2F");
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       reff=database.getReference();
        Query productQuery = reff              .child(getString(R.string.dbname_products));
//
        similarproductQuery3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Product product1 = singleSnapshot.getValue(Product.class);
                    GlobalData.productsMap.put(singleSnapshot.getValue(Product.class).getProduct_id(), singleSnapshot.getValue(Product.class));
                    similarProducts.add(singleSnapshot.getValue(Product.class));
                    Log.d(TAG, "checkMap3 :" + singleSnapshot);
//                                       product_name.add(singleSnapshot.getValue(Product.class).getProductName().toString());
                    String price = "N/A";
        setContentView(R.layout.activity_products);
                create = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, UploadActivity.class));
            }
        });
    }
 }
