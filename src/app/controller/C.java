package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.db.dbConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class C {

    private static final AlertType Warning = null;

	@FXML
    private TextField tf_log;
	@FXML
	private TextField tf_pass;
    @FXML
    private PasswordField pf_pass;
    @FXML
    private Button btn_log;
    @FXML
    private Button btn_show;
    @FXML
    private Button btn_db;

    public dbConnector db;
    String logowanie;
    @FXML
    void loginAction(MouseEvent event) throws IOException {
    	if(tf_log.getText().equals("admin") && (tf_pass.getText().equals("admin")) || (tf_log.getText().equals("admin") && (pf_pass.getText().equals("admin")))) {
    			System.out.println("Wrrrelcome!");
    			
    			Stage stageInfo = new Stage();
    			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/infoView.fxml"));
    			Scene scene = new Scene (parent);
    			stageInfo.setScene(scene);
    			stageInfo.setTitle("Info dla Login");
    			stageInfo.show();
    } else {
    	Alert a = new Alert (AlertType.INFORMATION);
    	a.setContentText("B³êdny login lub password");
    	a.setTitle("B³¹d");
    	a.showAndWait();
    	}
	}
    
    public void initialize() {
    	db = new dbConnector();
    }
    
    @FXML
    void logDB(MouseEvent event) throws ClassNotFoundException, SQLException, IOException{
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where login ='"+tf_log.getText()+
    			"' and pass ='"+pf_pass.getText()+"';");
    	
    	if(rs.next()) {
    		Stage stageTable = new Stage();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/tableView.fxml"));
			Scene sceneTable = new Scene (parent);
			stageTable.setScene(sceneTable);
			stageTable.setTitle("Table db");
			stageTable.show();
    	}
    	
    }
    
    @FXML
    void showAction(MouseEvent event) {
    	pf_pass.setVisible(false);
    	tf_pass.setVisible(true);
    	String pass = pf_pass.getText();
    	tf_pass.setText(pass);
    	pf_pass.setText("");
    }
    
    @FXML
    void hideAction(MouseEvent event) {
    	pf_pass.setVisible(false);
    	tf_pass.setVisible(true);
    	String pass = tf_pass.getText();
    	pf_pass.setText(pass);
    	tf_pass.setText("");

    }

}
