package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ALL_Patient_Info extends JFrame {

    ALL_Patient_Info() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(new BorderLayout());
        add(panel);

        JTable table = new JTable();
        table.setBackground(new Color(90, 156, 163));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel(new GridLayout(1, 8));
        headerPanel.setBackground(new Color(90, 156, 163));

        String[] headers = {"ID", "Number", "Name", "Gender", "Disease", "Room", "Time", "Deposit"};
        for (String header : headers) {
            JLabel label = new JLabel(header, JLabel.CENTER);
            label.setFont(new Font("Tahoma", Font.BOLD, 14));
            headerPanel.add(label);
        }

        panel.add(headerPanel, BorderLayout.NORTH);

        try {
            conn c = new conn();
            String q = "select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database connection failed!");
            e.printStackTrace();
        }

        JButton button = new JButton("BACK");
        button.setBounds(450,510,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.black);
        panel.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setUndecorated(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ALL_Patient_Info());
    }
}
