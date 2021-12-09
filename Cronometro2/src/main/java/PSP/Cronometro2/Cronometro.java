package PSP.Cronometro2;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;

public class Cronometro implements Runnable {

	private IntegerProperty segundos;
	private IntegerProperty minutos;
	private IntegerProperty horas;
	solicitarSuspender suspendido = new solicitarSuspender();

	public Cronometro() {
		super();
		this.segundos = new SimpleIntegerProperty(0);
		this.minutos = new SimpleIntegerProperty(0);
		this.horas = new SimpleIntegerProperty(0);
		this.suspendido.setSuspendido(false);
	}

	public solicitarSuspender getSuspendido() {
		return suspendido;
	}

	public void setSuspendido(solicitarSuspender suspendido) {
		this.suspendido = suspendido;
	}

	public IntegerProperty getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos.set(segundos);
	}

	public IntegerProperty getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos.set(minutos);
	}

	public IntegerProperty getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas.set(horas);
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {

				for (int i = 00, e = 00, h = 00; true; i++) {
					int contador = i;
					Thread.sleep(1000);
					this.suspendido.waitReanudar();
					Platform.runLater(() -> {
						segundos.set(contador);
					});
					if (i == 60) {
						i = 00;
						e++;
					}
					int contador2 = e;
					Platform.runLater(() -> {
						minutos.set(contador2);
					});
					if (e == 60) {
						e = 00;
						h++;
					}
					int contador3 = h;
					Platform.runLater(() -> {
						horas.set(contador3);
					});
					if (e == 60) {
						e = 00;
						i = 00;
					}

				}

			}
		} catch (Exception errr) {
			segundos.set(0);
			minutos.set(0);
			horas.set(0);

		}
	}
}
