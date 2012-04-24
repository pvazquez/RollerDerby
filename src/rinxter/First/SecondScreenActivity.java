package rinxter.First;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SecondScreenActivity extends Activity {
	
	ArrayList<Information> info = new ArrayList<Information>();
	
	public void onCreate(Bundle savedInstanceState)
	{
		//gets the information from the website using a URI connection
		super.onCreate(savedInstanceState);
		super.setRequestedOrientation(0);
		setContentView(rinxter.First.R.layout.screen2);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		URI uri = (URI) extras.get("URI");
		
		ArrayList<String> al = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		try {
			request.setURI(uri);
			HttpResponse response = client.execute(request);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
			
			al = new ArrayList<String>();
			String s;
			while(true)
			{
				s = buf.readLine();
				if(s==null || s.length()==0)
					break;
				al.add(s);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getting data from an array of hash tables
		try {
			if(al.get(0).charAt(0) == '[')
			{
				String JSON = "";
				for(String x : al)
				{
					JSON = JSON + x;	
				}
				JSONArray myj = new JSONArray(JSON);
				for(int i = 0; i < myj.length(); i++)
				{
					JSONObject bout = myj.getJSONObject(i);
					Information q = new Information(bout);
					info.add(q);
				}
			}
			else
			{
				JSONObject myj = new JSONObject(al.get(0));
				info.add(new Information(myj));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/* Layout of the screen*/
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT);
		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		rowLp.setMargins(2, 1, 2, 1);
		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		cellLp.setMargins(2, 0, 2, 0);
		
		// Create First Row
		TableLayout maintable = (TableLayout) findViewById(R.id.maintable);
		maintable.setLayoutParams(lp);
		TableRow firstRow = new TableRow(this);
		firstRow.setBackgroundColor(Color.BLACK);
		firstRow.setPadding(3, 1, 3, 1);
        firstRow.setLayoutParams(rowLp);
        for(String key : info.get(0).getKeys())
        {
          	TextView tv = new TextView(this);
          	tv.setLayoutParams(cellLp);              
            firstRow.addView(tv);
           	tv.setPadding(2, 2, 2, 2);
           	tv.setBackgroundColor(Color.WHITE);
           	tv.setTextColor(Color.BLUE);
           	tv.setText(key);
        }
        maintable.addView(firstRow);
        
        // Add Data To Table
		for(int i = 0; i < info.size(); i++)
		{
			int count = 0;
			TableRow TR = new TableRow(this);
			TR.setBackgroundColor(Color.BLACK);
			TR.setPadding(3, 1, 3, 1);
            TR.setLayoutParams(rowLp);         
            for(String key: info.get(i).getKeys())
            {
            	String z = info.get(i).getData(key);
            	if(count == 0)
            	{
            		Button b = new Button(this);
            		b.setLayoutParams(cellLp);
            		TR.addView(b);
            		b.setText("View Bout Stats");
            		try {
						b.setOnClickListener(new boutClickListener(z, getApplicationContext(), this));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
            	}
            	else
	            {
	            	TextView tv = new TextView(this);
		            tv.setLayoutParams(cellLp);              
		            TR.addView(tv);
		           	tv.setText(z);
		           	tv.setPadding(2, 2, 2, 2);
		           	tv.setBackgroundColor(Color.WHITE);
		           	tv.setTextColor(Color.BLACK);
	            }
            	count++;
            }
        	maintable.addView(TR);
		}
		
		//Back Button
		TableRow TR = new TableRow(this);
		TR.setLayoutParams(rowLp);
		Button back = new Button(this);
		back.setLayoutParams(cellLp);
		back.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		TR.addView(back);
		maintable.addView(TR);
		back.setText("Back");
	}
	
}

