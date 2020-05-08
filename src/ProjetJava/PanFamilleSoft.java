package ProjetJava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanFamilleSoft extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel comboLab, titre;
	private JComboBox<Object> comboSoftFamily;
	private Object[] familySoftTab;
	private JButton okBout, retourBout;
	private TableModelGen model;
	private JTable table;
	private FenetrePrincipal parent;
	private JPanel pan;
	
	public PanFamilleSoft(FenetrePrincipal fen) {
		parent = fen;
		try {
			familySoftTab = AccessBDGen.creerListe1Colonne(parent.getConnection().prepareStatement("select Libelle from FamilleSoftware"));
			comboLab = new JLabel("Sélectionnez une famille de software: ");
			comboSoftFamily = new JComboBox<Object>(familySoftTab);
			okBout = new JButton("OK");
			retourBout = new JButton("Retour");
			
			parent.getConnection().close();
		}catch(SQLException e) {
			e.getMessage();
		}
		titre = new JLabel("Installation par Section");
		titre.setFont(new Font("Calibri", Font.PLAIN, 17));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		pan = new JPanel();
		
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(7, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		pan.add(comboLab,c);
		c.gridx = 1;
		pan.add(comboSoftFamily,c);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		pan.add(okBout,c);
		
		setLayout(new BorderLayout());
		add(pan, BorderLayout.CENTER);
		add(titre, BorderLayout.NORTH);
		
		GestioBout gestBout = new GestioBout();
		okBout.addActionListener(gestBout);
		retourBout.addActionListener(gestBout);
	}
	
	private class GestioBout implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okBout) {
				afficheSoft();
			}else if(e.getSource() == retourBout) {
				parent.getCont().removeAll();
				parent.getCont().add(new PanFamilleSoft(parent));
				parent.getCont().repaint();
				parent.getCont().revalidate();
			}
		}
	}
	
	private int getIdFamSoft() {
		int id = 0;
		try {
			String soft = String.valueOf(comboSoftFamily.getSelectedItem());
			PreparedStatement prepStat = parent.getConnection().prepareStatement("select IdFamSoft from FamilleSoftware where Libelle = ?");
			prepStat.setString(1, soft);
			ResultSet result = prepStat.executeQuery();
			while(result.next()) {
				id = result.getInt(1);
			}
			parent.getConnection().close();
		}catch(SQLException e) {
			
		}
		return id;
	}
	
	private void afficheSoft() {
		try {
			PreparedStatement prepStat = parent.getConnection().prepareStatement("select * from Software where CodeFourn in (select CodeFourn from Fournisseur where NomContactComm is null) and IdFamSoft = ?");
			prepStat.setInt(1, getIdFamSoft());
			if(prepStat.executeQuery().first()) {
				model = AccessBDGen.creerTableModel(prepStat);
				table = new JTable(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(600, 175));
				
				pan.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.insets = new Insets(7, 5, 5, 5);
				
				pan.removeAll();
				c.gridx = 0;
				c.gridy = 0;
				pan.add(scrollPane,c);
				c.gridy = 1;
				pan.add(retourBout,c);
				pan.repaint();
				pan.revalidate();
			}else {
				JOptionPane.showMessageDialog(null, "Il n'y a pas de résultat pour ce choix", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			parent.getConnection().close();
		}catch(SQLException e) {
			e.getMessage();
		}
	}
}






































