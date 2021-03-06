/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathexpressionparser;

import java.util.logging.Level;
import java.util.logging.Logger;
import tokenizer2.AbstractTreeBuilder;
import tokenizer2.TokenizerException;

/**
 *
 * @author Moustafa
 */


public class Product extends BinaryOperation {

	public Product(Operation left, Operation right) {
		super(left, right);
	}

	public Operation getLeft(){
		return left;
	}
	
	public Operation getRight(){
		return right;
	}

	public String toString(){
		return "(" + left.toString() + ")*(" + right.toString() + ")";
	}

	
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product abs = (Product) o;
		return (left.equals(abs.left) && right.equals(abs.right));
	}
	
	public int hashCode(){
		return 73 * (left.hashCode() + right.hashCode());
	}

    @Override
    public Operation getDerivative() {
        return new Addition(new Product(left.getDerivative(), right)
                , new Product(left, right.getDerivative()));
        
    }

}
