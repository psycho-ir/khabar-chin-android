package com.example.ssarabadani.khabarchin_prototype.Activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ssarabadani.khabarchin_prototype.Fragments.LoginFragment;
import com.example.ssarabadani.khabarchin_prototype.Fragments.MainPageFragment;
import com.example.ssarabadani.khabarchin_prototype.Fragments.NewsCategoryFragment;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.example.ssarabadani.khabarchin_prototype.client.QueueInstance;


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


        fragmentManager = getSupportFragmentManager();


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
                        fragmentManager.beginTransaction().replace(R.id.main_frame, newsCategoryFragment, "news_category").commit();
                        break;
                    case R.id.drawer_favorite:
                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_login:
                        fragmentManager.beginTransaction().replace(R.id.main_frame, loginFragment, "news_category").commit();
//                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_about:
                        Toast.makeText(getApplicationContext(), "شما روی گزینه " + menuItem.getTitle().toString() + " کلیک کرده اید ", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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
