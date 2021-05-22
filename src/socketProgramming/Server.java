package socketProgramming;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Server extends JFrame {

	private JPanel absolutePane;
	private JTextField msg_text;
	private static JTextArea msg_area;

	
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream dis;
	static DataOutputStream dos;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String msgIn="";	
			serverSocket=new ServerSocket(1251);
			socket=serverSocket.accept();
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
			
			while(!msgIn.equals("Exit")) {
				msgIn=dis.readUTF();
				msg_area.setText(msg_area.getText()+"\n client: "+msgIn);
			}
		}
		catch(Exception ex) {
			
		}
	
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		absolutePane = new JPanel();
		absolutePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(absolutePane);
		absolutePane.setLayout(null);
		
		JTextArea msg_area = new JTextArea();
		msg_area.setBounds(10, 11, 400, 177);
		absolutePane.add(msg_area);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 210, 315, 32);
		absolutePane.add(msg_text);
		msg_text.setColumns(10);
		
		JButton btnServer = new JButton("Sent");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String msg="";
					msg=msg_text.getText();
					dos.writeUTF(msg);
					msg_text.setText("");
				}
				catch(Exception ex) {
					System.out.println("Error");
				}
				
				
			}
		});
		btnServer.setBounds(335, 227, 89, 23);
		absolutePane.add(btnServer);
	}
}
