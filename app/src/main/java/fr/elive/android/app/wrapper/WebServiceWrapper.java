package fr.elive.android.app.wrapper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.elive.android.app.model.CmaObject;
import fr.elive.android.app.model.RelationShip;
import fr.elive.android.app.model.User;

/**
 * Created by Psyko on 30/12/2015.
 */
public class WebServiceWrapper {

    private final String ComputerIP = "192.168.1.26";
    private final String baseUrlPath = "http://" + ComputerIP + ":8080/elive/REST/";
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
        gson = gsonBuilder.create();
    }

    private InputStream sendRequest(URL url) {

        // Ouverture de la connexion
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Connexion à l'URL
        try {
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return urlConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserInfos(int patientId) {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            User u = new User("Hervé", "MATYSIAK");
            RelationShip relationShip1 = new RelationShip("Charles", "MATYSIAK", 11);
            RelationShip relationShip2 = new RelationShip("Carmen", "MATYSIAK", 10);
            ArrayList relationShipList = new ArrayList<>();
            relationShipList.add(relationShip1);
            relationShipList.add(relationShip2);
            CmaObject cmaObject1 = new CmaObject("A022", "A02.2", "2", "INFECT. LOC. A SALMONELLA");
            CmaObject cmaObject2 = new CmaObject("A046", "A04.6", "2", "ENTERITE A YERSINIA ENTEROCOLITICA");
            CmaObject cmaObject3 = new CmaObject("A022", "A02.2", "2", "INFECT. LOC. A SALMONELLA");
            CmaObject cmaObject4 = new CmaObject("A046", "A04.6", "2", "ENTERITE A YERSINIA ENTEROCOLITICA");
            List<CmaObject> cmaObjectList = new ArrayList<>();
            cmaObjectList.add(cmaObject1);
            cmaObjectList.add(cmaObject2);
            cmaObjectList.add(cmaObject3);
            cmaObjectList.add(cmaObject4);
            u.setUserCmaList(cmaObjectList);
            u.setRelationshipList(relationShipList);
            return u;
/*
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(baseUrlPath + urlUserPath + patientId));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                return gson.fromJson(reader, new TypeToken<User>(){}.getType());
            }
*/
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }
        return null;


    }
}
