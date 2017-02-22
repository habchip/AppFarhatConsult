package AppFarhatConsult.ihm;

import java.awt.FileDialog;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Mon bouton");
	private FileDialog fileDialog = new FileDialog(this, "chose a file", FileDialog.LOAD);

	public MainFrame(int with, int height) {
		this.setTitle("App Farhat Consult");
		this.setSize(with, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// Ajout du bouton à notre content pane
		pan.add(bouton);
		this.setContentPane(pan);
		this.setVisible(true);

		fileDialog.setDirectory(".");
		fileDialog.setFile("*.xlsx");
		fileDialog.setVisible(true);
		String filename = fileDialog.getFile();
		if (filename == null)
		  System.out.println("You cancelled the choice");
		else
		  System.out.println("You chose " + filename);
	}
}