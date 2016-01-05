package fr.elive.android.app.fragment;
/**
 * Created by chriis on 03/01/2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.elive.android.app.R;
import fr.elive.android.app.adapter.TweetAdapter;
import fr.elive.android.app.model.Tweet;
import fr.elive.android.app.model.User;

public class dmuFragment extends Fragment {
    ListView listView;
    public View v;
    private User user;
    public Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dmufragment, container, false);
        context = container.getContext();
        listView = (ListView) v.findViewById(R.id.listViewDmu);

        return v;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        menu.add("bite mole");
        menu.add("grosse bite");
        super.onCreateOptionsMenu(menu, inflater);
    }



    private List<Tweet> genererTweets(){
        List<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet( "Prénom", getUser().getUserForname()));
        tweets.add(new Tweet( "Nom", getUser().getUserName()));

        if (!getUser().getRelationshipList().isEmpty() ) {

            tweets.add(new Tweet( getUser().getRelationshipList().get(0).getRelationshipTypeCode() + " Prénom", getUser().getRelationshipList().get(0).getEntourageForname()));
            tweets.add(new Tweet( getUser().getRelationshipList().get(0).getRelationshipTypeCode() + " Nom", getUser().getRelationshipList().get(0).getEntourageName()));
            tweets.add(new Tweet( getUser().getRelationshipList().get(1).getRelationshipTypeCode() + " Prénom", getUser().getRelationshipList().get(1).getEntourageForname()));
            tweets.add(new Tweet( getUser().getRelationshipList().get(1).getRelationshipTypeCode() + " Nom", getUser().getRelationshipList().get(1).getEntourageName()));
        }

        if (!getUser().getUserCmaList().isEmpty()) {
            for (int i = 0; i < getUser().getUserCmaList().size(); i++) {
                tweets.add(new Tweet( "Maladie", getUser().getUserCmaList().get(i).getCmaValue()));
            }
        }

        return tweets;
    }

    public void afficherListeTweets(){

        List<Tweet> tweets = genererTweets();

        TweetAdapter adapter = new TweetAdapter(context, tweets);
        if (listView == null || adapter == null) return;
        listView.setAdapter(adapter);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void showAlert(Context context, String title, String msg) {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
        alertbox.setTitle(title);
        alertbox.setMessage(msg);
        alertbox.show();

    }

    public void displayNoDataFound() {
        showAlert(v.getContext(), "Info", "No user found in Database.");
    }
}