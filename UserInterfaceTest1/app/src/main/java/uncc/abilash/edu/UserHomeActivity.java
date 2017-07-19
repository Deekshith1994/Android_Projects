package uncc.abilash.edu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserHomeActivity extends Activity{
	
	private Bundle bundle;
    static String trackid;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);

		setContentView(R.layout.user_home);
		
		final Button applianceButton = (Button) findViewById(R.id.button1);
		final Button accountButton = (Button) findViewById(R.id.button2);
		final Button logoutButton = (Button) findViewById(R.id.button3);
		final EditText trackid_in = (EditText) findViewById(R.id.track_ID);
        final TextView status_lab = (TextView) findViewById(R.id.status_lab);
        final TextView status_id = (TextView) findViewById(R.id.status_id);
        final TextView sCity_id = (TextView) findViewById(R.id.sCity_id);
        final TextView sCity_lab = (TextView) findViewById(R.id.sCity_lab);
        final TextView dCity_id = (TextView) findViewById(R.id.dCity_id);
        final TextView dCity_lab = (TextView) findViewById(R.id.dCity_lab);
        final TextView pCiti_id = (TextView) findViewById(R.id.pCity_id);
        final TextView pCity_lab = (TextView) findViewById(R.id.pCity_lab);
        final TextView start_lab = (TextView) findViewById(R.id.startDate_lab);
        final TextView start_id = (TextView) findViewById(R.id.startDate_id);
        final TextView end_lab = (TextView) findViewById(R.id.endDate_lab);
        final TextView end_id = (TextView) findViewById(R.id.endDate_id);

        sCity_lab.setText("");
        dCity_lab.setText("");
        pCity_lab.setText("");
        start_lab.setText("");
        end_lab.setText("");

        applianceButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                sCity_id.setText("");
                dCity_id.setText("");
                pCiti_id.setText("");
                status_id.setText("");
                start_id.setText("");
                end_id.setText("");

                 status_lab.setText("Status");
                sCity_lab.setText("Source City");
                dCity_lab.setText("Destination City");
                pCity_lab.setText("Present City");
                start_lab.setText("Packet Collected");
                end_lab.setText("Delivery");


                trackid = trackid_in.getText().toString();


                if (trackid.equals("")) {
                    Toast.makeText(getBaseContext(), "Enter Tracking ID", Toast.LENGTH_SHORT).show();
                } else if (isNetworkAvailable()) {
                    Log.d("test", "Network available");

                    String result = getConnection(trackid);
                    StringTokenizer st = new StringTokenizer(result, "-");


                    String SCity = st.nextToken();
                    String DCity = st.nextToken();
                    String PCity = st.nextToken();
                    String status = st.nextToken();
                    String stDate = st.nextToken();
                    String edDate = st.nextToken();

                    if (!((result.equals("null\n")) || (result.equals("")))) {


                      //  if (Android_SQLActivity.userString.equals(usrname)) {
                            Toast.makeText(getBaseContext(), "Tracking Successfull!", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();


                            sCity_id.setText(SCity);
                            dCity_id.setText(DCity);
                            pCiti_id.setText(PCity);
                            status_id.setText(status);
                           start_id.setText(stDate);
                            end_id.setText(edDate);



                      //  } else {
                     //       Toast.makeText(getBaseContext(), "Invalid Tracking ID!", Toast.LENGTH_SHORT).show();
                      //  }

                    } else
                        Toast.makeText(getBaseContext(), "Tracking Failed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        });
		
		
		logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
		
		accountButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bundle = getIntent().getExtras();
				Intent accountActivity = new Intent(getBaseContext(),ManageAccountActivity.class);
				accountActivity.putExtras(bundle);
				startActivity(accountActivity);
			}
		});

	}



    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public String getConnection(String trackid){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("trackid",trackid));
        nameValuePairs1.add(new BasicNameValuePair("username",Android_SQLActivity.userString));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.0.6:80/track.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getBaseContext(), "Server Not Responding", Toast.LENGTH_SHORT).show();
            return "";
        }
        //convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            result=sb.toString();
        }
        catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
        return result;

    }
}
