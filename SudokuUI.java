import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ericgumba on 5/27/17.
 */
public class SudokuUI extends JFrame implements ActionListener {

  private TextField textDisplay = new TextField();
  private Panel buttonPanel = new Panel();
  private String currentEntry = "";
  private JTextField[][] squares = new JTextField[9][9];

  private JPanel p[][]= new JPanel [3][3];


  public static void main(String args[]){ SudokuUI sud = new SudokuUI(); }

  public SudokuUI(){

    for(int x=0; x<=8; x++){
      for(int y=0; y<=8; y++) {
        squares[x][y] = new JTextField(1);
      }
    }


    for(int x=0; x<=2; x++){
      for(int y=0; y<=2; y++){
        p[x][y]=new JPanel(new GridLayout(3,3));
      }
    }

    Button solve = new Button("S");

    buttonPanel.add(solve);

    solve.addActionListener( this );

    setLayout(new GridLayout(4,3,5,5));

    for(int u=0; u<=2; u++){
      for(int i=0; i<=2; i++){
        for(int x=0; x<=2; x++ ){
          for(int y=0; y<=2; y++){
            p[u][i].add(squares[x+u*3][y+i*3]);
          }
        }
        add(p[u][i]);
      }
    }
    add(buttonPanel);
    setTitle( "sudoku" );
    setSize(400, 400 );
    setLocationByPlatform( true );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
    setVisible( true );
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final SudokuSolver SOLVE = new SudokuSolver();

    int board[][] = new int[9][9];
    for(int x = 0; x <= 8; x++){
      for(int y = 0; y <= 8; y++){
        if ( squares[x][y].getText().equals(" ") ) {
          board[x][y] = 0;
        } else {
          try {
            if (Integer.parseInt(squares[x][y].getText()) > 0
                && Integer.parseInt(squares[x][y].getText()) < 10 )
            board[x][y] = Integer.parseInt(squares[x][y].getText()); else board[x][y] = 0;

          } catch (Exception sadFace) {
            board[x][y] = 0;
          }
        }
      }
    }

    SOLVE.solve(board);


//
    for (int i = 0; i <= 8; i++){
      System.out.println(" ");
      for( int j = 0; j <= 8; j++){
        squares[i][j].setText(String.valueOf(board[i][j]));
      }
    }
//    System.out.println(" ");
  }
}
