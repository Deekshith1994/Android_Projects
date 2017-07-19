package uncc.vedhasd.smartutility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddViewApp extends ActionBarActivity {
    Bundle bundle;
    TextView tuserHeader,result1;
    EditText eAppName, eEnergy, eOpTime,eStartTime, eDeadline, eConstraints;
    Button bCancel, bAdd, bBack;
    String IPADD="192.168.0.13:80"; //10.39.202.120";
    String IPADDutil="192.168.0.24:80";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view_app);

        //mapping text view
        tuserHeader=(TextView)findViewById(R.id.tUserHeader);
        bundle=getIntent().getExtras();
        final String userid=bundle.getString("username");
        tuserHeader.setText("USER: "+ userid);

        //mapping Edittext value
        eAppName=(EditText)findViewById(R.id.eAppname);
        eEnergy=(EditText)findViewById(R.id.eEnergy);
        eOpTime=(EditText)findViewById(R.id.eOpTime);
        eStartTime=(EditText)findViewById(R.id.eStartTime);
        eDeadline=(EditText)findViewById(R.id.eDeadline);
        eConstraints=(EditText)findViewById(R.id.eConstraints);
        //result1= (TextView) findViewById(R.id.result);


        //mapping buttons
        bCancel=(Button)findViewById(R.id.bCancel);
        bAdd=(Button)findViewById(R.id.bAdd);
        bBack=(Button)findViewById(R.id.bBack);

        //listener for Cancel button
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                eAppName.setText(null);
                eEnergy.setText(null);
                eOpTime.setText(null);
                eStartTime.setText(null);
                eDeadline.setText(null);
                eConstraints.setText(null);
            }
        });

        //listener Add
        bAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String appname=eAppName.getText().toString();
                String energy=eEnergy.getText().toString();
                String optime=eOpTime.getText().toString();
                String starttime=eStartTime.getText().toString();
                String deadline=eDeadline.getText().toString();
                String constraints=eConstraints.getText().toString();

                if(starttime.contains(":")){
                    String[] hhmm=starttime.split(":");
                    if(Integer.parseInt(hhmm[0])<= 9){
                        hhmm[0]="0"+hhmm[0];
                    }
                    starttime=hhmm[0]+hhmm[1];
                }

                if(deadline.contains(":")){
                    String[] hhmm=deadline.split(":");
                    if(Integer.parseInt(hhmm[0])<= 9){
                        hhmm[0]="0"+hhmm[0];
                    }
                    deadline=hhmm[0]+hhmm[1];
                }


                // all the validations are done in sequence
                if (appname.length() == 0){
                    eAppName.setError("Please Enter Appliance");
                }/*
				else if (username.length() < 6){
					eUserName.setError("Please enter username greater than 6 characters");
				}*/
                else if (energy.length() == 0){
                    eEnergy.setError("Please enter Energy");
                }/*
				else if ((password.length() < 6)){
					ePassword.setError("Password too short, please enter more than 6 chars");
				}*/
                else if (optime.length() == 0){
                    eOpTime.setError("Please enter operation time");
                }
                else if (starttime.length() == 0){
                    eOpTime.setError("Please enter Start time");
                }
                else if(Integer.parseInt(deadline)>2400){
                    eDeadline.setError("Enter deadline less than 2400Hrs");
                }
                else if(Integer.parseInt(starttime)>2400){
                    eDeadline.setError("Enter start time less than 2400Hrs");
                }

                else if(Integer.parseInt(optime)>(Integer.parseInt(deadline)-Integer.parseInt(starttime)))
                {
                    eOpTime.setError("Operation Time cannot be greater than interval");
                }
                else if (!(constraints.equalsIgnoreCase("HARD")||constraints.equalsIgnoreCase("Soft"))){
                    eConstraints.setError("Enter either Hard or Soft");
                }/*
				else if (firstname.length() == 0){
					eFirstName.setError("Please enter first name");
				}
				else if (lastname.length() == 0){
					eLastName.setError("Please enter your last name");
				}
				else if (email.length() == 0){
					eEmail.setError("Enter email-id");
				}
				else if (!emailCheck){
					eEmail.setError("Please enter valid mail id");
				}
				else if (phone_No.length() == 0){
					ePhoneNumber.setError("Enter valid phone number");
				}
				else if(!cBox.isChecked()){
					Toast.makeText(getBaseContext(),"Please read terms and conditions",Toast.LENGTH_SHORT).show();
				}*/
                //finally on successful verification the appropriate message is displayed
                else {
                    if(!starttime.contains(":")){
                        String hh=starttime.substring(0,2);
                        String mm=starttime.substring(2,4);
                        starttime=hh+":"+mm;
                    }

                    if(!deadline.contains(":")){
                        String hh1=deadline.substring(0,2);
                        String mm1=deadline.substring(2,4);
                        deadline=hh1+":"+mm1;
                    }

                    String status = getConnection(userid,appname,energy,optime, starttime, deadline, constraints);
                    String statUtil=getConnectionUtil(userid,appname,energy,optime, starttime, deadline, constraints);

                    if(status.contains("true") && statUtil.contains("true")){
                        Toast.makeText(getBaseContext(),"New Appliance added!",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    else if(status.contains("false_user")){
                        Toast.makeText(getBaseContext(),"Appliance already exists!",Toast.LENGTH_SHORT).show();
                    }
                    else if(status.contains("false_email")){
                        Toast.makeText(getBaseContext(),"Email already registered. Please enter different email!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        result1.setText(status + "..." + statUtil );
                        Toast.makeText(getBaseContext(),"Problem! Server returned invalid value",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //listener Back
        bBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }






    /*---------------------------------default stub----------------------------------------
     *-------------------------------------------------------------------------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_view_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
		
	
	/*-----------------------------------------------------------------------------------
	 * ------------------------HTTP POST METHOD------------------------------------------
	 * ----------------------------------------------------------------------------------*/


    public String getConnection(String usr,String app, String pwd, String fName, String lName,String mail, String phone){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("username",usr));
        nameValuePairs1.add(new BasicNameValuePair("appname",app));
        nameValuePairs1.add(new BasicNameValuePair("energy",pwd));
        nameValuePairs1.add(new BasicNameValuePair("optime",fName));
        nameValuePairs1.add(new BasicNameValuePair("starttime",lName));
        nameValuePairs1.add(new BasicNameValuePair("deadline",mail));
        nameValuePairs1.add(new BasicNameValuePair("constraints",phone));

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+IPADD+"/addappliance.php");
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


    /*----util----*/
    public String getConnectionUtil(String usr,String app, String pwd, String fName, String lName,String mail, String phone){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("username",usr));
        nameValuePairs1.add(new BasicNameValuePair("appname",app));
        nameValuePairs1.add(new BasicNameValuePair("energy",pwd));
        nameValuePairs1.add(new BasicNameValuePair("optime",fName));
        nameValuePairs1.add(new BasicNameValuePair("starttime",lName));
        nameValuePairs1.add(new BasicNameValuePair("deadline",mail));
        nameValuePairs1.add(new BasicNameValuePair("constraints",phone));

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+IPADDutil+"/addappliance.php");
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

