package com.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuInputData;
    JMenu menuFormat;
    JMenu menuDemonstration;
    JMenuItem targetDemonstration;
    JMenuItem pathPrototype;
    JMenuItem result;


    public Menu() {
        JFrame jFrame = new JFrame("Руководитель группы прототипирования");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = jFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");
        menuInputData = new JMenu("Входные Данные");
        menuFormat = new JMenu("Форматировать");
        menuDemonstration = new JMenu("Демонстрация");

        targetDemonstration = new JMenuItem("Цели демострации прототипа");
        pathPrototype = new JMenuItem("Пути прототипа");
        result = new JMenuItem("Протокол релузьтатов");

        menuBar.add(menuFile);
        menuBar.add(menuInputData);
        menuBar.add(menuFormat);
        menuBar.add(menuDemonstration);

        menuDemonstration.add(targetDemonstration);
        menuDemonstration.add(pathPrototype);
        menuDemonstration.add(result);

        targetDemonstration.addActionListener(new FillTargetDemostration());
        pathPrototype.addActionListener(new FillPathPrototype());
        result.addActionListener(new FillResult());

        jFrame.setJMenuBar(menuBar);

        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }

    static class FillTargetDemostration implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    new WindowTargetDemonstration();
                    return true;
                }
            };
            worker.execute();

        }
    }

    static class FillPathPrototype implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    new WindowPathPrototype();
                    return true;
                }
            };
            worker.execute();

        }
    }

    static class FillResult implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    new WindowProtocolResult();
                    return true;
                }
            };
            worker.execute();

        }
    }
}
