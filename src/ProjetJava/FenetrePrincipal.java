package ProjetJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import accessBD.AccessBDGen;

public class FenetrePrincipal extends JFrame{
	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenu;
    private JMenu menuModif, menuAfficher, menuRecherche;
    private JMenuItem newInstall, listInstall, suprInstall, listSoftFamille, listSectionInstall, listTous;
    private Container cont;
    private JPanel panPrincipal, panConnection;
    private JLabel message, userLab, passLab;
    private static JTextField userText;
    private static JPasswordField passText;
    private JButton boutConnect;
    private Connection connect;
    
    public FenetrePrincipal() {
    	super("Programme Installation"); 
    	
    	setSize(800,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	panPrincipal = new JPanel();
    	
    	message = new JLabel("Bienvenue, veuillez vous connectez à la base de données pour utiliser ce programme.");
    	message.setHorizontalAlignment(SwingConstants.CENTER);
    	message.setFont(new Font("Calibri", Font.PLAIN, 20));
    	
    	boutConnect = new JButton("Connexion");
    	boutConnect.setAlignmentX(CENTER_ALIGNMENT);
    	
    	barreMenu = new JMenuBar();
    	setJMenuBar(barreMenu);
    	
    	menuModif = new JMenu("Modifications");
    	menuAfficher = new JMenu("Afficher");
    	menuRecherche = new JMenu("Recherche");
    	
    	newInstall = new JMenuItem("Nouvelle Installation");
    	listInstall = new JMenuItem("Installations");
    	listTous = new JMenuItem("Toutes les tables");
    	suprInstall = new JMenuItem("Supprimer Installation");
    	listSoftFamille = new JMenuItem("Software par Famille");
    	listSectionInstall = new JMenuItem("Installation par Section");
    	
    	menuModif.add(newInstall);
    	menuModif.addSeparator();
    	menuModif.add(suprInstall);
    	menuAfficher.add(listInstall);
    	menuAfficher.addSeparator();
    	menuAfficher.add(listTous);
    	menuRecherche.add(listSoftFamille);
    	menuRecherche.addSeparator();
    	menuRecherche.add(listSectionInstall);
    	
    	barreMenu.add(menuModif);
    	barreMenu.add(menuAfficher);
    	barreMenu.add(menuRecherche);
    	//menuModif.setEnabled(false);
    	//menuAfficher.setEnabled(false);
    	//menuRecherche.setEnabled(false);
    	
    	panPrincipal.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridy = 0;
    	panPrincipal.add(message,c);
    	c.gridy = 1;
    	panPrincipal.add(boutConnect,c);
    	
    	cont = getContentPane();
    	cont.setLayout(new BorderLayout());
    	cont.add(panPrincipal, BorderLayout.CENTER);
    	
    	
    	//Création du panneau de connexion
    	panConnection = new JPanel();
    	userLab = new JLabel("Utilisateur", SwingConstants.RIGHT);
    	passLab = new JLabel("Mot de passe", SwingConstants.RIGHT);
    	userText = new JTextField();
    	passText = new JPasswordField();
    	
    	panConnection.add(userLab);
    	panConnection.add(userText);
    	panConnection.add(passLab);
    	panConnection.add(passText);
    	panConnection.setLayout(new GridLayout(2,2,4,4));
    	
    	setVisible(true);
    	
    	GestioActionMenu actionMenu = new GestioActionMenu();
    	newInstall.addActionListener(actionMenu);
    	listInstall.addActionListener(actionMenu);
    	listTous.addActionListener(actionMenu);
    	suprInstall.addActionListener(actionMenu);
    	listSoftFamille.addActionListener(actionMenu);
    	listSectionInstall.addActionListener(actionMenu);
    	boutConnect.addActionListener(actionMenu);
    }
    
    private class GestioActionMenu implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		if(e.getSource() == newInstall) {
    			cont.removeAll();
				cont.add(new PanFormInstall(getFen()), BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == listInstall) {
    			cont.removeAll();
				cont.add(new PanAffichInstall(getFen()));
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == listTous) {
    			cont.removeAll();
    			JScrollPane scrollpane = new JScrollPane(new PanAffichTous(getFen()));
    			scrollpane.setBorder(null);
    			scrollpane.getVerticalScrollBar().setUnitIncrement(20);
				cont.add(scrollpane, BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == suprInstall) {
    			cont.removeAll();
				cont.add(new PanSuprInstall(getFen()), BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == listSoftFamille) {
    			cont.removeAll();
				cont.add(new PanFamilleSoft(getFen()), BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == listSectionInstall) {
    			cont.removeAll();
				cont.add(new PanSectionInstall(getFen()), BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
    		}else if(e.getSource() == boutConnect) {
    			 int option = JOptionPane.showConfirmDialog(null, panConnection,"Entrez Vos identifiants",JOptionPane.OK_CANCEL_OPTION);
     		    if(option == JOptionPane.OK_OPTION) {
 					if(getConnection() == null) {
 						JOptionPane.showMessageDialog(null, "Identifiants Invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
 					}else {
 						JOptionPane.showMessageDialog(null, "Connecté !", "Réussi", JOptionPane.INFORMATION_MESSAGE);
 						menuModif.setEnabled(true);
 				    	menuAfficher.setEnabled(true);
 				    	menuRecherche.setEnabled(true);
 				    	boutConnect.setEnabled(false);
 					}	
 				}
    		}
    	}
    }
    
    public FenetrePrincipal getFen() {
    	return this;
    }

	public Connection getConnection() {
    	connect = null;
    	try {
    		//connect = AccessBDGen.connecter("DbInstallations", userText.getText(), new String(passText.getPassword()));
    		connect = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");	
    	}catch(SQLException ex) {
    		ex.getMessage();
    	}
    	return connect;
    }

	public Container getCont() {
		return cont;
	}

}






























