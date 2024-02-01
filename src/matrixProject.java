import java.util.Arrays;
import java.util.Random;

public class matrixProject {
        public static void main(String[] args) {
            Random random = new Random();
            int gameFieldLength = 7;
            char water = '~';
            char ship3 = '3';
            char hit = 'h';
            char miss = 'm';
            char protection = 'p';
            int singleShipNumber = 4;
            int doubleShipNumber = 2;
            int tripleShipNumber = 1;
            char[][] gameBoard = createGameBoard(gameFieldLength,water,ship3,singleShipNumber,doubleShipNumber,tripleShipNumber,random,protection);
            for (int i = 0;i<gameFieldLength;i++)
            {
                for (int j = 0;j<gameFieldLength;j++)
                {
                    System.out.print(gameBoard[i][j]+" ");
                }
                System.out.println();
            }
        }
    
        private static char[][] createGameBoard(int gameFieldLength, char water, char ship3, int singleShipNumber, int doubleShipNumber, int tripleShipNumber,Random random,char protection) 
        {
            char[][] gameBoard = new char[gameFieldLength][gameFieldLength];
            int minCase = 1;
            int maxCase = 3;
            int [] coordinates = new int[2];
            int placedTripleShips = 0;
            int placedDoubleShips = 0;
            int placedSingleShips = 0;
            int minBorder = 1;
            int maxBorder = gameFieldLength-1;
            for(char[] row : gameBoard)
            {
                Arrays.fill(row,water);
            }
            while(placedTripleShips<tripleShipNumber)
            {
                // int minBorder = 1;
                // int maxBorder = gameFieldLength-1;
                for(int i = 0;i<coordinates.length;i++)
                {
            // int minBorder = 1;
            // int maxBorder = gameFieldLength-1;
                    coordinates[i] = minBorder + random.nextInt(maxBorder-minBorder);//(b2 - b1 + 1) + b1
                }
                if(gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship3;
                    int randomCase = minCase + random.nextInt(maxCase - minCase);
                    switch (randomCase)
                    {
                        case 1://vertical
                            gameBoard[coordinates[0]+1][coordinates[1]]=ship3;
                            gameBoard[coordinates[0]-1][coordinates[1]]=ship3;
                            if(coordinates[0]-1==0)//set borders to triple ship
                            {
                                for (int i = 0;i<4;i++)
                                {
                                    gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                                    gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                                }
                                gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                            }
                            else if(coordinates[0]+1==6)
                            {
                                gameBoard[coordinates[0]-2][coordinates[1]]=protection;
                                for (int i = 0;i<4;i++)
                                {
                                    gameBoard[coordinates[0]-2+i][coordinates[1]+1]=protection;
                                    gameBoard[coordinates[0]-2+i][coordinates[1]-1]=protection;
                                }
                                
                            }
                            else
                            {
                                gameBoard[coordinates[0]-2][coordinates[1]]=protection;
                                for (int i = 0;i<5;i++)
                                {
                                    gameBoard[coordinates[0]-2+i][coordinates[1]+1]=protection;
                                    gameBoard[coordinates[0]-2+i][coordinates[1]-1]=protection;
                                }
                                gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                            }
                            break;                  
                        case 2://horizontal
                            gameBoard[coordinates[0]][coordinates[1]+1]=ship3;
                            gameBoard[coordinates[0]][coordinates[1]-1]=ship3;
                            break;
                    }
                    placedTripleShips++;
                    
                }
            }
            // while (placedDoubleShips<doubleShipNumber)
            // {
            //     for (int i = 0;i<coordinates.length;i++)
            //     {
            //         coordinates[i] = random.nextInt(((gameFieldLength-2)-0+1)+0);
            //     }
            // }

            return gameBoard;
        }
    }

