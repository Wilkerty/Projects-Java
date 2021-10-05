/**
 * @author Gabriel Uzeda e Wilker Tyller
 * Classe abstrata responsavel pelas funcoes que atribuem os dados a um fornecedor,
 * e de mostragem dos mesmos.
 * @since dez 2019
 * @version 1.0
 */

public abstract class  Fornecedor {
    /**
     * Atributo nome que recebe o nome do fornecedor
     */
    private String nome;
    /**
     * Atributo tel que recebe o telefone do fornecedor
     */
    private String tel;
    /**
     * Atributo cnpj que recebe o cnpj do fornecedor
     */
    private String cnpj;

    /**
     * Construtor da classe responsavel por atribuir os dados (nome,tel,cnpj)
     * recebidos por parametro.
     * @param nome
     * @param tel
     * @param cnpj
     */
    public Fornecedor(String nome, String tel, String cnpj) {

        this.nome = nome;
        this.tel = tel;
        this.cnpj = cnpj;
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
     * Funcao responsavel por pegar o dado do atributo tel
     */
    public String getTel() {

        return tel;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo tel
     * @param tel
     */
    public void setTel(String tel) {

        this.tel = tel;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo cnpj
     */
    public String getCnpj() {

        return cnpj;
    }

    /**
     * Funcao responsavel para atribuir um dado ao atributo
     * @param cnpj
     */
    public void setCnpj(String cnpj) {

        this.cnpj = cnpj;
    }

    /**
     * Funcao responsavel por mostrar os dados do fornecedor para o usuario
     * @return
     */
    public String toString() {

        return "Fornecedor" +
                "\n" + nome +
                "\n" + tel +
                "\n" + cnpj;
    }
}
