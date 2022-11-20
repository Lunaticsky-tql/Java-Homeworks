package chat;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class client extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane2;
	private JTextField textField2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	public client(String h) {
		setTitle(h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 365);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);
		JLabel label = new JLabel("客户端设置：");
		label.setBounds(10, 10, 79, 15);
		contentPane2.add(label);		
		JLabel lblServeIp = new JLabel("serve ip：");
		lblServeIp.setBounds(10, 35, 79, 15);
		contentPane2.add(lblServeIp);		
		textField2= new JTextField();
		textField2.setBounds(100, 32, 125, 21);
		contentPane2.add(textField2);
		textField2.setColumns(10);		
		JLabel lblServePort = new JLabel("serve port：");
		lblServePort.setBounds(250, 35, 79, 15);
		contentPane2.add(lblServePort);		
		textField_3 = new JTextField();
		textField_3.setBounds(339, 32, 119, 21);
		contentPane2.add(textField_3);
		textField_3.setColumns(10);		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 66, 530, 206);
		contentPane2.add(textArea);
		try {
			InetAddress address=InetAddress.getLocalHost();
			textField2.setText(address.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
		            public void run() {
				int port=Integer.parseInt(textField_3.getText());
				try {
				socket=new Socket(textField2.getText(),port);
				textArea.append("Connect to server…\n");
				}catch (Exception e1) {
					e1.printStackTrace();
					//textArea.append("Connect to server…");
			}
		            }
		        }.start();
		        new Thread() {
		            public void run() {		                
		                    while (true) {
		                    	try {
				                	br = new BufferedReader(new InputStreamReader(
			                                socket.getInputStream()));
		                        String message = br.readLine();
		                        textArea.setText(textArea.getText() + message
		                                + "\r\n");
		                    }
		                 catch (Exception e) {		                    
		                }
		                    }
		            }
		        }.start();
		    }
		});
		btnConnect.setBounds(474, 31, 97, 23);
		contentPane2.add(btnConnect);
		JLabel lblSay = new JLabel("say：");
		lblSay.setBounds(10, 282, 88, 36);
		contentPane2.add(lblSay);
		textField_4 = new JTextField();
		textField_4.setBounds(73, 290, 351, 21);
		contentPane2.add(textField_4);
		textField_4.setColumns(10);	
		JButton btnSay = new JButton("say");
		btnSay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message= textField_4.getText();
				try {
					pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				pw.println(message);
				pw.flush();
			}
		});
		btnSay.setBounds(452, 289, 119, 23);
		contentPane2.add(btnSay);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					client frame2 = new client("客户端");
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
}

