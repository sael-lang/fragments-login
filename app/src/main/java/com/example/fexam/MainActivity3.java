//package com.example.fexam;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.fexam.R;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class RealtimeDBList extends AppCompatActivity {
//
//    private ListView dataListView;
//    private EditText itemText;
//    private Button findButton;
//    private Button deleteButton;
//    private Boolean searchMode = false;
//    private Boolean itemSelected = false;
//    private int selectedPosition = 0;
//
//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference dbRef = database.getReference("todo");
//    ArrayList<String> listItems = new ArrayList<String>();
//    ArrayList<String> listKeys = new ArrayList<String>();
//    ArrayAdapter<String> adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        dataListView = (ListView) findViewById(R.id.dataListView);
//        itemText = (EditText) findViewById(R.id.itemText);
//        deleteButton.setEnabled(false);
//
//        dataListView = (ListView) findViewById(R.id.dataListView);
//        itemText = (EditText) findViewById(R.id.itemText);
//        deleteButton.setEnabled(false);
//
//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_single_choice,
//                listItems);
//        dataListView.setAdapter(adapter);
//        dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//
//        dataListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent,
//                                            View view, int position, long id) {
//                        selectedPosition = position;
//                        itemSelected = true;
//                        deleteButton.setEnabled(true);
//                    }
//                });
//
//        addChildEventListener();
//    }
//    private void addChildEventListener() {
//        ChildEventListener childListener = new ChildEventListener() {
//
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                adapter.add(
//                        (String) dataSnapshot.child("description").getValue());
//
//                listKeys.add(dataSnapshot.getKey());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                String key = dataSnapshot.getKey();
//                int index = listKeys.indexOf(key);
//
//                if (index != -1) {
//                    listItems.remove(index);
//                    listKeys.remove(index);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//
//        };
//        dbRef.addChildEventListener(childListener);
//    }
//    public void addItem(View view) {
//
//        String item = itemText.getText().toString();
//        String key = dbRef.push().getKey();
//
//        itemText.setText("");
//        dbRef.child(key).child("description").setValue(item);
//
//        adapter.notifyDataSetChanged();
//    }
//    public void deleteItem(View view) {
//        dataListView.setItemChecked(selectedPosition, false);
//        dbRef.child(listKeys.get(selectedPosition)).removeValue();
//    }
//}