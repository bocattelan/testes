package com.cattelan.geradorEstimulos;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.views.FrictionMapView;

public class MainActivity extends Activity {
	// Custom Haptic Rendering view defined in TPadLib
	FrictionMapView fricView;
	
	// TPad object defined in TPadLib
	TPad mTpad;
	View basicView;
	funcoesCrescimento funcoes = new funcoesCrescimento();
	
//private boolean isTouch = false;
boolean pressed;
int sim = 0;
int NoMomento = 0;
public String[] latinSquareB = {"ABNCED", "BCADNE", "CDBEAN", "DECNBA", "ENDACB", "NAEBDC"};
//ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
public char[][] test = new char[6][]; 
public int [] score = new int [6];
int indexTest;
int tipoOnda;

int min = 0;
int max = 2;
Random r;
int ladoTela;
float posicaoInicial;
int numToques;
int funcao;
//String picName = "test";
//String extension = ".bmp";
//Vibrator vibra1;
View decorView;
//String temp = latinSquareB[0] + latinSquareB[1];
//Integer oi = tamanho.get(test[indexTest][NoMomento]);


@Override
protected void onCreate(Bundle savedInstanceState) {
	
	test[0] = (latinSquareB[0] + latinSquareB[1] +latinSquareB[5] + latinSquareB[2] + latinSquareB[4] + latinSquareB[3]).toCharArray();
	test[1] = (latinSquareB[1] + latinSquareB[2] +latinSquareB[0] + latinSquareB[3] + latinSquareB[5] + latinSquareB[4]).toCharArray();
	test[2] = (latinSquareB[2] + latinSquareB[3] +latinSquareB[1] + latinSquareB[4] + latinSquareB[0] + latinSquareB[5]).toCharArray(); 
	test[3] = (latinSquareB[3] + latinSquareB[4] +latinSquareB[2] + latinSquareB[5] + latinSquareB[1] + latinSquareB[0]).toCharArray();
	test[4] = (latinSquareB[4] + latinSquareB[5] +latinSquareB[3] + latinSquareB[0] + latinSquareB[2] + latinSquareB[1]).toCharArray();
	test[5] = (latinSquareB[5] + latinSquareB[0] +latinSquareB[4] + latinSquareB[1] + latinSquareB[3] + latinSquareB[2]).toCharArray();
	
	r = new Random();
	ladoTela = r.nextInt(max - min) + min;
	numToques = 0;
	funcao = r.nextInt(6 - 0) + 0;
	tipoOnda = funcao;
	super.onCreate(savedInstanceState);
	Intent dados = getIntent();
	pressed=false;
	//Toast.makeText(getApplicationContext(), tamanho.get('E').toString() + " Pixels",
			  // Toast.LENGTH_SHORT).show();
	//indexTest = dados.getIntExtra("indexTest", -1);
	//tipoOnda = dados.getIntExtra("tipoOnda",-1);
	
	
	//vibra1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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
			posicaoInicial = heigth/2f;
			
			//Random r3 = new Random();
			//int ladoTela = r3.nextInt(1 - 0) + 0;
			int width = view.getWidth();
			
			int tamanhoBarra = 100;
			// The switch case below looks at the event's properties and specifies what type of touch it was
			switch (event.getAction()) {

			case MotionEvent.ACTION_DOWN:
					mTpad.sendFriction(1f);
				break;

			case MotionEvent.ACTION_MOVE:
				// If the user moves to the left half of the view, turn off the TPad, else turn it on to 100%
				
		
				if (y > posicaoInicial && y < posicaoInicial + tamanhoBarra){
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
						break;
				}
				else{
					mTpad.sendFriction(1f);
				}
				break;
				
			case MotionEvent.ACTION_UP:
				funcao = r.nextInt(6 - 0) + 0;
				tipoOnda = funcao;
				// If the user lifts up their finger from the screen, turn the TPad off (0%)
				mTpad.turnOff();
				
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