package com.example.docbaov1613;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.docbaov1613.ViewModels.ArticleViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeaturedFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                articleViewModel.searchArticles(query);
                Toast.makeText(MainActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Nếu từ khóa tìm kiếm rỗng, tải lại tất cả các bài viết

                    reloadCurrentFragment();
                } else {
                    // Nếu có từ khóa tìm kiếm, tìm kiếm bài viết
                    articleViewModel.searchArticles(newText);
                }
                return false;
            }
        });

        return true;
    }
    private void reloadCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof FeaturedFragment) {
            ((FeaturedFragment) currentFragment).reloadArticles();
        } else if (currentFragment instanceof LatestFragment) {
            ((LatestFragment) currentFragment).reloadArticles();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.nav_featured) {
                        selectedFragment = new FeaturedFragment();
                    } else if (item.getItemId() == R.id.nav_latest) {
                        selectedFragment = new LatestFragment();
                    } else if (item.getItemId() == R.id.nav_library) {
                        selectedFragment = new LibraryFragment();
                    } else if (item.getItemId() == R.id.nav_account) {
                        selectedFragment = new AccountFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  selectedFragment).commit();
                    } else if (item.getItemId() == R.id.nav_notifications) {
                        selectedFragment = new NotificationFragment();
                    }

                    if (selectedFragment != null) {
                        // Xóa kết quả tìm kiếm khi chuyển đổi fragment
                        articleViewModel.clearSearchResults();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                    } else {
                        return false;
                    }
                }
            };

}