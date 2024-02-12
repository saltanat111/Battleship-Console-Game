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