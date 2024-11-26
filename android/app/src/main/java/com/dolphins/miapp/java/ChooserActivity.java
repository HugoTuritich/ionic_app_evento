/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mlkit.vision.QReveIonic.java;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.mlkit.vision.QReveIonic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/** Demo app chooser which allows you pick from all available testing Activities. */
public final class ChooserActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
  private static final String TAG = "ChooserActivity";
  Button bt_inicial_lectura;
  TextView tv_texto01, tv_texto02, tv_texto03;

  @SuppressWarnings("NewApi") // CameraX is only available on API 21+
  private static final Class<?>[] CLASSES =
          VERSION.SDK_INT < VERSION_CODES.LOLLIPOP
                  ? new Class<?>[] {
                  LivePreviewActivity.class, StillImageActivity.class,
          }
                  : new Class<?>[] {
                  LivePreviewActivity.class,
          };

  private static final int[] DESCRIPTION_IDS =
          VERSION.SDK_INT < VERSION_CODES.LOLLIPOP
                  ? new int[] {
                  R.string.desc_camera_source_activity, R.string.desc_still_image_activity,
          }
                  : new int[] {
                  R.string.desc_camera_source_activity,
          };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    /*
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
              new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
      StrictMode.setVmPolicy(
              new StrictMode.VmPolicy.Builder()
                      .detectLeakedSqlLiteObjects()
                      .detectLeakedClosableObjects()
                      .penaltyLog()
                      .build());
    }

    */
    super.onCreate(savedInstanceState);
    Constant.volver = true;
    Log.d(TAG, "4");

    traerDatos();






