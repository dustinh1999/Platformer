import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Fight extends JPanel implements ActionListener, KeyListener {
	//Variables for the input code
	int code, prevCode;
	JLabel win;
	//Variable to tell if game is over
	boolean end=false;
	double tempX,tempY;
	//Timer to keep program running
	Timer t = new Timer(5, this);
	//Unused duckCount variable
	int duckCount=0;
	//Values to determine motion of the person
	double x = 0, y = 0, velX = 0, velY = 0;
	//Currently unimplemented values for fighting
	double armPunch=0,legKick=0;
	//Curently unimplemented value for ducking
	double duckingFix=0;
	
	boolean start;
	boolean keyInProgress=false;
	
	//Values for the first platform
	double xVelPlat=-10;
	double yVal=400*Math.random()+100;
	double width=50*Math.random()+200;
	
	//Values for the second platform
	double xVelPlatTwo=0;
	double yValTwo=400*Math.random()+100;
	double widthTwo=50*Math.random()+200;
	
	//Values for the third platform
	double xVelPlatThree=0;
	double yValThree=400*Math.random()+100;
	double widthThree=50*Math.random()+200;
	
	//Time counters used to determine when to start the platforms
	double time=System.currentTimeMillis();
	double timeAfter;
	
	
	public Fight() {
		//Start the timer

		
		t.start();
		
		addKeyListener(this); // to JPanel
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		timeAfter=System.currentTimeMillis();
		//Design first character with body parts
		Graphics2D armPartOne = (Graphics2D) g;
		Graphics2D armPartTwo= (Graphics2D) g;
		Graphics2D body= (Graphics2D) g;
		Graphics2D legOne=(Graphics2D)g;
		Graphics2D legTwo=(Graphics2D)g;
		Graphics2D head=(Graphics2D)g;
		head.fill(new Ellipse2D.Double(x, y+225, 25, 25));
		body.fill(new Rectangle2D.Double(x, y+250, 25, 50));
		armPartOne.fill(new Rectangle2D.Double(x+armPunch,y+270, 35,15));
		legOne.fill(new Rectangle2D.Double(x+5,y+300+duckingFix, 5, 30));
		legTwo.fill(new Rectangle2D.Double(x+15,y+300+duckingFix,5,30));
		
		
		//Design platforms
		//Platform One
		Graphics2D platform=(Graphics2D) g;
		if (xVelPlat<-600-width-width)
		{
     	yVal=400*Math.random()+100;
		width=200*Math.random()+200;
		xVelPlat=0;
		}
		g.drawRect((int) (600+xVelPlat+width), (int)yVal, (int)width, 5);
		xVelPlat=xVelPlat-0.5;
		
		//Platform Two
		if (timeAfter-time<6000)
		{	
			xVelPlatTwo=0;
		}
		if (xVelPlatTwo<-600-widthTwo-widthTwo)
		{
			yValTwo=400*Math.random()+100;
			widthTwo=200*Math.random()+200;
			xVelPlatTwo=0;
		}
		g.drawRect((int) (600+xVelPlatTwo+widthTwo), (int)yValTwo, (int)widthTwo, 5);
		xVelPlatTwo=xVelPlatTwo-0.5;
		
		//Platform Three
		if (timeAfter-time<10000)
		{	
			xVelPlatThree=0;
		}
		if (xVelPlatThree<-600-widthThree-widthThree)
		{
			yValThree=400*Math.random()+100;
			widthThree=200*Math.random()+200;
			xVelPlatThree=0;
		}
		g.drawRect((int) (600+xVelPlatThree+widthThree), (int)yValThree, (int)widthThree, 5);
		xVelPlatThree=xVelPlatThree-0.5;
	}

	public void actionPerformed(ActionEvent e) {
		//Repaint whenever action is performed
		repaint();
		//If the player is on the platform, player does not fall. (Gravity)
		if (OnPlatform())
		{
			if (code==KeyEvent.VK_UP)
			{
				System.out.println("Hey");
				velY=-10;
			}
			
			else
			velY=0;
			
		}
		else
		{
			velY=velY+0.01;
		}
		//Movement in the x and y direction for character one
		x += velX;
		y += velY;

		//Linking the z button to a middle punch of character one
		if (code==KeyEvent.VK_Z)
		{
			armPunch=armPunch+5;
		} 
		
		//if up is pressed, begin decrementing velocity
		if (code==KeyEvent.VK_UP)
		{
			velY=velY+0.01;
		}
		
		//When the game is over, terminate the program and output that the player lost
		if (y>600)
		{
			System.out.println("You lost!");
			xVelPlat=0;
			xVelPlatTwo=0;
			xVelPlatThree=0;
			System.exit(0);
		}
		
	
	}
	//Tell if the character is on the Platform
	public boolean OnPlatform() {


		if ((x>=600+xVelPlat+width&&x<=600+xVelPlat+width+width)&&(y+300<yVal&&y+300>yVal-30))
		{	System.out.println("hello");
			return true;
		}
		else if ((x>=600+xVelPlatTwo+widthTwo&&x<=600+xVelPlatTwo+widthTwo+widthTwo)&&(y+300<yValTwo&&y+300>yValTwo-30))
			return true;
		else if ((x>=600+xVelPlatThree+widthThree&&x<=600+xVelPlatThree+widthThree+widthThree)&&(y+300<yValThree&&y+300>yValThree-30))
			return true;
		return false;
	}
	

	//Return what to do if the up arrow key is pressed (Set velocity upward)
	public void up() {
		velY = -10;
	}
	
//	public void down() {
//		
//		if (duckCount==0)
//		{
//		y=y+30;
//		}
//		duckCount=1;
//		duckingFix=-30;
//		repaint();
//
//		
//	}
	//Return what to do if the left arrow key is pressed (Set velocity left)
	public void left() {
		velX = -1.5;
	
	}
	//Return what to do if the user inputs the right arrow key
	public void right() {
		velX = 1.5;
	
	}
	//Return what to do when t he right arrow key is released
	public void releaseRight()
	{
		velX=0;
	}
	
//	public void releaseDown()
//	{
//		duckingFix=0;
//		y=y-30;
//		duckCount=0;
//		repaint();
//	}
	//Return what to do when left arrow key is released
	public void releaseLeft()
	{
		velX=0;
	}
	//Return what to do when up arrow key is released
	public void releaseUp()
	{
		velY=0;
		code=0;
	}
	
	//Currently unimplemented punching/shooting motion
	public void z() {
		if (start)
		{	
			tempX=x+armPunch;
			start=false;
		}


	}
	//Curently unimplemented punching/shooting motion
	public void releaseZ() {
		armPunch=0;

		start=true;
		code=0;
		repaint();
	}

	public void keyPressed(KeyEvent e){

		prevCode=code;
		keyInProgress=true;
		code = e.getKeyCode();
		//Call z function
		if (code==KeyEvent.VK_Z)
		{
			z();
		}
		//Call up function
		if (code == KeyEvent.VK_UP) {
			up();
			
		}
//		if (code == KeyEvent.VK_DOWN) {
//			down();
//		}
		
		//Call left function
		if (code == KeyEvent.VK_LEFT) {
			left();
		}
		
		//Call right function
		if (code == KeyEvent.VK_RIGHT) {
			right();
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		//Releasing keys Z, Right, Up, Left
		
		
		if (code==KeyEvent.VK_Z)
		{
			releaseZ();
			keyInProgress=false;
		}
		if (code==KeyEvent.VK_RIGHT)
		{
			releaseRight();
			keyInProgress=false;

		}
		if (code==KeyEvent.VK_UP)
		{
			releaseUp();
			keyInProgress=false;

		}
//		if (code==KeyEvent.VK_DOWN)
//		{
//			releaseDown();
//			keyInProgress=false;
//
//		}
		if (code==KeyEvent.VK_LEFT)
		{
			releaseLeft();
			keyInProgress=false;

		}
	}
	
//	public double getxVelPlat()
//	{
//		return xVelPlat;
//	}


//	public static void main(String[] args) {
//
//	//	JFrame frame = new JFrame();
//		Fight s = new Fight();
//
////		frame.add(s);
////		frame.setVisible(true);
////		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		frame.setSize(800,600);
//	}
}


