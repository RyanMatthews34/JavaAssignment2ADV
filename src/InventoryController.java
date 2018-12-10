import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class InventoryController implements Initializable {

    Inventory inventoryOBJ = new Inventory();
    AddVideoGameController addedVideoGames = new AddVideoGameController();
    /**
     * Initializing Variables for SceneBuilder
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
    @FXML
    private Button sellUnitButton;

    private ToggleGroup SelectedRadioButtonToggleGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Prompt Text on Combo Box
        consoleComboBox.setPromptText("Select a Console...");

        //Start with all items in ListView
        listView.getItems().addAll(inventoryOBJ.myProducts());
        //listView.getItems().addAll(addedVideoGames.myNewlyAddedProducts());

        //Select the first product in the list
        listView.getSelectionModel().select(0);
        imageView.setImage(new Image("Pictures/CounterStrike.jpg"));

        //Add the Key/Consoles to ComboBox
        consoleComboBox.getItems().addAll(inventoryOBJ.getConsoleNames());

        //When new category/console is selected run the function updateListView()
        consoleComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateListView());

        //Make it so only 1 radio button can be selected at a time
        SelectedRadioButtonToggleGroup = new ToggleGroup();
        this.PriceHighToLowRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.PriceLowToHighRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.TitleAToZRadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);
        this.TitleZToARadioButton.setToggleGroup(SelectedRadioButtonToggleGroup);

        //Make it so Radio button A to Z is selected at the start sorting the list view.
        TitleAToZRadioButton.setSelected(true);
        radioButtonChanged();

        /**
         This will on press call my function sellUnits
         from my inventory class passing the selected
         model get the current Units and subtract 1 from it
         */
        sellUnitButton.setOnAction(event -> {
            inventoryOBJ.sellProduct((Product) listView.getSelectionModel().getSelectedItem());
            // Setting the category label after selling 1 unit
            CategoryValueLabel.setText(Double.toString(inventoryOBJ.getCategoryValue(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem()))));
            //If the combo box has something selected than it gets everything in the listview and sends it to getInventoryValue
            InventoryValueLabel.setText(Double.toString(inventoryOBJ.getInventoryValue(listView.getSelectionModel().getSelectedItems())));
            //Update List view for sold unit
            listView.refresh();
        });

        /**This runs every time a new product is selected.
         It get the image attached to the selected product
         and changed the Inventory label to the selected
         product
         */
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product SelectedValue) {
                //Updates the image whenever there is a selected product (always something selected)
                if (SelectedValue != null) {
                    imageView.setImage(SelectedValue.getImage());
                }
                //Takes product selected and send it to getInventoryValue returning the label value
                InventoryValueLabel.setText(Double.toString(inventoryOBJ.getInventoryValue(listView.getSelectionModel().getSelectedItems())));
            }
        });
    }

    /**
     * This function is an event attached to each radio button
     * Changing the label above to tell the user what
     * button is currently selected and finding the current selected
     * category and sorting all items inside of it
     */
    public void radioButtonChanged() {

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.TitleAToZRadioButton)) {
            sortingLabel.setText("Sorting By Title A To Z");
            {
                //removes all items in the Listview before updating
                listView.getItems().clear();

                if (consoleComboBox.getSelectionModel().getSelectedItem() != null) {
                    listView.getItems().addAll(sortTitleAToZ(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem())));
                } else {
                    listView.getItems().addAll(sortTitleAToZ(inventoryOBJ.getListProducts()));
                }
            }
        }

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.TitleZToARadioButton)) {
            sortingLabel.setText("Sorting By Title Z To A");
            {
                listView.getItems().clear();

                if (consoleComboBox.getSelectionModel().getSelectedItem() != null) {
                    listView.getItems().addAll(sortTitleZToA(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem())));
                } else {
                    listView.getItems().addAll(sortTitleZToA(inventoryOBJ.getListProducts()));
                }
            }
        }

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.PriceLowToHighRadioButton)) {
            sortingLabel.setText("Sorting By Title Z To A");
            {
                listView.getItems().clear();

                if (consoleComboBox.getSelectionModel().getSelectedItem() != null) {
                    listView.getItems().addAll(sortPriceLowToHigh(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem())));
                } else {
                    listView.getItems().addAll(sortPriceLowToHigh(inventoryOBJ.getListProducts()));
                }
            }
        }

        if (this.SelectedRadioButtonToggleGroup.getSelectedToggle().equals(this.PriceHighToLowRadioButton)) {
            sortingLabel.setText("Sorting By Title Z To A");
            {
                listView.getItems().clear();

                if (consoleComboBox.getSelectionModel().getSelectedItem() != null) {
                    listView.getItems().addAll(sortPriceHighToLow(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem())));
                } else {
                    listView.getItems().addAll(sortPriceHighToLow(inventoryOBJ.getListProducts()));
                }
            }
        }
        //After sorting the list select first Item.
        listView.getSelectionModel().select(0);
    }


    /**
     * When Combo box is selected this method run
     * checking for the selected Radio button
     * Selecting the first product in the view
     * and set the category total value label
     */
    public void updateListView() {
        consoleComboBox.setOnAction((event) -> {
            consoleComboBox.getSelectionModel().getSelectedItem();
            // listView.getItems().clear();
            radioButtonChanged();
            listView.getSelectionModel().select(0);
            //InventoryValueLabel.setText(Double.toString(inventoryOBJ.getInventoryValue(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem().toString()))));
            CategoryValueLabel.setText(Double.toString(inventoryOBJ.getCategoryValue(inventoryOBJ.getSelectedKey(consoleComboBox.getSelectionModel().getSelectedItem()))));
        });
    }

    //Using .Stream to sort List of products in alphabetical order
    public List<Product> sortTitleAToZ(List<Product> products) {
        return products.stream()
                .sorted((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()))
                .collect(Collectors.toList());
    }

    //Using .Stream to sort list from Z to A
    public List<Product> sortTitleZToA(List<Product> products) {
        return products.stream()
                .sorted((a, b) -> b.getTitle().compareToIgnoreCase(a.getTitle()))
                .collect(Collectors.toList());
    }

    //Not using Streams but Collection to sort Product by price from low to high
    public List<Product> sortPriceLowToHigh(List<Product> products) {
        Collections.sort(products, (a, b) -> {
            if (a.getPrice() > b.getPrice())
                return 1;
            else
                return -1;
        });
        return products;
    }

    //Not using Streams but Collection to sort Product by price from low to high
    public List<Product> sortPriceHighToLow(List<Product> products) {
        Collections.sort(products, (a, b) -> {
            if (b.getPrice() > a.getPrice())
                return 1;
            else
                return -1;
        });
        return products;
    }

    //On button press change scene to create Video game view
    public void addVideoGameOnButtonPress(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddVideoGameView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}