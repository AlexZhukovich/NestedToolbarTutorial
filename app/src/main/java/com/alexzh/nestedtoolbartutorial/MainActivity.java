package com.alexzh.nestedtoolbartutorial;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private final static String SEARCH = "search";

    private ListFragment mListFragment;
    private Toolbar mNestedToolbar, mGeneralToolbar;
    private SearchView mSearchView;
    private DrawerLayout mDrawerLayout;

    private String searchRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mListFragment = new ListFragment();
        mNestedToolbar = (Toolbar) findViewById(R.id.nestedToolbar);
        mGeneralToolbar = (Toolbar) findViewById(R.id.generalToolbar);
        mGeneralToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        mGeneralToolbar.setNavigationOnClickListener(this);
        if (getSupportFragmentManager().findFragmentByTag(ListFragment.DETAIL) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, mListFragment)
                    .commit();
        }
        setSupportActionBar(mNestedToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        ((NavigationView)findViewById(R.id.navigation)).setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);
        if (searchRequest != null)
            mSearchView.setQuery(searchRequest, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText != null && !newText.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (String value : Arrays.asList(getResources().getStringArray(R.array.countries))) {
                if (value.contains(newText)) {
                    list.add(value);
                }
            }
            mListFragment.setList(list);
            return true;
        } else {
            mListFragment.setList(Arrays.asList(getResources().getStringArray(R.array.countries)));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH, mSearchView.getQuery().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        searchRequest = savedInstanceState.getString(SEARCH);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent webSiteIntent = new Intent(Intent.ACTION_VIEW);
        switch (menuItem.getItemId()) {
            case R.id.item_more_articles:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_more_articles)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_github:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_to_github)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_follow_google_plus:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_follow_me_google_plus)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_follow_twitter:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_follow_me_google_twitter)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_follow_linkedin:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_follow_me_google_linked_in)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_join_facebook:
                webSiteIntent.setData(Uri.parse(getString(R.string.link_join_to_facebook_community)));
                startActivity(webSiteIntent);
                mDrawerLayout.closeDrawers();
                return true;
        }
        return false;
    }
}
