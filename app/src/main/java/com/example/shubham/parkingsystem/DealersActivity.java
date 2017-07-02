package com.example.shubham.parkingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import static android.R.attr.data;

public class DealersActivity extends AppCompatActivity {

    private ListView listView;
    private DealerAdapter adapter;
    private Button picButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap=null;
    private String parsedVehicleno=null;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String uid=null;
    private int charges=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealers);
        listView=(ListView)findViewById(R.id.dealer_list);
        Dealer t=new Dealer(15.0,"Rahul Somani","969682500");
        Dealer k=new Dealer(11.0,"Archit Goyal","969682500");
        List<Dealer> transactions=new ArrayList<Dealer>();
        transactions.add(t);
        transactions.add(k);
        adapter=new DealerAdapter(this,transactions);
        listView.setAdapter(adapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference();

        picButton=(Button)findViewById(R.id.pic_button);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            if(imageBitmap!=null){
                parsedVehicleno=getVehicleNoFromImage(imageBitmap);
                if(parsedVehicleno!=null){
                    mDatabaseReference.child("Vehicles").child(parsedVehicleno).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            uid=(String)dataSnapshot.getValue();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                    if(uid!=null){
                        mDatabaseReference.child("Users").child(uid).child("timestart").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Date temp=(Date)dataSnapshot.getValue();
                                if(temp.getYear()==-1){
                                    mDatabaseReference.child("Users").child(uid).child("timestart").setValue(Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00")).getTime());
                                }
                                else{
                                    Date current=Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00")).getTime();
                                    mDatabaseReference.child("Dealers").child(mFirebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            charges=(int)dataSnapshot.child("charges").getValue();
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {}
                                    });
                                    int price=calculatePrice(temp,current,charges);
                                    mDatabaseReference.child("Payable").child(uid).child(mFirebaseAuth.getCurrentUser().getUid()).setValue(price);
                                    mDatabaseReference.child("Owes").child(mFirebaseAuth.getCurrentUser().getUid()).child(uid).setValue(price);
                                    mDatabaseReference.child("Users").child(uid).child("timestart").setValue(new Date(0,0,0));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {}
                        });
                    }
                }
                uid=null;
                charges=0;
                parsedVehicleno=null;
                imageBitmap=null;
            }
            else{
                Toast.makeText(this,"Please take the picture again!!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //  vehicle number that mudit will give
    public String getVehicleNoFromImage(Bitmap image){
        String number="";
        return number;
    }

    // function to calculate price
    public int calculatePrice(Date prev,Date current,int charges){
        return 0;
    }
}
