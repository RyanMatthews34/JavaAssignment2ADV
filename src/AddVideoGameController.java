import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AddVideoGameController implements Initializable {

    /**
     * adding property from inventory class to
     * go through the "addProduct" function
     */
    Inventory inventoryOBJ = new Inventory();

    public TreeMap<String, LinkedList<Product>> treeMap;

    public AddVideoGameController() {
        treeMap = new TreeMap<>();
    }

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField consoleTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField ratingTextField;
    @FXML
    private TextField unitsTextField;
    @FXML
    private Label errMsgLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * On press it will try to create a new Product object
     * if successful will print to screen if not the error will
     * be catch due to validation.
     */
    public void newVideoGameButtonPushed() {
        try {
            //Creating new Product Object
            Product newProduct = new Product(
                    titleTextField.getText(),
                    consoleTextField.getText(),
                    descriptionTextField.getText(),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(ratingTextField.getText()),
                    Integer.parseInt(unitsTextField.getText()));

            this.errMsgLabel.setText("");
            //If successful will print to screen.
            System.out.printf("Recently added Product: %s %n", newProduct);

            LinkedList<Product> testProduct = new LinkedList<Product>();
            testProduct.add(newProduct);
            inventoryOBJ.treeMap.put(consoleTextField.getText(), testProduct);

//            if (!treeMap.containsKey(console))
//                treeMap.get(console).add(product);
//            else {
//                LinkedList<Product> newConsole = new LinkedList<Product>();
//                newConsole.add(product);
//                treeMap.put(console, newConsole)
            //will catch an invaild input and print to screen
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            this.errMsgLabel.setText(e.getMessage());
        }
    }

    /**
     * On press will take user back to VideoGame Store
     */
    public void backToInventoryOnButtonPress(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public List<Product> myNewlyAddedProducts() {
        Product product111 = new Product("AAAAAA Strike", "Xbox", "Hello World", 11.99, 9, 5, new Image("Pictures/CounterStrike.jpg"));
        LinkedList<Product> linkNewProduct = new LinkedList<Product>();
        linkNewProduct.add(product111);

        treeMap.put("Xbox", linkNewProduct);

        List<Product> getNewProduct = new ArrayList<>();
        getNewProduct.addAll(treeMap.get("Xbox"));

        return getNewProduct;
    }
}

