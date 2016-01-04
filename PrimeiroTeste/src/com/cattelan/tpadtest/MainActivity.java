package com.cattelan.tpadtest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.views.FrictionMapView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// Custom Haptic Rendering view defined in TPadLib
	FrictionMapView fricView;
	
	// TPad object defined in TPadLib
	TPad mTpad;
	View basicView;
	funcoesCrescimento funcoes = new funcoesCrescimento();
	
	private static Map<Character, Integer> tamanho;
    static
    {
    	tamanho = new HashMap<Character, Integer>();
    	//a tela do MOTO G possui 1280 pixels de altura no total e 9,03cm total
    	//assim, cada pixel possui 0,007cm de altura
    	
    	tamanho.put('A', 6);
    	tamanho.put('B', 12);
    	tamanho.put('C', 25);
    	tamanho.put('D', 50);
    	tamanho.put('E', 75); 
    	tamanho.put('N', 100);//0,7cm
    	
    	/*
    	tamanho.put('A', 100);
    	tamanho.put('B', 100);
    	tamanho.put('C', 100);
    	tamanho.put('D', 100);
    	tamanho.put('E', 100); //0,7cm
    	tamanho.put('N', 100);
    	*/
    }
//private boolean isTouch = false;
boolean pressed;
int sim = 0;
int NoMomento = 0;
public String[] latinSquareB = {"ABNCED", "BCADNE", "CDBEAN", "DECNBA", "ENDACB", "NAEBDC"}; //6
//public String[] latinSquareB = {"ABHCGDFE", "BCADHEGF", "CDBEAFHG", "DECFBGAH", "EFDGCHBA", "FGEHDACB", "GHFAEBDC", "HAGBFCED"}; //8
public char[][] test = new char[6][]; 
public int [] score = new int [8];
int indexTest;
int tipoOnda;

int min = 0;
int max = 2;
Random r;
int ladoTela;
float posicaoInicial;
int numToques;
//String picName = "test";
//String extension = ".bmp";
//Vibrator vibra1;
View decorView;
//String temp = latinSquareB[0] + latinSquareB[1];
//Integer oi = tamanho.get(test[indexTest][NoMomento]);


