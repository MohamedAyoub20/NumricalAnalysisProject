/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numricalanalysis.Maths;

import guicomponents.InterpolationPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Mohamed
 */
public class ForwardInterpolation extends InterpolationPanel {

    protected LinkedList<Double> outputNumber = new LinkedList();
    

    public ForwardInterpolation() {
        this.delta = "Δ";
    }

    @Override
    protected void solve() {
        int numberOf_Y = this.y - 1;
        for (int i = 0; i < numberOf_Y; i++) {
            if (YNumbers.get(i) < 0) {
                double sub = YNumbers.get(i + 1) + (YNumbers.get(i) * -1);
                outputNumber.add(sub);
            } else {
                double sub = YNumbers.get(i + 1) - YNumbers.get(i);
                outputNumber.add(sub);
            }
        }
        numberOf_Y--;
        int z = 0;
        while (true) {

            for (int i = 0; i < numberOf_Y; i++) {

                if (outputNumber.get(z) > 0) {
                    double sub = outputNumber.get(z + 1) - outputNumber.get(z);
                    outputNumber.add(sub);
                } else {
                    double sub = outputNumber.get(z + 1) + (outputNumber.get(z) * -1);
                    outputNumber.add(sub);
                }
                z++;
            }
            z++;
            numberOf_Y--;
            if (numberOf_Y == 0) {
                break;
            }
        }
        int y1 = this.y - 1;
        int y2 = this.y - 1;
        int y3 = 0;
        for (int i = 2; i < (y2 + 2); i++) {
            for (int j = 0; j < y1; j++) {
                JLabel temp = new JLabel(String.valueOf(outputNumber.get(y3)), SwingConstants.CENTER);
                temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
                temp.setOpaque(true);
                temp.setToolTipText(temp.getText());
                temp.setForeground(Color.decode("#ece5dd"));
                temp.setBackground(Color.decode("#128c7e"));
                panels.get(i).add(temp);
                y3++;
            }
            y1--;
        }
        if (y1 == 0) {
            this.y = 0;
        }
        this.XNumbers.trimToSize();
        for (int i = 0; i < this.XNumbers.size(); i++) {
            JLabel temp = new JLabel(String.valueOf(XNumbers.get(i)), SwingConstants.CENTER);
            temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            temp.setToolTipText(temp.getText());
            temp.setOpaque(true);
            temp.setBackground(Color.decode("#128c7e"));
            temp.setForeground(Color.decode("#ece5dd"));
            panels.get(0).add(temp);
        }
        
        for (int i = 0; i < this.XNumbers.size(); i++) {
            JLabel temp = new JLabel(String.valueOf(YNumbers.get(i)), SwingConstants.CENTER);
            temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            temp.setToolTipText(temp.getText());
            temp.setOpaque(true);
            temp.setForeground(Color.decode("#ece5dd"));
            temp.setBackground(Color.decode("#128c7e"));
            panels.get(1).add(temp);
        }

    }
}
