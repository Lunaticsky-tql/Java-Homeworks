package chess;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RecordFrame extends JFrame implements ActionListener{

	public static final Font FONT_BUTTON=new Font("Arial",Font.PLAIN,15);
	private static final long serialVersionUID = 1L;
	private JButton lastStep;
	private JButton nextStep;
	public RecordFrame()
	{
		initUIFrame();
		changeLastBtnState(false);
	}

	private void initUIFrame() {
		 this.setTitle("Record");
	        this.setResizable(false);
	        this.setLayout(null); 
	        	 this.setSize(450, 720);
	        	 getContentPane().setBackground(WelcomePanel.BG_ALL);
	        	 RecordModeStarter.rv.setBounds(20, 0, 400, 600);
	        	 getContentPane().add(RecordModeStarter.rv);
	        	 JPanel commandPanel=getCommandPanel();
	        	 commandPanel.setBounds(0, 600, 450, 100);
	        	 getContentPane().add(commandPanel);
	        	 
	        
	}
	private JPanel getCommandPanel() {
		JPanel commandPanel=new JPanel();
		commandPanel.setBackground(WelcomePanel.BG_ALL);
		commandPanel.setLayout(null);
		lastStep=getButton();
		lastStep.setBounds(150,0,100,25);
		lastStep.setText("Last step");
		commandPanel.add(lastStep);
		nextStep=getButton();
		nextStep.setBounds(270,0,100,25);
		nextStep.setText("Next step");
		commandPanel.add(nextStep);
		return commandPanel;
	}

	private JButton getButton() {
		JButton button=new JButton();
		button.setForeground(Color.BLACK);
        button.setBackground(WelcomePanel.BG_BUTTON_COLOR);
        button.setFont(FONT_BUTTON);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
		return button;
	}

	public void changeLastBtnState(boolean enable) {
		lastStep.setEnabled(enable);
	}	
	public void changeNextBtnState(boolean enable) {
		nextStep.setEnabled(enable);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		String curBtn=e.getActionCommand();
		if(curBtn.equals("Last step"))
		{
			RecordModeStarter.rm.paintLastStep();
		}
		else if (curBtn.equals("Next step")) {
			RecordModeStarter.rm.paintNextStep();
		}
		
	}	
}
