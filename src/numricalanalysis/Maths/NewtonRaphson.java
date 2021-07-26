/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numricalanalysis.Maths;

import guicomponents.EquationsPanel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Izma3iin
 */
public class NewtonRaphson extends EquationsPanel {

    double x;
    double a;
    double b;
    double testA;
    double testB;
    double hA;
    double hB;
    double helpRoot;
    JButton solve = super.input.jbcalculate;
    JTextField input = super.equationField;
    TextArea result = super.result;

    public NewtonRaphson() {
        super();
        solve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(solve(equationField.getText()));
            }
        });
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
            tokenizer.AbstractTreeBuilder tree = new tokenizer.AbstractTreeBuilder(s);
            s = tree.getTree().getDerivative().toString();
            System.out.println(HelpingMethods.calculateValue(HelpingMethods.toFuctions(s.toUpperCase()), 5));

        } catch (tokenizer.TokenizerException ex) {
            Logger.getLogger(NewtonRaphson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String solve(String text) {
        try {
            helpRoot = getTerms();
            System.out.println("helpRoot = " + helpRoot);
            tokenizer.AbstractTreeBuilder tree2 = new tokenizer.AbstractTreeBuilder(text.toLowerCase());
            mathexpressionparser.Operation drevValue = tree2.getTree().getDerivative();
            StringBuilder result = new StringBuilder();
            LinkedList<String> list = HelpingMethods.toFuctions(text);
            int i = 1;
            while (true) {
                System.out.println("hi im in the loop and helpRoot = " + helpRoot);
                double value = drevValue.getNumericResult(helpRoot);
                System.out.println("value = " + value);
                x = helpRoot - ((HelpingMethods.calculateValue(list, helpRoot)) / value);
                System.out.println("x = " + x);
                result.append("Let the x").append(i).append(" = ").append(x).append("\n");
                result.append("Then the new value equals ").append(x).append("\n");
                result.append("Then the equation value = ").append(HelpingMethods.calculateValue(list, x)).append("\n");
                result.append("____________________________________________________________\n");
                i++;
                double sub = Math.abs(x - helpRoot);
                System.out.println("sub = " + sub);
                if (sub < 0.00009) {
                    break;
                }
                helpRoot = x;
            }
            String formatedAverage = String.format("%.4f", x);

            result.append("Hence, the root is ").append(formatedAverage).append(" correct to four decimal places.\n");
            return result.toString();
        } catch (tokenizer.TokenizerException ex) {
            Logger.getLogger(NewtonRaphson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private double getTerms() {

        String equation = input.getText();
        a = HelpingMethods.generateDouble();
        b = HelpingMethods.generateDouble();
        while (true) {
            LinkedList<String> list = HelpingMethods.toFuctions(equation);
            double test = HelpingMethods.calculateValue(list, a);

            if (test > 0) {
                testA = test;
                System.out.println("hi im in a and a = " + a + " and testA = " + testA);
                break;

            }
            a = HelpingMethods.generateDouble();

        }
        while (true) {
            LinkedList<String> list = HelpingMethods.toFuctions(equation);
            double test = HelpingMethods.calculateValue(list, b);

            if (test < 0) {
                testB = test;
                System.out.println("hi im in b and b = " + b + " and testB = " + testB);

                break;
            }
            b = HelpingMethods.generateDouble();

        }

        x = (a + b) / 2;
        return x;

    }

}
