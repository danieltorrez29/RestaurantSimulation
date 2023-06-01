package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * StatsFrame class
 * 
 * @author Daniel Torres
 */

public class StatsFrame extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField statsField;
	private JButton plotButton;

	/**
	 * 
	 * Constructor method
	 * 
	 * @param listener
	 */

	public StatsFrame(ActionListener listener) {
		super("Restaurant Simulation");
		setSize(800, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/dish.png"));
		initComponents(listener);
		setVisible(true);
	}

	/**
	 * 
	 * initTable void method
	 */

	private void initTable() {
		Object[][] data = { { "Arroz con pollo", 50, 3.2, 500000 }, { "Frijoles", 32, 6, 200000 },
				{ "Pasta", 44, 9.4, 600000 } };
		String[] columns = { "Plato", "Cantidad Vendida", "Calificación", "Ganancias" };
		table = new JTable(data, columns);
		table.setFont(new Font("Ebrima", Font.ITALIC, 14));
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
	}

	/**
	 * 
	 * initComponents void method
	 * 
	 * @param listener
	 */

	private void initComponents(ActionListener listener) {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 785, 0 };
		gbl_contentPane.rowHeights = new int[] { 58, 118, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setContentPane(contentPane);

		initTable();

		statsField = new JTextField();
		statsField.setHorizontalAlignment(SwingConstants.CENTER);
		statsField.setText("Estadísticas");
		statsField.setFont(new Font("Ebrima", Font.BOLD, 28));
		statsField.setForeground(new Color(255, 255, 255));
		statsField.setBackground(new Color(255, 111, 28));
		statsField.setEditable(false);
		GridBagConstraints gbc_statsField = new GridBagConstraints();
		gbc_statsField.fill = GridBagConstraints.BOTH;
		gbc_statsField.insets = new Insets(0, 0, 5, 0);
		gbc_statsField.fill = GridBagConstraints.HORIZONTAL;
		gbc_statsField.gridx = 0;
		gbc_statsField.gridy = 0;
		contentPane.add(statsField, gbc_statsField);
		statsField.setColumns(10);

		plotButton = new JButton("Gráfico");
		plotButton.setForeground(new Color(255, 255, 255));
		plotButton.setFont(new Font("Ebrima", Font.BOLD, 18));
		plotButton.setBackground(new Color(255, 111, 28));
		plotButton.setBorderPainted(false);
		plotButton.setFocusPainted(false);
		plotButton.addActionListener(listener);
		plotButton.setActionCommand("Gráfico");
		GridBagConstraints gbc_plotButton = new GridBagConstraints();
		gbc_plotButton.gridx = 0;
		gbc_plotButton.gridy = 2;
		contentPane.add(plotButton, gbc_plotButton);

	}
}