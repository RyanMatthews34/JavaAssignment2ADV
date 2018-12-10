import javafx.scene.image.Image;

import java.util.*;

public class Inventory {
    // return a list with ALL products
    //System.out.println(treeMap);

    // Print All Category/Console Names
    //System.out.println(treeMap.keySet());

    // Print Xbox Category/Console
    //System.out.println(treeMap.get("Xbox"));

    // Print PlayStation Category/Console
    //System.out.println(treeMap.get("PlayStation"));

    // Print PC Category/Console
    //System.out.println(treeMap.get("PC"));

    // Print GameCube Category/Console
    //System.out.println(treeMap.get("GameCube"));

    //Creating custom sorting method
    //Collections.sort(linkGameCube, (a,b) ->  (a.getPrice()>b.getPrice())?1:-1);

    //System.out.println("\nAfter sorting with the custom lambda on price");
    //for (Product product:linkGameCube)
    //System.out.println(product);

    public TreeMap<String, LinkedList<Product>> treeMap;

    public Inventory() {
        treeMap = new TreeMap<>();
    }

    /**
     * I will call to this function to get my Hard coded Products
     * and than create my own object of them in my controller
     * class, allowing me to manipulate them
     */
    public List<Product> myProducts() {
        Product product1 = new Product("Counter Strike", "Xbox", "Hello World", 11.50, 9, 5, new Image("Pictures/CounterStrike.jpg"));
        Product product2 = new Product("Dota 2", "Xbox", "Hello World", 15.00, 9, 8, new Image("Pictures/Dota2.jpg"));
        Product product3 = new Product("Fortnite", "Xbox", "Hello World", 20.50, 9, 12, new Image("Pictures/Fortnite.jpg"));
        Product product4 = new Product("Red Dead Redemption", "Xbox", "Hello World", 20, 9, 2, new Image("Pictures/RedDeadRedemption.jpg"));

        Product product5 = new Product("Fallout", "PlayStation", "Hello World", 12.00, 9, 4, new Image("Pictures/Fallout.jpg"));
        Product product6 = new Product("Portal", "PlayStation", "Hello World", 34.00, 9, 2, new Image("Pictures/Portal.jpg"));
        Product product7 = new Product("Super Smash", "PlayStation", "Hello World", 12.50, 9, 18, new Image("Pictures/SuperSmash.jpg"));
        Product product8 = new Product("Minecraft", "PlayStation", "Hello World", 44.00, 9, 12, new Image("Pictures/Minecraft.jpg"));

        Product product9 = new Product("BattleField", "PC", "Hello World", 10.00, 9, 10, new Image("Pictures/Battlefield.jpg"));
        Product product10 = new Product("Doom", "PC", "Hello World", 100.00, 9, 6, new Image("Pictures/Doom.jpg"));
        Product product11 = new Product("Mario Kart", "PC", "Hello World", 99.00, 9, 9, new Image("Pictures/MarioKart.jpg"));
        Product product12 = new Product("Elder Scrolls", "PC", "Hello World", 23.50, 9, 3, new Image("Pictures/ElderScrolls.jpg"));

        Product product13 = new Product("World of Warcraft", "GameCube", "Hello World", 19, 9, 18, new Image("Pictures/WorldOfWarcraft.jpg"));
        Product product14 = new Product("grand Theft Auto", "GameCube", "Hello World", 25.00, 9, 12, new Image("Pictures/GrandTheftAuto.jpg"));
        Product product15 = new Product("Zelda", "GameCube", "Hello World", 199, 9, 3, new Image("Pictures/Zelda.jpg"));
        Product product16 = new Product("God of War", "GameCube", "Hello World", 29, 9, 4, new Image("Pictures/GodOfWar.jpg"));

        LinkedList<Product> linkXbox = new LinkedList<Product>();
        linkXbox.add(product1);
        linkXbox.add(product2);
        linkXbox.add(product3);
        linkXbox.add(product4);
        // System.out.println(linkXbox);

        LinkedList<Product> linkPlayStation = new LinkedList<Product>();
        linkPlayStation.add(product5);
        linkPlayStation.add(product6);
        linkPlayStation.add(product7);
        linkPlayStation.add(product8);
        // System.out.println(linkPlayStation);

        LinkedList<Product> linkPC = new LinkedList<Product>();
        linkPC.add(product9);
        linkPC.add(product10);
        linkPC.add(product11);
        linkPC.add(product12);
        // System.out.println(linkPC);

        LinkedList<Product> linkGameCube = new LinkedList<Product>();
        linkGameCube.add(product13);
        linkGameCube.add(product14);
        linkGameCube.add(product15);
        linkGameCube.add(product16);
        // System.out.println(linkGameCube);

        treeMap.put("Xbox", linkXbox);
        treeMap.put("PlayStation", linkPlayStation);
        treeMap.put("PC", linkPC);
        treeMap.put("GameCube", linkGameCube);

        List<Product> getProduct = new ArrayList<>();
        getProduct.addAll(treeMap.get("Xbox"));
        getProduct.addAll(treeMap.get("PlayStation"));
        getProduct.addAll(treeMap.get("PC"));
        getProduct.addAll(treeMap.get("GameCube"));
        return getProduct;
    }

    public LinkedList<Product> getListProducts() {
        LinkedList<Product> linkedList = new LinkedList<>();
        for (String key : treeMap.keySet()) {
            linkedList.addAll(treeMap.get(key));
        }
        return linkedList;
    }

    /**
     * This Method returns all the Keys/Consoles/Category's
     * within the treeMap
     */
    public Set<String> getConsoleNames() {
        Set<String> consoleNames = treeMap.keySet();
        return consoleNames;
    }

    /**
     * This method receives a key/console/category and returns all
     * the products in the treeMap with that Key/console/category
     */
    public List<Product> getSelectedKey(String selectedKey) {
        List<Product> key = treeMap.get(selectedKey);
        return key;
    }


    /**
     * This Method will receive the selected product in the listView
     * and take the price and multiple by the number of Units
     * and return the sum
     */
    public Double getInventoryValue(List<Product> list) {

        return list.stream()
                .mapToDouble(product -> product.getPrice() * product.getUnits()).sum();
    }

    /**
     * This method will receive the selected console/category
     * and take all the products looping through there price * units
     * and add all the sums of each product together for category value
     */
    public Double getCategoryValue(List<Product> list) {

        return list.stream()
                .mapToDouble(product -> product.getPrice() * product.getUnits()).sum();
    }

    /**
     * This Method will allow me to take the current number
     * of units for a selected product and on press delete by 1
     */
    public void sellProduct(Product product) {

        int currentUnits = product.getUnits();

        if (currentUnits > 0) {
            currentUnits--;
            product.setUnits(currentUnits);
        }
    }

    /**
     * Adding a product if console doesn't already
     * exist and if it does add to correcting link list
     */
    public void addConsole(String console, Product product) {
        if (!treeMap.containsKey(console))
            treeMap.get(console).add(product);
        else {
            LinkedList<Product> newConsole = new LinkedList<Product>();
            newConsole.add(product);
            treeMap.put(console, newConsole);
        }
    }
}