import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RecipePage extends javax.swing.JFrame {

    int exampleNumber;
    static int recipeNumber;
    
    
    public RecipePage() throws IOException {
        initComponents();
    }                      
    private void initComponents() throws FileNotFoundException, IOException {
        
        //Initializes elements of the GUI
        scroll = new javax.swing.JScrollPane();
        firstPanel = new javax.swing.JPanel();
        panel = new javax.swing.JLayeredPane();
        goBackButton = new javax.swing.JButton();
        pulloutMenuButton = new javax.swing.JButton();
        recipeTextSpace = new javax.swing.JLabel();
        recipeTitle = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        editRecipeButton = new javax.swing.JButton();
        leftBorder = new javax.swing.JLabel();
        rightBorder = new javax.swing.JLabel();
        pulloutMenu = new javax.swing.JPanel();
        close = new javax.swing.JButton();
        recipeTitleLabel = new javax.swing.JLabel();
        recipeTitlePullout = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();
        websiteURL = new javax.swing.JLabel();
        tagsLabel = new javax.swing.JLabel();
        tagsListScroll = new javax.swing.JScrollPane();
        tagsList = new javax.swing.JList<>();
        chooseFontLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        font1 = new javax.swing.JButton();
        font2 = new javax.swing.JButton();
        font3 = new javax.swing.JButton();
        bgColourLabel = new javax.swing.JLabel();
        colour8 = new javax.swing.JButton();
        colour1 = new javax.swing.JButton();
        colour2 = new javax.swing.JButton();
        colour3 = new javax.swing.JButton();
        colour4 = new javax.swing.JButton();
        colour5 = new javax.swing.JButton();
        colour6 = new javax.swing.JButton();
        colour7 = new javax.swing.JButton();
        borderOutlineLabel = new javax.swing.JLabel();
        right = new javax.swing.JButton();
        left = new javax.swing.JButton();
        borderExample = new javax.swing.JLabel();
        recipeImage = new javax.swing.ImageIcon();

        //Creates the page
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(960, 540));
        setPreferredSize(new java.awt.Dimension(960, 540));
        
        //Creates a scroll pane
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Sets the default fonts and colours
        panel.setBackground(new java.awt.Color(227, 230, 164));
        pulloutMenu.setBackground(new java.awt.Color(239,241,201));
        editRecipeButton.setBackground(new java.awt.Color(251,222,210));
        editRecipeButton.setFont(new java.awt.Font("Segoe Print", 0, 12));
        goBackButton.setBackground(new java.awt.Color(251,222,210));
        goBackButton.setFont(new java.awt.Font("Segoe Print", 0, 12));
        close.setBackground(new java.awt.Color(251,222,210));
        close.setFont(new java.awt.Font("Segoe Print", 0, 12));
        pulloutMenuButton.setBackground(new java.awt.Color(251,222,210));
        pulloutMenuButton.setFont(new java.awt.Font("Segoe Print", 0, 11));
        font1.setBackground(new java.awt.Color(251,222,210));
        font2.setBackground(new java.awt.Color(251,222,210));
        font3.setBackground(new java.awt.Color(251,222,210));
        recipeTitleLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        recipeTitlePullout.setFont(new java.awt.Font("Segoe Print", 0, 12));
        sourceLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        websiteURL.setFont(new java.awt.Font("Segoe Print", 0, 12));
        tagsLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        tagsList.setFont(new java.awt.Font("Segoe Print", 0, 12));
        chooseFontLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        borderOutlineLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        bgColourLabel.setFont(new java.awt.Font("Segoe Print", 1, 12));
        left.setBorder(null);
        left.setContentAreaFilled(false);
        left.setOpaque(false);
        right.setBorder(null);
        right.setContentAreaFilled(false);
        right.setOpaque(false);
        
        //Elements of the pullout menu
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        firstPanel.setPreferredSize(new java.awt.Dimension(960, 650));
        firstPanel.setRequestFocusEnabled(false);
        
        panel.setOpaque(true);
        
        getContentPane().add(pulloutMenu);
        pulloutMenu.setBounds(640, 0, 310, 1000);
        
        panel.add(pulloutMenu);
        pulloutMenu.setVisible(false);
        pulloutMenu.setLayout(null);
        
        pulloutMenu.setBackground(new java.awt.Color(230, 230, 230));
        
        close.setText("Close");
        pulloutMenu.add(close);
        close.setBounds(205, 10, 72, 22);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        recipeTitleLabel.setText("Recipe Title:");
        pulloutMenu.add(recipeTitleLabel);
        recipeTitleLabel.setBounds(20, 20, 140, 16);
        
        recipeTitlePullout.setText(CustomRecipePage.titleArray[recipeNumber]);
        pulloutMenu.add(recipeTitlePullout);
        recipeTitlePullout.setBounds(20, 40, 250, 16);

        sourceLabel.setText("Source:");
        pulloutMenu.add(sourceLabel);
        sourceLabel.setBounds(20, 70, 50, 16);

        websiteURL.setText(CustomRecipePage.urlArray[recipeNumber]);
        pulloutMenu.add(websiteURL);
        websiteURL.setBounds(20, 90, 250, 16);

        tagsLabel.setText("Tags:");
        pulloutMenu.add(tagsLabel);
        tagsLabel.setBounds(20, 120, 110, 16);

        tagsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sweet", "Savory", "Quick", "Snack", "Meal", "Low Calorie", "Breakfast", "Lunch", "Dinner", "Vegetarian", "Vegan", "Gluten Free", "Baked", "Uncooked" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        tagsListScroll.setViewportView(tagsList);
        pulloutMenu.add(tagsListScroll);
        tagsListScroll.setBounds(20, 140, 220, 40);

        chooseFontLabel.setText("Choose Font:");
        pulloutMenu.add(chooseFontLabel);
        chooseFontLabel.setBounds(20, 220, 80, 16);
        pulloutMenu.add(separator);
        separator.setBounds(20, 200, 250, 10);

        font1.setText("Sans Serif");
        pulloutMenu.add(font1);
        font1.setFont(new java.awt.Font("Tw Cen MT", 0, 9));
        font1.setBounds(20, 240, 75, 22);
        font1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font1ActionPerformed(evt);
            }
        });

        font2.setText("Serif");
        pulloutMenu.add(font2);
        font2.setFont(new java.awt.Font("Times New Roman", 0, 10));
        font2.setBounds(110, 240, 75, 22);
        font2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font2ActionPerformed(evt);
            }
        });

        font3.setText("Fancy");
        pulloutMenu.add(font3);
        font3.setFont(new java.awt.Font("MV Boli", 0, 10));
        font3.setBounds(200, 240, 75, 22);
        font3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font3ActionPerformed(evt);
            }
        });

        bgColourLabel.setText("Choose Background Colour:");
        pulloutMenu.add(bgColourLabel);
        bgColourLabel.setBounds(20, 280, 190, 20);
        
        colour1.setBackground(new java.awt.Color(255, 252, 245));
        pulloutMenu.add(colour1);
        colour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour1ActionPerformed(evt);
            }
        });
        colour1.setBounds(20, 310, 20, 6);
        
        colour2.setBackground(new java.awt.Color(72, 64, 56));
        pulloutMenu.add(colour2);
        colour2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour2ActionPerformed(evt);
            }
        });
        colour2.setBounds(50, 310, 20, 6);
        
        colour3.setBackground(new java.awt.Color(249, 216, 215));
        pulloutMenu.add(colour3);
        colour3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour3ActionPerformed(evt);
            }
        });
        colour3.setBounds(80, 310, 20, 6);
        
        colour4.setBackground(new java.awt.Color(249, 226, 154));
        pulloutMenu.add(colour4);
        colour4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour4ActionPerformed(evt);
            }
        });
        colour4.setBounds(110, 310, 20, 6);
        
        colour5.setBackground(new java.awt.Color(227, 230, 164));
        pulloutMenu.add(colour5);
        colour5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour5ActionPerformed(evt);
            }
        });
        colour5.setBounds(140, 310, 20, 6);
        
        colour6.setBackground(new java.awt.Color(200, 218, 239));
        pulloutMenu.add(colour6);
        colour6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour6ActionPerformed(evt);
            }
        });
        colour6.setBounds(170, 310, 20, 6);
        
        colour7.setBackground(new java.awt.Color(224, 200, 239));
        pulloutMenu.add(colour7);
        colour7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour7ActionPerformed(evt);
            }
        });
        colour7.setBounds(200, 310, 20, 6);
        
        colour8.setBackground(new java.awt.Color(242, 242, 242));
        pulloutMenu.add(colour8);
        colour8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colour8ActionPerformed(evt);
            }
        });
        colour8.setBounds(230, 310, 20, 6);

        borderOutlineLabel.setText("Choose Border Outline:");
        pulloutMenu.add(borderOutlineLabel);
        borderOutlineLabel.setBounds(20, 340, 140, 16);
        pulloutMenu.add(right);
        right.setBounds(230, 410, 20, 20);
        right.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("arrow button right.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightActionPerformed(evt);
            }
        });
        pulloutMenu.add(left);
        left.setBounds(30, 410, 20, 20);
        left.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("arrow button left.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftActionPerformed(evt);
            }
        });

        borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))); // NOI18N
        pulloutMenu.add(borderExample);
        borderExample.setBounds(110, 370, 100, 100);
        exampleNumber = 1;

        goBackButton.setText("Go Back");
        panel.add(goBackButton);
        goBackButton.setBounds(10, 10, 110, 22);
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackActionPerformed(evt);
            }
        });

        pulloutMenuButton.setText("Pullout Menu"); //Add something that makes this lengthen when a certain amount of text is shown... 
        panel.add(pulloutMenuButton);
        pulloutMenuButton.setBounds(807, 10, 110, 22);
        pulloutMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulloutMenuActionPerformed(evt);
            }
        });
        
        image.setBounds(500,150,340,310);
        image.setOpaque(true);
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recipeImage = (new ImageIcon(new ImageIcon(getClass().getResource("catatouille icon.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        image.setIcon(recipeImage);
        image.setBounds(520,150,340,310);
        panel.add(image);

        recipeTextSpace.setText("<html>" + CustomRecipePage.notesArray[recipeNumber] + CustomRecipePage.ingredientsArray[recipeNumber] + CustomRecipePage.procedureArray[recipeNumber] + "</html>");
        recipeTextSpace.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel.add(recipeTextSpace);
        recipeTextSpace.setFont(new java.awt.Font("Segoe Print", 0, 14));
        recipeTextSpace.setBounds(80, 150, 440, 800);

        recipeTitle.setText(CustomRecipePage.titleArray[recipeNumber]);
        recipeTitle.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        recipeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       
        panel.add(recipeTitle);
        recipeTitle.setBounds(110, 40, 760, 40);

        editRecipeButton.setText("Edit Recipe");
        panel.add(editRecipeButton);
        editRecipeButton.setBounds(430, 90, 110, 22);
        editRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    editRecipeActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(RecipePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Borders for the recipe display
        leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border left.png")).getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH)));
        panel.add(leftBorder);
        leftBorder.setBounds(20, 50, 100, 100);

        rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border right.png")).getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH)));
        panel.add(rightBorder);
        rightBorder.setBounds(807, 50, 100, 100);

        javax.swing.GroupLayout firstPanelLayout = new javax.swing.GroupLayout(firstPanel);
        firstPanel.setLayout(firstPanelLayout);
        firstPanelLayout.setHorizontalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        firstPanelLayout.setVerticalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        scroll.setViewportView(firstPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }                        
    
    //Action performed methods for the various buttons
    private void goBackActionPerformed(java.awt.event.ActionEvent evt) {                                               
        new DatabasePage().setVisible(true);
        this.dispose();
    } 
    
    private void editRecipeActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                               
        new RecipeEditPage().setVisible(true);
        this.dispose();
    } 
    
    private void pulloutMenuActionPerformed(java.awt.event.ActionEvent evt) {                                               
        pulloutMenu.setVisible(true);
        pulloutMenuButton.setVisible(false);
        close.setVisible(true);
    } 
    
    private void closeActionPerformed(java.awt.event.ActionEvent evt) {                                               
        pulloutMenu.setVisible(false);
        close.setVisible(false);
        pulloutMenuButton.setVisible(true);
    } 
    
    private void font1ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        recipeTextSpace.setFont(new java.awt.Font("Tw Cen MT", 0, 14));
        recipeTitle.setFont(new java.awt.Font("Tw Cen MT", 0, 24));
        goBackButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12));
        pulloutMenuButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12));
        editRecipeButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12));
    } 
    
    private void font2ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        recipeTextSpace.setFont(new java.awt.Font("Times New Roman", 0, 14));
        recipeTitle.setFont(new java.awt.Font("Sitka Banner", 0, 24));
        goBackButton.setFont(new java.awt.Font("Times New Roman", 0, 12));
        pulloutMenuButton.setFont(new java.awt.Font("Times New Roman", 0, 12));
        editRecipeButton.setFont(new java.awt.Font("Times New Roman", 0, 12));
    } 
    
    private void font3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        recipeTextSpace.setFont(new java.awt.Font("Vivaldi", 0, 16));
        recipeTitle.setFont(new java.awt.Font("Palace Script MT", 0, 30));
        goBackButton.setFont(new java.awt.Font("Palace Script MT", 0, 12));
        pulloutMenuButton.setFont(new java.awt.Font("Palace Script MT", 0, 12));
        editRecipeButton.setFont(new java.awt.Font("Palace Script MT", 0, 12));
    } 
    
    private void colour1ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(255, 252, 245));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour2ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(72, 64, 56));
        recipeTitle.setForeground(new java.awt.Color(255, 255, 255));
        recipeTextSpace.setForeground(new java.awt.Color(255, 255, 255));
    } 
    
    private void colour3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(249, 216, 215));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(249, 226, 154));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour5ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(227, 230, 164));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour6ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(200, 218, 239));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour7ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(224, 200, 239));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void colour8ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        panel.setBackground(new java.awt.Color(242, 242, 242));
        recipeTitle.setForeground(new java.awt.Color(0, 0, 0));
        recipeTextSpace.setForeground(new java.awt.Color(0, 0, 0));
    } 
    
    private void rightActionPerformed(java.awt.event.ActionEvent evt){
        if (exampleNumber == 6){
            exampleNumber = 1;
        }
        else{
            exampleNumber++;
        }
        borderChoose(exampleNumber);
    }
    
    private void leftActionPerformed(java.awt.event.ActionEvent evt){ 
        if (exampleNumber == 1){
            exampleNumber = 6;
        }
        else{
            exampleNumber--;
        }
        borderChoose(exampleNumber);
    }
    
    private void borderChoose(int number){
        if (number == 1){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border left.png")).getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border left.png")).getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Meatball border right.png")).getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH)));
        }
        if (number == 2){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/baking border left.png")).getImage().getScaledInstance(100, 113, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/baking border left.png")).getImage().getScaledInstance(100, 113, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/baking border right.png")).getImage().getScaledInstance(100, 113, Image.SCALE_SMOOTH)));
        }
        if (number == 3){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Stars border left.png")).getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Stars border left.png")).getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/Stars border right.png")).getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH)));
        }
        if (number == 4){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/mouse border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/mouse border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/mouse border right.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
        if (number == 5){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/flower border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/flower border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/flower border right.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
        if (number == 6){
            borderExample.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/simple border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            leftBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/simple border left.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            rightBorder.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/simple border right.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
    }
    
    public void setRecipeNumber(int n){
        recipeNumber = n;
    }
    
    public int getRecipeNumber(){
        return recipeNumber;
    }
  
    //Main method
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RecipePage().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(RecipePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton editRecipeButton;
    private javax.swing.JPanel firstPanel;
    private javax.swing.JButton goBackButton;
    private javax.swing.JLabel image;
    private javax.swing.JLabel leftBorder;
    private javax.swing.JLabel rightBorder;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JButton pulloutMenuButton;
    private javax.swing.JLabel recipeTextSpace;
    private javax.swing.JLabel recipeTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel bgColourLabel;
    private javax.swing.JLabel borderExample;
    private javax.swing.JLabel borderOutlineLabel;
    private javax.swing.JLabel chooseFontLabel;
    private javax.swing.JButton close;
    private javax.swing.JButton colour1;
    private javax.swing.JButton colour2;
    private javax.swing.JButton colour3;
    private javax.swing.JButton colour4;
    private javax.swing.JButton colour5;
    private javax.swing.JButton colour6;
    private javax.swing.JButton colour7;
    private javax.swing.JButton colour8;
    private javax.swing.JButton font1;
    private javax.swing.JButton font2;
    private javax.swing.JButton font3;
    private javax.swing.JButton left;
    private javax.swing.JPanel pulloutMenu;
    private javax.swing.JLabel recipeTitlePullout;
    private javax.swing.JLabel recipeTitleLabel;
    private javax.swing.JButton right;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JLabel tagsLabel;
    private javax.swing.JList<String> tagsList;
    private javax.swing.JScrollPane tagsListScroll;
    private javax.swing.JLabel websiteURL;
    private javax.swing.ImageIcon recipeImage;
    // End of variables declaration                   
}