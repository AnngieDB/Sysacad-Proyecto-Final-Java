package Control;

import Control.InicioSesion.inicioSesionData;
import Excepciones.DatosIncorrectosException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import static Path.Path.*;

public class cambiarContraControl {

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnConfirmarCambio;

    @FXML
    private PasswordField PfContraActual;

    @FXML
    private PasswordField PfNuevaContra;

    @FXML
    private Label tctMenuPrincipal;

    @FXML
    private PasswordField PfVerificarNuevaContra;

    private Stage stage;

    private String contraActual;

    private String nuevaContra;

    private String verificarNuevaContraseña;

    /**
     * Metodo que se ejecuta al clickear el boton volver
     * @param event
     */
    @FXML
    void clickBtnVolver(ActionEvent event) {

        stage = (Stage) btnVolver.getScene().getWindow();
        EscenaControl escena = new EscenaControl();
        escena.cambiarEscena(menuPrincipalAlumnos, stage, "Menu Principal");

    }

    /**
     * Metodo que se encarga de confirmar el cambio de contraseña
     * @param event
     */
    @FXML
    void confirmarCambio(ActionEvent event) throws DatosIncorrectosException {

        contraActual = PfContraActual.getText();
        nuevaContra = PfNuevaContra.getText();
        verificarNuevaContraseña = PfVerificarNuevaContra.getText();

        if(contraActual == "" || nuevaContra == "" || verificarNuevaContraseña == "") {

            throw new DatosIncorrectosException("Complete todos los campos");

        }else if(contraActual.equals(nuevaContra) || contraActual.equals(verificarNuevaContraseña)) {

            throw new DatosIncorrectosException("La nueva contraseña no puede ser igual a la actual");

        }else if(!nuevaContra.equals(verificarNuevaContraseña)) {

            throw new DatosIncorrectosException("Las contraseñas no coinciden");

        }else{

            if(inicioSesionData.getLegajo().charAt(0) == 'E') {

                Consultas.consultaArchivo.cambiarContrasenia(fileNameAlumnos, inicioSesionData.getLegajo(), nuevaContra);

            } else if (inicioSesionData.getLegajo().charAt(0) == 'P') {

                Consultas.consultaArchivo.cambiarContrasenia(fileNameProfesores, inicioSesionData.getLegajo(), nuevaContra);

            } else if (inicioSesionData.getLegajo().charAt(0) == 'A') {

                Consultas.consultaArchivo.cambiarContrasenia(fileNameAdministrador, inicioSesionData.getLegajo(), nuevaContra);

            }

        }

    }



}