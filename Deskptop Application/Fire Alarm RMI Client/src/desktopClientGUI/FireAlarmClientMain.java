package desktopClientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FireAlarmClientMain {

	private JFrame frame;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireAlarmClientMain window = new FireAlarmClientMain();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FireAlarmClientMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		float backgroudnColorHSB[] = Color.RGBtoHSB(51,51,51,null);
		frame.getContentPane().setBackground(Color.getHSBColor(backgroudnColorHSB[0],backgroudnColorHSB[1],backgroudnColorHSB[2]));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Fire Alarm System");
		frame.setBounds(0, 0, 1366, 744);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel lblTitle = new JLabel("Fire Alarm System");
		lblTitle.setBounds(30, 0, 421, 70);
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 37));
		float titileColorHSB[] = Color.RGBtoHSB(255,69,0,null);
		frame.getContentPane().setLayout(null);
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.getHSBColor(titileColorHSB[0],titileColorHSB[1],titileColorHSB[2]));
		frame.getContentPane().add(lblTitle);
		
		JLabel lblLogin = new JLabel("Login");
		
		lblLogin.setOpaque(true);
		float loginRegeisterColorHSB[] = Color.RGBtoHSB(255,215,0,null);
		float loginRegeisterMouseFloatColorHSB[] = Color.RGBtoHSB(13,58,179,null);
		lblLogin.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0],loginRegeisterColorHSB[1],loginRegeisterColorHSB[2]));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBackground(Color.BLACK);
		lblLogin.setBounds(1166, 11, 46, 52);
		frame.getContentPane().add(lblLogin);
		
		
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogin.setForeground(Color.getHSBColor(loginRegeisterMouseFloatColorHSB[0],loginRegeisterMouseFloatColorHSB[1],loginRegeisterMouseFloatColorHSB[2]));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0],loginRegeisterColorHSB[1],loginRegeisterColorHSB[2]));
			}
		});
		
		JLabel lblRegister = new JLabel("Register");
		
		lblRegister.setOpaque(true);
		lblRegister.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0],loginRegeisterColorHSB[1],loginRegeisterColorHSB[2]));
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegister.setBackground(Color.BLACK);
		lblRegister.setBounds(1254, 11, 65, 52);
		frame.getContentPane().add(lblRegister);
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegister.setForeground(Color.getHSBColor(loginRegeisterMouseFloatColorHSB[0],loginRegeisterMouseFloatColorHSB[1],loginRegeisterMouseFloatColorHSB[2]));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegister.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0],loginRegeisterColorHSB[1],loginRegeisterColorHSB[2]));
			}
		});
		
		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 1366, 70);
		canvas.setBackground(Color.BLACK);
		frame.getContentPane().add(canvas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 86, 1330, 608);
		
		frame.getContentPane().add(scrollPane);
		
		String[] headders = { "ID","Status","Room No","Floor No","Smoke Level","CO2 Level","Last Updated" };
		DefaultTableModel tdm = new DefaultTableModel(headders, 0);
		table_1 = new JTable(tdm);
		table_1.setForeground(Color.BLACK);
		
		table_1.setDefaultEditor(Object.class, null);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		Object[] str = {"a","Active",1,2,10,8,"2018-19-21" };
		tdm.addRow(str);
		scrollPane.setViewportView(table_1);
		
	}
}