//    RequestQueue rQ = Volley.newRequestQueue(getApplicationContext());

    setContentView(R.layout.activity_chooser);

    tv_texto01 = (TextView) findViewById(R.id.tv_texto01);
    tv_texto02 = (TextView) findViewById(R.id.tv_texto02);
    tv_texto03 = (TextView) findViewById(R.id.tv_texto03);

    // tv_texto01.setText("XXXXX JM");

    bt_inicial_lectura = (Button) findViewById(R.id.bt_inicial_lectura);
    bt_inicial_lectura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), LivePreviewActivity.class);
        startActivity(i);
      }
    });

    // Set up ListView and Adapter
    ListView listView = findViewById(R.id.test_activity_list_view);

    MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
    adapter.setDescriptionIds(DESCRIPTION_IDS);

    listView.setAdapter(adapter);
    listView.setOnItemClickListener(this);
  }

  private void traerDatos() {
    RequestQueue rQ = Volley.newRequestQueue(getApplicationContext());

//     String url = "https://olimpia.redambar.com.py/api3/ver_evento2.php";
//   String url = "https://soldeamerica.redambar.com.py/api3/ver_evento2.php";
    String url = "https://spvoluque.redambar.com.py/api3/ver_evento2.php";

    StringRequest stringRequest = new
            StringRequest(Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {

                        Log.i("MIOj", response);
                        String json = response;

                        Log.i("MIOj2", "response= " + response);
                        JSONObject jb = null;

                        try {
                          try {
                            jb = new JSONObject(json);
                          } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                          }

                          if (response.contains("mensaje")) {
                            Log.i("MIO", "Encontré mensaje");
                            // 27/12/2023 11:33 hs jm
                            // le invertí el comentario //

                            // lay_datos.setVisibility(View.GONE);
                            // lay_no_datos.setVisibility(View.VISIBLE);

                            // jm lay_datos.setVisibility(View.VISIBLE);
                            // lay_no_datos.setVisibility(View.GONE);
                            Log.i("MIOj3", "response: " + jb.toString());
                            JSONArray jevento = jb.getJSONArray("evento");
                            // Log.i("MIOj5", "string de jvento: " +
                            //         jevento.get(0)["evento_codigo"]);
                            // Log.i("MIOj5", "eventocodigo: " + jevento.get(0).{"eventocodigo"});
                            tv_texto01.setText(jb.getString("nom_evento"));
                            tv_texto02.setText(jb.getString("nom_local"));
                            tv_texto03.setText(jb.getString("fecha"));

                            // tv_texto01.setText(jevento.getString(0).indexOf(0));
                            // JSONArray jm = jb.getJSONArray(String.valueOf(jevento));
                            // Log.i("MIOj6", jm.getString("estado"));
                            {
                               // tv_texto01.setText(jevento.get(0).toString());
                               //
                               // tv_texto01.setText(jevento.getString(0).indexOf("mensaje"));
                            }
                          } else {
                            // lay_datos.setVisibility(View.VISIBLE);
                            // lay_no_datos.setVisibility(View.GONE);

                            JSONArray jdata = new JSONArray(jb.getString("invitados_acreditados"));
                            Log.i("MIOj5", jdata.length() + " totales desconexiones");

                            if (jdata.length() > 0) {

                              for (int i = 0; i < jdata.length(); i++) {

                                // jm acre_inv = new Acre_Inv();

                                JSONObject ju = jdata.getJSONObject(i);
                                Log.i("MIOj5", "i: " + i);

                                /* jm
                                acre_inv.setAcre_inv(ju.getString("acre_inv"));
                                acre_inv.setNom_inv(ju.getString("nom_inv"));
                                acre_inv.setNom_acre(ju.getString("nom_acre"));
                                acre_inv.setDes_ven(ju.getString("des_ven"));
                                acre_inv.setAutorizado(ju.getString("autorizado"));
                                acre_inv.setEstado(ju.getInt("estado"));

                                if (acre_inv.getAcre_inv().equals("ACOMPAÑANTE")) {
                                  tv_acreditado_de.setVisibility(View.VISIBLE);
                                  tv_nom_inv.setVisibility(View.VISIBLE);
                                  tv_nom_acre.setVisibility(View.VISIBLE);
                                } else {
                                  tv_acreditado_de.setVisibility(View.GONE);
                                  tv_nom_inv.setVisibility(View.GONE);
                                  tv_nom_acre.setVisibility(View.VISIBLE);
                                }

                                tv_acre_inv.setText(acre_inv.getAcre_inv());
                                tv_nom_inv.setText(acre_inv.getNom_inv());
                                tv_nom_acre.setText(acre_inv.getNom_acre());
                                tv_des_ven.setText(acre_inv.getDes_ven());
                                tv_autorizado.setText(acre_inv.getAutorizado());


                                 */

                                // jm Log.i("MIO", "ya ingresado " +  acre_inv.getAutorizado());

                                // jm if (acre_inv.getAutorizado().contains("QR YA INGRESO")) {
                                // jm   acre_inv.setEstado(0);
                                // jm } else {
                                // jm }
/*
                                if (acre_inv.getEstado()==0) {
                                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    imagen.setImageDrawable(getResources().getDrawable(R.drawable.false_transp, getApplicationContext().getTheme()));
                                  } else {
                                    imagen.setImageDrawable(getResources().getDrawable(R.drawable.false_transp));
                                  }


                                } else {
                                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    imagen.setImageDrawable(getResources().getDrawable(R.drawable.checj_transp, getApplicationContext().getTheme()));
                                  } else {
                                    imagen.setImageDrawable(getResources().getDrawable(R.drawable.checj_transp));
                                  }
                                                    /*
                                                    timer.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Log.i("MIO", "Holaaaa");
                                                            Intent i = new Intent(getApplicationContext(), LivePreviewActivity.class);
                                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(i);
                                                        }
                                                    }, 1750);

                                                     */

                                }

                              }


                            } // else {
                              //
                            // }

                         //  }

                        } catch (JSONException e) {
                          Log.i("MIOj6", "Error " + e.getMessage());
                          e.printStackTrace();
                        }
                      }
                    }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.i("MIO", "en onErrorResponse " + error.getMessage());
                // Log.i("MIO", error.getCause().toString());

              }
            })
                    // parametros para el post //
            {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                return parameters;
              }
            };
    rQ.add(stringRequest);

  }


  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Class<?> clicked = CLASSES[position];
    startActivity(new Intent(this, clicked));
  }

  private static class MyArrayAdapter extends ArrayAdapter<Class<?>> {

    private final Context context;
    private final Class<?>[] classes;
    private int[] descriptionIds;

    MyArrayAdapter(Context context, int resource, Class<?>[] objects) {
      super(context, resource, objects);

      this.context = context;
      classes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;

      if (convertView == null) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(android.R.layout.simple_list_item_2, null);
      }

      ((TextView) view.findViewById(android.R.id.text1)).setText(classes[position].getSimpleName());
      ((TextView) view.findViewById(android.R.id.text2)).setText(descriptionIds[position]);

      return view;
    }

    void setDescriptionIds(int[] descriptionIds) {
      this.descriptionIds = descriptionIds;
    }
  }

  @Override
  public void onBackPressed() {
    // super.onBackPressed();
    Intent a = new Intent(Intent.ACTION_MAIN);
    a.addCategory(Intent.CATEGORY_HOME);
    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(a);

    // Toast.makeText(this, "NO", Toast.LENGTH_LONG).show();

  }






}



/*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.

    switch (item.getItemId()) {

      case R.id.action_elegir:
        Log.i("MIO", "Voy a mis pedidos");
        Intent i = new Intent(getApplicationContext(), ElegirEvento.class);
        startActivity(i);
        break;

    }

    return super.onOptionsItemSelected(item);
  }
 */


