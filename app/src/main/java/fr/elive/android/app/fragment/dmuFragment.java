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
    public User user;
    public Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dmufragment, container, false);
        context = container.getContext();
        listView = (ListView) v.findViewById(R.id.listViewDmu);

        return v;
    }



    private List<Tweet> genererTweets(){
        List<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet( "Nom", user.getUserName()));
        tweets.add(new Tweet( "Prénom", user.getUserForname()));
        return tweets;
    }

    private void afficherListeTweets(){
        List<Tweet> tweets = genererTweets();

        TweetAdapter adapter = new TweetAdapter(context, tweets);
        listView.setAdapter(adapter);
    }





}