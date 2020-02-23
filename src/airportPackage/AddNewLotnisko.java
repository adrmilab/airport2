package airportPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewLotnisko extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel nazwa;
    private JTextField nazwatextField;
    private JLabel miasto;
    private JTextField miastotextField;
    private JLabel kraj;
    private JTextField krajtextField;

    private Main main;

    public AddNewLotnisko(Main main1) {
        this();
        main1 = main;
    }

    public AddNewLotnisko() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(250, 300);

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
                String n = "";
                String m = "";
                String k = "";
                try {
                    n = nazwatextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    m = miastotextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Miasto'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    k = krajtextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Kraj'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (n.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (m.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Miasto'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (k.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Kraj'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Lotnisko lotnisko = new Lotnisko(n, m, k);
                try {
                    int aux = main.dodajLotnisko(main.finalConn, lotnisko);

                    setVisible(false);

                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Lotnisko dodane", "Dodawanie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Nie udało się dodać lotniska", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Nie udało się dodać lotniska", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public AddNewLotnisko(Main main1, Lotnisko l) {
        main1 = main;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(250, 300);
        nazwatextField.setText(l.getNazwa());
        miastotextField.setText(l.getMiasto());
        krajtextField.setText(l.getKraj());

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
                String n = "";
                String m = "";
                String k = "";
                try {
                    n = nazwatextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    m = miastotextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Miasto'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    k = krajtextField.getText().toString();
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Kraj'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (n.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Nazwa'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (m.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Miasto'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (k.length() == 0) {
                    JOptionPane.showMessageDialog(main, "Błędna wartość pola 'Kraj'", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Lotnisko lotnisko = new Lotnisko(n, m, k);
                try {
                    int aux = main.modyfikujLotnisko(main.finalConn, l.getNazwa(), lotnisko);

                    setVisible(false);

                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Lotnisko zmodyfikowane", "Modyfikowanie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Nie udało się zmodyfikować lotniska", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Nie udało się zmodyfikować lotniska", "Błąd", JOptionPane.ERROR_MESSAGE);
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
        AddNewLotnisko dialog = new AddNewLotnisko();
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
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
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
        contentPane.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nazwa = new JLabel();
        nazwa.setText("Nazwa");
        contentPane.add(nazwa, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nazwatextField = new JTextField();
        nazwatextField.setToolTipText("Wprowadż nazwę lotniska");
        contentPane.add(nazwatextField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        miasto = new JLabel();
        miasto.setText("Miasto");
        contentPane.add(miasto, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        miastotextField = new JTextField();
        miastotextField.setToolTipText("Wprowadź miasto");
        contentPane.add(miastotextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        kraj = new JLabel();
        kraj.setText("Kraj");
        contentPane.add(kraj, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        krajtextField = new JTextField();
        krajtextField.setToolTipText("Wprowadź kraj");
        contentPane.add(krajtextField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
