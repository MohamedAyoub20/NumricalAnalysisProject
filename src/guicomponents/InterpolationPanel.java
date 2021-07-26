/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import numricalanalysis.listeners.ChangeColorListner;

/**
 *
 * @author Mohamed
 */
public abstract class InterpolationPanel extends JPanel {

    JLabel xInitialLabel = new JLabel("X  Initial value : ");
    JTextField xInitialValueField = new JTextField();
    JLabel xStep = new JLabel("X Step size : ");
    JTextField xStepField = new JTextField();
    JLabel xTermsLabel = new JLabel("Number of X terms : ");
    JSlider xTermsNumberSlider = new JSlider(3, 10);
    JLabel yLabel = new JLabel("Y : ");
    JTextField yField = new JTextField();
    JPanel sol = new JPanel();
    JButton calculate = new JButton("Calculate!");
    protected String delta;
    protected JPanel titles = new JPanel();
    protected ArrayList<Double> YNumbers = new ArrayList<>();
    protected ArrayList<Double> XNumbers = new ArrayList<>();
    protected ArrayList<JPanel> panels = new ArrayList<>();

    protected int y = 0;

    public InterpolationPanel() {
        yField.setSize(700, 30);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));
        setBackground(Color.decode("#ece5dd"));
        yField.setPreferredSize(new Dimension(700, 30));
        xInitialValueField.setSize(60, 30);
        xInitialValueField.setPreferredSize(new Dimension(60, 30));
        xStepField.setSize(60, 30);
        xStepField.setPreferredSize(new Dimension(60, 30));
        sol.setBackground(Color.decode("#25d366"));
        sol.setPreferredSize(new Dimension(720, 465));
        sol.setSize(720, 465);
        sol.setBorder(new EmptyBorder(0, 10, 10, 10));
        calculate.addMouseListener(new ChangeColorListner());
        calculate.setBackground(Color.decode("#25d366"));
        calculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (xTermsNumberSlider.getValue() == yField.getText().split(",").length) {
                    buildPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "X's must equal Y's");
                }
                XNumbers = new ArrayList<>();
                YNumbers = new ArrayList<>();

            }
        });

        xTermsNumberSlider.setMajorTickSpacing(1);
        xTermsNumberSlider.setMinorTickSpacing(1);
        xTermsNumberSlider.setPaintTicks(true);
        xTermsNumberSlider.setPaintLabels(true);
        xTermsNumberSlider.setSize(new Dimension(300, 60));
        xTermsNumberSlider.setPreferredSize(new Dimension(300, 60));
        xTermsNumberSlider.setBackground(Color.decode("#ece5dd"));
        titles.setSize(new Dimension(720, 60));
        titles.setPreferredSize(new Dimension(720, 60));
        titles.setBackground(Color.decode("#25d366"));
        titles.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(xInitialLabel);
        add(xInitialValueField);
        add(xStep);
        add(xStepField);
        add(xTermsLabel);
        add(xTermsNumberSlider);
        add(yLabel);
        add(yField);
        add(calculate);
        add(titles);
        add(sol);

    }

    private void buildPanel() {
        this.YNumbers.removeAll(this.YNumbers);
        Double step = Double.valueOf(xStepField.getText());
        sol.removeAll();

        String[] numbers = yField.getText().split(",");
        for (int i = 0; i < numbers.length; i++) {
            this.YNumbers.add(Double.valueOf(numbers[i]));
            if (i == 0) {
                this.XNumbers.add(Double.valueOf(xInitialValueField.getText()));

            } else {
                this.XNumbers.add((this.XNumbers.get(i - 1) + step));
            }
            y++;
        }
        JLabel xTitle = new JLabel("X",SwingConstants.CENTER);
        xTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        xTitle.setToolTipText(xTitle.getText());
        xTitle.setOpaque(true);
        xTitle.setBackground(Color.decode("#128c7e"));
        xTitle.setForeground(Color.decode("#ece5dd"));
        JLabel yTitle = new JLabel("Y",SwingConstants.CENTER);
        yTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        yTitle.setToolTipText(yTitle.getText());
        yTitle.setOpaque(true);
        yTitle.setBackground(Color.decode("#128c7e"));
        yTitle.setForeground(Color.decode("#ece5dd"));

        titles.add(xTitle);
        titles.add(yTitle);
        this.YNumbers.trimToSize();
        for (int i = 0; i < this.YNumbers.size() - 1; i++) {
            JLabel temp = new JLabel(delta + (i + 1) + "Y",SwingConstants.CENTER);
            temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            temp.setToolTipText(temp.getText());
            temp.setOpaque(true);
            temp.setBackground(Color.decode("#128c7e"));
            temp.setForeground(Color.decode("#ece5dd"));
            titles.add(temp);

        }
        sol.setLayout(new GridLayout(1, this.YNumbers.size() + 1, 10, 10));
        titles.setLayout(new GridLayout(1, this.YNumbers.size() + 1, 10, 10));

        panels = new ArrayList<>();

        repaint();
        revalidate();

        panels.add(new JPanel(new GridLayout(this.YNumbers.size(), 1, 10, 10)));
        for (int i = 0; i < this.YNumbers.size(); i++) {
            panels.add(new JPanel(new GridLayout(this.YNumbers.size() - i, 1, 10, 10)));
        }
        for (int i = 0; i < panels.size(); i++) {
            JPanel temp = panels.get(i);
            temp.setBackground(Color.decode("#25d366"));
            //'Δ'
            sol.add(temp);
        }

        solve();
        repaint();
        revalidate();
    }

    protected abstract void solve();
}
