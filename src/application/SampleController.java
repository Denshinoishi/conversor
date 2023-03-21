package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.scene.control.TextField;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.control.Tab;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.ChoiceBox;

public class SampleController {
	String resforclip1 = "";
	String resforclip2 = "";//Strings para la opción de copiar
	String iso4217 = "Nomra ISO2417\nCOP\t-\tPesos Colombianos\n" + "USD\t-\tDólares Americanos\n" + "EUR\t-\tEuros\n"
			+ "JPY\t-\tYenes Japoneses\n" + "KRW\t-\tWons Surcoreanos\n" + "GBP\t-\tLibras Esterlinas";

	// Lista de opciones para Tasas
	ObservableList<String> moneda1 = FXCollections.observableArrayList();
	ObservableList<String> moneda2 = FXCollections.observableArrayList();
	// Lista de opciones para Masas
	ObservableList<String> masas1 = FXCollections.observableArrayList();
	ObservableList<String> masas2 = FXCollections.observableArrayList();

	private String lastSelectedTasa2 = ""; // String para guardar el último valor en la lista 2 de tasas
	private String lastSelectedMasa2 = ""; // String para guardar el último valor en la lista 2 de tasas

	private Moneda moneda = new Moneda();
	private Pesos peso = new Pesos();

	
	@FXML
	private Label expIso;
	@FXML
	private TabPane tbPn;
	@FXML
	private Tab menuDivisas;
	
	@FXML
	private AnchorPane divisasTab;
	@FXML
	private ChoiceBox<String> tasaChoice1;
	@FXML
	private Button divisasBtn;
	@FXML
	private Label divisasLbl;
	@FXML
	private ChoiceBox<String> tasaChoice2;
	@FXML
	private Button divisasBtn1;
	@FXML
	private Label resultLbl;
	@FXML
	private Label resultLbl2;
	@FXML
	private TextField valorTxt;
	@FXML
	private TextField valorTxt2;
	@FXML
	private Tab menuOtros;
	@FXML
	private AnchorPane OtroPn;
	@FXML
	private ChoiceBox<String> masasChoice1;
	@FXML
	private ChoiceBox<String> masasChoice2;
	@FXML
	private Button masasBtn1;
	@FXML
	private Button masasBtn2;

	@FXML
	private Label otrosLbl;


	
	@FXML
	private void initialize() {
		
		
		

		valorTxt.setText("1"); // Valor por defecto al arrancar la aplicación
		valorTxt2.setText("1");
		expIso.setText(iso4217); // Tabla de ISO4217 con las convenciones

		// Cargamos las llaves como Strings en las listas
		moneda1.add("COP");
		moneda1.add("USD");
		moneda1.add("JPY");
		moneda1.add("EUR");
		moneda1.add("GBP");
		moneda1.add("KRW");

		moneda2.add("COP");
		moneda2.add("USD");
		moneda2.add("JPY");
		moneda2.add("EUR");
		moneda2.add("GBP");
		moneda2.add("KRW");

		masas1.add("Gramos");
		masas1.add("Onzas");
		masas1.add("Libras");
		masas1.add("Quintales");
		masas1.add("Arrobas");
		masas1.add("Toneladas");

		masas2.add("Gramos");
		masas2.add("Onzas");
		masas2.add("Libras");
		masas2.add("Quintales");
		masas2.add("Arrobas");
		masas2.add("Toneladas");

		// Cargamos las listas a las desplegables de tasas
		tasaChoice1.setItems(moneda1);
		tasaChoice2.setItems(moneda2);
		// Cargamos las listas a las desplegables de masas
		masasChoice1.setItems(masas1);
		masasChoice2.setItems(masas2);
		// Se definen la opciones de inicio
		tasaChoice1.setValue("COP");
		tasaChoice2.setValue("USD");

		masasChoice1.setValue("Gramos");
		masasChoice2.setValue("Onzas");

		lastSelectedTasa2 = tasaChoice2.getValue();
		lastSelectedMasa2 = masasChoice2.getValue();
	

		eliminaOpcionTasa2();		//Estos métodos elimina la opción selecionada en la primera lista de la segunda en el arranque
		eliminaOpcionMasa2();
		configuraListaTasas();		//Estos modifican la segunda lista si se elige la misma opción en la primera
		configuraLitaMasas();
		
		divisasBtn1.setDisable(true);
		masasBtn2.setDisable(true);

	}// Fin Método initialize


	private void eliminaOpcionTasa2() {
		Object selectedValue = tasaChoice1.getSelectionModel().getSelectedItem();
		if (selectedValue != null) {
			moneda2.setAll(moneda1);
			moneda2.remove(selectedValue);
			tasaChoice2.setItems(moneda2);
			tasaChoice2.getSelectionModel().selectFirst();
		}
	}
	
	private void eliminaOpcionMasa2() {
		Object selectedValue = masasChoice1.getSelectionModel().getSelectedItem();
		if (selectedValue != null) {
			masas2.setAll(masas1);
			masas2.remove(selectedValue);
			masasChoice2.setItems(masas2);
			masasChoice2.getSelectionModel().selectFirst();
		}
	}
	

