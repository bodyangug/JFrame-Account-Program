package com.window;

import javax.swing.*;
import java.awt.*;

public class WindowSuccess extends JFrame {

    JButton btnOk;
    JLabel labelOk;

    public WindowSuccess() {
        JFrame jFrame = new JFrame("Оповещение");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = jFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        labelOk = new JLabel("Форма успешно сохранилась!");
        btnOk = new JButton("Ok");


        setLabelOk(layout, labelOk, contentPane, 50, 50);
        setBtnOk(layout, btnOk, contentPane, 120, 70);

        btnOk.addActionListener(e -> jFrame.dispose());

        jFrame.setSize(300, 200);
        jFrame.setVisible(true);
    }

    public void setLabelOk(SpringLayout layout, JLabel label, Container contentPane, int height, int grow) {
        contentPane.add(label);
        layout.putConstraint(SpringLayout.WEST, label, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, grow,
                SpringLayout.NORTH, contentPane);
    }

    public void setBtnOk(SpringLayout layout, JButton button, Container contentPane, int height, int grow) {
        contentPane.add(button);
        layout.putConstraint(SpringLayout.WEST, button, height,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, button, grow,
                SpringLayout.NORTH, contentPane);
    }

}