@Override
protected void onCreate(Bundle savedInstanceState) {
	//http://hamsterandwheel.com/grids/index2d.php GERADOR DE LATIN SQUARE
	test[0] = (latinSquareB[0] + latinSquareB[1] +latinSquareB[5] + latinSquareB[2] + latinSquareB[4] + latinSquareB[3]).toCharArray();
	test[1] = (latinSquareB[1] + latinSquareB[2] +latinSquareB[0] + latinSquareB[3] + latinSquareB[5] + latinSquareB[4]).toCharArray();
	test[2] = (latinSquareB[2] + latinSquareB[3] +latinSquareB[1] + latinSquareB[4] + latinSquareB[0] + latinSquareB[5]).toCharArray(); 
	test[3] = (latinSquareB[3] + latinSquareB[4] +latinSquareB[2] + latinSquareB[5] + latinSquareB[1] + latinSquareB[0]).toCharArray();
	test[4] = (latinSquareB[4] + latinSquareB[5] +latinSquareB[3] + latinSquareB[0] + latinSquareB[2] + latinSquareB[1]).toCharArray();
	test[5] = (latinSquareB[5] + latinSquareB[0] +latinSquareB[4] + latinSquareB[1] + latinSquareB[3] + latinSquareB[2]).toCharArray();
	//6
	/*
	test[0] = (latinSquareB[0] + latinSquareB[1] +latinSquareB[7] + latinSquareB[2] + latinSquareB[6] + latinSquareB[3] + latinSquareB[5] + latinSquareB[4]).toCharArray();
	test[1] = (latinSquareB[1] + latinSquareB[2] +latinSquareB[0] + latinSquareB[3] + latinSquareB[7] + latinSquareB[4] + latinSquareB[6] + latinSquareB[5]).toCharArray();
	test[2] = (latinSquareB[2] + latinSquareB[3] +latinSquareB[1] + latinSquareB[4] + latinSquareB[0] + latinSquareB[5] + latinSquareB[7] + latinSquareB[6]).toCharArray(); 
	test[3] = (latinSquareB[3] + latinSquareB[4] +latinSquareB[2] + latinSquareB[5] + latinSquareB[1] + latinSquareB[6] + latinSquareB[0] + latinSquareB[7]).toCharArray();
	test[4] = (latinSquareB[4] + latinSquareB[5] +latinSquareB[3] + latinSquareB[6] + latinSquareB[2] + latinSquareB[7] + latinSquareB[1] + latinSquareB[0]).toCharArray();
	test[5] = (latinSquareB[5] + latinSquareB[6] +latinSquareB[4] + latinSquareB[7] + latinSquareB[3] + latinSquareB[0] + latinSquareB[2] + latinSquareB[1]).toCharArray();
	test[6] = (latinSquareB[6] + latinSquareB[7] +latinSquareB[5] + latinSquareB[0] + latinSquareB[4] + latinSquareB[1] + latinSquareB[3] + latinSquareB[2]).toCharArray();
	test[7] = (latinSquareB[7] + latinSquareB[0] +latinSquareB[6] + latinSquareB[1] + latinSquareB[5] + latinSquareB[2] + latinSquareB[4] + latinSquareB[3]).toCharArray();
	*/
	//8
	
	r = new Random();
	ladoTela = r.nextInt(max - min) + min;
	numToques = 0;
	
	super.onCreate(savedInstanceState);
	Intent dados = getIntent();
	pressed=false;
	indexTest = dados.getIntExtra("indexTest", -1);
	tipoOnda = dados.getIntExtra("tipoOnda",-1);
	
	
	setContentView(R.layout.activity_main);
	decorView = getWindow().getDecorView();
    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
				
    mTpad = new TPadImpl(this);
    basicView = (View) findViewById(R.id.view1);
    basicView.setOnTouchListener(new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// Grab the x coordinate of the touch event, and the width of the view the event was in
			float y = event.getY();
			float x = event.getX();
			int heigth = view.getHeight();
			int inicio;
			
			
			int width = view.getWidth();
			
			int tamanhoBarra = tamanho.get(test[indexTest][NoMomento]);
			// The switch case below looks at the event's properties and specifies what type of touch it was
			switch (event.getAction()) {

			case MotionEvent.ACTION_DOWN:
				numToques++;
				inicio = r.nextInt(275 - 100) + 100;
				posicaoInicial = heigth/2f-inicio;
					mTpad.sendFriction(1f);

				break;

			case MotionEvent.ACTION_MOVE:
				// If the user moves to the left half of the view, turn off the TPad, else turn it on to 100%
				
				if(ladoTela == 0)//caso esquerda
					{
				if (y > posicaoInicial && y < posicaoInicial + tamanhoBarra 
						&& x <= width/2f) 
						{
					
					switch (tipoOnda)
						{
						case 0:
							//Toast.makeText(getApplicationContext(),"quadrada" , Toast.LENGTH_SHORT).show();
							mTpad.turnOff();
							break;
							//mTpad.sendFriction( funcoes.linear(y - posicaoInicial,(float)tamanhoBarra));
						case 1:
							//Toast.makeText(getApplicationContext(),"linear" , Toast.LENGTH_SHORT).show();
							mTpad.sendFriction( funcoes.linear(y - posicaoInicial,(float)tamanhoBarra));
							break;
						case 2:
							//Toast.makeText(getApplicationContext(),"quadratica" ,Toast.LENGTH_SHORT).show();
							mTpad.sendFriction( funcoes.quadratica(y - posicaoInicial,(float)tamanhoBarra));
							break;
						case 3:
							//Toast.makeText(getApplicationContext(),"logaritmica" ,Toast.LENGTH_SHORT).show();
							mTpad.sendFriction( funcoes.logaritmica(y - posicaoInicial,(float)tamanhoBarra));
							break;
						case 4:
							//Toast.makeText(getApplicationContext(),"exponencial" , Toast.LENGTH_SHORT).show();
							mTpad.sendFriction( funcoes.exponencial(y - posicaoInicial,(float)tamanhoBarra));
							break;
						case 5:
							//Toast.makeText(getApplicationContext(),"gaussiana" ,Toast.LENGTH_SHORT).show();
							mTpad.sendFriction( funcoes.gaussiana(y - posicaoInicial,(float)tamanhoBarra));
							}
						}
				else {
					mTpad.sendFriction(1f);
				}
					
					}
				
				else //caso direita
				{
				 if (y > posicaoInicial && y < posicaoInicial + tamanhoBarra && x >= width/2f) 
					{
				switch (tipoOnda)
					{
					case 0:
						//Toast.makeText(getApplicationContext(),"quadrada" , Toast.LENGTH_SHORT).show();
						mTpad.turnOff();
						break;
						//mTpad.sendFriction( funcoes.linear(y - posicaoInicial,(float)tamanhoBarra));
					case 1:
						//Toast.makeText(getApplicationContext(),"linear" , Toast.LENGTH_SHORT).show();
						mTpad.sendFriction( funcoes.linear(y - posicaoInicial,(float)tamanhoBarra));
						break;
					case 2:
						//Toast.makeText(getApplicationContext(),"quadratica" ,Toast.LENGTH_SHORT).show();
						mTpad.sendFriction( funcoes.quadratica(y - posicaoInicial,(float)tamanhoBarra));
						break;
					case 3:
						//Toast.makeText(getApplicationContext(),"logaritmica" ,Toast.LENGTH_SHORT).show();
						mTpad.sendFriction( funcoes.logaritmica(y - posicaoInicial,(float)tamanhoBarra));
						break;
					case 4:
						//Toast.makeText(getApplicationContext(),"exponencial" , Toast.LENGTH_SHORT).show();
						mTpad.sendFriction( funcoes.exponencial(y - posicaoInicial,(float)tamanhoBarra));
						break;
					case 5:
						//Toast.makeText(getApplicationContext(),"gaussiana" ,Toast.LENGTH_SHORT).show();
						mTpad.sendFriction( funcoes.gaussiana(y - posicaoInicial,(float)tamanhoBarra));
						}
					} 
				
			else {
				mTpad.sendFriction(1f);
			}
				}
			  
			
				break;

			case MotionEvent.ACTION_UP:
				// If the user lifts up their finger from the screen, turn the TPad off (0%)
				mTpad.turnOff();
				
				
				//Toast.makeText(getApplicationContext(), ladoTela + "",
						  // Toast.LENGTH_SHORT).show();
			
		    	//vibra.cancel();
		    	// cria mensagem perguntando se sentiu em estimulo
				if(numToques == 2){
					numToques = 0;
		    	new AlertDialog.Builder(MainActivity.this)
		    	.setCancelable(false)
		        .setTitle("Querido Usuário")
		        .setMessage("Em qual lado houve um estímulo?")
		        .setPositiveButton("Direita", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) { 
		            
		            	
		            	
		                // continue with delete
		            	sim++;
		            	
		            	
		            	//score[0]++;
		            	if (ladoTela == 1){
		            	switch (test[indexTest][NoMomento]){
		            	case 'A' :
		            		score[0]++;
		            		break;
		            	case 'B':
		            		score[1]++;
		            		break;
		            	case 'C':
		            		score[2]++;
		            		break;
		            	case 'D':
		            		score[3]++;
		            		break;
		            	case 'E':
		            		score[4]++;
		            		break;
		            	case 'N':
		            		score[5]++;
		            		break;
		            	}
		            	}
		            	ladoTela = r.nextInt(max - min) + min;
		            	NoMomento++;
		            	if(NoMomento < test[indexTest].length){
		            	//pega nova foto
		            	    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		            	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		            	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		            	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		            	            | View.SYSTEM_UI_FLAG_FULLSCREEN
		            	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		            			}
		            		else{
		            		 //Starting a new Intent
		            			Bundle b=new Bundle();
		            			b.putIntArray("score", score);
		            			Intent i=new Intent(getApplicationContext(), FIM.class);
		            			i.putExtras(b);
		            		 //Starting a new Intent
		            			startActivity(i);
		            			finish();
		            		//setContentView(R.layout.relatorio_final);
		            	}
		            }
		         })
		        .setNegativeButton("Esquerda", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	
		            	
		            	if (ladoTela == 0){
			            	switch (test[indexTest][NoMomento]){
			            	case 'A' :
			            		score[0]++;
			            		break;
			            	case 'B':
			            		score[1]++;
			            		break;
			            	case 'C':
			            		score[2]++;
			            		break;
			            	case 'D':
			            		score[3]++;
			            		break;
			            	case 'E':
			            		score[4]++;
			            		break;
			            	case 'N':
			            		score[5]++;
			            		break;
			            	}
			            	}
		            	ladoTela = r.nextInt(max - min) + min;
		            	NoMomento++;
		            	if(NoMomento < test[indexTest].length){
		            	//pega nova foto
		            		
		            		
		            	    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		            	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		            	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		            	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		            	            | View.SYSTEM_UI_FLAG_FULLSCREEN
		            	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		            			}
		            		else{
		            		 //Starting a new Intent
		            			Bundle b=new Bundle();
		            			b.putIntArray("score", score);
		            			Intent i=new Intent(getApplicationContext(), FIM.class);
		            			i.putExtras(b);
		            		 //Starting a new Intent
		            			startActivity(i);
		            			finish();
		            		//setContentView(R.layout.relatorio_final);
		            	}
		            }
		         })
		        .setIcon(android.R.drawable.ic_dialog_alert)
		         .show();
		    	
				}
				
				
				
				//NoMomento++;
				//break;
				
			}

			return true;
		}
		
	});

    	
    	
	}

