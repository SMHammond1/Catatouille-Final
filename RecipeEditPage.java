
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RecipeEditPage extends javax.swing.JFrame {

    int recipeNum;
    
    public RecipeEditPage() throws IOException {
        initComponents();
    }
                 
    private void initComponents() throws FileNotFoundException, IOException {

        titleTextBox = new javax.swing.JTextField();
        ingredientsTextBox = new javax.swing.JTextArea();
        procedureTextBox = new javax.swing.JTextArea();
        notesTextBox = new javax.swing.JTextArea();
        uploadPictureButton = new javax.swing.JButton();
        semicolonReminder = new javax.swing.JLabel();
        saveRecipeButton = new javax.swing.JButton();
        goBackButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        ingredientsLabel = new javax.swing.JLabel();
        notesLabel = new javax.swing.JLabel();
        procedureLabel = new javax.swing.JLabel();
        ingredientsScrollPane = new javax.swing.JScrollPane();
        procedureScrollPane = new javax.swing.JScrollPane();
        notesScrollPane = new javax.swing.JScrollPane();
        tagListScrollPane = new javax.swing.JScrollPane();
        tagList = new javax.swing.JList<>();
        tagsLabel = new javax.swing.JLabel();
        catChef = new javax.swing.JLabel();
        source = "";
        speechBubble = new javax.swing.JLabel();
        
        CustomRecipePage.convertToArray();
        recipeNum = RecipePage.recipeNumber;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(960, 540));
        getContentPane().setLayout(null);
        
        getContentPane().setBackground(new java.awt.Color(227, 230, 164));
        titleLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        ingredientsLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        notesLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        procedureLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        uploadPictureButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        semicolonReminder.setFont(new java.awt.Font("Segoe Print", 1, 10));
        saveRecipeButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        goBackButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        tagsLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        tagList.setFont(new java.awt.Font("Segoe Print", 0, 12));
        titleTextBox.setFont(new java.awt.Font("Segoe Print", 0, 12));
        ingredientsTextBox.setFont(new java.awt.Font("Segoe Print", 0, 12));
        procedureTextBox.setFont(new java.awt.Font("Segoe Print", 0, 12));
        notesTextBox.setFont(new java.awt.Font("Segoe Print", 0, 12));
        uploadPictureButton.setBackground(new java.awt.Color(249,195,173));
        saveRecipeButton.setBackground(new java.awt.Color(251,222,210));
        goBackButton.setBackground(new java.awt.Color(251,222,210));
        tagList.setBackground(new java.awt.Color(249,249,235));
        titleTextBox.setBackground(new java.awt.Color(249,249,235));
        ingredientsTextBox.setBackground(new java.awt.Color(249,249,235));
        procedureTextBox.setBackground(new java.awt.Color(249,249,235));
        notesTextBox.setBackground(new java.awt.Color(249,249,235));
  
        
        recipeImage = new javax.swing.ImageIcon((new ImageIcon(getClass().getResource("/Images/catatouille icon.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        
        
        
        getContentPane().add(titleTextBox);
        titleTextBox.setText(CustomRecipePage.titleArray[recipeNum]);
        titleTextBox.setBounds(90, 40, 320, 22);

        getContentPane().add(ingredientsTextBox);
        ingredientsTextBox.setText(CustomRecipePage.ingredientsArray[recipeNum]);
        ingredientsTextBox.setRows(5);
        ingredientsTextBox.setColumns(20);
        ingredientsTextBox.setLineWrap(true);
        ingredientsScrollPane.setViewportView(ingredientsTextBox);
        getContentPane().add(ingredientsScrollPane);
        ingredientsScrollPane.setBounds(90, 100, 320, 80);
        
        getContentPane().add(procedureTextBox);
        procedureTextBox.setText(CustomRecipePage.procedureArray[recipeNum]);
        procedureTextBox.setRows(5);
        procedureTextBox.setColumns(20);
        procedureTextBox.setLineWrap(true);
        procedureScrollPane.setViewportView(procedureTextBox);
        getContentPane().add(procedureScrollPane);
        procedureScrollPane.setBounds(90, 210, 320, 80);
        
        getContentPane().add(notesTextBox);
        notesTextBox.setText(CustomRecipePage.notesArray[recipeNum]);
        notesTextBox.setRows(5);
        notesTextBox.setColumns(20);
        notesTextBox.setLineWrap(true);
        notesScrollPane.setViewportView(notesTextBox);
        getContentPane().add(notesScrollPane);
        notesScrollPane.setBounds(90, 320, 320, 80);
        

        uploadPictureButton.setText("UPLOAD PICTURE");
        getContentPane().add(uploadPictureButton);
        uploadPictureButton.setBounds(480, 100, 310, 210);
        uploadPictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadPictureButtonActionPerformed(evt);
            }
        });

        semicolonReminder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        semicolonReminder.setText("<html>Please separate each line by a semi-colon (;)<br> Ex: 1 cup sugar; 3 eggs; 2 cup flour</html>");
        getContentPane().add(semicolonReminder);
        semicolonReminder.setBounds(475, 20, 310, 60);
        speechBubble.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/speech bubble.png")).getImage().getScaledInstance(340, 90, Image.SCALE_SMOOTH)));
        speechBubble.setBounds(480, 3, 340, 90);
        getContentPane().add(speechBubble);

        saveRecipeButton.setText("SAVE RECIPE");
        saveRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveRecipeButtonActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(CustomRecipePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getContentPane().add(saveRecipeButton);
        saveRecipeButton.setBounds(480, 320, 310, 60);

        goBackButton.setText("GO BACK");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    goBackButtonActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(RecipeEditPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getContentPane().add(goBackButton);
        goBackButton.setBounds(480, 390, 310, 60);

        titleLabel.setText("Title");
        getContentPane().add(titleLabel);
        titleLabel.setBounds(90, 20, 320, 16);

        ingredientsLabel.setText("Ingredients");
        getContentPane().add(ingredientsLabel);
        ingredientsLabel.setBounds(90, 80, 320, 16);

        notesLabel.setText("Notes");
        getContentPane().add(notesLabel);
        notesLabel.setBounds(90, 300, 320, 16);

        procedureLabel.setText("Procedure");
        getContentPane().add(procedureLabel);
        procedureLabel.setBounds(90, 190, 320, 20);

        tagList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sweet", "Savory", "Quick", "Snack", "Meal", "Low Calorie", "Breakfast", "Lunch", "Dinner", "Vegetarian", "Vegan", "Gluten Free", "Baked", "Uncooked" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tagListScrollPane.setViewportView(tagList);

        getContentPane().add(tagListScrollPane);
        tagListScrollPane.setBounds(90, 430, 320, 60);

        tagsLabel.setText("Tags");
        getContentPane().add(tagsLabel);
        tagsLabel.setBounds(90, 410, 320, 16);

        catChef.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/helpful cat chef.png")).getImage().getScaledInstance(130, 163, Image.SCALE_SMOOTH)));
        getContentPane().add(catChef);
        catChef.setBounds(800, 60, 130, 163);

        pack();
    }
    
    private void saveRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        String title = " ";
        String ingredients = " ";
        String procedure = " ";
        String notes = " "; 
        title = titleTextBox.getText();
        ingredients = ingredientsTextBox.getText();
        procedure = procedureTextBox.getText();
        notes = notesTextBox.getText();

        int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to save your changes to the recipe?", "Save Recipe", JOptionPane.PLAIN_MESSAGE);
        if(a == JOptionPane.YES_OPTION){  
            try {
                Recipe r2;
                r2 = new Recipe(title, source, ingredients, procedure, notes);
                System.out.println(r2.customRecipeToString());

            } catch (NullPointerException except){
                System.out.println(title +"\n\n" + ingredients + "\n\n" + procedure + "\n\n" + notes + "\nfailed");
            }
            
            new RecipePage().setVisible(true);
            this.dispose();
        }
        
    }                                                       

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                         
        int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard your edits?", "Go Back", JOptionPane.PLAIN_MESSAGE);
        if(a == JOptionPane.YES_OPTION){  
            new RecipePage().setVisible(true);
            this.dispose();
        }
        
    }         
    
    private void uploadPictureButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        fileChooser = new javax.swing.JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
        fileChooser.addChoosableFileFilter(filter);
        int res = fileChooser.showSaveDialog(null);
        if(res == fileChooser.APPROVE_OPTION){
          File file = fileChooser.getSelectedFile();
                    try {
                        recipeImage = new ImageIcon(ImageIO.read(file));
                        Image resize = recipeImage.getImage();
                        if (resize.getHeight(rootPane) >= resize.getWidth(rootPane)){
                            recipeImage = new ImageIcon(resize.getScaledInstance(resize.getWidth(rootPane)/(resize.getHeight(rootPane)/190) , 190, Image.SCALE_SMOOTH));
                        }
                        else {
                            recipeImage = new ImageIcon(resize.getScaledInstance(310, resize.getHeight(rootPane)/(resize.getWidth(rootPane)/310), Image.SCALE_SMOOTH));
                        }
                        } catch (IOException e) {
                        e.printStackTrace();
                    }
        }
        uploadPictureButton.setIcon(recipeImage);
        uploadPictureButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uploadPictureButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        uploadPictureButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        uploadPictureButton.setBackground(new java.awt.Color(247, 247, 237));
    }
    
    public ImageIcon getImage(){
        return recipeImage;
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomRecipePage().setVisible(true);
            }
        });
    }

    // Variables declaration                 
    private javax.swing.JButton uploadPictureButton;
    private javax.swing.JButton saveRecipeButton;
    private javax.swing.JButton goBackButton;
    private javax.swing.JLabel semicolonReminder;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel ingredientsLabel;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JLabel procedureLabel;
    private javax.swing.JLabel tagsLabel;
    private javax.swing.JLabel catChef;
    private javax.swing.JList<String> tagList;
    private javax.swing.JScrollPane ingredientsScrollPane;
    private javax.swing.JScrollPane procedureScrollPane;
    private javax.swing.JScrollPane notesScrollPane;
    private javax.swing.JScrollPane tagListScrollPane;
    private javax.swing.JTextField titleTextBox;
    private javax.swing.JTextArea ingredientsTextBox;
    private javax.swing.JTextArea procedureTextBox;
    private javax.swing.JTextArea notesTextBox;
    private static javax.swing.ImageIcon recipeImage;
    private String source;
    private javax.swing.JFileChooser fileChooser;
    private static javax.swing.JLabel speechBubble;
    // End of variables declaration                   
}