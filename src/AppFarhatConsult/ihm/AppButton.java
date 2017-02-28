package AppFarhatConsult.ihm;

import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import AppFarhatConsult.ExcelServices.ExcelInReader;
import AppFarhatConsult.ExcelServices.ExcelRefReader;
import AppFarhatConsult.ExcelServices.ExcelWriter;

@SuppressWarnings("serial")
public class AppButton extends JButton implements MouseListener {
	// Defines for the application
	public final static String fileRefName   = "ressources/BDDPDI.xlsx";
	public final static String fileOutName   = "ARGI.xlsx";
	public final static String sheetOutName  = "feuille";

	public final static String separator     = ",";
	public final static int lineToParseIn    = 1;
	
	public final static int TYPE_IMPORTE = 0;
	public final static int TYPE_GEN = 1;

	private int type;
	private Frame mainFrame;
	private static String fileName = "";

	public AppButton(ImageIcon icon, int type, Frame mainFrame) {
		super(icon);
		this.type = type;
		this.addMouseListener(this);
		this.mainFrame = mainFrame;
	}

	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent event) {
		if (this.type == TYPE_IMPORTE) {
			FileDialog fileDialog = new FileDialog(this.mainFrame, "Chose a file", FileDialog.LOAD);
			fileDialog.setDirectory(".");
			fileDialog.setFile("*.xlsx");
			fileDialog.setVisible(true);
			String choice = fileDialog.getFile();
			if (choice != null)
				this.fileName = fileDialog.getDirectory() + choice;

		} else if (this.type == TYPE_GEN) {
			if (this.fileName == "") {
				JOptionPane.showMessageDialog(this.mainFrame, "Attention vous devez choisir un fichier d'entré !");
			} else {
				try {
 					ExcelInReader fileParsor = new ExcelInReader(this.fileName);
					ExcelWriter excelWriter = new ExcelWriter(fileOutName, sheetOutName);
					ExcelRefReader excelReader = new ExcelRefReader(fileRefName);
					String[] keywords = fileParsor.Parse(lineToParseIn, separator);
					int lineNumber = 2;

					// For each keyword found, write a line
					for (String keyword : keywords) {
						try {
							String[] excelLine = excelReader.read(keyword.trim());
							excelWriter.addLine(excelLine, lineNumber);
							lineNumber++;
						} catch (IllegalArgumentException e) {
							System.out.println("Wrong keyword : " + keyword);
						}
					}

					excelWriter.writeAndClose();
					JOptionPane.showMessageDialog(this.mainFrame, "Fichier généré avec succès");
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
					JOptionPane.showMessageDialog(this.mainFrame, "ERREUR " + e.getMessage());
				}
			}
		} else {
			throw new IllegalArgumentException("Undefined button type");
		}
	}

	public void mouseEntered(MouseEvent event) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent event) {
		setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}
}
