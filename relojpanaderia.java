import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class relojpanaderia extends Thread {
	private JLabel lb1;
	private JLabel lb2;
	
	
	 public relojpanaderia(JLabel lb1,JLabel lb2){
	        this.lb1 = lb1;
	       this.lb2 = lb2;
	    }
	    
	    public void run(){
	       while(true){
	           Date hoy = new Date();
	           SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a");
	           SimpleDateFormat d= new SimpleDateFormat("dd/MM/yyyy");
	           //lb1.setText(s.format(hoy));
	           lb1.setText(t.format(hoy));
	           lb2.setText(d.format(hoy));
	           try{
	               sleep(1000);
	           }catch(Exception ex){
	               
	           }
	       } 
	    }

		
}
