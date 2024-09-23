package actividad6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FriendsContactGUI extends JFrame implements ActionListener {
    private JButton addButton, updateButton, deleteButton, displayButton;
    private JTextArea displayArea;

    public FriendsContactGUI() {
        super("Gestor de Contactos de Amigos");

        // Configuración de la interfaz gráfica
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Agregar Amigo");
        updateButton = new JButton("Actualizar Amigo");
        deleteButton = new JButton("Eliminar Amigo");
        displayButton = new JButton("Mostrar Amigos");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        displayButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addFriendAction();
        } else if (e.getSource() == updateButton) {
            updateFriendAction();
        } else if (e.getSource() == deleteButton) {
            deleteFriendAction();
        } else if (e.getSource() == displayButton) {
            displayFriendsAction();
        }
    }

    private void addFriendAction() {
        JTextField nameField = new JTextField();
        JTextField numberField = new JTextField();
        Object[] message = {
            "Nombre:", nameField,
            "Número:", numberField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Amigo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String numberText = numberField.getText();

            try {
                long number = Long.parseLong(numberText);
                // Llamar a la clase AddFriend
                AddFriend.main(new String[]{name, numberText});
                JOptionPane.showMessageDialog(this, "Amigo agregado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar amigo: " + ex.getMessage());
            }
        }
    }

    private void updateFriendAction() {
        JTextField nameField = new JTextField();
        JTextField numberField = new JTextField();
        Object[] message = {
            "Nombre existente:", nameField,
            "Nuevo número:", numberField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Actualizar Amigo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String numberText = numberField.getText();

            try {
                long number = Long.parseLong(numberText);
                // Llamar a la clase UpdateFriend
                UpdateFriend.main(new String[]{name, numberText});
                JOptionPane.showMessageDialog(this, "Amigo actualizado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar amigo: " + ex.getMessage());
            }
        }
    }

    private void deleteFriendAction() {
        String name = JOptionPane.showInputDialog(this, "Nombre del amigo a eliminar:");
        if (name != null && !name.trim().isEmpty()) {
            try {
                // Llamar a la clase DeleteFriend
                DeleteFriend.main(new String[]{name});
                JOptionPane.showMessageDialog(this, "Amigo eliminado exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar amigo: " + ex.getMessage());
            }
        }
    }

    private void displayFriendsAction() {
        try {
            // Redirigir la salida estándar para capturar el resultado
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(baos);
            java.io.PrintStream oldOut = System.out;
            System.setOut(ps);

            // Llamar a la clase DisplayFriends
            DisplayFriends.main(new String[]{});

            System.out.flush();
            System.setOut(oldOut);

            String output = baos.toString();
            displayArea.setText(output);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al mostrar amigos: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FriendsContactGUI();
    }
}
