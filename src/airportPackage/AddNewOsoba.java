package airportPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewOsoba extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel firstName;
    private JTextField firstNameTextField;
    private JLabel lastName;
    private JTextField lastNameTextField;
    private JLabel pesel;
    private JTextField peselEditText;
    private JLabel role;
    private JComboBox comboBoxRole;

    private Main main;

    public AddNewOsoba(Main main1) {
        this();
        main1 = main;
    }

    public AddNewOsoba() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(250, 300);
        comboBoxRole.addItem("Pilot");
        comboBoxRole.addItem("Stewardessa");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p;
                String n;
                String nn;
                try {
                    p = peselEditText.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Pesel'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (p.length() != 11) {
                    JOptionPane.showMessageDialog(main, "Pesel musi mieć 11 znaków", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    long p1 = Long.parseLong(p);
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Wartość 'Pesel' musi być numerem", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    n = firstNameTextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (n.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    nn = lastNameTextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwisko'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nn.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwisko'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Osoba osoba = new Osoba(
                        p,
                        n,
                        nn,
                        comboBoxRole.getSelectedItem().toString());
                try {
                    int aux = main.dodajOsobe(main.finalConn, osoba);
                    setVisible(false);
                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Person added succesfully", "Person added", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Error saving person", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Error saving person", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public AddNewOsoba(Main main1, Osoba os) {
        main1 = main;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(250, 300);
        comboBoxRole.addItem("Pilot");
        comboBoxRole.addItem("Stewardessa");
        comboBoxRole.setSelectedItem(os.getRola());
        firstNameTextField.setText(os.getImie());
        lastNameTextField.setText(os.getNazwisko());
        peselEditText.setText(os.getPesel());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p;
                String n;
                String nn;
                try {
                    p = peselEditText.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Pesel'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (p.length() != 11) {
                    JOptionPane.showMessageDialog(main, "Pesel musi mieć 11 znaków", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    long p1 = Long.parseLong(p);
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Wartość 'Pesel' musi być numerem", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    n = firstNameTextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (n.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    nn = lastNameTextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwisko'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nn.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwisko'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Osoba osoba = new Osoba(
                        p,
                        n,
                        nn,
                        comboBoxRole.getSelectedItem().toString());
                try {
                    int aux = main.modyfikujOsobeZZalogi(main.finalConn, os.getPesel(), osoba);
                    setVisible(false);
                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Zmodyfikowano pracownika", "Modyfikowanie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Błąd w modyfikowaniu pracownika", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Błąd w modyfikowaniu pracownika", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void onOK() {
        // add your code here
//        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddNewOsoba dialog = new AddNewOsoba();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("Zapisz");
        panel2.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Odrzuć");
        panel2.add(buttonCancel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        firstName = new JLabel();
        firstName.setText("Imię");
        contentPane.add(firstName, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameTextField = new JTextField();
        firstNameTextField.setToolTipText("Wprowadź imię");
        contentPane.add(firstNameTextField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lastName = new JLabel();
        lastName.setText("Nazwisko");
        contentPane.add(lastName, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameTextField = new JTextField();
        lastNameTextField.setToolTipText("Wprowadź nazwisko");
        contentPane.add(lastNameTextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pesel = new JLabel();
        pesel.setText("Pesel");
        contentPane.add(pesel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        peselEditText = new JTextField();
        peselEditText.setToolTipText("Wprowadż pesel");
        contentPane.add(peselEditText, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        role = new JLabel();
        role.setText("Rola");
        contentPane.add(role, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxRole = new JComboBox();
        contentPane.add(comboBoxRole, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
