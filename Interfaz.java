import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class Interfaz {
	JFrame ventana;
	JLabel welcome,mensaje,muser,mpass;
	JTextField usuario;
	JPasswordField password;
	Dimension d,h;
	JRadioButton userad,userve,userca;
	String combo;
	JButton acceder;
	JPanel panelA;
	
	
	public void CrearVentana() {
		d = new Dimension();
		h = new Dimension();
		ventana = new JFrame("Sistema Panaderia");
		ventana.setSize(400, 300);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BoxLayout(ventana.getContentPane(),BoxLayout.Y_AXIS ));
		
		
		welcome = new JLabel("Bienvenido a la Panaderia");
		mensaje = new JLabel("Iniciar Sesion");
		userad = new JRadioButton("Administrador");
		usuario = new JTextField();
		password = new JPasswordField();
		muser = new JLabel("Usuario");
		mpass = new JLabel("Contraseña");
		acceder = new JButton("Iniciar Sesion");
		/*panelA = new JPanel();
		
		panelA.setLayout(new BoxLayout(panelA, BoxLayout.Y_AXIS));
		panelA.setBorder(new MatteBorder(6, 6, 6, 6, (Color) new Color(0, 0, 0)));
		panelA.setForeground(new Color(51, 255, 204));
		panelA.setBounds(40, 25, 400, 100);
		ventana.getContentPane().add(panelA);*/

		
		
		
		welcome.setBounds(150, 30, 154, 26);
		ventana.getContentPane().add(welcome);
		//panelA.add(welcome);
		
		
		/*mensaje.setBounds(60, 40, 290, 26);
		ventana.getContentPane().add(mensaje);*/
		//panelA.add(mensaje);
		
		
		
		userad.setBounds(100, 60, 109, 23);///Creamos la seleccion de usurios
		ventana.getContentPane().add(userad);		
		
		userad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(userad.isSelected()) {
					combo = (String) userad.getLabel();
					JOptionPane.showMessageDialog(ventana, "Escogiste: "+combo);
				}
					
			}
		});
		
		muser.setBounds(50, 100, 109, 23);
		ventana.getContentPane().add(muser);
		usuario.setBounds(150, 100, 109, 23);//usuario
		ventana.getContentPane().add(usuario);
		
		
		mpass.setBounds(50, 150, 109, 23);
		ventana.getContentPane().add(mpass);
		password.setBounds(150, 150, 109, 23);
		ventana.getContentPane().add(password);
		
		acceder.setBounds(100, 200, 120, 23);
		ventana.getContentPane().add(acceder);
		acceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(ventana, "Escogiste: "+combo);
				
				if(combo=="Administrador") {
					admin();
	
				}
			}
		});
		
		ventana.getContentPane().setLayout(null);	
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		
	}
	
	public void admin() {
		JFrame wadmin = new JFrame("Administrador");
		wadmin.setSize(1000,600);
		wadmin.setLocationRelativeTo(null);
		wadmin.setLayout(new BoxLayout(wadmin.getContentPane(),BoxLayout.Y_AXIS ));
		
		JMenu archivo,opciones;
		JMenuItem salir;
		JMenuBar menuAdmin;
		JLabel inventario,ventas;
		JButton inven,ven;
		
		
		menuAdmin = new JMenuBar();	
		menuAdmin.setBackground(new Color(0, 204, 255));
		
		archivo = new JMenu("Archivo");
		opciones = new JMenu("Opciones");				
		salir = new  JMenuItem("Salir");
		
		
		menuAdmin.add(archivo);
		menuAdmin.add(opciones);
		archivo.add(salir);
		
		/*inventario = new JLabel("Revisar y Administrar Inventario");
		inventario.setBounds(30, 100, 200, 23);
		wadmin.getContentPane().add(inventario);
		inven = new JButton("Inventario");
		inven.setBounds(250,100, 109, 23);
		wadmin.getContentPane().add(inven);
		inven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inventario();
			}
		});*/
		
		
		/*ventas = new JLabel("Revisar y Administrar Ventas");
		ventas.setBounds(30, 150, 200, 23);
		wadmin.getContentPane().add(ventas);
		ven = new JButton("Ventas");
		ven.setBounds(250,150, 109, 23);
		wadmin.getContentPane().add(ven);*/
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 10,800, 500);
		wadmin.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrolltab = new JScrollPane();
		scrolltab.setBounds(50, 0, 500, 70);
		//scrolltab.setPreferredSize(new Dimension(500, 70));
		//wadmin.getContentPane().add(scrolltab);
		panel.add(scrolltab);
		Object[][] datos= {
				{"Juan",new Integer(25), new Boolean(true)},
				{"Abraham",new Integer(21),new Boolean(true)},
				{"Luis",new Integer(21),new Boolean(true)},
		};
		String[] columnas = {"id","Materia","Cantidad","Precio"};
		DefaultTableModel dtm = new DefaultTableModel(datos,columnas);
		JTable table = new JTable(dtm);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrolltab.setViewportView(table);
		
		JLabel matesele,id,materia,cantidad,precio;
		
		matesele = new JLabel("Materia Seleccionada");
		matesele.setFont(new Font("Tahoma",Font.BOLD, 14));
		matesele.setBounds(100,100, 109,27);
		wadmin.getContentPane().add(matesele);
		panel.add(matesele);
		
		materia = new JLabel("Materia");
		materia.setFont(new Font("Tahoma",Font.BOLD, 14));
		materia.setBounds(100,150,109,27);
		wadmin.getContentPane().add(materia);
		panel.add(materia);
		
		id = new JLabel("id");
		id.setFont(new Font("Tahoma",Font.BOLD, 14));
		id.setBounds(50,150,109,27);
		wadmin.getContentPane().add(id);
		panel.add(id);
		
		cantidad = new JLabel("Cantidad");
		cantidad.setFont(new Font("Tahoma",Font.BOLD, 14));
		cantidad.setBounds(150,150,109,27);
		wadmin.getContentPane().add(cantidad);
		panel.add(cantidad);
		
		precio = new JLabel("Precio");
		precio.setFont(new Font("Tahoma",Font.BOLD, 14));
		precio.setBounds(250,150,109,27);
		wadmin.getContentPane().add(precio);
		panel.add(precio);
		

		wadmin.setJMenuBar(menuAdmin);
		wadmin.getContentPane().setLayout(null);	
		wadmin.setVisible(true);
		wadmin.setDefaultCloseOperation(wadmin.EXIT_ON_CLOSE);
	}
	
	
	/*public void inventario() {
		JFrame winven = new JFrame("Inventario");
		winven.setSize(700,400);
		winven.setLocationRelativeTo(null);
		winven.setLayout(new BoxLayout(winven.getContentPane(),BoxLayout.Y_AXIS ));
		JScrollPane scrolltab = new JScrollPane();
		scrolltab.setBounds(50, 0, 500, 70);
		//scrolltab.setPreferredSize(new Dimension(500, 70));
		winven.getContentPane().add(scrolltab);
		
		Object[][] datos= {
				{"Juan",new Integer(25), new Boolean(true)},
				{"Abraham",new Integer(21),new Boolean(true)},
				{"Luis",new Integer(21),new Boolean(true)},
		};
		String[] columnas = {"id","Materia","Cantidad","Precio"};
		DefaultTableModel dtm = new DefaultTableModel(datos,columnas);
		JTable table = new JTable(dtm);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrolltab.setViewportView(table);
		
		
		JScrollPane scrollMat = new JScrollPane();
		scrollMat.setBounds(50, 200, 500, 70);
		//scrolltab.setPreferredSize(new Dimension(500, 70));
		winven.getContentPane().add(scrollMat);
		
		Object[][] materia= {
				{"Juan",new Integer(25), new Boolean(true)},
				{"Abraham",new Integer(21),new Boolean(true)},
				{"Luis",new Integer(21),new Boolean(true)},
		};
		String[] columater= {"id","Materia","Cantidad","Precio"};
		DefaultTableModel dtmat = new DefaultTableModel(materia,columater);
		JTable tablema = new JTable(dtmat);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrollMat.setViewportView(tablema);
		
		
		winven.getContentPane().setLayout(null);	
		winven.setVisible(true);
		winven.setDefaultCloseOperation(winven.EXIT_ON_CLOSE);

	}*/
	
	public Interfaz(){
		CrearVentana();
	}
}
