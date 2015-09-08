package com.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

        /* Instantiates the Singleton class. */
        Singleton.getInstance();
        Singleton.setContext(this);
        Singleton.setFragmentManager(fragmentManager);

        isDrawerLocked = false;

        setupDrawer();
        setDrawerItemClickListener();

        /* Saves the current fragment on device rotation. */
        if (savedInstanceState == null) {
            /* Navigates to the home page. */
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
                /* Opens and closes the navigation drawer on the home button click. */
                if (drawerLayout != null && !isDrawerLocked) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }

                break;

            case R.id.action_report_bug:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"navneet@tds.net"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bug Report - AHA Android App");

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException ae1) {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                    playStoreIntent.setData(
                            Uri.parse("market://details?id=com.google.android.gm"));

                    try {
                        startActivity(playStoreIntent);
                    } catch (ActivityNotFoundException ae2) {
                        new AlertDialog.Builder(Singleton.getContext())
                                .setCancelable(true)
                                .setIcon(android.R.drawable.stat_sys_warning)
                                .setTitle("No Email Client Found!")
                                .setMessage("Please download an email client to send email.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                }

                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        }
    }

    /**
     * Sets up the navigation drawer. Checks if the device is a tablet and locks the navigation
     * drawer to open if the device is a tablet.
     */
    private void setupDrawer() {
        String[] drawerItems = getResources().getStringArray(R.array.drawer_items);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, drawerItems));

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);

        /* Checks if the device is a tablet and locks the
        navigation drawer to open if it the device is a tablet. */
        if(((ViewGroup.MarginLayoutParams)frameLayout.getLayoutParams()).leftMargin ==
                (int)getResources().getDimension(R.dimen.drawer_size)) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            isDrawerLocked = true;
        }

        /* Sets the home button to open the navigation drawer. */
        if (getActionBar() != null) {
            if (!isDrawerLocked) {
                getActionBar().setHomeButtonEnabled(true);
            }

            getActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * Listens to the navigation drawer item clicks and directs the user to their destination.
     */
    private void setDrawerItemClickListener() {
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isDrawerLocked) {
                    drawerLayout.closeDrawers();
                }

                final int HOME = 0;
                final int UPCOMING_EVENTS = 1;
                final int MAKE_A_DONATION = 2;
                final int ABOUT_US = 3;
                final int CONTACT_US = 4;

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

                    default:
                        break;
                }
            }
        });
    }

    /**
     * The current fragment is replaced by the given fragment
     * if the given fragment is not the same fragment as the current fragment.
     *
     * @param newFragment An instance of the new fragment.
     */
    private void replaceFragment(Fragment newFragment) {
        Fragment container = fragmentManager.findFragmentById(R.id.fragment_container);

        boolean isVisible = false;

        /* Checks if the new fragment is the same fragment as
        the fragment that is currently visible. */
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