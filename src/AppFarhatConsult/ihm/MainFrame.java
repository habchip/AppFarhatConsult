package AppFarhatConsult.ihm;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	// HMI configurations
	private final static int buttonRadius = 220;
	private final static int logoWith = 320;
	private final static int logoHeight = 220;
	private final static int leftMargin = 150;
	private final static int rightMargin = 150;
	private final static int topMargin = 50;
	private final static int bottomMargin = 150;
	
	// Visual objects
	private JPanel pan = new JPanel();
	
	private ImageIcon importe = new ImageIcon("ressources/import.jpg");
	private ImageIcon gen = new ImageIcon("ressources/gen.jpg");
	private ImageIcon logo = new ImageIcon("ressources/LOGO.jpg");
	
	private AppButton buttonImporte = new AppButton(importe, AppButton.TYPE_IMPORTE, this);
	private AppButton buttonGen = new AppButton(gen, AppButton.TYPE_GEN, this);
	
	private JLabel importeLabel = new JLabel("IMPORTER LE PDD");
	private JLabel genLabel = new JLabel("GENERER L'ARGUMENTAIRE");
	private JLabel logoLabel = new JLabel(logo);
	

	public MainFrame(int widthFrame, int heightFrame) {
		// Main frame configurations
		this.setTitle("App Farhat Consult");
		this.setSize(widthFrame, heightFrame);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		// Buttons configurations
		pan.setLayout(null);
		buttonImporte.setBounds(leftMargin, heightFrame - bottomMargin - buttonRadius - 5, buttonRadius, buttonRadius);
		buttonImporte.setBorderPainted(false);
		buttonGen.setBounds(widthFrame - buttonRadius - rightMargin, heightFrame - bottomMargin - buttonRadius, buttonRadius, buttonRadius);
		buttonGen.setBorderPainted(false);
		
		// Label configurations
		Font labelFont = new Font("ARIAL", Font.BOLD, 18);
		importeLabel.setBounds(leftMargin + 20, heightFrame - bottomMargin , buttonRadius, 20);
		importeLabel.setFont(labelFont);
		importeLabel.setForeground(Color.GRAY);
		genLabel.setBounds(widthFrame - buttonRadius - rightMargin - 20, heightFrame - bottomMargin , buttonRadius + 50, 20);
		genLabel.setFont(labelFont);
		genLabel.setForeground(Color.GRAY);
		logoLabel.setBounds((widthFrame - logoWith) / 2, topMargin, logoWith, logoHeight);
		
		// Add button and label
		pan.setBackground(Color.WHITE);
		pan.add(buttonImporte);
		pan.add(buttonGen);
		pan.add(importeLabel);
		pan.add(genLabel);
		pan.add(logoLabel);
		this.setContentPane(pan);
		this.setVisible(true);
	}
}