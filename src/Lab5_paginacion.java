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


        System.out.println("Número de fallos con Optimo: "+Optimo(seriePaginas, frames));
        System.out.println("Número de fallos con FIFO: "+FIFO(seriePaginas, frames));
        System.out.println("Número de fallos con LRU: "+LRU(seriePaginas, frames));
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

    public static int LRU(String serieAleatoria, int frames) {
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

    public static int Optimo(String serieAleatoria, int frames) {
      int fallos = 0;
      int paginaActual = 0;
      boolean find = false;
      boolean insertado = false;
      String primeraPagina;
      String arregloFrames[] = new String[frames];
      int demoraSigUso[] = new int[frames];

      /*
       for (int i = 0; i < serieAleatoria.length(); i++){
       usoPaginas[Integer.parseInt(serieAleatoria.substring(i, i+1))]++;
       }*/
      while (serieAleatoria.length() > 0) {
          primeraPagina = serieAleatoria.substring(0, 1);
          serieAleatoria = serieAleatoria.substring(1, serieAleatoria.length());
          find = false;
          for (int i = 0; i < frames; i++) {
             if (arregloFrames[i] != null && arregloFrames[i].equals(primeraPagina)) {
                  demoraSigUso[i] = 0;
                  find = true;
                  break;
             }
          }
          if (!find) {
             insertado = false;
             for (int i = 0; i < frames; i++) {
                  if (arregloFrames[i] == null) {
                      arregloFrames[i] = primeraPagina;
                      insertado = true;
                      break;
                  }
             }
             if (!insertado) {
                  // Se calcula la demora del siguiente uso de cada página en los frames
                  for (int i = 0; i < frames; i++) {
                      demoraSigUso[i] = 0;
                  }

                  for (int f = 0; f < frames; f++) {
                      int i = 0;
                      while (i < serieAleatoria.length() && !serieAleatoria.substring(i,i+1).equals(arregloFrames[f])) {
                          demoraSigUso[f]++;
                          i++;
                     }
                  }

                  // Se ubica frame con la mayor demora
                  paginaActual = 0;
                  for (int i = 1; i < frames; i++) {
                      if (demoraSigUso[i] > demoraSigUso[paginaActual]) {
                          paginaActual = i;
                      }
                  }

                  // Se realiza la acción correspondiente
                  arregloFrames[paginaActual] = primeraPagina;
             }

             String line = "demoraFrames: ";
            //  for (int i : demoraSigUso) {
            //       line += i + " ";
            //  }
            //  System.out.println(line);

             fallos++;
          }
//            String line = "ArregloFrames: ";
//            for (String i : arregloFrames) {
//                line += i + " ";
//            }
//            System.out.println(line);
//            line = "demoraFrames: ";
//            for (int i : demoraFrames) {
//                line += i + " ";
//            }
//            System.out.println(line);
      }

      return fallos;
    }
}
