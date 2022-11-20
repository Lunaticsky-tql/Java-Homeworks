package drawing_board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxEditor;


public class Setting extends JDialog implements ActionListener{
	Color[] colors = {Color.white,Color.black,Color.red,Color.green,Color.blue,Color.yellow};
	private static final long serialVersionUID = 1L;
	public static final int TRIANGLE=0;
	public static final int LINE=1;
	public static final int OVAL=2;
	public static final int SQUARE=3;
	public static final int TEXT=4;
	private JLayeredPane content;
	private JLabel backLabel;
	private JLabel ovalLabel;
	private JLabel triaLabel;
	private JLabel lineLabel;
	private JLabel textLabel;
	private JLabel rectLabel;
	private JComboBox<Color> backColor=new JComboBox<Color>(colors);
	private JComboBox<Color> ovalColor=new JComboBox<Color>(colors);
	private JComboBox<Color> triaColor=new JComboBox<Color>(colors);
	private JComboBox<Color> lineColor=new JComboBox<Color>(colors);
	private JComboBox<Color> textColor=new JComboBox<Color>(colors);
	private JComboBox<Color> rectColor=new JComboBox<Color>(colors);
	private JComboBox<String> ovalSize;
	private JComboBox<String> triaSize;
	private JComboBox<String> lineSize;
	private JComboBox<String> rectSize;
	private JComboBox<String> textFace;
	private JComboBox<String> textStyle;
	private JPanel textSize;
	private JPanel basicArrowPanel;
	private JTextField numberTextField;
	// button
	private JButton backColorButton;
	private JButton ovalColorButton;
	private JButton triaColorButton;
	private JButton lineColorButton;
	private JButton textColorButton;
	private JButton rectColorButton;
	private JCheckBox ovalCheckBox;
	private JCheckBox triaCheckBox;
	private JCheckBox rectCheckBox;
	private JButton okButton;
	private JButton quitButton;
	private DrawingBoard board;
	ComboBoxRenderer backRenderer = new ComboBoxRenderer();
	ComboBoxRenderer ovalRenderer = new ComboBoxRenderer();
	ComboBoxRenderer triaRenderer = new ComboBoxRenderer();
	ComboBoxRenderer lineRenderer = new ComboBoxRenderer();
	ComboBoxRenderer textRenderer = new ComboBoxRenderer();
	ComboBoxRenderer rectRenderer = new ComboBoxRenderer();
	private static final String STROKES[] = new String[] { "2.0px", "2.5px", "3.0px", "4.0px", "5.0px", "6.0px" };
	private static final String FACES[] = new String[] { "楷体", "隶书", "宋体", "华文彩云", "华文行楷", "微软雅黑" };
	private static final String STYLE[] = new String[] { "Plain", "Bold", "Italic" };
	private static final Font FONT = new Font("微软雅黑", Font.PLAIN, 12);

	public Setting(DrawingBoard board) {
		this.board = board;
		initGUI();
	}

