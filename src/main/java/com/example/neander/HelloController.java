package com.example.neander;

import com.example.neander.Exepitions.InstrucaoNexiste;
import com.example.neander.configs.Dados;
import com.example.neander.neander.InstrucaoNeander;
import com.example.neander.neander.Neander;
import com.example.neander.neander.processadorInstrucaoNeander;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    TextField caminho;
    private List<Dados> instrucaoDados =new ArrayList<>();
    @FXML
    private TableView<Dados> tabela;
    @FXML
    private TableColumn<Dados, Integer> endereco;
    @FXML
    private TableColumn<Dados, Integer> dados;

    private List<InstrucaoNeander> instrucaoBasic =new ArrayList<>();
    @FXML
    private TableView<InstrucaoNeander> tabela1;
    @FXML
    private TableColumn<InstrucaoNeander, Integer> endereco1;

    @FXML
    private TableColumn<InstrucaoNeander, String> mine;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
@FXML
private void executa(){
    endereco.setCellValueFactory(
            new PropertyValueFactory<>("ende"));
    dados.setCellValueFactory(
            new PropertyValueFactory<>("memoria"));

    endereco1.setCellValueFactory(
            new PropertyValueFactory<>("endereco"));
    mine.setCellValueFactory(
            new PropertyValueFactory<>("codigoString"));

    processadorInstrucaoNeander n = null;
    try {
        n = new processadorInstrucaoNeander(caminho.getText());
    } catch (Exception e) {
        e.printStackTrace();
    }

    assert n != null;
    Neander processadorN = new Neander(n.getInstrucoes(), 255);


    try {
        instrucaoDados = processadorN.resultado();
        instrucaoBasic=n.getInstrucoes();
    } catch (InstrucaoNexiste e) {
        throw new RuntimeException(e);
    }

    tabela.setItems(listaDeClientes());
    tabela1.setItems(listaComandas());
}

    private ObservableList<InstrucaoNeander> listaComandas() {
        return  FXCollections.observableArrayList(instrucaoBasic);
}
    private ObservableList<Dados> listaDeClientes() {
        return FXCollections.observableArrayList(listaVolta());}

    private ArrayList<Dados> listaVolta(){
        for (int i = 0; i < instrucaoDados.size(); i++) {

            instrucaoDados.get(i).setEnde(i);
        }
        return (ArrayList<Dados>) instrucaoDados;
    }
}