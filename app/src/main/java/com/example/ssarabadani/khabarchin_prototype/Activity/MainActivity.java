package com.example.ssarabadani.khabarchin_prototype.Activity;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.ssarabadani.khabarchin_prototype.Fragments.LoginFragment;
import com.example.ssarabadani.khabarchin_prototype.Fragments.MainPageFragment;
import com.example.ssarabadani.khabarchin_prototype.Fragments.NewsCategoryFragment;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.example.ssarabadani.khabarchin_prototype.client.Handler;
import com.example.ssarabadani.khabarchin_prototype.client.QueueInstance;
import com.example.ssarabadani.khabarchin_prototype.client.UserAPI;
import com.example.ssarabadani.khabarchin_prototype.dto.AcceptedData;
import com.example.ssarabadani.khabarchin_prototype.dto.AcceptedResultData;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    MainPageFragment mainFragment = new MainPageFragment();
    NewsCategoryFragment newsCategoryFragment = new NewsCategoryFragment();
    LoginFragment loginFragment = new LoginFragment();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QueueInstance.prepare(this);
        setContentView(R.layout.activity_main);

//        // spring android sample
//        RestTemplate template = new RestTemplate();
//        String isAcceptedUrl = "http://phoneservice.fanavard.com/Service.svc/IsAccepted";
//        AcceptedData data = new AcceptedData();
//        data.Username = "soroosh";
//        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//        template.getMessageConverters().add(gsonHttpMessageConverter);
//        AcceptedResultData resultData = template.postForObject(isAcceptedUrl, data, AcceptedResultData.class);
//        System.out.println(resultData);
//
//        //end of spring android sample

        UserAPI userAPI = new UserAPI();
        userAPI.isUsernameAccepted("soroosh", new Handler() {
            @Override
            public void handle() {
                System.out.println("handled");
            }

        }, new Handler() {
            @Override
            public void handle() {
                System.out.println("Error");
            }
        });


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, newsCategoryFragment, "news_category").addToBackStack("news_category").commit();
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(getResources().getColor(R.color.notification_bar_red));

        }
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);


                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.drawer_home:
                        fragmentManager.beginTransaction().replace(R.id.main_frame, mainFragment, "main").commit();
                        break;
                    case R.id.drawer_news:
                        fragmentManager.beginTransaction().replace(R.id.main_frame, newsCategoryFragment, "news_category").addToBackStack("news_category").commit();
                        break;
//                    case R.id.drawer_favorite:
//                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.drawer_login:
//                        fragmentManager.beginTransaction().replace(R.id.main_frame, loginFragment, "news_category").commit();
//                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.drawer_about:
                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.khabar_chin_title, R.string.khabar_chin_title) {

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);

            }


        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }


    @Override
    public void onBackPressed() {


        Fragment fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName());

        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            if (!(fragment instanceof NewsCategoryFragment)) {
                fragmentManager.popBackStack();
            } else {
                finish();
            }
        }

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
