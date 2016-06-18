/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilitat.botiga;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Broquil
 */
public class ModificarProducte extends javax.swing.JPanel {

    /**
     * Creates new form ModificarProducte
     */
    Producte original = new Producte();
    Producte canvi = new Producte();
        
    public ModificarProducte() {
        initComponents();
        DefaultListModel a = new DefaultListModel(); 
      
        for(int i=0; i<25; i++){
            a.addElement(Dades.llistaProductes.get(i).nom);
        }
        llistaNoms.setModel(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomProducte = new javax.swing.JTextField();
        preuProducte = new javax.swing.JTextField();
        quantitatPrestatge = new javax.swing.JTextField();
        Marca = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        llistaNoms = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        entrada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        aplicar = new javax.swing.JButton();

        nomProducte.setText("Nom");

        preuProducte.setText("Preu");

        quantitatPrestatge.setText("Quantitat al prestatge");

        Marca.setText("Marca");

        llistaNoms.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        llistaNoms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                llistaNomsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(llistaNoms);

        jLabel1.setText("Nom:");

        jLabel2.setText("Preu:");

        jLabel3.setText("Quantitat al prestatge:");

        jLabel4.setText("Marca:");

        entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaKeyReleased(evt);
            }
        });

        jLabel5.setText("Cerca");

        aplicar.setText("Aplicar");
        aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nomProducte, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(entrada)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(preuProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantitatPrestatge, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Marca, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(nomProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(preuProducte)
                    .addComponent(quantitatPrestatge)
                    .addComponent(Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(aplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void llistaNomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_llistaNomsMouseClicked
        // TODO add your handling code here:
        ListModel l = llistaNoms.getModel();
        int i = llistaNoms.getSelectedIndex();
        Object e = l.getElementAt(i);
        String nom = e.toString();
        original = Funcions.cercaPerNom(nom);
        nomProducte.setText(original.nom);
        preuProducte.setText(String.valueOf(original.preu));
        quantitatPrestatge.setText(String.valueOf(original.prestatgeMaxim));
        Marca.setText(original.marca);
        
    }//GEN-LAST:event_llistaNomsMouseClicked

    private void entradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaKeyReleased
        
        String c = entrada.getText().toUpperCase();
        DefaultListModel llistaElements = Funcions.cercaPorcioProducte(c);

        DefaultListModel a = new DefaultListModel();

        int i = 0;
        while(!llistaElements.isEmpty() && i<25){
            a.addElement(llistaElements.firstElement());
            llistaElements.remove(0);
            i++;
        }
        llistaNoms.setModel(a);
    }//GEN-LAST:event_entradaKeyReleased

    private void aplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarActionPerformed
        // TODO add your handling code here:
        
        /*canvi.nom = nomProducte.getText();
        String a = preuProducte.getText();
        if(a.contains(",")){
            a = a.replace(",", ".");
        }
        canvi.preu = Float.parseFloat(a);
        canvi.marca = Marca.getText();
        canvi.prestatgeMaxim = Integer.parseInt(quantitatPrestatge.getText());
        
        try {
            Funcions.actualitzaProducte(original, canvi);
        } catch (IOException ex) {
            Logger.getLogger(ModificarProducte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ModificarProducte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(ModificarProducte.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomProducte.setText("");
        preuProducte.setText(String.valueOf(""));
        quantitatPrestatge.setText(String.valueOf(""));
        Marca.setText("");*/
    }//GEN-LAST:event_aplicarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Marca;
    private javax.swing.JButton aplicar;
    private javax.swing.JTextField entrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList llistaNoms;
    private javax.swing.JTextField nomProducte;
    private javax.swing.JTextField preuProducte;
    private javax.swing.JTextField quantitatPrestatge;
    // End of variables declaration//GEN-END:variables
}