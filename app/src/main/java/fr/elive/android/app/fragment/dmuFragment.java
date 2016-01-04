package fr.elive.android.app.fragment;
/**
 * Created by chriis on 03/01/2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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



    private List<Tweet> genererTweets(){
        List<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet( "Prénom", getUser().getUserForname()));
        tweets.add(new Tweet( "Nom", getUser().getUserName()));
        tweets.add(new Tweet( "Relation Prénom", getUser().getRelationshipList().get(0).getEntourageForname()));
        tweets.add(new Tweet( "Relation Nom", getUser().getRelationshipList().get(0).getEntourageName()));
        tweets.add(new Tweet( "Relation Prénom", getUser().getRelationshipList().get(1).getEntourageForname()));
        tweets.add(new Tweet( "Relation Nom", getUser().getRelationshipList().get(1).getEntourageName()));
        tweets.add(new Tweet( "Maladie", getUser().getUserCmaList().get(0).getCmaValue()));
        tweets.add(new Tweet( "Maladie", getUser().getUserCmaList().get(1).getCmaValue()));
        tweets.add(new Tweet( "Maladie", getUser().getUserCmaList().get(2).getCmaValue()));
        tweets.add(new Tweet( "Maladie", getUser().getUserCmaList().get(3).getCmaValue()));
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
}