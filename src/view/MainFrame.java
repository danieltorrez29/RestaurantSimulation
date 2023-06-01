package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * MainFrame class
 * 
 * @author Daniel Torres
 */

public class MainFrame extends JFrame {

	/**
	 * Attribute declaration
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel logoLabel;
	private JLabel titleLabel;
	private JPanel whitePanel;
	private JLabel profitsLogoLabel;
	private JTextField profitsField;
	private JLabel tipsLogoLabel;
	private JTextField tipsField;
	private JButton initButton;
	private JLabel clientsLabel;
	private JTextField textField;
	private JLabel clientsLogoLabel;
	private JLabel tableLogoLabel;
	private JLabel tablesLabel;
	private JTextField tablesField;
	private JLabel waitersLogoLabel;
	private JLabel waitersLabel;
	private JTextField waitersField;
	private JButton statsButton;
	private JLabel statsLogoLabel;

	/**
	 * 
	 * Constructor method
	 * 
	 * @param listener
	 */

	public MainFrame(ActionListener listener) {
		super("Restaurant Simulation");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/dish.png"));
		initComponents(listener);
		setVisible(true);
	}

	/**
	 * 
	 * initComponents void method
	 * 
	 * @param listener
	 */

	private void initComponents(ActionListener listener) {

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 111, 28));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		whitePanel = new JPanel();
		whitePanel.setBounds(0, 352, 794, 119);
		whitePanel.setLayout(null);
		contentPane.add(whitePanel);

		profitsLogoLabel = new JLabel("");
		profitsLogoLabel.setIcon(new ImageIcon("resources/images/profits.png"));
		profitsLogoLabel.setBounds(33, 22, 70, 68);
		whitePanel.add(profitsLogoLabel);

		profitsField = new JTextField();
		profitsField.setText("$1.000.000");
		profitsField.setHorizontalAlignment(SwingConstants.CENTER);
		profitsField.setFont(new Font("Ebrima", Font.BOLD, 18));
		profitsField.setEditable(false);
		profitsField.setBackground(new Color(187, 249, 193));
		profitsField.setBounds(106, 42, 148, 32);
		whitePanel.add(profitsField);
		profitsField.setColumns(10);

		tipsLogoLabel = new JLabel("");
		tipsLogoLabel.setIcon(new ImageIcon("resources/images/tips.png"));
		tipsLogoLabel.setBounds(274, 22, 70, 68);
		whitePanel.add(tipsLogoLabel);

		tipsField = new JTextField();
		tipsField.setText("$100.000");
		tipsField.setHorizontalAlignment(SwingConstants.CENTER);
		tipsField.setFont(new Font("Ebrima", Font.BOLD, 18));
		tipsField.setEditable(false);
		tipsField.setColumns(10);
		tipsField.setBackground(new Color(217, 217, 217));
		tipsField.setBounds(344, 42, 148, 32);
		whitePanel.add(tipsField);

		initButton = new JButton("Iniciar");
		initButton.setForeground(new Color(255, 255, 255));
		initButton.setFont(new Font("Ebrima", Font.BOLD, 22));
		initButton.setBackground(new Color(255, 111, 28));
		initButton.setBounds(580, 42, 156, 32);
		initButton.setFocusPainted(false);
		initButton.setBorderPainted(false);
		initButton.addActionListener(listener);
		initButton.setActionCommand("Iniciar");
		whitePanel.add(initButton);

		titleLabel = new JLabel("MONTECHEF");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("Ebrima", Font.BOLD, 38));
		titleLabel.setBounds(57, 29, 239, 44);
		contentPane.add(titleLabel);

		logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("resources/images/logo.png"));
		logoLabel.setBounds(70, 96, 239, 213);
		contentPane.add(logoLabel);

		clientsLabel = new JLabel("Clientes Totales");
		clientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clientsLabel.setForeground(Color.WHITE);
		clientsLabel.setFont(new Font("Ebrima", Font.BOLD, 20));
		clientsLabel.setBounds(409, 77, 161, 32);
		contentPane.add(clientsLabel);

		textField = new JTextField();
		textField.setText("$1.000.000");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Ebrima", Font.BOLD, 18));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(581, 78, 148, 32);
		contentPane.add(textField);

		clientsLogoLabel = new JLabel("");
		clientsLogoLabel.setIcon(new ImageIcon("resources/images/clients.png"));
		clientsLogoLabel.setBounds(340, 53, 70, 68);
		contentPane.add(clientsLogoLabel);

		tableLogoLabel = new JLabel("");
		tableLogoLabel.setIcon(new ImageIcon("resources/images/table.png"));
		tableLogoLabel.setBounds(340, 116, 70, 68);
		contentPane.add(tableLogoLabel);

		tablesLabel = new JLabel("Mesas");
		tablesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tablesLabel.setForeground(Color.WHITE);
		tablesLabel.setFont(new Font("Ebrima", Font.BOLD, 20));
		tablesLabel.setBounds(409, 133, 161, 32);
		contentPane.add(tablesLabel);

		tablesField = new JTextField();
		tablesField.setText("$1.000.000");
		tablesField.setHorizontalAlignment(SwingConstants.CENTER);
		tablesField.setFont(new Font("Ebrima", Font.BOLD, 18));
		tablesField.setEditable(false);
		tablesField.setColumns(10);
		tablesField.setBackground(Color.WHITE);
		tablesField.setBounds(581, 133, 148, 32);
		contentPane.add(tablesField);

		waitersLogoLabel = new JLabel("");
		waitersLogoLabel.setIcon(new ImageIcon("resources/images/waiters.png"));
		waitersLogoLabel.setBounds(340, 176, 70, 68);
		contentPane.add(waitersLogoLabel);

		waitersLabel = new JLabel("Meseros");
		waitersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		waitersLabel.setForeground(Color.WHITE);
		waitersLabel.setFont(new Font("Ebrima", Font.BOLD, 20));
		waitersLabel.setBounds(409, 191, 161, 32);
		contentPane.add(waitersLabel);

		waitersField = new JTextField();
		waitersField.setText("$1.000.000");
		waitersField.setHorizontalAlignment(SwingConstants.CENTER);
		waitersField.setFont(new Font("Ebrima", Font.BOLD, 18));
		waitersField.setEditable(false);
		waitersField.setColumns(10);
		waitersField.setBackground(Color.WHITE);
		waitersField.setBounds(581, 191, 148, 32);
		contentPane.add(waitersField);

		statsButton = new JButton("Estadísticas");
		statsButton.addActionListener(listener);
		statsButton.setForeground(new Color(0, 0, 0));
		statsButton.setFont(new Font("Ebrima", Font.BOLD, 22));
		statsButton.setFocusPainted(false);
		statsButton.setBorderPainted(false);
		statsButton.setBackground(new Color(255, 255, 255));
		statsButton.setActionCommand("Estadísticas");
		statsButton.setBounds(568, 277, 169, 32);
		contentPane.add(statsButton);

		statsLogoLabel = new JLabel("");
		statsLogoLabel.setIcon(new ImageIcon("resources/images/stats.png"));
		statsLogoLabel.setBounds(488, 259, 70, 68);
		contentPane.add(statsLogoLabel);
	}
}