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
import javax.swing.JTextField;

public class ChooseColorPanel extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static final Color BG_ALL=new Color(196, 113, 20);
	public static final Color BG_BUTTON_COLOR=new Color(222, 182, 104);
	public static final Font FONT_TEXT=new Font("Arial",Font.BOLD,20);
	public static final Font FONT_BUTTON=new Font("Arial",Font.PLAIN,19);
	public WelcomePanel welcomePanel;
	public boolean forward=false;
	ChooseColorPanel(WelcomePanel welcomePanel){
		super();
        initUI();
        closeHandler(welcomePanel);
        this.welcomePanel=welcomePanel;

	}

	private void initUI() {
		
		this.setTitle("Choose your chess color");
        this.setSize(400, 250);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
		addComponent();		
	}


	private void addComponent() {
		getContentPane().setBackground(BG_ALL);
		JLabel title=getChooseDialogTitle();
		title.setBounds(72, 30, 250, 50);
		getContentPane().add(title);
		JButton blackButton=getButton(Color.BLACK);
		blackButton.setText("Black");
		blackButton.setBounds(60, 110, 100, 30);
		getContentPane().add(blackButton);
		JButton whiteButton=getButton(Color.WHITE);
		whiteButton.setText("White");
		whiteButton.setBounds(230, 110, 100, 30);
		getContentPane().add(whiteButton);
	}
	private JButton getButton(Color color) {
		JButton button=new JButton();
		button.setForeground(color);
        button.setBackground(BG_BUTTON_COLOR);
        button.setFont(FONT_BUTTON);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
		return button;
	}
	private JLabel getChooseDialogTitle() {
		JLabel title=new JLabel();
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setBackground(BG_ALL);
		title.setText("Choose your chess color");
        title.setFocusable(false);
        title.setFont(FONT_TEXT);
        title.setBorder(null);
		return title;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedColor=e.getActionCommand();
		
		if(selectedColor.equals("Black"))
		{
			Vars.c.setFirstHand();
		}
		else if(selectedColor.equals("White"))
		{
			Vars.c.setGoteHand();
		}
		forward=true;
		UIFrame ui=new UIFrame(welcomePanel);
		ui.setLocationRelativeTo(null);
		ui.setVisible(true);
		this.setVisible(false);
	}
	
	private void closeHandler(WelcomePanel welcomePanel) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				if(forward==false)
				 welcomePanel.setVisible(true);
			}
		});
	}
		
}
