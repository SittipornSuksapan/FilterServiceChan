package com.chan.revernue.filterapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chan.revernue.filterapplication.transaction.dao.RealmMember;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import io.realm.RealmConfiguration;


public class LoginActivity extends AppCompatActivity {

    private String urlLogin = "http://devtest.boripatp.com/chan01/api/login.php";
//    private String urlAllList = "http://devtest.boripatp.com/chan01/api/selectListAll.php";

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private String email, password;
    private ImageView imgLogo;

    Realm realm;


    String id, member_password,member_fistname,member_lastname,member_email,member_tel;

    //Ok http3
    private static final OkHttpClient client = new OkHttpClient();
    static String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);

        Realm.init(this);
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name(Realm.DEFAULT_REALM_NAME)
                        .deleteRealmIfMigrationNeeded()
                        .build();
                realm = Realm.getInstance(config);
        
        

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =edtEmail.getText().toString();
                password = edtPassword.getText().toString();
                if (email.equals("") || password.equals("") ){
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.email),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (email.length() <= 1 || password.length() <= 1) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.password_id), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    LoginMember(urlLogin, email, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                RealmResults<RealmMember> member_result = realm.where(RealmMember.class).findAll();
                member_result.deleteAllFromRealm();
                realm.commitTransaction();
            }
        });

    }

    void LoginMember(String urlLogin, String email, String password ) throws IOException{
        FormBody.Builder formbuilder = new FormBody.Builder();
        formbuilder.add("member_email", email);
        formbuilder.add("member_password", password);

        MediaType.parse("application/json; charset=utf-8");
        RequestBody formRequestBody = formbuilder.build();
        Request request = new Request.Builder()
                .url(urlLogin)
                .post(formRequestBody)
                .build();
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("Result login user", "Fail");


                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            jsonData = response.body().string();
                            Log.i("Result login user", jsonData);
                            runOnUiThread(new Runnable() {
                              @Override
                              public void run() {



                                  try {
                                      JSONObject jsonObject = new JSONObject(jsonData);
                                      String result = jsonObject.getString("result");
                                      String mesage = jsonObject.getString("mesage");
                                      String dataJson = jsonObject.getString("data");
                                      if (dataJson.equals("[]")){
                                          Toast.makeText(getApplicationContext(), mesage, Toast.LENGTH_LONG).show();
                                      }else {
                                          JSONObject dataJsonObject = new JSONObject(dataJson);

                                          if (result.equals("true")) {
                                              id = dataJsonObject.getString("id");
                                              member_password = dataJsonObject.getString("member_password");
                                              member_fistname = dataJsonObject.getString("member_fistname");
                                              member_lastname = dataJsonObject.getString("member_lastname");
                                              member_email = dataJsonObject.getString("member_email");
                                              member_tel = dataJsonObject.getString("member_tel");

                                              executeRealmMember(id, member_password, member_fistname, member_lastname, member_email, member_tel);

                                          } else {

                                              Toast.makeText(getApplicationContext(), mesage, Toast.LENGTH_LONG).show();

                                          }
                                      }


                                  } catch (JSONException e) {
                                      e.printStackTrace();
                                  }
                               }
                             });


                        }
                    });

    }

    private void executeRealmMember(final String id, final String member_password, final String member_fistname,
                                    final String member_lastname, final String member_email, final String member_tel) {
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                RealmMember dataMember = realm.createObject(RealmMember.class);
                dataMember.setId(id);
                dataMember.setMember_password(member_password);
                dataMember.setMember_fistname(member_fistname);
                dataMember.setMember_lastname(member_lastname);
                dataMember.setMember_email(member_email);
                dataMember.setMember_tel(member_tel);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                     realm.beginTransaction();
                     RealmResults<RealmMember> result = realm.where(RealmMember.class).findAll();
                     realm.commitTransaction();
                     String a = result.get(0).getId();
                     String b = result.get(0).getMember_password();
                     String c = result.get(0).getMember_fistname();
                     String d = result.get(0).getMember_lastname();
                     String e = result.get(0).getMember_email() ;
                     String f = result.get(0).getMember_tel()  ;
                Toast.makeText(LoginActivity.this,a+"\n"+b+"\n"+c+"\n"+d+"\n"+e+"\n"+f, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), HomeListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(LoginActivity.this,"Create user error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

