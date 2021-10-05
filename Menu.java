import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Gabriel Uzeda e Wilker Tyller
 * Classe abstrata responsavel pelas funcoes que atribuem os dados a um fornecedor,
 * e de mostragem dos mesmos.
 * @since dez 2019
 * @version 1.0
 */


public class Menu {
    public static void main(String[] args){
        new Menu();
        /**
         * Funcao responsavel pela tela inicial da interface, mostrando mensagens, e coletando
         * dados, outras funcoes sao acionadas de acordo com a vontade do usuario
         */
    }

    public Menu() {
        int i = 1;
        JOptionPane.showMessageDialog(null, "Bem-vindo");
        //Menu principal
        while (i != 0) {
            i = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:\n1-Buscar Lojas \n2 Administrador \n0 Sair"));
            if (i == 1) {
                menuUsuario();
            }
            if (i == 2) {
                menuAdm();
            }
        }
    }
    /**
     * Funcao responsavel por receber as instrucoes para a opcao administrador do menu.
     * Neste sao solicitados e introduzidos dados para opcoes de cadastro, edicao e exclusao
     * de lojas.
     */
    private void menuAdm() {
        int j = 10;
        Usuario u = new Usuario();
        if (u.autentifica()) {
            while (j != 0) {
                j = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:\n1-Cadastrar Loja \n2 Alterar Loja \n0 Voltar"));
                if (j == 1) {
                    String nomeLoja = JOptionPane.showInputDialog("Nome da Loja");
                    if (nomeLoja == null || "null".equals(nomeLoja) || "".equals(nomeLoja)) {
                        JOptionPane.showMessageDialog(null, "Somente valores validos!");
                    } else {
                        String nomeLocalizacao = JOptionPane.showInputDialog("Localização");
                        Loja l = new Loja(nomeLoja, nomeLocalizacao);
                    }
                }
                if (j == 2) {
                    String path = selecionaLoja();
                    Loja l = new Loja(path);
                    int k = 1;
                    while (k != 0) {
                        k = Integer.parseInt(JOptionPane.showInputDialog(null, "O que deseja editar?\n 1 Produto \n 2 Nome \n 3 Localização \n 4 Excluir \n 0 Sair"));
                        if (k == 1) {
                            editarProduto(new Loja (path), path);
                        }
                        if (k == 2) {
                            l.setNome(JOptionPane.showInputDialog(null, "Novo nome:"));
                            l.gravarLoja(path);
                        }
                        if (k == 3) {
                            l.setLocalizacao(JOptionPane.showInputDialog(null, "Nova Localização:"));
                            l.gravarLoja(path);
                        }
                        if (k == 4){
                            l.deletaArquivo("lojas/"+l.getNome());
                            break;
                        }

                    }
                }
            }
        }
    }
    /**
     * Funcao responsavel por alterar as informacoes de um loja solicitada pelo usuario
     * @return
     */
    private String selecionaLoja() {
        File dir = new File("lojas/");
        File[] lista = dir.listFiles();
        String temp = "";
        int n;
        for (n = 0; n < lista.length; n++) {
            temp += n + 1 + " " + lista[n].getName() + "\n";
        }
        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma loja para alterar:\n" + temp)) - 1;
        //Lê arquivo e armazena dados
        String path = lista[n].getPath();
        return path;

    }
    /**
     * Funcao responsavel por editar as informacoes de um produto de acordo com as exeigencias
     * do usuario
     * @param l
     * @param path
     */
    public void editarProduto(Loja l, String path){
        String tmp = "";
        int p;
        for (p = 0; p < l.listaProdutos.size(); p++) {
            tmp += "\n0" + (p + 1) + " " + l.listaProdutos.get(p).getNomeProduto();
        }
        p = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual produto?\n" + tmp + "\n0 Novo Produto"));
        p -=1;
        if (p>0){
            l.listaProdutos.remove(p);
            l.cadastrarListaProdutos();
            l.gravarLoja(path);
        }else {
            l.cadastrarListaProdutos();
            l.gravarLoja(path);
        }
    }
    /**
     * Funcao responsavel pelo menu de usuario na parte grafica do programa.
     * Nesta, e possivel buscar informacoes sobre produtos e lojas, a partir
     * do uso da funcao de busca por nome do produto, ou, da localizacao
     */
    private void menuUsuario() {
        //Buscar produto ou loja
        String aux;
        File dir = new File("lojas/");
        File[] lista = dir.listFiles();
        int ax = Integer.parseInt(JOptionPane.showInputDialog(null, "Pesquisar por:\n 1 Nome do Produto\n 2 Localização"));
        if (ax == 1) {
            procurarPorNome(lista);
        } else {//Procura por localização
            ArrayList<Loja> listaLojas = new ArrayList<Loja>();
            aux = JOptionPane.showInputDialog(null, "Informe a Localização").toUpperCase();
            for (int n = 0; n < lista.length; n++) {
                listaLojas.add(new Loja(lista[n].getAbsolutePath()));
            }
            boolean flag = false;
            for (int l = 0; l < listaLojas.size(); l++) {
                if (aux.equals(listaLojas.get(l).getLocalizacao())) {
                    flag = true;
                    JOptionPane.showMessageDialog(null, "Nome da Loja:\n" + listaLojas.get(l).getNome() + "\nLocalização: \n" + listaLojas.get(l).getLocalizacao());
                    int list = JOptionPane.showConfirmDialog(null, "Mostrar lista de produtos?");
                    if (list == 0) {
                        for (int p = 0; p < listaLojas.get(l).listaProdutos.size(); p++) {
                            JOptionPane.showMessageDialog(null, "Nome do produto: " + listaLojas.get(l).listaProdutos.get(p).getNomeProduto()
                                    + "\nPreço do Produto: " + listaLojas.get(l).listaProdutos.get(p).getPrecoVenda());
                        }
                    }
                }
            }
            if (flag == false) JOptionPane.showMessageDialog(null, "Não encontrado!");

        }

    }
    /**
     * Funcao responsavel por fazer a busca dos dados de um produto de nome inserido pelo usuario
     * @param lista
     */
    private void procurarPorNome(File[] lista) {
        String aux = JOptionPane.showInputDialog(null, "Informe o nome do produto.").toUpperCase();
        String palavrasArquivo;
        boolean flag = false;
        for (int n = 0; n < lista.length; n++) {
            try {
                RandomAccessFile file = new RandomAccessFile(lista[n].getAbsolutePath(), "r");
                for (int j = 0; j < file.length(); j++) {
                    try {
                        palavrasArquivo = file.readLine();
                        if (aux.equals(palavrasArquivo)) {
                            flag = true;
                            Loja l = new Loja(lista[n].getAbsolutePath());
                            JOptionPane.showMessageDialog(null, "Nome da Loja:\n" + l.getNome() + "\nLocalização: \n" + l.getLocalizacao());
                            int list = JOptionPane.showConfirmDialog(null, "Mostrar lista de produtos?");
                            if (list == 0) {
                                for (int p = 0; p < l.listaProdutos.size(); p++) {
                                    JOptionPane.showMessageDialog(null, "Nome do produto: " + l.listaProdutos.get(p).getNomeProduto()
                                            + "\nPreço do Produto: " + l.listaProdutos.get(p).getPrecoVenda());
                                }
                            } else break;
                        }
                    } catch (IOException a) {
                        JOptionPane.showMessageDialog(null, "Problema ao Comparar palavras!");
                    }

                }
                file.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (flag == false) JOptionPane.showMessageDialog(null, "Não encontrado!");
    }

}
