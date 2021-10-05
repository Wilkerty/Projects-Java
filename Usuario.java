import javafx.animation.PauseTransition;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Gabriel Uzeda e Wilker Tyller
 * Classe responsavel pela identificacao dos usuarios, e autentificacao de senha
 * dos mesmos
 */
public class Usuario {
    private ArrayList<String> usuario = new ArrayList<>();
    private ArrayList<String> password = new ArrayList<>();
    private File arquivo;

    /**
     * Funcao responsavel por ler e armazenar os dados de entrada de um usuario
     */
    public Usuario() {
        this.arquivo = new File("usuarios/lista.protected");
        try (RandomAccessFile file = new RandomAccessFile(this.arquivo, "r")) {
            String temp = "nao nulo";
            temp=file.readLine();
            while(temp!=null){
                this.usuario.add(temp);
                temp = file.readLine();
                this.password.add(temp);
                temp = file.readLine();
            }

        }catch (IOException a){
            JOptionPane.showMessageDialog(null, a);
        }
    }


    /**
     * Funcao responsavel por retornar a autentificacao de usuario
     * @return
     */
    protected boolean autentifica() {
        String usuario;
        String password;
        JTextField user = new JTextField();
        JTextField pass = new JPasswordField();
        Object[] message = {
                "Username:", user,
                "Password:", pass
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            for(int i=0; i < this.usuario.size(); i++){
                usuario=this.usuario.get(i);
                password=this.password.get(i);
                if(usuario.equals(user.getText()) && password.equals(pass.getText())) {
                    return true;
                }
            }
        }
        return false;
    }
}

