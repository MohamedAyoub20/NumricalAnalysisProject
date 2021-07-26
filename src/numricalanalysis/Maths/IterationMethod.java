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
import javax.swing.JButton;
import javax.swing.JTextField;
import mathexpressionparser.Operation;
import tokenizer.AbstractTreeBuilder;
import tokenizer.TokenizerException;

/**
 *
 * @author Mohamed
 */
public class IterationMethod extends EquationsPanel {

    JTextField equation;
    TextArea result;
    JButton solve;

    public IterationMethod() {
        super();
        equation = super.equationField;
        result = super.result;
        solve = super.input.jbcalculate;
        solve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String equationString = equation.getText();
                result.setText(equationString);
            }
        });

    }

    @Override
    protected String solve(String equation) {
        StringBuilder sb = new StringBuilder();
        double result = 0;
        try {
            while (true) {
                double testNumber = HelpingMethods.generateDouble();
                tokenizer.AbstractTreeBuilder treeValue = new tokenizer.AbstractTreeBuilder(equation.toLowerCase());
                double test = treeValue.getTree().getNumericResult(testNumber);
                if (Math.abs(test) < 1 && Math.abs(test) != 0) {
                    sb.append("suppose that the intial value = ").append(test).append("\n");
                    result = test;
                    System.out.println("hiooooooooooo");
                    break;
                }
               
                    
            }

        } catch (TokenizerException ex) {
            return sb.toString();
        }
        double newRoot = HelpingMethods.calculateValue(HelpingMethods.toFuctions(equation), result);
        int i = 1;
        while (true) {
            System.out.println("hi");
            double sub = newRoot - result;
            if (Math.abs(sub) <= 0.00009) {
                sb.append("hence the root equals ");
                String root = String.format("%.4f", newRoot);
                sb.append(root).append("\n");
                break;
            }
            sb.append("X").append(i).append(" = ").append(result).append("\n");
            result = newRoot;
            newRoot = HelpingMethods.calculateValue(HelpingMethods.toFuctions(equation), newRoot);
            i++;
                if (i>50) {
                    sb= new StringBuilder(("This equation can't be solved.\n"));
                    break;
                }
        }

        return sb.toString();
    }

}
