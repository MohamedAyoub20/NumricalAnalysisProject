/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathexpressionparser;

import static java.util.Objects.requireNonNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import tokenizer2.AbstractTreeBuilder;
import tokenizer2.TokenizerException;

/**
 *
 * @author Moustafa
 */
public abstract class BinaryOperation implements Operation {

    protected Operation left;
    protected Operation right;

    public BinaryOperation(Operation left, Operation right) {
        this.left = requireNonNull(left);
        this.right = requireNonNull(right);
    }

    public Operation getLeft() {
        return left;
    }

    public Operation getRight() {
        return right;
    }

    @Override
    public double getNumericResult(double value) {
        try {
            tokenizer2.AbstractTreeBuilder tree = new AbstractTreeBuilder(toString());
            return tree.getTree().getNumericResult(value);
        } catch (TokenizerException ex) {
            Logger.getLogger(Abs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
