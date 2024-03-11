import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class matrixProject {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
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
            int[][] tripleShipCoordinates = new int[3][2];
            char[][] gameBoard = createGameBoard(gameFieldLength,water,ship3,random,protection,ship1,ship2,tripleShipCoordinates);

            ArrayList<Integer> doubleShipLocations = identifyAllDoubleShipLocations(gameBoard,gameFieldLength,ship2);
            //ArrayList<Integer> identifyAllTripleShipLocations = identifyAllTripleShipLocations(gameBoard,  gameFieldLength, ship3);
            printGameBoard(gameBoard,gameFieldLength,protection,water);
            getCheckUserShotAndUpdataGameboard(gameBoard,gameFieldLength,water,ship1,ship2,ship3,hit,miss,sunk,scan,doubleShipLocations,tripleShipCoordinates);
            //String nextgame = scan.nextLine();

            // while (nextgame=="y")
            // {
            //     gameBoard = createGameBoard(gameFieldLength,water,ship3,random,protection,ship1,ship2);
            //     printGameBoard(gameBoard,gameFieldLength,protection,water);
            //     getCheckUserShotAndUpdataGameboard(gameBoard,gameFieldLength,water,ship1,ship2,ship3,hit,miss,sunk,scan);
            //     nextgame = scan.nextLine();
            // }
        }
    
        private static ArrayList<Integer> identifyAllDoubleShipLocations(char[][] gameBoard, int gameFieldLength,char ship2) {
            ArrayList <Integer> doubleShipLocations = new ArrayList<>();
            for (int i = 0;i<gameFieldLength;i++)
            {
                for (int j = 0;j<gameFieldLength;j++)
                {
                    if(gameBoard[i][j]==ship2)
                    {
                        doubleShipLocations.add(i);
                        doubleShipLocations.add(j);
                    }
                }
            }
            Collections.sort(doubleShipLocations);
            return doubleShipLocations;
        }
        // private static ArrayList<Integer> identifyAllTripleShipLocations(char[][] gameBoard, int gameFieldLength,char ship3) {
        //     ArrayList <Integer> doubleShipLocations = new ArrayList<>();
        //     for (int i = 0;i<gameFieldLength;i++)
        //     {
        //         for (int j = 0;j<gameFieldLength;j++)
        //         {
        //             if(gameBoard[i][j]==ship3)
        //             {
        //                 doubleShipLocations.add(i);
        //                 doubleShipLocations.add(j);
        //             }
        //         }
        //     }
            
        //     return doubleShipLocations;
        // }

        private static void getCheckUserShotAndUpdataGameboard(char[][] gameBoard, int gameFieldLength, char water,  char ship1, char ship2, char ship3, char hit, char miss, char sunk, Scanner scan, ArrayList<Integer> doubleShipLocations, int[][] tripleShipCoordinates) {
            int[] userShot = new int[2];
            int shipNum = 11;
            int allShots = 0;
            int tripleShipDetected = 0;
            ArrayList<Integer> doubleShipDetected = new ArrayList<>();
            while (shipNum>0)
            {
                    System.out.println("enter coordinate X:");//get user shot
                    userShot[1] = scan.nextInt();
                    userShot[1]--;
                    System.out.println("enter coordinate Y:");
                    userShot[0] = scan.nextInt();
                    userShot[0]--;
                    allShots++;
                    while(((userShot[0]>7)||(userShot[1]>7))||((userShot[0]<0)||(userShot[1]<0)))
                    {
                        System.out.println("Range is from 1 to 7"+"\n"+"enter coordinate X:");//get user shot
                        userShot[1] = scan.nextInt();
                        userShot[1]--;
                        System.out.println("enter coordinate Y:");
                        userShot[0] = scan.nextInt();
                        userShot[0]--;
                    }
                    
                    if(gameBoard[userShot[0]][userShot[1]]==ship3)//check user shot
                    {
                        gameBoard[userShot[0]][userShot[1]] = hit;
                        tripleShipDetected++;
                        shipNum--;
                        // if(tripleShipDetected==3)
                        // {
                        //     gameBoard[]
                        // }
                    }
                    else if(gameBoard[userShot[0]][userShot[1]]==ship2)
                    {
                        gameBoard[userShot[0]][userShot[1]] = hit;
                        shipNum--;
                    }
                    else if(gameBoard[userShot[0]][userShot[1]]==ship1)
                    {
                        gameBoard[userShot[0]][userShot[1]] = sunk;
                        shipNum--;
                    }
                    else
                    {
                        gameBoard[userShot[0]][userShot[1]] = miss;
                    }
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    System.out.print("  ");
                    for (int i = 0;i<7;i++)//update game board
                    {
                        System.out.print(i+1+" ");
                    }
                    System.out.println();
                    for (int i = 0;i<gameFieldLength;i++)
                    {
                        
                        System.out.print(i+1+" ");
                        for (int j = 0;j<gameFieldLength;j++)
                        {
                            
                            if((gameBoard[i][j]!=water)&&(gameBoard[i][j]!=hit)&&(gameBoard[i][j]!=miss)&&(gameBoard[i][j]!=sunk)&&(gameBoard[i][j]!=miss))
                            {
                                System.out.print(water+" ");  
                            }
                            else{
                                System.out.print(gameBoard[i][j]+" ");
                            }
                                 
                        } 
                        System.out.println();
                    }
            }
            System.out.println("Game over.You won!"+"\n"+"All shots number:" + allShots+"\n"+"Do you want to start over?Type \"y\" for yes and \"n\" for no");
            String nextgame = scan.nextLine();
            String nextGameCompare = "y";
            if (Objects.equals(nextgame,nextGameCompare))
            {
                String[] str = null;
                main(str);
            }
        }
        

        private static void printGameBoard(char[][] gameBoard,int gameFieldLength,char protection,char water) {
            System.out.print("  ");
            for (int i = 0;i<7;i++)
            {
                System.out.print(i+1+" ");
            }
            System.out.print(" "+"\n");
            for (int i = 0;i<gameFieldLength;i++)
            {
                System.out.print(i+1+" ");
                for (int j = 0;j<gameFieldLength;j++)
                {
                    if(gameBoard[i][j]!=water)
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

        private static char[][] createGameBoard(int gameFieldLength, char water, char ship3, Random random,char protection,char ship1,char ship2, int[][] tripleShipCoordinates) 
        {
            char[][] gameBoard = new char[gameFieldLength][gameFieldLength];
            int [] coordinates = new int[2];
            int placedTripleShips = 0;
            int placedDoubleShips = 0;
            int placedSingleShips = 0;
            int randomCase = 1 + random.nextInt(3 - 1);
            for(char[] row : gameBoard)
            {
                Arrays.fill(row,water);
            }
            while(placedTripleShips<1)
            {
                for(int i = 0;i<coordinates.length;i++)
                {
                    coordinates[i] = 1 + random.nextInt(6-1);
                }
                if(gameBoard[coordinates[0]][coordinates[1]]==water)
                {
                    gameBoard[coordinates[0]][coordinates[1]]=ship3;
                    switch (randomCase)
                    {
                        case 1://vertical
                            gameBoard[coordinates[0]+1][coordinates[1]]=ship3;
                            gameBoard[coordinates[0]-1][coordinates[1]]=ship3;
                            for (int j = 0;j<3;j++)//save coordinates to change from hit to sunk
                            {
                                tripleShipCoordinates[j][0] = coordinates[0]-1+j;//y coord
                            }
                            for (int j = 0;j<3;j++)
                            {
                                tripleShipCoordinates[j][1] = coordinates[1];//x coord
                            }
                            for(int i = 0;i<3;i++)
                            {
                                gameBoard[coordinates[0]-1+i][coordinates[1]+1]=protection;
                                gameBoard[coordinates[0]-1+i][coordinates[1]-1]=protection;
                                
                                if(coordinates[0]==1)
                                {                                 
                                    gameBoard[coordinates[0]+2][coordinates[1]-1+i]=protection;//down side of ship
                                }
                                else if(coordinates[0]==5)
                                {
                                    gameBoard[coordinates[0]-2][coordinates[1]-1+i]=protection;//upper side of ship
                                }
                                else
                                {
                                    gameBoard[coordinates[0]+2][coordinates[1]-1+i]=protection;
                                    gameBoard[coordinates[0]-2][coordinates[1]-1+i]=protection;
                                }
                            }                           
                            break;                 
                        case 2://horizontal
                            gameBoard[coordinates[0]][coordinates[1]+1]=ship3;
                            gameBoard[coordinates[0]][coordinates[1]-1]=ship3;
                            for (int j = 0;j<3;j++)
                            {
                                tripleShipCoordinates[j][0] = coordinates[0];//y coord
                            }
                            for (int j = 0;j<3;j++)
                            {
                                tripleShipCoordinates[j][1] = coordinates[1]-1+j;//x coord
                            }
                            for(int i = 0;i<3;i++)
                            {
                                if(coordinates[1]==1)
                                {
                                    gameBoard[coordinates[0]-1+i][coordinates[1]+2]=protection;//right side of the ship
                                }
                                else if(coordinates[1]==5)
                                {
                                    gameBoard[coordinates[0]-1+i][coordinates[1]-2]=protection;//left side of the ship
                                }
                                else
                                {
                                    gameBoard[coordinates[0]-1+i][coordinates[1]-2]=protection;
                                    gameBoard[coordinates[0]-1+i][coordinates[1]+2]=protection;
                                }
                                gameBoard[coordinates[0]-1][coordinates[1]-1+i]=protection;
                                gameBoard[coordinates[0]+1][coordinates[1]-1+i]=protection;
                            }
                            break;
                    }
                    placedTripleShips++;
                }
            }
            while (placedDoubleShips<2)
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
                        for (int i = 0;i<2;i++)//vertical protections
                        {
                            if(coordinates[1]!=6)//6
                            {
                                gameBoard[coordinates[0]+i][coordinates[1]+1]=protection;//right side protection left side ships
                            }
                            if(coordinates[1]!=0)//0
                            {
                                gameBoard[coordinates[0]+i][coordinates[1]-1]=protection;//left side protection of right side ships
                            }
                        }
                        for (int i = 0;i<2;i++)//horizontal protections
                        {
                            if((coordinates[0]!=5)&&(coordinates[1]==0))
                            {
                                gameBoard[coordinates[0]+2][coordinates[1]+i]=protection;//lower prot of left side ships except lower left
                            }
                            else if((coordinates[0]!=5)&&(coordinates[1]==6))
                            {
                                gameBoard[coordinates[0]+2][coordinates[1]-1+i]=protection;//lower prot of right side ship except lower left 
                            }
                            else if(coordinates[0]!=5)
                            {
                                for (int j = 0;j<3;j++)
                                {
                                    gameBoard[coordinates[0]+2][coordinates[1]-1+j]=protection;//lower prot 
                                }
                                break;
                            }
                        }
                        for(int i = 0;i<2;i++)
                        {
                            if((coordinates[0]!=0)&&(coordinates[1]==0))
                            {
                                gameBoard[coordinates[0]-1][coordinates[1]+i]=protection;//upper prot of left side ships except upper left
                            }
                            else if((coordinates[0]!=0)&&(coordinates[1]==6))
                            {
                                gameBoard[coordinates[0]-1][coordinates[1]-1+i]=protection;//upper prot of right side ship except upper left
                            }
                            else if(coordinates[0]!=0)
                            {
                                for (int j = 0;j<3;j++)
                                {
                                    gameBoard[coordinates[0]-1][coordinates[1]-1+j]=protection;//upper prot 
                                }
                                break;
                            }
                        }
                        placedDoubleShips++;
                    }
            }
            while (placedSingleShips<4) 
            {
                coordinates[0] =  random.nextInt(gameFieldLength);
                coordinates[1] =  random.nextInt(gameFieldLength);
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