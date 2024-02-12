import java.util.Arrays;
import java.util.Random;

public class matrixProject {
        public static void main(String[] args) {
            //System.out.print("\033[H\033[2J");  
            //System.out.flush();
            Random random = new Random();
            int gameFieldLength = 7;
            char water = '~';
            char ship3 = '3';
            char ship2 = '2';
            char ship1 = '1';
            char hit = 'h';
            char miss = 'm';
            char sunk = 's';
            char protection = 'p';
            int singleShipNumber = 4;
            int doubleShipNumber = 2;
            int tripleShipNumber = 1;
            char[][] gameBoard = createGameBoard(gameFieldLength,water,ship3,singleShipNumber,doubleShipNumber,tripleShipNumber,random,protection,ship1,ship2);
            printGameBoard(gameBoard,gameFieldLength,protection,water);
           
        }
    
        private static void printGameBoard(char[][] gameBoard,int gameFieldLength,char protection,char water) {
            for (int i = 0;i<gameFieldLength;i++)
            {
                System.out.print(i+1+" ");
                for (int j = 0;j<gameFieldLength;j++)
                {
                    if(gameBoard[i][j]==protection)
                    {
                        System.out.print(water+" ");
                    }
                    else
                    {
                        System.out.print(gameBoard[i][j]+" ");
                    }
                    
                }
                System.out.println();
            }
        }

