package fr.elive.android.app.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.Vector;

import fr.elive.android.app.R;
import fr.elive.android.app.adapter.MyPagerAdapter;
import fr.elive.android.app.fragment.dmuFragment;
import fr.elive.android.app.fragment.nfcFragment;

public class FragmentsActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager);

        // Création de la liste de FragmentsActivity que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des FragmentsActivity dans la liste
        fragments.add(Fragment.instantiate(this, nfcFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, dmuFragment.class.getName()));

        // Création de l'adapter qui s'occupera de l'affichage de la liste de FragmentsActivity
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);


    }
}