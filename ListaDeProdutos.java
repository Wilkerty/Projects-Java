/**
 * @author Gabriel Uzeda e Wilker Tyller
 * Classe abstrata responsavel pelas funcoes que atribuem os dados a um fornecedor,
 * e de mostragem dos mesmos.
 * @since dez 2019
 * @version 1.0
 */
public class ListaDeProdutos extends Fornecedor {

    private String nome;
    private String categoria;
    private int estoque;
    private float precoVenda;
    private float precoCompra;
    private String validade;
    private String dataCompra;

    /**
     * Construtor da classe. Responsavel por armazenar os dados do produto, e,
     * tambem os dados do fornecedor da classe abstrata Fornecedor
     * @param nome
     * @param tel
     * @param cnpj
     * @param nome1
     * @param categoria
     * @param estoque
     * @param precoVenda
     * @param precoCompra
     * @param validade
     * @param dataCompra
     */
    public ListaDeProdutos(String nome, String tel, String cnpj, String nome1, String categoria, int estoque, float precoVenda, float precoCompra, String validade, String dataCompra) {
        super(nome, tel, cnpj);
        this.nome = nome1;
        this.categoria = categoria;
        this.estoque = estoque;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.validade = validade;
        this.dataCompra = dataCompra;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo nome
     */
    public String getNomeProduto() {
        return nome;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo nome
     * @param nome
     */
    public void setNomeProduto(String nome) {
        this.nome = nome;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo categoria
     * @param nome
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo estoque
     */
    public int getEstoque() {
        return estoque;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo estoque
     * @param nome
     */
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo precoVenda
     */
    public float getPrecoVenda() {
        return precoVenda;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo precoVenda
     * @param nome
     */
    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo precoCompra
     */
    public float getPrecoCompra() {
        return precoCompra;
    }

    /**
     * Funcao responsavel por atribuir um dado ao atributo precoCompra
     * @param nome
     */
    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo validade
     */
    public String getValidade() {
        return validade;
    }
    /**
     * Funcao responsavel por atribuir um dado ao atributo validade
     * @param nome
     */
    public void setValidade(String validade) {
        this.validade = validade;
    }

    /**
     * Funcao responsavel por pegar o dado do atributo dataCompra
     */
    public String getDataCompra() {
        return dataCompra;
    }
    /**
     * Funcao responsavel por atribuir um dado ao atributo dataCompra
     * @param nome
     */
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    /**
     * Funcao responsavel por mostrar os dados do produto para o usuario
     * @return
     */
    @Override
    public String toString() {
        return super.toString() +
                "\n" + nome +
                "\n" + categoria +
                "\n" + estoque +
                "\n" + precoVenda +
                "\n" + precoCompra +
                "\n" + validade +
                "\n" + dataCompra;
    }
}