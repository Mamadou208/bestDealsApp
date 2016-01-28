package com.example.tb_laota.BestDeals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tb_laota.BestDeals.adapter.Adapter;
import com.example.tb_laota.BestDeals.app.AppConfig;
import com.example.tb_laota.BestDeals.app.AppController;
import com.example.tb_laota.BestDeals.helper.RecognitionListenerImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public static final int SCAN_QR_CODE_INTENT = 5;

    private ProgressDialog dialog;
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private SearchView search;
    private Adapter adapter;
    private SpeechRecognizer recognizer;
    private RecognitionListenerImpl listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        adapter = new Adapter(this, array);
        listView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        search = (SearchView) findViewById(R.id.searchView1);
        search.setQueryHint("SearchView");

        //Creat volley request obj
        JsonArrayRequest jsonArrayRequest = createProductRequest();
        AppController.getApplication().addToRequesQueue(jsonArrayRequest);

        ImageButton qrButton = (ImageButton) findViewById(R.id.scanQrCodeButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //start the scanning activity from the com.google.zxing.client.android.SCAN intent
                    Intent intent = new Intent(ACTION_SCAN);
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, SCAN_QR_CODE_INTENT);
                } catch (ActivityNotFoundException anfe) {
                    //on catch, show the download dialog
                    showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
                }
            }
        });

        listener = new RecognitionListenerImpl(new RecognitionListenerImpl.Consumer<String>() {
            @Override
            public void apply(String s) {
                String voiceResult = String.format("/voice/%s", s);
                JsonArrayRequest productRequest = createProductRequest(voiceResult);
                AppController.getApplication().addToRequesQueue(productRequest);
            }
        });
        recognizer = SpeechRecognizer.createSpeechRecognizer(MainActivity.this.getApplicationContext());
        recognizer.setRecognitionListener(listener);

        ImageButton speechRecognition = (ImageButton) findViewById(R.id.speechRecognition);
        speechRecognition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.domain.app");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "speak now...");

                recognizer.startListening(intent);
            }
        });
    }

    private AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {
                    Log.i("Anfe", anfe.getMessage(), anfe);
                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == SCAN_QR_CODE_INTENT) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String barcodeUrl = String.format("/barcode/%s", contents);
                AppController.getApplication().addToRequesQueue(createProductRequest(barcodeUrl));
            }
        }
    }

    private JsonArrayRequest createProductRequest() {
        return createProductRequest("");
    }

    @NonNull
    private JsonArrayRequest createProductRequest(String urlContext) {
        return new JsonArrayRequest(AppConfig.URL_PRODUCTS + urlContext, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        try {
                            item.setCreatedAt(obj.getString("createdAt"));
                            item.setTitle(obj.getString("name"));
                            item.setImage(obj.getString("image"));
                            item.setDescription(obj.getString("description"));
                            item.setRate(((Number) obj.get("price")).doubleValue());
                            array.add(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }
}
