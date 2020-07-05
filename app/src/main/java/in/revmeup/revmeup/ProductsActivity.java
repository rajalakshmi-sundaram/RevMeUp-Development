package in.revmeup.revmeup;

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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsActivity extends AppCompatActivity {
FloatingActionButton create;
    String name;
    String TAG = ProductsActivity.class.getSimpleName();
    ListView lv;
    ArrayList<HashMap<String,String>> productList;
    HashMap<String,String> product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        productList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetProducts().execute();
                create = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, UploadActivity.class));
            }
        });
    }
    public class GetProducts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String json = null;
            try {
                InputStream is = getAssets().open("json.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer,StandardCharsets.UTF_8);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Log.e(TAG, "Response : " + json);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray products = jsonObj.getJSONArray("products");
                    JSONObject c= products.getJSONObject(0);
                    name=c.getString("product_name");

                    product.put("product_name",name);
                    productList.add(product);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(ProductsActivity.this,name,Toast.LENGTH_SHORT).show();
            ListAdapter adapter = new SimpleAdapter(ProductsActivity.this, productList,
                    R.layout.list_item, new String[]{"name"},
                    new int[]{R.id.name});
            lv.setAdapter(adapter);

        }
    }
}