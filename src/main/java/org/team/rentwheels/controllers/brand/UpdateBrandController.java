package org.team.rentwheels.controllers.brand;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.team.rentwheels.models.Brand;
import org.team.rentwheels.services.BrandService;
import org.team.rentwheels.utils.StageManager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateBrandController implements Initializable {
    private BrandService brandService;

    private BrandsController brandsController;

    private FileChooser fileChooser;
    private File file;
    private byte[] imageByte;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView ivCarImage;

    @FXML
    private Label lblSelectImage;

    @FXML
    private TextField txtfCountry;

    @FXML
    private TextField txtfFoundationYear;

    @FXML
    private TextField txtfName;

    private Brand selectedBrand; // This will store the brand to be updated

    public UpdateBrandController() {
        this.brandsController = new BrandsController();
        this.brandService=new BrandService();
    }

    public UpdateBrandController(BrandService brandService, BrandsController brandsController, Brand selectedBrand) {
        this.brandService = brandService;
        this.brandsController = brandsController;
        this.selectedBrand = selectedBrand;
    }
    public void initData(BrandService brandService, BrandsController brandsController, Brand selectedBrand) {
        this.brandService = brandService;
        this.brandsController = brandsController;
        this.selectedBrand = selectedBrand;

        initializeData();
    }

    private void initializeData() {
        selectedBrand=brandsController.getSelectedBrand();
//        System.out.println(selectedBrand.toString());
        if (selectedBrand != null) {
            // Set existing data to fields for editing
            txtfName.setText(selectedBrand.getBrandName());
            txtfCountry.setText(selectedBrand.getCountyOfMake());
            txtfFoundationYear.setText(String.valueOf(selectedBrand.getFoundationYear()));
            // Load existing image for the brand
            imageByte = selectedBrand.getImage(); // Assuming getImage returns the byte array
            if (imageByte != null) {
                try {
                    // Load image into ImageView
                    Image image = new Image(new ByteArrayInputStream(imageByte));
                    ivCarImage.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exception appropriately
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnEventSave(ActionEvent event) {
        try {
            // Update brand with the edited information
            selectedBrand.setBrandName(txtfName.getText());
            selectedBrand.setCountyOfMake(txtfCountry.getText());
            selectedBrand.setFoundationYear(Integer.parseInt(txtfFoundationYear.getText()));
            selectedBrand.setImage(imageByte); // Use the updated image byte array
            lblSelectImage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("ImageFile", "*.png", "*.jpg", "*.jpeg")
                );
                file = fileChooser.showOpenDialog(null);
                if(file !=null){
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        imageByte = new byte[fileInputStream.available()];
                        fileInputStream.read(imageByte);
                        Image image = new Image(file.toURI().toString());
                        ivCarImage.setImage(image);
                        fileInputStream.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            });
            brandService.updateBrand(selectedBrand.getId(), selectedBrand);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Brand Saved Successfully");
            alert.setContentText("The brand has been saved successfully.");

            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional <?> ) result).isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                Stage stage1 = (Stage) btnSave.getScene().getWindow();
                stage1.close();
                StageManager.replace("fxml/Brand/brands.fxml", true, 1200, 700);

            }
        } catch (Exception e) {
            // Handle any exceptions or errors that might occur during the saving process
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Brand Save Error");
            alert.setContentText("An error occurred while saving the brand.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnEventCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
