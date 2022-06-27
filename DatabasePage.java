import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DatabasePage extends javax.swing.JFrame {
    
    int testPressHorizontal = 0;
    int testPressVertical = 0;
    static int numberOfButtons;
    static int buttonsRemoved;
    int buttonNumber;
    static int buttonIndex;
    
    public DatabasePage() {
        initComponents();
    }
                          
    private void initComponents() {
       
        CustomRecipePage.convertToArray();
        numberOfButtons = CustomRecipePage.getNOB();
        
        panel = new javax.swing.JLayeredPane();
        firstPanel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        recipeTitle = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        tagFilter = new javax.swing.JComboBox<>();
        goBackButton = new javax.swing.JButton();
        randomButton = new javax.swing.JButton();
        delete = new java.awt.PopupMenu();
        deleteOption = new java.awt.MenuItem("Delete Recipe");
        recipeButton = new JButton[100];

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(250, 120, 88));
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(960, 540));
       
        panel.setOpaque(true);
        
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        firstPanel.setPreferredSize(new java.awt.Dimension(960, 540));
        firstPanel.setRequestFocusEnabled(false);
        
        panel.setBackground(new java.awt.Color(227, 230, 164));
        goBackButton.setBackground(new java.awt.Color(251,222,210));
        searchButton.setBackground(new java.awt.Color(251,222,210));
        randomButton.setBackground(new java.awt.Color(251,222,210));
        searchBar.setBackground(new java.awt.Color(249,249,235));
        tagFilter.setBackground(new java.awt.Color(249,249,235));
        goBackButton.setFont(new java.awt.Font("Segoe Print", 0, 12));
        searchBar.setFont(new java.awt.Font("Segoe Print", 0, 12));
        searchButton.setFont(new java.awt.Font("Segoe Print", 0, 12));
        tagFilter.setFont(new java.awt.Font("Segoe Print", 0, 12));
        randomButton.setFont(new java.awt.Font("Segoe Print", 0, 12));
        
        recipeTitle.setFont(new java.awt.Font("Segoe Print", 1, 30));
        recipeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recipeTitle.setText("Recipes");
        panel.add(recipeTitle);
        recipeTitle.setBounds(375, 15, 210, 60);

        searchBar.setText("Search Recipe");
        searchBar.setMaximumSize(null);
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        panel.add(searchBar);
        searchBar.setBounds(70, 90, 350, 22);

        searchButton.setText("Search");
        panel.add(searchButton);
        searchButton.setBounds(420, 90, 89, 22);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        tagFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter by Tag", "Sweet", "Savory", "Quick", "Snack", "Meal", "Low Calorie", "Breakfast", "Lunch", "Dinner", "Vegetarian", "Vegan", "Gluten Free", "Baked", "Uncooked" }));
        for (int i = 0; i < 14; i++){
            //System.out.print(Arrays.toString(list.toArray()));
        }
        panel.add(tagFilter);
        tagFilter.setBounds(520, 90, 350, 22);
        
        goBackButton.setText("Go Back");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });
        panel.add(goBackButton);
        goBackButton.setBounds(10, 10, 90, 22);
        
        if (CustomRecipePage.NOB > 0){
            randomButton.setText("Random");
            randomButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        randomButtonActionPerformed(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(DatabasePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            panel.add(randomButton);
            randomButton.setBounds(841, 10, 90, 22);
        }

        numberOfButtons = numberOfButtons - buttonsRemoved;
        
        for(int n = 0; n < numberOfButtons; n++){
            testPressHorizontal++;
            if (testPressHorizontal == 4){
                testPressHorizontal = 1;
                testPressVertical++;
            }
            
            recipeButton[n] = new javax.swing.JButton();
            recipeButton[n].setIcon((new ImageIcon(new ImageIcon(getClass().getResource("catatouille icon.png")).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH))));
            recipeButton[n].setText(CustomRecipePage.titleArray[n]);
            recipeButton[n].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            recipeButton[n].setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            recipeButton[n].setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            recipeButton[n].setBackground(new java.awt.Color(249,249,235));
            recipeButton[n].setFont(new java.awt.Font("Segoe Print", 0, 12));
            recipeButton[n].setAlignmentY(1.0F);
            recipeButton[n].setEnabled(true);
            recipeButton[n].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        recipeButtonActionPerformed(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(DatabasePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            recipeButton[n].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON3){
                        rightButtonClicked(evt);
                    }
                }
            });
            panel.add(recipeButton[n]);
            recipeButton[n].setBounds(70 + 270*(testPressHorizontal-1), 130 + 270*testPressVertical, 260, 260);
            firstPanel.setPreferredSize(new java.awt.Dimension(960, 540 + 280*testPressVertical));
            buttonIndex++;
        }
        
        delete.add(deleteOption); 
        panel.add(delete);
        
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
    
    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if ("".contains(searchBar.getText())){
            for (int i = numberOfButtons-1; i > -1; i--){
               recipeButton[i].setVisible(true);
               //recipeButton[i].setIcon(CustomRecipePage.image[i]);
               recipeButton[i].setText(CustomRecipePage.titleArray[i]);
            }
        }
        
        else{
            int NOBsearched = 0;
            
            for (int i = numberOfButtons-1; i > -1; i--){
               recipeButton[i].setVisible(false);
            }
            for (int i = 0; i < numberOfButtons; i++){
               if (CustomRecipePage.titleArray[i].contains(searchBar.getText())){
                    recipeButton[NOBsearched].setVisible(true);
                    //recipeButton[NOBsearched].setIcon(CustomRecipePage.image[i]);
                    recipeButton[NOBsearched].setText(CustomRecipePage.titleArray[i]);
                    NOBsearched++;
               }
            }
            for (int i = numberOfButtons-1; i > -1; i--){
               String buttonText = recipeButton[i].getText();
               if (!buttonText.contains(searchBar.getText())){
                   recipeButton[i].setVisible(false);
                   for (int k = i; k > 0; k--){
                       
                   }
               }
               else {
                   recipeButton[i].setVisible(true);
               }
            }
        }
    }    
    
    private void recipeButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                             
        for (int m = 0; m < numberOfButtons; m++){
            if (evt.getSource() == recipeButton[m]){
                    buttonNumber = m;     
            }
            
            if (!CustomRecipePage.titleArray[buttonNumber].equals(recipeButton[buttonNumber].getText())){
                        for (int p = 0; p < numberOfButtons; p++){
                            if (CustomRecipePage.titleArray[p].equals(recipeButton[buttonNumber].getText())){
                                buttonNumber = p;
                                break;
                            } //doesn't work when recipes are the same, add thing to catch ingredients/recipes
                        }
                    }
        }
        
        new RecipePage().setRecipeNumber(buttonNumber);
        
        new RecipePage().setVisible(true);
        this.dispose();
    } 
    
    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new FrontPage().setVisible(true);
        this.dispose();
    } 
    
    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        Random random = new Random();
        int n = random.nextInt(CustomRecipePage.NOB);
        new RecipePage().setRecipeNumber(n);
        new RecipePage().setVisible(true);
        this.dispose();
    } 
    
    private void rightButtonClicked(java.awt.event.MouseEvent evt) { 
        for (int m = numberOfButtons-1; m > -1; m--){
            if (evt.getSource() == recipeButton[m]){
                    delete.show(recipeButton[m],evt.getX(),evt.getY());
                    buttonNumber = m;
            }
        }
        deleteOption.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteClicked(evt, buttonNumber);
                }
        });
    }
     
    private void deleteClicked(java.awt.event.ActionEvent evt, int bn){
        int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this recipe?", "Delete Recipe?", JOptionPane.PLAIN_MESSAGE);
        if(a == JOptionPane.YES_OPTION){
            recipeButton[bn].setVisible(false);
            panel.remove(recipeButton[bn]);
            getContentPane().remove(recipeButton[bn]);
            for (int i = bn+1; i < numberOfButtons; i++) {
                CustomRecipePage.titleArray[i-1] = CustomRecipePage.titleArray[i];
                CustomRecipePage.urlArray[i-1] = CustomRecipePage.urlArray[i];
                CustomRecipePage.ingredientsArray[i-1] = CustomRecipePage.ingredientsArray[i];
                CustomRecipePage.notesArray[i-1] = CustomRecipePage.notesArray[i];
                CustomRecipePage.procedureArray[i-1] = CustomRecipePage.procedureArray[i];
                //CustomRecipePage.image[i-1] = CustomRecipePage.image[i];
            }
            buttonsRemoved++;
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabasePage().setVisible(true);
            }
        });
    }
    
    // Variables Declaration -
    private javax.swing.JLayeredPane panel;
    private javax.swing.JPanel firstPanel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JButton recipeButton[];
    private java.awt.PopupMenu delete;
    private java.awt.MenuItem deleteOption;
    private javax.swing.JLabel recipeTitle;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> tagFilter;
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton randomButton;
    // End of variables declaration
}

