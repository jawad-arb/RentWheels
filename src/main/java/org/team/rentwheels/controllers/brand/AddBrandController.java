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
import org.team.rentwheels.services.BrandService;
import org.team.rentwheels.utils.StageManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddBrandController implements Initializable {

    private final BrandService brandService;
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

    public AddBrandController() {
        this.brandService = new BrandService();
    }

    public AddBrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    }
    @FXML
    void btnEventCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnEventSave(ActionEvent event) throws SQLException, IOException {
        try {
            brandService.addBrand(txtfName.getText(),
                    txtfCountry.getText(),
                    Integer.parseInt(txtfFoundationYear.getText()),
                    imageByte);
            // Brand saved successfully, show a success dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Brand Saved Successfully");
            alert.setContentText("The brand has been saved successfully.");

            Optional < ButtonType > result = alert.showAndWait();
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
        }

    }

}