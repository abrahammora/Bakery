import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class sql {
	static java.sql.Connection conexion=null;
	
	public boolean GetConnection(String usuario,String password) {
		String db ="panaderia";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String servidor ="jdbc:mysql://127.0.0.1/"+db;
			conexion = DriverManager.getConnection(servidor, usuario, password);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error numero 1 en la conexion de la Base de Datos"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
			conexion=null;
			return false;
		}catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error numero 2 en la conxeion de la Base de Datos"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
			conexion = null;
			return false;
		}
	}
	
	public ArrayList<Object[]> getUser(String user,String pass) {
		try {
			Object[] rows = null;
			Statement statement = (Statement) conexion.createStatement();
			ResultSet result = statement.executeQuery("select * from usuarios where nombre='"+user+"'and pass='"+pass+"'");
			 java.sql.ResultSetMetaData rsm = result.getMetaData();
		        // crear una lista donde almacenare los datos
		        ArrayList<Object[]> data=new ArrayList<>();
		        // calcular el numero de filas y gebnerar la informacion para cada una
		        while (result.next()) {            
		              rows=new Object[rsm.getColumnCount()];
		             for (int i = 0; i < rows.length; i++) {
		                // generar los datos de cada fila 
		                 rows[i]=result.getObject(i+1);
		                 }
		             // Guardar los datos consultados en una lista 
		             data.add(rows);
		         }
		        result.close();
		        return data;
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error numero 1 en la conexion de la Base de Datos"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
			conexion = null;
			return null;
		}
	}
		
	public void consultas (JTable table){ 

	    try{
	        Statement st = (Statement) conexion.createStatement();
	        ResultSet rs;
	        Object[] rows = null;
	        rs = st.executeQuery("SELECT u.nombre,u.app,u.apm,u.domicilio,u.telefono,c.nombre_cargo,u.pass,u.salario,u.fecha_ingreso,u.fecha_despido FROM usuarios as u inner join cargo as c on u.id_cargo=c.id_cargo");
	        java.sql.ResultSetMetaData rsm = rs.getMetaData();
	        // crear una lista donde almacenare los datos
	        ArrayList<Object[]> data=new ArrayList<>();
	        // calcular el numero de filas y gebnerar la informacion para cada una
	        while (rs.next()) {            
	              rows=new Object[rsm.getColumnCount()];
	             for (int i = 0; i < rows.length; i++) {
	                // generar los datos de cada fila 
	                 rows[i]=rs.getObject(i+1);
	                 }
	             // Guardar los datos consultados en una lista 
	             data.add(rows);
	         }
	        // general el modelo de la tabla
	        DefaultTableModel dtm =(DefaultTableModel) table.getModel();
	        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
	        if (dtm.getRowCount() != 0){
	            int d = dtm.getRowCount();
	        for (int y = 0; y < d; y++){
	            dtm.removeRow(0);
	           }
	        }
	        table.setModel(dtm);
	        // agregar los datos de la consulta a la tabla 
	        for (int i = 0; i <data.size(); i++) {
	             dtm.addRow(data.get(i));
	            }   
	        rs.close();
	       
	       
	     }catch(SQLException ex){
	        JOptionPane.showMessageDialog(null,ex.getMessage(), "¡ERROR!", JOptionPane.ERROR_MESSAGE);
	        conexion=null;
	        
	     }
		 
	   }
	
	
	public void getCargo (JComboBox<Object> jcombobox){ 

		 try{
             Statement st = (Statement) conexion.createStatement();
             ResultSet rs = st.executeQuery("select nombre_cargo from cargo");
             
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			
			combo.addElement("Seleccione un cargo");
			jcombobox.setModel(combo);
             while(rs.next()){
            	 combo.addElement(rs.getString("nombre_cargo"));
            	 jcombobox.setModel(combo);
            	 
             }
             rs.close();
             
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             
         }
		 
		 
	   }
	
	public void getCategoria (JComboBox<Object> jcombobox){ 

		 try{
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery("select nombre_categoria from categoria_pan");
            
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			
			combo.addElement("Seleccione una categoria");
			jcombobox.setModel(combo);
            while(rs.next()){
           	 combo.addElement(rs.getString("nombre_categoria"));
           	 jcombobox.setModel(combo);
           	 
            }
            rs.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
        }
		 
		 
	   }
	
	public void setUsuario(int id_cargo,String nombre,String app,String apm,String domicilio,String tel,String pass,float salario,String datein,String dateexp) {
		String sql = "insert into usuarios(id_cargo,nombre,app,apm,domicilio,telefono,pass,salario,fecha_ingreso,fecha_despido)"
				+ "values("				
				+ "'"+id_cargo+"',"
				+ "'"+nombre+"',"
				+ "'"+app+"',"
				+ "'"+apm+"',"
				+ "'"+domicilio+"',"
				+ "'"+tel+"',"
				+ "'"+pass+"',"
				+ "'"+salario+"',"
				+ "'"+datein+"',"
				+ "'"+dateexp+"')";
		 try{
             Statement st = (Statement) conexion.createStatement();
            st.execute(sql);
             JOptionPane.showMessageDialog(null, "El usuario fue agregado correctamente");                        
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }   
	}
	
	public void editarUsuario(int id,int id_cargo,String nombre,String app,String apm,String domicilio,String tel,String pass,float salario,String datein,String dateexp) {
		 String sql = "UPDATE usuarios SET id_cargo='"+id_cargo+"',"
	                + "nombre='"+nombre+"',"
	                + "app='"+app+"',"
	                + "apm='"+apm+"',"
	                + "domicilio='"+domicilio+"',"
	                + "telefono='"+tel+"',"
	                + "pass='"+pass+"',"
	                + "salario="+salario+","
	                + "fecha_ingreso='"+datein+"',"
	                + "fecha_despido='"+dateexp+"' "
	                + "where id_usuario="+id+";";
	                
		 
		  int dec = JOptionPane.showConfirmDialog(null, "Desea editar el usuario seleccionado",
                  "Confirmar Edicion", JOptionPane.OK_CANCEL_OPTION);
		  switch(dec){
                     case 0:
                          try{
                              Statement st = (Statement) conexion.createStatement();
                             st.execute(sql);
                              JOptionPane.showMessageDialog(null, "El usuario a sido modificado");
                              
                             }catch(SQLException ex){
                                 JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                 
                                }   
                         break;
                     case 2:
                         JOptionPane.showMessageDialog(null, "Modificación  Cancelada");
                         
                         break;
                 }
				
				
	}
	
	public void eliminarUsuario(int id,JTable table_2,int row) {
		 String sql = "delete from usuarios where id_usuario="+id+";";
	                
	                
		 
		  int dec = JOptionPane.showConfirmDialog(null, "Desea eliminar el usuario seleccionado",
                 "Confirmar Eliminación", JOptionPane.OK_CANCEL_OPTION);
		  switch(dec){
                    case 0:
                         try{
                             Statement st = (Statement) conexion.createStatement();
                            st.execute(sql);
                             JOptionPane.showMessageDialog(null, "El usuario a sido eliminado");
                             DefaultTableModel dtm = (DefaultTableModel) table_2.getModel();
         					dtm.removeRow(row);
                             
                            }catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                
                               }   
                        break;
                        
                    case 2:
                        JOptionPane.showMessageDialog(null, "Eliminación Cancelada");
                        
                        break;
                }
				
				
	}
	public void consultaMateria (JTable table){ 

	    try{
	        Statement st = (Statement) conexion.createStatement();
	        ResultSet rs;
	        Object[] rows = null;
	        rs = st.executeQuery("SELECT nombre_materia,cantidad,precio from materia");
	        java.sql.ResultSetMetaData rsm = rs.getMetaData();
	        // crear una lista donde almacenare los datos
	        ArrayList<Object[]> data=new ArrayList<>();
	        // calcular el numero de filas y gebnerar la informacion para cada una
	        while (rs.next()) {            
	              rows=new Object[rsm.getColumnCount()];
	             for (int i = 0; i < rows.length; i++) {
	                // generar los datos de cada fila 
	                 rows[i]=rs.getObject(i+1);
	                 }
	             // Guardar los datos consultados en una lista 
	             data.add(rows);
	         }
	        // general el modelo de la tabla
	        DefaultTableModel dtm =(DefaultTableModel) table.getModel();
	        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
	        if (dtm.getRowCount() != 0){
	            int d = dtm.getRowCount();
	        for (int y = 0; y < d; y++){
	            dtm.removeRow(0);
	           }
	        }
	        table.setModel(dtm);
	        // agregar los datos de la consulta a la tabla 
	        for (int i = 0; i <data.size(); i++) {
	             dtm.addRow(data.get(i));
	            }   
	        rs.close();
	       
	       
	     }catch(SQLException ex){
	        JOptionPane.showMessageDialog(null,ex.getMessage(), "¡ERROR!", JOptionPane.ERROR_MESSAGE);
	        conexion=null;
	        
	     }
		 
	   }
		
	public void consultaProducto (JTable table){ 

	    try{
	        Statement st = (Statement) conexion.createStatement();
	        ResultSet rs;
	        Object[] rows = null;
	        rs = st.executeQuery("SELECT p.descripcion,p.nombre,ca.nombre_categoria,p.pventa,p.fventa from producto as p inner join categoria_pan as ca on p.id_categoria=ca.id_categoria");
	        java.sql.ResultSetMetaData rsm = rs.getMetaData();
	        // crear una lista donde almacenare los datos
	        ArrayList<Object[]> data=new ArrayList<>();
	        // calcular el numero de filas y gebnerar la informacion para cada una
	        while (rs.next()) {            
	              rows=new Object[rsm.getColumnCount()];
	             for (int i = 0; i < rows.length; i++) {
	                // generar los datos de cada fila 
	                 rows[i]=rs.getObject(i+1);
	                 }
	             // Guardar los datos consultados en una lista 
	             data.add(rows);
	         }
	        // general el modelo de la tabla
	        DefaultTableModel dtm =(DefaultTableModel) table.getModel();
	        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
	        if (dtm.getRowCount() != 0){
	            int d = dtm.getRowCount();
	        for (int y = 0; y < d; y++){
	            dtm.removeRow(0);
	           }
	        }
	        table.setModel(dtm);
	        // agregar los datos de la consulta a la tabla 
	        for (int i = 0; i <data.size(); i++) {
	             dtm.addRow(data.get(i));
	            }   
	        rs.close();
	       
	       
	     }catch(SQLException ex){
	        JOptionPane.showMessageDialog(null,ex.getMessage(), "¡ERROR!", JOptionPane.ERROR_MESSAGE);
	        conexion=null;
	        
	     }
		 
	   }
	public ArrayList<Object[]> searchPro(String name) {
		Object[] rows;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from usuarios where nombre='"+name+"'");
			 java.sql.ResultSetMetaData rsm = result.getMetaData();
		        // crear una lista donde almacenare los datos
		        ArrayList<Object[]> data=new ArrayList<>();
		        // calcular el numero de filas y gebnerar la informacion para cada una
		        while (result.next()) {            
		              rows=new Object[rsm.getColumnCount()];
		             for (int i = 0; i < rows.length; i++) {
		                // generar los datos de cada fila 
		                 rows[i]=result.getObject(i+1);
		                 }
		             // Guardar los datos consultados en una lista 
		             data.add(rows);
		         }
		        result.close();
		        return data;
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}
	public ArrayList<Object[]> searchUser(String name) {
		Object[] rows;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from usuarios where nombre='"+name+"'");
			 java.sql.ResultSetMetaData rsm = result.getMetaData();
		        // crear una lista donde almacenare los datos
		        ArrayList<Object[]> data=new ArrayList<>();
		        // calcular el numero de filas y gebnerar la informacion para cada una
		        while (result.next()) {            
		              rows=new Object[rsm.getColumnCount()];
		             for (int i = 0; i < rows.length; i++) {
		                // generar los datos de cada fila 
		                 rows[i]=result.getObject(i+1);
		                 }
		             // Guardar los datos consultados en una lista 		             
		             data.add(rows);
		         }
		        result.close();
		        return data;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public ArrayList<Object[]> search(String name) {				
		Object[] rows;
		try {						
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from usuarios where nombre='"+name+"'");
			 java.sql.ResultSetMetaData rsm = result.getMetaData();
		        // crear una lista donde almacenare los datos
		        ArrayList<Object[]> data=new ArrayList<>();
		        // calcular el numero de filas y gebnerar la informacion para cada una
		        while (result.next()) {            
		              rows=new Object[rsm.getColumnCount()];
		             for (int i = 0; i < rows.length; i++) {
		                // generar los datos de cada fila 
		                 rows[i]=result.getObject(i+1);
		                 }
		             // Guardar los datos consultados en una lista 
		             data.add(rows);
		         }
		        result.close();
		        return data;
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//return id;
	}
	
	public int idMateria(String r) {
		int id = 0;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet resul = st.executeQuery("select id_materia from materia where nombre_materia='"+r+"'");
			while(resul.next()) {
				id=resul.getInt("id_materia");
			}
			resul.close();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	public int idProducto(String r) {
		int id = 0;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet resul = st.executeQuery("select id_producto from producto where nombre='"+r+"'");
			while(resul.next()) {
				id=resul.getInt("id_producto");
			}
			resul.close();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	
	public int idUser(String r) {
		int id = 0;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet resul = st.executeQuery("select id_usuario from usuarios where nombre='"+r+"'");
			while(resul.next()) {
				id=resul.getInt("id_usuario");
			}
			resul.close();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	public void setMateria(String nombre,float cantidad,float precio) {
		String sql="insert into materia(nombre_materia,cantidad,precio) "
				+ "values("				
				+ "'"+nombre+"',"
				+ "'"+cantidad+"',"
				+ "'"+precio+"')";
		
		try {
			Statement st = (Statement) conexion.createStatement();
			st.execute(sql);
			JOptionPane.showMessageDialog(null, "Materia Agregada Correctammente");
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
				
				
				
	}
	
	public void editarMateria(int id,String nombre,float cantidad,float precio) {
		 String sql = "UPDATE materia SET nombre_materia='"+nombre+"',"
				 + "cantidad="+cantidad+","
				 + "precio="+precio+" where id_materia="+id+";";
	                
	                
		 //System.out.println(sql);
		  int dec = JOptionPane.showConfirmDialog(null, "Desea editar la materia seleccionada",
               "Confirmar Edicion", JOptionPane.OK_CANCEL_OPTION);
		  switch(dec){
                  case 0:
                       try{
                           Statement st = (Statement) conexion.createStatement();
                          st.execute(sql);
                           JOptionPane.showMessageDialog(null, "La materia a sido modificada");
                           
                          }catch(SQLException ex){
                              JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                              
                             }   
                      break;
                  case 2:
                      JOptionPane.showMessageDialog(null, "Modificación  Cancelada");
                      
                      break;
              }
	}
	
	public void eliminarMateria(int id,JTable table,int r) {
		 String sql = "delete from materia where id_materia="+id+";";					                	                		 
		  int dec = JOptionPane.showConfirmDialog(null, "Desea eliminar la materia seleccionada",
              "Confirmar Edicion", JOptionPane.OK_CANCEL_OPTION);
		  switch(dec){
                 case 0:
                      try{
                          Statement st = (Statement) conexion.createStatement();
                         st.execute(sql);
                          JOptionPane.showMessageDialog(null, "La materia a sido eliminada");
                          DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                          dtm.removeRow(r);
                          
                         }catch(SQLException ex){
                             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                             
                            }   
                     break;
                 case 2:
                     JOptionPane.showMessageDialog(null, "Eliminación  Cancelada");
                     
                     break;
             }
	}
	
	
	public void setProducto(int id_categoria,String nombre,String descripcion,float pventa,String fventa) {
		String sql="insert into producto(id_categoria,nombre,descripcion,pventa,fventa) values("				
				+ "'"+id_categoria+"',"
				+ "'"+nombre+"',"
				+ "'"+descripcion+"',"				
				+ ""+pventa+","
				+ "'"+fventa+"');";
				
		try {
			Statement st = (Statement) conexion.createStatement();
			st.execute(sql);
			JOptionPane.showMessageDialog(null, "Producto Agregado Correctammente");
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	public void editarProducto(int id,int id_categoria,String nombre,String descripcion,float pventa,String fventa) {
		String sql="update producto set id_categoria="+id_categoria+","
				+ "nombre='"+nombre+"',"
				+ "descripcion='"+descripcion+"',"				
				+ "pventa="+pventa+","
				+ "fventa="+fventa+" where id_producto="+id+";";
		
		int dec = JOptionPane.showConfirmDialog(null, "Desea editar el producto seleccionado",
	               "Confirmar Edicion", JOptionPane.OK_CANCEL_OPTION);
			  switch(dec){
	                  case 0:
	                       try{
	                           Statement st = (Statement) conexion.createStatement();
	                          st.execute(sql);
	                           JOptionPane.showMessageDialog(null, "El producto a sido modificada");
	                           
	                          }catch(SQLException ex){
	                              JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                              
	                             }   
	                      break;
	                  case 2:
	                      JOptionPane.showMessageDialog(null, "Modificación  Cancelada");
	                      
	                      break;
	              }
	}
	
	public void eliminarProducto(int id,JTable table_1,int r) {
		String sql="delete from producto where id_producto="+id+";";
		int dec = JOptionPane.showConfirmDialog(null, "Desea eliminar el producto seleccionado",
	              "Confirmar Eliminación", JOptionPane.OK_CANCEL_OPTION);
			  switch(dec){
	                 case 0:
	                      try{
	                          Statement st = (Statement) conexion.createStatement();
	                         st.execute(sql);
	                          JOptionPane.showMessageDialog(null, "El producto a sido eliminado");
	                          DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
	                          dtm.removeRow(r);
	                         }catch(SQLException ex){
	                             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                             
	                            }   
	                     break;
	                 case 2:
	                     JOptionPane.showMessageDialog(null, "Eliminación  Cancelada");
	                     
	                     break;
	             }
		
	}
	
	
}
