import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Start2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cell array[][];//array of Cells
	public int N=10;//number of cells on each side
	public boolean done=false;//done true if maze is solved
	int startX=1,startY=1,endX=N/2,endY=N/2;//start and end coordinates
	//Gui interface elements
	private JButton solveButton;//starts the maze solver
	private JButton setButton;//sets the maze with start and end
	private JButton resetButton;//resets the maze's fields
	private JButton newButton;//creates new maze
	private JTextField sXText;
    private JTextField sYText;
    private JTextField eXText;
    private JTextField eYText;
    private JLabel sXLabel,sYLabel,eXLabel,eYLabel;
    
    
	public static void main(String[] args)
    {
    	Start2 s = new Start2();
    	s.run();//starts gui application
    }
	
	public void pregenerate()//creates and paints borders of the maze
	{	
		
		for (int x = 0; x < N+2; x++) {
            array[x][0].visited = true;
            array[x][N+1].visited = true;
            array[x][0].border = true;
            array[x][N+1].border = true;
        }
        for (int y = 0; y < N+2; y++) {
            array[0][y].visited = true;
            array[N+1][y].visited = true;
            array[0][y].border = true;
            array[N+1][y].border = true;
        }
	}

	public void generate(int x, int y)//generates the maze using the DFS recursive backtracker algorithm
	{
        array[x][y].visited = true;        
      //checking whether there is a possible unvisited cell in any direction
        while (!array[x][y+1].visited || !array[x+1][y].visited || !array[x][y-1].visited || !array[x-1][y].visited)
        {        	
            //picking a random cell neighbor 
            while (true) {
                double r = UGenerator.uniform(4);
                if (r == 0 && !array[x][y-1].visited)//picked the west neighbor
                {
                    array[x][y].west = false;//deleting the west wall
                    array[x][y-1].east = false;//deleting neighbor's east wall
                    generate(x, y - 1);//recursion-continuing path to the neighbor's cell
                    break;
                }
                else if (r == 1 && !array[x+1][y].visited)//picked the south neighbor 
                {
                    array[x][y].south = false;//deleting the south wall
                    array[x+1][y].north = false;//deleting neighbor's north wall
                    generate(x+1, y);//recursion-continuing path to the neighbor's cell
                    break;
                }
                else if (r == 2 && !array[x][y+1].visited)//picked the east neighbor
                {
                    array[x][y].east = false;//deleting the east wall
                    array[x][y+1].west = false;//deleting neighbor's west wall
                    generate(x, y+1);//recursion-continuing path to the neighbor's cell
                    break;
                }
                else if (r == 3 && !array[x-1][y].visited)//picked the north neighbor
                {
                    array[x][y].north = false;//deleting the north wall
                    array[x-1][y].south = false;//deleting neighbor's south wall
                    generate(x-1, y);//recursion-continuing path to the neighbor's cell
                    break;
                }
            }
        }
    }
		
	public void solve(int x, int y)
	{
		
		
		if (x == 0 || y == 0 || x == N+1 || y == N+1) //if coordinates are borders return
			return;
        if (done || array[x][y].visited) //if maze is solved or field has already been visited return
        	return;
        
        array[x][y].visited = true;
        array[x][y].drawme=true;             
        array[x][y].drawColor(array[x][y].getGraphics());//paints current field
        
        if(array[x][y].end==true) //if end maze is solved
        	done=true;

        if (!array[x][y].north) //if no wall to the north go north
        	solve(x-1, y);
        if (!array[x][y].east)  //if no wall to the east go east
        	solve(x, y+1);
        if (!array[x][y].south) //if no wall to the south go south
        	solve(x+1, y);
        if (!array[x][y].west)  //if no wall to the west go west
        	solve(x, y-1);

        if (done) 
        	return;
        
        if(array[x][y].drawme==true)//if backtracking paint with secondary color
           	array[x][y].drawme2=true;        
        
        array[x][y].draw2Color(array[x][y].getGraphics());
	}
	
	public void solve()//starts the actual maze solver
	{
		
        done = false;
        solve(startX, startY);
    }
	
	public void unvisit()//cleaning up the maze cell's fields
	{
		for (int x = 1; x <= N; x++)
            for (int y = 1; y <= N; y++){
            	array[x][y].erase(array[x][y].getGraphics());
                array[x][y].visited = false;
                array[x][y].drawme = false;
                array[x][y].drawme2 = false;
                array[x][y].start = false;
                array[x][y].end = false;
                array[x][y].repaint();
            }
	}
	
	public void clearWalls()//deleting the maze's walls
	{
		for (int x = 0; x <= N+1; x++)
            for (int y = 0; y <= N+1; y++){
            	array[x][y].north=true;
            	array[x][y].south=true;
            	array[x][y].east=true;
            	array[x][y].west=true;
            	
            }
	}
	
	public void run()//gui manager method
	{
		//myframe is divided into JPanels:outer and lower, outer:upper, upper:LCD, LCD:Cells
		final Start2 myframe = new Start2();
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.setSize( 1400, 1600);
             
        BorderLayout mar=new BorderLayout();
        myframe.setLayout(mar);
        final JPanel outer = new JPanel();
               
        BorderLayout layout = new BorderLayout();       
        layout.setHgap(20);
        layout.setVgap(20);        
        outer.setLayout(layout);
        
        final JPanel LCD = new JPanel();       
        LCD.setLayout(new GridLayout(N+2,N+2));
        LCD.setPreferredSize(new Dimension(800, 800));
        LCD.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //creation of the fields/cells
        array=new Cell[N+2][N+2];
        Cell temp = null;
        for ( int i = 0; i <= N+1; i++ )
        {
        	for(int j = 0;j<=N+1; j++)
        	{
        		temp = new Cell();           
        		temp.setPreferredSize(new Dimension(40, 40));        		
        		array[i][j]=temp;      
        		LCD.add(array[i][j]);
        	}
        }
                
        JPanel upper = new JPanel();
        upper.setLayout(new GridBagLayout());
        upper.add(LCD);
        outer.add(LCD,BorderLayout.CENTER);
        myframe.add(outer,BorderLayout.CENTER);
        myframe.setVisible( true );
        myframe.pack();
        
        //initial generation of the maze
        pregenerate();
        generate(startX,startY);
        
        
        JPanel lower = new JPanel();//lower interfaces buttons and fields
        lower.setLayout(new FlowLayout());
        solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveListener());
        setButton = new JButton("Set");
        setButton.addActionListener(new SetListener());
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetListener());
        newButton=new JButton("New Maze");
        newButton.addActionListener(new NewListener());
        sXText=new JTextField("1");
        sYText=new JTextField("1");
        eXText=new JTextField(Integer.toString(N/2));
        eYText=new JTextField(Integer.toString(N/2));
        sXLabel=new JLabel("Start x");
        sYLabel=new JLabel("y");
        eXLabel=new JLabel("End x");
        eYLabel=new JLabel("y");
        
        lower.add(newButton);
        lower.add(solveButton);
        lower.add(setButton);
        lower.add(resetButton);
        
        lower.add(sXLabel);
        lower.add(sXText);
        
        lower.add(sYLabel);
        lower.add(sYText);
        
        lower.add(eXLabel);
        lower.add(eXText);
        
        lower.add(eYLabel);
        lower.add(eYText);
        
        myframe.add(lower,BorderLayout.PAGE_END);
        myframe.pack();
        
               
	}
	//ActionListener classes
	class SolveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			solve();
								
		}
	}
	
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			unvisit();
								
		}
	}
	
	class SetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
					
			unvisit();
			try{
			startX = Integer.parseInt(sXText.getText());
			startY = Integer.parseInt(sYText.getText());
			endX = Integer.parseInt(eXText.getText());
			endY = Integer.parseInt(eYText.getText());
			if(startX==0||startY==0||startX==N+1||startY==N+1||endX==0||endY==0||endX==N+1||endY==N+1)
			{
				startX=1;
				startY=1;
				endX=N/2;
				endY=N/2;
			}
			}
			catch(ArrayIndexOutOfBoundsException a)
			{
				a.getStackTrace();
				sXText.setText("OoB");
				sYText.setText("OoB");
				eXText.setText("OoB");
				eYText.setText("OoB");
				startX=1;
				startY=1;
				endX=N/2;
				endY=N/2;				
			}
			catch(NumberFormatException s)
			{
				s.getStackTrace();
				sXText.setText("NaN");
				sYText.setText("NaN");
				eXText.setText("NaN");
				eYText.setText("NaN");
				startX=1;
				startY=1;
				endX=N/2;
				endY=N/2;
			}
			array[startX][startY].start=true;
			array[endX][endY].end=true;
	    	array[startX][startY].repaint();
	    	array[endX][endY].repaint();
			array[startX][startY].startColor(array[startX][startY].getGraphics());
			array[endX][endY].startColor(array[endY][endY].getGraphics());
		}
	}
	
	class NewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			unvisit();
			clearWalls();
			pregenerate();
			generate(1,1);
								
		}
	}
	
	
}
