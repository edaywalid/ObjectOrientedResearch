package View;
import Metrics.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Calculator extends JFrame{
    private JPanel mainPanel;
    private JButton calculateButton;
    private JRadioButton metrique7;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JButton calculateButton1;
    private JLabel classSelectedLabel;
    private JRadioButton metrique1;
    private JRadioButton metrique2;
    private JRadioButton metrique3;
    private JRadioButton metrique4;
    private JRadioButton metrique5;
    private JRadioButton metrique6;
    private JRadioButton metrique8;
    private JFileChooser jf;
    private FileFilter filter;
    public File f;
    private String s;
    public Set<Integer> choice= new HashSet<>();
    Calculator() {

        super();
        ImageIcon background =new ImageIcon("C:\\Users\\Mimo\\Downloads");
        JLabel labelBackground = new JLabel("",background,JLabel.CENTER);
        labelBackground.setBounds(0,0,1080,1920);
        //mainPanel.add(labelBackground);

        setContentPane(mainPanel);
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(700, 500));
        setResizable(true);
        this.setVisible(true);

        calculateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s="For the class "+ f.getName() +" :\n";
                if(choice.contains(1)) {
                    handledExceptions hd = new handledExceptions("a name");
                    s=s+"Number Of handled Exceptions: "+(hd.calculate(f.getAbsolutePath()))+"\n";

                }
                if(choice.contains(2)){
                    NumberOfImportedClasses nic=new NumberOfImportedClasses("a name");
                    s=s+"Number Of Imported Classes: "+(nic.calculate(f.getAbsolutePath()))+"\n";

                }
                if(choice.contains(3)){
                    NumberOfImportedClassesUsed nicu=new NumberOfImportedClassesUsed("a name");
                    s=s+"Number Of Imported Classes Used: "+(nicu.calculate(f.getAbsolutePath()))+"\n";

                }
                if(choice.contains(4)){
                    NumberOfInterfacesImplemented noii=new NumberOfInterfacesImplemented("a name");
                    s=s+"Number Of Interfaces Implemented: "+(noii.calculate(f.getAbsolutePath()))+"\n";

                }
                if(choice.contains(5)) {
                    numberOfUsedMethodsFromImports noumi = new numberOfUsedMethodsFromImports("a name");
                    s = s + "Number Of Used Methods From Imports: " + (noumi.calculate(f.getAbsolutePath()))+ "\n";
                }

                if(choice.contains(6)){
                    RUEMetric ru=new RUEMetric("a name");
                    s=s+"Cpu Usage: "+(ru.calculate(f.getAbsolutePath()))+"\n";
                    }
                if(choice.contains(7)){
                    WebImportCounter wic=new WebImportCounter("a name");
                    s=s+"Number Of Web Imports: "+(wic.calculate(f.getAbsolutePath()))+"\n";
                }
//                if(choice.contains(8)){
//                VariableNamingCoherence vr=new VariableNamingCoherence();
//                    s=s+"Number Of Imported Classes Used: "+(vr.Calculate(f.getAbsolutePath()))+"\n";
//                    System.out.println(vr.Calculate(f.getAbsolutePath()));
        // }
                JFrame frame = new JFrame("Result");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                JTextArea textArea = new JTextArea();
                textArea.setText(s);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setMargin(new Insets(10, 10, 10, 10));
                JScrollPane scrollPane = new JScrollPane(textArea);
                frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                frame.setVisible(true);
            }


        });
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf = new JFileChooser();
                jf.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .java files", "java");

                jf.addChoosableFileFilter(restrict);

                jf.showOpenDialog(null);
                 f = jf.getSelectedFile();
                classSelectedLabel.setText(f.getName());

                System.out.println("File Choosen is: " + f.getName());
                System.out.println("File Choosen is: " + f.getAbsolutePath());
            }
        });
        metrique1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique1.isSelected()){choice.add(1);
                    System.out.println(choice);}
                else {choice.remove(1);}
            }
        });
        metrique2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique2.isSelected()){choice.add(2);
                    System.out.println(choice);}
                else {choice.remove(2);}
            }
        });
        metrique3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique3.isSelected()){choice.add(3);
                    System.out.println(choice);}
                else {choice.remove(3);}
            }
        });
        metrique4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique4.isSelected()){choice.add(4);
                    System.out.println(choice);}
                else {choice.remove(4);}
            }
        });
        metrique5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique5.isSelected()){choice.add(5);
                    System.out.println(choice);}
                else {choice.remove(5);}
            }
        });
        metrique6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique6.isSelected()){choice.add(6);
                    System.out.println(choice);}
                else {choice.remove(6);}
            }
        });
        metrique7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique7.isSelected()){choice.add(7);
                    System.out.println(choice);}
                else {choice.remove(7);}
            }
        });
        metrique8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metrique8.isSelected()){choice.add(8);
                    System.out.println(choice);}
                else {choice.remove(8);}
            }
        });

    }



}
