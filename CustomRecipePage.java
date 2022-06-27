import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CustomRecipePage extends javax.swing.JFrame {

    //Creates an array of recipe objects that can be displayed on the database page
    static int NOB;
    static Recipe recipesArray[]= new Recipe[100];
    static String titleArray[] = new String[100];
    static String ingredientsArray[] = new String[100];
    static String procedureArray[] = new String[100];
    static String notesArray[] = new String[100];
    static String urlArray[] = new String[100];
    //static ImageIcon imageArray[] = new ImageIcon[100];
    
    public CustomRecipePage() {
        initComponents();
    }
                 
    private void initComponents() {

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
        speechBubble = new javax.swing.JLabel();
        recipeImage = new javax.swing.ImageIcon();
        
        recipeImage = new javax.swing.ImageIcon((new ImageIcon(getClass().getResource("/Images/catatouille icon.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

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

        getContentPane().add(titleTextBox);
        titleTextBox.setBounds(90, 40, 320, 22);

        getContentPane().add(ingredientsTextBox);
        ingredientsTextBox.setRows(5);
        ingredientsTextBox.setColumns(20);
        ingredientsTextBox.setLineWrap(true);
        ingredientsScrollPane.setViewportView(ingredientsTextBox);

        getContentPane().add(ingredientsScrollPane);
        ingredientsScrollPane.setBounds(90, 100, 320, 80);
        
        getContentPane().add(procedureTextBox);
        procedureTextBox.setRows(5);
        procedureTextBox.setColumns(20);
        procedureTextBox.setLineWrap(true);
        procedureScrollPane.setViewportView(procedureTextBox);

        getContentPane().add(procedureScrollPane);
        procedureScrollPane.setBounds(90, 210, 320, 80);
        
        getContentPane().add(notesTextBox);
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
                goBackButtonActionPerformed(evt);
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
        
        catChef.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/helpful cat chef.png")).getImage().getScaledInstance(130, 163, Image.SCALE_SMOOTH)));
        getContentPane().add(catChef);
        catChef.setBounds(800, 60, 130, 163);

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


        pack();
    }// </editor-fold>                                                                                                     

    private void saveRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        String title = "";
        String ingredients = "";
        String procedure = "";
        String notes = "";
        
        title = titleTextBox.getText();
        notes = notesTextBox.getText();
        ingredients = ingredientsTextBox.getText();
        procedure = procedureTextBox.getText();
        
        Recipe r2 = null;
        
        try {
           r2 = new Recipe(title, ingredients, procedure, notes);
           recipesArray[NOB] = r2;
           System.out.println(recipesArray[NOB].customRecipeToString());
           
        } catch (NullPointerException except){
            System.out.println(title +"\n\n" + ingredients + "\n\n" + procedure + "\n\n" + notes + "\nfailed");
        }
        
        //Fix this so that the display in RecipePage works correctly
        if (!"".equals(titleTextBox.getText())){
            titleArray[NOB] = r2.getTitle();
        }
        else{
            titleArray[NOB] = "Title";
        }
        if (!"".equals(ingredientsTextBox.getText())){
            ingredientsArray[NOB] = r2.getIngredients();
        }
        else{
            ingredientsArray[NOB] = "Ingredients";
        }
        if (!"".equals(procedureTextBox.getText())){
            procedureArray[NOB] = r2.getProcedure();
        }
        else{
            procedureArray[NOB] = "Procedure";
        }
        if (!"".equals(notesTextBox.getText())){
            notesArray[NOB] = r2.getNotes();
        }
        else{
            notesArray[NOB] = "Notes";
        }
        
        /*if (imageArray[NOB] == null){
            image[NOB] = new javax.swing.ImageIcon((new ImageIcon(getClass().getResource("/Images/catatouille icon.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        }
        if (selectedTags[NOB] == null){
            selectedTags[NOB] = " ";
        }*/
        
        urlArray[NOB] = "N/A";

        new RecipePage().setRecipeNumber(NOB);
        new RecipePage().setVisible(true);
        this.dispose();
        
        NOB++;
    }                                                       

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard this recipe?", "Go Back", JOptionPane.PLAIN_MESSAGE);
        if(a == JOptionPane.YES_OPTION){  
            new FrontPage().setVisible(true);
            this.dispose();
        }
        
    }             
    
    public static int getNOB(){
        return NOB;
    }
    
    public static void setNOB(int n) throws IOException{
        new RecipePage().setRecipeNumber(NOB);
        NOB = n+1;
    } //Creates a bug where two of the same url recipes are displayed, but not a major bug
    
    public static void setTitleArray(String t, int n){
        titleArray[n] = t;
    }
    
    public static void setIngredientsArray(String i, int n){
        ingredientsArray[n] = i;
    }
    
    public static void setNotesArray(String n, int a){
        notesArray[a] = n;
    }
    
    public static void setProcedureArray(String a, int n){
        procedureArray[n] = a;
    }
    
    public static void setUrlArray(String u, int n){
        urlArray[n] = u;
    }
    
    public static void setRecipesArray(Recipe r, int n){
        recipesArray[n] = r;
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
                            //Send the file title to the recipe object
                        }
                        else {
                            recipeImage = new ImageIcon(resize.getScaledInstance(310, resize.getHeight(rootPane)/(resize.getWidth(rootPane)/310), Image.SCALE_SMOOTH));
                            //recipe object image = default
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
    
    public static void convertToArray(){
        //Taken from: https://howtodoinjava.com/java/io/java-filefilter-example/
        File directory = new File("C:/Users/shamm/Pictures/School related stuff/ICS4U/Web_scraper_copy_v1");

        //Verify if it is a valid directory
        if (!(directory.exists() && directory.isDirectory()))
        {
          System.out.println(String.format("Directory %s does not exist", directory));
          return;
        }

        FileFilter txtFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".txt");
          }
        };            

        File[] files = directory.listFiles(txtFileFilter);

        for (File f: files){

            FileReader fr = null;
            try {
                fr = new FileReader(f.getName()); //Replace with fileTitle

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            BufferedReader fileIn = new BufferedReader(fr);
            String recipe = "";

            for (int i = 0; i < 200; i++){
                try {
                    recipe += fileIn.readLine() + "<br>";
                } catch (NullPointerException | StringIndexOutOfBoundsException | IOException e){
                    Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, e);
                }    
            }

            String title;
            String notes;
            String ingredients;
            String procedure;
            String source;

            try {
                title = recipe.substring(recipe.indexOf("Title: ")+7, recipe.indexOf("Source:"));
                source = recipe.substring(recipe.indexOf("Source: ")+8, recipe.indexOf("Notes: "));

            } catch (StringIndexOutOfBoundsException ex){
                title = recipe.substring(recipe.indexOf("Title: ")+7, recipe.indexOf("Notes:"));
                source = "N/A";
            }

            notes = recipe.substring(recipe.indexOf("Notes: ")+7, recipe.indexOf("Ingredients: "));
            ingredients = recipe.substring(recipe.indexOf("Ingredients: ")+13, recipe.indexOf("Procedure: "));
            procedure = recipe.substring(recipe.indexOf("Procedure: ")+11, recipe.indexOf("null"));
            
            String tempNotes = "<html>Notes:<br>";
            for (int i = 0; i < notes.length(); i++){
                try {
                    tempNotes += notes.substring(0, 65) + "<br>";
                    notes = notes.substring(65, notes.length());
                } catch (StringIndexOutOfBoundsException ex){
                }   
            } notes = tempNotes + notes;
            
            String tempString = "";
            String tempProcedure = "";
            
            for (int i = 0; i < procedure.length(); i++){
            try {
                
                tempString = procedure.substring(0, 65);
                
                if (tempString.endsWith(" ")){
                    
                    tempString = procedure.substring(0, 65);
                    
                    if (tempString.startsWith(" ")){
                        tempProcedure += procedure.substring(1, 65) + "<br>";
                    } else {
                        tempProcedure += procedure.substring(0, 65) + "<br>";
                    }
                    procedure = procedure.substring(65, procedure.length());
                    
                } else {
                    
                    if (tempString.startsWith(" ")){
                        tempProcedure += procedure.substring(1, tempString.lastIndexOf(" ")) + "<br>";
                    } else {
                        tempProcedure += procedure.substring(0, tempString.lastIndexOf(" ")) + "<br>";
                    }

                    procedure = procedure.substring(tempString.lastIndexOf(" "), procedure.length());
                }
                
            } catch (StringIndexOutOfBoundsException ex){   
            }
         } procedure = tempProcedure + procedure;
            
            
            
            titleArray[NOB] = title.substring(0, title.length()-8);
            ingredientsArray[NOB] = ingredients;
            procedureArray[NOB] = procedure;
            notesArray[NOB] = notes;
            urlArray[NOB] = source;
            recipesArray[NOB] = new Recipe("x", title, urlArray[NOB], notesArray[NOB], ingredientsArray[NOB], procedureArray[NOB]);

            NOB++;
        }
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomRecipePage().setVisible(true);    
                convertToArray();
            }
        });
    }

    // Variables declaration - do not modify                     
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
    private static javax.swing.JLabel speechBubble;
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration                   
}