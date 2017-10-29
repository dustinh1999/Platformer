import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Character extends Fight {

	public void drawPerson(Graphics2D g)
	{
		g.fill(new Ellipse2D.Double(x,y,40,40));
		  g.fill(new Ellipse2D.Double(x,y,100,100));
		}
}
