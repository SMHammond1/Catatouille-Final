

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadPhotosNOT extends javax.swing.JFrame {

    /**
     * Creates new form UploadPhotos
     */
    public UploadPhotosNOT() {
        initComponents();
    }
                         
    private void initComponents() {
        
        recipeImage = new javax.swing.ImageIcon((new ImageIcon(getClass().getResource("/Images/catatouille icon.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

        fileChooser = new javax.swing.JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
        fileChooser.addChoosableFileFilter(filter);
        int res = fileChooser.showSaveDialog(null);
        //if the user clicks on save in Jfilechooser
        if(res == fileChooser.APPROVE_OPTION){
          File file = fileChooser.getSelectedFile();
                    try {
                        recipeImage = new ImageIcon(ImageIO.read(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        }
        
       
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );

        pack();
    }
    
    public ImageIcon getImage(){
        return recipeImage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadPhotosNOT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.ImageIcon recipeImage;
    // End of variables declaration                   
}
