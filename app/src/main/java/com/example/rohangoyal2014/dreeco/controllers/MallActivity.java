package com.example.rohangoyal2014.dreeco.controllers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.adapters.MallFragmentPagerAdapter;
import com.example.rohangoyal2014.dreeco.utils.FirebaseUserDataModel;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MallActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ACTIVITY_RESULT_REQUEST_CODE = 1;
    public static final String result = "ACTIVITY_RESULT";
    public static Location mLocation = null;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    Button signInButton;
    Button signoutButton;
    TextView drawerNameTV;
    View loggedInDrawerLayout, loggedOutDrawerLayout;

    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_RESULT_REQUEST_CODE) {
            if (data != null) {
                boolean state = data.getBooleanExtra(result, false);
                Log.e("state", String.valueOf(state));
                if (state) {
                    startActivity(new Intent(this, MallActivity.class));
                    finish();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        signInButton = findViewById(R.id.btn_sign_in);
        signoutButton = findViewById(R.id.btn_log_out);
        loggedInDrawerLayout = findViewById(R.id.logged_in_drawer_layout);
        loggedOutDrawerLayout = findViewById(R.id.logged_out_drawer_layout);
        drawerNameTV = findViewById(R.id.drawer_name);

        signInButton.setOnClickListener(this);
        signoutButton.setOnClickListener(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        new Thread() {
            @Override
            public void run() {
                super.run();
                setUpLocation();
            }
        }.start();


        setUpPagerAdapter();

        setSupportActionBar(toolbar);

        FirebaseUser currentUser = ServerUtils.mAuth.getCurrentUser();
        updateUI(currentUser);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.search_bottom:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.map_bottom:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.add_new:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.notifications:
                        viewPager.setCurrentItem(4);
                }
                return true;
            }
        });

        DuoDrawerLayout drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    private void setUpLocation() {

        if (Build.VERSION.SDK_INT < 23) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                getLastLocation();

            }
        } else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //asking for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                getLastLocation();

            }

        }

    }

    private void getLastLocation() throws SecurityException {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null
                        if (location != null) {
                            // Logic to handle location object
//                            Log.e("Location",location.getLatitude()+" "+location.getLongitude());
                            getAddress(location.getLatitude(), location.getLongitude());
                            mLocation = location;
                        }
                    }
                });
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.isEmpty()) {
                return;
            }
            Address obj = addresses.get(0);
            String address = obj.getSubLocality().concat(",").concat(obj.getLocality()).concat("(").concat(obj.getPostalCode()).concat(")");

            toolbar.setTitle("Location");
            toolbar.setSubtitle(address);

//            Log.e("IGA", "Address" + add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setUpPagerAdapter() {
        MallFragmentPagerAdapter adapter = new MallFragmentPagerAdapter(getSupportFragmentManager(), bottomNavigationView);
        viewPager.setAdapter(adapter);
    }

    public void drawerLoggedIn() {
        drawerNameTV.setText("Hello, " + ServerUtils.mFirebaseDataModel.getFirstName());
        loggedOutDrawerLayout.setVisibility(View.GONE);
        loggedInDrawerLayout.setVisibility(View.VISIBLE);
    }

    public void drawerLoggedOut() {
        loggedOutDrawerLayout.setVisibility(View.VISIBLE);
        loggedInDrawerLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mall_action_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_sign_in:
                startActivityForResult(new Intent(this, LoginActivity.class), ACTIVITY_RESULT_REQUEST_CODE);
                break;
            case R.id.btn_log_out:
                ServerUtils.mAuth.signOut();
                ServerUtils.mFirebaseDataModel = null;
                drawerLoggedOut();
                break;
            case R.id.my_orders_drawer_logged_in:
                break;
            case R.id.payments_drawer_logged_in:
                break;
            case R.id.coupons_drawer_logged_in:
            case R.id.coupons_drawer_logged_out:
                break;
            case R.id.invite_drawer_logged_in:
            case R.id.invite_drawer_logged_out:
                String shareBody = "I downloaded Dreeco ,it's great. I recommend you to use it for ordering your food online.<LINK>";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Recommendation");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
                break;
            case R.id.rate_drawer_logged_in:
            case R.id.rate_drawer_logged_out:
                break;
            case R.id.help_drawer_logged_in:
            case R.id.help_drawer_logged_out:
                break;
        }
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            final String email = user.getEmail();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child(ServerUtils.USERS_ROUTE).orderByChild(ServerUtils.USERS_ATTRIBUTE_EMAIL).equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String phone = "", firstName = "", lastName = "";
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        for (DataSnapshot ds2 : ds.getChildren()) {
                            if (ds2.getKey().equals(ServerUtils.USERS_ATTRIBUTE_FIRST_NAME)) {
                                firstName = ds2.getValue().toString();
                            } else if (ds.getKey().equals(ServerUtils.USERS_ATTRIBUTE_LAST_NAME)) {
                                lastName = ds2.getValue().toString();
                            } else if (ds.getKey().equals(ServerUtils.USERS_ATTRIBUTE_PHONE)) {
                                phone = ds.getValue().toString();
                            }
                        }
                    }
                    ServerUtils.mFirebaseDataModel = new FirebaseUserDataModel(firstName, lastName, phone, email);
                    drawerLoggedIn();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    signoutButton.performClick();
                }
            });
        }

    }

}
