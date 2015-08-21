package com.example.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Main Activity of the application.
 *
 * @author Navneet Reddy
 */
public class MainActivity extends Activity {

    private FragmentManager fragmentManager;

    private DrawerLayout drawerLayout;
    private ListView drawerList;

    private boolean isDrawerLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        Singleton.getInstance();
        Singleton.setFragmentManager(fragmentManager);

        isDrawerLocked = false;

        setupDrawer();
        setDrawerItemClickListener();

        if (savedInstanceState == null) {
            // Go to the home page.
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new HomePageFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                if (drawerLayout != null && !isDrawerLocked) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }

                break;

            case R.id.settings:
                return true;

            case R.id.action_settings:
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() {
        String[] drawerItems = getResources().getStringArray(R.array.drawer_items);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, drawerItems));

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);

        if(((ViewGroup.MarginLayoutParams)frameLayout.getLayoutParams()).leftMargin ==
                (int)getResources().getDimension(R.dimen.drawer_size)) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            isDrawerLocked = true;
        }

        if (getActionBar() != null) {
            if (!isDrawerLocked)
                getActionBar().setHomeButtonEnabled(true);

            getActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setDrawerItemClickListener() {
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isDrawerLocked)
                    drawerLayout.closeDrawers();

                final int HOME = 0;
                final int ABOUT_US = 1;
                final int UPCOMING_EVENTS = 2;
                final int MAKE_A_DONATION = 3;
                final int CONTACT_US = 4;
                final int DEVELOPER = 5;

                switch (position) {
                    case HOME:
                        replaceFragment(new HomePageFragment());
                        break;

                    case ABOUT_US:
                        replaceFragment(new AboutUsFragment());
                        break;

                    case UPCOMING_EVENTS:
                        replaceFragment(new UpcomingEventsFragment());
                        break;

                    case MAKE_A_DONATION:
                        replaceFragment(new MakeADonationFragment());
                        break;

                    case CONTACT_US:
                        replaceFragment(new ContactUsFragment());
                        break;

                    case DEVELOPER:
                        replaceFragment(new AboutDeveloperFragment());

                        Toast.makeText(getApplicationContext(), "NAVNEET'S FRAGMENT!",
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    private void replaceFragment(Fragment newFragment) {
        Fragment container = fragmentManager.findFragmentById(R.id.fragment_container);

        boolean isVisible = false;

        if (newFragment.getClass().toString().equals(container.getClass().toString())) {
            isVisible = true;
        }

        if (!isVisible) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}