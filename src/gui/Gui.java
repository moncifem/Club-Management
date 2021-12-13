package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Files.*;
import java.nio.file.Path;
import java.nio.file.Path.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.DimensionUIResource;

import com.formdev.flatlaf.FlatIntelliJLaf;

import metier.Etudiant;
import metier.NiveauPreference;
import metier.Preference;

public class Gui extends JFrame {
	private JLabel lbImage,lbEtat,lbNom,lbPrenom,lbAnnee,lbPref,lbComment,lbSemestre;
	private JRadioButton rdFirst,rdSecond,rdS1,rdS2,rdS3,rdS4;
	private JTextArea comment;
	private JComboBox cbxEtat,cbxRobo,cbxGo,cbxEchec,cbxLogiciel;
	private JCheckBox cbRobo,cbGo,cbEchec,cbLogiciel;
	private JButton btnAdd,btnModify,btnDel;
	private JTextField tfNom,tfPrenom;
	private String etat[]={"Mme","Mr"};
	private String niveau[]= {"débutant","intermediaire","avancé"};
	//String s="C:\Myfolder\java%20workplace\MyClub\bin\gui\img\avatMale.png";
	
	private  URL urlFemale=getClass().getResource("/images/avatFemale.png");
	private  URL urlMale=getClass().getResource("/images/avatMale.png");
	//final private static String urlMale="C:\\Myfolder\\java%20workplace\\MyClub\\bin\\gui\\img\\avatMale.png";
	//Gui.class.getResource("/assets/img/avatMale.png").getPath().substring(1).replace("%20"," ").replace("/","\\");
	private  URL frameIcon=getClass().getResource("/images/club.png");
	//ImageIO.read(getClass().getResourceAsStream("/images/c.png"));
	final private ImageIcon iconF = new ImageIcon(urlFemale);
	final private ImageIcon iconM = new ImageIcon(urlMale);
	final private ImageIcon iconFrame = new ImageIcon(frameIcon);
	private ButtonGroup group = new ButtonGroup();
	private ButtonGroup groupS12 = new ButtonGroup();
	private ButtonGroup groupS34 = new ButtonGroup();
	private  String fileName="etudiants.txt";
	//new java.io.File(getClass().getResource("/data/etudiants.txt").getFile()).toURI().toString();
			//getClass().getResource("etudiants.txt").getFile();
	//GUI
	public Gui() throws IOException {
		this.setTitle("Gestion Club");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 600));
		this.setLocationRelativeTo(null);
		setIconImage(iconFrame.getImage());
		initComponents();
	}
	//Components
	private void initComponents() throws IOException{	
		//create panels with layouts
		JPanel rootPanel=(JPanel) this.getContentPane();
		rootPanel.setLayout(new GridLayout(7,1));
		rootPanel.setPreferredSize(new Dimension(50, 50));
		JPanel panelEtat=new JPanel(new FlowLayout());
		JPanel panelNom=new JPanel(new FlowLayout());
		JPanel panelAnnee=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelPreference=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJeu=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//JPanel panelNiveau=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJeuRobot=new JPanel(new GridLayout(2,1,25, 10));
		JPanel panelJeuGo=new JPanel(new GridLayout(2,1,25, 10));
		JPanel panelJeuEchec=new JPanel(new GridLayout(2,1,25, 10));
		JPanel panelJeuLogiciel=new JPanel(new GridLayout(2,1,25, 10));
		
		JPanel panelComment=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelButton=new JPanel(new FlowLayout(FlowLayout.CENTER));
		//Set panel Etat
		lbImage=new JLabel(iconF);
		lbEtat=new JLabel("Etat civil: ");
		cbxEtat=new JComboBox(etat);
		panelEtat.add(lbImage);
		panelEtat.add(lbEtat);
		panelEtat.add(cbxEtat);
		//Set panel Information 
		lbNom=new JLabel("Nom: ");
		tfNom=new JTextField(10);
		lbPrenom=new JLabel("Prénom: ");
		tfPrenom=new JTextField(10);
		panelNom.add(lbNom);
		panelNom.add(tfNom);
		panelNom.add(lbPrenom);
		panelNom.add(tfPrenom);
		//Set panel Study Year
		lbAnnee=new JLabel("Année: ");
		lbSemestre=new JLabel("--> Semestre");
		rdFirst=new JRadioButton("1A");
		rdSecond=new JRadioButton("2A");
		rdS1=new JRadioButton("S1");
		rdS2=new JRadioButton("S2");
		rdS3=new JRadioButton("S3");
		rdS4=new JRadioButton("S4");
		rdS1.setVisible(false);
		rdS1.setSelected(true);
		rdS2.setVisible(false);
		rdS3.setVisible(false);
		rdS3.setSelected(true);
		rdS4.setVisible(false);
		lbSemestre.setVisible(false);
		group.add(rdFirst);
		group.add(rdSecond);
		groupS12.add(rdS1);
		groupS12.add(rdS2);
		groupS34.add(rdS3);
		groupS34.add(rdS4);
		
		panelAnnee.add(lbAnnee);
		panelAnnee.add(rdFirst);
		panelAnnee.add(rdSecond);
		panelAnnee.add(lbSemestre);
		panelAnnee.add(rdS1);
		panelAnnee.add(rdS2);
		panelAnnee.add(rdS3);
		panelAnnee.add(rdS4);
		
		//Set panel Preference
		lbPref=new JLabel("Préférences:");
		panelPreference.add(lbPref);
		// Set panel Game Choice
		cbRobo=new JCheckBox("Robotique");
		cbGo=new JCheckBox("Jeu de Go");
		cbEchec=new JCheckBox("Jeu Echec");
		cbLogiciel=new JCheckBox("Logiciels libres");
		panelJeuRobot.add(cbRobo);
		panelJeuGo.add(cbGo);
		panelJeuEchec.add(cbEchec);
		panelJeuLogiciel.add(cbLogiciel);
		//Set panel Game Level
		cbxRobo=new JComboBox(niveau);
		cbxGo=new JComboBox(niveau);
		cbxEchec=new JComboBox(niveau);
		cbxLogiciel=new JComboBox(niveau);
		cbxRobo.setVisible(false);
		cbxGo.setVisible(false);
		cbxEchec.setVisible(false);
		cbxLogiciel.setVisible(false);
		panelJeuRobot.add(cbxRobo);
		panelJeuGo.add(cbxGo);
		panelJeuEchec.add(cbxEchec);
		panelJeuLogiciel.add(cbxLogiciel);
		panelJeu.add(panelJeuRobot);
		panelJeu.add(panelJeuGo);
		panelJeu.add(panelJeuEchec);
		panelJeu.add(panelJeuLogiciel);
		//Set panel Comment 
		lbComment=new JLabel("Commentaire: ");
		comment=new JTextArea("mentionner notamment si vous êtes prêt à animer un club");
		comment.setPreferredSize(new DimensionUIResource(400, 30));
		panelComment.add(lbComment);
		panelComment.add(comment);
		//Set panel Buttons
		btnDel=new JButton("Supprimer");
		btnModify=new JButton("Modifier");
		btnAdd=new JButton("Ajouter");
		panelButton.add(btnDel);
		panelButton.add(btnModify);
		panelButton.add(btnAdd);
		//Add panels to main panel of the frame
		this.getContentPane().add(panelEtat);
		this.getContentPane().add(panelNom);
		this.getContentPane().add(panelAnnee);
		this.getContentPane().add(panelPreference);
		this.getContentPane().add(panelJeu);
		this.getContentPane().add(panelComment);
		this.getContentPane().add(panelButton);
		//Set ComboBox listner: change image with selection
		cbxEtat.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){
				   JComboBox cb = (JComboBox) e.getSource();
			        try {
						updateLabel((String) cb.getSelectedItem());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   }
				});
		cbxEtat.setSelectedIndex(0);
		//Set Window listner for closing
		this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "Application Fermée", "Message",JOptionPane.INFORMATION_MESSAGE);
	        }
	    });
		//Set mouse listner for Buttons
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				  highlightButtons(btnAdd,"Ajouter cet utilisateur?");
				  }
			  public void mouseExited(MouseEvent e) {
				  deHighlightButtons(btnAdd,"Ajouter");
			}
		});
		btnDel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				  highlightButtons(btnDel,"Supprimer cet utilisateur?");
				  }
			  public void mouseExited(MouseEvent e) {
				  deHighlightButtons(btnDel,"Supprimer");
			}
		});
		btnModify.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				  highlightButtons(btnModify,"Modifier cet utilisateur?");
				  }
			  public void mouseExited(MouseEvent e) {
				  deHighlightButtons(btnModify,"Modifier");
			}
		});
		comment.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(comment.getText().equals("mentionner notamment si vous êtes prêt à animer un club")) {
					 comment.setText("");
				}
				  comment.setPreferredSize(new DimensionUIResource(400, 50));
				  comment.setCaretColor(Color.BLACK);
				  }
			  public void mouseExited(MouseEvent e) {
				  //comment.setText("mentionner notamment si vous êtes prêt à animer un club");
				  comment.setPreferredSize(new DimensionUIResource(400, 30));
				  comment.setCaretColor(comment.getBackground());
			}
		});
		//Set Semestre selection hide and show by Year selection
		rdFirst.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	lbSemestre.setVisible(true);
					   rdS1.setVisible(true);
						rdS2.setVisible(true);
						rdS3.setVisible(false);
						rdS4.setVisible(false);
		        } else {
		        	lbSemestre.setVisible(false);
					   rdS1.setVisible(false);
						rdS2.setVisible(false);
						rdS3.setVisible(false);
						rdS4.setVisible(false);
		        };
		    }
		});
		rdSecond.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	 lbSemestre.setVisible(true);
					   rdS1.setVisible(false);
						rdS2.setVisible(false);
						rdS3.setVisible(true);
						rdS4.setVisible(true);
		        } else {
		        	lbSemestre.setVisible(false);
					   rdS1.setVisible(false);
						rdS2.setVisible(false);
						rdS3.setVisible(false);
						rdS4.setVisible(false);
		        };
		    }
		});
		btnDel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				  clear();
				  }
		});
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String errors="",comment="",semestre="",jeuRobot="",jeuGo="",jeuEchec="",jeuLogiciel="",nom="",prenom="",s ="",a ="",etat="";
				//(String nom,String prenom,String annee,String semestre,String comment,ETATCIVIL etatcivil,List<NiveauPreference> rn)
				//NiveauPreference(NIVEAU niveau,Preference preference)
				List<NiveauPreference> rn=new ArrayList<>();
				etat=(String) cbxEtat.getSelectedItem();
				nom=tfNom.getText();
				prenom=tfPrenom.getText();
				Preference robotique=new Preference("Robotique");
				Preference echec=new Preference("Echec");
				Preference go=new Preference("Go");
				Preference logiciel=new Preference("Logiciel Libre");
				if(rdFirst.isSelected()) {
					if(rdS1.isSelected()) {
						semestre=" 1er année en S1 ";
						s="1";
						a="1";
					}
					else {
						semestre=" 1er année en S2 ";
						s="2";
						a="1";
					}
				}
				else if(rdSecond.isSelected()) {
					if(!rdS3.isSelected()) {
						semestre=" 2eme année en S4 ";
						s="3";
						a="2";
					}else{
						semestre=" 2eme année en S3 ";
						s="4";
						a="2";
					}
				}
				if(cbRobo.isSelected()) {
					jeuRobot="\n--joue en jeu de Robotique niveau "+cbxRobo.getSelectedItem()+",";
					rn.add(new NiveauPreference(NiveauPreference.strToNiveau((String) cbxRobo.getSelectedItem()), robotique));
				}
				if(cbGo.isSelected()) {
					jeuGo="\n--joue en jeu de Go niveau "+cbxGo.getSelectedItem()+",";
					rn.add(new NiveauPreference(NiveauPreference.strToNiveau((String) cbxGo.getSelectedItem()), go));
				}
				if(cbEchec.isSelected()) {
					jeuEchec="\n--joue en jeu d'Echec niveau "+cbxEchec.getSelectedItem()+",";
					rn.add(new NiveauPreference(NiveauPreference.strToNiveau((String) cbxEchec.getSelectedItem()), echec));
				}
				if(cbLogiciel.isSelected()) {
					jeuLogiciel="\n--joue en jeu de Logiciel niveau "+cbxLogiciel.getSelectedItem()+",";
					rn.add(new NiveauPreference(NiveauPreference.strToNiveau((String) cbxLogiciel.getSelectedItem()), logiciel));
				}
				if(nom.equals("")) {
					errors+="Missing: Nom\n";
				}
				if(prenom.equals("")) {
					errors+="Missing: Prenom\n";
				}
				if(semestre.equals("")) {
					errors+="Missing: Semestre\n";
				}
				if(jeuRobot.equals("") && jeuGo.equals("") && jeuEchec.equals("") && jeuLogiciel.equals("")) {
					errors+="Missing: Selectionner au moin un jeu\n";
				}
				if(!errors.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), errors, "Erreur!",JOptionPane.ERROR_MESSAGE);
				}else {
					String se=etat+" "+nom+" "+prenom+"\n\netudiant en"+semestre+"\n"+jeuRobot+jeuGo+jeuEchec+jeuLogiciel+comment;
					int reply = JOptionPane.showConfirmDialog(null, "Etes vous sure d'ajouter "+se, "Ajouter"+prenom, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(new JFrame(), se, "Etudiant ajouter",JOptionPane.INFORMATION_MESSAGE);
						Etudiant etudiant=new Etudiant(nom,prenom,a,s,comment,Etudiant.strToEtat((String) cbxEtat.getSelectedItem()),rn);
						System.out.println(fileName);
						System.out.println(urlFemale);
						File file = new File(fileName);
						FileWriter fr;
						try {
							fr = new FileWriter(file, true);
							BufferedWriter br = new BufferedWriter(fr);
							br.write(etudiant.toString()+"\n");
							br.close();
							fr.close();;
						} catch (IOException e1) {
							e1.printStackTrace();
							System.out.println("not found");
						}
						clear();
					} 
				}
			}
		});
		cbRobo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		            cbxRobo.setVisible(true);
		        } else {
		        	cbxRobo.setVisible(false);
		        };
		    }
		});
		cbLogiciel.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		            cbxLogiciel.setVisible(true);
		        } else {
		        	cbxLogiciel.setVisible(false);
		        };
		    }
		});
		cbGo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	cbxGo.setVisible(true);
		        } else {
		        	cbxGo.setVisible(false);
		        };
		    }
		});
		cbEchec.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		            cbxEchec.setVisible(true);
		        } else {
		        	cbxEchec.setVisible(false);
		        };
		    }
		});
	}
	//method for updating the icon when selecting combobox
	private void updateLabel(String s) throws IOException {
		if(s.equals("Mme")) {
			//this.lbImage.setIcon(new ImageIcon(ImageIO.read(new File("img/avatFemale.png"))));
			new JLabel(iconF);
			lbImage.setIcon(new ImageIcon(iconF.getImage().getScaledInstance(65, 70, Image.SCALE_SMOOTH)));
		}
		else {
			this.lbImage.setIcon(iconM);
			lbImage.setIcon(new ImageIcon(iconM.getImage().getScaledInstance(65, 70, Image.SCALE_SMOOTH)));
		}
	}
	//Color text on mouse hover event
	public void highlightButtons(JButton button,String s) {
        	button.setForeground(Color.WHITE);
        	button.setBackground(Color.DARK_GRAY);
            button.setText(s);
    }
	public void deHighlightButtons(JButton button,String s) {
    	button.setForeground(Color.BLACK);
    	button.setBackground(Color.WHITE);
        button.setText(s);
}
	public void clear() {
		cbxEtat.setSelectedIndex(1);
		  tfNom.setText("");
		  tfPrenom.setText("");
		  group.clearSelection();
		  groupS12.clearSelection();
		  groupS34.clearSelection();
		  cbRobo.setSelected(false);
		  cbGo.setSelected(false);
		  cbEchec.setSelected(false);
		  cbLogiciel.setSelected(false);
		  rdS1.setSelected(true);
		  rdS3.setSelected(true);
		  comment.setText("mentionner notamment si vous êtes prêt à animer un club");
	}
	//Initiate the program
	public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel( new FlatIntelliJLaf() );
		Gui mm= new Gui();
		mm.setVisible(true);
	}
}
