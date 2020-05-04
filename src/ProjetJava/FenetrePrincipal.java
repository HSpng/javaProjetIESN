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
    private JMenu menuModif, menuAfficher, listSoft, menuTable;
    private JMenuItem newInstall, listInstall, suprInstall, softFamille, softInstall, listTous;
    private Container cont;
    private JPanel panPrincipal, panConnection;
    private JLabel message, userLab, passLab;
    private static JTextField userText;
    private static JPasswordField passText;
    private JButton boutConnect;
    
    public FenetrePrincipal() {
    	super("Programme Installation"); 
    	
    	setSize(800,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	panPrincipal = new JPanel();
    	
    	message = new JLabel("Bienvenue, veuillez vous connectez à la base de données pour utiliser ce programme.");
    	message.setHorizontalAlignment(SwingConstants.CENTER);
    	message.setFont(new Font("Calibri", Font.PLAIN, 20));
    	
    	boutConnect = new JButton("Connexion");
    	boutConnect.setAlignmentX(CENTER_ALIGNMENT);
    	
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
    	//menuModif.setEnabled(false);
    	//menuAfficher.setEnabled(false);
    	
    	menuModif.setEnabled(true);
    	menuAfficher.setEnabled(true);
    	
    	Box box = Box.createVerticalBox();
    	box.add(message);
    	box.add(boutConnect);
    	
    	panPrincipal.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridy = 0;
    	panPrincipal.add(message,c);
    	c.gridy = 1;
    	panPrincipal.add(boutConnect,c);
    	
    	cont = getContentPane();
    	cont.setLayout(new BorderLayout());
    	cont.add(panPrincipal, BorderLayout.CENTER);
    	setVisible(true);
    	
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
    	
    	//Evenenment menu nouveau formulaire
    	
    	boutConnect.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    int option = JOptionPane.showConfirmDialog(null, panConnection,"Entrez Vos identifiants",JOptionPane.OK_CANCEL_OPTION);
    		    if(option == JOptionPane.OK_OPTION) {
					if(getConnection() == null) {
						JOptionPane.showMessageDialog(null, "Identifiants Invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Connecté !", "Réussi", JOptionPane.INFORMATION_MESSAGE);
						menuModif.setEnabled(true);
				    	menuAfficher.setEnabled(true);
					}
					
				}
    		}
    	});
    	
    	newInstall.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {	
    			cont.removeAll();
    			JLabel titre = new JLabel("Encoder une nouvelle installation");
    			titre.setFont(new Font("Calibri", Font.PLAIN, 17));
    			titre.setHorizontalAlignment(SwingConstants.CENTER);
    			cont.add(titre, BorderLayout.NORTH);
				cont.add(new FormInstall(), BorderLayout.CENTER);
				cont.repaint();
				cont.revalidate();
				
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
    			JScrollPane scrollpane = new JScrollPane(new PanAffichTous());
				cont.add(scrollpane);
				cont.repaint();
				cont.revalidate();
	
    		}
    	});
    	
    	
    }

	public static Connection getConnection() {
    	Connection connect = null;
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






























