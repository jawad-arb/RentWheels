package org.team.rentwheels.controllers.brand;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.team.rentwheels.RentWheels;
import org.team.rentwheels.models.Brand;
import org.team.rentwheels.services.BrandService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BrandsController implements Initializable {

    private final BrandService brandService;

    @FXML
    private ImageView add_brand;

    @FXML
    private ImageView add_brand1;

    @FXML
    private TableColumn<Brand, Integer> clId;

    @FXML
    private TableColumn<Brand, String> clCountry;

    @FXML
    private TableColumn<Brand, Integer> clFoundationYear;

    @FXML
    private TableColumn<Brand, byte[]> clImage;

    @FXML
    private TableColumn<Brand, String> clName;

    @FXML
    private ImageView delete_brand;

    @FXML
    private VBox refrech_VB;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Brand> tableView;

    @FXML
    private ImageView update_brand;

    public BrandsController() {
        this.brandService = new BrandService();
    }
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        clCountry.setCellValueFactory(new PropertyValueFactory<>("countyOfMake"));
        clFoundationYear.setCellValueFactory(new PropertyValueFactory<>("foundationYear"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
            loadBrandData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBrandData() throws SQLException {
        List<Brand> brandList= brandService.getAllBrands();
        tableView.getItems().addAll(brandList);

    }

    @FXML
    void addEvent(MouseEvent event) {
        openAddBrandWindow();
    }



    @FXML
    void deleteEvent(MouseEvent event) {
        Brand selectedBrand = tableView.getSelectionModel().getSelectedItem();
        if (selectedBrand != null) {
            // Show confirmation dialog before deletion
            boolean confirmed = showConfirmationDialog(selectedBrand);
            if (confirmed) {
                try {
                    brandService.deleteBrand(selectedBrand.getId());
                    tableView.getItems().remove(selectedBrand);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void refrechEvent(MouseEvent event) {

    }

    @FXML
    void updateEvent(MouseEvent event) {
        openUpdateBrandWindow();
    }

    private void openAddBrandWindow() {
        FXMLLoader fxmlLoader=new FXMLLoader(RentWheels.class.getResource("fxml/Brand/addBrand.fxml"));
        try {
            // Load the FXML and get the root node (the layout defined in the FXML)
            Parent root = fxmlLoader.load();

            // Get the controller associated with the FXML

            // Create a new stage and set the scene to display the Add Brand view
            Stage addBrandStage = new Stage();
            Scene scene = new Scene(root);
            addBrandStage.setScene(scene);
            addBrandStage.setTitle("Add Brand");

            // Show the new stage
            addBrandStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateBrandWindow() {
        Brand selectedBrand = getSelectedBrand(); // Retrieve the selected brand from the table

        if (selectedBrand != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(RentWheels.class.getResource("fxml/Brand/updateBrand.fxml"));
            try {
                Parent root = fxmlLoader.load();
                UpdateBrandController updateBrandController = fxmlLoader.getController();

                // Pass the selected brand to the UpdateBrandController
                updateBrandController.initData(brandService, this, selectedBrand);

                Stage updateBrandStage = new Stage();
                Scene scene = new Scene(root);
                updateBrandStage.setScene(scene);
                updateBrandStage.setTitle("Update Brand");
                updateBrandStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean showConfirmationDialog(Brand brand) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Deleting Brand: " + brand.getBrandName());
        alert.setContentText("Are you sure you want to delete this brand?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Show and wait for user action
        boolean[] result = {false}; // To store the result

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                // User confirmed deletion
                result[0] = true;
            } else {
                // User cancelled the deletion
                result[0] = false;
            }
        });

        return result[0];
    }

    public Brand getSelectedBrand() {
        if (tableView != null && tableView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("test");
            return tableView.getSelectionModel().getSelectedItem();
        } else {
            // Provide feedback or handle the case where no item is selected
            System.out.println("No brand selected");
            return null;
        }
    }




}
