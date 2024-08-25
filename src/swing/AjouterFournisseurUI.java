package swing;
import javax.swing.*;
import dao.DaoFournisseur;
import entities.Fournisseur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterFournisseurUI extends JFrame implements ActionListener {
    private JTextField txtNumF;
    private JTextField txtNomF;
    private JTextField txtAdrF;
    private JButton btnAjouter;
    private DaoFournisseur daoFournisseur;

    public AjouterFournisseurUI() {
        setTitle("Ajouter un Fournisseur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoFournisseur = new DaoFournisseur();
        txtNumF = new JTextField(10);
        txtNomF = new JTextField(10);
        txtAdrF = new JTextField(10);
        btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Numéro Fournisseur:"));
        panel.add(txtNumF);
        panel.add(new JLabel("Nom Fournisseur:"));
        panel.add(txtNomF);
        panel.add(new JLabel("Adresse Fournisseur:"));
        panel.add(txtAdrF);
        panel.add(btnAjouter);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            int numF = Integer.parseInt(txtNumF.getText());
            String nomF = txtNomF.getText();
            String adrF = txtAdrF.getText();
            Fournisseur fournisseur = new Fournisseur(numF, nomF, adrF);
            try {
                daoFournisseur.ajouter(fournisseur);
                JOptionPane.showMessageDialog(this, "Fournisseur ajouté avec succès !");
                this.dispose();
                new ListerFournisseursUI();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du fournisseur.");
            }
        }
    }

    public static void main(String[] args) {
        new AjouterFournisseurUI();
    }
}
