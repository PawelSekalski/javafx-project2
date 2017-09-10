package app.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class infoC {

    @FXML
    private TextField tf_i;
    @FXML
    private TextField tf_n;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private RadioButton rb5;
    @FXML
    private Button send;
    @FXML
    private CheckBox cb1;
    @FXML
    private CheckBox cb2;
    @FXML
    private CheckBox cb3;
    @FXML
    private CheckBox cb4;
    @FXML
    private TextField tf_inne;
    @FXML
    private ComboBox<String> combo;
    @FXML
    void sendAction(MouseEvent event) throws FileNotFoundException {
    	
    	PrintWriter pw = new PrintWriter("C:\\Users\\PS\\Desktop\\Java-programy\\zapisyZTest\\"+tf_i.getText()+tf_n.getText()+".txt");
    	String rb_sel = "";
    	String cb_sel = "";
    	String combo_sel = "";
    	String info ="Ankiet info: "+tf_i.getText()+", "+tf_n.getText()+"\n";

    	//rbutton
    	if(rb1.isSelected()) {
    		rb_sel = rb1.getText();
    	
    	} else if (rb2.isSelected()) {
    		rb_sel = rb2.getText();
    	
		} else if (rb3.isSelected()) {
			rb_sel = rb3.getText();
		
		} else if (rb4.isSelected()) {
			rb_sel = rb4.getText();
		
		} else if (rb5.isSelected()) {
			rb_sel = rb5.getText();
		}
    	info += "Znajomoœæ jêzyka angielskiego: "+rb_sel+"\n";

    	//checkbox
    	if(cb1.isSelected()) {
    		cb_sel = cb1.getText();
    	}
    	if(cb2.isSelected()) {
    		cb_sel += cb2.getText();
    	}
    	if(cb3.isSelected()) {
    		cb_sel += cb3.getText();
    	}
    	if(cb4.isSelected()) {
    		cb_sel += tf_inne.getText();
    	}
    	info += "Znajomoœæ jêzyków programowania: "+cb_sel+"\n";

    	combo_sel = "Wybór kursu: "+combo.getSelectionModel().getSelectedItem()+"\n";

    	info += combo_sel;
    	pw.println(info);  
    	pw.close();
    	
    }
    @FXML
    void inne(MouseEvent event) {
    	tf_inne.setDisable(false);
    }
    
    //inicjalizacja
    public void initialize(){
    	    	//combobox
    	combo.getItems().add("Back-end Dev");
    	combo.getItems().add("Front-end Dev");
    	combo.getItems().add("No Dev");
    	combo.getSelectionModel().select(0);
    }
}
