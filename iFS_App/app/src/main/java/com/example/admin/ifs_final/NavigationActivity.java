package com.example.admin.ifs_final;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;*/

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NavigationActivity extends AppCompatActivity

     implements CustomNavigationView.NavigationItemSelectedListner {
        ArrayList<String> menuList;
        ArrayAdapter<String> menuAdapter;
        CustomNavigationView navView;
        DrawerLayout drawerLayout;
        android.support.v4.app.FragmentTransaction ft;


    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;


        private NetworkTask networkTask = new NetworkTask();


        @SuppressLint("InvalidWakeLockTag")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            navView = (CustomNavigationView) findViewById(R.id.navView);
            drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
            FirstFragment firstFragment = new FirstFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.mainContent, firstFragment).commit();
            prepareListItems();
            menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList);
            navView.setAdapter(menuAdapter);
            navView.setHeaderView(getHeader(),20);
            navView.setOnNavigationItemSelectedListner(this);
            navView.setScrollState(CustomNavigationView.MENU_ITEM_SCROLLABLE);
            //navView.setSelectionBackGround(getResources().getColor(R.color.colorAccent));

            drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    drawerLayout.getChildAt(0).setTranslationX(slideOffset * drawerView.getWidth());
                    drawerLayout.bringChildToFront(drawerView);
                    drawerLayout.requestLayout();

                    }

                @Override
                public void onDrawerOpened(View drawerView) {

                }

                @Override
                public void onDrawerClosed(View drawerView) {

                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });

            FirebaseMessaging.getInstance().subscribeToTopic("firetopic").addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    String msg = "구독실패";

                    // 주제구독 실패
                    if (!task.isSuccessful()) {
                        Log.d("구독", "주제구독 되지않았습니다.");
                        Log.d("구독",msg);
                        Toast.makeText(NavigationActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                    // 주제구독 성공
                    else {
                        Log.d("구독", "주제구독 되었습니다.");
                    }

                }

            });


            //추가한 라인
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                return;
                            }
                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            // Log and toast
                            Log.d("토큰", token);

                            try {
                                Map<String, String> params = new HashMap<>();

                                params.put("tokentest","tokentest");
                                params.put("token", token);

                                String msg = networkTask.execute(params).get();
                                Log.d("토큰",msg);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            //화면깨우기
            powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "WAKELOCK");

            wakeLock.acquire(); // WakeLock 깨우기
            wakeLock.release(); // WakeLock 해제
        }


        private void prepareListItems() {
            menuList = new ArrayList<>();
            menuList.add("Main");
            menuList.add("정보통신관 1층");
            menuList.add("정보통신관 2층");
        }

        private View getHeader() {
            View view = getLayoutInflater().inflate(R.layout.header, null);
            TextView button = (TextView) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("header", "buttonClicked");
                }
            });
            return view;
        }

        @Override
        public void onItemSelected(View view, int position) {
            switch (position) {
                case 0:
                    FirstFragment firstFragment=new FirstFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainContent, firstFragment).commit();
                    break;
                case 1:
                    SecondFragment secondFragment=new SecondFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainContent, secondFragment).commit();
                    break;
                case 2:
                    ThirdFragment thirdFragment=new ThirdFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainContent, thirdFragment).commit();
                    break;
            }
            drawerLayout.closeDrawers();
        }

        /*public class NetworkTask2 extends AsyncTask<Void, Void, String> {

            private String url;
            private ContentValues values;

            public NetworkTask2(String url, ContentValues values){
                this.url = url;
                this.values = values;
            }

            @Override
            protected String doInBackground(Void... params){

                String result;
                RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
                result = requestHttpURLConnection.request(url,values);

                return result;
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

                tv_outPut.setText(s);
            }
        }*/
    }