	private void initGUI() {
		setModal(true);
		setSize(600, 400);
		setTitle("Setting");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		content = new JLayeredPane();
		content.setLayout(null);
		getContentPane().add(content);
		
		//initialization of panels
		//As I used absolute position for each panel, so 
		//I put them in a large function rather than using small
		//parts of getters and setters which is more elegant
		//in order to adjust there 
		//relative position more easily.
		
		backLabel = new JLabel("background:");
		backLabel.setBounds(20, 20, 80, 25);
		backLabel.setFont(FONT);
		content.add(backLabel);
		
		
		backColor.setBounds(120, 20, 80, 25);
		backColorButton=new JButton();
		backColorButton.setBounds(120, 20, 60, 25);
		backColorButton.setBackground(board.getBackground());
		backColor.setSelectedItem(board.getBackground());
		backColorButton.setFocusPainted(false);
		backColorButton.setEnabled(false);
		backRenderer.setPreferredSize(new Dimension(40, 25));
		backColor.addActionListener(this);
		backColor.setRenderer(backRenderer);
		content.add(backColorButton,JLayeredPane.DRAG_LAYER);
		content.add(backColor,JLayeredPane.DEFAULT_LAYER);
	
		
		ovalLabel = new JLabel("oval:");
		ovalLabel.setBounds(40, 60, 55, 25);
		ovalLabel.setFont(FONT);
		content.add(ovalLabel);

		ovalColorButton=new JButton();
		ovalColorButton.setBounds(120, 60, 60, 25);
		ovalColorButton.setBackground(board.colorMap.get(OVAL));
		ovalColor.setSelectedItem(board.colorMap.get(OVAL));
		ovalColorButton.setFocusPainted(false);
		ovalColorButton.setEnabled(false);
		ovalColor.setBounds(120, 60, 80, 25);
		ovalRenderer.setPreferredSize(new Dimension(40, 25));
		ovalColor.setRenderer(ovalRenderer);
		ovalColor.addActionListener(this);
		content.add(ovalColorButton,JLayeredPane.DRAG_LAYER);
		content.add(ovalColor,JLayeredPane.DEFAULT_LAYER);


		ovalSize = new JComboBox<String>(STROKES);
		ovalSize.setBounds(240, 60, 80, 25);
		ovalSize.setFont(FONT);
		ovalSize.setSelectedItem(transToString(board.strokeMap.get(OVAL)));
		ovalSize.addActionListener(this);
		content.add(ovalSize);
		
		ovalCheckBox=new JCheckBox("fill");
		ovalCheckBox.addActionListener(this);
		ovalCheckBox.setSelected(board.fillMap.get(OVAL));
		ovalCheckBox.setBounds(360, 60, 80, 25);
		content.add(ovalCheckBox);

		triaLabel = new JLabel("triangle:");
		triaLabel.setBounds(30, 100, 55, 25);
		triaLabel.setFont(FONT);
		content.add(triaLabel);
		
		extracted();

		triaSize = new JComboBox<String>(STROKES);
		triaSize.setBounds(240, 100, 80, 25);
		triaSize.setFont(FONT);
		triaSize.setSelectedItem(transToString(board.strokeMap.get(TRIANGLE)));
		triaSize.addActionListener(this);
		content.add(triaSize);
		
		triaCheckBox=new JCheckBox("fill");
		triaCheckBox.addActionListener(this);
		triaCheckBox.setSelected(board.fillMap.get(TRIANGLE));
		triaCheckBox.setBounds(360, 100, 80, 25);
		content.add(triaCheckBox);

		rectLabel = new JLabel("rectangle:");
		rectLabel.setBounds(25, 140, 70, 25);
		rectLabel.setFont(FONT);
		content.add(rectLabel);
		
		rectColorButton=new JButton();
		rectColorButton.setBounds(120, 140, 60, 25);
		rectColorButton.setBackground(board.colorMap.get(SQUARE));
		rectColor.setSelectedItem(board.colorMap.get(SQUARE));
		rectColorButton.setFocusPainted(false);
		rectColorButton.setEnabled(false);
		rectColor.setBounds(120, 140, 80, 25);
		rectRenderer.setPreferredSize(new Dimension(40, 25));
		rectColor.setRenderer(rectRenderer);
		rectColor.setPreferredSize(new Dimension(50, 25));
		rectColor.addActionListener(this);
		content.add(rectColor,JLayeredPane.DEFAULT_LAYER);
		content.add(rectColorButton,JLayeredPane.DRAG_LAYER);

		rectSize = new JComboBox<String>(STROKES);
		rectSize.setBounds(240, 140, 80, 25);
		rectSize.setFont(FONT);
		rectSize.setSelectedItem(transToString(board.strokeMap.get(SQUARE)));
		rectSize.addActionListener(this);
		content.add(rectSize);
		
		rectCheckBox=new JCheckBox("fill");
		rectCheckBox.setSelected(board.fillMap.get(SQUARE));
		rectCheckBox.addActionListener(this);
		rectCheckBox.setBounds(360, 140, 80, 25);
		content.add(rectCheckBox);		
		
		lineLabel = new JLabel("line:");
		lineLabel.setBounds(40, 180, 55, 25);
		lineLabel.setFont(FONT);
		content.add(lineLabel);
		
		lineColorButton=new JButton();
		lineColorButton.setBounds(120, 180, 60, 25);
		lineColorButton.setBackground(board.colorMap.get(LINE));
		lineColor.setSelectedItem(board.colorMap.get(LINE));
		lineColorButton.setFocusPainted(false);
		lineColorButton.setEnabled(false);
		lineRenderer.setPreferredSize(new Dimension(40, 25));
		lineColor.setBounds(120, 180, 80, 25);
		lineColor.setPreferredSize(new Dimension(50, 25));
		lineColor.setRenderer(lineRenderer);
		lineColor.addActionListener(this);
		content.add(lineColor,JLayeredPane.DEFAULT_LAYER);
		content.add(lineColorButton,JLayeredPane.DRAG_LAYER);	
		
		lineSize = new JComboBox<String>(STROKES);
		lineSize.setBounds(240, 180, 80, 25);
		lineSize.setFont(FONT);
		lineSize.setSelectedItem(transToString(board.strokeMap.get(LINE)));
		lineSize.addActionListener(this);
		content.add(lineSize);
		
		textLabel = new JLabel("text:");
		textLabel.setBounds(40, 220, 55, 25);
		textLabel.setFont(FONT);
		content.add(textLabel);

		textColorButton=new JButton();
		textColorButton.setBounds(120, 220, 60, 25);
		textColorButton.setBackground(board.colorMap.get(TEXT));
		textColor.setSelectedItem(board.colorMap.get(TEXT));
		textColorButton.setFocusPainted(false);
		textColorButton.setEnabled(false);
		textRenderer.setPreferredSize(new Dimension(40, 25));
		textColor.setBounds(120, 220, 80, 25);
		textColor.setPreferredSize(new Dimension(50, 25));
		textColor.setRenderer(textRenderer);
		textColor.addActionListener(this);
		content.add(textColorButton,JLayeredPane.DRAG_LAYER);
		content.add(textColor,JLayeredPane.DEFAULT_LAYER);
		
		textFace = new JComboBox<String>(FACES);
		textFace.setBounds(240, 220, 80, 25);
		textFace.setFont(FONT);
		textFace.setSelectedItem(board.textFace);
		textFace.addActionListener(this);
		content.add(textFace);

		textStyle = new JComboBox<String>(STYLE);
		textStyle.setBounds(360, 220, 80, 25);
		textStyle.setFont(FONT);
		textStyle.setSelectedItem(getStyleStr(board.textStyle));
		textStyle.addActionListener(this);
		content.add(textStyle);

		textSize=new JPanel(new BorderLayout());
		textSize.setBounds(480, 220, 80, 25);
		textSize.add(getNumberTextField(),BorderLayout.CENTER);
		textSize.add(getBasicArrowPanel(),BorderLayout.EAST);
		content.add(textSize);
		
		okButton = new JButton("Sure");
		okButton.setBounds(100, 270, 80, 30);
		okButton.setFont(FONT);
		okButton.addActionListener(this);
		content.add(okButton);

		quitButton = new JButton("Cancel");
		quitButton.setBounds(350, 270, 80, 30);
		quitButton.setFont(FONT);
		quitButton.addActionListener(this);
		content.add(quitButton);
	}

