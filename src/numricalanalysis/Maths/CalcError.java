/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numricalanalysis.Maths;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import numricalanalysis.listeners.DigitsOnlyKeyListener;

/**
 *
 * @author mahdy
 */
public class CalcError extends JPanel {

    JLabel originalNumberLabel = new JLabel("Original number :", SwingConstants.RIGHT);
    JTextArea originalNumberArea = new JTextArea();
    JLabel approximatedNumberLabel = new JLabel("Approximated number :", SwingConstants.RIGHT);
    JTextArea approximatedNumberArea = new JTextArea();
    JPanel OP = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 50));
    JButton operate = new JButton("Get error!");
    JLabel trueErrorLabel = new JLabel("Error = ");
    JTextArea trueErrorArea = new JTextArea("0");
    JLabel absErrorLabel = new JLabel("Absolute error = ");
    JTextArea absErrorArea = new JTextArea("0");
    JLabel relativeErrorLabel = new JLabel("Relative error = ");
    JTextArea relativeErrorArea = new JTextArea("0");
    JLabel percentageErrorLabel = new JLabel("Percentage error = ");
    JTextArea percentageErrorArea = new JTextArea("0");

    public CalcError() {
        setBackground(Color.decode("#ece5dd"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        originalNumberArea.setPreferredSize(new Dimension(143, 20));
        approximatedNumberArea.setPreferredSize(new Dimension(143, 20));
        operate.setPreferredSize(new Dimension(200, 30));
        originalNumberArea.addKeyListener(new DigitsOnlyKeyListener());
        approximatedNumberArea.addKeyListener(new DigitsOnlyKeyListener());
        OP.setPreferredSize(new Dimension(700, 500));
        OP.setBackground(this.getBackground());
        operate.setBackground(Color.decode("#dcf8c6"));
        doResults();
        OP.add(trueErrorLabel);
        OP.add(trueErrorArea);
        OP.add(absErrorLabel);
        OP.add(absErrorArea);
        OP.add(relativeErrorLabel);
        OP.add(relativeErrorArea);
        OP.add(percentageErrorLabel);
        OP.add(percentageErrorArea);
        operate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                double trueVal = Double.parseDouble(originalNumberArea.getText());
                double aproxVal = Double.parseDouble(approximatedNumberArea.getText());
                trueErrorArea.setText(String.format("%f", calcError(trueVal, aproxVal)));

                absErrorArea.setText(Double.toString(absError(trueVal, aproxVal)));
                relativeErrorArea.setText(String.format("%f", relativeError(trueVal, aproxVal)));
                percentageErrorArea.setText(String.format("%f", percentageError(trueVal, aproxVal)) + "%");
            }
        });
        add(originalNumberLabel);
        add(originalNumberArea);
        add(approximatedNumberLabel);
        add(approximatedNumberArea);
        add(operate);
        add(OP);
    }

    double calcError(double trueValue, double aproxValue) {
        return trueValue - aproxValue;
    }

    double absError(double trueValue, double aproxValue) {
        return Math.abs(calcError(trueValue, aproxValue));
    }

    double relativeError(double trueValue, double aproxValue) {
        return absError(trueValue, aproxValue) / trueValue;
    }

    double percentageError(double trueValue, double aproxValue) {
        return relativeError(trueValue, aproxValue) * 100;
    }

    private void doResults() {
        trueErrorArea.setEditable(false);
        absErrorArea.setEditable(false);
        relativeErrorArea.setEditable(false);
        percentageErrorArea.setEditable(false);
        trueErrorArea.setBackground(OP.getBackground());
        absErrorArea.setBackground(OP.getBackground());
        relativeErrorArea.setBackground(OP.getBackground());
        percentageErrorArea.setBackground(OP.getBackground());
        trueErrorArea.setPreferredSize(new Dimension(550, 20));
        absErrorArea.setPreferredSize(new Dimension(550, 20));
        relativeErrorArea.setPreferredSize(new Dimension(550, 20));
        percentageErrorArea.setPreferredSize(new Dimension(520, 20));
         trueErrorArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        absErrorArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        relativeErrorArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        percentageErrorArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        absErrorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        trueErrorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        relativeErrorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        percentageErrorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
    }

}
