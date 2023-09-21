import java.io.Serializable;
public class TransacaoServidor implements Serializable {
    private String tipoMensagem;
    private double valor;
    private String hora;
    private String data;
    private String redeTransmissora;
    private String codigoResposta;
    private long NSU;
    private Cartao cartao;
    public TransacaoServidor(String tipoMensagem, double valor, String hora, String data, String redeTransmissora, String codigoResposta, long NSU, Cartao cartao) {
        this.tipoMensagem = tipoMensagem;
        this.valor = valor;
        this.hora = hora;
        this.data = data;
        this.redeTransmissora = redeTransmissora;
        this.codigoResposta = codigoResposta;
        this.NSU = NSU;
        this.cartao = cartao;
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

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }

    public long getNSU() {
        return NSU;
    }

    public void setNSU(long NSU) {
        this.NSU = NSU;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "TransacaoServidor{" +
                "tipoMensagem='" + tipoMensagem + '\'' +
                ", valor=" + valor +
                ", hora='" + hora + '\'' +
                ", data='" + data + '\'' +
                ", redeTransmissora='" + redeTransmissora + '\'' +
                ", codigoResposta='" + codigoResposta + '\'' +
                ", NSU=" + NSU +
                '}';
    }
}
