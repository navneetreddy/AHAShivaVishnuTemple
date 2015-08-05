package com.example.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

    FragmentManager fragmentManager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
    private String[] drawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
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
                final int PUJA_SERVICES = 2;
                final int MAKE_A_DONATION = 3;
                final int BALA_VIHAR_CLASSES = 4;
                final int EVENT_GALLERY = 5;
                final int HINDU_FESTIVALS = 6;
                final int ANNUAL_PUJA_CALENDAR = 7;
                final int TEMPLE_NEWS = 8;
                final int CORPORATE_SPONSORS = 9;
                final int AHA_FINANCIALS = 10;
                final int AHA_NEWSLETTER = 11;
                final int TEMPLE_MANAGEMENT = 12;
                final int CONTACT_US = 13;
                final int GALLERY = 14;

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

                    case PUJA_SERVICES:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new PujaServicesFragment())
                                .addToBackStack("PujaServicesFragment")
                                .commit();
                        break;

                    case MAKE_A_DONATION:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new MakeADonationFragment())
                                .addToBackStack("MakeADonationFragment")
                                .commit();
                        break;

                    case BALA_VIHAR_CLASSES:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new BalaViharClassFragment())
                                .addToBackStack("BalaViharClassFragment")
                                .commit();
                        break;

                    case EVENT_GALLERY:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new EventGalleryFragment())
                                .addToBackStack("EventGalleryFragment")
                                .commit();
                        break;

                    case HINDU_FESTIVALS:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new HinduFestivalsFragment())
                                .addToBackStack("HinduFestivalsFragment")
                                .commit();
                        break;

                    case ANNUAL_PUJA_CALENDAR:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new AnnualPujaCalendarFragment())
                                .addToBackStack("AnnualPujaCalendarFragment")
                                .commit();
                        break;

                    case TEMPLE_NEWS:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new TempleNewsFragment())
                                .addToBackStack("TempleNewsFragment")
                                .commit();
                        break;

                    case CORPORATE_SPONSORS:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new CorporateSponsorsFragment())
                                .addToBackStack("CorporateSponsorsFragment")
                                .commit();
                        break;

                    case AHA_FINANCIALS:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new AHAFinancialsFragment())
                                .addToBackStack("AHAFinancialsFragment")
                                .commit();
                        break;

                    case AHA_NEWSLETTER:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new AHANewsletterFragment())
                                .addToBackStack("AHANewsletterFragment")
                                .commit();
                        break;

                    case TEMPLE_MANAGEMENT:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new TempleManagementFragment())
                                .addToBackStack("TempleManagementFragment")
                                .commit();
                        break;

                    case CONTACT_US:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new ContactUsFragment())
                                .addToBackStack("ContactUsFragment")
                                .commit();
                        break;

                    case GALLERY:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new GalleryFragment())
                                .addToBackStack("GalleryFragment")
                                .commit();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public void setupDrawer() {
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(false);

        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.om_symbol_1,
                R.string.app_name,  /* "open drawer" description */
                R.string.app_name  /* "close drawer" description */
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerItems = getResources().getStringArray(R.array.drawerItems);

        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, drawerItems));
    }
}