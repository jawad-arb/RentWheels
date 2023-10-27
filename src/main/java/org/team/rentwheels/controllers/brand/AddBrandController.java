package org.team.rentwheels.controllers.brand;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.team.rentwheels.services.BrandService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                imageByte = new byte[fileInputStream.available()];
                fileInputStream.read(imageByte);
                Image image = new Image(file.toURI().toString());
                ivCarImage.setImage(image);
                fileInputStream.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
        brandService.addBrand(txtfName.getText(),
                                txtfCountry.getText(),
                                Integer.parseInt(txtfFoundationYear.getText())
        ,                       imageByte);
    }

}