package PSP.Cronometro2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

	@FXML
	Button Inicio;
	@FXML
	Label horas;
	@FXML
	Label minutos;
	@FXML
	Label segundos;
	@FXML
	Button Parar;
	@FXML
	Button Reiniciar;

	boolean result=false;
	Cronometro c = new Cronometro();
	Thread t = new Thread(c);
	@FXML
	protected void initialize() {
		Inicio.setOnAction(event -> {
			if(result==false) {
				t.start();		
				result=true;
				}
			
			c.getSuspendido().setSuspendido(false);
				
			Inicio.setDisable(true);
			horas.textProperty().bind(c.getHoras().asString());
			minutos.textProperty().bind(c.getMinutos().asString());
			segundos.textProperty().bind(c.getSegundos().asString());
			
			
		});

		Parar.setOnAction(event -> {

			if (c.suspendido.getSuspendido() == false) {
				c.getSuspendido().setSuspendido(true);
				Parar.setText("Reanudar");

			} else {
				c.getSuspendido().setSuspendido(false);
				Parar.setText("Parar");
			}

		});
		
		Reiniciar.setOnAction(event->{		
			c.getSuspendido().setSuspendido(true);
			t.interrupt();
			Inicio.setDisable(false);
			horas.textProperty().unbind();
			minutos.textProperty().unbind();
			segundos.textProperty().unbind();
			horas.setText("00");
			minutos.setText("00");
			segundos.setText("00");
			
		});

	}

}