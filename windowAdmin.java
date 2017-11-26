import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

//import zapateria.reloj;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.crypto.NullCipher;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import com.toedter.calendar.JDayChooser;

public class windowAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_mat;
	private JTextField textField_cant;
	private JTextField textField_precio;
	private JTextField textField_aid;
	private JTextField textField_amate;
	private JTextField textField_acanti;
	private JTextField textField_apre;
	private JLabel reloj1,reloj2;
	static Connection conexion = null;
	private JTextField textField_idpro;
	private JTextField textField_descrip;
	private JTextField textField_pcompra;
	private JTextField textField_pventa;
	private JTextField textField_indate;
	private JTable table_1;
	private JTextField textFieldiduser;
	private JTextField textFielddomici;
	private JTextField textFieldtel;
	private JTextField textFielddate;
	private JTextField textFielddateexp;
	private JTable table_2;
	JButton btnChoice;
	private JTextField textFieldname;
	private JTextField textFieldapp;
	private JTextField textFieldapm;
	private JTextField textFieldsal;
	private JTextField textFieldpass;
	private JTextField textField_panno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowAdmin frame = new windowAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	
	
	/**
	 * Create the frame.
	 */
	public windowAdmin() {
		//windowAdmin frame = new windowAdmin();
		sql info = new sql();
		boolean s = info.GetConnection("root", "");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 528);		
		setLocationRelativeTo(null);		
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Menu.selectionBackground"));
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmUsuarios = new JMenuItem("Cambiar Usuario");
		mnOpciones.add(mntmUsuarios);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		reloj1 = new JLabel();
		reloj1.setBounds(500, 11, 87, 14);
		contentPane.add(reloj1,reloj2);
			
		reloj2 = new JLabel();
		reloj2.setBounds(587, 11, 66, 14);
		contentPane.add(reloj2);
		
			 relojpanaderia r = new relojpanaderia(reloj1,reloj2);
		     r.start();
			
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 846, 446);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 128, 128));
		tabbedPane.addTab("Inventario", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 448, 97);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		table.setBackground(SystemColor.info);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row,col;
					if(table.getRowSelectionAllowed()) {
						/*System.out.println(table.getSelectedRow() + table.getSelectedColumn());
						row=table.getSelectedRow();
						col=table.getSelectedRow();
						System.out.println(table.getValueAt(row, col).toString());*/
						//System.out.println("hola");
						
							row=table.getSelectedRow();
							//col=table.getSelectedColumn();
							
								//row=table.getSelectedRow();
								//textField_id.setText(table.getValueAt(row,0).toString());
								textField_mat.setText(table.getValueAt(row,0).toString());
								textField_cant.setText(table.getValueAt(row ,1).toString());
								textField_precio.setText(table.getValueAt(row,2).toString());

								
								
						
						
					
					}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "Harina", "14", "30"},
				{new Integer(2), "Azucar", "2", "20"},
				{new Integer(3), "Huevo", "5", "40"},							
			},
			new String[] {
				 "Materia", "Cantidad", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		info.consultaMateria(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(72, 209, 204));
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(20, 237, 477, 151);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAgregar = new JLabel("Agregar Materia Prima al Inventario");
		lblAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAgregar.setBounds(62, 126, 232, 14);
		panel_1.add(lblAgregar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(240, 248, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//textField_aid.setEnabled(false);
				//textField_aid.setEditable(true);
				
				
				textField_amate.setEditable(true);
				
				
				textField_acanti.setEditable(true);
				
				
				textField_apre.setEditable(true);
				btnChoice.setText("Agregar");
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregar.setBounds(333, 117, 89, 23);
		panel_1.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(240, 248, 255));
		btnModificar.setBounds(23, 58, 103, 23);
		panel_1.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aid,amat,acant,apre; 							
				if(table.getSelectedRow()>=0) {
					if(/*textField_id.getText()!=null&&*/textField_mat.getText()!=null&&textField_cant.getText()!=null&&textField_precio.getText()!=null) {
						/*aid=textField_id.getText();
						textField_aid.setText(aid);
						textField_aid.setEditable(false);*/						
						amat=textField_mat.getText();
						textField_amate.setText(amat);
						textField_amate.setEditable(true);						
						acant=textField_cant.getText();
						textField_acanti.setText(acant);
						textField_acanti.setEditable(true);						
						apre=textField_precio.getText();
						textField_apre.setText(apre);
						textField_apre.setEditable(true);						
						btnChoice.setText("Modificar");		
						
						
					}else 
						JOptionPane.showMessageDialog(null, "Debes de elegir una materia");
				}else
					JOptionPane.showMessageDialog(null, "Debes de elegir una materia","Advertencia",JOptionPane.WARNING_MESSAGE);
				
					
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(240, 248, 255));
		btnEliminar.setBounds(187, 58, 89, 23);
		panel_1.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String aid,amat,acant,apre;
				if(table.getSelectedRow()>=0) {
					if(/*textField_id.getText()!=null&&*/textField_mat.getText()!=null&&textField_cant.getText()!=null&&textField_precio.getText()!=null) {
						/*aid=textField_id.getText();
						textField_aid.setText(aid);*/
	
						
						amat=textField_mat.getText();
						textField_amate.setText(amat);
	
						
						acant=textField_cant.getText();
						textField_acanti.setText(acant);
	
						
						apre=textField_precio.getText();
						textField_apre.setText(apre);
	
						btnChoice.setText("Eliminar");
					}
				}else
					JOptionPane.showMessageDialog(null, "Debes seleccionar una materia","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(240, 248, 255));
		btnActualizar.setBounds(332, 58, 103, 23);
		panel_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info.consultaMateria(table);
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(72, 209, 204));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(20, 129, 448, 97);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMateriaSeleccionada = new JLabel("Materia Seleccionada");
		lblMateriaSeleccionada.setBounds(130, 5, 128, 15);
		panel_2.add(lblMateriaSeleccionada);
		lblMateriaSeleccionada.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(184, 31, 55, 15);
		panel_2.add(lblCantidad);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*JLabel lblId = new JLabel("id");
		lblId.setBounds(21, 31, 62, 14);
		panel_2.add(lblId);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));*/
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setBounds(37, 31, 62, 14);
		panel_2.add(lblMateria);
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(338, 31, 46, 14);
		panel_2.add(lblPrecio);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBounds(10, 56, 86, 20);
		panel_2.add(textField_id);
		textField_id.setColumns(10);*/
		
		textField_mat = new JTextField();
		textField_mat.setEditable(false);
		textField_mat.setBounds(10, 56, 86, 20);
		panel_2.add(textField_mat);
		textField_mat.setColumns(10);
		
		textField_cant = new JTextField();
		textField_cant.setEditable(false);
		textField_cant.setBounds(172, 56, 86, 20);
		panel_2.add(textField_cant);
		textField_cant.setColumns(10);
		
		textField_precio = new JTextField();
		textField_precio.setEditable(false);
		textField_precio.setBounds(318, 56, 86, 20);
		panel_2.add(textField_precio);
		textField_precio.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(72, 209, 204));
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBounds(535, 11, 258, 377);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setBounds(71, 5, 36, 15);
		panel_3.add(lblDatos);
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*textField_aid = new JTextField();
		textField_aid.setEditable(false);
		textField_aid.setBounds(144, 72, 86, 20);
		panel_3.add(textField_aid);
		textField_aid.setColumns(10);*/
		
		/*JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(32, 74, 46, 14);
		panel_3.add(lblId_1);
		lblId_1.setFont(new Font("Tahoma", Font.BOLD, 12));*/
		
		JLabel lblMateria_1 = new JLabel("Materia:");
		lblMateria_1.setBounds(32, 82, 68, 14);
		panel_3.add(lblMateria_1);
		lblMateria_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField_amate = new JTextField();
		textField_amate.setEditable(false);
		textField_amate.setBounds(144, 80, 86, 20);
		panel_3.add(textField_amate);
		textField_amate.setColumns(10);
		
		textField_acanti = new JTextField();
		textField_acanti.setEditable(false);
		textField_acanti.setBounds(144, 165, 86, 20);
		panel_3.add(textField_acanti);
		textField_acanti.setColumns(10);
		
		textField_apre = new JTextField();
		textField_apre.setEditable(false);
		textField_apre.setBounds(144, 250, 86, 20);
		panel_3.add(textField_apre);
		textField_apre.setColumns(10);
		
		JLabel lblPrecio_1 = new JLabel("Precio Unitario: ");
		lblPrecio_1.setBounds(32, 252, 102, 14);
		panel_3.add(lblPrecio_1);
		lblPrecio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCantidad_1 = new JLabel("Cantidad(kg): ");
		lblCantidad_1.setBounds(32, 167, 93, 14);
		panel_3.add(lblCantidad_1);
		lblCantidad_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnChoice = new JButton("Choice");
		btnChoice.setBackground(new Color(240, 248, 255));
		btnChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnChoice.getText()=="Modificar") {
					//String eid=textField_aid.getText();
					String emate=textField_amate.getText();
					String ecant=textField_acanti.getText();
					String epre=textField_apre.getText();
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();
					int id=info.idMateria(emate);
					System.out.println(id);
					//if(id>0) {
						info.editarMateria(id, emate, Float.parseFloat(ecant), Float.parseFloat(epre));
						info.consultaMateria(table);
						//textField_aid.setText("");
						textField_amate.setText("");
						textField_acanti.setText("");
						textField_apre.setText("");
						
						
					      //table.setValueAt(eid, row, 0);
					    /*  table.setValueAt(emate, row, 0);
					      table.setValueAt(ecant, row, 1);
					      table.setValueAt(epre, row, 2);*/
					      //table.setModel(dtm);
					//}//else
						//JOptionPane.showMessageDialog(null, "Debes seleccionar una materia","Advertencia",JOptionPane.WARNING_MESSAGE);
					
					/*textField_id.setText(table.getValueAt(row,0).toString());
					textField_mat.setText(table.getValueAt(row,1).toString());
					textField_cant.setText(table.getValueAt(row ,2).toString());
					textField_precio.setText(table.getValueAt(row,3).toString());*/									
				       
				      //dtm.addRow(obj);
					
				
				      
				}else if(btnChoice.getText()=="Agregar") {
					validacion num = new validacion();
					//String id=textField_aid.getText().toString();
					String materia=textField_amate.getText().toString();
					String cantidad=textField_acanti.getText().toString();
					String precio=textField_apre.getText().toString();
					ArrayList<Object[]> id=info.search(materia);
					Object array = id.toArray();
					//for(int i=0;i<array.length;i++)
						//System.out.println(array.toString());
																	
					
					boolean flag=false;
					
						if(/*!id.equals("")&&*/!materia.equals("")&&!cantidad.equals("")&&!precio.equals("")) {
							//if(num.isNumeric(id)) {
								if(num.isNumeric(cantidad)) {
										if(num.isNumeric(precio)) {
											for(int i=0;i<id.size();i++) {
												materia=id.get(i).toString();
												flag=true;												
											}
												
											if(flag) {
												info.setMateria(materia, Float.parseFloat(cantidad), Float.parseFloat(precio));
											       info.consultaMateria(table);
											       textField_acanti.setText("");
											       textField_apre.setText("");
											       textField_amate.setText("");
											}else
												JOptionPane.showMessageDialog(null, "La materia ya existe","Advertencia",JOptionPane.WARNING_MESSAGE);
												
											
											/*DefaultTableModel dtm = (DefaultTableModel) table.getModel();
											 Object[] obj = new Object[4];
										       obj[0]= id;
										       obj[1]= materia;
										       obj[2]= cantidad;
										       obj[3]= precio;						       
										       dtm.addRow(obj);
										       table.setModel(dtm);*/
										       
									//	       textField_aid.setText("");
										     

										}else 
											JOptionPane.showMessageDialog(null, "Precio debe ser numero","Error",JOptionPane.ERROR_MESSAGE);
								}else
									JOptionPane.showMessageDialog(null, "Cantidad debe ser numero","Error",JOptionPane.ERROR_MESSAGE);
													
							/*}else
								JOptionPane.showMessageDialog(null, "Id debe ser numero");*/
						}else
							JOptionPane.showMessageDialog(null, "Debes llenar todos los campos","Advertencia",JOptionPane.WARNING_MESSAGE);
						 
						 	
																		
							

						     
							

					
					
					
																				
				}else if(btnChoice.getText()=="Eliminar") {
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					int r = table.getSelectedRow();
					//String id = textField_aid.getText();
					int id=info.idMateria(textField_amate.getText());
					if(r>=0) {
						   //int confirmar=JOptionPane.showConfirmDialog(null, 
//											           "Esta seguro que desea eliminar el registro? ","Eliminar",JOptionPane.YES_OPTION); 
						   //if(JOptionPane.OK_OPTION==confirmar) {
							info.eliminarMateria(id,table,r);

								//textField_aid.setText("");
								textField_amate.setText("");
								textField_acanti.setText("");
								textField_apre.setText("");
								
								//dtm.removeRow(r);
								textField_id.setText("");															
								textField_mat.setText("");																
								textField_cant.setText("");																
								textField_precio.setText("");
								

							//}
					
					}else
						JOptionPane.showMessageDialog(null, "Debes Seleccionar una Materia","Advertencia",JOptionPane.WARNING_MESSAGE);
					
					
				}
				
					
					
			}
		});
		btnChoice.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChoice.setBounds(71, 321, 111, 23);
		panel_3.add(btnChoice);
		//PRODUCTO
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 128, 128));
		tabbedPane.addTab("Producto", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(72, 209, 204));
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBounds(10, 11, 821, 165);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		/*JLabel lblIdproducto = new JLabel("ID_Producto:");
		lblIdproducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdproducto.setBounds(10, 11, 97, 14);
		panel_5.add(lblIdproducto);*/
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcion.setBounds(10, 90, 76, 14);
		panel_5.add(lblDescripcion);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategoria.setBounds(252, 11, 76, 14);
		panel_5.add(lblCategoria);
		
		/*JLabel lblPrecioCompra = new JLabel("Precio Compra:");
		lblPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioCompra.setBounds(252, 65, 97, 14);
		panel_5.add(lblPrecioCompra);*/
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta:");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioVenta.setBounds(252, 90, 83, 14);
		panel_5.add(lblPrecioVenta);
		
		JLabel lblFechaIngreso = new JLabel("Fecha Venta:");
		lblFechaIngreso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaIngreso.setBounds(508, 11, 97, 14);
		panel_5.add(lblFechaIngreso);
		
		/*textField_idpro = new JTextField();
		textField_idpro.setBounds(109, 9, 86, 20);
		panel_5.add(textField_idpro);
		textField_idpro.setColumns(10);*/
		
		textField_descrip = new JTextField();
		textField_descrip.setBounds(109, 88, 86, 20);
		panel_5.add(textField_descrip);
		textField_descrip.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(359, 9, 86, 20);
		/*comboBox.addItem("Concha");
		comboBox.addItem("Pastel");*/
		info.getCategoria(comboBox);
		panel_5.add(comboBox);
		
		/*textField_pcompra = new JTextField();
		textField_pcompra.setBounds(359, 63, 86, 20);
		panel_5.add(textField_pcompra);
		textField_pcompra.setColumns(10);*/
		
		textField_pventa = new JTextField();
		textField_pventa.setBounds(359, 88, 86, 20);
		panel_5.add(textField_pventa);
		textField_pventa.setColumns(10);
		
		textField_indate = new JTextField("aaaa/mm/dd");
		textField_indate.addFocusListener(new FocusAdapter() {
			
			 @Override
			    public void focusGained(FocusEvent e) {
			        if (textField_indate.getText().equals("aaaa/mm/dd")) {
			            textField_indate.setText("");
			            textField_indate.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (textField_indate.getText().isEmpty()) {
			            textField_indate.setForeground(Color.GRAY);
			            textField_indate.setText("aaaa/mm/dd");
			        }
			    }
		});
		textField_indate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//System.out.println(arg0.getKeyCode());
				validacion date = new validacion();
				int tam=textField_indate.getText().length();				
				int key = arg0.getKeyCode();
				if(key!=KeyEvent.VK_ENTER&&key!=KeyEvent.VK_BACK_SPACE&&key!=KeyEvent.VK_SHIFT&&key!=KeyEvent.VK_LEFT
						&&key!=KeyEvent.VK_RIGHT)
					if(tam<10){					
						if(key!=55&&(key<48||key>57))
							JOptionPane.showMessageDialog(null, "La fecha es incorrecta");											
					}else
						JOptionPane.showMessageDialog(null, "EL tamaño de la fecha se excede");
			}
		});
		
		textField_indate.setBounds(615, 9, 86, 20);
		panel_5.add(textField_indate);
		textField_indate.setColumns(5);		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre_1.setBounds(10, 15, 76, 14);
		panel_5.add(lblNombre_1);
		
		textField_panno = new JTextField();
		textField_panno.setBounds(109, 11, 86, 20);
		panel_5.add(textField_panno);
		textField_panno.setColumns(10);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 192, 821, 95);
		panel_4.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row,col;
				if(table_1.getRowSelectionAllowed()) {
					/*System.out.println(table.getSelectedRow() + table.getSelectedColumn());
					row=table.getSelectedRow();
					col=table.getSelectedRow();
					System.out.println(table.getValueAt(row, col).toString());*/
					//System.out.println("hola");
					
						row=table_1.getSelectedRow();
						//col=table.getSelectedColumn();
						
							//row=table.getSelectedRow();
							//textField_idpro.setText(table_1.getValueAt(row,0).toString());
							textField_descrip.setText(table_1.getValueAt(row,0).toString());
							textField_panno.setText(table_1.getValueAt(row,1).toString());
							comboBox.setSelectedItem(table_1.getValueAt(row, 2).toString());
							//textField_pcompra.setText(table_1.getValueAt(row,4).toString());
							textField_pventa.setText(table_1.getValueAt(row,3).toString());
							textField_indate.setText(table_1.getValueAt(row, 4).toString());																					
					
					
				
				}
			}
		});
		table_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		table_1.setBackground(UIManager.getColor("info"));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {	
				{new Integer(1),"Pan bueno","Concha","Pastel","23","43","12/01/08"}
			},
			new String[] {
				"Descripcion","Nombre" ,"Categoria", "Precio Venta", "Fecha Venta"
			}
		));
		info.consultaProducto(table_1);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.setBackground(new Color(240, 248, 255));
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validacion num = new validacion();
				String id;
				String des=null;
				String cate=(String) comboBox.getSelectedItem();
				String pcmpra;
				String pventa;
				String fingre=null;
				String name=textField_panno.getText();
				des=textField_descrip.getText().toString();
				fingre=textField_indate.getText().toString();
				//id=textField_idpro.getText().toString();
				//pcmpra=textField_pcompra.getText().toString();
				 pventa=textField_pventa.getText().toString();
				 int id_categoria=comboBox.getSelectedIndex();
				 boolean flag=false;
				 ArrayList<Object[]> nombres=info.searchPro(name);
				if(/*!id.equals("")&&*/!name.equals("")&&!fingre.equals("")&&!pventa.equals("")) {							
						 //if(num.isNumeric(id)) {
							 if(/*num.isNumeric(pcmpra)&&*/num.isNumeric(pventa)) {
							/* DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
							 Object[] obj = new Object[7];
						       obj[0]=id;
						       obj[1]= des;
						       obj[2]= name;
						       obj[3]=cate;
						       obj[4]= pcmpra;
						       obj[5]= pventa;
						       obj[6]= fingre;					       
						     dtm.addRow(obj);
						     table_1.setModel(dtm);*/
								 for(int i=0;i<nombres.size();i++) {
									 name=nombres.get(i).toString();
									 flag=true;
								 }
								 if(flag) {
									 //info.setProducto(id_categoria, name, des, Float.parseFloat(pventa), fingre);
									 info.consultaProducto(table_1);
							     
							     //textField_idpro.setText("");
							     textField_indate.setText("");
							     textField_descrip.setText("");
							     textField_panno.setText("");
							     //textField_pcompra.setText("");
							     textField_pventa.setText("");
								 }else
									 JOptionPane.showMessageDialog(null, "El producto ya existe","Error",JOptionPane.ERROR_MESSAGE);
								 
							 }else 
								 JOptionPane.showMessageDialog(null, "Precio Compra y Precio Venta deben ser numeros");
				
 
						 /*}else
							JOptionPane.showMessageDialog(null, "id debe ser numero");*/
					
												
		
				}else {					

					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
				}
				

				
				}
		});
		btnAgregar_1.setBounds(47, 298, 89, 23);
		panel_4.add(btnAgregar_1);
		
		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBackground(new Color(240, 248, 255));
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String id=textField_idpro.getText();
				String des=textField_descrip.getText();
				String cate=(String) comboBox.getSelectedItem();
				//String pcmpra=textField_pcompra.getText();
				String pventa=textField_pventa.getText();
				String fingre=textField_indate.getText();
				String name=textField_panno.getText();				
				int id_categoria=comboBox.getSelectedIndex();
				int row = table_1.getSelectedRow();
				int id=info.idProducto(name);
				if(row>=0) {
					info.editarProducto(id, id_categoria, name, des,Float.parseFloat(pventa), fingre);
					info.consultaProducto(table_1);
					/*table_1.setValueAt(id, row, 0);
					table_1.setValueAt(des, row, 1);
					table_1.setValueAt(name, row, 2);
					table_1.setValueAt(cate, row, 3);
					table_1.setValueAt(pcmpra, row, 4);
					table_1.setValueAt(pventa, row, 5);
					table_1.setValueAt(fingre, row, 6);*/
					
					//textField_idpro.setText("");
					textField_descrip.setText("");
					textField_panno.setText("");
					//(String) comboBox.getSelectedItem();
					//textField_pcompra.setText("");
					textField_pventa.setText("");
					textField_indate.setText("");
				
				}else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnModificar_1.setBounds(245, 298, 89, 23);
		panel_4.add(btnModificar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setBackground(new Color(240, 248, 255));
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_idpro.getText();
				String des=textField_descrip.getText();
				String cate=(String) comboBox.getSelectedItem();
				String pcmpra=textField_pcompra.getText();
				String pventa=textField_pventa.getText();
				String fingre=textField_indate.getText();
				String name=textField_panno.getText();
				int row = table_1.getSelectedRow();
				if(row>=0) {					
					table_1.setValueAt(id, row, 0);
					table_1.setValueAt(des, row, 1);
					table_1.setValueAt(name, row, 2);
					table_1.setValueAt(cate, row,3);
					table_1.setValueAt(pcmpra, row, 4);
					table_1.setValueAt(pventa, row, 5);
					table_1.setValueAt(fingre, row, 6);
					
					info.eliminarProducto(Integer.parseInt(id),table_1,row);
					/*DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
					int r = table_1.getSelectedRow();
					dtm.removeRow(r);*/
					
					
					textField_idpro.setText("");
					textField_descrip.setText("");
					//(String) comboBox.getSelectedItem();
					textField_pcompra.setText("");
					textField_pventa.setText("");
					textField_indate.setText("");
				
				}else
					JOptionPane.showMessageDialog(null, "Debes seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar_1.setBounds(472, 298, 89, 23);
		panel_4.add(btnEliminar_1);
		
		JButton btnActualizar_2 = new JButton("Actualizar");
		btnActualizar_2.setBackground(new Color(240, 248, 255));
		btnActualizar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info.consultaProducto(table_1);
			}
		});
		btnActualizar_2.setBounds(650, 298, 110, 23);
		panel_4.add(btnActualizar_2);
		//USUARIOS
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 128, 128));
		tabbedPane.addTab("Usuarios", null, panel_6, null);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(72, 209, 204));
		panel_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_7.setBounds(10, 11, 821, 227);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		
