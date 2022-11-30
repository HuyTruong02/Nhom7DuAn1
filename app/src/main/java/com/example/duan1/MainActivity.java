package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.duan1.Model.Phong;
import com.example.duan1.fragment.DichVuFragment;
import com.example.duan1.fragment.DoanhThuFragment;
import com.example.duan1.fragment.HoaDonFragment;
import com.example.duan1.fragment.HopDongFragment;
import com.example.duan1.fragment.KhachThueFragment;
import com.example.duan1.fragment.PhongFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    ListView listView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar_);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quản lý phòng");
        // Kết Thúc Xử Lí Toolbar
        // ánh xạ drawer vs navigation
        drawerLayout = findViewById(R.id.Draw_layout);
        navigationView = findViewById(R.id.Draw_nav);
        fragmentManager = getSupportFragmentManager();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();
        fragmentManager.beginTransaction().add(R.id.fragment_main, new PhongFragment()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_phongtro:
                        replaceFragment(new PhongFragment());
                        getSupportActionBar().setTitle("Quản Lý Phòng");
                        break;
                    case R.id.nav_khachthue:
                        replaceFragment(new KhachThueFragment());
                        getSupportActionBar().setTitle("Quản Lý Khách Thuê");
                        break;
                    case R.id.nav_dichvu:
                        replaceFragment(new DichVuFragment());
                        getSupportActionBar().setTitle("Quản Lý Dịch Vụ");
                        break;
                    case R.id.nav_hopdong:
                        replaceFragment(new HopDongFragment());
                        getSupportActionBar().setTitle("Quản Lý Hợp Đồng");
                        break;
                    case R.id.nav_hoadon:
                        replaceFragment(new HoaDonFragment());
                        getSupportActionBar().setTitle("Quản Lý Hóa Đơn");
                        break;
                    case R.id.nav_doanhthu:
                        replaceFragment(new DoanhThuFragment());
                        getSupportActionBar().setTitle("Doanh Thu");
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }

}