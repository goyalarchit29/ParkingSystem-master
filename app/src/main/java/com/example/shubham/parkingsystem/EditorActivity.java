package com.example.shubham.parkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditorActivity extends AppCompatActivity {

    private EditText mEditVehicle;
  //  private ListView mVehicleList;
    private Button mAddvehicle;
    private FirebaseAuth auth;
    private DatabaseReference mUserDatabaseReference;
    // private ArrayAdapter<String> mVehicleListAdapter;
    private ArrayList<String> mListItems=new ArrayList<String>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        auth=FirebaseAuth.getInstance();
        mUserDatabaseReference=FirebaseDatabase.getInstance().getReference();

        mEditVehicle=(EditText)findViewById(R.id.edit_vehicle_no);
       // mVehicleList=(ListView)findViewById(R.id.vehicle_list);
        mAddvehicle=(Button)findViewById(R.id.add_vehicle);
       // mVehicleListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mListItems);
       // mVehicleList.setAdapter(mVehicleListAdapter);
        mAddvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item=mEditVehicle.getText().toString().trim();
                if(item!=null && item.length()>0) {
                    FirebaseUser currentUser=auth.getCurrentUser();
                    mUserDatabaseReference.child("Vehicles").child(item).setValue(currentUser.getUid());
                    mListItems.add(item);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mListItems);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView

                int x=viewHolder.getAdapterPosition();
                mUserDatabaseReference.child("Vehicles").child(mListItems.get(x)).removeValue();

                mListItems.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
