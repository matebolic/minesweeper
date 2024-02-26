import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class board {
    public static void gameRules() {
        System.out.println(scoreColor.BLUE_BOLD + "AKNAKERESŐ - MINESWEEPER" + scoreColor.RESET);
        System.out.println("1.01. version - 2023");
        System.out.println(scoreColor.RED_BOLD + "created by: m&&m&&m's" + scoreColor.RESET);
        System.out.println(scoreColor.RED + "M" + scoreColor.RESET + "arkovics Olivér - " + scoreColor.RED + "M" + scoreColor.RESET + "áté Balázs - " + scoreColor.RED + "M" + scoreColor.RESET + "ester Péter" + scoreColor.RESET);
        System.out.println();
        System.out.println(scoreColor.WHITE_UNDERLINED + "A játék célja:" + scoreColor.RESET);
        System.out.println("Találd meg a táblán lévő aknákat!" +
                "\n" +
                "Ehhez minden olyan mezőt fel kell fedned, amin nincs akna. Ha ez sikerül, akkor nyertél." +
                "\n" +
                "De vigyázz! Ha egyszer egy aknát rejtő mezőt fedsz fel, akkor vége a játéknak!" +
                "\n" + "\n" +
                scoreColor.WHITE_UNDERLINED + "A szabályok a következők: " + scoreColor.RESET +
                "\n" +
                "1. Minden lépésben meg kell adnod a kiválasztott mező oszlopát és sorát." +
                "\n" +
                "2. A pályán látható számok azt jelzik, hogy egy-egy mezőnek hány szomszédján (függőlegesen, vízszintesen és átlósan)" +
                "\n" +
                "található akna. A '0' azt jelzi, hogy a mező szomszédjaiban nem található akna." +
                "\n" +
                "2. Miután megadtad mező helyét, el kell döntened, hogy fel akarod-e fedni, vagy meg akarod-e jelölni azt egy zászlóval." +
                "\n" +
                "Amennyiben úgy gondolod, hogy a mező alatt akna található, akkor nyomj egy F-et." +
                "\n" +
                "3. A játékban annyi zászló áll a rendelkezésedre, amennyi akna található. Ha vissza akarod venni a zászlót," +
                "\n" +
                "akkor jelöld meg a mezőt, majd ismételten adj rá jelölést, ezzel a zászló eltűnik így fel fogod tudni fedni a mezőt egy másik lépésben." +
                "\n" +
                "4. A játék végén pontozzuk a teljesítményed a pálya nehézsége és a kitöltés gyorsasága szerint." +
                "\n" +
                "Légy óvatos!"
        );


        System.out.println();
        System.out.println(scoreColor.RED + "Kezdhetjük a játékot? Ha igen, nyomj egy billentyűt és egy ENTERT!" + scoreColor.RESET);
        Scanner sc = new Scanner(System.in);
        String start = sc.next();
        functions.clearScreen();
    }

    /**
     * Rejtett tábla, amit a játékos nem lát
     *
     * @param xSide
     * @param ySide
     * @param chosenCoordinateX
     * @param chosenCoordinateY
     * @return
     */

    public static int[][] hiddenBoard(int numberOfMines, int xSide, int ySide, int chosenCoordinateX, int chosenCoordinateY) {
        int[][] hiddenBoard = createNullBoard(xSide, ySide);
        int mineCreated = 0;
        while (mineCreated < numberOfMines) {
            int randX = ThreadLocalRandom.current().nextInt(1, xSide - 1);      //creating random coordinates
            int randY = ThreadLocalRandom.current().nextInt(1, ySide - 1);      //creating random coordinates
            try {

                if (((((Math.abs(randX - chosenCoordinateX)) > 1)
                        || ((Math.abs(randY - chosenCoordinateY)) > 1)) && hiddenBoard[randX][randY] != 9)) {    //******** X és Y felcserélve!
                    //az első lépés szomszédos mezőire, és arra a mezőre, ahol már van akna nem rak aknát
                    hiddenBoard[randX][randY] = 9;              //******** X és Y felcserélve!
                    mineCreated++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hiba");
            }
        }
        //Eddig legenerálódnak a csillagok

        // számértékek megjelenítése
        for (int i = 1; i < hiddenBoard.length - 1; i++) {                   //2-vel csökkentett határok
            for (int j = 1; j < hiddenBoard[i].length - 1; j++) {
                if (hiddenBoard[i][j] == 9) {                                //jobb átlátás/ellenőrzés miatt a "9"-es a "*"
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (hiddenBoard[k][l] != 9) {
                                hiddenBoard[k][l]++;
                            }
                        }
                    }
                }
            }
        }
        hiddenBoard[chosenCoordinateX][chosenCoordinateY] = 8;  //******** X és Y felcserélve!
        return hiddenBoard;
    }


    /**
     * Megrajzolja a látható táblát
     *
     * @param board
     */
    public static void drawBoard(char[][] board) {
        int columnCounter = 0;
        int rowCounter = 0;
        System.out.print("  ");         //2 karakter - számozás elcsúszása miatt******************!!!

        for (int i = 0; i < board[0].length - 2; i++) {            //Számok kiírása az oszlopok fölé
            columnCounter++;
            System.out.print(columnCounter + (columnCounter > 9 ? "  " : "   "));  //10 oszlop felett mínusz egy szóköz, mert különben elcsúszna a számozás az oszlopoktól
        }
        System.out.println();
        System.out.println();
        for (int i = 1; i < board.length - 1; i++) {
            //A vízszintes vonalak, sarkok kiírásának helye
            System.out.println(unicodeChar.tableRows(board.length, board[i].length - 2, i));

            for (int j = 1; j < board[0].length - 1; j++) {
                System.out.print(unicodeChar.VV_N_TABLE + " ");         //a számok mellé kerülő függőlegesek
                System.out.print(scoreColor.color(board[i][j]) + board[i][j] + scoreColor.RESET);
                System.out.print(" ");                           //a számok közé kerülő szóközök
                if (j == board[0].length - 2) {                  //sor végén a számok kiírása az sorok mellé
                    rowCounter++;
                    System.out.print(unicodeChar.VV_N_TABLE + "    " + rowCounter);     //5 karakter eltolás helyett 4+1 !******************!!!
                    System.out.println();
                } else {
                    System.out.print("");               //a mezők elválasztása egymástól a sorokban
                }

                if (i == board.length - 2 && j == board[i].length - 2) { // utolsó sor alatti vonalak, az utolsó elem után kiiratva
                    i++;
                    System.out.println(unicodeChar.tableRows(board.length, board[i].length - 2, i));
                    System.out.println();
                }
            }
        }
    }

    /**
     * Megrajzolja a rejtett táblát
     *
     * @param board
     */

    public static void drawBoard(int[][] board) {
        for (int i = 1; i < board.length - 1; i++) {                //a keret (első és utolsó sor, ill. oszlop) nem kerül megjelenítésre
            System.out.println();
            for (int j = 1; j < board[i].length - 1; j++) {
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    /**
     * @param chosenCoordinateX - a kiválasztott koordináta x értéke
     * @param chosenCoordinateY - a kiválasztott koordináta y értéke
     * @param hiddenResult      - a rejtett tábla
     * @param board             - gameboard amit a játékos lát
     */
    public static void emptyField(int chosenCoordinateX, int chosenCoordinateY, int[][] hiddenResult, char[][] board) {
        board[chosenCoordinateX][chosenCoordinateY] = '0';                      //******** X és Y felcserélve!

        List<Integer> listEarlierPointsX = new ArrayList<>();              //Lista létrehozása X-re
        List<Integer> listEarlierPointsY = new ArrayList<>();
        listEarlierPointsX.add(chosenCoordinateX);                              //Első megadott X koordináta hozzáadása
        listEarlierPointsY.add(chosenCoordinateY);                              //Első megadott Y koordináta hozzáadása

        for (int i = chosenCoordinateX - 1; i <= chosenCoordinateX + 1; i++) {      //******** X és Y felcserélve!
            for (int j = chosenCoordinateY - 1; j <= chosenCoordinateY + 1; j++) {

                boolean withinBorders = i > 0 && j > 0 && i < hiddenResult.length - 1 && j < hiddenResult[i].length - 1;
                boolean lastNeighbourCheck = i == chosenCoordinateX + 1 && j == chosenCoordinateY + 1;
                boolean yesBombNeighbour = hiddenResult[i][j] != 0 && hiddenResult[i][j] != 9 && board[i][j] == '_';
                boolean noBombNeighbour = hiddenResult[i][j] == 0 && board[i][j] == '_';
                boolean alreadyVisible = board[i][j] != '_';
                boolean onlyOneZeroCoordinate = listEarlierPointsX.size() <= 1;


                if (noBombNeighbour && withinBorders) {                             //ha 0-t talál
                    board[i][j] = '0';
                    chosenCoordinateX = i;      //******** X és Y felcserélve!
                    chosenCoordinateY = j;      //koordináta-változtatás
                    listEarlierPointsX.add(chosenCoordinateX);                              //új X koordináta hozzáadása
                    listEarlierPointsY.add(chosenCoordinateY);                              //új Y koordináta hozzáadása
                    i = chosenCoordinateX - 1;                                              //az i, j-t itt kell meghatározni, különben nem jó!!!!!
                    j = chosenCoordinateY - 2;                                              // A j azért -2 mert a j++ alapból hozzáad

                } else if (yesBombNeighbour && withinBorders) {                      //ha számot talál, ami nem 0
                    board[i][j] = Character.forDigit(hiddenResult[i][j], 10);
                    board[chosenCoordinateX][chosenCoordinateY] = '0';                       //******** X és Y felcserélve!
                    if (lastNeighbourCheck) {
                        if (onlyOneZeroCoordinate) {
                            continue;
                        }
                        listEarlierPointsX.remove(listEarlierPointsX.size() - 1);         //kivenni a legutolsó X listelemet
                        listEarlierPointsY.remove(listEarlierPointsY.size() - 1);         //kivenni a legutolsó Y listelemet

                        chosenCoordinateX = listEarlierPointsX.get(listEarlierPointsX.size() - 1);  //koordináta-változtatás
                        chosenCoordinateY = listEarlierPointsY.get(listEarlierPointsY.size() - 1);  //koordináta-változtatás
                        i = chosenCoordinateX - 1;
                        j = chosenCoordinateY - 2;
                    }

                } else if (alreadyVisible && lastNeighbourCheck) {            //ha már felfedett mezőt talál
                    if (onlyOneZeroCoordinate) {
                        continue;
                    }
                    listEarlierPointsX.remove(listEarlierPointsX.size() - 1);         //kivenni a legutolsó X listelemet
                    listEarlierPointsY.remove(listEarlierPointsY.size() - 1);         //kivenni a legutolsó Y listelemet

                    chosenCoordinateX = listEarlierPointsX.get(listEarlierPointsX.size() - 1);  //koordináta-változtatás
                    chosenCoordinateY = listEarlierPointsY.get(listEarlierPointsY.size() - 1);  //koordináta-változtatás
                    i = chosenCoordinateX - 1;
                    j = chosenCoordinateY - 2;

                } else if (!withinBorders && lastNeighbourCheck) {      //ha a látható táblán kívülre esik az szomszéd-vizsgálat utolsó lépése
                    if (onlyOneZeroCoordinate) {
                        continue;
                    }
                    listEarlierPointsX.remove(listEarlierPointsX.size() - 1);         //kivenni a legutolsó X listelemet
                    listEarlierPointsY.remove(listEarlierPointsY.size() - 1);         //kivenni a legutolsó Y listelemet

                    chosenCoordinateX = listEarlierPointsX.get(listEarlierPointsX.size() - 1);  //koordináta-változtatás
                    chosenCoordinateY = listEarlierPointsY.get(listEarlierPointsY.size() - 1);  //koordináta-változtatás
                    i = chosenCoordinateX - 1;
                    j = chosenCoordinateY - 2;
                }
            }
        }
    }


    /**
     * Létrehozza a táblát
     *
     * @param xSide
     * @param ySide
     * @return
     */
    public static char[][] createEmptyBoard(int xSide, int ySide) {
        char[][] board = new char[xSide][ySide];                            //******** X és Y felcserélve!
        for (char[] chars : board) {                                        //eredetileg két for ciklus volt itt
            Arrays.fill(chars, '_');
        }
        return board;
    }

    /**
     * Nullákkal feltölti a táblázatot
     *
     * @param xSide
     * @param ySide
     * @return
     */
    public static int[][] createNullBoard(int xSide, int ySide) {
        int[][] nullBoard = new int[xSide][ySide];                          //******** X és Y felcserélve!
        for (int[] ints : nullBoard) {                                      //eredetileg két for ciklus volt itt
            Arrays.fill(ints, 0);
        }
        return nullBoard;
    }
}
