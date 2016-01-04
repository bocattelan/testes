package com.cattelan.geradorEstimulos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;




public class Choose_test extends Activity {
	int escolha;
	int indexTest = 100;
	int tipoOnda = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_choose_test);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.planets_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		//Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		     @Override
		     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		         //stuff here to handle item selection 
		    	 indexTest = (int) id;
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {

		     }
		});
		
		
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.ondas, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner2.setAdapter(adapter2);
		//Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		     @Override
		     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		         //stuff here to handle item selection 
		    	 tipoOnda = (int) id;
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {

		     }
		});
		
		
		
		
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen); 
        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
 
                //Sending data to another Activity
                nextScreen.putExtra("indexTest", indexTest);
                nextScreen.putExtra("tipoOnda", tipoOnda);
                startActivity(nextScreen);
                finish();
 
            }
        });
        
        
        
	}
	

	
}
