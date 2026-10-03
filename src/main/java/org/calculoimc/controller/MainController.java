package org.calculoimc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.calculoimc.model.Pessoa;
import org.calculoimc.utils.ArquivoUtil;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainController {
    @FXML
    public TextField txtNome;

    @FXML
    public TextField txtAltura;

    @FXML
    public TextField txtPeso;

    @FXML
    public Label lbIMC;

    @FXML
    public Label IbClassificacao;

    @FXML
    private TableView<Pessoa> tabela;

    @FXML
    private TableColumn<Pessoa, Integer> colId;

    @FXML
    private TableColumn<Pessoa, String> colNome;

    @FXML
    private TableColumn<Pessoa, Double> colAltura;

    @FXML
    private TableColumn<Pessoa, Double> colPeso;

    @FXML
    private TableColumn<Pessoa, Double> colImc;

    private final ObservableList<Pessoa> dados = FXCollections.observableArrayList();
    private final DecimalFormat df = new DecimalFormat("#0.00");

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colImc.setCellValueFactory(new PropertyValueFactory<>("imc"));
        tabela.setItems(dados);
    }

    private Pessoa lerFormulario() {
        String nome = txtNome.getText().trim().replace(",", " ");
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Informe o nome.");
        }
        double altura = Double.parseDouble(txtAltura.getText().trim().replace(',', '.'));
        double peso = Double.parseDouble(txtPeso.getText().trim().replace(',', '.'));
        if (altura <= 0 || peso <= 0) {
            throw new IllegalArgumentException("Altura e peso devem ser maiores que zero.");
        }
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setAltura(altura);
        p.setPeso(peso);
        p.calcularIMC();
        return p;
    }

    private void mostrarResultado(Pessoa p) {
        lbIMC.setText(df.format(p.getImc()));
        IbClassificacao.setText(p.classificacaoIMC());
    }

    private void erro(String mensagem) {
        new Alert(Alert.AlertType.ERROR, mensagem).showAndWait();
    }

    @FXML
    protected void onCalcularIMCClink() {
        try {
            mostrarResultado(lerFormulario());
        } catch (NumberFormatException e) {
            erro("Altura e peso devem ser números válidos. Exemplo: 1.75 e 70");
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }

    @FXML
    protected void onSalvarClick() {
        try {
            Pessoa p = lerFormulario();
            p.setId(dados.size() + 1);
            dados.add(p);
            ArquivoUtil.salvar(dados);
            mostrarResultado(p);
            txtNome.clear();
            txtAltura.clear();
            txtPeso.clear();
        } catch (NumberFormatException e) {
            erro("Altura e peso devem ser números válidos. Exemplo: 1.75 e 70");
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        } catch (IOException e) {
            erro("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    @FXML
    protected void onCarregarClick() {
        try {
            dados.setAll(ArquivoUtil.carregar());
            for (int i = 0; i < dados.size(); i++) {
                dados.get(i).setId(i + 1);
            }
            tabela.refresh();
        } catch (IOException e) {
            erro("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
}
