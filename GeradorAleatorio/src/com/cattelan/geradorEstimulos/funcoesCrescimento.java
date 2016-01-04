package com.cattelan.geradorEstimulos;

public class funcoesCrescimento {

	
	public float linear(float posicao, float tamanhoBarra){
		if(posicao < tamanhoBarra/2){
			float y = 1f - (1f/(tamanhoBarra/2f))*posicao;
			return y;
		}
		else{
			float y = (1f/(tamanhoBarra/2f))*posicao;
			return y;
		}
		
	}
	public float logaritmica(float posicao, float tamanhoBarra){
		
		if(posicao < tamanhoBarra/2){
			float y = 1f - logb(posicao, tamanhoBarra/2);
			return y;
		}
		else{
			float y = logb(posicao, tamanhoBarra/2);
			return y;
		}
		
	}
	public float exponencial(float posicao, float tamanhoBarra){
		if(posicao < tamanhoBarra/2){
			float y = (float) (1f - Math.exp(posicao - tamanhoBarra));
			return y;
		}
		else{
			float y = (float) Math.exp(posicao - tamanhoBarra);
			return y;
		}
		
	}
	
	public float quadratica(float posicao, float tamanhoBarra){
		if(posicao < tamanhoBarra/2){
			float y = 1f - (posicao*posicao)/tamanhoBarra*tamanhoBarra;
			return y;
		}
		else{
			float y = (posicao*posicao)/tamanhoBarra*tamanhoBarra;
			return y;
		}
		
	}
	
	public float gaussiana(float posicao, float tamanhoBarra){ //esse nao deve ser dividido em 2
		float y = (float) Math.exp(-1*Math.pow((posicao-tamanhoBarra/2), 2)/tamanhoBarra);
		return y;
		
	}
	

	 public static float logb( float a, float b )
	 {
	 return (float) (Math.log(a) / Math.log(b));
	 }
	 
	 public static float log2( float a )
	 {
	 return logb(a,2);
	 }

}