	public void configuraListaTasas() {
		// Generar un listener para validar que la opción habilitada en lista
		// tasaChoice1 no aparesca en la
		// lista tasaChoise2
		tasaChoice1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				moneda2.setAll(moneda1);
				moneda2.remove(newValue);
				// Si se selecciona en la lista tasaChoice1 el mismo valor que se encentra en
				// tasaChoice2 validamos para que esta pase a la siguiente opción o a la primera
				// según corresponda.
				if (lastSelectedTasa2.equals(newValue)) {
					int index = moneda2.indexOf(lastSelectedTasa2);
					if (index < moneda2.size() - 1) {
						// tasaChoice2 pasa a la siguiente opción.
						tasaChoice2.getSelectionModel().select(index + 1);
					} else {
						// tasChoice2 pasa a la primera opción.
						tasaChoice2.getSelectionModel().selectFirst();
					}
				} else {
					// tasaChoice2 permanece en la última opción seleccionada.
					tasaChoice2.getSelectionModel().select(lastSelectedTasa2);
				}
			}
		});

		// Generamos un listener para guardar el último valor en la lista tasaChoise2.
		tasaChoice2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// Validamos que el valor seleccionado en tasaChoice2 no esté presente en
				// tasaChoice1

				lastSelectedTasa2 = newValue;

			}
		});

	}

	public void configuraLitaMasas() {

		// Generar un listener para validar que la opción habilitada en lista
		// tasaChoice1 no aparesca en la
		// lista tasaChoise2
		masasChoice1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				masas2.setAll(masas1);
				masas2.remove(newValue);
				// Si se selecciona en la lista tasaChoice1 el mismo valor que se encentra en
				// tasaChoice2 validamos para que esta pase a la siguiente opción o a la primera
				// según corresponda.
				if (lastSelectedMasa2.equals(newValue)) {
					int index = masas2.indexOf(lastSelectedMasa2);
					if (index < masas2.size() - 1) {
						// tasaChoice2 pasa a la siguiente opción.
						masasChoice2.getSelectionModel().select(index + 1);
					} else {
						// tasChoice2 pasa a la primera opción.
						masasChoice2.getSelectionModel().selectFirst();
					}
				} else {
					// tasaChoice2 permanece en la última opción seleccionada.
					masasChoice2.getSelectionModel().select(lastSelectedMasa2);
				}
			}
		});

		// Generamos un listener para guardar el último valor en la lista tasaChoise2.
		masasChoice2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// en la variable lastSelectedTasa2.
				lastSelectedMasa2 = newValue;
			}
		});

	}

	// Método para convertir Divisas
	public void convertirDivisas() throws Exception {
		// Tomamos el valor a convertir
		String monto = valorTxt.getText();
		// Variable para guardar en valor como double
		double mn;
		// Verificamos si el campo esta vacío
		if (monto.isEmpty()) {
			// Si esta vacío mostramos una alarma
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Advertencia");
			alert.setHeaderText("El campo de valor esta vacío");
			alert.setContentText("Por favor, ingrese un valor numérico");
			alert.showAndWait();
			return;
		}

		// Verificamos que el valor sea un número
		try {

			// Si lo es lo cargamos a la variable mn
			mn = Double.parseDouble(monto);

			// Validamos las llaves de cada lista
			String list1 = tasaChoice1.getValue();
			String list2 = tasaChoice2.getValue();
			// El resultado retorna del metodo convertir de la instancia de moneda
			double res = moneda.convertir(list1, list2, mn);
			// Creamos un formato para la presentación del resultado
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
			// Este es el resultado para copiar al porta papeles
			resforclip1 = Double.toString(res);
			// Este es el resultado para cargar al label de salida
			String resultado = decimalFormat.format(res);
			resultLbl.setText(resultado + " " + tasaChoice2.getValue());
			// Sino es un numero generamos una advertencia
			divisasBtn1.setDisable(false);
		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Advertencia");
			alert.setHeaderText("Valor no válido");
			alert.setContentText("Por favor, ingrese un valor numérico");
			alert.showAndWait();
			return;
		}

	}

	// Método para convertir Unidaddes de Masa
	public void convertirMasas() throws Exception {
		// Tomamos el valor a convertir
		String monto = valorTxt2.getText();
		// Variable para guardar en valor como double
		double mn;
		// Verificamos si el campo esta vacío
		if (monto.isEmpty()) {
			// Si esta vacío mostramos una alarma
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Advertencia");
			alert.setHeaderText("El campo de valor esta vacío");
			alert.setContentText("Por favor, ingrese un valor numérico");
			alert.showAndWait();
			return;
		}

		// Verificamos que el valor sea un número
		try {

			// Si lo es lo cargamos a la variable mn
			mn = Double.parseDouble(monto);

			// Validamos las llaves de cada lista
			String list1 = masasChoice1.getValue();
			String list2 = masasChoice2.getValue();
			// El resultado retorna del metodo convertir de la instancia de moneda
			double res = peso.convertir(list1, list2, mn);
			// Creamos un formato para la presentación del resultado
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000000");
			// Este es el resultado para copiar al porta papeles
			resforclip2 = Double.toString(res);
			// Este es el resultado para cargar al label de salida
			String resultado = decimalFormat.format(res);
			resultLbl2.setText(resultado + " " + masasChoice2.getValue());
			// Sino es un numero generamos una advertencia
			masasBtn2.setDisable(false);
		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Advertencia");
			alert.setHeaderText("Valor no válido");
			alert.setContentText("Por favor, ingrese un valor numérico");
			alert.showAndWait();
			return;
		}

	}

	// Event Listener on Button[#divisasBtn].onAction
	@FXML
	public void convertir(ActionEvent event) throws Exception {
		// TODO Autogenerated

		if (menuDivisas.isSelected()) {
			convertirDivisas();
		} else {
			convertirMasas();
		}

	}

	// Event Listener on Button[#divisasBtn1].onAction
	@FXML
	public void copiar(ActionEvent event) {
		// TODO Autogenerated

		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		
		if(menuDivisas.isSelected()) {
			content.putString(resforclip1);
		}else {
			content.putString(resforclip2);
		}
		
		clipboard.setContent(content);

	}
}
