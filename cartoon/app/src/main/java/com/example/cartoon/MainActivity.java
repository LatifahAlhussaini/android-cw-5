package com.example.cartoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Cartoon> CartoonLaibrary = new ArrayList<>() ;

        Cartoon A = new Cartoon("My Daddy Long Legs",8.2,1,40,1990,R.drawable.mydaddylonglegs);
        Cartoon B = new Cartoon("UFO Robot Goldrake",7,1,74,1975,R.drawable.ufo);
        Cartoon C = new Cartoon("Popeye the Sailor",7.1,25,220,1960,R.drawable.popeyethesailor);
        Cartoon D = new Cartoon("Bumpety Boo",6.2,1,130,1985,R.drawable.bumpetyboo);
        Cartoon E = new Cartoon("Sangokushi",8.9,1,47,1971,R.drawable.sangokushi);
        Cartoon F = new Cartoon("Adnan and Lina",8.7,1,26,1978,R.drawable.adnanandlina);


        CartoonLaibrary.add(A);
        CartoonLaibrary.add(B);
        CartoonLaibrary.add(C);
        CartoonLaibrary.add(D);
        CartoonLaibrary.add(E);
        CartoonLaibrary.add(F);

        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView CartoonList = findViewById(R.id.RV);

        final LinearLayout LL = findViewById(R.id.LinearLayout);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(R.drawable.ic_baseline_home_24);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("profile").withIcon(R.drawable.ic_baseline_person_24);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Settings").withIcon(R.drawable.ic_baseline_settings_24);

        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(4).withName("About");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return true;
                    }
                })
                .build();



        CartoonList.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        CartoonList.setLayoutManager(lm);

        CartoonAdapter CA = new CartoonAdapter(LL, CartoonLaibrary,MainActivity.this);
        CartoonList.setAdapter(CA);


    }
}