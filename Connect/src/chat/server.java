package chat;

import java.net.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
public class server extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField postNumber;
	private JTextArea enterPost;
	private Socket socket;
	private PrintWriter pw1;
	public static JTextArea dialog;
	static int result=0;
	client f1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server frame1 = new server();
					frame1.setVisible(true);
					frame1.setResizable(false);
				    } catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});
	}
	public server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblPort = new JLabel("Server port number:");
		lblPort.setBounds(80, 30, 140, 15);
		contentPane.add(lblPort);
		postNumber = new JTextField();
		postNumber.setBounds(220, 25, 60, 25);
		contentPane.add(postNumber);
		dialog = new JTextArea();
		dialog.setBounds(25, 300, 435, 180);
		dialog.setBorder(new TitledBorder("Dialog:"));
		contentPane.add(dialog);
		JButton btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		
	             
		});
		btnStart.setBounds(310, 24, 60, 25);
		contentPane.add(btnStart);
		enterPost=new JTextArea();
		enterPost.setBorder(new TitledBorder("Enter the content to post"));
		enterPost.setBounds(25, 69, 435, 160);
		contentPane.add(enterPost);	
		JButton btnSay = new JButton("Send to");
		btnSay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message1= enterPost.getText();
				try {
					pw1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				pw1.println(message1);
				pw1.flush();
			}
		});
		btnSay.setBounds(140, 250, 80, 25);
		contentPane.add(btnSay);
		JButton btnStop = new JButton("stop");
		btnSay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		btnStop.setBounds(390, 24, 60, 25);
		contentPane.add(btnStop);
	}
}
