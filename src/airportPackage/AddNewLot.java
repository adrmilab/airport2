package airportPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.sql.Date.valueOf;

public class AddNewLot extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel dw;
    private JTextField dwtextField;
    private JLabel dp;
    private JTextField dptextField;
    private JComboBox rodzaj;
    private JLabel brama;
    private JComboBox bramacomboBox;
    private JLabel linialotnicza;
    private JComboBox liniaLotniczacomboBox;
    private JLabel lotnisko;
    private JComboBox lotniskocomboBox;
    private JLabel samolot;
    private JComboBox samolotComboBox;

    private Main main;

    public AddNewLot(Main main1) {
        this();
        main1 = main;
    }

    public AddNewLot() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 800);

        rodzaj.addItem("Z");
        rodzaj.addItem("DO");

        List<String> bramy = main.wypiszBramyID(main.finalConn);
        for (int i = 0; i < bramy.size(); i++) {
            bramacomboBox.addItem(bramy.get(i));
        }

        List<String> linie = main.wypiszLinieLotniczeNazwy(main.finalConn);
        for (int i = 0; i < linie.size(); i++) {
            liniaLotniczacomboBox.addItem(linie.get(i));
        }

        List<String> lotniska = main.wypiszLotniskaNazwy(main.finalConn);
        for (int i = 0; i < lotniska.size(); i++) {
            lotniskocomboBox.addItem(lotniska.get(i));
        }

        List<String> samoloty = main.wypiszSamolotyID(main.finalConn);
        for (int i = 0; i < samoloty.size(); i++) {
            samolotComboBox.addItem(samoloty.get(i));
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
                int aux = 1;
                Date datap = new Date(Calendar.getInstance().getTime().getTime());
                Date dataw = new Date(Calendar.getInstance().getTime().getTime());

                try {
                    datap = valueOf(dptextField.getText().toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(main, "Niepoprawna data przylotu. Format jest YYYY-MM-DD, np. 2020-02-24", "Błąd", JOptionPane.ERROR_MESSAGE);
                    aux = -1;
                }
                try {
                    dataw = valueOf(dwtextField.getText().toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(main, "Niepoprawna data wylotu. Format jest YYYY-MM-DD, np. 2020-02-24", "Błąd", JOptionPane.ERROR_MESSAGE);
                    aux = -1;
                }
                if (aux > 0) {
                    if (datap.before(dataw)) {
                        JOptionPane.showMessageDialog(main, "Data przylotu musi być poźniejsza od daty wylotu.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        aux = -1;
                        return;
                    }
                }


                if (aux > 0) {
                    Lot lot = new Lot(
                            datap,
                            dataw,
                            rodzaj.getSelectedItem().toString(),
                            Integer.parseInt(bramacomboBox.getSelectedItem().toString()),
                            liniaLotniczacomboBox.getSelectedItem().toString(),
                            lotniskocomboBox.getSelectedItem().toString(),
                            samolotComboBox.getSelectedItem().toString());
                    try {
                        int aux2 = main.dodajLot(main.finalConn, lot);
                        setVisible(false);
                        if (aux == 1) {
                            JOptionPane.showMessageDialog(main, "Nowy lot poprawnie dodany", "Dodawanie", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(main, "Nie udało się dodać lotu", "Błąd", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(main, "Nie udało się dodać lotu", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    public AddNewLot(Main main1, Lot l) {
        main1 = main;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 800);

        rodzaj.addItem("Z");
        rodzaj.addItem("DO");

        List<String> bramy = main.wypiszBramyID(main.finalConn);
        for (int i = 0; i < bramy.size(); i++) {
            bramacomboBox.addItem(bramy.get(i));
        }

        List<String> linie = main.wypiszLinieLotniczeNazwy(main.finalConn);
        for (int i = 0; i < linie.size(); i++) {
            liniaLotniczacomboBox.addItem(linie.get(i));
        }

        List<String> lotniska = main.wypiszLotniskaNazwy(main.finalConn);
        for (int i = 0; i < lotniska.size(); i++) {
            lotniskocomboBox.addItem(lotniska.get(i));
        }

        List<String> samoloty = main.wypiszSamolotyID(main.finalConn);
        for (int i = 0; i < samoloty.size(); i++) {
            samolotComboBox.addItem(samoloty.get(i));
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


        dwtextField.setText(l.getData_wylotu().toString());
        dptextField.setText(l.getData_przylotu().toString());
        rodzaj.setSelectedItem(l.getZ_do());
        bramacomboBox.setSelectedItem(String.valueOf(l.getBrama()));
        liniaLotniczacomboBox.setSelectedItem(l.getLiniaLotnicza());
        lotniskocomboBox.setSelectedItem(l.getSkomunikowaneLotnisko());
        samolotComboBox.setSelectedItem(l.getSamolot());

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
                int aux = 1;
                Date datap = new Date(Calendar.getInstance().getTime().getTime());
                Date dataw = new Date(Calendar.getInstance().getTime().getTime());

                try {
                    datap = valueOf(dptextField.getText().toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(main, "Niepoprawna data przylotu. Format jest YYYY-MM-DD, np. 2020-02-24", "Błąd", JOptionPane.ERROR_MESSAGE);
                    aux = -1;
                }
                try {
                    dataw = valueOf(dwtextField.getText().toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(main, "Niepoprawna data wylotu. Format jest YYYY-MM-DD, np. 2020-02-24", "Błąd", JOptionPane.ERROR_MESSAGE);
                    aux = -1;
                }
                if (aux > 0) {
                    if (datap.before(dataw)) {
                        JOptionPane.showMessageDialog(main, "Data przylotu musi być poźniejsza od daty wylotu.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        aux = -1;
                        return;
                    }
                }


                if (aux > 0) {
                    Lot lot = new Lot(
                            datap,
                            dataw,
                            rodzaj.getSelectedItem().toString(),
                            Integer.parseInt(bramacomboBox.getSelectedItem().toString()),
                            liniaLotniczacomboBox.getSelectedItem().toString(),
                            lotniskocomboBox.getSelectedItem().toString(),
                            samolotComboBox.getSelectedItem().toString());
                    try {
                        aux = main.modyfikujLot(main.finalConn, l.getId_lotu(), lot);
                        setVisible(false);
                        if (aux == 1) {
                            JOptionPane.showMessageDialog(main, "Lot poprawnie zmodyfikowany", "Modyfikacja", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(main, "Nie udało się zmodyfikować lotu", "Błąd", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(main, "Nie udało się zmodyfikować lotu", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
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
        AddNewLot dialog = new AddNewLot();
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
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(15, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
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
        contentPane.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dw = new JLabel();
        dw.setText("Data wylotu");
        contentPane.add(dw, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dwtextField = new JTextField();
        dwtextField.setToolTipText("Wprowadź datę wylotu w formacie RRRR-MM-DD");
        contentPane.add(dwtextField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dp = new JLabel();
        dp.setText("Data przylotu");
        contentPane.add(dp, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Rodzaj");
        contentPane.add(label1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dptextField = new JTextField();
        dptextField.setToolTipText("Wprowadź datę wyloty w formacie RRRR-MM-DD");
        contentPane.add(dptextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        rodzaj = new JComboBox();
        contentPane.add(rodzaj, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        brama = new JLabel();
        brama.setText("Numer bramy");
        contentPane.add(brama, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bramacomboBox = new JComboBox();
        contentPane.add(bramacomboBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linialotnicza = new JLabel();
        linialotnicza.setText("Linia lotnicza");
        contentPane.add(linialotnicza, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        liniaLotniczacomboBox = new JComboBox();
        contentPane.add(liniaLotniczacomboBox, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lotnisko = new JLabel();
        lotnisko.setText("Lotnisko");
        contentPane.add(lotnisko, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lotniskocomboBox = new JComboBox();
        contentPane.add(lotniskocomboBox, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        samolot = new JLabel();
        samolot.setText("ID samolotu");
        contentPane.add(samolot, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        samolotComboBox = new JComboBox();
        contentPane.add(samolotComboBox, new com.intellij.uiDesigner.core.GridConstraints(13, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
