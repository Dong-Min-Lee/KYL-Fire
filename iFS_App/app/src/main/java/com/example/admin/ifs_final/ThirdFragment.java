package com.example.admin.ifs_final;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ThirdFragment extends Fragment {
    private static final String TAG = "ThirdFragment";

    private DatabaseReference mPostReference;

    TextView text_ID;
    TextView text_Name;

    ImageView room_1, room_2, room_3, room_4, stairs_1, stairs_2, Hallway_1, Hallway_2, Hallway_3,
            escape1,escape2,escape_no1,escape_no2,line1,line2,line3,line1_1,line2_2,line3_3;

    String fire;
    String fired;
    String sort = "fire";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_activity_content, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.navIcon);
        final NavigationActivity navigationActivity = (NavigationActivity) getActivity();

        room_1 = (ImageView) view.findViewById(R.id.room_1);
        room_2 = (ImageView) view.findViewById(R.id.room_2);
        room_3 = (ImageView) view.findViewById(R.id.room_3);
        room_4 = (ImageView) view.findViewById(R.id.room_4);
        stairs_1 = (ImageView) view.findViewById(R.id.stairs_1);
        stairs_2 = (ImageView) view.findViewById(R.id.stairs_2);
        Hallway_1 = (ImageView) view.findViewById(R.id.Hallway_1);
        Hallway_2= (ImageView) view.findViewById(R.id.Hallway_2);
        Hallway_3= (ImageView) view.findViewById(R.id.Hallway_3);
        escape1= (ImageView) view.findViewById(R.id.escape1);
        escape2= (ImageView) view.findViewById(R.id.escape2);
        escape_no1= (ImageView) view.findViewById(R.id.escape_no1);
        escape_no2= (ImageView) view.findViewById(R.id.escape_no2);
        line1= (ImageView) view.findViewById(R.id.line1);
        line2= (ImageView) view.findViewById(R.id.line2);
        line3= (ImageView) view.findViewById(R.id.line3);
        line1_1= (ImageView) view.findViewById(R.id.line1_1);
        line2_2= (ImageView) view.findViewById(R.id.line2_2);
        line3_3= (ImageView) view.findViewById(R.id.line3_3);

        getFirebaseDatabase();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.drawerLayout.openDrawer(navigationActivity.navView, true);
            }
        });
        return view;
    }


    public void postFirebaseDatabase(boolean add){
        mPostReference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebasePost post = new FirebasePost(fire, fired);
            postValues = post.toMap();
        }
        childUpdates.put("/icom2f/" + fire, postValues);
        mPostReference.updateChildren(childUpdates);
    }

    public void getFirebaseDatabase(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FirebasePost get = postSnapshot.getValue(FirebasePost.class);
                    String[] info = {get.fired,get.fire};
                     //String Result = setTextLength(info[0],10) + setTextLength(info[1],10);

                     String loca = info[0];
                     String fdfire = info[1];
                    String fire = "1";

                    //방 1호
                    if(fdfire.equals(fire) && loca.equals("SR1")){
                        room_1.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line2_2.setVisibility(View.VISIBLE);
                        line3_3.setVisibility(View.VISIBLE);

                    }
                    else if(fdfire.equals("0") && loca.equals("SR1")){
                        room_1.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line3_3.setVisibility(View.INVISIBLE);
                        line2_2.setVisibility(View.INVISIBLE);

                    }

                    //방 2호
                    if(fdfire.equals(fire) && loca.equals("SR2")){
                        room_2.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line2_2.setVisibility(View.VISIBLE);
                        line3_3.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR2")) {
                        room_2.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line3_3.setVisibility(View.INVISIBLE);
                        line2_2.setVisibility(View.INVISIBLE);
                    }

                    //방 3호
                    if(fdfire.equals(fire) && loca.equals("SR3")){
                        room_3.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR3")) {
                        room_3.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                    }

                    //방 4호
                    if(fdfire.equals(fire) && loca.equals("SR4")){
                        room_4.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR4")) {
                        room_4.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                    }

                    //좌측 복도
                    if(fdfire.equals(fire) && loca.equals("SR5")){
                        Hallway_1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        escape_no1.setVisibility(View.VISIBLE);
                        line2_2.setVisibility(View.VISIBLE);
                        line3_3.setVisibility(View.VISIBLE);

                    }
                    else if(fdfire.equals("0") && loca.equals("SR5")) {
                        Hallway_1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        escape_no1.setVisibility(View.INVISIBLE);
                        line3_3.setVisibility(View.INVISIBLE);
                        line2_2.setVisibility(View.INVISIBLE);
                    }
                    //중앙 복도
                    if(fdfire.equals(fire) && loca.equals("SR6")){
                        Hallway_2.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line3_3.setVisibility(View.VISIBLE);

                    }
                    else if(fdfire.equals("0") && loca.equals("SR6")) {
                        Hallway_2.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line3_3.setVisibility(View.INVISIBLE);
                    }
                    //우측 복도
                    if(fdfire.equals(fire) && loca.equals("SR7")){
                        Hallway_3.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        escape_no2.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR7")) {
                        Hallway_3.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        escape_no2.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                    }

                    //좌측 계단
                    if(fdfire.equals(fire) && loca.equals("SR8")){
                        stairs_1.setVisibility(View.VISIBLE);
                        escape2.setVisibility(View.VISIBLE);
                        line1_1.setVisibility(View.VISIBLE);
                        line2_2.setVisibility(View.VISIBLE);
                        line3_3.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR8")) {
                        stairs_1.setVisibility(View.INVISIBLE);
                        escape2.setVisibility(View.INVISIBLE);
                        line1_1.setVisibility(View.INVISIBLE);
                        line2_2.setVisibility(View.INVISIBLE);
                        line3_3.setVisibility(View.INVISIBLE);
                    }
                    //우측 계단
                    if(fdfire.equals(fire) && loca.equals("SR9")){
                        stairs_2.setVisibility(View.VISIBLE);
                        escape1.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.VISIBLE);
                        line3.setVisibility(View.VISIBLE);
                    }
                    else if(fdfire.equals("0") && loca.equals("SR9")) {
                        stairs_2.setVisibility(View.INVISIBLE);
                        escape1.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        line3.setVisibility(View.INVISIBLE);
                    }
                    //567 좌중우 복도
                    //8좌 9우 계단

                    Log.d("getFirebaseDatabase1", "key: " + key);
                    Log.d("getFirebaseDatabase1", "info: " + info[1]);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("icom2f").orderByChild(sort);
        sortbyAge.addValueEventListener(postListener);
    }

    public String setTextLength(String text, int length){
        if(text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }

}
