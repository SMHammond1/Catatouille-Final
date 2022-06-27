import java.io.*;
import org.jsoup.Jsoup; //Taken from https://www.javatpoint.com/jsoup-tutorial
import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Recipe {
    //Instance variables
    private static String url;
    private static String title;
    private static String ingredients;
    private static String procedure;
    private static String notes; 
    private static String recipe;
    private static String fileTitle;
    private static int borderType; 
    private static String source;

    //Constructors
    public Recipe(String t, String i, String p, String n) throws IOException{ //Constructor for custom recipe
        fileTitle = t;
        title = t;
        ingredients = i;
        sortCustomIngredients();
        procedure = p;
        sortCustomProcedure();
        notes = n;
        recipe = "Title: " + title + "\n\nNotes: " + notes + "\n\nIngredients: " + ingredients + "\n\nProcedure: " + procedure; 
        storeRecipe();
    }
    
    public Recipe(String t, String s, String i, String p, String n) throws IOException{
        fileTitle = t;
        title = t;
        source = s;
        ingredients = i;
        sortCustomIngredients();
        procedure = p;
        sortCustomProcedure();
        notes = n;
        recipe = "Title: " + title + "\nSource: " + source + "\n\nNotes: " + notes + "\n\nIngredients: " + ingredients + "\n\nProcedure: " + procedure; 
        storeRecipe();
    }
    
    public Recipe(String u) throws IOException{ //Constructor for recipe via url
        url = u;
        try {
            getRecipe();
            recipe = "Title: " + title + "\n\nSource: "+ url + "\n\nNotes: "+ notes + "\n\nIngredients: \n" + ingredients + "\n\nProcedure: " + procedure; 
        } catch (IllegalArgumentException | MalformedURLException exception){
        }  
    }
    
    public Recipe(String x, String t, String s, String n, String i, String p){
        recipe = "Title: " + t + "\n\nSource: "+ s + "\n\nNotes: "+ n + "\n\nIngredients: \n" + i + "\n\nProcedure: " + p;
    }
    
    public Recipe(){ //Paramaterless "recipe"
        recipe = "\nNo recipe information was entered. No recipe found.";
    }
    
    //Instance methods 
    private static String getRecipe() throws IOException{ //Gives recipe from a url
        
        boolean recipeExists = isRecipeAvailable(url); //Sends url to isRecipeAvailable method to check if it is one of "scrape-able" recipe sites
        
        if (recipeExists){
            scrapeWebsite(url);
            recipe = "Title: " + title + "\n\nSource: "+ url + "\n\nNotes: " +  notes + "\n\nIngredients: \n" + ingredients + "\n\nProcedure: " + procedure;
            storeRecipe();
            return recipe;
        } else {
            return "\nRecipe not found.";
        }
    }
    
    public static void storeRecipe() throws IOException{ //This method stores the recipe information into a new text file   
        FileWriter fw = new FileWriter(fileTitle + ".txt");
        PrintWriter fileOut = new PrintWriter (fw);
        fileOut.println(recipe);
        fileOut.close();
    }
  
    private static void scrapeWebsite(String s) throws IOException{ //Scrapes website from a set of "valid" URLs 
        
        String url = s;
        Document recipe = Jsoup.connect(url).get(); //Taken from https://jsoup.org/cookbook/input/load-document-from-url
        String instructionStep;
        
        if (url.contains("sallysbakingaddiction")){ //https://sallysbakingaddiction.com
            fileTitle = recipe.title();
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "tasty-recipes-header-content clearfix");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "tasty-recipes-notes");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "tasty-recipes-ingredients-body").first();

            title = recipeTitleAndInfo.text();
            
            try {
                title = title.substring(0, title.indexOf("★"));
            } catch (NullPointerException e){
                
            }
            notes = recipeTitleAndInfo.text().substring(recipeTitleAndInfo.text().indexOf("★"), recipeTitleAndInfo.text().length()) + "\n" + recipeNotes.text();
            procedure = "1. " + (recipe.getElementsByAttributeValue("id", "instruction-step-1")).text();            
            for (int k = 2; k <101; k++){ //Puts each line of the procedure of the recipe onto its own line
                instructionStep = "instruction-step-" + k;
               
                if ("".equals(recipe.getElementsByAttributeValue("id", instructionStep).text())){
                } else {
                    procedure += "\n" + k + ". " + (recipe.getElementsByAttributeValue("id", instructionStep)).text();
                }                
            }
            sortIngredients(recipeIngredients);

        } else if (url.contains("allrecipes")){ //https://www.allrecipes.com
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "headline heading-content elementFont__display");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe-meta-container two-subcol-content clearfix recipeMeta");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients-section__fieldset").first();
            Elements recipeProcedure = recipe.getElementsByAttributeValue("class", "subcontainer instructions-section-item");

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            procedure = recipeProcedure.text();
            sortProcedure();
            sortIngredients(recipeIngredients);

        } else if (url.contains("food-guide")){//https://food-guide.canada.ca/en/recipes
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "page_title page-title-en");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "field field--name-body field--type-text-with-summary field--label-hidden field--item");
            Elements recipeTips = recipe.getElementsByAttributeValue("class", "field field--name-field-cooking-tips field--type-text-long field--label-above");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "field field--name-field-ingredients").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "field field--name-field-directions").first(); 
            
            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text() + " / " + recipeTips.text();

            //This tries different ways of sorting it based on how the website page is coded - older recipes on the site had a different formatting
            try{
                Element[] procedureArray = new Element[50];
                int stepNum = 1;

                for (int k = 0; k < procedureArray.length; k++){
                  try {
                    procedureArray[k] = recipeProcedure.getElementsByTag("li").first();
                    recipeProcedure.getElementsByTag("li").first().remove();
                  } catch (NullPointerException e){

                  }
                }
                
                for (Element procedureArray1 : procedureArray) {
                  String step = "" + procedureArray1;
                  if (!"null".equals(step)) {
                      procedure += stepNum + ". " + procedureArray1.text() + "\n";
                      stepNum++;
                  }
                }
                procedure = procedure.substring(procedure.indexOf("null")+4, procedure.length());
                
            } catch(NullPointerException e){
                procedure = recipeProcedure.text();
                sortProcedure();
              }
            
            sortIngredients(recipeIngredients);

        } else if (url.contains("foodnetwork")){//https://www.foodnetwork.com/ 
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "o-AssetTitle");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "o-RecipeInfo");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "o-Ingredients").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "o-Method__m-Body").first(); 

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();

            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);
            
        } else if (url.contains("jamieoliver")){ //https://www.jamieoliver.com/recipes
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "hidden-xs");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "row recipe-details");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "col-md-12 ingredient-wrapper").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "recipeSteps").first();

            fileTitle = recipe.getElementsByAttributeValue("class","h1 single-recipe-title").text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            notes = notes.substring(0, notes.indexOf("In")+2) + " " + notes.substring(notes.indexOf("In")+2, notes.indexOf("Difficulty")+10) + " " + notes.substring(notes.indexOf("Difficulty")+10, notes.length());
            sortIngredients(recipeIngredients);
            
            try {
                sortProcedureV2(recipeProcedure);
            } catch (NullPointerException exception){       
                procedure = recipe.getElementsByAttributeValue("class", "method-p").text();
            }

        } else if (url.contains("seriouseats")){ //https://www.seriouseats.com/ [Some recipes do not work on this site]
           
            try {
                Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "heading__title");
                Elements recipeNotes = recipe.getElementsByAttributeValue("id", "project-meta_1-0");
                Element recipeIngredients = recipe.getElementsByAttributeValue("id", "structured-ingredients_1-0").first();
                Element recipeProcedure = recipe.getElementsByAttributeValue("id", "mntl-sc-block_3-0").first();

                fileTitle = recipeTitleAndInfo.text();
                title = recipeTitleAndInfo.text();
                notes = recipeNotes.text();

                
                sortProcedureV2(recipeProcedure);
                
            } catch (NullPointerException except){ //Catches recipes with different formatting for the ingredients than most recipes on the site
                
                Element recipeIngredients = recipe.getElementsByAttributeValue("id", "ingredient-list_1-0").first();
                Element ingr = recipeIngredients;   
                Element[] ingredientsArray = new Element[50];
                
                for (int i = 0; i < ingredientsArray.length; i++){
                    try {
                      ingredientsArray[i] = ingr.getElementsByTag("li").first();
                      ingr.getElementsByTag("li").first().remove();
                    } catch (NullPointerException e){   
                    }
                }

                for (Element ingredientsArray1 : ingredientsArray) {
                    String ingredient = "" + ingredientsArray1;
                    if (!"null".equals(ingredient)) {
                        ingredients += ingredientsArray1.text() + "\n";
                    }
                  } 
            
                ingredients = ingredients.substring(4, ingredients.length());
                
                Element recipeProcedure = recipe.getElementsByAttributeValue("id", "mntl-sc-block_3-0").first();
                
                sortProcedureV2(recipeProcedure);
            
            }

        } else if (url.contains("delish")){ //https://www.delish.com/
            fileTitle = recipe.title();
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "content-header recipe-header");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe-details-container");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients-body").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "directions-body").first(); 

            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);            

        } else if (url.contains("marthastewart")){ //https://www.marthastewart.com/
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "headline heading-content elementFont__display");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe-meta-container two-subcol-content clearfix recipeMeta");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "recipe-shopper-wrapper").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "instructions-section").first();

            fileTitle = recipe.getElementsByAttributeValue("class", "headline heading-content elementFont__display").text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            
            try {
                sortProcedureV2(recipeProcedure);
            } catch (NullPointerException exception){
                procedure = recipeProcedure.text();
                sortProcedure();
            }

        } else if (url.contains("tasty")){ //https://tasty.co/
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "recipe-name extra-bold xs-mb05 md-mb1");
            Element recipeNotes = recipe.getElementsByAttributeValue("class", "servings-display xs-text-2 xs-mb2").first();
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients__section xs-mt1 xs-mb3").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "prep-steps list-unstyled xs-text-3").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);

        } else if (url.contains("foodland")){ //https://www.ontario.ca/foodland/foodland-ontario
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "large-6 column");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe-description columns");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "recipe-ingredients").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "recipe-instructions recipe-section").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);

        } else if (url.contains("sobeys")){ //https://www.sobeys.com/en/recipes/ 
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "text-container");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "component_recipe_details  component-recipe-details d-flex justify-content-between");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "component_recipe_ingredients  component-recipe-ingredients ").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "component_recipe_steps  component-recipe-steps ").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);

        } else if (url.contains("eggs.ca")){ //https://www.eggs.ca/recipes/
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("itemprop", "name");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe-info");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients__inner").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "steps").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text(); 
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);            

        } else if (url.contains("ctv")){ //https://more.ctv.ca/food/recipes.html
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "c-title");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients aem-GridColumn--tablet--4 aem-GridColumn aem-GridColumn--phone--3 aem-GridColumn--default--3").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "c-directions").first();
 
            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipe.getElementsByAttributeValue("class", "c-recipedetails__info").text() + " " + recipe.getElementsByAttributeValue("class","dt-duration").text();
            
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure); 

        } else if (url.contains("diabetes.ca")){ //https://www.diabetes.ca/nutrition---fitness/recipes?Categories=&MealType=&SearchText=&Sort=&Page=1
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "nb-u-10/12@md");
            Element procedureIngredients = recipe.getElementsByAttributeValue("class", "nb-o-grid__item nb-u-7/12@md").first();
            Element recipeIngredients = procedureIngredients.getElementsByTag("ul").first();
            Element recipeProcedure = procedureIngredients.getElementsByTag("ol").first();
            
            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = procedureIngredients.text().substring(procedureIngredients.text().indexOf("Prep Time"), procedureIngredients.text().indexOf("Ingredients"));
            
            try {
                sortProcedureV2(recipeProcedure);
                sortIngredients(recipeIngredients);
            } catch (NullPointerException exception){
                Elements tempContents = recipe.getElementsByAttributeValue("class", "nb-o-grid__item nb-u-7/12@md");
                String content = tempContents.text();
                ingredients = content.substring(content.indexOf("Ingredients"), content.indexOf("1."));
                procedure = content.substring(content.indexOf("1."), content.indexOf("Sponsored by"));
                sortProcedure();
            }

        } else if (url.contains("myplate")){ //https://www.myplate.gov/myplate-kitchen/recipes
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "mp-recipe-full__title desktop:grid-col-6");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", " mp-recipe-full__overview desktop:grid-col-5 grid-row");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "field field--name-field-ingredients field--type-entity-reference-revisions field--label-above").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "clearfix text-formatted field field--name-field-instructions field--type-text-long field--label-above").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            
            sortIngredients(recipeIngredients);      
            
            /*Tries the "usual" sorting method, but tries another if it does not work (depending on when the recipe page was published, the formatting is slightly different.
              This allows recipes with different formatting to be scraped from this website.*/
            try {
                sortProcedureV2(recipeProcedure);
                            
            } catch (StringIndexOutOfBoundsException | NullPointerException e) {
                try {
                    Element[] procedureArray = new Element[50];
                    int stepNum = 1;
                    for (int k = 0; k < procedureArray.length; k++){
                        try {
                          procedureArray[k] = recipeProcedure.getElementsByTag("p").first();
                          recipeProcedure.getElementsByTag("p").first().remove();
                        } catch (NullPointerException exception){
                        }
                     }
                    for (Element procedureArray1 : procedureArray) {
                        String step = "" + procedureArray1;
                        if (!"null".equals(step)) {
                            procedure += procedureArray1.text() + "\n";
                            stepNum++;
                        }
                    }
                    procedure = procedure.substring(4, procedure.length());
                } catch (NullPointerException excep){
                    procedure = recipeProcedure.text();
                    sortProcedure();
                }   
            } 

        } else if (url.contains("bbcgoodfood")){ //https://www.bbcgoodfood.com/recipes
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "headline post-header__title post-header__title--masthead-layout");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "post-header__row post-header__planning list list--horizontal");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "recipe__ingredients col-12 mt-md col-lg-6").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "recipe__method-steps mb-lg col-12 col-lg-6").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();  
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);            

        } else if (url.contains("dairyfarmersofcanada")){ //https://dairyfarmersofcanada.ca/en/canadian-goodness/recipes
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "hero__title");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "recipe__l");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "ingredients-list").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("class", "preparation-steps").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);

        } else if (url.contains("pamperedchef")){ //https://www.pamperedchef.ca/recipe-landing-page
            Elements recipeTitleAndInfo = recipe.getElementsByAttributeValue("class", "title clearfix title-xs-sm hidden-md hidden-lg");
            Elements recipeNotes = recipe.getElementsByAttributeValue("class", "reviews-block recipes-time-block");
            Element recipeIngredients = recipe.getElementsByAttributeValue("class", "prd-wrap").first();
            Element recipeProcedure = recipe.getElementsByAttributeValue("id", "rpDirections").first();

            fileTitle = recipeTitleAndInfo.text();
            title = recipeTitleAndInfo.text();
            notes = recipeNotes.text() + "\n" + recipe.getElementsByAttributeValue("id", "rpYield").text() + "\n" + recipe.getElementsByAttributeValue("id","rpTips").text();
            sortIngredients(recipeIngredients);
            sortProcedureV2(recipeProcedure);           
        } 
    }
    
    private static boolean isRecipeAvailable(String r){ //Checks if the URL is one that is "scrape-able" (ie. its different formatting has been added to the scraper)
       
        boolean recipeAvailable;
        String checkRecipe = r;
        
        if (checkRecipe.contains("sallysbakingaddiction")){  
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("allrecipes")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("food-guide")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("foodnetwork")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("jamieoliver")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("seriouseats")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("delish")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("marthastewart")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("tasty")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("foodland")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("sobeys")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("eggs.ca")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("ctv")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("diabetes.ca")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("myplate")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("bbcgoodfood")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("dairyfarmersofcanada")){
            recipeAvailable = true;
            
        } else if (checkRecipe.contains("pamperedchef")){
            recipeAvailable = true;
            
        } else {
            recipeAvailable = false;
        }        
        return recipeAvailable;
    }
    
    //This method sorts ingredients based on how the html on the website is formatted
    private static void sortIngredients(Element recipeIngredients){
        
        Element ingr = recipeIngredients;   
        Element[] ingredientsArray = new Element[50];      
        
        if (url.contains("foodnetwork") | url.contains("seriouseats")){
            for (int i = 0; i < ingredientsArray.length; i++){
                try {
                  ingredientsArray[i] = ingr.getElementsByTag("p").first();
                  ingr.getElementsByTag("p").first().remove();
                } catch (NullPointerException e){   
                }
            }
            
            for (Element ingredientsArray1 : ingredientsArray) {
                String ingredient = "" + ingredientsArray1;
                if (!"null".equals(ingredient)) {
                    ingredients += ingredientsArray1.text() + "\n";
                }
              } 
            
            if (url.contains("foodnetwork")){
                ingredients = ingredients.substring(ingredients.indexOf("Deselect All")+12, ingredients.length());
            } else {
                ingredients = ingredients.substring(4, ingredients.length());
            }
                
        } else if (url.contains("delish")){
            
            int num = 1;

            for (int i = 0; i < ingredientsArray.length; i++){
                try {
                  ingredientsArray[i] = ingr.getElementsByTag("span").first();
                  ingr.getElementsByTag("span").first().remove();
                } catch (NullPointerException e){   
                }
            }       
            for (Element ingredientsArray1 : ingredientsArray) {
                
                if (num%2 == 0){
                    String ingredient = "" + ingredientsArray1;
                    if (!"null".equals(ingredient)) {
                        ingredients += ingredientsArray1.text() + "\n";
                    }
                } else {
                    String ingredient = "" + ingredientsArray1;
                    if (!"null".equals(ingredient)) {
                        ingredients += ingredientsArray1.text() + " ";
                    }
                }
                
                num++;
                
              } 
            
            ingredients = ingredients.substring(4, ingredients.length());
            
        } else if (url.contains("sobeys")){
            for (int i = 0; i < ingredientsArray.length; i++){
                try {
                  ingredientsArray[i] = ingr.getElementsByTag("dl").first();
                  ingr.getElementsByTag("dl").first().remove();
                } catch (NullPointerException e){   
                }     
            }
            
            for (Element ingredientsArray1 : ingredientsArray) {
                String ingredient = "" + ingredientsArray1;
                if (!"null".equals(ingredient)) {
                    ingredients += ingredientsArray1.text() + "\n";
                }
              } 
            
            ingredients = ingredients.substring(4, ingredients.length());
            
        } else if (url.contains("marthastewart")){
            for (int i = 0; i < ingredientsArray.length; i++){
                try {
                  ingredientsArray[i] = ingr.getElementsByTag("span").first();
                  ingr.getElementsByTag("span").first().remove();
                } catch (NullPointerException e){   
                }     
            }
            
            for (Element ingredientsArray1 : ingredientsArray) {
                String ingredient = "" + ingredientsArray1;
                if (!"null".equals(ingredient)) {
                    ingredients += ingredientsArray1.text() + "\n";
                }
              } 
            
            ingredients = ingredients.substring(4, ingredients.length());
        
        } else {
            for (int i = 0; i < ingredientsArray.length; i++){
                try {
                  ingredientsArray[i] = ingr.getElementsByTag("li").first();
                  ingr.getElementsByTag("li").first().remove();
                } catch (NullPointerException e){   
                }
            }

            for (Element ingredientsArray1 : ingredientsArray) {
                String ingredient = "" + ingredientsArray1;
                if (!"null".equals(ingredient)) {
                    ingredients += ingredientsArray1.text() + "\n";
                }
              } 
            
            ingredients = ingredients.substring(4, ingredients.length());            
        }      
    }
    
    //This method sorts the procedure for allrecipes and marthastewart recipes, so that each step is on its own line.  
    private static void sortProcedure(){ 
        
        if (url.contains("allrecipes") | url.contains("marthastewart")){ //This part of the method works for allrecipes.com or marthastewart recipes
            String tempString = "\n";
            int stepNumber = 2;
            String step;

            for (int i = 0; i < procedure.length();i++){
              step = "Step " + stepNumber;

              try { 
                if (procedure.contains(step)){
                    if (stepNumber == 2){ //After step 1 on allrecipes.com and marthastewart.com "advertisement" is added with the scraped info. This removes it.
                        if (url.contains("marthastewart")){ //I will probably need to move this so that the 2 step isn't removed
                            procedure = procedure.substring(procedure.indexOf("Step 1"), procedure.length());
                            tempString+= procedure.substring(procedure.indexOf("Step 1"),procedure.indexOf("Advertisement")) + "\n" + procedure.substring(procedure.indexOf("Step 2"), procedure.indexOf(step));
                            procedure = procedure.substring(procedure.indexOf(step), procedure.length());
                        } else if (url.contains("allrecipes")){
                            tempString += procedure.substring(0, procedure.indexOf("Advertisement")) + "\n";
                            procedure = procedure.substring(procedure.indexOf(step), procedure.length());
                        }                           

                        stepNumber++; 

                    } else {
                        tempString += procedure.substring(0, procedure.indexOf(step)) + "\n";
                        procedure = procedure.substring(procedure.indexOf(step), procedure.length());
                        stepNumber++;   
                    }
                } else { //Removes "Advertisement" from the end of step 1 
                        procedure = procedure.substring(0, procedure.indexOf("Advertisement"));
                }
                    
                } catch (StringIndexOutOfBoundsException e){ 
                }
            }
            procedure = tempString + procedure + "\n";
            
        } else if (url.contains("food-guide.canada") | url.contains("diabetes.ca") | url.contains("myplate")){ //Sorts the procedure for recipes on the Canadian Food Guide website
            
            String stepNum;
            String tempString = "\n";
            
            for (int i = 1; i < 100; i++){
                stepNum = i + ". ";
                if (procedure.indexOf(stepNum)>0){
                    tempString += procedure.substring(0, procedure.indexOf(stepNum));
                    procedure = "\n" + procedure.substring(procedure.indexOf(stepNum), procedure.length());
                }
            } procedure = tempString + procedure;
        }
    }
    
    //sortProcedureV2 sorts the recipes based on tags in the html, which varries between each website. 
    private static void sortProcedureV2 (Element e){
        Element recipeProcedure = e;        
        Element[] procedureArray = new Element[50];
        int stepNum = 1;

        if (url.contains("foodland") | url.contains("eggs.ca")| url.contains("marthastewart")| url.contains("seriouseats") | url.contains("dairyfarmersofcanada")){
            for (int k = 0; k < procedureArray.length; k++){
              try {
                procedureArray[k] = recipeProcedure.getElementsByTag("p").first();
                recipeProcedure.getElementsByTag("p").first().remove();
              } catch (NullPointerException exception){
              }
            }
        } else if (url.contains("sobeys")){
            for (int k = 0; k < procedureArray.length; k++){
              try {
                procedureArray[k] = recipeProcedure.getElementsByTag("dl").first();
                recipeProcedure.getElementsByTag("dl").first().remove();
              } catch (NullPointerException exception){
              }
            }            
        } else {
            for (int k = 0; k < procedureArray.length; k++){
              try {
                procedureArray[k] = recipeProcedure.getElementsByTag("li").first();
                recipeProcedure.getElementsByTag("li").first().remove();
              } catch (NullPointerException exception){
              }
            }
        }

        if (url.contains("bbcgoodfood")){
            for (Element procedureArray1 : procedureArray) {
                String step = "" + procedureArray1;
                if (!"null".equals(step)) {
                    procedure += procedureArray1.text() + "\n";
                  }
            }
        } else {
            for (Element procedureArray1 : procedureArray) {
                String step = "" + procedureArray1;
                if (!"null".equals(step)) {
                    procedure += stepNum + ". " + procedureArray1.text() + "\n";
                    stepNum++;
                }
            }
        }

        procedure = procedure.substring(procedure.indexOf("null")+4, procedure.length());
    }
    
    //These methods allow the user to access and edit specific information within the recipe    
    public String getTitle(){
        return title;
    }   
    
    public void setTitle(String t){
        title = t;
    }
    
    public String getFileTitle(){
        return fileTitle; 
    }
    
    public String getNotes(){
        return notes;
    }
    
    public void setNotes(String n){
        notes = n;
    }
    
    public String getIngredients(){
        return ingredients;
    }
    
    public void setIngredients(String i){
        ingredients = i;
    }
    
    public String getProcedure(){
        return procedure;
    }
    
    public void setProcedure(String p){
        procedure = p;
    }
    
    @Override
    public String toString(){ //Gives recipe
        if (isRecipeAvailable(url)){ //Checks if the recipe exists before returning the recipe information
            return recipe;
        } else {
            return "No recipe found.";
        }        
    }
    
    //Returns the recipe String for a custom recipe.
    public String customRecipeToString(){
        return recipe;
    }
    
    //Sorts custom recipe ingredients based on semi colons. For every instance of a semi colon, the recipe puts the following text onto a new line. 
    private static void sortCustomIngredients(){
        String tempString = "";
        for (int k = 1; k < ingredients.length(); k++){
            if (ingredients.contains(";")){
                try{
                    tempString += "\n" + ingredients.substring(0, ingredients.indexOf(";"));
                    ingredients = ingredients.substring(ingredients.indexOf(";")+ 2, ingredients.length());
                }catch (StringIndexOutOfBoundsException e){
                    ingredients = ingredients.substring(0, ingredients.indexOf(";"));
                }
            }
        } ingredients = tempString + "\n" + ingredients;
    }
    
    //Similar to the sortCustomIngredients method, each instance of a semi colon crates a new line. 
    private static void sortCustomProcedure(){
        int num = 1;
        String tempString = "";
        
        for (int k = 1; k < procedure.length(); k++){
            if (procedure.contains(";")){
                try {
                    tempString += "\n" + k + ". " + procedure.substring(0, procedure.indexOf(";"));
                    procedure = procedure.substring(procedure.indexOf(";")+ 2, procedure.length());
                    num++;
                } catch (StringIndexOutOfBoundsException exp){
                    procedure = procedure.substring(0, procedure.indexOf(";"));
                }  
            }
        } procedure = tempString + "\n" + num + ". " + procedure;
        
    }
    
} //Closes Recipe class