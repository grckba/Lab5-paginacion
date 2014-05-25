import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab5_paginacion {

    public static void main(String[] args) {
        Random numAleatorio;
        numAleatorio = new Random();
        String seriePaginas = "";
        int n=0, frames=0;

        if(args.length == 2 && Integer.parseInt(args[0]) != 0 && Integer.parseInt(args[1]) != 0){

           n = Integer.parseInt(args[0]);

           frames = Integer.parseInt(args[1]);
        }else{
           System.out.println("Ingrese n:");
           Scanner entradaEscaner = new Scanner(System.in);
           n = Integer.parseInt(entradaEscaner.nextLine());

           System.out.println("Ingrese número de frames:");
           frames = Integer.parseInt(entradaEscaner.nextLine());
        }

        for (int i = 0; i < n; i++) {
            seriePaginas += numAleatorio.nextInt(10);
        }

        System.out.println(seriePaginas);
        System.out.println(frames);

        //System.out.println("Número de fallos con FIFO: "+FIFO("70120304230321201701", 3));
        System.out.println("Número de fallos con FIFO: "+FIFO(seriePaginas, frames));
    }

    public static int FIFO(String serieAleatoria, int frames) {
        int contPagina = 0;
        int paginaActual;
        boolean find = false;
        String primeraPagina;
        String arregloFrames[] = new String[frames];

        while (serieAleatoria.length() > 0) {
            primeraPagina = serieAleatoria.substring(0, 1);
            serieAleatoria = serieAleatoria.substring(1, serieAleatoria.length());
            find = false;
            for (int i = 0; i < frames; i++) {
                if (arregloFrames[i] != null && arregloFrames[i].equals(primeraPagina)) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                paginaActual = contPagina % frames;
                if (arregloFrames[paginaActual] == null || arregloFrames[paginaActual] != primeraPagina) {
                    arregloFrames[paginaActual] = primeraPagina;
                    contPagina++;
                }
            }
// System.out.println("ArregloFrames: ");
// for (String i : arregloFrames) {
// System.out.println(i);
// }
        }
        return contPagina;
    }

    public int LRU(String seriePaginas, int frames) {
        int fallos = 0;
        // TODO
        return fallos;
    }

    public int Optimo(String seriePaginas, int frames) {
        int fallos = 0;
        // TODO
        return fallos;
    }

}
