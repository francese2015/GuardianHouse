package it.unisa.guardianhouse;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity {

	private Button btnAccount;
	private Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		btnAccount = (Button) findViewById(R.id.button_account);
        btnLogout = (Button) findViewById(R.id.button_logout);
        
        // bottone account
        btnAccount.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
            	Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
        		startActivity(intent);
            } 
        });
        
     // bottone logout
        btnLogout.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
            	
            } 
        });        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
}
