package chess;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatPanel extends JFrame{


	private static final long serialVersionUID = 1L;
	public static final Font FONT_BUTTON=new Font("Arial",Font.PLAIN,13);
	public static final Font FONT_PANEL=new Font("Arial",Font.PLAIN,15);
    public static final Color PANE_COLOR=new Color(220, 220, 220);
	JTextArea box=new JTextArea();
	JTextField input=new JTextField();
	private String me;
	private String friend;
	JButton sendBtn;
	public ChatPanel(String myName, String friendName) {
		me=myName;
		friend=friendName;
		this.setTitle("Chat");
		this.setSize(400, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		getContentPane().setBackground(WelcomePanel.BG_ALL);
		box.setBounds(30, 20, 330, 370);
		box.setBackground(PANE_COLOR);
		box.setFont(FONT_PANEL);
		box.setEditable(false);
		getContentPane().add(box);
		getWritePane();
		sendBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Vars.c.sendChatMessage(input.getText(),me);
			}
		});
    	Vars.chat=this;
	}
	private void getWritePane() {
		input.setBounds(20, 405, 270, 30);
		input.setBackground(PANE_COLOR);
		input.setFont(FONT_PANEL);
		getContentPane().add(input);
		sendBtn=getButton("Send!");
		sendBtn.setBounds(300,405,70,30);
		getContentPane().add(sendBtn);

	}
	private JButton getButton(String s) {
		JButton button=new JButton(s);
		button.setForeground(Color.BLACK);
        button.setBackground(WelcomePanel.BG_BUTTON_COLOR);
        button.setFont(FONT_BUTTON);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
		return button;
	}
	public void addChatMessage(String name, String text) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				box.append(name+": "+text+"\n");
				if(name.equals(friend))
				{
					Vars.b.drawChatMessage(name,text);
					Vars.b.repaint();
				}
			}
		});
		
	}
	public String getMe() {
		return me;
	}
	public void setMe(String me) {
		this.me = me;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
}
