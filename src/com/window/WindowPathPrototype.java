package com.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WindowPathPrototype extends JFrame {

    private static final int TEXT_FIELD_SIZE = 15;
    private static final int TEXT_AREA_ROWS = 6;
    private static final int TEXT_AREA_COLUMNS = 20;
    private static final String SEP = System.lineSeparator();


    JLabel labelProject;
    JLabel labelDialog;
    JLabel labelFinish;
    JLabel labelDateDemonstration;
    JLabel labelDirector;
    JLabel labelDeveloper;
    JLabel labelOne;
    JLabel labelTwo;
    JLabel labelThree;
    JLabel labelFormulation;

    JTextField textFieldProject;
    JTextField textFieldDialog;
    JTextField textFieldFinish;
    JTextField textFieldDateDemonstration;
    JTextField textFieldDirector;
    JTextField textFieldDeveloper;
    JTextField textFieldOne;
    JTextField textFieldTwo;
    JTextField textFieldThree;
    JTextField textFieldFormulation;


    JTextArea textAreaFormulation;
    JScrollPane scrollFormulation;

    JButton btnCreate;
    JButton btnClose;


    public WindowPathPrototype() {
        JFrame jFrame = new JFrame("Форма постановки задачи на разработку прототипа");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = jFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        labelProject = new JLabel("Проект");
        labelDialog = new JLabel("Диалог");
        labelFinish = new JLabel("Срок сдачи работы");
        labelDateDemonstration = new JLabel("Дата демонстрации");
        labelDirector = new JLabel("Руководитель");
        labelDeveloper = new JLabel("Разработчик(и)");
        labelOne = new JLabel("1");
        labelTwo = new JLabel("2");
        labelThree = new JLabel("3");
        labelFormulation = new JLabel("Формулировка Задачи");

        textFieldProject = new JTextField(TEXT_FIELD_SIZE);
        textFieldDialog = new JTextField(TEXT_FIELD_SIZE);
        textFieldFinish = new JTextField(TEXT_FIELD_SIZE);
        textFieldDateDemonstration = new JTextField(TEXT_FIELD_SIZE);
        textFieldDirector = new JTextField(TEXT_FIELD_SIZE);
        textFieldDeveloper = new JTextField(TEXT_FIELD_SIZE);
        textFieldOne = new JTextField(TEXT_FIELD_SIZE);
        textFieldTwo = new JTextField(TEXT_FIELD_SIZE);
        textFieldThree = new JTextField(TEXT_FIELD_SIZE);
        textFieldFormulation = new JTextField(TEXT_FIELD_SIZE);

        btnCreate = new JButton("Создать документ");
        btnClose = new JButton("Закрыть форму");

        textAreaFormulation = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);

        scrollFormulation = new JScrollPane(textAreaFormulation);

        contentPane.add(labelProject);
        contentPane.add(textFieldProject);

        contentPane.add(labelDialog);
        contentPane.add(textFieldDialog);

        contentPane.add(labelFinish);
        contentPane.add(textFieldFinish);

        contentPane.add(labelDateDemonstration);
        contentPane.add(textFieldDateDemonstration);

        contentPane.add(labelDirector);
        contentPane.add(textFieldDirector);

        contentPane.add(labelDeveloper);
        contentPane.add(labelOne);
        contentPane.add(labelTwo);
        contentPane.add(labelThree);
        contentPane.add(textFieldOne);
        contentPane.add(textFieldTwo);
        contentPane.add(textFieldThree);

        contentPane.add(labelFormulation);
        contentPane.add(scrollFormulation);

        contentPane.add(btnCreate);
        contentPane.add(btnClose);

        setPosition(labelProject, textFieldProject, labelProject, 10, 20, 25, layout, contentPane);
        setPosition(labelDialog, textFieldDialog, labelProject, 10, 20, 55, layout, contentPane);
        setPosition(labelFinish, textFieldFinish, labelProject, 250, 320, 25, layout, contentPane);
        setPosition(labelDateDemonstration, textFieldDateDemonstration, labelProject, 250, 320, 55, layout, contentPane);
        setPosition(labelDirector, textFieldDirector, labelProject, 250, 320, 85, layout, contentPane);
        setPosition(labelDeveloper, textFieldDeveloper, labelProject, 250, 320, 120, layout, contentPane);
        setPositionWithScroll(labelOne, textFieldOne, labelProject, 350, 120, 320, 120, layout, contentPane);
        setPositionWithScroll(labelTwo, textFieldTwo, labelProject, 350, 145, 320, 145, layout, contentPane);
        setPositionWithScroll(labelThree, textFieldThree, labelProject, 350, 170, 320, 170, layout, contentPane);

        setPositionWithScroll(labelFormulation, scrollFormulation, labelProject, 20, 100, -40, 120, layout, contentPane);

        setPosition(btnCreate, btnClose, labelProject, 10, 120, 250, layout, contentPane);

        btnCreate.addActionListener(new CreateClick());
        btnClose.addActionListener(e -> jFrame.dispose());


        jFrame.setSize(600, 350);
        jFrame.setVisible(true);
    }


    private void setPosition(Component label, Component textField, Component labelOrigin, int height, int hieghtField, int grow, SpringLayout layout, Container contentPane) {
        layout.putConstraint(SpringLayout.WEST, label, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, textField, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textField, hieghtField,
                SpringLayout.EAST, labelOrigin);

    }

    private void setPositionWithScroll(Component label, Component textArea, Component labelOrigin, int height, int grow, int hieghtArea, int growArea, SpringLayout layout, Container contentPane) {
        layout.putConstraint(SpringLayout.WEST, label, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, textArea, growArea,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textArea, hieghtArea,
                SpringLayout.EAST, labelOrigin);

    }

    class CreateClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           boolean isOk = false;
            try {
                writeInfoToTheFile();
                isOk = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(isOk){
                new WindowSuccess();
            }
        }

        private void writeInfoToTheFile() throws IOException {
            String data = fillTextToFill();
            String time = getCurrentDate();
            File file = new File(".\\src\\Постановка задачи\\" + textFieldProject.getText() + " постановка задачи " + time + ".txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                fr.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert fr != null;
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        private String getCurrentDate() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            Date date = new Date(System.currentTimeMillis());
            return formatter.format(date);
        }
    }

    private String fillTextToFill() {
        StringBuilder text = new StringBuilder();
        text.append(labelProject.getText()).append(" : ").append(textFieldProject.getText()).append(SEP);
        text.append(labelDialog.getText()).append(" : ").append(textFieldDialog.getText()).append(SEP);
        text.append(labelFinish.getText()).append(" : ").append(textFieldFinish.getText()).append(SEP);
        text.append(labelDateDemonstration.getText()).append(" : ").append(textFieldDateDemonstration.getText()).append(SEP);
        text.append(labelDirector.getText()).append(" : ").append(textFieldDirector.getText()).append(SEP);
        text.append(labelDeveloper.getText()).append(" : ").append(SEP);
        text.append(labelOne.getText()).append(" - ").append(textFieldOne.getText()).append(SEP);
        text.append(labelTwo.getText()).append(" - ").append(textFieldTwo.getText()).append(SEP);
        text.append(labelThree.getText()).append(" - ").append(textFieldThree.getText()).append(SEP);
        text.append(labelFormulation.getText()).append(" : ").append(textAreaFormulation.getText()).append(SEP);
        return text.toString();
    }

}
