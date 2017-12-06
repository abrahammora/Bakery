import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Blob;
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
	public void desconectar() {
		try {
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	public ArrayList<Object[]> getUser(String user,String pass) {
		try {
			Object[] rows = null;
			Statement statement = (Statement) conexion.createStatement();
			ResultSet result = statement.executeQuery("select * from usuarios2 where nombre='"+user+"'and pass='"+pass+"'");
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
	        rs = st.executeQuery("SELECT u.nombre,u.app,u.apm,u.domicilio,u.telefono,c.nombre_cargo,u.pass,u.salario,u.fecha_ingreso FROM usuarios2 as u inner join cargo as c on u.id_cargo=c.id_cargo");
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
	public void getPan (JComboBox<Object> jcombobox,int id_cat){ 

		 try{
           Statement st = (Statement) conexion.createStatement();
           ResultSet rs = st.executeQuery("select nombre_pan from panes where id_categoria="+id_cat+"");
           
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			
			combo.addElement("Seleccione un Pan");
			jcombobox.setModel(combo);
           while(rs.next()){
          	 combo.addElement(rs.getString("nombre_pan"));
          	 jcombobox.setModel(combo);
          	 
           }
           rs.close();
           
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           
       }
		 
		 
	   }
	
	public void setUsuario(int id_cargo,String nombre,String app,String apm,String domicilio,String tel,String pass,float salario,String datein) {
		String sql = "insert into usuarios2(id_cargo,nombre,app,apm,domicilio,telefono,pass,salario,fecha_ingreso)"
				+ "values("				
				+ "'"+id_cargo+"',"
				+ "'"+nombre+"',"
				+ "'"+app+"',"
				+ "'"+apm+"',"
				+ "'"+domicilio+"',"
				+ "'"+tel+"',"
				+ "MD5('"+pass+"'),"
				+ "'"+salario+"',"
				+ "'"+datein+"')";
			System.out.println(sql);
		 try{
             Statement st = (Statement) conexion.createStatement();
            st.execute(sql);
             JOptionPane.showMessageDialog(null, "El usuario fue agregado correctamente");                        
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }   
	}
	
	public void editarUsuario(int id,int id_cargo,String nombre,String app,String apm,String domicilio,String tel,String pass,float salario,String datein) {
		 String sql = "UPDATE usuarios2 SET id_cargo="+id_cargo+","
	                + "nombre='"+nombre+"',"
	                + "app='"+app+"',"
	                + "apm='"+apm+"',"
	                + "domicilio='"+domicilio+"',"
	                + "telefono='"+tel+"',"
	                + "pass=SHA('"+pass+"'),"
	                + "salario="+salario+","
	                + "fecha_ingreso='"+datein+"' "	                
	                + "where id_usuario="+id+";";
	                
		 System.out.println(sql);
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
		 String sql = "delete from usuarios2 where id_usuario="+id+";";
	                
	                
		 
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
	        rs = st.executeQuery("SELECT v.nombre_pan,ca.nombre_categoria,v.precio_unitario,v.cantidad_piezas,v.fventa from productos as p inner join categoria_pan as ca on p.id_categoria=ca.id_categoria inner join venta as v on v.id_venta=p.id_venta");
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
	
	public void consultaVenta (JTable table){ 

	    try{
	        Statement st = (Statement) conexion.createStatement();
	        ResultSet rs;
	        Object[] rows = null;
	        rs = st.executeQuery("SELECT pa.nombre_pan,ca.nombre_categoria,v.precio_unitario,v.cantidad_piezas,v.fventa from venta as v inner join categoria_pan as ca on v.id_categoria=ca.id_categoria inner join panes as pa on pa.id_pan=v.id_pan");
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
	public boolean searchPro(String name) {
		
		String nmo = null;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from producto where nombre='"+name+"'");					       
		        while (result.next()) {
		        	nmo=result.getString("nombre");
		        	//System.out.println(nmo);
		        	if(nmo==name) 
		        		return true;
		        	else
		        		return false;		       
		
		         }
		        result.close();
		        
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    	return true;
		
	}
public boolean searchVen(String name) {
		
		String nmo = null;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from venta where nombre_pan='"+name+"'");					       
		        while (result.next()) {
		        	nmo=result.getString("nombre_pan");
		        	//System.out.println(nmo);
		        	if(nmo==name) 
		        		return true;
		        	else
		        		return false;		       
		
		         }
		        result.close();
		        
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    	return true;
		
	}
	public boolean searchUser(String name) {
		String nmo;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from usuarios2 where nombre='"+name+"'");
			 
		        while (result.next()) {            
		        	nmo=result.getString("nombre");
		        	//System.out.println(nmo);
		        	if(nmo==name) 
		        		return true;
		        	else
		        		return false;	
		         }
		        result.close();
		        
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean search(String name) {				
		String nmo;
		try {						
			Statement st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery("select * from materia where nombre_materia='"+name+"'");
		
		        while (result.next()) {            
		        	nmo=result.getString("nombre_materia");
		        	//System.out.println(nmo);
		        	if(nmo==name) 
		        		return true;
		        	else
		        		return false;
		         }
		        result.close();
		       
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
	public int idVenta(int r) {
		int id = 0;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet resul = st.executeQuery("select id_venta from venta where id_categoria='"+r+"'");
			while(resul.next()) {
				id=resul.getInt("id_venta");
			}
			resul.close();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	public int idPan(String r) {
		int id = 0;
		try {
			Statement st = (Statement) conexion.createStatement();
			ResultSet resul = st.executeQuery("select id_pan from panes where nombre_pan='"+r+"'");
			while(resul.next()) {
				id=resul.getInt("id_pan");
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
			ResultSet resul = st.executeQuery("select id_usuario from usuarios2 where nombre='"+r+"'");
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
	                
	                
		// System.out.println(sql);
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
	
	
	public void setProducto(int id_categoria,int id_venta) {
		String sql="insert into productos(id_categoria,id_venta) values("				
				+ "'"+id_categoria+"',"
				+ ""+id_venta+");";								
				
		try {
			Statement st = (Statement) conexion.createStatement();
			st.execute(sql);
			JOptionPane.showMessageDialog(null, "Producto Agregado Correctammente");
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	public void setVenta(int id_categoria,int id_pan,float precioU,int cantidad,String fventa) {
		String sql="insert into venta(id_categoria,id_pan,precio_unitario,cantidad_piezas,fventa) values("				
				+ "'"+id_categoria+"',"				
				+ "'"+id_pan+"',"				
				+ ""+precioU+","
				+ ""+cantidad+","												
				+ "'"+fventa+"');";		
		try {
			Statement st = (Statement) conexion.createStatement();
			st.execute(sql);
			JOptionPane.showMessageDialog(null, "Pan Agregado Correctammente");
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	public void editarProducto(int id,int id_categoria,String nombre,float pventa,String fventa) {
		String sql="update producto set id_categoria="+id_categoria+","
				+ "nombre='"+nombre+"',"				
				+ "pventa="+pventa+","
				+ "fventa='"+fventa+"' where id_producto="+id+";";
		//System.out.println(sql);
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
	
	public void editarVenta(int id,int id_categoria,int id_pan,float precioU,int cantidad,String fventa) {
		String sql="update venta set id_categoria="+id_categoria+","
				+ "id_pan="+id_pan+","
				+ "precio_unitario="+precioU+","
				+ "cantidad_piezas="+cantidad+","
				+ "fventa='"+fventa+"' where id_venta="+id+";";
		System.out.println(sql);
		int dec = JOptionPane.showConfirmDialog(null, "Desea editar el pan seleccionado",
	               "Confirmar Edicion", JOptionPane.OK_CANCEL_OPTION);
			  switch(dec){
	                  case 0:
	                       try{
	                           Statement st = (Statement) conexion.createStatement();
	                          st.execute(sql);
	                           JOptionPane.showMessageDialog(null, "El pan a sido modificada");
	                           
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
	public void eliminarVenta(int id,JTable table_1,int r) {
		String sql="delete from venta where id_venta="+id+";";
		int dec = JOptionPane.showConfirmDialog(null, "Desea eliminar el pan seleccionado",
	              "Confirmar Eliminación", JOptionPane.OK_CANCEL_OPTION);
			  switch(dec){
	                 case 0:
	                      try{
	                          Statement st = (Statement) conexion.createStatement();
	                         st.execute(sql);
	                          JOptionPane.showMessageDialog(null, "El pan a sido eliminado");
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
	
	public float getTotal() {
		float res = 0;
		 try{
			 Statement st = (Statement) conexion.createStatement();
				ResultSet resul = st.executeQuery("select SUM(precio_unitario*cantidad_piezas) as Total from venta");
				while(resul.next()) {
					res=resul.getFloat("Total");
					
				}
				resul.close();
				return res;         
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
             }   
		
	}
	
	public ImageIcon getImage(int id_pan) throws IOException {
		Array res = null;
		ImageIcon newicon = null;
		ImageIcon foto;
		InputStream is;
		 try{
			 Statement st = (Statement) conexion.createStatement();
				ResultSet resul = st.executeQuery("select imagen from imagenes where id_pan="+id_pan+"");
				while(resul.next()) {
					is=resul.getBinaryStream(1);
					BufferedImage bi = ImageIO.read(is);
					foto = new ImageIcon(bi);
					Image img = foto.getImage();
					Image newimg = img.getScaledInstance(214, 230, Image.SCALE_SMOOTH);
					newicon = new ImageIcon(newimg);
					
				}
				resul.close();
				return newicon;       
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
             }   
	}
	
	/*ArrayList<Imagen> getImagenes(int id_pan) {
		 ArrayList<Imagen> lista = new ArrayList();
		 try {
			 Statement st = (Statement) conexion.createStatement();
			 ResultSet rs = st.executeQuery("SELECT imagen,tipo FROM imagenes where id_pan="+id_pan+""); 
		 while (rs.next())
		 {
			 Imagen imagen=new Imagen();
			 Blob blob = (Blob) rs.getBlob("imagen");
			 String nombre = rs.getObject("nombre").toString();
			 byte[] data = blob.getBytes(1, (int)blob.length());
			 BufferedImage img = null;
		 try {
			 img = ImageIO.read(new ByteArrayInputStream(data));
		 } catch (IOException ex) {
			 //Logger.getLogger(class.getName().log(Level.SEVERE, null, ex);
		 }
		 
		 imagen.setImagen(img);
		 imagen.setNombre(nombre);
		 lista.add(imagen);
		 }
		 rs.close();
		 } catch (SQLException ex) {
			 //Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 return lista;
		}*/
	
}
