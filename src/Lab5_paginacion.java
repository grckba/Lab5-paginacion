
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab5_paginacion {

    public static void main(String[] args) {
        Random numAleatorio;
        numAleatorio = new Random();
        String seriePaginas = "";
        int n, frames;

        System.out.println("Ingrese n:");
        Scanner entradaEscaner = new Scanner(System.in);
        n = Integer.parseInt(entradaEscaner.nextLine());

        System.out.println("Ingrese número de frames:");
        frames = Integer.parseInt(entradaEscaner.nextLine());

        for (int i = 0; i < n; i++) {
            seriePaginas += numAleatorio.nextInt(10);
        }

        System.out.println(seriePaginas);
        System.out.println(frames);

        //System.out.println("Número de fallos con FIFO: "+FIFO("70120304230321201701", 3));
        //System.out.println("Número de fallos con Optimo: "+Optimo("123351226215763", 4));
        System.out.println("Número de fallos con FIFO: " + FIFO(seriePaginas, frames));
        System.out.println("Número de fallos con Optimo: " + Optimo(seriePaginas, frames));
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
            
        }
        return contPagina;
    }

    public int LRU(String seriePaginas, int frames) {
        int fallos = 0;
        // TODO
        return fallos;
    }

    public static int Optimo(String serieAleatoria, int frames) {
        int fallos = 0;
        int paginaActual = 0;
        boolean find = false;
        String primeraPagina;
        String arregloFrames[] = new String[frames];
        int demoraFrames[] = new int[frames];

        for (int i = 0; i < frames; i++) {
            demoraFrames[i] = 0;
        }

        while (serieAleatoria.length() > 0) {
            primeraPagina = serieAleatoria.substring(0, 1);
            serieAleatoria = serieAleatoria.substring(1, serieAleatoria.length());
            find = false;
            for (int i = 0; i < frames; i++) {
                if (arregloFrames[i] != null && arregloFrames[i].equals(primeraPagina)) {
                    demoraFrames[i] = 0;
                    find = true;
                    break;
                }
            }
            if (!find) {
                // Se busca el frame con la mayor demora
                paginaActual = 0;
                for (int i = 1; i < frames; i++) {
                    if(demoraFrames[i] > demoraFrames[paginaActual]){
                        paginaActual = i;
                    }
                }
                arregloFrames[paginaActual] = primeraPagina;
                fallos++;
                for (int i = 0; i < frames; i++) {
                    if(i != paginaActual){
                        demoraFrames[i]++;
                    }else{
                        demoraFrames[paginaActual] = 0;
                    }
                }
            }
//            String line = "ArregloFrames: ";
//            for (String i : arregloFrames) {
//                line+=i+" ";
//            }
//            System.out.println(line);
//            line = "demoraFrames: ";
//            for (int i : demoraFrames) {
//                line+=i+" ";
//            }
//            System.out.println(line);
        }
        return fallos;
    }
}
