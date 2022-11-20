package chess;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class WelcomePanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    public static final Color BG_ALL=new Color(196, 113, 20);
    public static final Color BG_BUTTON_COLOR=new Color(222, 182, 104);
    public static final Font FONT_TEXT=new Font("Futura XBlkIt BT",Font.PLAIN,50);
    public static final Font FONT_BUTTON=new Font("Arial",Font.BOLD,19);
	 public WelcomePanel() {
	        super();
	        initUI();
	        this.setTitle("Gobang 2013599 ���ҵ");
	        this.setSize(400, 400);
	        this.setResizable(false);
	        this.setLayout(null);
	        this.setLocationRelativeTo(null); 
	        
	    }
	private void initUI() {
		getContentPane().setBackground(BG_ALL);
		JTextField goBangTitle=getGobangTitle();
		goBangTitle.setBounds(75, 40, 240, 60);
		getContentPane().add(goBangTitle);
		JButton selfPlay=getButton();
		selfPlay.setText("Self play");
		selfPlay.setBounds(115, 130, 150, 35);
		getContentPane().add(selfPlay);
		JButton AIPlay=getButton();
		AIPlay.setText("Play with AI");
		AIPlay.setBounds(115, 180, 150, 35);
		getContentPane().add(AIPlay);
		JButton onlinePlay=getButton();
		onlinePlay.setText("Play online");
		onlinePlay.setBounds(115, 230, 150, 35);
		getContentPane().add(onlinePlay);
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
	private JTextField getGobangTitle() {
		JTextField tf=new JTextField();
		tf.setHorizontalAlignment(JTextField.CENTER);
		tf.setBackground(BG_ALL);
		tf.setText("Gobang");
        tf.setFocusable(false);
        tf.setEditable(false);
        tf.setFont(FONT_TEXT);
        tf.setBorder(null);
		return tf;
	}
	public static void main(String[] args) {
		WelcomePanel hello=new WelcomePanel();
        hello.setVisible(true);
        hello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedMode=e.getActionCommand();
		
		if(selectedMode.equals("Self play"))
		{
			Vars.c.setMode(Controller.SELF_PLAY);
			Vars.c.setFirstHand();
			UIFrame ui=new UIFrame(this);
			ui.setLocationRelativeTo(null);
			ui.setVisible(true);
		}
		else if(selectedMode.equals("Play with AI"))
		{
			Vars.c.setMode(Controller.AI_PLAY);
			ChooseColorPanel cp=new ChooseColorPanel(this);
			cp.setVisible(true);
		}
		else {
			Vars.c.setMode(Controller.ONLINE_PLAY);
			ConnectPanel cnp= new ConnectPanel(this);
			cnp.setVisible(true);
		}
		this.setVisible(false);
	}
}
