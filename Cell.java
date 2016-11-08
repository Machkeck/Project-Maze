import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Cell extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public boolean north=true;     // wall north of cell
    public boolean east=true;		//wall east of cell
    public boolean south=true;		//wall south of cell
    public boolean west=true;		//wall west of cell
    public boolean visited=false;   // has the cell been visited
    public boolean start=false;		//cell is starting point
    public boolean end=false;		//cell is ending point
    public boolean drawme=false;	//cell colored with first color
    public boolean drawme2=false;	//cell colored with second color
    public boolean border=false;	//is cell border
    
    
    public void wait(int n)//wait n milliseconds
    {
        long t0, t1;
        t0 = System.currentTimeMillis();

        do {
            t1 = System.currentTimeMillis();
        } while ((t1 - t0) < n);
    }
    public void borderpainter(Graphics g)
    {
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    public void visited(Graphics g)//draw that field is visited
    {
    //	g.setColor(Color.BLACK);
		//g.fillOval((this.getWidth()-this.getWidth()/5)/2, (this.getHeight()-this.getHeight()/5)/2, this.getWidth()/5, this.getHeight()/5);
    	//g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    public void draw2Color(Graphics g)//draw with second color to show backtracking
    {
    	g.setColor(Color.MAGENTA);
		g.fillOval((this.getWidth()-this.getWidth()/5)/2,(this.getHeight()-this.getHeight()/5)/2,this.getWidth()/5,this.getHeight()/5);
		wait(100);
    }
    public void drawColor(Graphics g)//draw with first color to show movement
    {
    	g.setColor(Color.CYAN);
		g.fillOval((this.getWidth()-this.getWidth()/5)/2,(this.getHeight()-this.getHeight()/5)/2,this.getWidth()/5,this.getHeight()/5);		
		wait(100);
    }
    public void erase(Graphics g)//erase all graphics in cell
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
    public void startColor(Graphics g)//draw starting point
    {
    	g.setColor(Color.GREEN);
		g.fillOval((this.getWidth()-this.getWidth()/4)/2,(this.getHeight()-this.getHeight()/4)/2,this.getWidth()/4,this.getHeight()/4);
    }
    public void endColor(Graphics g)//draw ending point
    {
    	g.setColor(Color.RED);
		g.fillOval((this.getWidth()-this.getWidth()/4)/2,(this.getHeight()-this.getHeight()/4)/2,this.getWidth()/4,this.getHeight()/4);
    }
    public void N(Graphics g)//draw north wall only
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
    	g.drawLine(0, 0, this.getWidth()-1, 0);//north
    }
    public void S(Graphics g)//draw south wall only
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
    	g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south
    }
    public void W(Graphics g)//draw west wall only
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west
    }
    public void E(Graphics g)//draw east wall only
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void SWE(Graphics g)//draw south-west-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, 0, this.getHeight()-1);//west        	
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south        	
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void NWE(Graphics g)//draw north-west-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void NSE(Graphics g)//draw north-south-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south        
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void NSW(Graphics g)//draw north-south-west walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south 
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    }
    public void NS(Graphics g)//draw north-south walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south 
    }
    public void NW(Graphics g)//draw north-west walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north 
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    }
    public void NE(Graphics g)//draw north-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north       
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void SW(Graphics g)//draw south-west walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south 
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    }
    public void SE(Graphics g)//draw south-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south        
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void WE(Graphics g)//draw west-east walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight()); 
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void NSWE(Graphics g)//draw all walls
    {
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(0, 0, this.getWidth()-1, 0);//north
		g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);//south 
    	g.drawLine(0, 0, 0, this.getHeight()-1);//west       
    	g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);//east
    }
    public void paintComponent(Graphics g)//draws graphics depending on the cells boolean fields
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	
    	if(this.north && this.south && this.east && this.west)
    	{
    		NSWE(g);
    	}
    	else if(!this.south && this.north && this.west && this.east)
    	{
    		NWE(g);
    	}
    	else if(!this.north && this.east && this.west && this.south)
    	{
    		SWE(g);
    	}
    	else if(!this.west && this.north && this.south && this.east)
    	{
    		NSE(g);
    	}
    	else if(!this.east && this.north && this.south && this.west)
    	{
    		NSW(g);
    	}
    	else if(!this.north && !this.east && this.south && this.west)
    	{
    		SW(g);
    	}
    	else if(this.north && !this.east && !this.south && this.west)
    	{
    		NW(g);
    	}
    	else if(!this.north && this.east && this.south && !this.west)
    	{
    		SE(g);
    	}
    	else if(this.north && this.east && !this.south && !this.west)
    	{
    		NE(g);
    	}
    	else if(!this.north && this.east && !this.south && this.west)
    	{
    		WE(g);
    	}
    	else if(this.north && !this.east && this.south && !this.west)
    	{
    		NS(g);
    	}
    	else if(this.north && !this.south && !this.east && !this.west)
    	{
    		N(g);
    	}
    	else if(!this.north && this.south && !this.east && !this.west)
    	{
    		S(g);
    	}
    	else if(!this.north && !this.south && !this.east && this.west)
    	{
    		W(g);
    	}
    	else if(!this.north && !this.south && this.east && !this.west)
    	{
    		E(g);
    	}
    	else if(!this.north && !this.south && !this.east && !this.west)
    	{
    		g.clearRect(0, 0, this.getWidth(), this.getHeight());
        	
    	}
    	if(visited)
    	{
    		visited(g);
    	}
    	if(start)
    	{
    		startColor(g);
    	}
    	if(end)
    	{
    		endColor(g);
    	}
    	if(border)
    	{
    		borderpainter(g);
    	}
    }
}