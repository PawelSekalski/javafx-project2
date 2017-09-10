package app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import app.db.dbConnector;
import app.model.tableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class tableC {

    @FXML
    private TableView<tableModel> tableTwo;
    @FXML
    private TableColumn<tableModel, Integer> col_id;
    @FXML
    private TableColumn<tableModel, String> col_im;
    @FXML
    private TableColumn<tableModel, String> col_naz;
    @FXML
    private TableColumn<tableModel, Double> col_wyn;
    @FXML
    private AnchorPane ap2;
    @FXML
    private Button btn_select;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_last;
    @FXML
    private TextField tf_salary;
    @FXML
    private Button btn_inc;
    @FXML
    private Button btn_upc;

    public dbConnector db; //import
    //wa¿ne - wypisywanie na tabelê
    public ObservableList<tableModel> data;
    
    @FXML
    void btnSelectAction(ActionEvent event) throws ClassNotFoundException, SQLException{
    	Connection conn = db.Connection();
    	data = FXCollections.observableArrayList();
    	ResultSet rs =  conn.createStatement().executeQuery("select * from employee");
    	
    	while(rs.next()) {
    		data.add(new tableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<tableModel, Integer>("id"));
    	col_im.setCellValueFactory(new PropertyValueFactory<tableModel, String>("name"));
    	col_naz.setCellValueFactory(new PropertyValueFactory<tableModel, String>("last"));
    	col_wyn.setCellValueFactory(new PropertyValueFactory<tableModel, Double>("salary"));
    	
    	tableTwo.setItems(null);
    	tableTwo.setItems(data);
    	
    }
    
    @FXML
    void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	try {
    	int id_del = tableTwo.getSelectionModel().getSelectedItem().getId();
    	String sql = "delete from employee where id="+id_del+";";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectAction(event);
    	
    	} catch (NullPointerException e) {
    		Alert a = new Alert (AlertType.INFORMATION);
        	a.setContentText("Zaznacz usuwany rekord, pauo!");
        	a.setHeaderText("FATALNY ERROR 80085");
        	a.setTitle("U³error");
        	a.showAndWait();
    	}
    }
   
    @FXML
    void btnINC(MouseEvent event) {
    	btn_upc.setDisable(false);
    	ap2.setDisable(false);
    	btn_inc.setDisable(true);
    }

    @FXML
    void btnInsertAction(ActionEvent event) {
    	btn_upc.setDisable(true);
    	ap2.setDisable(false);
    	btn_inc.setDisable(false);
    	
    }

    @FXML
    void btnInsertCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	if(tf_name.getText().equals("") || (tf_last.getText().equals("") || (tf_salary.getText().equals("")))) {
    		Alert a = new Alert (AlertType.INFORMATION);
    		a.setContentText("Brakuje info, pauo!");
    		a.setHeaderText("FATALNY ERROR 01");
    		a.setTitle("U³error");
    		a.showAndWait();

    	} else {
    		String sql = "insert into employee (name, last, salary) values ('"+tf_name.getText()+"','"
    				+tf_last.getText()+"',"+tf_salary.getText()+");";
    		PreparedStatement ps = conn.prepareStatement(sql);
        	ps.executeUpdate();
        	btnSelectAction(event);
        	btn_upc.setDisable(true);
        	ap2.setDisable(true);
    	}
    }
    
    @FXML
    void btnUpdateAction(ActionEvent event) {
    	try {
    		tf_name.setText(tableTwo.getSelectionModel().getSelectedItem().getName());
    		tf_last.setText(tableTwo.getSelectionModel().getSelectedItem().getLast());
    		tf_salary.setText(String.valueOf(tableTwo.getSelectionModel().getSelectedItem().getSalary()));
    		ap2.setDisable(false);
    		btn_inc.setDisable(true);
    		btn_upc.setDisable(false);
    	} catch (NullPointerException e) {
    		Alert a = new Alert (AlertType.INFORMATION);
    		a.setContentText("Zaznacz zmieniany rekord, pauo!");
    		a.setHeaderText("FATALNY ERROR 4000004");
    		a.setTitle("U³error");
    		a.showAndWait();
    	}
    }

    @FXML
    void btnUpdateCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	int id_update = tableTwo.getSelectionModel().getSelectedItem().getId();
    	String sql = "update employee set name ='"+tf_name.getText()+"', last='"
    			+tf_last.getText()+"', salary="+tf_salary.getText()+" where id ="+id_update+";";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectAction(event);
    }
    
    @FXML
    //inicjalizacja
    public void initialize() {
    	db = new dbConnector();
    }
    
}
    

