package room;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
 
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
 
public class Client{
 
	private JFrame frame;
	private JList<String> userList;
	private JTextArea textArea;
	private JTextField textField;
	private JTextField txt_port;
	private JTextField txt_hostIp;
	private JTextField txt_name;
	private JButton btn_start;
	private JButton btn_stop;
	private JButton btn_send;
	private JPanel northPanel;
	private JPanel southPanel;
	private JScrollPane rightScroll;
	private JScrollPane leftScroll;
	private JSplitPane centerSplit;
 
	private DefaultListModel<String> listModel;
	private boolean isConnected = false;
 
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private MessageThread messageThread;

 
	public static void main(String[] args) {
		new Client();
	}
 
	public void send() {
		if (!isConnected) {
			JOptionPane.showMessageDialog(frame, "You have not linked to the sever", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String message = textField.getText().trim();
		if (message == null || message.equals("")) {
			JOptionPane.showMessageDialog(frame, "Empty message!", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		sendMessage(frame.getTitle() + "#" + "ALL" + "#" + message);
		textField.setText(null);
	}

	public Client() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setForeground(Color.black);
		textField = new JTextField();
		txt_port = new JTextField("6666");
		txt_hostIp = new JTextField("127.0.0.1");
		txt_name = new JTextField("2013599");
		btn_start = new JButton("Link");
		btn_stop = new JButton("Stop");
		btn_send = new JButton("Send");
		listModel = new DefaultListModel<String>();
		userList = new JList<String>(listModel);
 
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,7,5,0));
		northPanel.add(new JLabel("Port",SwingConstants.CENTER));
		northPanel.add(txt_port);
		northPanel.add(new JLabel("IP",SwingConstants.CENTER));
		northPanel.add(txt_hostIp);
		northPanel.add(new JLabel("Name",SwingConstants.CENTER));
		northPanel.add(txt_name);
		northPanel.add(btn_start);
		northPanel.add(btn_stop);
		northPanel.setBorder(new TitledBorder("Information"));
 
		rightScroll = new JScrollPane(textArea);
		rightScroll.setBorder(new TitledBorder("Message Box"));
		leftScroll = new JScrollPane(userList);
		leftScroll.setBorder(new TitledBorder("Online clients"));
		southPanel = new JPanel(new BorderLayout());
		southPanel.add(textField, "Center");
		southPanel.add(btn_send, "East");
		southPanel.setBorder(new TitledBorder("Write Message"));
 
		centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll,
				rightScroll);
		centerSplit.setDividerLocation(100);
 
		frame = new JFrame("Client");
		frame.setLayout(new BorderLayout());
		frame.add(northPanel, "North");
		frame.add(centerSplit, "Center");
		frame.add(southPanel, "South");
		frame.setSize(600, 400);
		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screen_width - frame.getWidth()) / 2,
				(screen_height - frame.getHeight()) / 2);
		frame.setVisible(true);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port;
				if (isConnected) {
					return;
				}
				try {
					try {
						port = Integer.parseInt(txt_port.getText().trim());
					} catch (NumberFormatException e2) {
						throw new Exception("Format error!");
					}
					String hostIp = txt_hostIp.getText().trim();
					String name = txt_name.getText().trim();
					if (name.equals("") || hostIp.equals("")) {
						throw new Exception("Cannot be empty!");
					}
					boolean flag = connectServer(port, hostIp, name);
					if (flag == false) {
						throw new Exception("Connection Failure!");
					}
					frame.setTitle(name);
					JOptionPane.showMessageDialog(frame, "Coneect successfully!  Port:"+txt_port.getText());
					btn_start.setEnabled(false);
					txt_port.setEnabled(false);
					txt_hostIp.setEnabled(false);
					txt_name.setEnabled(false);
					btn_stop.setEnabled(true);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(frame, exc.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
 
		btn_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isConnected) {
					return;
				}
				try {
					boolean flag = closeConnection();
					if (flag == false) {
						throw new Exception("Error!");
					}
					JOptionPane.showMessageDialog(frame, "Offline!");
					btn_start.setEnabled(true);
					txt_port.setEnabled(true);
					txt_name.setEnabled(true);
					btn_stop.setEnabled(false);
				} catch (Exception exc) {
					
				}
			}
		});
 
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (isConnected) {
					closeConnection();
				}
				System.exit(0);
			}
		});
	}
 

	public boolean connectServer(int port, String hostIp, String name) {
		try {
			socket = new Socket(hostIp, port);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			sendMessage(name + "#" + socket.getLocalAddress().toString());
			messageThread = new MessageThread(reader, textArea);
			messageThread.start();
			isConnected = true;
			return true;
		} catch (Exception e) {
			textArea.append("Connection failure" + "\r\n");
			isConnected = false;
			return false;
		}
	}
 

	public void sendMessage(String message) {
		writer.println(message);
		writer.flush();
	}
 
//close the window by client
	@SuppressWarnings("deprecation")
	public synchronized boolean closeConnection() {
		try {
			sendMessage("CLOSE");
			messageThread.stop();
	
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				socket.close();
			}
			isConnected = false;
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			isConnected = true;
			return false;
		}
	}
 
	
	class MessageThread extends Thread {
		private BufferedReader reader;
		private JTextArea textArea;
 
	
		public MessageThread(BufferedReader reader, JTextArea textArea) {
			this.reader = reader;
			this.textArea = textArea;
		}
 //close the thread by sever
		public synchronized void closeCon() throws Exception {
	
			listModel.removeAllElements();
		
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				socket.close();
			}
			isConnected = false;
		}
 
		public void run() {
			String message = "";
			while (true) {
				try {
					message = reader.readLine();
					StringTokenizer stringTokenizer = new StringTokenizer(
							message, "#");
					String command = stringTokenizer.nextToken();
					if (command.equals("CLOSE"))
					{
						textArea.append("Sever closed!\r\n");
						closeCon();
						return;
					} else if (command.equals("ADD")) {
						String username = "";
						if ((username = stringTokenizer.nextToken()) != null) {
							listModel.addElement(username);
						}
					} else if (command.equals("DELETE")) {
						String username = stringTokenizer.nextToken();
						listModel.removeElement(username);
					} else if (command.equals("USERLIST")) {
						int size = Integer
								.parseInt(stringTokenizer.nextToken());
						String username = null;
						for (int i = 0; i < size; i++) {
							username = stringTokenizer.nextToken();
							listModel.addElement(username);
						}
					} else if (command.equals("MAX")) {
						textArea.append(stringTokenizer.nextToken()
								+ stringTokenizer.nextToken() + "\r\n");
						closeCon();
						JOptionPane.showMessageDialog(frame, "Full", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						textArea.append(message + "\r\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}