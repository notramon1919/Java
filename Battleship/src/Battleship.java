import java.util.Scanner;

public class Battleship {
    public void run() {
        int opcion, intentos, tamanyx = 10, tamanyy = 10;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Selector de dificultad: ");                                                                 //
        System.out.println("1.- Fàcil");                                                                                //
        System.out.println("2.- Mitjà");                                                                                //  Menú i selecció d'opció que es mostrarà a l'usuari només començar
        System.out.println("3.- Difícil");                                                                              //
        System.out.println("4.- Personalitzat");                                                                        //
        opcion = teclado.nextInt();                                                                                     //
        if (opcion == 4) {
            System.out.println("Mode personalitzat escollit");
            System.out.println("Escolliu la mida de la matriu: ");                                                      //
            System.out.println("x: No major de 26");                                                                    //
            tamanyx = teclado.nextInt();                                                                                //  Si l'opció escollida és la 4 (Personalitzada), la mida de l'array multidimensional es
            while (tamanyx > 26) {                                                                                      //  crearà segons els valors afegits per l'usuari, en cas que no sigui l'opció 4,
                System.out.println("He dit no major de 26");                                                            //  la mida serà la predeterminadada (10x10, línia 5)
                tamanyx = teclado.nextInt();                                                                       //
            }                                                                                                           //
            System.out.println("y: ");                                                                                  //
            tamanyy = teclado.nextInt();                                                                                //
        }                                                                                                               //
        char[][] taula2 = new char[tamanyx][tamanyy];
        char[][] taula3 = new char[tamanyx][tamanyy];
        switch (opcion) {
            case 1:
                intentos = 50;
                nivel(taula2, taula3, opcion, intentos, tamanyx, tamanyy);
                break;
            case 2:
                intentos = 30;
                nivel(taula2, taula3, opcion, intentos, tamanyx, tamanyy);
                break;
            case 3:
                intentos = 10;
                nivel(taula2, taula3, opcion, intentos, tamanyx, tamanyy);
                break;
            case 4:
                System.out.println("Quants intents vols? (Mínim 1)");
                intentos = teclado.nextInt();
                while (intentos==0) {
                    System.out.println("Mínim 1");
                    intentos = teclado.nextInt();
                }
                nivel(taula2, taula3, opcion, intentos, tamanyx, tamanyy);
                break;
        }//del switch
    }//del run
    public void nivel(char[][] taula2, char[][] taula3, int opcion, int intentos, int tamanyx, int tamanyy) {
        int ocup = 0;
        omplirTaula(taula2, tamanyx, tamanyy);
        omplirTaula(taula3, tamanyx, tamanyy);

        if (opcion == 1) ocup = 22;
        else if (opcion == 2) ocup = 13;
        else if (opcion == 3) ocup = 4;

        if (opcion == 4) {
            nivellPers(taula2, taula3, intentos, tamanyx, tamanyy);
            return;
        }

        crearTauler(taula2, opcion, tamanyx, tamanyy);
        mostrarTauler(taula2);

        if (comensarJugar(taula2, taula3, intentos, ocup, tamanyx, tamanyy) == 1) {
            System.out.println("Enhorabona has guanyat!!!");
            return;
        }
        System.out.println("Has perdut! :C");
    }
    public void nivellPers(char[][] taula2, char[][] taula3, int intentos, int tamanyx, int tamanyy) {
        int oport=300;
        int p, z, b, l;
        Scanner tecladoPers = new Scanner(System.in);

        System.out.println("Cuants portavions vols?");
        p = tecladoPers.nextInt();
        System.out.println("Cuants cruissats vols?");
        z = tecladoPers.nextInt();
        System.out.println("Cuants vaixells vols?");
        b = tecladoPers.nextInt();
        System.out.println("Cuantes llanxes vols?");
        l = tecladoPers.nextInt();

        if (insereixVaixells(p, 4, taula2, tamanyx, tamanyy, oport)==0){
            System.out.println("Error al col·locars el vaixells");
            return;
        }
        if (insereixVaixells(z, 3, taula2, tamanyx, tamanyy, oport)==0){
            System.out.println("Error al col·locars el vaixells");
            return;
        }
        if (insereixVaixells(z, 2, taula2, tamanyx, tamanyy, oport)==0){
            System.out.println("Error al col·locars el vaixells");
            return;
        }
        if (insereixVaixells(l, 1, taula2, tamanyx, tamanyy, oport)==0){
            System.out.println("Error al col·locar els vaixells");
            return;
        }

        mostrarTauler(taula2);
        int ocup = (p * 5) + (z * 4) + (b * 3) + (l);
        if (comensarJugar(taula2, taula3, intentos, ocup, tamanyx, tamanyy) == 1) {
            System.out.println("Enhorabona has guanyat!!!");
        }
    }
    public int comensarJugar(char[][] taula2, char[][] taula3, int intentosrest, int ocup, int tamanyx, int tamanyy) {
        Scanner tecladoJuego = new Scanner(System.in);
        boolean salir, sigue=false;
        int valor = 0, y, numx = 0;
        char x, letra='A';
        char []letrasCord = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        while (intentosrest >= 0) {
            System.out.println("Dispara (A-" + letrasCord[tamanyx - 1] + ") " + "(0-" + (tamanyy - 1) + "): ");
            x = tecladoJuego.next().charAt(0);
            y = tecladoJuego.nextInt();
            while (!sigue) {
                salir=false;
                while (!salir) {                    //
                    if (x == letra) {               //
                        salir = true;               //
                    } else {                        //
                        letra++;                    //
                        numx++;                     //
                    }                               //
                }
                if (numx < tamanyx && y < tamanyy) {
                    sigue = true;
                } else {
                    System.out.println("Error. Introduïx una coordenada vàlida: ");
                    x = tecladoJuego.next().charAt(0);
                    y = tecladoJuego.nextInt();
                }
            }
            disparo(numx, y, taula2, taula3);
            if (comprobarVictoria(taula3) == ocup) {
                valor = 1;
                break;
            }
            if (intentosrest == 1) {
                System.out.println("Tens 0 oportunitats restants. Has perdut!");
                break;
            }
            mostrarTauler(taula3);
            intentosrest--;
        }
        return valor;
    }
    public void omplirTaula(char[][] taula, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                taula[i][j] = '-';
            }
        }
    }
    public void crearTauler(char[][] taula, int dif, int tamanyx, int tamanyy) {
        int oport=300;
        switch (dif) {
            case 1:
                insereixVaixells(5, 1, taula, tamanyx, tamanyy, oport);
                insereixVaixells(3, 2, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 3, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 4, taula, tamanyx, tamanyy, oport);
                break;
            case 2:
                insereixVaixells(2, 1, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 2, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 3, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 4, taula, tamanyx, tamanyy, oport);
                break;
            case 3:
                insereixVaixells(1, 1, taula, tamanyx, tamanyy, oport);
                insereixVaixells(1, 2, taula, tamanyx, tamanyy, oport);
                break;
            case 4:
                break;
        }
    }
    public void mostrarTauler(char[][] taula) {
        char car2 = 'A';
        int car = 0;
        System.out.print("\t");
        for (int i = 0; i < taula.length; i++) {
            System.out.print("\t" + car);
            car++;
        }
        System.out.println();
        int i = 0;
        while (i < taula.length) {
            System.out.print("\t" + car2);
            car2++;
            for (int j = 0; j < taula.length; j++) {
                System.out.print("\t" + taula[i][j]);
            }
            System.out.println();
            i++;
        }
    }
    public int insereixVaixells(int x, int y, char[][] taula2, int tamanyx, int tamanyy, int oport) {
        for (int i = x; i > 0; i--) {
            int j = (int) (Math.random() * (tamanyx));
            int z = (int) (Math.random() * (tamanyy));
            if (y == 1) {
                while (taula2[j][z] != '-') {
                    j = (int) (Math.random() * (tamanyx));
                    z = (int) (Math.random() * (tamanyy));
                    oport--;
                    if (oport==0){
                        return oport;
                    }
                }
                taula2[j][z] = 'L';
            }//*****llanxa*****

            if (y == 2) {
                boolean colocable = false;
                while (!colocable) {
                    j = (int) (Math.random() * (tamanyx));
                    z = (int) (Math.random() * (tamanyy));
                    if (z + 3 <= tamanyy && taula2[j][z] == '-' && taula2[j][z + 1] == '-' && taula2[j][z + 2] == '-') {
                        colocable = true;
                    }
                    oport--;
                    if (oport==0){
                        return oport;
                    }
                }
                taula2[j][z] = 'B';
                taula2[j][z + 1] = 'B';
                taula2[j][z + 2] = 'B';
            }//vaixell

            if (y == 3) {
                boolean colocable = false;
                while (!colocable) {
                    j = (int) (Math.random() * (tamanyx));
                    z = (int) (Math.random() * (tamanyy));
                    if (z + 4 <= tamanyy && taula2[j][z] == '-' && taula2[j][z + 1] == '-' && taula2[j][z + 2] == '-' && taula2[j][z + 3] == '-') {
                        colocable = true;
                    }
                    oport--;
                    if (oport==0){
                        return oport;
                    }
                }
                taula2[j][z] = 'Z';
                taula2[j][z + 1] = 'Z';
                taula2[j][z + 2] = 'Z';
                taula2[j][z + 3] = 'Z';
            }//cruiassat

            if (y == 4) {
                boolean colocable = false;
                while (!colocable) {
                    j = (int) (Math.random() * (tamanyx));
                    z = (int) (Math.random() * (tamanyy));
                    if (j + 5 <= tamanyx && taula2[j][z] == '-' && taula2[j + 1][z] == '-' && taula2[j + 2][z] == '-' && taula2[j + 3][z] == '-' && taula2[j + 4][z] == '-') {
                        colocable = true;
                    }
                    oport--;
                    if (oport==0){
                        return oport;
                    }
                }
                taula2[j][z] = 'P';
                taula2[j + 1][z] = 'P';
                taula2[j + 2][z] = 'P';
                taula2[j + 3][z] = 'P';
                taula2[j + 4][z] = 'P';
            }//portavions
        }//for de cantidad de barcos generados
        return oport;
    }
    public void disparo(int x, int y, char[][] taula2, char[][] taula3) {
        if (taula2[x][y] != '-') {
            System.out.println("Tocado");
            taula3[x][y] = 'X';
        } else {
            System.out.println("Agua");
            taula3[x][y] = 'A';
        }
    }
    public int comprobarVictoria(char[][] taula3) {
        int numx = 0;
        int i = 0;
        while (i < taula3.length) {
            for (int j = 0; j < taula3.length; j++) {
                if (taula3[i][j] == 'X') numx++;
            }
            i++;
        }
        return numx;
    }
}// del class
