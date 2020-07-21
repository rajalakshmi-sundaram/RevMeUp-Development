package in.revmeup.revmeup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AdvActivity extends AppCompatActivity {
    Button ch, up;
    ImageView img;
    public Uri imguri;
    StorageReference mStorageReference;
    EditText imgDesc;
    String imageDescription;
    Button go;
    String prodName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);
        Intent intent = getIntent();
        prodName = intent.getStringExtra("product_name");
        mStorageReference=FirebaseStorage.getInstance().getReference("Images");

        ch = (Button) findViewById(R.id.ch);
        up = (Button) findViewById(R.id.button6);
        img = (ImageView) findViewById(R.id.imageView3);
        imgDesc = (EditText)findViewById(R.id.imgDescr);

        go = (Button)findViewById(R.id.button7);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Filechooser();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Fileuploader();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ch.setVisibility(View.GONE);
                up.setVisibility(View.GONE);
                go.setVisibility(View.GONE);
                //FragmentTransaction transaction=getFragmentManager().beginTransaction();
                FragmentManager fragmentManager = getSupportFragmentManager();
                NewFragment fragment=new NewFragment();
                Bundle bundle=new Bundle();
                bundle.putString("product_name",prodName);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();
            }
        });
    }

    private String getExtension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private  void Fileuploader()
    {
        StorageReference Ref=mStorageReference.child(System.currentTimeMillis()+"."+getExtension(imguri));
        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        DatabaseReference imagestore= FirebaseDatabase.getInstance().getReference().child("Advertisement");
                        imageDescription = imgDesc.getText().toString();
                        Advertisement advertisement=new Advertisement(prodName,String.valueOf(imguri),imageDescription);

                        Toast.makeText(AdvActivity.this,"Image uploaded Successfully",Toast.LENGTH_LONG).show();
                        imagestore.child(prodName).setValue(advertisement).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AdvActivity.this,"Image uploaded Successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void Filechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }
}