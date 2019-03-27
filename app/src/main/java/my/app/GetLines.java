package my.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import data.HorraireArret;
import data.Ligne;
import data.RecyclerViewAdapter;

public class GetLines extends AppCompatActivity {

    private class DownloadFilesTask extends AsyncTask<String, Integer, ArrayList<Ligne>> {

        // On a besoin du contexte pour replacer l'AsyncTask
        private Context context;
        // On récupère l'activité d'appel, au cas où besoin dans le traitement
        private Activity activity;

        public DownloadFilesTask(Activity activity) {
            this.context = activity.getApplicationContext();
            this.activity = activity;
        }
        protected void onPreExecute() {
            super.onPreExecute();

        }
        // protected Boolean doInBackground(Void... arg0) {
        @Override
        protected ArrayList<Ligne> doInBackground(String... strings) {
            return callWebService();
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        // protected void onPostExecute(final Boolean success)
        @Override
        protected void onPostExecute(ArrayList<Ligne> lignes) {
            super.onPostExecute(lignes);
        }

    }


    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<Ligne> lignes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lines);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mAdapter = new RecyclerViewAdapter(lignes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        DownloadFilesTask dft;
        dft = new DownloadFilesTask(GetLines.this);
        dft.execute();

    }


    private ArrayList<Ligne> callWebService() {

        String url = "https://data.metromobilite.fr/api/routers/default/index/routes?reseaux=TRAM";
        RequestQueue queue = Volley.newRequestQueue(GetLines.this);

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        deserializeData(response);
                        Log.d("CALLWEBSERVICE", "onResponse");


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CALLWEBSERVICE", "onError");
                    }
                }
        );
        queue.add(request);
        return this.lignes;
    }

    private void deserializeData(String response) {

        JSONArray jArray = null;
        try {
            jArray = new JSONArray(response);
            JSONObject ligne;
            for(int i = 0; i < jArray.length(); i++){
                ligne =  jArray.getJSONObject(i);
                this.lignes.add(new Ligne(ligne.getString("id"),
                        ligne.getString("gtfsId"),
                        ligne.getString("shortName"),
                        ligne.getString("longName"),
                        ligne.getString("color"),
                        ligne.getString("textColor"),
                        ligne.getString("mode"),
                        ligne.getString("type")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
