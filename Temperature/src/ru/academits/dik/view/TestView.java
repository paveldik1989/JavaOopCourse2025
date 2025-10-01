package ru.academits.dik.view;

import javax.swing.*;
import java.awt.*;

public class TestView {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("MyGUI");
            frame.setSize(500, 100);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            String[] scales = {"Kelvin", "Celsius"};

            JComboBox<String> inputScale = new JComboBox<>(scales);
            JTextField inputTemperatureField = new JTextField(5);
            JComboBox<String> outputScale = new JComboBox<>(scales);


            JButton convertButton = new JButton("Конвертировать");
            JTextField outputTemperatureField = new JTextField(5);

            frame.setLayout(new FlowLayout());

            frame.add(inputScale);
            frame.add(inputTemperatureField);

            frame.add(convertButton);

            frame.add(outputScale);
            frame.add(outputTemperatureField);

            final double[] inputTemperature = new double[1];

            convertButton.addActionListener(e -> {
                try {
                    inputTemperature[0] = Double.parseDouble(inputTemperatureField.getText());
                    System.out.println(inputTemperature[0]);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,"Ошибка: неверный формат числа");
                }
            });
        });
    }
}
