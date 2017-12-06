import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.naming.ldap.PagedResultsResponseControl;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class panaderia {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	String combo,combo2;
	private ButtonGroup group;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panaderia window = new panaderia();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public panaderia() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		//frame.setBounds(100, 100, 450, 300);
		frame.setSize(450,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*Container contenedor = frame.getContentPane();
		panel p = new panel();
		p.imagenFondo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\abraham\\eclipse-workspace\\SistemaPanaderia2\\src\\pan1.jpg");
		contenedor.add(p);*/
		JLabel lblBienvenidoALa = new JLabel("Bienvenido a la Panaderia");
		lblBienvenidoALa.setBounds(147, 11, 153, 14);
		frame.getContentPane().add(lblBienvenidoALa);
		
		JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAdministrador.isSelected()) {
					 combo = (String) rdbtnAdministrador.getLabel();
					JOptionPane.showMessageDialog(frame, "Escogiste: "+combo);
					
				}
			}
		});
		rdbtnAdministrador.setBounds(46, 47, 109, 23);
		frame.getContentPane().add(rdbtnAdministrador);
		
		JRadioButton rdbtnVendedor = new JRadioButton("Vendedor");
		rdbtnVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVendedor.isSelected()) {
					combo = rdbtnVendedor.getLabel();
					JOptionPane.showMessageDialog(frame, "Escogiste: "+combo);
				}
			}
		});
		rdbtnVendedor.setBounds(257, 47, 109, 23);
		frame.getContentPane().add(rdbtnVendedor);
		
		group=new ButtonGroup();
		
		group.add(rdbtnAdministrador);
		group.add(rdbtnVendedor);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(46, 107, 69, 14);
		frame.getContentPane().add(lblUsuario);
		
		textField = new JTextField("Usuario");
		textField.addFocusListener(new FocusAdapter() {
			 public void focusGained(FocusEvent e) {
			        if (textField.getText().equals("Usuario")) {
			        	textField.setForeground(Color.BLACK);
			            textField.setText("");
			            
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (textField.getText().isEmpty()) {
			            textField.setForeground(Color.GRAY);
			            textField.setText("Usuario");
			        }
			    }
		});
		textField.setBounds(190, 104, 110, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(46, 157, 69, 14);
		frame.getContentPane().add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 154, 110, 17);
		frame.getContentPane().add(passwordField);
		windowAdmin wadmin = new windowAdmin();
		windowVentas wvent = new windowVentas();
		validacion vpss = new validacion();
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBounds(190, 204, 125, 23);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sql conectar = new sql();			
				boolean estado;
				String user = textField.getText();
				String pass=vpss.MD5(passwordField.getText());
				  /*String key = "92AE31A79FEEB2A3"; //llave
			        String iv = "0123456789ABCDEF"; // vector de inicialización
			        String cleartext = passwordField.getText().toString();
			        String pass = null;
					try {
						pass = vpss.encrypt(key, iv, cleartext);
						System.out.println(pass);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				if(combo=="Administrador"){
					 estado = conectar.GetConnection("root","");
					ArrayList<Object[]> contra= conectar.getUser(user,pass);
					boolean flag=false;
					if(estado) {
//						JOptionPane.showMessageDialog(null,conectar.getUser(user, pass));
					
							for(int i=0;i<contra.size();i++) {
								user=contra.get(i).toString();
								flag=true;
							}
							if(flag) {							
								

								JOptionPane.showMessageDialog(null,"Bienvenido Al Sistema");
								wadmin.setVisible(true);
								frame.dispose();
							}else
								JOptionPane.showMessageDialog(null,"Usuario o Contraseña Incorrecta","Mensaje Error",JOptionPane.ERROR_MESSAGE);
							

						
					}else {
						JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
					}																										
					
				}else if(combo=="Vendedor") {
					 estado = conectar.GetConnection("root","");
					ArrayList<Object[]> contra= conectar.getUser(user,pass);
					boolean flag=false;
					if(estado) {
//						JOptionPane.showMessageDialog(null,conectar.getUser(user, pass));
					
							for(int i=0;i<contra.size();i++) {
								user=contra.get(i).toString();
								flag=true;
							}
							if(flag) {							
								

								JOptionPane.showMessageDialog(null,"Bienvenido Al Sistema");
								wvent.setVisible(true);
								frame.dispose();
							}else
								JOptionPane.showMessageDialog(null,"Usuario o Contraseña Incorrecta","Mensaje Error",JOptionPane.ERROR_MESSAGE);
							

						
					}else {
						JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
					}
				}
				else
					JOptionPane.showMessageDialog(null,"Debes selecionar un cargo","Error",JOptionPane.WARNING_MESSAGE);
				/*if(user=="admin" || pass=="admin") {
					JOptionPane.showMessageDialog(null, "Bienvenido Al Sistema");
					
				}*/
			}
		});
		frame.getContentPane().add(btnIniciarSesion);
		
	
		
		
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}


