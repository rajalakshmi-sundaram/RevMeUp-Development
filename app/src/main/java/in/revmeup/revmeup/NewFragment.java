package in.revmeup.revmeup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment {
    TextView productName;
    ImageView productImage;
    TextView productDescription;
    public Uri imguri;
    String prodName;
    final ArrayList<String> details = new ArrayList<>();
    Bitmap mIcon_val;
    String desc="description";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewFragment newInstance(String param1, String param2) {
        NewFragment fragment = new NewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /*public void imageSetter()
    {
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            imguri=data.getData();
            productImage.setImageURI(imguri);
        }
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new, container, false);
        Bundle bundle=getArguments();
        prodName = String.valueOf(bundle.getString("product_name"));
        productImage = (ImageView) v.findViewById(R.id.imageView4);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reff;
        productName =  v.findViewById(R.id.textView14);
        productDescription = v.findViewById(R.id.textView15);
        productName.setText(prodName);
        reff = database.getReference();
        reff.child("Advertisement").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Advertisement advertisement = singleSnapshot.getValue(Advertisement.class);

                    if (advertisement.getProductName() == prodName) {

                        imguri = Uri.parse(advertisement.getProductImageUrl());
                        productImage.setImageURI(imguri);
                        productDescription.setText(advertisement.getProductDescription());
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        return v;
    }
}