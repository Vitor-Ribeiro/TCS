/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.EmpresaDao;
import dao.EmpresaDaoImpl;
import dao.HibernateUtil;
import dao.SensoresDao;
import dao.SensoresDaoImpl;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.PasswordAuthentication;
import modelo.Empresa;
import modelo.Produto;
import modelo.Sensor;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.EnviarEmail;

/**
 *
 * @author User
 */
@ManagedBean
public class MensagemControle {

    private Session sessao;
    private EmpresaDao empresaDao;
    private String mensagem;
    private SensoresDao sensorDao;

    public MensagemControle() {
        empresaDao = new EmpresaDaoImpl();
        sensorDao = new SensoresDaoImpl();
    }

    public void pesquisarAlerta() {
        mensagem = "";
        try {
            Usuario usuario = UsuarioLogadoControle.getUserConected();
            sessao = HibernateUtil.abrirSessao();
            Empresa empresa = empresaDao.pesquisarMensagemAlerta(usuario.getEmpresa().getPeriodo(), sessao);
            if (!empresa.getProdutos().isEmpty()) {
                mensagem = "Ar Condicionado ligado: ";
                for (Produto produto : empresa.getProdutos()) {
                    mensagem += " - " + produto.getLocal();
                    for (Sensor sensor : produto.getSensores()) {
                        sensor.setLeitura_alerta(true);
                        sensorDao.alterar(sensor, sessao);
                    }

                }
                if (usuario.isHabilitadoEmail()) {
                    EnviarEmail enviarEmail = new EnviarEmail();
                    enviarEmail.enviarEmailMensagem(mensagem, usuario);
                }

            }

        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar alerta! " + e.getMessage());
        } finally {
            sessao.close();
        }

    }

    public String getMensagem() {
        return mensagem;
    }

}