@Override
protected void onRestart() {
    super.onRestart();
    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
}

@Override
protected void onDestroy() {
	mTpad.disconnectTPad();
	super.onDestroy();
}
	

/*

@Override
public boolean onTouchEvent(MotionEvent event) {
	
	Vibrator vibra = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	final View decorView = getWindow().getDecorView();
    int eventaction = event.getAction();

    switch (eventaction) {
        
    case MotionEvent.ACTION_UP:
    	vibra.cancel();
    	// cria mensagem perguntando se sentiu em estimulo
    	new AlertDialog.Builder(this)
    	.setCancelable(false)
        .setTitle("Querido Usuário")
        .setMessage("Houve um estímulo?")
        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
            	new CountDownTimer(3000, 1000) {

      	            public void onTick(long millisUntilFinished) {
      	            }

      	            public void onFinish() {
         		toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); 
      	            }
         		 }.start();
            	
            	
            	
            	
                // continue with delete
            	sim++;
            	
            	//score[0]++;
            	switch (test[indexTest][NoMomento]){
            	case 'A' :
            		score[0]++;
            		break;
            	case 'B':
            		score[1]++;
            		break;
            	case 'C':
            		score[2]++;
            		break;
            	case 'D':
            		score[3]++;
            		break;
            	case 'E':
            		score[4]++;
            		break;
            	}
            	
            	NoMomento++;
            	if(NoMomento < test[indexTest].length){
            	//pega nova foto
            	    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            	            | View.SYSTEM_UI_FLAG_FULLSCREEN
            	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            			}
            		else{
            		 //Starting a new Intent
            			Bundle b=new Bundle();
            			b.putIntArray("score", score);
            			Intent i=new Intent(getApplicationContext(), FIM.class);
            			i.putExtras(b);
            		 //Starting a new Intent
            			startActivity(i);
            			finish();
            		//setContentView(R.layout.relatorio_final);
            	}
            }
         })
        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            	
            	
            	new CountDownTimer(3000, 1000) {

      	            public void onTick(long millisUntilFinished) {
      	            }

      	            public void onFinish() {
         		toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); 
      	            }
         		 }.start();
            	
            	
            	
            	
            	
            	NoMomento++;
            	if(NoMomento < test[indexTest].length){
            	//pega nova foto
            		
            		
            	    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            	            | View.SYSTEM_UI_FLAG_FULLSCREEN
            	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            			}
            		else{
            		 //Starting a new Intent
            			Bundle b=new Bundle();
            			b.putIntArray("score", score);
            			Intent i=new Intent(getApplicationContext(), FIM.class);
            			i.putExtras(b);
            		 //Starting a new Intent
            			startActivity(i);
            			finish();
            		//setContentView(R.layout.relatorio_final);
            	}
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
         .show();
    	
    	 
    	
        break;
        
    default:
    
    
    }
    return true;

}
*/

}