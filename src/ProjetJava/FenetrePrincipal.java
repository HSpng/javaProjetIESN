package ProjetJava;

import java.awt.Container;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import accessBD.AccessBDGen;

public class FenetrePrincipal extends JFrame{
	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenu;
    private JMenu menuModif, menuAfficher, listSoft;
    private JMenuItem newInstall, listInstall, suprInstall, softFamille, softInstall;
    private Container cont;
    private JLabel message;
    private FormInstall formulaireInstall;
    
    public FenetrePrincipal() {
    	super("Programme Installation"); 
    	
    	setSize(800,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
    	message = new JLabel("Bienvenue, veuillez utiliser le menu ci-dessus pour utiliser ce programme.");
    	message.setVerticalAlignment(SwingConstants.CENTER);
    	message.setHorizontalAlignment(SwingConstants.CENTER);
    	message.setFont(new Font("Calibri", Font.PLAIN, 19));
    	
    	barreMenu = new JMenuBar();
    	setJMenuBar(barreMenu);
    	menuModif = new JMenu("Modifications");
    	listSoft = new JMenu("Software");
    	menuAfficher = new JMenu("Afficher");
    	
    	newInstall = new JMenuItem("Nouvelle Installation");
    	listInstall = new JMenuItem("Installations");
    	suprInstall = new JMenuItem("Supprimer Installation");
    	softFamille = new JMenuItem("Famille de software");
    	softInstall = new JMenuItem("Installation par Section");
    	
    	listSoft.add(softFamille);
    	listSoft.add(softInstall);
    	menuModif.add(newInstall);
    	menuModif.addSeparator();
    	menuModif.add(suprInstall);
    	menuAfficher.add(listInstall);
    	menuAfficher.addSeparator();
    	menuAfficher.add(listSoft);
    	
    	barreMenu.add(menuModif);
    	barreMenu.add(menuAfficher);
    	
    	cont = getContentPane();
    	cont.add(message);
    	setVisible(true);
    	
    	//Evenenment menu nouveau formulaire
    	newInstall.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cont.removeAll();
    			formulaireInstall = new FormInstall();    			
    			cont.add(formulaireInstall);
    			cont.repaint();
    			cont.revalidate();
    			cont.setLayout(new FlowLayout());
    		}
    	});
    	
    }
    
    public static Connection connection() {
    	try {
    		Connection connect = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");
    		return connect;
    	}catch(SQLException ex) {
    		ex.getMessage();
    		return null;
    	}
    }

}






