        private static char[][] createGameBoard(int gameFieldLength, char water, char ship3, int singleShipNumber, int doubleShipNumber, int tripleShipNumber,Random random,char protection,char ship1,char ship2) 
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
            int randomCase = minCase + random.nextInt(maxCase - minCase);
            for(char[] row : gameBoard)
            {
                Arrays.fill(row,water);
            }
            while(placedTripleShips<tripleShipNumber)
            {
                for(int i = 0;i<coordinates.length;i++)
                {
                    coordinates[i] = minBorder + random.nextInt(maxBorder-minBorder);
                }
                if(gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship3;
                    switch (randomCase)
                    {
                        case 1://vertical
                            gameBoard[coordinates[0]+1][coordinates[1]]=ship3;
                            gameBoard[coordinates[0]-1][coordinates[1]]=ship3;
                            if(coordinates[0]==1)
                            {
                                for (int i = 0;i<4;i++)
                                {
                                    gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                                    gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                                }
                                gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                            }
                            else if(coordinates[0]==5)
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
                            if(coordinates[1]==1)
                            {
                                for (int i = 0;i<4;i++)
                                {
                                    gameBoard[coordinates[0]-1][coordinates[1]-1+i]=protection;
                                    gameBoard[coordinates[0]+1][coordinates[1]-1+i]=protection;
                                }
                                gameBoard[coordinates[0]][coordinates[1]+2]=protection;
                            }
                            else if(coordinates[1]==5)
                            {
                                for (int i = 0;i<4;i++)
                                {
                                    gameBoard[coordinates[0]-1][coordinates[1]+1-i]=protection;
                                    gameBoard[coordinates[0]+1][coordinates[1]+1-i]=protection;
                                }
                                gameBoard[coordinates[0]][coordinates[1]-2]=protection;
                            }
                            else
                            {
                                gameBoard[coordinates[0]][coordinates[1]-2]=protection;
                                for (int i = 0;i<5;i++)
                                {
                                    gameBoard[coordinates[0]-1][coordinates[1]-2+i]=protection;
                                    gameBoard[coordinates[0]+1][coordinates[1]-2+i]=protection;
                                }
                                gameBoard[coordinates[0]][coordinates[1]+2]=protection;

                            }

                            break;
                    }
                    placedTripleShips++;
                    
                }
            }
            while (placedDoubleShips<doubleShipNumber)
            {
                for(int i = 0;i<coordinates.length;i++)
                {
                    coordinates[i] =random.nextInt(gameFieldLength);
                }
                //vertical down
                    if ((gameBoard[coordinates[0]][coordinates[1]]==water)&&(coordinates[0]<=5)&&(gameBoard[coordinates[0]+1][coordinates[1]]==water))
                    {
                        gameBoard[coordinates[0]][coordinates[1]]=ship2;
                        gameBoard[coordinates[0]+1][coordinates[1]]=ship2;
                        if(coordinates[0]==0&&coordinates[1]==0)//left upper corner
                        {
                            for(int i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                        }
                        else if(coordinates[0]==5&&coordinates[1]==0)//left lower corner
                        {
                            for(int i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        }
                        else if(coordinates[0]==0&&coordinates[1]==6)//right upper corner
                        {
                            for (int i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]+i][coordinates[1]-1]=protection;
                            }
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                        }
                        else if(coordinates[0]==5&&coordinates[1]==6)//right lower corner
                        {
                            for (int i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        }
                        else if(coordinates[1]==0)//right side
                        {
                            for (int  i = 0;i<4;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;                        
                        }
                        else if(coordinates[1]==6)//left side
                        {
                            for (int  i = 0;i<4;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                        }
                        else if(coordinates[0]==0)//upper side
                        {
                            for (int  i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]+i][coordinates[1]-1]=protection;
                                gameBoard[coordinates[0]+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                        }
                        else if(coordinates[0]==5)//lower side
                        {
                            for (int  i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                                gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        }
                        else //all sides of the ship are wall free
                        {
                            for (int  i = 0;i<4;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                                gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                            }
                            gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                            gameBoard[coordinates[0]+2][coordinates[1]]=protection;
                        }

                        placedDoubleShips++;
                    }
                       
            }
            while (placedSingleShips<singleShipNumber) 
            {
                
                coordinates[0] =  random.nextInt(maxBorder);
                coordinates[1] =  random.nextInt(maxBorder);
                if (gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship1; 
                    if (coordinates[0]==0&&coordinates[1]==0)
                    {
                        gameBoard[coordinates[0]+1][coordinates[1]]=protection;
                        gameBoard[coordinates[0]][coordinates[1]+1]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]+1]=protection;
                    }
                    else if(coordinates[0]==0&&coordinates[1]==6)
                    {
                        gameBoard[coordinates[0]][coordinates[1]-1]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]-1]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]]=protection;
                    }
                    else if(coordinates[0]==6&&coordinates[1]==0)
                    {
                        gameBoard[coordinates[0]][coordinates[1]+1]=protection;
                        gameBoard[coordinates[0]-1][coordinates[1]+1]=protection;
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                    }
                    else if(coordinates[0]==6&&coordinates[1]==6)
                    {
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        gameBoard[coordinates[0]-1][coordinates[1]-1]=protection;
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                    }
                    else if(coordinates[0]==0)
                    {
                        gameBoard[coordinates[0]][coordinates[1]+1]=protection;
                        gameBoard[coordinates[0]][coordinates[1]-1]=protection;
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]+1][coordinates[1]-1+i]=protection;
                        }
                    }
                    else if(coordinates[0]==6)
                    {
                        gameBoard[coordinates[0]][coordinates[1]+1]=protection;
                        gameBoard[coordinates[0]][coordinates[1]-1]=protection;
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]-1][coordinates[1]-1+i]=protection;
                        }   
                    }
                    else if(coordinates[1]==0)
                    {
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]]=protection;
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                        }
                    }
                    else if(coordinates[1]==6)
                    {
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]]=protection;
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                        }
                    }
                    else
                    {
                        gameBoard[coordinates[0]-1][coordinates[1]]=protection;
                        gameBoard[coordinates[0]+1][coordinates[1]]=protection;
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                        } 
                        for(int i = 0;i<3;i++)
                        {
                            gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                        } 
                    }
                    placedSingleShips++;
                }
        }
            return gameBoard;
        }
    }