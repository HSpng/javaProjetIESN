package ProjetJava;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.GregorianCalendar;

public class FormInstall extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel idInstall, date, commentaire, dureeInstall, refProcedure, dateValidation, softLab, osLab, reseauLab;
	private JTextField textIdInstall, textJour, textMois, textAnnee, textCommentaire, textDureeInstall, textRefProcedure, textJourValid, textMoisValid, textAnneeValid;
	private JComboBox<String> comboSoft, comboOS, comboReseau;
	private JRadioButton boutInstall1, boutInstall2, boutValid1, boutValid2, boutValid3;
	private ButtonGroup groupBoutTypeInstall, groupBoutValid;
	private JButton boutEnvoi;
	private JFrame fenetreParent;
	
	public FormInstall( ) /*throws DateException */{
		
		idInstall = new JLabel("ID installations: ");
		textIdInstall = new JTextField(String.valueOf(getIdInstallBD()));
		textIdInstall.setEditable(false);
		
		date = new JLabel("Date de l'installation (jj/mm/aaaa): ");
		textJour = new JTextField("Jour",4);
		textMois = new JTextField("Mois",4);
		textAnnee = new JTextField("Année",4);
		
		boutInstall1 = new JRadioButton("Installation Standard",true);
		boutInstall2 = new JRadioButton("Installation Personnalisée",false);
		groupBoutTypeInstall = new ButtonGroup();
		groupBoutTypeInstall.add(boutInstall1);
		groupBoutTypeInstall.add(boutInstall2);
		
		commentaire = new JLabel("Commentaire (f): ");
		textCommentaire = new JTextField(40);
		
		dureeInstall = new JLabel("Duree de l'installation en minute: ");
		textDureeInstall = new JTextField(8);
		
		refProcedure = new JLabel("Ref. Procedure d'installation (f): ");
		textRefProcedure = new JTextField(40);
		
		boutValid1 = new JRadioButton("Terminée",true);
		boutValid2 = new JRadioButton("En Cours",false);
		boutValid3 = new JRadioButton("À Prévoir",false);
		groupBoutValid = new ButtonGroup();
		groupBoutValid.add(boutValid1);
		groupBoutValid.add(boutValid2);
		groupBoutValid.add(boutValid3);
		
		dateValidation = new JLabel("Date validation prévue: ");
		textJourValid = new JTextField("Jour",4);
		textMoisValid = new JTextField("Mois",4);
		textAnneeValid = new JTextField("Année ",4);
		dateValidation.setVisible(false);
		textJourValid.setVisible(false);
		textMoisValid.setVisible(false);
		textAnneeValid.setVisible(false);
		
		String[] softwareList = {"Bob50","NetBeans","Office 2013","Visual Studio","Oracle 11g"};
		comboSoft = new JComboBox<String>(softwareList);
		softLab =new JLabel("Software: ");
		String[] responsableList = {"Marvin Gobin","André Van Kerrebroeck"};
		comboReseau = new JComboBox<String>(responsableList);
		reseauLab = new JLabel("Admin: ");
		String[] oSList = {"Fedora","Mint","RedHat8","Ubuntu","Win7 pro FR","Win8 pro EN","Win8 pro FR"};
		comboOS = new JComboBox<String>(oSList);
		osLab = new JLabel("OS: ");
		
		boutEnvoi =new JButton("Terminer");
		
	//Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		//ligne 1
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(idInstall,c);
		c.gridx = 1;
		c.gridy = 0;
		add(textIdInstall,c);
		//ligne 2
		c.gridx = 0;
		c.gridy = 1;
		add(date,c);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		add(textJour,c);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(textMois,c);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		add(textAnnee,c);
		//ligne 3
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(boutInstall1,c);
		c.gridx = 1;
		c.gridy = 2;
		add(boutInstall2,c);
		//ligne 4
		c.gridx = 0;
		c.gridy = 3;
		add(commentaire,c);
		c.gridwidth = 3;
		c.gridx = 1;
		add(textCommentaire, c);
		//ligne 5
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(dureeInstall,c);
		c.gridx = 1;
		add(textDureeInstall,c);
		//ligne 6
		c.gridx = 0;
		c.gridy = 5;
		add(refProcedure,c);
		c.gridx = 1;
		c.gridwidth = 3;
		add(textRefProcedure,c);
		//ligne 7
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(boutValid1,c);
		c.gridx = 1;
		add(boutValid2,c);
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(boutValid3,c);
		//ligne 8
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_START;
		add(softLab,c);
		c.gridx = 1;
		c.gridy = 7;
		add(comboSoft,c);
		//ligne 9
		c.gridx = 0;
		c.gridy = 8;
		add(osLab,c);
		c.gridx = 1;
		c.gridy = 8;
		add(comboOS,c);
		//ligne 10
		c.gridx = 0;
		c.gridy = 9;
		add(reseauLab,c);
		c.gridx = 1;
		c.gridy = 9;
		add(comboReseau,c);
		//ligne 11 (à prevoir)
		c.gridx = 0;
		c.gridy = 10;
		add(dateValidation,c);
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_START;
		add(textJourValid,c);
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.CENTER;
		add(textMoisValid,c);
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_END;
		add(textAnneeValid,c);
		//Bouton
		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_START;
		add(boutEnvoi,c);
		
		
		
	//Gestionnaire d'action
		GestioAprevoir gestPrevoir = new GestioAprevoir();
		boutValid3.addItemListener(gestPrevoir);
		boutValid1.addItemListener(gestPrevoir);
		boutValid2.addItemListener(gestPrevoir);
		
		GestioViderField viderField = new GestioViderField();
		textJour.addMouseListener(viderField);
		textMois.addMouseListener(viderField);
		textAnnee.addMouseListener(viderField);
		textJourValid.addMouseListener(viderField);
		textMoisValid.addMouseListener(viderField);
		textAnneeValid.addMouseListener(viderField);
		
		GestioBoutEnvoi EnvoiEvent = new GestioBoutEnvoi();
		boutEnvoi.addActionListener(EnvoiEvent);
		
		getIdInstallBD();
	}
	
		//Evenement pour afficher la date de validation si le bouton à prevoir est coché
	private class GestioAprevoir implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == boutValid3 && e.getStateChange() == ItemEvent.SELECTED) {
				dateValidation.setVisible(true);
				textJourValid.setVisible(true);
				textMoisValid.setVisible(true);
				textAnneeValid.setVisible(true);
			}else {
				dateValidation.setVisible(false);
				textJourValid.setVisible(false);
				textMoisValid.setVisible(false);
				textAnneeValid.setVisible(false);
			}
		}
	}
	
		//Evenement pour vider les textField des date
	private class GestioViderField implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == textJour) {
				textJour.setText("");
			}else if(e.getSource() == textMois) {
				textMois.setText("");
			}else if(e.getSource() == textAnnee) {
				textAnnee.setText("");
			}else if(e.getSource() == textJourValid) {
				textJourValid.setText("");
			}else if(e.getSource() == textMoisValid) {
				textMoisValid.setText("");
			}else if(e.getSource() == textAnneeValid) {
				textAnneeValid.setText("");
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
	
	//Evenement bouton terminer
	private class GestioBoutEnvoi implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		//verification Date de l'installation
			if(verifDate(textJour,textMois,textAnnee) != null) {
				java.sql.Date sqlD = verifDate(textJour,textMois,textAnnee);
				
				if(verifDureeInstall (textDureeInstall)) {
			//verification Date de validation + si le bouton à prevoir est coché
					if(boutValid3.isSelected() == true ) {
						if(verifDate(textJourValid, textMoisValid, textAnneeValid) != null) {
							java.sql.Date sqlDValid = verifDate(textJourValid, textMoisValid, textAnneeValid);
						
							//Boite de dialogue "êtes vous sur"
							JOptionPane jopOuiNon = new JOptionPane();
							int option = jopOuiNon.showConfirmDialog(null,"Voulez-vous envoyer ces informations ?","Attention", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(option == JOptionPane.OK_OPTION) {
						
							}
						}
					}else {
						//Boite de dialogue "êtes vous sur"
						JOptionPane jopOuiNon = new JOptionPane();
						int option = jopOuiNon.showConfirmDialog(null,"Voulez-vous envoyer ces informations ?","Attention", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
						
						}
					}
				}
			}	
		}
	}
	
	
	//fonction de verification des dates et renvois de celle-ci en format sql
	private java.sql.Date verifDate (JTextField jour, JTextField mois, JTextField annee) /*throws DateException*/ {
	
		try {
			int jourInt = Integer.parseInt(jour.getText());
			int moisInt = Integer.parseInt(mois.getText());
			int anneeInt = Integer.parseInt(annee.getText());
			/*
			if(jourInt < 0 || jourInt > 31) {
				//JOptionPane.showMessageDialog(null, "Date invalide", "Erreur", JOptionPane.ERROR_MESSAGE);									
				//throw new DateException();
				
			}else {
			
				GregorianCalendar date1 = new GregorianCalendar(anneeInt, moisInt - 1, jourInt);
				java.sql.Date sqlD = new java.sql.Date(date1.getTimeInMillis());
			
				return sqlD;
			}
			*/
			
			GregorianCalendar date1 = new GregorianCalendar(anneeInt, moisInt - 1, jourInt);
			java.sql.Date sqlD = new java.sql.Date(date1.getTimeInMillis());
		
			return sqlD;
		}catch( java.lang.NumberFormatException ex1) {
			JOptionPane.showMessageDialog(null, "Date invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			java.sql.Date dateNull = null;
			return dateNull;
			
		}
	}
	
	//Methode verification Numéro de référence
	private boolean verifDureeInstall (JTextField field) {
		try {
			int nbDuree = Integer.parseInt(field.getText());
			return true;
			
			
		}catch(java.lang.NumberFormatException ex1) {
			JOptionPane.showMessageDialog(null, "Duree Installation Invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			int nbDureeNull = 0;
			return false;
		}
	}
	
	//Methode pour récupérer l'id d'installation dans la DB et rajouter 1
	private int getIdInstallBD() {
		try {
			Connection connect = FenetrePrincipal.connection();
			String sqlInstruction = "select IdInstallation from Installation";
			ResultSet result = connect.createStatement().executeQuery(sqlInstruction);
			
			int compt = 0;
			while(result.next()) {
				compt++;
			}		
			
			connect.close();
			return compt + 1;
			
		}catch(SQLException e) {
			e.getMessage();
			int nbNull =0;
			return nbNull;
		}
	}
}


































