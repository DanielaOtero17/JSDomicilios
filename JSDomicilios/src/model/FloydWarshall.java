package model;

public class FloydWarshall {

	private int[][] matrizAdjacencia;

	private int[][] matrizSalida;
	private int[] vect1;
	private int[] vect2;
	private int[][] A;
	private int[][] B;
	private int acumulado;

	public FloydWarshall(int[][] matriz) {
		this.matrizAdjacencia = matriz;
		vect1 = new int[matriz.length];
		vect2 = new int[matriz.length];
		A = new int[matriz.length][matriz.length];
		B = new int[matriz.length][matriz.length];
	}

	public void executar() {
		int n = matrizAdjacencia.length;

		int bucle,i,j,suma;
		
	for(bucle=0;bucle<=n;bucle++){
		
		for(i=0;i<=n;i++){
			
			vect1[i]= A[bucle][i];
			vect2[i]= A[i][bucle];
	}

	for(i=1;i<=n;i++)
	for(j=1;j<=n;j++){
	if(vect2[i]==Integer.MAX_VALUE || vect1[j]==Integer.MAX_VALUE) 
		suma=Integer.MAX_VALUE;
	else suma= vect2[i]+vect1[j];
	if(suma<A[i][j]){
	A[i][j]=suma;
	B[i][j]=bucle;}

	mostrar1(n);
	}}
	}
	
	//Imprime distancias o pesos optimo
	public void mostrar1(int n){
		int i,j;
	      for(i=1;i<=n;i++){
	    for(j=1;j<=n;j++){
	    	System.out.println(A[i][j]);
	    	acumulado+= A[i][j];
	    	}
	    }
	}
	public void ingresar(int n, int[][] matriz){
	    //cout<<"ingrese numero de nodos";
	    //cin>>n;
	    int i,j;
	 for(i=1;i<=n;i++){
	    for(j=1;j<=n;j++){
	    //cout<<"ingrese matriz de ["<<i<<"]["<<j<<"] ";
	    //cin>>A[i][j];    }
	}
	 }
	}
	public void preguntar(int vertice1, int vertice2){
		
		int i,j;
	//    cout<<"de que vertice a que vertice desea ir : "<<endl;
	// cin>>i>>j;
	if(vertice1==0 || vertice2==0 || vertice1==vertice2){
	System.out.println("distancia minima es 0");
	}else{
		
		System.out.println("distancia minima es " + A[vertice1][vertice2] );
		System.out.println("pasa por el vertice " + B[vertice1][vertice2] + 
				"y despues por el" + vertice2);
	}
	}
	
	public int getAcumulado(){
		
		return acumulado;
	}

			
	}
