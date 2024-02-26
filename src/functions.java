import java.util.*;

public class functions {

    public static int timeElapsed = 0;
    private static Timer timer;
    private static TimerTask runningGame;


    public static void setTimer() {                                                                              //TIMER ITT VAN.
        timer = new Timer();
        runningGame = new TimerTask() {
            @Override
            public void run() {
                timeElapsed++;
            }
        };
        timer.schedule(runningGame, 0, 1000);
    }

    public static void stopTimer() {
        timer.cancel();
        runningGame.cancel();

    }

    //8*8 = 60
//16*16 = 238
    //32*32=656
//(xside * yside) /time
    public static int score(int[][] hiddenBoard) {
        double sum = 0;
        for (int i = 1; i < hiddenBoard.length - 1; i++) {
            for (int j = 1; j < hiddenBoard[i].length - 1; j++) {
                if (hiddenBoard[i][j] != 9) {
                    sum += hiddenBoard[i][j];
                }
            }
        }
        sum-=8;       //A 8-at hozzá kellene adni alapból, mert 1 db 8-as minimum lesz
        return (int) ((sum *3000 )/ timeElapsed);
    }


    /**
     * Első koordináta bekérés
     *
     * @return
     */
    public static int[] firstClick(int ySide, int xSide) {
        int[] firstClick = new int[2];
        Scanner sc = new Scanner(System.in);
        boolean isWrongInputX = true;
        boolean isWrongInputY = true;

        while (isWrongInputX) {             //X koordináta vizsgálata
            try {
                System.out.println("Válassz oszlopot!(add meg az X értékét)");
                firstClick[1] = sc.nextInt();
            } catch (InputMismatchException f) {
                System.out.println("Nem számot adtál meg koordinátának!");
                sc.next();                                          // ez átírja a bemenetet, enélkül végtelen loop
                continue;
            }

            if (firstClick[1] < 1 || firstClick[1] > ySide - 2) {
                System.out.println("A táblán kívül van a megadott oszlop!");
            } else {
                isWrongInputX = false;
            }
        }

        while (isWrongInputY) {             //Y koordináta vizsgálata
            try {
                System.out.println("Válassz sort!(add meg az Y értékét)");
                firstClick[0] = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Nem számot adtál meg koordinátának!");
                sc.next();                  // ez átírja a bemenetet, enélkül végtelen loop
                continue;
            }

            if (firstClick[0] < 1 || firstClick[0] > xSide - 2) {
                System.out.println("A táblán kívül van a megadott sor!");
            } else {
                isWrongInputY = false;
            }
        }
        return firstClick;
    }

    /**
     * Lépés bekérés, ismétlődő
     */
    public static String[] click(int ySide, int xSide, char[][] playerBoard) {            //ha már vmi be van írva, azt ne lehessen felülírni, kivéve removeFlag!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String[] click = new String[3];
        Scanner sc = new Scanner(System.in);
        boolean isWrongInputX = true;
        boolean isWrongInputY = true;
        boolean isAlreadyVisible = true;

        do {
            while (isWrongInputX) {             //X koordináta vizsgálata
                try {
                    System.out.println("Válassz oszlopot!(add meg az X értékét)");
                    click[1] = sc.nextLine();
                    Integer.parseInt(click[1]);       //csak akkor működik, ha számot ír be
                } catch (NumberFormatException f) {
                    System.out.println("Nem számot adtál meg koordinátának!");
                    System.out.println();
                    continue;
                }
                int choiceX = Integer.parseInt(click[1]);

                if (choiceX < 1 || choiceX > ySide - 2) {
                    System.out.println("A táblán kívül van a megadott oszlop!");
                    System.out.println();
                } else {
                    isWrongInputX = false;
                }
            }

            while (isWrongInputY) {             //Y koordináta vizsgálata
                try {
                    System.out.println("Válassz sort!(add meg az Y értékét)");
                    click[0] = sc.nextLine();
                    Integer.parseInt(click[0]);       //csak akkor működik, ha számot ír be
                } catch (NumberFormatException e) {
                    System.out.println("Nem számot adtál meg koordinátának!");
                    continue;
                }

                int choiceY = Integer.parseInt(click[0]);

                if (choiceY < 1 || choiceY > xSide - 2) {
                    System.out.println("A táblán kívül van a megadott sor!");
                } else {
                    isWrongInputY = false;
                }
            }

            /* XY pont fel vizsgálata, hogy fel van-e már fejtve */
            int choiceY = Integer.parseInt(click[0]);
            int choiceX = Integer.parseInt(click[1]);
            if (playerBoard[choiceY][choiceX] == 'F' || playerBoard[choiceY][choiceX] == '_') {
                isAlreadyVisible = false;
            } else {
                isWrongInputX = true;
                isWrongInputY = true;
                System.out.println("Ez a mező már látható, válassz egy új még nem láthatót!");
                System.out.println();
            }
        } while (isAlreadyVisible);


        System.out.println("Ha meg akarod jelölni, nyomj egy F-et!");
        click[2] = sc.nextLine().toUpperCase();
        return click;
    }

    public static void gameOver(int yChoice, int xChoice, int[][] hiddenResult, char[][] gameOverBoard) {
        if (hiddenResult[yChoice][xChoice] == 9) {
            for (int i = 1; i < hiddenResult.length - 1; i++) {
                for (int j = 1; j < hiddenResult[i].length - 1; j++) {
                    if (hiddenResult[i][j] == 9) {
                        gameOverBoard[i][j] = '*';
                    }
                }
            }
        }
    }

