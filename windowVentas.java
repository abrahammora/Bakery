import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class windowVentas extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;
	
	public Image imagenFondo;
	public URL fondo;	
	private JTextField textField_1;
	public float res;
	JLabel reloj2;
	JPanel panel_2;
	JComboBox comboBox_1;
	JLabel lblNewLabel;
	JLabel labelFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowVentas frame = new windowVentas();
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
	public windowVentas() {
		setTitle("VENTAS");
		sql sell = new sql();
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\abraham\\Desktop\\pan1.jpg"));
		setBackground(new Color(0, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 582);
		setLocationRelativeTo(null);
		setResizable(false);
		
	
		//this.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSalir = new JMenu("Archivo");
		menuBar.add(mnSalir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()!=null) {
					
					int op = JOptionPane.showConfirmDialog(null, "Desea Salir del Sistema","Mensaje Confirmacion",JOptionPane.YES_OPTION);
					switch(op) {
							case 0: JOptionPane.showMessageDialog(null, "Hasta luego");sell.desconectar();System.exit(0);
							break;
							case 2: JOptionPane.showMessageDialog(null, "Confirmacion Cancelada");
							break;
					}
				}
			}
		});
		mnSalir.add(mntmSalir);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCambiarDeUsuario = new JMenuItem("Cambiar de Usuario");
		mntmCambiarDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()!=null) {					
					dispose();
					panaderia ini = new panaderia();
				}
			}
		});
		mnOpciones.add(mntmCambiarDeUsuario);
		
		
		
		contentPane = new JPanel();
			
		//contentPane.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\abraham\\Desktop\\pan1.jpg"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*Container contenedor = getContentPane();
		p = new panel();
		contenedor.add(p);
		this.setVisible(true);*/
		
		JLabel reloj1 = new JLabel();
		reloj1.setBounds(930, 11, 87, 14);
		
		contentPane.add(reloj1,reloj2);
			
		reloj2 = new JLabel();
		reloj2.setBounds(1027, 11, 66, 14);
		contentPane.add(reloj2);
		
			 relojpanaderia r = new relojpanaderia(reloj1,reloj2);
		     r.start();
		JPanel panel = new JPanel();
		
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(255, 245, 238));
		panel.setBounds(37, 102, 796, 268);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombrePan = new JLabel("Nombre Pan:");
		lblNombrePan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombrePan.setBounds(10, 86, 96, 14);
		panel.add(lblNombrePan);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategoria.setBounds(10, 29, 76, 14);
		panel.add(lblCategoria);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_cat=comboBox.getSelectedIndex();
				sell.getPan(comboBox_1, id_cat);
			}
		});
		comboBox.setBounds(158, 27, 105, 20);
		sell.getCategoria(comboBox);
		panel.add(comboBox);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioUnitario.setBounds(10, 144, 96, 14);
		panel.add(lblPrecioUnitario);
		
		JLabel lblCantidadDePiezas = new JLabel("Cantidad de Piezas:");
		lblCantidadDePiezas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidadDePiezas.setBounds(10, 214, 124, 14);
		panel.add(lblCantidadDePiezas);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 142, 105, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		textArea.setBounds(325, 66, 152, 105);
		panel.add(textArea);
		
		JLabel lblTotalApagar = new JLabel("Total a Pagar");
		lblTotalApagar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalApagar.setBounds(353, 29, 86, 14);
		panel.add(lblTotalApagar);
		
		JLabel lblEfectivo = new JLabel("Efectivo:");
		lblEfectivo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEfectivo.setBounds(583, 29, 96, 14);
		panel.add(lblEfectivo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(645, 56, 141, 44);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCambio.setBounds(583, 144, 69, 14);
		panel.add(lblCambio);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(645, 169, 139, 44);
		panel.add(textArea_1);
		
		SpinnerNumberModel nm = new SpinnerNumberModel();
		JSpinner spinner = new JSpinner();
		spinner.setBounds(158, 212, 105, 20);
		
		nm.setMaximum(100);
		nm.setMinimum(0);
		spinner.setModel(nm);
		panel.add(spinner);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=(String)comboBox_1.getSelectedItem();
				int id_pan=sell.idPan(name);
				try {
					ImageIcon pan=sell.getImage(id_pan);
					labelFoto.setIcon(pan);
					lblNewLabel.setText(name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		comboBox_1.setBounds(158, 84, 105, 20);
		panel.add(comboBox_1);
		
		/*JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcion.setBounds(10, 70, 86, 14);
		panel.add(lblDescripcion);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 68, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 382, 796, 92);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row;
				if(table.getRowSelectionAllowed()) {
					/*System.out.println(table.getSelectedRow() + table.getSelectedColumn());
					row=table.getSelectedRow();
					col=table.getSelectedRow();
					System.out.println(table.getValueAt(row, col).toString());*/
					//System.out.println("hola");
					
						row=table.getSelectedRow();
						//col=table.getSelectedColumn();
						
							//row=table.getSelectedRow();
							//textField_idpro.setText(table_1.getValueAt(row,0).toString());
							//textField.setText(table.getValueAt(row,0).toString());	
							//textField_1.setText(table.getValueAt(row, 1).toString());
							comboBox.setSelectedItem(table.getValueAt(row, 1).toString());
							textField_2.setText(table.getValueAt(row,2).toString());
							//textField_pcompra.setText(table_1.getValueAt(row,4).toString());
							spinner.setValue(table.getValueAt(row,3));
							//textField_indate.setText(table_1.getValueAt(row, 4).toString());
							//dateChooser.setDate((Date) table_1.getValueAt(row, 4));
					
					
				
				}
			}
		});
		table.setBackground(new Color(253, 245, 230));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Nombre","Categoria", "Precio", "Piezas", "Fecha Venta"
			}
		));
		sell.consultaVenta(table);
		scrollPane.setViewportView(table);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validacion num = new validacion();
				String id;
				String des=null;
				String cate=(String) comboBox.getSelectedItem();
				String nom=(String) comboBox_1.getSelectedItem();
				String pventa;				
				String fingre=reloj2.getText().toString();
				
				//des=textField_1.getText().toString();											
				 pventa=textField_2.getText().toString();
				 String canti = spinner.getValue().toString();

				 int id_categoria=comboBox.getSelectedIndex();
				 int id_pan=sell.idPan(nom);
				 boolean flag=false;
				 //ArrayList<Object[]> nombres= new ArrayList<Object[]>();
				 //nombres=info.searchPro(name);				
				if(/*!name.equals("")&&*/!fingre.equals("")&&!pventa.equals("")) {													
							 if(num.isNumeric(pventa)) {																
								/*if(!sell.searchVen(name)) {
									
									     JOptionPane.showMessageDialog(null, "El producto ya existe","Error",JOptionPane.ERROR_MESSAGE);
								 }else {*/
									 		
									 sell.setVenta(id_categoria,id_pan,Float.parseFloat(pventa),Integer.parseInt(canti), fingre);
								 	//System.out.println("pan "+id_pan+" "+id_categoria);
									 sell.consultaVenta(table);			
									  res=sell.getTotal();
									  
									 textArea.setText("$"+Float.toString(res));
									 /*int idv=sell.idVenta(name);
									 sell.setProducto(id_categoria, idv);*/
						     
								     //textField_idpro.setText("");
								     //textField_indate.setText("");
								     //textField.setText("");
								     //textField_1.setText("");
								     //textField_pcompra.setText("");
								     textField_2.setText("");
								     spinner.setValue(0);
								     
							//	}
									 
								 
							 }else 
								 JOptionPane.showMessageDialog(null, "Precio Unitario Incorrecto");
				
						 																										
				}else {					

					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
				}
			}
		});
		btnAgregar.setBounds(37, 499, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRealizarVenta = new JButton("Realizar Venta");		
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Imagen img = new Imagen();
				 panel_2.add(img);
				 panel_2.repaint();*/
				
					float res=sell.getTotal();
					float change=Float.parseFloat(textField_3.getText());
					if(change>res)
						textArea_1.setText("$"+Float.toString(change-res));
					else
						JOptionPane.showMessageDialog(null, "Dinero Insuficiente","Error",JOptionPane.ERROR_MESSAGE);
				
				 
			}
		});
		btnRealizarVenta.setBounds(261, 499, 127, 23);
		contentPane.add(btnRealizarVenta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String id=textField_idpro.getText();
				//String des=textField_1.getText();
				String cate=(String) comboBox.getSelectedItem();
				//String pcmpra=textField_pcompra.getText();
				String pventa=textField_2.getText();
				String fingre=reloj2.getText();
				String name=(String) comboBox_1.getSelectedItem();		
				String cant=spinner.getValue().toString();
				// System.out.println(fingre);
				int id_pan=sell.idPan(name);
				int id_categoria=comboBox.getSelectedIndex();
				int row = table.getSelectedRow();
				
				if(row>=0) {
					int id=sell.idVenta(id_categoria);
					sell.editarVenta(id,id_categoria,id_pan,Float.parseFloat(pventa),Integer.parseInt(cant),fingre);
					sell.consultaVenta(table);
					 res=sell.getTotal();
					  
					 textArea.setText("$"+Float.toString(res));
					
					/*table_1.setValueAt(id, row, 0);
					table_1.setValueAt(des, row, 1);
					table_1.setValueAt(name, row, 2);
					table_1.setValueAt(cate, row, 3);
					table_1.setValueAt(pcmpra, row, 4);
					table_1.setValueAt(pventa, row, 5);
					table_1.setValueAt(fingre, row, 6);*/
					
					//textField_idpro.setText("");
					//textField_1.setText("");
					//textField.setText("");
					//(String) comboBox.getSelectedItem();
					//textField_pcompra.setText("");
					textField_2.setText("");
					//textField_indate.setText("");
					spinner.setValue(0);
				
				}else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(530, 499, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int name=comboBox.getSelectedIndex();
				int row = table.getSelectedRow();
				int id=sell.idVenta(name);
				if(row>=0) {					
					//table_1.setValueAt(id, row, 0);
					/*table_1.setValueAt(des, row, 0);
					table_1.setValueAt(name, row, 1);
					table_1.setValueAt(cate, row,2);
					table_1.setValueAt(pcmpra, row, 3);
					table_1.setValueAt(pventa, row, 4);
					table_1.setValueAt(fingre, row, 5);*/
					
					sell.eliminarVenta(id,table,row);
					 res=sell.getTotal();
					  
					 textArea.setText("$"+Float.toString(res));
					/*DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
					int r = table_1.getSelectedRow();
					dtm.removeRow(r);*/
					
					//textField_1.setText("");
					//String cate=(String) comboBox.getSelectedItem();
					//textField_pcompra.setText("");
					//textField.setText("");
					spinner.setValue(0);
					textField_2.setText("");
				
				
				}else
					JOptionPane.showMessageDialog(null, "Debes seleccionar un pan","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setBounds(744, 499, 89, 23);
		contentPane.add(btnEliminar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 239, 213));
		panel_1.setBounds(188, 37, 578, 46);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHornitoDePan = new JLabel("Hornito de pan \"Los Angeles\"");
		lblHornitoDePan.setBounds(88, 5, 432, 33);
		panel_1.add(lblHornitoDePan);
		lblHornitoDePan.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 24));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 248, 220));
		panel_2.setBounds(879, 140, 214, 230);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		 labelFoto = new JLabel("");
		labelFoto.setBounds(0, 0, 214, 230);
		panel_2.add(labelFoto);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 248, 220));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(879, 389, 214, 56);
		
		contentPane.add(lblNewLabel);
		
	
	
	}
}
