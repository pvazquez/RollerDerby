package rinxter.First;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Information {
	
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> values = new ArrayList<String>();
	
	public Information(JSONObject data) throws JSONException
	{
		JSONArray info_name = data.names();
		JSONArray info_data = data.toJSONArray(info_name);
		
		for(int i = 0; i < info_name.length(); i++)
			names.add(info_name.getString(i));
		for(int i = 0; i < info_data.length(); i++)
			values.add(info_data.getString(i));
	}

}
