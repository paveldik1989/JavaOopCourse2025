package ru.academits.temperature.dik.view;

import ru.academits.temperature.dik.controller.Controller;
import ru.academits.temperature.dik.model.temperature.*;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements View {
    private TemperatureScale[] temperatureScales;
    private Controller controller;

    @Override
    public void setTemperatureScales(TemperatureScale[] temperatureScales) {
        this.temperatureScales = temperatureScales;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverter temperatureConverter = new TemperatureConverterImp();

            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(500, 100);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setMinimumSize(new Dimension(500, 100));

            JComboBox<TemperatureScale> inputScale = new JComboBox<>(temperatureScales);
            JTextField inputTemperatureField = new JTextField(5);
            JComboBox<TemperatureScale> outputScale = new JComboBox<>(temperatureScales);
            JButton convertButton = new JButton("Convert");
            JTextField outputTemperatureField = new JTextField(5);

            frame.setLayout(new GridBagLayout());

            GridBagConstraints constraints1 = new GridBagConstraints();
            constraints1.gridx = 0;
            constraints1.gridy = 0;
            constraints1.gridheight = 1;
            constraints1.gridwidth = 2;
            frame.add(new JLabel("Исходная температура"), constraints1);

            GridBagConstraints constraints2 = new GridBagConstraints();
            constraints2.gridx = 3;
            constraints2.gridy = 0;
            constraints2.gridheight = 1;
            constraints2.gridwidth = 2;
            frame.add(new JLabel("Расчетная температура"), constraints2);

            GridBagConstraints constraints3 = new GridBagConstraints();
            constraints3.gridx = 0;
            constraints3.gridy = 1;
            constraints3.gridheight = 1;
            constraints3.gridwidth = 1;
            constraints3.fill = GridBagConstraints.BOTH;
            frame.add(inputTemperatureField, constraints3);

            GridBagConstraints constraints4 = new GridBagConstraints();
            constraints4.gridx = 1;
            constraints4.gridy = 1;
            constraints4.gridheight = 1;
            constraints4.gridwidth = 1;
            frame.add(inputScale, constraints4);

            GridBagConstraints constraints5 = new GridBagConstraints();
            constraints5.gridx = 2;
            constraints5.gridy = 1;
            constraints5.gridheight = 1;
            constraints5.gridwidth = 1;
            frame.add(convertButton, constraints5);

            GridBagConstraints constraints6 = new GridBagConstraints();
            constraints6.gridx = 3;
            constraints6.gridy = 1;
            constraints6.gridheight = 1;
            constraints6.gridwidth = 1;
            constraints6.fill = GridBagConstraints.BOTH;
            frame.add(outputTemperatureField, constraints6);
            outputTemperatureField.setEditable(false);

            GridBagConstraints constraints7 = new GridBagConstraints();
            constraints7.gridx = 4;
            constraints7.gridy = 1;
            constraints7.gridheight = 1;
            constraints7.gridwidth = 1;
            frame.add(outputScale, constraints7);

            outputScale.addActionListener(e -> controller.setOutputTemperatureScale((TemperatureScale) outputScale.getSelectedItem()));
            inputScale.addActionListener(e -> controller.setInputTemperatureScale((TemperatureScale) inputScale.getSelectedItem()));

            convertButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(inputTemperatureField.getText());
                    controller.setInputTemperature(inputTemperature);

                    double outputTemperature = controller.getOutputTemperature();

                    outputTemperatureField.setText(String.format("%.2f", outputTemperature));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: неверный формат числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}