	private void extracted() {
		triaColorButton=new JButton();
		triaColorButton.setBounds(120, 100, 60, 25);
		triaColorButton.setBackground(board.colorMap.get(TRIANGLE));
		triaColor.setSelectedItem(board.colorMap.get(TRIANGLE));
		triaColorButton.setFocusPainted(false);
		triaColorButton.setEnabled(false);
		triaColor.setBounds(120, 100, 80, 25);
		triaRenderer.setPreferredSize(new Dimension(40, 25));
		triaColor.setRenderer(triaRenderer);
		triaColor.setPreferredSize(new Dimension(50, 25));
		triaColor.addActionListener(this);
		content.add(triaColor,JLayeredPane.DEFAULT_LAYER);
		content.add(triaColorButton,JLayeredPane.DRAG_LAYER);
	}


	//parts of text size selector
	private Component getBasicArrowPanel() {
		if (basicArrowPanel==null) {
			basicArrowPanel=new JPanel(new GridLayout(2, 1));
			BasicArrowButton upBasicArrowButton=new BasicArrowButton(BasicArrowButton.NORTH);
			upBasicArrowButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					numberTextField.setText(String.valueOf(Integer.parseInt(numberTextField.getText())+1));
				}
			});
			BasicArrowButton downBasicArrowButton=new BasicArrowButton(BasicArrowButton.NORTH);
			downBasicArrowButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					numberTextField.setText(String.valueOf(Integer.parseInt(numberTextField.getText())-1));
				}
			});
			upBasicArrowButton.setBorder(null);
			basicArrowPanel.add(upBasicArrowButton);
			basicArrowPanel.add(downBasicArrowButton);
		}
		return basicArrowPanel;
	}

	private Component getNumberTextField() {
		if (numberTextField==null) {
			numberTextField =new JTextField(String.valueOf(board.textSize));
			numberTextField.setHorizontalAlignment(JTextField.LEFT);;
			numberTextField.setBackground(new Color(238, 238, 238));
			numberTextField.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
			numberTextField.setFocusable(false);
			numberTextField.setFont(FONT);
		}
		return numberTextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource(); 
			Color color = (Color)cb.getSelectedItem();
			backColorButton.setBackground(color);
		}
		if (e.getSource() == ovalColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource(); 
			Color color = (Color)cb.getSelectedItem();
			ovalColorButton.setBackground(color);
		}
		if (e.getSource() == triaColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource(); 
        	Color color = (Color)cb.getSelectedItem();
        	triaColorButton.setBackground(color);
		}
		if (e.getSource() == lineColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource(); 
        	Color color = (Color)cb.getSelectedItem();
        	lineColorButton.setBackground(color);
		}
		if (e.getSource() == textColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource();
        	Color color = (Color)cb.getSelectedItem();
        	textColorButton.setBackground(color);
        	
		}
		if (e.getSource() == rectColor) {
			JComboBox<Color> cb = (JComboBox<Color>) e.getSource();
        	Color color = (Color)cb.getSelectedItem();
        	rectColorButton.setBackground(color);
        	
		}
		if (e.getSource() == okButton) {
			// color
			board.setBackground((Color) backColor.getSelectedItem());
			board.colorMap.replace(OVAL, (Color) ovalColor.getSelectedItem());
			board.colorMap.replace(TRIANGLE, (Color) triaColor.getSelectedItem());
			board.colorMap.replace(LINE, (Color) lineColor.getSelectedItem());
			board.colorMap.replace(TEXT, (Color) textColor.getSelectedItem());
			board.colorMap.replace(SQUARE, (Color) rectColor.getSelectedItem());
			//stroke
			board.strokeMap.replace(OVAL, transToFloat(ovalSize));
			board.strokeMap.replace(TRIANGLE, transToFloat(triaSize));
			board.strokeMap.replace(LINE, transToFloat(lineSize));
			board.strokeMap.replace(SQUARE, transToFloat(rectSize));
			//fill
			board.fillMap.replace(OVAL, ovalCheckBox.isSelected());
			board.fillMap.replace(TRIANGLE, triaCheckBox.isSelected());
			board.fillMap.replace(SQUARE, rectCheckBox.isSelected());
			board.textFace = String.valueOf(textFace.getSelectedItem());
			if (String.valueOf(textStyle.getSelectedItem()).equals("Plain")) {
				board.textStyle = Font.PLAIN;
			}
			if (String.valueOf(textStyle.getSelectedItem()).equals("Bold")) {
				board.textStyle = Font.BOLD;
			}
			if (String.valueOf(textStyle.getSelectedItem()).equals("Italic")) {
				board.textStyle = Font.ITALIC;
			}
			board.textSize = Integer.valueOf(String.valueOf(numberTextField.getText()));
			// close
			dispose();
		}
		if (e.getSource() == quitButton) {
			dispose();
		}
	}

	//tools the selections needed
	private String transToString(float value) {
		String temp = String.valueOf(value);
		return temp + "px";
	}

	private float transToFloat(Object object) {
		String temp = ((JComboBox) object).getSelectedItem().toString();
		return Float.parseFloat(temp.substring(0, temp.length() - 2));
	}

	private String getStyleStr(int style) {
		if (style == Font.PLAIN) {
			return "Plain";
		}
		if (style == Font.BOLD) {
			return "Bold";
		}
		if (style == Font.ITALIC) {
			return "Italic";
		}
		return null;
	}

}
