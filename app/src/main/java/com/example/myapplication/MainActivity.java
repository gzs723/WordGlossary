package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.fragment.NewsFragment;
import com.example.myapplication.fragment.TranslateFragment;
import com.example.myapplication.fragment.UserFragment;
import com.example.myapplication.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer)
    DrawerLayout mdrawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    //当前界面
    MenuItem currentMenuItem;
    Fragment currentFragment;
    int nevigationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {

            nevigationId = SharePreferenceUtil.getNevigationItem(this);
            if (nevigationId != -1) {
                currentMenuItem = navigationView.getMenu().findItem(nevigationId);
            }
            if (currentMenuItem == null) {
                currentMenuItem = navigationView.getMenu().findItem(R.id.translate_fragment);
            }

            if (currentMenuItem != null) {
                currentMenuItem.setCheckable(true);
                Fragment fragment = getFragmentById(currentMenuItem.getItemId());

                if (fragment != null) {

                    //当没有sp的时候第一次加载
                    switchFragment(fragment);
                }


            }

        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (currentMenuItem != item || currentMenuItem == null) {
                    //之前可以选，选中之后，不可选，读取完再选
//                    currentMenuItem.setCheckable(false);
                    int id = item.getItemId();
                    SharePreferenceUtil.putNevigationItem(getApplication(), id);
                    currentMenuItem = item;
                    currentMenuItem.setCheckable(true);
                    switchFragment(getFragmentById(id));

                }

                mdrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    private Fragment getFragmentById(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.translate_fragment:
                fragment=new TranslateFragment();
                break;
            case R.id.login_fragment:
                fragment = new UserFragment();
                break;
            case R.id.news_fragment:
                fragment = new NewsFragment();
                break;
            default:
                fragment=new TranslateFragment();
                break;
        }
        return fragment;
    }

    private void switchFragment(Fragment fragment) {

        if (currentFragment == null || !currentFragment.getClass().getName().equals(fragment.getClass().getName())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        currentFragment = fragment;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
