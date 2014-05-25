import java.util.ArrayList; 
import java.util.Random;
import java.util.Scanner;

public class Lab5_paginacion {

    public static void main(String[] args) {
        Random numAleatorio;
        numAleatorio = new Random ();
        String serieAleatoria = "";
        int n, frames;
        
        System.out.println("Ingrese n:");
        Scanner entradaEscaner = new Scanner (System.in);
        n = Integer.parseInt(entradaEscaner.nextLine());
        
        System.out.println("Ingrese número de frames:");
        frames = Integer.parseInt(entradaEscaner.nextLine());

        for (int i=0; i<n; i++) {
            serieAleatoria += numAleatorio.nextInt(10) ;
        }
        
        System.out.println(serieAleatoria);
        System.out.println(frames);
    }
        
    public int FIFO(String serieAleatoria, int frames){
        int fallos = 0;
        // TODO
        return fallos;
    }
    
    public int LRU(String serieAleatoria, int frames){
        int fallos = 0;
        // TODO
        return fallos;
    }
    
    public int Optimo(String serieAleatoria, int frames){
        int fallos = 0;
        // TODO
        return fallos;
    }
    
}
