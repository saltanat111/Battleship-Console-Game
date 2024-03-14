import java.util.*; 

public class matrixProject {
    static Map<Integer, String> ratings = new HashMap<>();
    
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            Random random = new Random();
            int gameFieldLength = 7;
            char water = '~';
            char ship3 = '3';
            char ship2a = 'a';
            char ship2b = 'b';
            char ship1 = '1';
            char hit = 'h';
            char miss = 'm';
            char sunk = 's';
            char protection = 'p';  
            int[] userShot = new int[2];
            int allShots = 0;

            char[][] gameBoard = createGameBoard(gameFieldLength,water,ship3,random,protection,ship1,ship2a,ship2b);
            String name = ascNameAndReturnValue(scan);
            printGameBoard(gameBoard,gameFieldLength,protection,water);
            getCheckUserShotAndUpdataGameboard(gameBoard,gameFieldLength,water,ship1,ship3,hit,miss,sunk,scan,ship2a,ship2b,name,allShots,userShot);
            ratings.put(allShots, name);

            String nextgame = scan.nextLine();
            String yes = "y";
            while (nextgame.equals(yes))
            {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                gameBoard = createGameBoard(gameFieldLength,water,ship3,random,protection,ship1,ship2a,ship2b);
                name = ascNameAndReturnValue(scan);
                printGameBoard(gameBoard,gameFieldLength,protection,water);
                getCheckUserShotAndUpdataGameboard(gameBoard,gameFieldLength,water,ship1,ship3,hit,miss,sunk,scan,ship2a,ship2b,name,allShots,userShot);
                ratings.put(allShots, name);
                nextgame = scan.nextLine();
            }
            sortbykey();
        }
        private static int[] getUserShotIfWrong (int[] userShot,Scanner scan)
        {
            while(((userShot[0]>6)||(userShot[1]>6))||((userShot[0]<0)||(userShot[1]<0)))
                        {
                            System.out.println("Range is from 1 to 7"+"\n"+"enter coordinate X:");//get user shot
                            userShot[1] = scan.nextInt();
                            userShot[1]--;
                            System.out.println("enter coordinate Y:");
                            userShot[0] = scan.nextInt();
                            userShot[0]--;
                        }
                        return userShot;
        }
        public static void sortbykey()
        {
            ArrayList<Integer> sortedKeys = new ArrayList<Integer>(ratings.keySet());
            Collections.sort(sortedKeys);

            for (int x : sortedKeys)
                System.out.println("Name :" + ratings.get(x)+ "  " + "Number of shots :" + x);
        }
        
        private static String ascNameAndReturnValue(Scanner scan) {
            System.out.println("Enter your name : ");
            String name = scan.nextLine();
            return name;
        }


        private static void getCheckUserShotAndUpdataGameboard(char[][] gameBoard, int gameFieldLength, char water,  char ship1, char ship3, char hit, char miss, char sunk, Scanner scan ,char ship2a,char ship2b,String name,int allShots,int[]userShot) {
            int shipNum = 11;
            int tripleShipDetected = 0;
            int ship2aDetected = 0;
            int ship2bDetected = 0;
            char hit3 = 'u';
            char hit2a = 'A';
            char hit2b = 'B';
            while (shipNum>0)
            {
                    System.out.println("enter coordinate X:");//get user shot
                    userShot[1] = scan.nextInt();
                    userShot[1]--;
                    System.out.println("enter coordinate Y:");
                    userShot[0] = scan.nextInt();
                    userShot[0]--;
                    allShots++;
                    getUserShotIfWrong ( userShot, scan);
                    while ((gameBoard[userShot[0]][userShot[1]]==miss)||(gameBoard[userShot[0]][userShot[1]]==hit)||(gameBoard[userShot[0]][userShot[1]]==sunk)||(gameBoard[userShot[0]][userShot[1]]==hit2a)||(gameBoard[userShot[0]][userShot[1]]==hit2b)||(gameBoard[userShot[0]][userShot[1]]==hit3)) {
                        System.out.println("Do not shot at shotted before cells"+"\n"+"enter coordinate X:");//get user shot
                        userShot[1] = scan.nextInt();
                        userShot[1]--;
                        System.out.println("enter coordinate Y:");
                        userShot[0] = scan.nextInt();
                        userShot[0]--;
                        getUserShotIfWrong ( userShot, scan);
                    }
                    
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();

                    if(gameBoard[userShot[0]][userShot[1]]==ship3)//check user shot
                    {
                        gameBoard[userShot[0]][userShot[1]] = hit3;
                        tripleShipDetected++;
                        shipNum--;
                        if(tripleShipDetected==3)
                        {
                            for (int i = 0;i<gameFieldLength;i++)
                            {
                                for(int j = 0;j<gameFieldLength;j++)
                                {
                                    if(gameBoard[i][j]==hit3)
                                    {
                                        gameBoard[i][j] = sunk;
                                    }
                                }
                            }
                        }
                    }
                    else if(gameBoard[userShot[0]][userShot[1]]==ship2b)
                    {
                        gameBoard[userShot[0]][userShot[1]] = hit2b;
                        ship2bDetected++;
                        shipNum--;
                        if(ship2bDetected==2)
                        {
                            for (int i = 0;i<gameFieldLength;i++)
                            {
                                for(int j = 0;j<gameFieldLength;j++)
                                {
                                    if(gameBoard[i][j]==hit2b)
                                    {
                                        gameBoard[i][j] = sunk;
                                    }
                                }
                            }
                        }
                    }
                    else if(gameBoard[userShot[0]][userShot[1]]==ship2a)
                    {
                        gameBoard[userShot[0]][userShot[1]] = hit2a;
                        ship2aDetected++;
                        shipNum--;
                        if(ship2aDetected==2)
                        {
                            for (int i = 0;i<gameFieldLength;i++)
                            {
                                for(int j = 0;j<gameFieldLength;j++)
                                {
                                    if(gameBoard[i][j]==hit2a)
                                    {
                                        gameBoard[i][j] = sunk;
                                    }
                                }
                            }
                        }
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
                    // System.out.print("\033[H\033[2J");  
                    // System.out.flush();
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
                            if((gameBoard[i][j]==hit3)||(gameBoard[i][j]==hit2a)||(gameBoard[i][j]==hit2b))
                            {
                                System.out.print(hit+" ");
                            }
                            else if((gameBoard[i][j]!=water)&&(gameBoard[i][j]!=hit)&&(gameBoard[i][j]!=miss)&&(gameBoard[i][j]!=sunk))
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

        private static char[][] createGameBoard(int gameFieldLength, char water, char ship3, Random random,char protection,char ship1 ,char ship2a,char ship2b) 
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
                        if(placedDoubleShips == 0)
                        {
                            gameBoard[coordinates[0]][coordinates[1]]=ship2a;
                            gameBoard[coordinates[0]+1][coordinates[1]]=ship2a;
                            placedDoubleShips++;
                        }
                        else if(placedDoubleShips == 1)
                        {
                            gameBoard[coordinates[0]][coordinates[1]]=ship2b;
                            gameBoard[coordinates[0]+1][coordinates[1]]=ship2b;
                            placedDoubleShips++;
                        }
                        
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