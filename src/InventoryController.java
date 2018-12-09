import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.*;


public class InventoryController implements Initializable {

    Inventory inventoryOBJ = new Inventory();

    /*
    Initializing Variables for SceneBuilder
    */
    @FXML
    private ComboBox<String> consoleComboBox;
    @FXML
    private Label InventoryValueLabel;
    @FXML
    private Label CategoryValueLabel;
    @FXML
    private Label sortingLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView listView;
    @FXML
    private RadioButton PriceHighToLowRadioButton;
    @FXML
    private RadioButton PriceLowToHighRadioButton;
    @FXML
    private RadioButton TitleAToZRadioButton;
    @FXML
    private RadioButton TitleZToARadioButton;

    private ToggleGroup SelectedRadioButtonToggleGroup;

    /*
    This function is an event attached to each radio button
    Changing the label above to tell the user what
    button is currently selected
     */
    public void radioButtonChanged() {
        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.PriceHighToLowRadioButton))
            sortingLabel.setText("Sorting From Price High To Low");
        

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.PriceLowToHighRadioButton))
            sortingLabel.setText("Sorting From Price Low To High");

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.TitleAToZRadioButton))
            sortingLabel.setText("Sorting By Title A To Z");

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.TitleZToARadioButton))
            sortingLabel.setText("Sorting By Title Z To A");
    }


    /*
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sortTitleAToZ(inventoryOBJ.myProducts());

        //Prompt Text on Combo Box
        consoleComboBox.setPromptText("Select a Console...");

        //Start with all items in ListView
        listView.getItems().addAll(inventoryOBJ.myProducts());

        //Select the first product in the list
        listView.getSelectionModel().select(0);
        imageView.setImage(new Image("Pictures/CounterStrike.jpg"));
        //Add the Key to ComboBox
        consoleComboBox.getItems().addAll(inventoryOBJ.getConsoleNames());

        //When new category is selected run the function updateListView()
        consoleComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateListView());

        //Make it so only 1 radio button can be selected at a time
        SelectedRadioButtonToggleGroup = new ToggleGroup();
        this.PriceHighToLowRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.PriceLowToHighRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.TitleAToZRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.TitleZToARadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);

        TitleAToZRadioButton.setSelected(true);

        //This runs every time a new product is selected.
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product SelectedValue) {
                //Change images based on product selected
                imageView.setImage(SelectedValue.getImage());
                System.out.println(SelectedValue);
                double productAmount = inventoryOBJ.getInventoryValue(listView.getSelectionModel().getSelectedItems());
                InventoryValueLabel.setText(Double.toString(productAmount));
            }
        });
    }

    /*
    On the first selection to the ComboBox it doesn't update
    but the second selection it does unsure why
    */
    public void updateListView() {
        consoleComboBox.setOnAction((event) -> {
            consoleComboBox.getSelectionModel().getSelectedItem();

            if (consoleComboBox.getSelectionModel().getSelectedItem().equals("Xbox")) {
                //Clears Previous Items in ListView
                listView.getItems().clear();
                //Adds Items to ListView with specific key
                listView.getItems().addAll(inventoryOBJ.getSelectedKey("Xbox"));
                //Sets Image related to selected Category
                //imageView.setImage(new Image("Pictures/Xbox.jpg"));
                //Select the first product in the list
                listView.getSelectionModel().select(0);
                //setting the label to add the category inventory together
                double categoryAmount = inventoryOBJ.getCategoryValue(listView.getItems());
                CategoryValueLabel.setText(Double.toString(categoryAmount));
            } else if (consoleComboBox.getSelectionModel().getSelectedItem().equals("PlayStation")) {
                //Clears Previous Items in ListView
                listView.getItems().clear();
                //Adds Items to ListView with specific key
                listView.getItems().addAll(inventoryOBJ.getSelectedKey("PlayStation"));
                //imageView.setImage(new Image("Pictures/PlayStation.jpg"));
                //Select the first product in the list
                listView.getSelectionModel().select(0);
                //setting the label to add the category inventory together
                double categoryAmount = inventoryOBJ.getCategoryValue(listView.getItems());
                CategoryValueLabel.setText(Double.toString(categoryAmount));
            } else if (consoleComboBox.getSelectionModel().getSelectedItem().equals("PC")) {
                //Clears Previous Items in ListView
                listView.getItems().clear();
                //Adds Items to ListView with specific key
                listView.getItems().addAll(inventoryOBJ.getSelectedKey("PC"));
                //imageView.setImage(new Image("Pictures/PC.jpg"));
                //Select the first product in the list
                listView.getSelectionModel().select(0);
                //setting the label to add the category inventory together
                double categoryAmount = inventoryOBJ.getCategoryValue(listView.getItems());
                CategoryValueLabel.setText(Double.toString(categoryAmount));
            } else if (consoleComboBox.getSelectionModel().getSelectedItem().equals("GameCube")) {
                //Clears Previous Items in ListView
                listView.getItems().clear();
                //Adds Items to ListView with specific key
                listView.getItems().addAll(inventoryOBJ.getSelectedKey("GameCube"));
                //imageView.setImage(new Image("Pictures/GameCube.jpg"));
                //Select the first product in the list
                listView.getSelectionModel().select(0);
                //setting the label to add the category inventory together
                double categoryAmount = inventoryOBJ.getCategoryValue(listView.getItems());
                CategoryValueLabel.setText(Double.toString(categoryAmount));
            } else {
                //Clears Previous Items in ListView
                listView.getItems().clear();
                //Set Prompt Text
                consoleComboBox.setPromptText("Select a Console...");
                //Add the Key to ComboBox
                consoleComboBox.getItems().addAll(inventoryOBJ.treeMap.keySet());
            }
        });
    }


    public List<Product> sortTitleAToZ(List<Product> products) {
        products.stream()
                .sorted((i1, i2) -> i1.compareTo(i2))
                .forEach(product -> System.out.println(product));
        return products;
    }

    public List<Product> sortTitleZToA(List<Product> products) {
        products.stream()
                .sorted((i1, i2) -> i2.compareTo(i1))
                .forEach(System.out::println);
        return products;
    }

    public List<Product> sortPriceLowTToHigh(List<Product> products) {
        products.stream().sorted(Comparator.comparing(Product::getPrice))
                .forEach(product -> System.out.println(product));
        return products;
    }

    public List<Product> sortPriceHighToLow(List<Product> products) {
        products.stream().sorted(Comparator.comparing(Product::getPrice).reversed())
                .forEach(product -> System.out.println(product));
        return products;
    }

    /*
    This will on press call my function sellUnits
    from my inventory class passing the selected
    model get the current Units and subtract 1 from it
    */
    public void sellUnitButtonPushed(ActionEvent event) {
        //inventoryOBJ.sellProduct(listView.getSelectionModel().getSelectedItems());
        //inventoryOBJ.sellProduct(listView.getSelectionModel().getSelectedItem().toString());
        System.out.println("Button has been pressed");
    }
}