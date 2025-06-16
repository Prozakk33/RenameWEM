package main;

import java.awt.*;
import javax.swing.*;



public class ProgressBar {



	JFrame frame = new JFrame();

	JProgressBar bar = new JProgressBar(0,73534);

	

	ProgressBar(){

		bar.setValue(0);
		bar.setBounds(0,0,420,60);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli",Font.BOLD,15));
		bar.setForeground(Color.red);


		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 100);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Renaming Files");

	}
	

	public void fill(int counter) {


		if (counter >= 73530) {
			
			bar.setString("Done! :)");
			
		} 
		else {
			
			bar.setString(counter + " / 73534 files renamed");
			bar.setValue(counter);
			
		}



	}
	
	public void close() {
		frame.setVisible(false);
	}

}
