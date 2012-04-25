package rinxter.First;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class boutClickListener implements View.OnClickListener {

	private URI boutURI = null;
	private Context context = null;
	private Activity activity = null;
	
	public boutClickListener(String boutId, Context c, Activity a) throws URISyntaxException
	{
		boutURI = new URI("http://rinxter.net/wftda/ds?type=bout&boutId=" + boutId);
		context = c; 
		activity = a;
	}
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(context, SecondScreenActivity.class);
		i.putExtra("URI", boutURI);
		i.putExtra("page", "single");
		activity.startActivity(i);
	}

}
