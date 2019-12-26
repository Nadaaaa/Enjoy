package com.upbeatdigital.enjoy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.upbeatdigital.enjoy.ui.fragments.HomeFragment;
import com.upbeatdigital.enjoy.R;
import com.upbeatdigital.enjoy.api.responsemodels.ResponseGetHome;
import com.upbeatdigital.enjoy.api.apiutils.Retrofit;
import com.upbeatdigital.enjoy.databinding.ActivityMainBinding;
import com.upbeatdigital.enjoy.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Disposable disposable;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setHeaderData();
        binding.toolbarTitle.setText("Home");
        binding.toolbarNotificationNumber.setText("20");

        setUpNavigationView();
        loadHomeFragment();
    }

    private void setHeaderData() {
        View navigationHeader = binding.navigationView.getHeaderView(0);
        ImageView image = navigationHeader.findViewById(R.id.header_image);
        TextView name = navigationHeader.findViewById(R.id.header_name);
        TextView email = navigationHeader.findViewById(R.id.header_email);

        Glide.with(this).load("https://homepages.cae.wisc.edu/~ece533/images/monarch.png").into(image);
        name.setText("Nada");
        email.setText("nadabadr95@gmail.com");
    }


    private void loadHomeFragment() {
        Fragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frameLayout_main, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        binding.drawerLayout.closeDrawers();
    }

    private void setUpNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    loadHomeFragment();
                    break;
                /*case R.id.nav_myOrders:

                    break;
                case R.id.nav_artists:

                    break;
                case R.id.nav_works:

                    break;
                case R.id.nav_serviceRequest:

                    break;
                case R.id.nav_contactUs:

                    break;
                case R.id.nav_settings:

                    break;
                case R.id.nav_aboutApp:
                    break;*/
                default:
                    loadHomeFragment();
                    binding.drawerLayout.closeDrawers();
            }
            return true;
        });

        binding.toolbarMenu.setOnClickListener(view -> {
            if(!binding.drawerLayout.isDrawerOpen(Gravity.START)) binding.drawerLayout.openDrawer(Gravity.START);
            else binding.drawerLayout.closeDrawer(Gravity.END);
        });

/*        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));

        binding.drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();*/
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}