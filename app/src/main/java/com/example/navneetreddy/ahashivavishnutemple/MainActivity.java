package com.example.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Main Activity of the application.
 */
public class MainActivity extends Activity {

    FragmentManager fragmentManager;

    private DrawerLayout drawerLayout;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        Singleton.getInstance();
        Singleton.setFragmentManager(fragmentManager);

        setupDrawer();
        setDrawerItemClickListener();

        // Go to the home page.
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new HomePageFragment())
                .addToBackStack("HomePageFragment")
                .commit();
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
            case R.id.settings:
                return true;

            case R.id.action_settings:
                return true;

            case android.R.id.home:
                if (drawerLayout != null) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setDrawerItemClickListener() {
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();

                final int HOME = 0;
                final int ABOUT_US = 1;
                final int UPCOMING_EVENTS = 2;
                final int MAKE_A_DONATION = 3;
                final int CONTACT_US = 4;
                final int DEVELOPER = 5;

                switch (position) {
                    case HOME:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new HomePageFragment())
                                .addToBackStack("HomePageFragment")
                                .commit();
                        break;

                    case ABOUT_US:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new AboutUsFragment())
                                .addToBackStack("AboutUsFragment")
                                .commit();
                        break;

                    case UPCOMING_EVENTS:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new UpcomingEventsFragment())
                                .addToBackStack("UpcomingEventsFragment")
                                .commit();
                        break;

                    case MAKE_A_DONATION:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new MakeADonationFragment())
                                .addToBackStack("MakeADonationFragment")
                                .commit();
                        break;

                    case CONTACT_US:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new ContactUsFragment())
                                .addToBackStack("ContactUsFragment")
                                .commit();
                        break;

                    case DEVELOPER:
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.fragment_container, new GalleryFragment())
//                                .addToBackStack("GalleryFragment")
//                                .commit();
                        Toast.makeText(getApplicationContext(), "NAVNEET'S FRAGMENT!",
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public void setupDrawer() {
        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(false);
        }

        String[] drawerItems = getResources().getStringArray(R.array.drawer_items);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, drawerItems));
    }
}