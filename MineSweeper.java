
/*
**  Jessica Ims
**
**  EECS 692 - Java
**  Spring 2014
**
*/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Random;


/* MINESWEEPER CLASS */
public class MineSweeper extends JFrame 
{ 
     private int level;
     private int lives;
     private int mines;
     private boolean gameOver;
     private Cell[][] cells = new Cell[10][10];
     private JLabel messageBar = new JLabel("PROCESSING...");
     private Random generator = new Random();
     
     /* Constructor */
     public MineSweeper()
     {
        level = 1;
        lives = 2;
        mines = 5;
        gameOver = false;

        JPanel p = new JPanel(new GridLayout(10, 10, 0, 0));

        for(int i = 0; i < 10; i++)
          {
             for(int j = 0; j < 10; j++)
               {
                  p.add(cells[i][j] = new Cell());
                  cells[i][j].setBackground(Color.WHITE);
                  cells[i][j].setFont(new Font("Times New Roman", Font.BOLD, 20));
               }
          }

        // Randomly place 5 mines
        for(int i = 0; i < 5; i++)
          {
             int x = generator.nextInt(10);
             int y = generator.nextInt(10);
             if( cells[x][y].getValue() == 'X' )
              {
                 i--;
              }
             else
              {
                 cells[x][y].newValue(9);
              }
          }

        // Count the mines in adjacent cells
        for(int i = 0; i < 10; i++)
          {
             for(int j = 0; j < 10; j++)
               {
                  if( cells[i][j].getValue() != 'X' )
                   {
                      int count = 0;

                      // Check for mine in left adjacent cell
                      if((j - 1) >= 0)
                       {
                          if( cells[i][j - 1].getValue() == 'X' )
                           {
                              count++;
                           }
                       }

                      // Check for mine in right adjacent cell
                       if((j + 1) < 10)
                       {
                          if( cells[i][j + 1].getValue() == 'X' )
                           {
                              count++;
                           }
                       }

                      if((i - 1) >= 0)
                       {
                          // Check for mine in top adjacent cell
                          if( cells[i - 1][j].getValue() == 'X' )
                           {
                              count++;
                           }

                          // Check for mine in top-left adjacent cell
                          if((j - 1) >= 0)
                           {
                              if( cells[i - 1][j - 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }

                          // Check for mine in top-right adjacent cell
                          if((j + 1) < 10)
                           {
                              if( cells[i - 1][j + 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }
                       }

                      if((i + 1) < 10)
                       {
                          // Check for mine in bottom adjacent cell
                          if( cells[i + 1][j].getValue() == 'X' )
                           {
                              count++;
                           }

                          // Check for mine in bottom-left adjacent cell
                          if((j - 1) >= 0)
                           {
                              if( cells[i + 1][j - 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }

                          // Check for mine in bottom-right adjacent cell
                          if((j + 1) < 10)
                           {
                              if( cells[i + 1][j + 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }
                       }

                      //Set the value of the cell to the number of adjacent mines
                      cells[i][j].newValue(count);
                   }
               }
          }

        messageBar.setText("LEVEL " + level + ".  Mines: " + mines + ".  Lives left: " + lives + ".");
        messageBar.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        p.setBorder(new LineBorder(Color.black, 1));
        messageBar.setBorder(new LineBorder(Color.black, 1));
        add(messageBar, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);
     }

     /* Reset the gameboard */
     public void reset() 
     { 
        if(level < 8)
         {
            messageBar.setText("PROCESSING...");
            level++;
            switch(level)
             {
                 case 2:
                      mines = 7;
                      lives = 2;
                      break;
                 case 3:
                      mines = 9;
                      lives = 3;
                      break;
                 case 4:
                      mines = 11;
                      lives = 3;
                      break;
                 case 5:
                      mines = 13;
                      lives = 4;
                      break;
                 case 6:
                      mines = 15;
                      lives = 4;
                      break;
                 case 7:
                      mines = 17;
                      lives = 4;
                      break;
                 case 8:
                      mines = 19;
                      lives = 5;
                      break;
                 default:
                      mines = 5;
                      lives = 2;
                      break;
             }

            //clear the board
            for(int i = 0; i < 10; i++)
              {
                 for(int j = 0; j < 10; j++)
                   {
                      cells[i][j].resetCell();
                   }
              }

            layMines();
            messageBar.setText("LEVEL " + level + ".  Mines: " + mines + ".  Lives left: " + lives + ".");
            gameOver = false;
         }
        else
         {
            messageBar.setText("YOU WIN!");
         }
     }

     /* Set up the mines */
     public void layMines() 
     { 
        // Randomly place 5 mines
        for(int i = 0; i < mines; i++)
          {
             int x = generator.nextInt(10);
             int y = generator.nextInt(10);
             if( cells[x][y].getValue() == 'X' )
              {
                 i--;
              }
             else
              {
                 cells[x][y].setValue(9);
              }
          }

        // Count the mines in adjacent cells
        for(int i = 0; i < 10; i++)
          {
             for(int j = 0; j < 10; j++)
               {
                  if( cells[i][j].getValue() != 'X' )
                   {
                      int count = 0;

                      // Check for mine in left adjacent cell
                      if((j - 1) >= 0)
                       {
                          if( cells[i][j - 1].getValue() == 'X' )
                           {
                              count++;
                           }
                       }

                      // Check for mine in right adjacent cell
                       if((j + 1) < 10)
                       {
                          if( cells[i][j + 1].getValue() == 'X' )
                           {
                              count++;
                           }
                       }

                      if((i - 1) >= 0)
                       {
                          // Check for mine in top adjacent cell
                          if( cells[i - 1][j].getValue() == 'X' )
                           {
                              count++;
                           }

                          // Check for mine in top-left adjacent cell
                          if((j - 1) >= 0)
                           {
                              if( cells[i - 1][j - 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }

                          // Check for mine in top-right adjacent cell
                          if((j + 1) < 10)
                           {
                              if( cells[i - 1][j + 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }
                       }

                      if((i + 1) < 10)
                       {
                          // Check for mine in bottom adjacent cell
                          if( cells[i + 1][j].getValue() == 'X' )
                           {
                              count++;
                           }

                          // Check for mine in bottom-left adjacent cell
                          if((j - 1) >= 0)
                           {
                              if( cells[i + 1][j - 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }

                          // Check for mine in bottom-right adjacent cell
                          if((j + 1) < 10)
                           {
                              if( cells[i + 1][j + 1].getValue() == 'X' )
                               {
                                  count++;
                               }
                           }
                       }

                      //Set the value of the cell to the number of adjacent mines
                      cells[i][j].setValue(count);
                   }
               }
          }
     }

     /* Check if the game is over.  If so, end game. */
     public boolean isWon() 
     { 
        for(int i = 0; i < 10; i++)
          {
             for(int j = 0; j < 10; j++)
               {
                  if( (cells[i][j].getValue() != 'X') && cells[i][j].isHidden() )
                   {
                       return false;
                   }
               }
          }

        gameOver = true;
        return true;
     }

     /* Process result of clicking on a mine */
     public void boom() 
     { 
        lives--;
        if(lives == 0)
         { 
            gameOver = true;
            messageBar.setText("YOU LOSE.");
         }
        else
         { 
            messageBar.setText("LEVEL " + level + ".  Mines: " + mines + ".  Lives left: " + lives + ".");
         }
     }


     /* Main Method */
     public static void main(String[] args) 
     { 
        JFrame frame = new MineSweeper();
        frame.setTitle("MineSweeper");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
     }



     /* CELL CLASS */
     public class Cell extends JPanel
     { 
        private char value;
        private boolean hidden;

        /* Constructor */
        public Cell()
        {
           value = '0';
           hidden = true;
           setBorder(new LineBorder(Color.black, 1));
           addMouseListener(new CellListener());
        }

        /* Get the value of the cell */
        public char getValue()
        {
           return value;
        }

        /* Check if the value is hidden */
        public boolean isHidden()
        {
           return hidden;
        }

        /* set the value of the cell and call repaint */
        public void newValue(int v)
        {
           setValue(v);
           repaint();
        }

        /* Click a cell: reveal the cell's value */
        public void clicked()
        {
           hidden = false;
           repaint();
        }

        /* Reset the cell */
        public void resetCell()
        {
           hidden = true;
           value = '0';
           repaint();
        }

        /* Set the value of the cell */
        public void setValue(int v)
        {
           switch(v)
            {
               case 0:
                    value = '0';
                    break;
               case 1:
                    value = '1';
                    break;
               case 2:
                    value = '2';
                    break;
               case 3:
                    value = '3';
                    break;
               case 4:
                    value = '4';
                    break;
               case 5:
                    value = '5';
                    break;
               case 6:
                    value = '6';
                    break;
               case 7:
                    value = '7';
                    break;
               case 8:
                    value = '8';
                    break;
               default:
                    value = 'X';
                    break;
            }
        }

        /* Paint the cell */
        @Override
        protected void paintComponent(Graphics g)
        {
           super.paintComponent(g);
           
           if(hidden)
            {
               g.drawString(" ", 25, 20);
            }
           else
            {
              switch(value)
               {
                  case '0':
                       g.setColor(Color.GRAY);
                       break;
                  case '1':
                       g.setColor(Color.BLACK);
                       break;
                  case '2':
                       g.setColor(Color.GREEN);
                       break;
                  case '3':
                       g.setColor(Color.CYAN);
                       break;
                  case '4':
                       g.setColor(Color.BLUE);
                       break;
                  case '5':
                       g.setColor(Color.MAGENTA);
                       break;
                  case '6':
                       g.setColor(Color.PINK);
                       break;
                  case '7':
                       g.setColor(Color.ORANGE);
                       break;
                  case '8':
                       g.setColor(Color.RED);
                       break;
                  default:
                       g.setColor(Color.RED);
                       break;
               }

              FontMetrics fm = g.getFontMetrics();
              int strW = fm.stringWidth(String.valueOf(value));
              int strA = fm.getAscent();
              int x = (getWidth() / 2) - (strW / 2);
              int y = (getHeight() / 2) + (strA / 4);;
              g.drawString(String.valueOf(value), x, y);
            }
        }

        /* LISTENER CLASS */
        private class CellListener extends MouseAdapter
        { 
           /* Click a cell */
           @Override
           public void mouseClicked(MouseEvent e)
           {
              if(hidden && (gameOver == false))
               {
                  clicked();
                  if(value == 'X')
                   {
                      boom();
                   }
                  else if(isWon())
                   {
                      reset();
                   }
               }
           }
        }
     }
}


