import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Gabriel Uzeda e Wilker Tyller
 * Classe responsavel pelas funcoes de leitura e processamento de dados de uma loja.
 * As funcoes da classe manipulam uma lista de dados de acordo com os comandos de entrada
 * do usuario. Entre os processos disponiveis, a base de dados pode vir do usuario,
 * inserindo novos valores, ou, de um arquivo de texto contendo os dados iniciais do projeto
 * @since dez 2019
 * @version 1.0
 */
public class Loja {
    /**
     * Declaracao do atributo nome da loja
     */
    private String nome;

    /**
     * Declaracao do atributo localizacao da loja
     */
    private String localizacao;

    /**
     * Declaracao de uma nova lista de produtos
     */
    public ArrayList<ListaDeProdutos> listaProdutos = new ArrayList<ListaDeProdutos>();

    /**
     * Construtor responsavel por recber os dados de nome e localizacao de uma loja,
     * imprimir a confirmacao de cadastro e, acionar as funcoes gravarLoja e cadastrarListaProdutos
     * ate que uma condicao de parada seja alcançada
     * @param nome
     * @param localizacao
     */
    public Loja(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        JOptionPane.showMessageDialog(null, "Loja Cadastrada!\nNome: " + this.nome + "\nLocalizacao: " + this.localizacao);
        while (JOptionPane.showConfirmDialog(null, "Cadastrar um Produto?") == 0) {
            cadastrarListaProdutos();
        }
        gravarLoja("lojas/" + this.nome);
    }

    /**
     * Segundo construtor de Loja. Responsavel pela leitura da lista
     * @param path
     */
    public Loja(String path) {

        try {
            InputStream bitReader = new FileInputStream(path);
            InputStreamReader charReader = new InputStreamReader(bitReader);

            char tempChar = 0;
            String temp = "";

            while (tempChar != '=') {
                tempChar = (char) charReader.read();
            }
            while (tempChar != '\n') {
                tempChar = (char) charReader.read();
                if (tempChar != '\n') temp += tempChar;
            }
            this.nome = temp;

            //Reseta Temp
            tempChar = 0;
            temp = "";

            while (tempChar != '=') {
                tempChar = (char) charReader.read();
            }
            while (tempChar != '\n') {
                tempChar = (char) charReader.read();
                if (tempChar != '\n') temp += tempChar;
            }
            this.localizacao = temp;
            charReader.close();
            bitReader.close();
            lerListaProdutos(path);
        } catch (IOException t) {
        }
    }

    /**
     * Funcao responsavel por deletar um arquivo
     * @param path
     */
    public void deletaArquivo(String path){
        File f = new File(path);
        if (f.exists()) {
            boolean sucess = f.delete();
            if (sucess != true) JOptionPane.showMessageDialog(null, "Falha ao Deletar arquivo");
        }
    }

    /**
     * Funcao responsavel por criar um arquivo
     */
    public File criaArquivo() throws IOException {
        File file = new File(("lojas/"+this.nome));
        // file.createNewFile();
        return file;
    }

