/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import modelo.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class EnviarEmailTest {
    
    public EnviarEmailTest() {
    }

    @Test
    public void testEnviarEmailMensagem() {
        System.out.println("enviarEmailMensagem");
        String mens = "";
        Usuario usuario = null;
        EnviarEmail instance = new EnviarEmail();
        boolean expResult = false;
        boolean result = instance.enviarEmailMensagem(mens, usuario);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
