package ProjetJava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanAffichTous extends JPanel {
	private static final long serialVersionUID = 1L;
	private String requetes[] = {"select * from Professeur", "select * from Section","select * from ResponsableReseaux","select * from Installation","select * from AnneeEtude","select * from Fournisseur","select * from FamilleSoftware","select * from OS","select * from Software","select * from Editeur","select * from TypePC", "select * from UtilisationSoftware","select * from SoftwarePreinstalle"}; 
	private PreparedStatement prepStat;
	TableModelGen model;
	private JTable table;
	private JLabel nomTable, titre;
	private JPanel	panTables;
	private FenetrePrincipal parent;
	private int hauteurMax;
	
	public PanAffichTous(FenetrePrincipal fen) {
		parent = fen;
		panTables = new JPanel();
		
		titre = new JLabel("Toutes les Tables");
		titre.setFont(new Font("Calibri", Font.PLAIN, 17));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		hauteurMax = 0;
		Box box = Box.createVerticalBox();
		try {
			for(String requete : requetes) {
				prepStat = parent.getConnection().prepareStatement(requete);
				model= AccessBDGen.creerTableModel(prepStat);
				
				table = new JTable(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane defilant = new JScrollPane(table);
				int hauteur = table.getPreferredSize().height;
				hauteurMax += hauteur + 42;
				defilant.setPreferredSize(new Dimension(550, hauteur + 23));
				
				nomTable = new JLabel("Table "+ requete.substring(14) );
				
				box.add(nomTable);
				box.add(defilant);
			}
			
			
			setPreferredSize(new Dimension(550, hauteurMax));
			setLayout(new BorderLayout());
			panTables.add(box);
			add(panTables, BorderLayout.CENTER);
			add(titre, BorderLayout.NORTH);
			parent.getConnection().close();
		}catch(SQLException e) {
			e.getMessage();
		}
		
	}	
}





















