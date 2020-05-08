package ProjetJava;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanAffichInstall extends JPanel {

	private static final long serialVersionUID = 1L;
	private String requete;
	private PreparedStatement prepStat;
	private Connection connect;
	TableModelGen model;
	private JTable table;
	private JLabel titre;
	private FenetrePrincipal parent;
	
	public PanAffichInstall(FenetrePrincipal fen) {
		parent = fen;
		try {
			connect = parent.getConnection();
			requete = "select * from Installation";
			prepStat = connect.prepareStatement(requete);
			model = AccessBDGen.creerTableModel(prepStat);
			connect.close();
		}catch(SQLException e) {
			e.getMessage();
		}
		table = new JTable(model);		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane defilant = new JScrollPane(table);
		
		titre = new JLabel("Toutes les installations");
		titre.setFont(new Font("Calibri", Font.PLAIN, 17));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		//defilant.setPreferredSize(new Dimension(750, 400));
		
		setLayout(new BorderLayout());
		add(titre, BorderLayout.PAGE_START);
		add(defilant, BorderLayout.CENTER);
		
	}	
}
