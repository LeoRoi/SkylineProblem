import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

public class AlertBox extends JFrame {
	public AlertBox(int ind) {
		JLabel a1 = new JLabel("Please check your input!");
		a1.setBounds(4, 1, 180, 30);
		
		JButton ok = new JButton("ok");
		ok.addActionListener(new okAction());
		ok.setBounds(195, 1, 80, 30);
		
		setTitle("Error!");
		setLayout(null);
		setSize(280, 70);
		setLocation(600, 700);
		
		switch (ind) {
		case 1:
			add(a1);
			break;
		}

		add(ok);
		setVisible(true);
	}
	/*
	public AlertBox() {		
		JFrame frame = new JFrame("BoxLayout-Demo (einfach)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	    frame.setTitle("Choose the color!");
		frame.setLocation(600, 700);  
		frame.setSize(100, 200);
		
		JButton red = new JButton("red");
		JButton blue = new JButton("blue");
		JButton green = new JButton("green");
		JButton orange = new JButton("orange");
		JButton yellow = new JButton("yellow");
		
		red.addActionListener(new redAct());
		blue.addActionListener(new okAction("blue"));
		green.addActionListener(new okAction("green"));
		orange.addActionListener(new okAction("orange"));
		yellow.addActionListener(new okAction("yellow"));
		
		frame.add(red, Component.CENTER_ALIGNMENT);
		frame.add(blue, Component.CENTER_ALIGNMENT);
		frame.add(green, Component.CENTER_ALIGNMENT);
		frame.add(orange, Component.CENTER_ALIGNMENT);
		frame.add(yellow, Component.CENTER_ALIGNMENT);
		frame.setVisible(true);
	}

	class redAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Skyline.color = Color.red;
			dispose();
		}
	}
	*/
	class okAction implements ActionListener {
		okAction(){};
		/*
		okAction(String col) {
			switch(col) {
			case "red":
				Skyline.color = Color.red;
				break;
			case "blue":
				Skyline.color = Color.blue;
				break;
			case "green":
				Skyline.color = Color.green;
				break;
			case "orange":
				Skyline.color = Color.orange;
				break;
			case "yellow":
				Skyline.color = Color.yellow;
				break;
			}
		}
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
