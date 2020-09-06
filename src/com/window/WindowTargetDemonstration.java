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

public class WindowTargetDemonstration extends JFrame {

    private static final int TEXT_FIELD_SIZE = 15;
    private static final int TEXT_AREA_ROWS = 5;
    private static final int TEXT_AREA_COLUMNS = 20;
    private static final String SEP = System.lineSeparator();

    JLabel labelProject;
    JLabel labelAuthor;
    JLabel labelDate;
    JLabel labelVersion;
    JLabel labelStatus;
    JLabel labelDocument;
    JLabel labelPathPrototype;
    JLabel labelNameFunction;
    JLabel labelRole;
    JLabel labelDaily;
    JLabel labelPlanWork;

    JTextField textFieldProject;
    JTextField textFieldAuthor;
    JTextField textFieldDate;
    JTextField textFieldVersion;
    JTextField textFieldStatus;
    JTextField textFieldDocument;
    JTextField textFieldPathPrototype;
    JTextField textFieldNameFunction;
    JTextField textFieldRole;

    JTextArea textAreaDaily;
    JTextArea textAreaPlanWork;

    JScrollPane scrollDaily;
    JScrollPane scrollPanWork;

    JButton btnCreate;
    JButton btnClose;


    public WindowTargetDemonstration() {
        JFrame jFrame = new JFrame("Форма Документа Целей Демонстрации Прототипа");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Список компонентов формы
        Container contentPane = jFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        labelProject = new JLabel("Проект");
        labelAuthor = new JLabel("Автор");
        labelDate = new JLabel("Дата");
        labelVersion = new JLabel("Версия");
        labelStatus = new JLabel("Статус");
        labelDocument = new JLabel("Документ");
        labelPathPrototype = new JLabel("Путь прототипа №");
        labelNameFunction = new JLabel("Имя Функции");
        labelRole = new JLabel("Роль Пользователя");
        labelDaily = new JLabel("Повестка Дня");
        labelPlanWork = new JLabel("План работы с пользователями");

        textFieldProject = new JTextField(TEXT_FIELD_SIZE);
        textFieldAuthor = new JTextField(TEXT_FIELD_SIZE);
        textFieldDate = new JTextField(TEXT_FIELD_SIZE);
        textFieldVersion = new JTextField(TEXT_FIELD_SIZE);
        textFieldStatus = new JTextField(TEXT_FIELD_SIZE);
        textFieldDocument = new JTextField(TEXT_FIELD_SIZE);
        textFieldPathPrototype = new JTextField(TEXT_FIELD_SIZE);
        textFieldNameFunction = new JTextField(TEXT_FIELD_SIZE);
        textFieldRole = new JTextField(TEXT_FIELD_SIZE);

        textAreaDaily = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        textAreaPlanWork = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);

        scrollDaily = new JScrollPane(textAreaDaily);
        scrollPanWork = new JScrollPane(textAreaPlanWork);

        btnCreate = new JButton("Cоздать Документ");
        btnClose = new JButton("Закрыть Форму");

        contentPane.add(labelProject);
        contentPane.add(textFieldProject);

        contentPane.add(labelAuthor);
        contentPane.add(textFieldAuthor);

        contentPane.add(labelDate);
        contentPane.add(textFieldDate);

        contentPane.add(labelVersion);
        contentPane.add(textFieldVersion);

        contentPane.add(labelStatus);
        contentPane.add(textFieldStatus);

        contentPane.add(labelDocument);
        contentPane.add(textFieldDocument);

        contentPane.add(labelPathPrototype);
        contentPane.add(textFieldPathPrototype);

        contentPane.add(labelNameFunction);
        contentPane.add(textFieldNameFunction);

        contentPane.add(labelRole);
        contentPane.add(textFieldRole);

        contentPane.add(labelDaily);
        contentPane.add(scrollDaily);

        contentPane.add(labelPlanWork);
        contentPane.add(scrollPanWork);

        contentPane.add(btnCreate);
        contentPane.add(btnClose);
        setPosition(labelProject, textFieldProject, labelProject, 10, 20, 25, layout, contentPane);
        setPosition(labelAuthor, textFieldAuthor, labelProject, 10, 20, 55, layout, contentPane);
        setPosition(labelDate, textFieldDate, labelProject, 10, 20, 85, layout, contentPane);
        setPosition(labelVersion, textFieldVersion, labelProject, 10, 20, 115, layout, contentPane);
        setPosition(labelStatus, textFieldStatus, labelProject, 10, 20, 145, layout, contentPane);
        setPosition(labelDocument, textFieldDocument, labelProject, 250, 320, 25, layout, contentPane);
        setPosition(labelPathPrototype, textFieldPathPrototype, labelProject, 250, 320, 55, layout, contentPane);
        setPosition(labelNameFunction, textFieldNameFunction, labelProject, 250, 320, 85, layout, contentPane);
        setPosition(labelRole, textFieldRole, labelProject, 250, 320, 115, layout, contentPane);

        setPositionWithScroll(labelDaily, scrollDaily, labelProject, 10, 185, -40, 200, layout, contentPane);
        setPositionWithScroll(labelPlanWork, scrollPanWork, labelProject, 250, 185, 200, 200, layout, contentPane);

        setPosition(btnCreate, btnClose, labelProject, 10, 120, 300, layout, contentPane);

        btnCreate.addActionListener(new CreateClick());
        btnClose.addActionListener(e -> jFrame.dispose());

        jFrame.setSize(600, 400);
        jFrame.setVisible(true);
    }


    private void setPosition(Component label, Component textField, Component labelOrigin, int height, int heightField, int grow, SpringLayout layout, Container contentPane) {
        layout.putConstraint(SpringLayout.WEST, label, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, textField, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textField, heightField,
                SpringLayout.EAST, labelOrigin);

    }

    private void setPositionWithScroll(Component label, Component textArea, Component labelOrigin, int height, int grow, int heightArea, int growArea, SpringLayout layout, Container contentPane) {
        layout.putConstraint(SpringLayout.WEST, label, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, grow,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, textArea, growArea,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textArea, heightArea,
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
            if (isOk) {
                new WindowSuccess();
            }
        }

        private void writeInfoToTheFile() throws IOException {
            String data = fillTextToFill();
            String time = getCurrentDate();
            File file = new File(".\\src\\Демонстрация целей\\" + textFieldProject.getText() + " постановка задачи " + time + ".txt");
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
        text.append(labelAuthor.getText()).append(" : ").append(textFieldAuthor.getText()).append(SEP);
        text.append(labelDate.getText()).append(" : ").append(textFieldDate.getText()).append(SEP);
        text.append(labelVersion.getText()).append(" : ").append(textFieldVersion.getText()).append(SEP);
        text.append(labelStatus.getText()).append(" : ").append(textFieldStatus.getText()).append(SEP);
        text.append(labelDocument.getText()).append(" : ").append(textFieldDocument.getText()).append(SEP);
        text.append(labelPathPrototype.getText()).append(" : ").append(textFieldPathPrototype.getText()).append(SEP);
        text.append(labelNameFunction.getText()).append(" : ").append(textFieldNameFunction.getText()).append(SEP);
        text.append(labelRole.getText()).append(" : ").append(textFieldRole.getText()).append(SEP);
        text.append(labelDaily.getText()).append(" : ").append(textAreaDaily.getText()).append(SEP);
        text.append(labelPlanWork.getText()).append(" : ").append(textAreaDaily.getText()).append(SEP);
        return text.toString();
    }

}