    /**
     * Funcao responsavel por gravar os dados de entrada de dada loja
     * @param path
     */
    public void gravarLoja(String path) {
        String temp;
        try {
            deletaArquivo(path);
            FileWriter arq = new FileWriter(criaArquivo());
            PrintWriter gravarArq = new PrintWriter(arq);
            temp="NOME DA LOJA="+this.nome;
            gravarArq.printf(temp.toUpperCase()+"\n");
            temp="LOCALIZAÃ‡Ã‚O="+this.localizacao;
            gravarArq.printf(temp.toUpperCase());
            for (int i = 0; i < listaProdutos.size(); i++) {
                temp=listaProdutos.get(i).toString();
                gravarArq.printf(temp.toUpperCase());
            }
            gravarArq.close();
            arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcao responsavel por cadastrar fornecedores e produtos em uma lista, ate que
     * o criterio de parada seja alcancado. A atribuicao e feita pelo usuario na interface
     * grafica
     */
    public void cadastrarListaProdutos() {

        JTextField nomeFornecedor = new JTextField();
        JTextField telefoneFornecedor = new JTextField();
        JTextField CNPJFornecedor = new JTextField();
        JTextField nomeProduto = new JTextField();
        JTextField categoria = new JTextField();
        JTextField quantidade = new JTextField();
        JTextField precoVenda = new JTextField();
        JTextField precoCompra = new JTextField();
        JTextField validade = new JTextField();
        JTextField dataCompra = new JTextField();

        Object[] message = {
                "Fornecedor:", nomeFornecedor,
                "Telefone do Fornecedor:", telefoneFornecedor,
                "CNPJ do Fornecedor:", CNPJFornecedor,
                "Nome do Produto", nomeProduto,
                "Categoria", categoria,
                "Quantidade", quantidade,
                "PreÃ§o da Compra:", precoCompra,
                "Preco p/ Venda:", precoVenda,
                "Validade:", validade,
                "Data da Compra:", dataCompra
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Arquivo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            listaProdutos.add(new ListaDeProdutos(nomeFornecedor.getText(), telefoneFornecedor.getText(), CNPJFornecedor.getText(), nomeProduto.getText(), categoria.getText(), Integer.parseInt(quantidade.getText()), Float.parseFloat(precoVenda.getText()), Float.parseFloat(precoCompra.getText()), validade.getText(), dataCompra.getText()));
        }
    }

    /**
     * Funcao responsavel por ler uma lista de produtos
     * @param path
     */
    public void lerListaProdutos(String path) {
        try {

            String temp = "nÃ£o nulo";

            //Criando VariÃ¡veis necessÃ¡rias para armazenar uma nova Lista de Produtos

            String nome;
            String tel;
            String cnpj;
            String nome1;
            String categoria;
            int estoque;
            float precoVenda;
            float precoCompra;
            String validade;
            String dataCompra;

            InputStream bitReader = new FileInputStream(path);
            InputStreamReader charReader = new InputStreamReader(bitReader);
            BufferedReader stringReader = new BufferedReader(charReader);

            while (temp != null) {
                temp = stringReader.readLine();
                if ("FORNECEDOR".equals(temp)) {
                    nome = stringReader.readLine();
                    tel = stringReader.readLine();
                    cnpj = stringReader.readLine();
                    nome1 = stringReader.readLine();
                    categoria = stringReader.readLine();
                    estoque = Integer.parseInt(stringReader.readLine());
                    precoVenda = Float.parseFloat(stringReader.readLine());
                    precoCompra = Float.parseFloat(stringReader.readLine());
                    validade = stringReader.readLine();
                    dataCompra = stringReader.readLine();
                    listaProdutos.add(new ListaDeProdutos(nome,
                            tel,
                            cnpj,
                            nome1,
                            categoria,
                            estoque,
                            precoVenda,
                            precoCompra,
                            validade,
                            dataCompra));
                }


            }
            stringReader.close();
            charReader.close();
            bitReader.close();

        } catch (IOException e) {

        }
    }

    /**
     * Funcao responsavel por pegar o dado do atributo nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo localizacao
     * @return
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo localizacao
     * @param localizacao
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Funcao responsavel por pegar uma lista de produtos
     * @return
     */
    public ArrayList<ListaDeProdutos> getListaProdutos() {
        return listaProdutos;
    }

    /**
     * Funcao responsavel por atribuir dados a uma lista de produtos
     * @param listaProdutos
     */
    public void setListaProdutos(ArrayList<ListaDeProdutos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }


    /**
     * Funcao responsavel por mostrar os dados da loja e a lista de produtos para o usuario
     * @return
     */
    @Override
    public String toString() {
        return "Loja= " + nome +
                "LocalizaÃ§Ã£o= " + localizacao +
                "Lista de Produtos= " + listaProdutos;
    }

}
