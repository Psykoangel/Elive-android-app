package fr.elive.android.app.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;

import java.util.List;
import java.util.Vector;

import fr.elive.android.app.R;
import fr.elive.android.app.adapter.MyPagerAdapter;
import fr.elive.android.app.fragment.dmuFragment;
import fr.elive.android.app.fragment.nfcFragment;
import fr.elive.android.app.task.WebServiceTask;
import fr.elive.android.app.wrapper.NfcWrapper;

public class FragmentsActivity extends FragmentActivity {

    private NfcWrapper nfcWrapper;

    private dmuFragment dmuFrag;
    private nfcFragment nfcFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager);

        // Création de la liste de FragmentsActivity que fera défiler le PagerAdapter
        List<Fragment> fragments = new Vector<Fragment>();

        dmuFrag = new dmuFragment();
        nfcFrag = new nfcFragment();

        // Ajout des FragmentsActivity dans la liste
        fragments.add(dmuFrag);
        fragments.add(nfcFrag);

        // Création de l'adapter qui s'occupera de l'affichage de la liste de FragmentsActivity
        PagerAdapter mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(mPagerAdapter);

        Pair<Boolean, String> result = nfcWrapper.Instance().setNfcAdapter(this);

        if (result != null && !result.first) nfcFrag.showAlert(nfcFrag.getActivity(), "Error", result.second);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcWrapper.Instance().desactivateForegroundIntentCatching(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcWrapper.Instance().activateForegroundIntentCatching(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String userId = nfcWrapper.Instance().handleTagIntent(intent);
        Integer id = Integer.parseInt(userId);
        new WebServiceTask(dmuFrag).execute(id);

    }
}