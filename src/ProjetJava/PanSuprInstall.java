package ProjetJava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanSuprInstall extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel consigneLab, dateLab, titre;
	private JTextField jourText, moisText, anneeText;
	private JButton	okBout, delBout;
	private JTable table;
	private TableModelGen model;
	private JPanel panSupr;
	private FenetrePrincipal parent;
	
	public PanSuprInstall(FenetrePrincipal fen) {
		parent = fen;
		consigneLab = new JLabel("Selectionner une ligne à supprimer");
		dateLab = new JLabel("Date de l'installation à supprimer: ");
		jourText = new JTextField("Jour");
		moisText = new JTextField("Mois");
		anneeText = new JTextField("Annee");
		
		panSupr = new JPanel();
		titre = new JLabel("Supprimer une installation");
		titre.setFont(new Font("Calibri", Font.PLAIN, 17));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		okBout = new JButton("OK");
		delBout = new JButton("Supprimer");
		
		//Layout pour la date et bouton OK
		panSupr.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(7, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		panSupr.add(dateLab,c);
		c.gridx = 1;
		panSupr.add(jourText,c);
		c.gridx = 2;
		panSupr.add(moisText,c);
		c.gridx = 3;
		panSupr.add(anneeText,c);
		c.gridx = 0;
		c.gridy = 2;
		//c.gridwidth = 2;
		c.anchor = GridBagConstraints.LINE_END;
		panSupr.add(okBout,c);
		
		setLayout(new BorderLayout());
		add(panSupr, BorderLayout.CENTER);
		add(titre, BorderLayout.NORTH);
		
		GestioBoutOk gestBoutOk	= new GestioBoutOk();
		okBout.addActionListener(gestBoutOk);
		
		GestioBoutDel gestBoutDel = new GestioBoutDel();
		delBout.addActionListener(gestBoutDel);
		
		GestioViderField viderField = new GestioViderField();
		jourText.addMouseListener(viderField);
		moisText.addMouseListener(viderField);
		anneeText.addMouseListener(viderField);
	}
	
	private class GestioViderField implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == jourText) {
				jourText.setText("");
			}else if(e.getSource() == moisText) {
				moisText.setText("");
			}else if(e.getSource() == anneeText) {
				anneeText.setText("");
			}
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	private class GestioBoutOk implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(verifDate(jourText, moisText, anneeText)) {
					affichInstall();
				}
			} catch (DateException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class GestioBoutDel implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
				int id =(int)table.getValueAt(row, 0);
				int option = JOptionPane.showConfirmDialog(null,"Etes-vous sûr de vouloir supprimer cette installation ?","Attention", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION) {
					try {
						String requete = "DELETE From Installation Where IdInstallation = ?";
						PreparedStatement prepstat = parent.getConnection().prepareStatement(requete);
						prepstat.setInt(1, id);
					
						if(prepstat.executeUpdate() > 0) {
							affichInstall();
							JOptionPane.showMessageDialog(null, "Entrée supprimer", "Bravo", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Une Erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
						parent.getConnection().close();					
					}catch(SQLException eSql) {
						eSql.getMessage();
					}
			
				}
			}
		}
	}
	
	
	//Methode pour afiicher les installation pour la date entrée  par l'user
	private void affichInstall() {
		try {
			String requete = "select * from Installation where CodeSoftware in (select CodeSoftware from Software where CodeInstallation is null) and DateInstallation = ?";
			PreparedStatement prepStat = parent.getConnection().prepareStatement(requete);
			prepStat.setDate(1, getDate(jourText,moisText,anneeText));			
			model = AccessBDGen.creerTableModel(prepStat);
			ResultSet result = prepStat.executeQuery();
			if(result.first()) {  						//Si return false, la date n'existe pas dans la DB, si return true, la date existe
				table = new JTable(model);			
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(600, 200));

				panSupr.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.insets = new Insets(7, 5, 5, 5);
				
				panSupr.removeAll();
				c.gridx = 0;
				c.gridy = 0;
				panSupr.add(scrollPane,c);
				c.gridx = 0;
				c.gridy = 1;
				panSupr.add(consigneLab,c);
				c.gridx = 0;
				c.gridy = 3;
				panSupr.add(delBout,c);
				panSupr.revalidate();
				panSupr.repaint();

			}else {
				JOptionPane.showMessageDialog(null, "Il n'y a pas d'installation pour cette date", "Erreur", JOptionPane.ERROR_MESSAGE);	//message d'erreur si la date n'existe pas
			}
			
			parent.getConnection().close();
		}catch(SQLException e) {
			e.getMessage();
		}
		
	}
	
	private boolean verifDate (JTextField jour, JTextField mois, JTextField annee) throws DateException {
		boolean verif = false;
		try {
			int jourInt = Integer.parseInt(jour.getText());
			int moisInt = Integer.parseInt(mois.getText());
			int anneeInt = Integer.parseInt(annee.getText());
			
			//Si le jour et le mois sont correct
			if(jourInt < 1 || jourInt > 31 || moisInt < 1 || moisInt > 12 || anneeInt < 2000 || anneeInt > 2100) {
				throw new DateException(jourInt, moisInt, anneeInt);
				
			}else {
				verif = true;	
			}
			
		}catch( java.lang.NumberFormatException ex1) {
			//Si la valeur rentrée n'est pas un INT, une exception est généré et on renvoie NULL
			JOptionPane.showMessageDialog(null, "Date invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		return verif;
		
	}
	
	//Methode pour retourner une date sous le format SQL
	private java.sql.Date getDate (JTextField jour, JTextField mois, JTextField annee){
		java.sql.Date sqlD;
		
		int jourInt = Integer.parseInt(jour.getText());
		int moisInt = Integer.parseInt(mois.getText());
		int anneeInt = Integer.parseInt(annee.getText());
		
		GregorianCalendar date1 = new GregorianCalendar(anneeInt, moisInt - 1, jourInt);
		sqlD = new java.sql.Date(date1.getTimeInMillis());
		return sqlD;
	}
	
	
}




