/*		JLabel lblIdusuario = new JLabel("ID_Usuario:");
		lblIdusuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdusuario.setBounds(10, 11, 80, 14);
		panel_7.add(lblIdusuario);*/
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDomicilio.setBounds(10, 174, 80, 14);
		panel_7.add(lblDomicilio);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargo.setBounds(262, 60, 46, 14);
		panel_7.add(lblCargo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefono.setBounds(262, 11, 72, 14);
		panel_7.add(lblTelefono);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
		lblFechaDeIngreso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaDeIngreso.setBounds(475, 11, 120, 14);
		panel_7.add(lblFechaDeIngreso);
		
		JLabel lblFechaDespido = new JLabel("Fecha de Despido:");
		lblFechaDespido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaDespido.setBounds(475, 115, 120, 14);
		panel_7.add(lblFechaDespido);
		
		/*textFieldiduser = new JTextField();
		textFieldiduser.setBounds(140, 9, 86, 20);
		panel_7.add(textFieldiduser);
		textFieldiduser.setColumns(10);*/
		//textFieldiduser.setEditable(false);
		
		textFielddomici = new JTextField();
		textFielddomici.setBounds(140, 168, 86, 20);
		panel_7.add(textFielddomici);
		textFielddomici.setColumns(10);
		
		textFieldtel = new JTextField();
		textFieldtel.setBounds(344, 9, 86, 20);
		panel_7.add(textFieldtel);
		textFieldtel.setColumns(10);
		
		textFielddate = new JTextField("aaaa/mm/dd");
		textFielddate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int tam=textFielddate.getText().length();				
				int key = arg0.getKeyCode();
				if(key!=KeyEvent.VK_ENTER&&key!=KeyEvent.VK_BACK_SPACE&&key!=KeyEvent.VK_SHIFT&&key!=KeyEvent.VK_LEFT
						&&key!=KeyEvent.VK_RIGHT)
					if(tam<10) {
					/*if(key == KeyEvent.VK_ENTER) {
						Toolkit.getDefaultToolkit().beep();
						System.out.println("ENTER");
						
					}*/
						if(key!=55&&(key<48||key>57))
							JOptionPane.showMessageDialog(null, "La fecha es incorrecta");
					
				}else
					JOptionPane.showMessageDialog(null, "EL tamaño de la fecha se excede");
			}
		});
		textFielddate.addFocusListener(new FocusAdapter() {
			 @Override
			    public void focusGained(FocusEvent e) {
			        if (textFielddate.getText().equals("aaaa/mm/dd")) {
			            textFielddate.setText("");
			            textFielddate.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (textFielddate.getText().isEmpty()) {
			            textFielddate.setForeground(Color.GRAY);
			            textFielddate.setText("aaaa/mm/dd");
			        }
			    }
		});
		textFielddate.setBounds(605, 9, 86, 20);
		panel_7.add(textFielddate);
		textFielddate.setColumns(10);
		
		textFielddateexp = new JTextField("aaaa/mm/dd");
		textFielddateexp.addFocusListener(new FocusAdapter() {
			 @Override
			    public void focusGained(FocusEvent e) {
			        if (textFielddateexp.getText().equals("aaaa/mm/dd")) {
			            textFielddateexp.setText("");
			            textFielddateexp.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (textFielddateexp.getText().isEmpty()) {
			            textFielddateexp.setForeground(Color.GRAY);
			            textFielddateexp.setText("aaaa/mm/dd");
			        }
			    }
		});
		textFielddateexp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				validacion date = new validacion();
				int tam=textFielddateexp.getText().length();				
				int key = arg0.getKeyCode();
				Pattern pat = Pattern.compile("\\d{2}\\d{2}\\d{4}");
				if(key!=KeyEvent.VK_ENTER&&key!=KeyEvent.VK_BACK_SPACE&&key!=KeyEvent.VK_SHIFT&&key!=KeyEvent.VK_LEFT
						&&key!=KeyEvent.VK_RIGHT)
					if(tam<10) {					
						if(key!=55&&(key<48||key>57))
							if(!date.isDate(textFielddateexp.getText().toString()))
								JOptionPane.showMessageDialog(null, "La fecha es incorrecta");
							else
								JOptionPane.showMessageDialog(null, "Fecha correcta");
					
				}else
					JOptionPane.showMessageDialog(null, "EL tamaño de la fecha se excede");
			}
		});
		textFielddateexp.setBounds(605, 113, 86, 20);
		panel_7.add(textFielddateexp);
		textFielddateexp.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(344, 58, 86, 20);		
		/*comboBox_1.addItem("Administrador");
		comboBox_1.addItem("Vendedor");*/				
		//if(s) {
			info.getCargo(comboBox_1);		
		//}
		panel_7.add(comboBox_1);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(140, 9, 86, 20);
		panel_7.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 11, 66, 14);
		panel_7.add(lblNombre);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		lblApellidoPaterno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellidoPaterno.setBounds(10, 62, 120, 14);
		panel_7.add(lblApellidoPaterno);
		
		textFieldapp = new JTextField();
		textFieldapp.setBounds(140, 60, 86, 20);
		panel_7.add(textFieldapp);
		textFieldapp.setColumns(10);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellidoMaterno.setBounds(10, 115, 120, 14);
		panel_7.add(lblApellidoMaterno);
		
		textFieldapm = new JTextField();
		textFieldapm.setBounds(140, 113, 86, 20);
		panel_7.add(textFieldapm);
		textFieldapm.setColumns(10);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalario.setBounds(262, 174, 72, 14);
		panel_7.add(lblSalario);
		
		textFieldsal = new JTextField();
		textFieldsal.setBounds(344, 172, 86, 20);
		panel_7.add(textFieldsal);
		textFieldsal.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setBounds(262, 115, 80, 14);
		panel_7.add(lblContrasea);
		
		textFieldpass = new JTextField();
		textFieldpass.setBounds(344, 113, 86, 20);
		panel_7.add(textFieldpass);
		textFieldpass.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 248, 821, 102);
		panel_6.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setBackground(SystemColor.info);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row,col;
				if(table_2.getRowSelectionAllowed()) {
					/*System.out.println(table.getSelectedRow() + table.getSelectedColumn());
					row=table.getSelectedRow();
					col=table.getSelectedRow();
					System.out.println(table.getValueAt(row, col).toString());*/
					//System.out.println("hola");
					
						row=table_2.getSelectedRow();
						//col=table.getSelectedColumn();
						
							//row=table.getSelectedRow();
							//textFieldiduser.setText(table_2.getValueAt(row,0).toString());
							textFieldname.setText(table_2.getValueAt(row, 0).toString());
							textFieldapp.setText(table_2.getValueAt(row, 1).toString());
							textFieldapm.setText(table_2.getValueAt(row, 2).toString());
							
							textFielddomici.setText(table_2.getValueAt(row,3).toString());
							textFieldtel.setText(table_2.getValueAt(row,4).toString());
							comboBox_1.setSelectedItem(table_2.getValueAt(row, 5).toString());
							textFieldpass.setText(table_2.getValueAt(row, 6).toString());
							textFieldsal.setText(table_2.getValueAt(row,7).toString());
							textFielddate.setText(table_2.getValueAt(row,8).toString());
							textFielddateexp.setText(table_2.getValueAt(row,9).toString());
							

							
							
					
					
				
				}
			}
		});
		
	

		
		
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "Luis","Cordoba","Carrasco","calle #23", "222212","Administrador","password",new Integer(2000) ,"03/04/2006",null},
				
			},
			new String[] {
					"Nombre","Apellido Paterno","Apellido Materno","Domicilio", "Telefono", "Cargo","Contraseña", "Salario","Fecha Ingreso", "Fecha Despido"
			}
		));
		scrollPane_2.setViewportView(table_2);
		info.consultas(table_2);
		
		
		
		JButton btnAgregar_2 = new JButton("Agregar");
		btnAgregar_2.setBackground(new Color(240, 248, 255));
		btnAgregar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validacion num = new validacion();
				String iduser=textFieldiduser.getText().toString();
				String name=textFieldname.getText().toString();
				String app=textFieldapp.getText().toString();
				String apm=textFieldapm.getText().toString();
				String dom=textFielddomici.getText().toString();
				String tel=textFieldtel.getText().toString();
				String salario=textFieldsal.getText().toString();
				String date=textFielddate.getText().toString();
				String expdate=textFielddateexp.getText().toString();
				String pass = textFieldpass.getText().toString();
				ArrayList<Object[]> nombre = info.searchUser(name);
				int id_cargo=comboBox_1.getSelectedIndex();
				boolean flag=false;
				//System.out.println(nombre);
				if(/*!iduser.equals("")&&*/!dom.equals("")&&!tel.equals("")&&!date.equals("")&&!name.equals("")&&!app.equals("")&&!apm.equals("")&&!salario.equals("")) {
					//if(num.isNumeric(iduser)) {
						if(num.isNumeric(salario)) {
							/*DefaultTableModel dtm = (DefaultTableModel) table_2.getModel();
							 Object[] obj = new Object[11];
						       obj[0]= iduser;
						       obj[1]= name;
						       obj[2]= app;
						       obj[3]= apm;				       
						       obj[4]= dom;				       				       
						       obj[5]= tel;
						       obj[6]=comboBox_1.getSelectedItem();
						       obj[7]=pass;
						       obj[8]=salario;
						       obj[9]= date;
						       obj[10]= expdate;*/						      
						       for(int i=0;i<nombre.size();i++) {
						    	   name=nombre.get(i).toString();
						    	   flag=true;
						       }
								if(flag) {
									  //info.setUsuario(id_cargo,name, app, apm, dom, pass,tel,Float.parseFloat(salario), date, expdate);
								       info.consultas(table_2);
								     //dtm.addRow(obj);
								     //table_2.setModel(dtm);
								     
								      textFieldiduser.setText("");
								      textFieldname.setText("");
								      textFieldapp.setText("");
								      textFieldapm.setText("");
								      textFieldsal.setText("");
								      textFielddomici.setText("");
								      comboBox_1.setSelectedItem("");
								      textFieldtel.setText("");
								      textFielddate.setText("");
								      textFielddateexp.setText("");
								      textFieldpass.setText("");
								}else 
									JOptionPane.showMessageDialog(null, "El usuario ya existe","Error",JOptionPane.ERROR_MESSAGE);
						     

						}else
							JOptionPane.showMessageDialog(null, "Salario debe ser numero");
									
					/*}else
						JOptionPane.showMessageDialog(null, "Id_Usuario de ser numero");*/
					
				}else
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
			}
		});
		btnAgregar_2.setBounds(34, 384, 89, 23);
		panel_6.add(btnAgregar_2);
		
		JButton btnModificar_2 = new JButton("Modificar");
		btnModificar_2.setBackground(new Color(240, 248, 255));
		btnModificar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textFieldiduser.getText().toString();
				int id_cargo=comboBox_1.getSelectedIndex();

				String nombre=textFieldname.getText().toString();
				String app=textFieldapp.getText().toString();
				String apm=textFieldapm.getText().toString();
				String domicilio=textFielddomici.getText().toString();
				String tel=textFieldtel.getText().toString();
				String pass=textFieldpass.getText().toString();
				String salario=textFieldsal.getText().toString();
				String datein=textFielddate.getText().toString();
				String dateexp=textFielddateexp.getText().toString();
				int row = table_2.getSelectedRow();
				if(row>=0) {
					info.editarUsuario(Integer.parseInt(id), id_cargo, nombre, app, apm, domicilio, tel, pass, Float.parseFloat(salario), datein, dateexp);
					
					
					table_2.setValueAt(textFieldiduser.getText(), row, 0);
					table_2.setValueAt(nombre, row, 1);
					table_2.setValueAt(app, row, 2);
					table_2.setValueAt(apm, row, 3);
					table_2.setValueAt(domicilio, row, 4);
					table_2.setValueAt(tel, row, 5);
					table_2.setValueAt(comboBox_1.getSelectedItem(), row, 6);
					table_2.setValueAt(pass, row, 7);
					table_2.setValueAt(salario, row, 8);
					table_2.setValueAt(datein, row, 9);
					table_2.setValueAt(dateexp, row, 10);
					
					textFieldiduser.setText("");

					textFieldname.setText("");
					textFieldapp.setText("");
					textFieldapm.setText("");
					textFielddomici.setText("");
					textFieldtel.setText("");
					textFieldpass.setText("");
					textFieldsal.setText("");
					textFielddate.setText("");
					textFielddateexp.setText("");
				}else
					JOptionPane.showMessageDialog(null, "Debes Seleccionar un usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnModificar_2.setBounds(268, 384, 89, 23);
		panel_6.add(btnModificar_2);
		
		JButton btnEliminar_2 = new JButton("Eliminar");
		btnEliminar_2.setBackground(new Color(240, 248, 255));
		btnEliminar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textFieldiduser.getText().toString();

				String nombre=textFieldname.getText().toString();
				String app=textFieldapp.getText().toString();
				String apm=textFieldapm.getText().toString();
				String domicilio=textFielddomici.getText().toString();
				String tel=textFieldtel.getText().toString();
				String pass=textFieldpass.getText().toString();
				String salario=textFieldsal.getText().toString();
				String datein=textFielddate.getText().toString();
				String dateexp=textFielddateexp.getText().toString();
				int row = table_2.getSelectedRow();
				if(row>=0) {									
					table_2.setValueAt(textFieldiduser.getText(), row, 0);
					table_2.setValueAt(nombre, row, 1);
					table_2.setValueAt(app, row, 2);
					table_2.setValueAt(apm, row, 3);
					table_2.setValueAt(domicilio, row, 4);
					table_2.setValueAt(tel, row, 5);
					table_2.setValueAt(comboBox_1.getSelectedItem(), row, 6);
					table_2.setValueAt(pass, row, 7);
					table_2.setValueAt(salario, row, 8);
					table_2.setValueAt(datein, row, 9);
					table_2.setValueAt(dateexp, row, 10);
					
					info.eliminarUsuario(Integer.parseInt(id),table_2,row);
					
					/*DefaultTableModel dtm = (DefaultTableModel) table_2.getModel();
					int r = table_2.getSelectedRow();
					dtm.removeRow(r);*/
					
					textFieldiduser.setText("");

					textFieldname.setText("");
					textFieldapp.setText("");
					textFieldapm.setText("");
					textFielddomici.setText("");
					textFieldtel.setText("");
					textFieldpass.setText("");
					textFieldsal.setText("");
					textFielddate.setText("");
					textFielddateexp.setText("");
				}else
					JOptionPane.showMessageDialog(null, "Debes seleccionar un usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
				
				
				
				
			}			
		});
		btnEliminar_2.setBounds(481, 384, 89, 23);
		panel_6.add(btnEliminar_2);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.setBackground(new Color(240, 248, 255));
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info.consultas(table_2);
			}
			
		});
		btnActualizar_1.setBounds(684, 384, 105, 23);
		panel_6.add(btnActualizar_1);
		
		
	}
}

