import java.io.Serializable;

public class TransacaoCliente implements Serializable {
    private String tipoMensagem;
    private double valor;
    private String hora;
    private String data;
    private String redeTransmissora;
    private String numeroCartao;
    private String formaPagamento;
    private Cartao cartao;

    public TransacaoCliente() {
    }

    public TransacaoCliente(String tipoMensagem, double valor, String hora, String data, String redeTransmissora, String numeroCartao, String formaPagamento) {
        this.tipoMensagem = tipoMensagem;
        this.valor = valor;
        this.hora = hora;
        this.data = data;
        this.redeTransmissora = redeTransmissora;
        this.numeroCartao = numeroCartao;
        this.formaPagamento = formaPagamento;
    }

    public String getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRedeTransmissora() {
        return redeTransmissora;
    }

    public void setRedeTransmissora(String redeTransmissora) {
        this.redeTransmissora = redeTransmissora;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "TransacaoCliente{" +
                "tipoMensagem='" + tipoMensagem + '\'' +
                ", valor=" + valor +
                ", hora='" + hora + '\'' +
                ", data='" + data + '\'' +
                ", redeTransmissora='" + redeTransmissora + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                '}';
    }
}
