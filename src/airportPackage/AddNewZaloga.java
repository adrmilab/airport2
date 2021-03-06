package airportPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AddNewZaloga extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel idlotu;
    private JComboBox comboBox1;
    private JLabel czlonkowie;
    private JTable table1;

    private Main main;

    public AddNewZaloga(Main main1) {
        this();
        main1 = main;
    }

    public AddNewZaloga() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 600);
        List<Osoba> osoby = main.wypiszOsoby(main.finalConn);
        String[] columns = new String[]{};
        Object[][] data = new Object[][]{};
        DefaultTableModel t1;
        data = new Object[osoby.size()][5];
        columns = new String[]{
                "PESEL", "IMIĘ", "NAZWISKO", "ROLA", "ID LOTÓW"
        };
        Object[] osoba;
        for (int i = 0; i < osoby.size(); i++) {
            osoba = new Object[]{
                    osoby.get(i).getPesel(),
                    osoby.get(i).getImie(),
                    osoby.get(i).getNazwisko(),
                    osoby.get(i).getRola(),
                    osoby.get(i).getId_lotu()
            };
            data[i] = osoba;
        }
        t1 = new DefaultTableModel(data, columns);
        table1.setModel(t1);
        table1.setRowSelectionAllowed(true);

        List<String> loty = main.wypiszLotyID(main.finalConn);
        for (int i = 0; i < loty.size(); i++) {
            comboBox1.addItem(loty.get(i));
        }

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
                List<Osoba> osoby = new ArrayList<Osoba>();
                int[] wybrane = table1.getSelectedRows();
                for (int i = 0; i < wybrane.length; i++) {
                    osoby.add(new Osoba(table1.getModel().getValueAt(wybrane[i], 0).toString(),
                            table1.getModel().getValueAt(wybrane[i], 1).toString(),
                            table1.getModel().getValueAt(wybrane[i], 2).toString(),
                            table1.getModel().getValueAt(wybrane[i], 3).toString()));
                }

                Zaloga zaloga = new Zaloga(osoby);
                try {
                    int aux = main.dodajZaloge(main.finalConn, zaloga, comboBox1.getSelectedItem().toString());
                    setVisible(false);
                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Zaloga dodana", "Dodawanie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Nie udało się dodać załogi", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Nie udało się dodać załogi", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public AddNewZaloga(Main main1, int zal) {
        main1 = main;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 600);
        List<Osoba> osoby = main.wypiszOsoby(main.finalConn);
        String[] columns = new String[]{};
        Object[][] data = new Object[][]{};
        DefaultTableModel t1;
        data = new Object[osoby.size()][5];
        columns = new String[]{
                "PESEL", "IMIĘ", "NAZWISKO", "ROLA", "ID LOTÓW"
        };
        Object[] osoba;
        for (int i = 0; i < osoby.size(); i++) {
            osoba = new Object[]{
                    osoby.get(i).getPesel(),
                    osoby.get(i).getImie(),
                    osoby.get(i).getNazwisko(),
                    osoby.get(i).getRola(),
                    osoby.get(i).getId_lotu()
            };
            data[i] = osoba;
        }
        t1 = new DefaultTableModel(data, columns);
        table1.setModel(t1);
        table1.setRowSelectionAllowed(true);
        table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        List<String> loty = main.wypiszLotyID(main.finalConn);
        for (int i = 0; i < loty.size(); i++) {
            comboBox1.addItem(loty.get(i));
        }
        int first = -1;
        int last = -1;
        for (int i = 0; i < osoby.size(); i++) {
            String a = osoby.get(i).getId_lotu();

            if (a != null) {
                Integer aux = Integer.valueOf(a);
                if (aux == zal) {
                    if (first == -1) {
                        first = i;
                    }
                    last = i;
                }
            }

        }
        table1.setRowSelectionInterval(first, last);
        comboBox1.setSelectedItem(zal);

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
                List<Osoba> osoby = new ArrayList<Osoba>();
                int[] wybrane = table1.getSelectedRows();
                for (int i = 0; i < wybrane.length; i++) {
                    osoby.add(new Osoba(table1.getModel().getValueAt(wybrane[i], 0).toString(),
                            table1.getModel().getValueAt(wybrane[i], 1).toString(),
                            table1.getModel().getValueAt(wybrane[i], 2).toString(),
                            table1.getModel().getValueAt(wybrane[i], 3).toString()));
                }

                Zaloga zaloga = new Zaloga(osoby);
                try {
                    main.usunZaloge(main.finalConn, String.valueOf(zal));
                    int aux = main.dodajZaloge(main.finalConn, zaloga, comboBox1.getSelectedItem().toString());
                    setVisible(false);
                    if (aux == 1) {
                        JOptionPane.showMessageDialog(main, "Zaloga dodana", "Dodawanie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(main, "Nie udało się dodać załogi", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main, "Nie udało się dodać załogi", "Błąd", JOptionPane.ERROR_MESSAGE);
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
        AddNewZaloga dialog = new AddNewZaloga();
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
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
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
        contentPane.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        idlotu = new JLabel();
        idlotu.setText("ID lotu");
        contentPane.add(idlotu, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        contentPane.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        czlonkowie = new JLabel();
        czlonkowie.setText("Członkowie");
        contentPane.add(czlonkowie, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