    public static void showNumber(int yChoice, int xChoice, int[][] hiddenResult, char[][] board) {
        if (hiddenResult[yChoice][xChoice] != 9 && hiddenResult[yChoice][xChoice] != 0) {
            board[yChoice][xChoice] = Character.forDigit(hiddenResult[yChoice][xChoice], 10);
        }
    }


    public static boolean isFinished(char[][] board, int numberOfMines) {
        int count = 0;
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == '_' || board[i][j] == 'F') {
                    count++;
                }
            }
        }
        return count == numberOfMines;

    }

    public static char[][] flagsAfterWin(char[][] board) {
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = 'F';
                }
            }
        }
        return board;
    }


    public static void createFlag(int xCoord, int yCoord, char[][] board) {
        if (board[xCoord][yCoord] == '_') {
            board[xCoord][yCoord] = 'F';
        }
    }

    public static void removeFlag(int xCoord, int yCoord, char[][] board) {
        if (board[xCoord][yCoord] == 'F') {
            board[xCoord][yCoord] = '_';
        }
    }

    public static void clearScreen() {          //Windows CMD-n és IDE terminálon nem működik
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int[] gameLevel() {         //bemenet megfelelőségének biztosítása
        boolean correctInput = false;
        int[] chosenLevel = new int[3];
        int[] levelEasy = new int[]{10, 10, 10};       //easy + 2-2 a méreteknél
        int[] levelMedium = new int[]{18, 18, 40};
        int[] levelHard = new int[]{18, 34, 50};
        int[] levelCustom = new int[3];

        while (!correctInput) {
            System.out.println("Válassz nehézségi szintet!");
            System.out.println("1 - Easy (8x8, 10 akna)");
            System.out.println("2 - Medium (16x16, 40 akna)");
            System.out.println("3 - Die hard (32x16, 50 akna)");
            System.out.println("4 - Egyéni, te határozhatod meg a pálya adatait.");
            Scanner dc = new Scanner(System.in);
            int level = dc.nextInt();
            //ySide, xSide, mines

            if (level > 4 || level < 1) {
                System.out.println();
                System.out.println("Hibás nehézségi szint!");
                System.out.println();
            } else if (level == 1) {
                chosenLevel = levelEasy;
                correctInput = true;
            } else if (level == 2) {
                chosenLevel = levelMedium;
                correctInput = true;
            } else if (level == 3) {
                chosenLevel = levelHard;
                correctInput = true;
            } else if (level == 4) {
                boolean isPlayable = false;
                boolean isBigEnough = false;
                while (!isBigEnough) {
                    while (!isPlayable) {
                        try {
                            System.out.println("Add meg a pálya magasságát!");
                            Scanner sc = new Scanner(System.in);
                            levelCustom[0] = Math.abs(sc.nextByte() + 2);
                        } catch (Exception e) {
                            System.out.println("Nem jó paraméter!");
                            continue;
                        }
                        isPlayable = true;
                    }
                    isPlayable = false;
                    while (!isPlayable) {
                        try {
                            System.out.println("Add meg a pálya szélességét!");
                            Scanner sc = new Scanner(System.in);
                            levelCustom[1] = Math.abs(sc.nextByte() + 2);
                        } catch (Exception e) {
                            System.out.println("Nem jó paraméter!");
                            continue;
                        }
                        isPlayable = true;
                    }
                    if ((levelCustom[0] - 2) * (levelCustom[1] - 2) < 20) {
                        System.out.println("Túl kicsi a pálya, min. 20 mezőből kell állnia!");
                        isPlayable = false;
                    } else {
                        isBigEnough = true;
                    }
                }
                isPlayable = false;
                while (!isPlayable) {
                    try {
                        System.out.println("Add meg az aknák számát");
                        Scanner sc = new Scanner(System.in);
                        levelCustom[2] = Math.abs(sc.nextByte());
                    } catch (Exception e) {
                        System.out.println("Nem jó paraméter!");
                        continue;
                    }
                    int mineLimit = (levelCustom[0] - 2) * (levelCustom[1] - 2) - 9;
                    if (levelCustom[2] > mineLimit) { //8 szomszédja van egy mezőnek, ezért maximum annyi akna lehet a táblán, amennyitől még körbe tudja vizsgálni egy mező szomszédjait a program
                        System.out.println("Túl sok akna, max. " + mineLimit + " db lehet!");
                    } else {
                        isPlayable = true;
                    }
                }
                chosenLevel = levelCustom;
                correctInput = true;
            }
        }
        return chosenLevel;
    }

    /**
     * Ha valaki csalni akar, akkor annak felfedi az összes bombát, csak annyit kell beírni a 3. inputba, hogy "cheat"
     *
     * @param board
     * @param hiddenResult
     */
    public static char[][] cheat(char[][] board, int[][] hiddenResult) {
        char[][] cheatBoard = new char[board.length][board[0].length];
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if ((board[i][j] == '_' && hiddenResult[i][j] == 9) || (board[i][j] == 'F' && hiddenResult[i][j] == 9)) {
                    cheatBoard[i][j] = '*';
                } else {
                    cheatBoard[i][j] = board[i][j];
                }
            }
        }
        return cheatBoard;
    }
}
