import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class FrontPage extends javax.swing.JFrame {
       
    int buttonNumber = 0;

    public FrontPage() {
        initComponents();
    }
                    
    private void initComponents() {

        //Initializes all the elements of the GUI (ex. buttons)
        jDesktopPane1 = new javax.swing.JDesktopPane();
        customRecipeInputButton = new javax.swing.JButton();
        recipeInputBox = new javax.swing.JTextField();
        recipeFeedback = new javax.swing.JTextField();
        recipeFeedbackLabel = new javax.swing.JLabel();
        recipeInputLabel = new javax.swing.JLabel();
        recipeInputButton = new javax.swing.JButton();
        databaseButton = new javax.swing.JButton();
        smallCustomRecipeInputButton = new javax.swing.JButton();
        smallDatabaseButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        catButton = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        logo = new ImageIcon(getClass().getResource("catatouille icon small.png"));
        helpButton = new javax.swing.JButton();

        //Creates new page
        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(250, 120, 88));
        setBounds(new java.awt.Rectangle(0, 0, 920, 1080));
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(960, 540));
        getContentPane().setLayout(null);
        
        //Sets all the default colours and fonts for this page
        getContentPane().setBackground(new java.awt.Color(227, 230, 164));
        smallCustomRecipeInputButton.setBorder(null);
        smallCustomRecipeInputButton.setBackground(new java.awt.Color(227, 230, 164));
        smallDatabaseButton.setBorder(null);
        smallDatabaseButton.setBackground(new java.awt.Color(227, 230, 164));
        recipeInputButton.setBorder(null);
        recipeInputButton.setBackground(new java.awt.Color(227, 230, 164));
        recipeInputLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        customRecipeInputButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        databaseButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        recipeInputBox.setFont(new java.awt.Font("Segoe Print", 0, 12));
        recipeFeedback.setFont(new java.awt.Font("Segoe Print", 0, 12));
        recipeFeedbackLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        databaseButton.setBackground(new java.awt.Color(251,222,210));
        customRecipeInputButton.setBackground(new java.awt.Color(251,222,210));
        recipeFeedback.setBackground(new java.awt.Color(249,249,235));
        recipeInputBox.setBackground(new java.awt.Color(249,249,235));
        helpButton.setFont(new java.awt.Font("Segoe Print", 1, 12));
        helpButton.setBackground(new java.awt.Color(251, 222, 210));
        
        //Adds Catatouille icon 
        icon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("catatouille icon.png")).getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
        getContentPane().add(icon);
        icon.setBounds(569, 85-30, 85, 85);
        
        //Sets page title
        title.setFont(new java.awt.Font("Segoe Print", 2, 30));
        title.setText("Catatouille");
        getContentPane().add(title);
        title.setBounds(660, 55, 229, 94);
        
        //Creates recipe Input label and text box
        recipeInputLabel.setText("Recipe Input");
        getContentPane().add(recipeInputLabel);
        recipeInputLabel.setBounds(569, 197-35, 229, 16);
        
        recipeInputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    recipeInputBoxActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getContentPane().add(recipeInputBox);
        recipeInputBox.setBounds(569, 225-35, 229, 22);        

        recipeInputButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("go button.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));;
        getContentPane().add(recipeInputButton);
        recipeInputButton.setBounds(804, 225-35, 50, 22);
        recipeInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    recipeInputBoxActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Sets custom recipe label and button
        customRecipeInputButton.setText("Custom Recipe Input");
        customRecipeInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customRecipeInputButtonActionPerformed(evt);
            }
        });
        getContentPane().add(customRecipeInputButton);
        customRecipeInputButton.setBounds(615, 295-42, 225, 16);
        
        smallCustomRecipeInputButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/go button.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        getContentPane().add(smallCustomRecipeInputButton);
        smallCustomRecipeInputButton.setBounds(569, 250, 25, 22);
        smallCustomRecipeInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customRecipeInputButtonActionPerformed(evt);
            }
        });

        //Sets database label and button
        databaseButton.setText("Recipe Database");
        databaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(databaseButton);
        databaseButton.setBounds(615, 368-55, 225, 16);
        
        smallDatabaseButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/go button.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        getContentPane().add(smallDatabaseButton);
        smallDatabaseButton.setBounds(569, 365-55, 25, 22);
        smallDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseButtonActionPerformed(evt);
            }
        });
        
        //Creates recipe feedback label and text box
        recipeFeedbackLabel.setText("Feedback? Please copy this link and fill out the form:");
        getContentPane().add(recipeFeedbackLabel);
        recipeFeedbackLabel.setBounds(569, 425-40, 350, 22);   
        recipeFeedback.setText("https://docs.google.com/forms/d/e/1FAIpQLScFnNP0AOAlcVvar297I6QI_evHDkxm9qGtfTERUPLhqNwrbw/viewform?usp=sf_link");
        recipeFeedback.setMaximumSize(null);
        getContentPane().add(recipeFeedback);
        recipeFeedback.setBounds(569, 450-40, 287, 22);
        
        //Creates chef cat button
        catButton.setOpaque(false);
        catButton.setContentAreaFilled(false);
        catButton.setBorderPainted(false);
        catButton.setIcon((new ImageIcon(new ImageIcon(getClass().getResource("cat chef.png")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH))));
        catButton.setToolTipText("");
        catButton.setBorder(null);
        catButton.setRequestFocusEnabled(false);
        catButton.setRolloverEnabled(false);
        catButton.setSelectedIcon((new ImageIcon(new ImageIcon(getClass().getResource("cat chef.png")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH))));
        catButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catButtonActionPerformed(evt);
            }
        });
        getContentPane().add(catButton);
        catButton.setBounds(92, 44, 375, 496);

        pack();
        
        //Creates help button
        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        getContentPane().add(helpButton);
        helpButton.setBounds(10, 10, 90, 22);
        
    }// </editor-fold>                        
    
    //Scrapes a website 
    private void recipeInputBoxActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                               
        
        String recipeWebsite;
        recipeWebsite = recipeInputBox.getText();
        Recipe recipe = new Recipe(recipeWebsite);
        
        int numRecipes = CustomRecipePage.getNOB();
        CustomRecipePage.setTitleArray(recipe.getTitle(), numRecipes);
        CustomRecipePage.setNotesArray(recipe.getNotes(), numRecipes);
        CustomRecipePage.setIngredientsArray(recipe.getIngredients(), numRecipes);
        CustomRecipePage.setUrlArray(recipeWebsite, numRecipes);
        CustomRecipePage.setProcedureArray(recipe.getProcedure(), numRecipes);
        CustomRecipePage.setRecipesArray(recipe, numRecipes);
        CustomRecipePage.setNOB(numRecipes);
  
        new RecipePage().setVisible(true);
        this.dispose();
    }                                              

    //Changes pages to the custom recipe page
    private void customRecipeInputButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        new CustomRecipePage().setVisible(true);
        this.dispose();
    }                                                                                        

    //Changes pages to the database page
    private void databaseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        new DatabasePage().setVisible(true);
        this.dispose();
    }         
    
    //Changes the version of the chef cat 
    private void catButtonActionPerformed(java.awt.event.ActionEvent evt) {
        switch (buttonNumber) {
            case 1:
                catButton.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/cat chef garfield.png")).getImage().getScaledInstance(490, 500, Image.SCALE_SMOOTH)));
                buttonNumber++;
                break;
            case 0:
                catButton.setIcon((new ImageIcon(new ImageIcon(getClass().getResource("/Images/cat chef spooky.png")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH))));
                buttonNumber++;
                break;
            case 2:
                catButton.setIcon((new ImageIcon(new ImageIcon(getClass().getResource("/Images/cat chef pink.png")).getImage().getScaledInstance(485, 500, Image.SCALE_SMOOTH))));
                buttonNumber++;
                break;
            case 3:
                catButton.setIcon((new ImageIcon(new ImageIcon(getClass().getResource("/Images/cat chef.png")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH))));
                buttonNumber = 0;
                break;
            default:
                break;
        }
    }  
    
    //Pop-up that displays the "scrape-able" websites when the "help" button is clicked
    private void helpButtonActionPerformed(java.awt.event.ActionEvent event){
        JOptionPane.showMessageDialog(this, "https://sallysbakingaddiction.com/\n" +
        "https://www.allrecipes.com/\n" +
        "https://food-guide.canada.ca/en/recipes\n" +
        "https://www.foodnetwork.com/ \n" +
        "https://www.jamieoliver.com/recipes/\n" +
        "https://www.seriouseats.com/\n" +
        "https://www.delish.com/\n" +
        "https://www.marthastewart.com/\n" +
        "https://tasty.co/\n" +
        "https://www.ontario.ca/foodland/foodland-ontario\n" +
        "https://www.sobeys.com/en/recipes/ \n" +
        "https://www.eggs.ca/recipes/ \n" +
        "https://more.ctv.ca/food/recipes.html \n" +
        "https://www.diabetes.ca/nutrition---fitness/recipes?Categories=&MealType=&SearchText=&Sort=&Page=1 \n" +
        "https://www.myplate.gov/myplate-kitchen/recipes \n" +
        "https://www.bbcgoodfood.com/recipes \n" +
        "https://dairyfarmersofcanada.ca/en/canadian-goodness/recipes \n" +
        "https://www.pamperedchef.ca/recipe-landing-page", "Websites Available for Recipe Input:", JOptionPane.PLAIN_MESSAGE);
    }

    //Main - runs the page
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontPage().setVisible(true);                        
            }
        });
    }

    // Variables declaration                   
    private javax.swing.JButton catButton;
    private javax.swing.JButton customRecipeInputButton;
    private javax.swing.JButton databaseButton;
    private javax.swing.JLabel icon;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JTextField recipeInputBox;
    private javax.swing.JButton recipeInputButton;
    private javax.swing.JLabel recipeInputLabel;
    private javax.swing.JButton smallCustomRecipeInputButton;
    private javax.swing.JButton smallDatabaseButton;
    private javax.swing.JLabel title;
    private javax.swing.ImageIcon logo;
    private javax.swing.JTextField recipeFeedback;
    private javax.swing.JLabel recipeFeedbackLabel;
    private javax.swing.JButton helpButton;
    // End of variables declaration                   
}