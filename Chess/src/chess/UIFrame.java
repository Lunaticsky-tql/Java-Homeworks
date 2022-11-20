package chess;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UIFrame extends JFrame implements ActionListener{

	public static final Font FONT_BUTTON=new Font("Arial",Font.PLAIN,15);
	public static final Font FONT_SMALL=new Font("Arial",Font.PLAIN,12);
	public static final int CROSSHAIR_CURSOR=1;
	public static final int MOVE_CURSOR=13;
	public static final int DEFAULT_CURSOR=0;
	private static final long serialVersionUID = 1L;
	public WelcomePanel welcomePanel;
	private JButton rePut;
	private JButton goBack;
	private JButton restart;
	private JButton chat;
	public UIFrame(WelcomePanel welcomePanel)
	{
		initUIFrame();
		this.welcomePanel=welcomePanel;
		Vars.v=this;
		closeHandler();

	}

	private void closeHandler() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Vars.m.removeHistory();;
				welcomePanel.setVisible(true);
			}
		});
	}

	private void initUIFrame() {
		 this.setTitle("Gobang");
	        this.setResizable(false);
	        this.setLayout(null); 
	        getContentPane().setBackground(WelcomePanel.BG_ALL);
	        
	       
	        if(Vars.c.getMode()==Controller.SELF_PLAY)
	        {
	        	 this.setSize(450, 720);
	           	 Vars.b.setBounds(20, 0, 400, 600);
	           	 getContentPane().add(Vars.b);
	           	 JPanel commandPanel=getCommandPanel();
	           	 commandPanel.setBounds(0, 600, 450, 120);
	           	 getContentPane().add(commandPanel);
	        	changeRegretBtnState(false);
	        }
	        if(Vars.c.getMode()==Controller.AI_PLAY)
	        {
	        	 this.setSize(450, 720);
	           	 Vars.b.setBounds(20, 0, 400, 600);
	           	 getContentPane().add(Vars.b);
	           	 JPanel commandPanel=getCommandPanel();
	           	 commandPanel.setBounds(0, 600, 450, 120);
	           	 getContentPane().add(commandPanel);
	        	changeReputBtnState(false);
	        }
	        if(Vars.c.getMode()==Controller.ONLINE_PLAY)
	        {
	        	 this.setSize(450, 720);
	           	 Vars.b.setBounds(20, 40, 400, 560);
	           	 getContentPane().add(Vars.b);
	           	 JPanel commandPanel=getOnlineCommandPanel();
	           	 commandPanel.setBounds(0, 600, 450, 120);
	           	 getContentPane().add(commandPanel);
				changeRegretBtnState(false);
				changeReputBtnState(false);
	        }
	        
	}
	private JPanel getCommandPanel() {
		JPanel commandPanel=new JPanel();
		commandPanel.setBackground(WelcomePanel.BG_ALL);
		commandPanel.setLayout(null);
		
		rePut=getButton();
		rePut.setBounds(30,0,100,25);
		rePut.setText("Reput");
		commandPanel.add(rePut);
		goBack=getButton();
		goBack.setBounds(150,0,100,25);
		goBack.setText("Regret");
		commandPanel.add(goBack);
		restart=getButton();
		restart.setBounds(270,0,100,25);
		restart.setText("Restart");
		commandPanel.add(restart);
		return commandPanel;
	}
	private JPanel getOnlineCommandPanel() {
		JPanel onlineCommandPanel=new JPanel();
		onlineCommandPanel.setBackground(WelcomePanel.BG_ALL);
		onlineCommandPanel.setLayout(null);
		
		rePut=getButton();
		rePut.setBounds(30,0,75,25);
		rePut.setText("Reput");
		onlineCommandPanel.add(rePut);
		goBack=getButton();
		goBack.setBounds(125,0,75,25);
		goBack.setText("Regret");
		onlineCommandPanel.add(goBack);
		restart=getButton();
		restart.setBounds(215,0,75,25);
		restart.setText("Restart");
		onlineCommandPanel.add(restart);
		chat=getButton();
		chat.setBounds(305,0,75,25);
		chat.setText("Chat");
		onlineCommandPanel.add(chat);
		return onlineCommandPanel;
	}
	
	private JButton getButton() {
		JButton button=new JButton();
		 button.setForeground(Color.BLACK);
        button.setBackground(WelcomePanel.BG_BUTTON_COLOR);
        if(Vars.c.getMode()==Controller.ONLINE_PLAY)
        {
        	button.setFont(FONT_SMALL);
        }
        else {
            button.setFont(FONT_BUTTON);
		}
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
		return button;
	}

	public void changeReputBtnState(boolean enable) {
		rePut.setEnabled(enable);
	}	
	public void changeRegretBtnState(boolean enable) {
		goBack.setEnabled(enable);
	}	
	public void changeRestartBtnState(boolean enable) {
		rePut.setEnabled(enable);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		String curBtn=e.getActionCommand();
		if(curBtn.equals("Regret"))
		{
			Vars.c.handleGoBack();
		}
		else if (curBtn.equals("Restart")) {
			Vars.c.handleResrart();
		}
		else if (curBtn.equals("Reput")) {
			changeReputBtnState(false);
			Vars.c.handleReput();
		}
		else if (curBtn.equals("Chat")) {
			Vars.chat.setVisible(true);
		}
	}

	public void showWarning(String string) {
		JOptionPane.showMessageDialog(this, string, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showOption(String command,String string) {
		 int opt = JOptionPane.showConfirmDialog(this,
				    string, "Quest",
				     JOptionPane.YES_NO_OPTION);
				   if (opt == JOptionPane.YES_OPTION) {
					   if(command.equals("REPUT"))
					   {
						   Vars.n.sendReputMessage("OK");
					   }
					   if(command.equals("RECORD"))
					   {
						   RecordModeStarter record=new RecordModeStarter(Vars.m.getHistory(),Vars.c.getMode(),Vars.c.getLocalColor());
						   record.rf.setVisible(true);
					   }
					   if(command.equals("REGRET"))
					   {
						   Vars.n.sendRegretMessage("OK");
					   }
					   if(command.equals("RESTART"))
					   {
						   Vars.n.sendRestartMessage("OK");
					   }
				   }
				   else if (opt == JOptionPane.NO_OPTION) {
					   if(command.equals("REPUT"))
					   {
						   Vars.n.sendReputMessage("NO");
					   } 
					   if(command.equals("RECORD"))
					   {
						   //do nothing
					   }
					   if(command.equals("REGRET"))
					   {
						   Vars.n.sendRegretMessage("NO");
					   }
					   if(command.equals("RESTART"))
					   {
						   Vars.n.sendRestartMessage("NO");
					   }
				}
			
		
	}

	public void showMessage(String string1, String string2) {
		JOptionPane.showMessageDialog(this, string2, string1,
				JOptionPane.PLAIN_MESSAGE);
	}



	
}
