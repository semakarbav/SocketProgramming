package socketProgramming;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel absolutePane;
	private JTextField msg_text;
	private static JTextArea msg_area;
	static Socket socket;
	static DataInputStream dis;
	static DataOutputStream dos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String msgIn="";	
			
			socket=new Socket("localhost",1251); 
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
			
			while(!msgIn.equals("Exit")) {
				msgIn=dis.readUTF();
				msg_area.setText(msg_area.getText()+"\n Server: "+msgIn);
			}
		}
		catch(Exception ex) {
			
		}
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		absolutePane = new JPanel();
		absolutePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(absolutePane);
		absolutePane.setLayout(null);
		
		JTextArea msg_area = new JTextArea();
		msg_area.setBounds(34, 10, 350, 170);
		absolutePane.add(msg_area);
		
		msg_text = new JTextField();
		msg_text.setBounds(36, 207, 280, 27);
		absolutePane.add(msg_text);
		msg_text.setColumns(10);
		
		JButton btnClient = new JButton("Sent");
		btnClient.addActionListener(new ActionListener() {
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
		btnClient.setBounds(326, 209, 89, 23);
		absolutePane.add(btnClient);
	}
}
