import java.util.Arrays;
import java.util.Random;

public class matrixProject {
        public static void main(String[] args) {
            int gameFieldLength = 7;
            char water = '~';
            char ship = 's';
            char hit = 'h';
            char miss = 'm';
            int singleShipNumber = 4;
            int doubleShipNumber = 2;
            int tripleShipNumber = 1;
            char[][] gameBoard = createGameBoard(gameFieldLength,water,ship,singleShipNumber,doubleShipNumber,tripleShipNumber);
        }
    
        private static char[][] createGameBoard(int gameFieldLength, char water, char ship, int singleShipNumber, int doubleShipNumber, int tripleShipNumber) 
        {
            char[][] gameBoard = new char[gameFieldLength][gameFieldLength];
            for(char[] row : gameBoard)
            {
                Arrays.fill(row,water);
            }
            return placeShips(gameFieldLength,gameBoard,water,ship,singleShipNumber,doubleShipNumber,tripleShipNumber);
        }
    
        private static char[][] placeShips(int gameFieldLength, char[][] gameBoard, char water, char ship, int singleShipNumber, int doubleShipNumber, int tripleShipNumber) 
        {
            int placedTripleShips = 0;
            while(tripleShipNumber<1)
            {
                int [] coordinates = new int[2];
                for(int i = 0;i<coordinates.length;i++)
                {
                    coordinates[i] = new Random().nextInt(((gameFieldLength-1)-0+1)+0);//(b2 - b1 + 1) + b1
                }
                if(gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship;
                    // if((coordinates[0]==0&&(coordinates[1]==0||coordinates[1]==6))||(coordinates[0]==6&&(coordinates[1]==0||coordinates[1]==6)))//corners
                    // {
    
                    // }
                    int randomCaseToPlaceTripleShip = new Random().nextInt();//add cases number as border
                }
    
            }
            int placedSingleShips = 0;
            while (placedSingleShips<singleShipNumber)
            {
                int[] coordinates = new int[1];
                for (int i = 0;i<coordinates.length;i++)
                {
                    coordinates[i] = new Random().nextInt(gameFieldLength-1);
                }
                if(gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship;
                    placedSingleShips++;
                }
            }
            int placedDoubleShips = 0;
            while (doubleShipNumber>placedDoubleShips)
            {
                
            }
        }
    }

