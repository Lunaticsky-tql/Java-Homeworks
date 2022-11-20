package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConnectPanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static final Color BG_ALL=new Color(196, 113, 20);
	public static final Color BG_BUTTON_COLOR=new Color(222, 182, 104);
	public static final Font FONT_TEXT=new Font("Arial",Font.BOLD,20);
	public static final Font FONT_BUTTON=new Font("Arial",Font.PLAIN,19);
	public static final Font FONT_LABEL=new Font("Arial",Font.PLAIN,15);
	public WelcomePanel welcomePanel;
	private JTextField ipTF;
	private JTextField nameTF;
	private JButton listenButton;
	private JButton connectButton;
	public boolean forward=false;
	private String friendName;
	ConnectPanel(WelcomePanel welcomePanel){
		super();
        initUI();
    	this.welcomePanel=welcomePanel;	
    	Vars.connect=this;
    	closeHandler(welcomePanel);
	}


	private void initUI() {
		
		this.setTitle("Connect to your friend!");
        this.setSize(450, 300);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
		addComponent();		
	}


	private void addComponent() {
		getContentPane().setBackground(BG_ALL);
		JLabel title=getConnectDialogTitle();
		title.setBounds(90, 30, 250, 50);
		getContentPane().add(title);
		JLabel severString =getLabel("Sever:");
		severString.setBounds(60, 100, 60, 25);
		getContentPane().add(severString);
		ipTF=getTF("localhost");
		ipTF.setBounds(130, 100, 70, 25);
		getContentPane().add(ipTF);
		JLabel nameString =getLabel("Nickname:");
		nameString.setBounds(220, 100, 70, 25);
		getContentPane().add(nameString);
		nameTF=getTF("no name");
		nameTF.setBounds(300, 100, 70, 25);
		getContentPane().add(nameTF);
		listenButton=getButton();
		listenButton.setText("Listen");
		listenButton.setBounds(65, 160, 110, 30);
		getContentPane().add(listenButton);
		connectButton=getButton();
		connectButton.setText("Connect");
		connectButton.setBounds(245, 160, 110, 30);
		getContentPane().add(connectButton);
	}
	private JTextField getTF(String s) {
		JTextField tf=new JTextField(10);
		tf.setText(s);
		return tf;
	}

	private JLabel getLabel(String s) {
		JLabel title=new JLabel();
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setBackground(BG_ALL);
		title.setText(s);
        title.setFocusable(false);
        title.setFont(FONT_LABEL);
        title.setBorder(null);
		return title;
	}


	private JButton getButton() {
		JButton button=new JButton();
		button.setForeground(Color.BLACK);
        button.setBackground(BG_BUTTON_COLOR);
        button.setFont(FONT_BUTTON);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
		return button;
	}
	private JLabel getConnectDialogTitle() {
		JLabel title=new JLabel();
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setBackground(BG_ALL);
		title.setText("Connect to your friend!");
        title.setFocusable(false);
        title.setFont(FONT_TEXT);
        title.setBorder(null);
		return title;
	}	
	
	public void changeListenBtnState(boolean enable) {
		listenButton.setEnabled(enable);
	}
	public void changeConnectBtnState(boolean enable) {
		connectButton.setEnabled(enable);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedColor=e.getActionCommand();
		
		if(selectedColor.equals("Listen"))
		{
			beginListen();
		}
		else if(selectedColor.equals("Connect"))
		{
			beginConnect(ipTF.getText());
			
		}
	}
	
	public void beginListen() {
		Vars.n.listen();
		changeListenBtnState(false);
		changeConnectBtnState(false);
	}
	
	public void beginConnect(String ip) {
		Vars.n.connect(ip);
		changeListenBtnState(false);
		changeConnectBtnState(false);
	}
		
	public void connected(String source) {
		Vars.n.sendNameMessage(nameTF.getText());
		if(source.equals("CUSTOMER"))
		{
			JOptionPane.showMessageDialog(this, "Connected! As host, your color is black", "Connect successfully!",
					JOptionPane.PLAIN_MESSAGE);
			Vars.c.setFirstHand();
			Vars.n.sendConnectMessage("HOST");
		
		}
		else if(source.equals("HOST")) {
			JOptionPane.showMessageDialog(this, "Connected! As customer, your color is white", "Connect successfully!",
					JOptionPane.PLAIN_MESSAGE);
			Vars.c.setGoteHand();
		}	
		enterRoom();
	}


	private void enterRoom() {
		forward=true;
		UIFrame ui=new UIFrame(welcomePanel);					
		ui.setLocationRelativeTo(null);
		ui.setVisible(true);
		this.setVisible(false);
	}
	public void refused(String source) {
		if(source.equals("CUSTOMER"))
		{
			Vars.n.sendConnectMessage("REFUSED");
		
		}
		else if (source.equals("HOST")) {
			JOptionPane.showMessageDialog(this, "Please wait!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}	
		
	}


	public void handleSeverClose() {
		changeListenBtnState(true);
		changeConnectBtnState(true);
	}
	
	private void closeHandler(WelcomePanel welcomePanel) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				if(forward==false)
				{
					 welcomePanel.setVisible(true);
					 Vars.n.stop();
				}

			}
		});
	}


	public void rememberName(String nextToken) {
		friendName=nextToken;
		ChatPanel chat=new ChatPanel(nameTF.getText(),friendName);
		chat.setVisible(false);

	}
}
