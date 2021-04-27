import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.net.http.HttpClient;

import javax.swing.*;
public class Login_proxy implements ActionListener {
	JFrame frame;
	JPanel panel;
	JLabel user_label;
	JTextField user_text;
	JPasswordField pass_text;
	JLabel success;
	JLabel pass_label;
	JButton login_button;
	public Login_proxy() {
		frame = new JFrame();
		frame.setTitle("Login system");
		user_label = new JLabel("User");
	    pass_label = new JLabel("Password");
		
		panel = new JPanel();
		user_text = new JTextField(); //initialize the space for user inputting the name and password
		pass_text = new JPasswordField();
				
		frame.setSize(300, 200); //set the size of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		panel.setLayout(null);
		user_label.setBounds(10, 20, 120, 25);  //set the position of the label
		pass_label.setBounds(10, 50, 120, 25);
		
		panel.add(pass_label); //add labels into the panel
		panel.add(user_label);
		
		user_text.setBounds(100, 20, 165, 25);//set the position of the text compoent and add into
												//panel
		pass_text.setBounds(100, 50, 165, 25);
		panel.add(user_text);
		panel.add(pass_text);
		login_button = new JButton("login");//set up thr login button
		login_button.setBounds(10, 80, 120, 25);
		panel.add(login_button);
		login_button.addActionListener(this);
		//action listener will perform actions after login button was pressed
		success = new JLabel();
		success.setBounds(10, 110, 120, 25); //adding the sucess compoent if user login successfullly
		panel.add(success);
		frame.setVisible(true);
	}
	private boolean user_check() {
		String u_input = this.user_text.getText();
		String p_input = this.pass_text.getText();
		int count=0;
		boolean user = false;
		boolean pass =false;
		String line;
		BufferedReader in = null;
		String[] names  = new String [20];
		String passwords [] = new String [20];
		try {
			in = new BufferedReader(new FileReader("Combination.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String columns[];
	      	//in.readLine(); //skip header
				int i=0;
			while ((line = in.readLine()) != null) {
					
					columns = line.split(",");
					
					names[i] = columns[0];
					passwords[i] = columns[1];
					i++;
					count++;
				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<names.length;i++) {
			if(u_input.equals(names[i]) && p_input.equals(passwords[i]) ) {
				user=true;
				pass =true;
			}
			else {
				continue;
			}
		}
		
		if(user ==true && pass == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(user_check() == true) {
			success.setText("login success");
		    new MainUI();
		}else {
	    	 JOptionPane.showMessageDialog(panel, "invalid user,please try again");
	    	 System.exit(0);
	      }
		
	}
	public void test_login() {
		System.out.println(this.pass_text.getText());
		System.out.println(this.user_text.getText());
		System.out.println(this.user_check());
	}
	
}
