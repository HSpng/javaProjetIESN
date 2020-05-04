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
    private JMenu menuModif, menuAfficher, listSoft, menuTable;
    private JMenuItem newInstall, listInstall, suprInstall, softFamille, softInstall, listTous;
    private Container cont;
    private JLabel message;
    private JScrollPane scrollpane;
    
    public FenetrePrincipal() {
    	super("Programme Installation"); 
    	
    	setSize(800,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
    	message = new JLabel("Bienvenue, veuillez utiliser le menu ci-dessus pour utiliser ce programme.");
    	message.setHorizontalAlignment(SwingConstants.CENTER);
    	message.setFont(new Font("Calibri", Font.PLAIN, 22));
    	
    	barreMenu = new JMenuBar();
    	setJMenuBar(barreMenu);
    	menuModif = new JMenu("Modifications");
    	listSoft = new JMenu("Software");
    	menuAfficher = new JMenu("Afficher");
    	menuTable = new JMenu("Table");
    	
    	newInstall = new JMenuItem("Nouvelle Installation");
    	listInstall = new JMenuItem("Installations");
    	listTous = new JMenuItem("Toutes les tables");
    	suprInstall = new JMenuItem("Supprimer Installation");
    	softFamille = new JMenuItem("Famille de software");
    	softInstall = new JMenuItem("Installation par Section");
    	
    	
    	listSoft.add(softFamille);
    	listSoft.add(softInstall);
    	menuModif.add(newInstall);
    	menuModif.addSeparator();
    	menuModif.add(suprInstall);
    	menuAfficher.add(menuTable);
    	menuAfficher.addSeparator();
    	menuAfficher.add(listSoft);
    	menuTable.add(listTous);
    	menuTable.add(listInstall);
    	
    	barreMenu.add(menuModif);
    	barreMenu.add(menuAfficher);
    	
    	cont = getContentPane();
    	cont.add(message);
    	setVisible(true);
    	
    	//Evenenment menu nouveau formulaire
    	newInstall.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 			
    			cont.removeAll();
				cont.add(new FormInstall());
				cont.repaint();
				cont.revalidate();
				//cont.setLayout(new FlowLayout());
    		}
    	});
    	
    	listInstall.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 			
    			cont.removeAll();
				cont.add(new PanAffichInstall());
				cont.repaint();
				cont.revalidate();
				//cont.setLayout(new FlowLayout());		
    		}
    	});
    	
    	listTous.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 			
    			cont.removeAll();
    			PanAffichTous panTous = new PanAffichTous();
    			JScrollPane scrollpane = new JScrollPane(panTous );
    			//scrollpane.setPreferredSize(panTous.get);
				cont.add(scrollpane);
				cont.repaint();
				cont.revalidate();
				//cont.setLayout(new FlowLayout());		
    		}
    	});
    	
    	
    }

	public static Connection getConnection() {
    	Connection connect;
    	try {
    		connect = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");
    		
    	}catch(SQLException ex) {
    		ex.getMessage();
    		connect = null;
    	}
    	return connect;
    }

	public Container getCont() {
		return cont;
	}

}






























