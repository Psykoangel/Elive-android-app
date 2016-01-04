package fr.elive.android.app.task;

import android.os.AsyncTask;

import fr.elive.android.app.fragment.dmuFragment;
import fr.elive.android.app.model.User;
import fr.elive.android.app.wrapper.WebServiceWrapper;

/**
 * Created by Psyko on 03/01/2016.
 */
public class WebServiceTask extends AsyncTask<Integer, Void, User> {

    private WebServiceWrapper webServiceWrapper;

    private dmuFragment fragment;

    public WebServiceTask(dmuFragment frag){
        webServiceWrapper = new WebServiceWrapper();
        fragment = frag;
    }

    @Override
    protected User doInBackground(Integer... params) {

        if (!(params.length > 0)) return null;

        Integer id = params[0];

        User u = webServiceWrapper.getUserInfos(id);

        //User u = new User(String.valueOf(params[0]), "Welhaisley");

        if (u != null && u.validate())
            return u;

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        if (user != null) {
            fragment.setUser(user);
            fragment.afficherListeTweets();
        } else {
            fragment.displayNoDataFound();
        }
    }
}
