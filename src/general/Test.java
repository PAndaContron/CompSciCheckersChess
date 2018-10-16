package general;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test
{

	public static void main(String[] args)
	{
		JPanel panel1 = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("resources/images/checkers/redPiece.png");
				img.paintIcon(this, g, getX(), getY());
			}
		};
		panel1.setPreferredSize(new Dimension(300, 400));
		JPanel panel2 = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("resources/images/checkers/redPiece.png");
				img.paintIcon(this, g, getX(), getY());
			}
		};
		panel2.setPreferredSize(new Dimension(300, 400));
		
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new GridLayout(0, 2));
		frame.getContentPane().add(panel1, BorderLayout.WEST);
		frame.getContentPane().add(panel2, BorderLayout.EAST);
//		frame.getContentPane().add(panel1, new GridBagConstraints(1, 1, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//		frame.getContentPane().add(panel2, new GridBagConstraints(2, 1, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		frame.setSize(800, 400);
		frame.setVisible(true);
	}

}
