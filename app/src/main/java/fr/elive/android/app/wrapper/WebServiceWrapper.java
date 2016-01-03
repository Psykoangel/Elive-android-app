package fr.elive.android.app.wrapper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.elive.android.app.model.CmaObject;
import fr.elive.android.app.model.RelationShip;
import fr.elive.android.app.model.User;

/**
 * Created by Psyko on 30/12/2015.
 */
public class WebServiceWrapper {

    private final String baseUrlPath = "http://localhost:8080/elive/REST/";
    private final String urlUserPath = "USER/";

    public Gson gson;

    class RelationShipInstanceCreator implements InstanceCreator<RelationShip> {
        public RelationShip createInstance(Type type)
        {
            return new RelationShip();
        }
    }

    class CmaObjectInstanceCreator implements InstanceCreator<CmaObject> {
        public CmaObject createInstance(Type type)
        {
            return new CmaObject();
        }
    }

    public WebServiceWrapper() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RelationShip.class, new RelationShipInstanceCreator());
        gsonBuilder.registerTypeAdapter(CmaObject.class, new CmaObjectInstanceCreator());
        Gson gson = gsonBuilder.create();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    public User getUserInfos(int patientId) {

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(baseUrlPath + urlUserPath + patientId));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                return gson.fromJson(reader, new TypeToken<User>(){}.getType());
            }

        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }
        return null;
    }
}
