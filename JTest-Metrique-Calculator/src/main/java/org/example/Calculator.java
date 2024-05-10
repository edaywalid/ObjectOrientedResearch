package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

public class Calculator extends JFrame{
    private JPanel mainPanel;
    private JButton calculateButton;
    private JRadioButton metrique4RadioButton;
    private JRadioButton metrique1RadioButton;
    private JRadioButton metrique3RadioButton;
    private JRadioButton metrique7RadioButton;
    private JRadioButton metrique8RadioButton;
    private JRadioButton metrique2RadioButton;
    private JRadioButton metrique6RadioButton;
    private JRadioButton metrique5RadioButton;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JButton calculateButton1;
    private JLabel classSelectedLabel;
    private JFileChooser jf;
    private FileFilter filter;

    Calculator(){

        super();
        setContentPane(mainPanel);
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(500,500));
        setResizable(true);
        this.setVisible(true);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf = new JFileChooser();
                jf.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .java files", "java");

                jf.addChoosableFileFilter(restrict);

                jf .showOpenDialog(null);
                File f = jf.getSelectedFile();
                classSelectedLabel.setText(f.getAbsolutePath());

                System.out.println("File Choosen is: "+f.getName());
                System.out.println("File Choosen is: "+f.getAbsolutePath());
            }
        });
    }


}
