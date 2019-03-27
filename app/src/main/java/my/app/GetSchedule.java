package my.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class GetSchedule extends AppCompatActivity {

    private ArrayList<HorraireArret> horraires = new ArrayList<>();
    ProgressBar progressBar;

    private class DownloadFilesTask extends AsyncTask<String, Integer, ArrayList<HorraireArret>> {

        // On a besoin du contexte pour replacer l'AsyncTask
        private Context context;
        // On récupère l'activité d'appel, au cas où besoin dans le traitement
        private Activity activity;

        public DownloadFilesTask(Activity activity) {
            this.context = activity.getApplicationContext();
            this.activity = activity;
        }
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
        // protected Boolean doInBackground(Void... arg0) {
        @Override
        protected ArrayList<HorraireArret> doInBackground(String... strings) {
            return callWebService();
        }

        protected void onProgressUpdate(Integer... progress) {
            /*
            CursorLoader cl = new CursorLoader(this.context);
            cl.
            */
        }

        // protected void onPostExecute(final Boolean success)
        @Override
        protected void onPostExecute(ArrayList<HorraireArret> horraires) {
            super.onPostExecute(horraires);

            /*** Affichage des horraires
            TextView textView = findViewById(R.id.horrairesDisplay);
            int size;
            size = GetSchedule.this.lenght();
            if (size > 0) {
                textView.setText(GetSchedule.this.horraires.get(0).getStopId());
            } else {
                textView.setText("erreur, taille horraires: "+ GetSchedule.this.lenght());
            }
            Log.d("POSTEXECUTE", "OK");

            ***/

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_schedule);
        progressBar = findViewById(R.id.progressBar);

        DownloadFilesTask dft;
        dft = new DownloadFilesTask(GetSchedule.this);
        dft.execute();
    }

    private ArrayList<HorraireArret> callWebService() {

        String url = "https://data.metromobilite.fr/api/routers/default/index/stops/SEM:2254/stoptimes";
        RequestQueue queue = Volley.newRequestQueue(GetSchedule.this);

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
        return this.horraires;
    }

    private ArrayList<HorraireArret> deserializeData(String json) {


        JSONArray jArray = null;
        try {
            jArray = new JSONArray(json);
            JSONObject jObject = jArray.getJSONObject(0);

            JSONArray jTimes = new JSONArray(jObject.getString("times"));
            int lenghtArray;
            lenghtArray = jTimes.length();
            JSONObject jTime;
            for (int i = 0; i < jTimes.length(); i++) {
                jTime = jTimes.getJSONObject(i);
                this.horraires.add(new HorraireArret(jTime.getString("stopId"),
                        jTime.getString("stopName"),
                        jTime.getInt("scheduledArrival"),
                        jTime.getInt("scheduledDeparture"),
                        jTime.getInt("realtimeArrival"),
                        jTime.getInt("realtimeDeparture"),
                        jTime.getInt("arrivalDelay"),
                        jTime.getInt("departureDelay"),
                        jTime.getBoolean("timepoint"),
                        jTime.getBoolean("realtime"))
                );

            }

            /*** Affichage des horraires ***/
            TextView textView1 = findViewById(R.id.arretDisplay);
            TextView textView2 = findViewById(R.id.horrairesDisplay);
            int size;
            size = GetSchedule.this.lenght();
            if (size > 0) {
                textView1.setText("prochain départ " +GetSchedule.this.horraires.get(0).getStopName());
                textView2.setText(convert(GetSchedule.this.horraires.get(0).getDepartReel()));
            } else {
                textView2.setText("erreur, taille horraires: "+ GetSchedule.this.lenght());
            }
            progressBar.setVisibility(View.GONE);


        } catch(JSONException e){
            e.printStackTrace();
        }
        return this.horraires;
    }

    public int lenght() {return this.horraires.size();}

    public String convert(int secondes) {
        String s;
        int heu, tmp, min, sec;
        heu = secondes / 3600;
        tmp = secondes % 3600;
        min = tmp / 60;
        sec = tmp % 60;
        s = intToString(heu) + ":" + intToString(min) + ":" + intToString(sec);
        return s;
    }

    public String intToString(int i){
        if( i < 10){
            return "0" + i;

        } else {
            return "" + i;
        }
    }

    public HorraireArret getHorraire(int index) {
        return this.horraires.get(index);
    }

}
