/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

/**
 *
 * @author Usuario
 */
public class MisException extends RuntimeException{
    public MisException(){
		super("Error:\n");
    }
    public MisException(String error){
	super("Error:\n"+error);
    }
}
