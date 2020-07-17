package in.revmeup.revmeup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment {
    TextView productName;
    ImageView productImage;
    TextView productDescription;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_new, container, false);

       // productImage = (ImageView) v.findViewById(R.id.imageView4);
        //productDescription = (TextView) v.findViewById(R.id.textView15);
       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference reff;

        productName =  v.findViewById(R.id.textView14);
        productDescription = v.findViewById(R.id.textView15);
        reff = database.getReference();
        reff.child("Advertisement").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Advertisement advertisement = singleSnapshot.getValue(Advertisement.class);
                    productName.setText(advertisement.getProductName());
                    URL newurl=null;
                    try {
                        newurl=new URL(advertisement.getProductImageUrl());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    /*URL newurl = null;
                    try {
                        newurl = new URL(advertisement.getProductImageUrl());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    try {
                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productImage.setImageBitmap(mIcon_val);*/

                    desc=advertisement.getProductDescription();
                    productDescription.setText(desc);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       return v;
    }
}
