package fr.elive.android.app.adapter;

/**
 * Created by chriis on 03/01/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.elive.android.app.R;
import fr.elive.android.app.model.Tweet;

public class TweetAdapter extends ArrayAdapter<Tweet> {

    public TweetAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Tweet tweet = getItem(position);
        viewHolder.titre.setText(tweet.getTitre());
        viewHolder.description.setText(tweet.getDescription());

        return convertView;
    }

    private class TweetViewHolder{
        public TextView titre;
        public TextView description;

    }
}