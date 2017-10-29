import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FightingFrame extends JFrame implements ActionListener{
	JLabel win;
	
	public FightingFrame()
	{	
		super("Fight!");
		Fight a=new Fight();
		add(a);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		a.setBackground(Color.red);
	}
	public static void main(String[]args)
	{
		FightingFrame a=new FightingFrame();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
