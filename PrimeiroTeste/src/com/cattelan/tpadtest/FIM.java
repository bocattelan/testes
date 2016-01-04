package com.cattelan.tpadtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class FIM extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.relatorio_final);
		View decorView = getWindow().getDecorView();
	    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		
	Bundle b=this.getIntent().getExtras();
	int[] valores=b.getIntArray("score");
	
	TextView myAwesomeTextView = (TextView)findViewById(R.id.relatorio);
	String texto = "FINAL\nTeste A: " + valores[0] + 
			"\nTeste B: " + valores[1] +
			"\nTeste C: " + valores[2] +
			"\nTeste D: " + valores[3] +
			"\nTeste E: " + valores[4] +
			"\nTeste F: " + valores[5] +
			"\nTeste G: " + valores[6] +
			"\nTeste H: " + valores[7];
			
	myAwesomeTextView.setText(texto);
	}
}
