import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cartao implements Serializable {
    private String numero;
    private String nomeCliente;
    private double saldo;
    List<TransacaoCliente> transacaoClientes = new ArrayList<>();
    List<TransacaoServidor> transacaoServidores = new ArrayList<>();
    public Cartao() {
    }

    public Cartao(String numero, String nomeCliente, double saldo) {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<TransacaoCliente> getTransacaoClientes() {
        return transacaoClientes;
    }

    public List<TransacaoServidor> getTransacaoServidores() {
        return transacaoServidores;
    }

    public void adicionarTransacaoCliente(TransacaoCliente transacaoCliente) {
        this.transacaoClientes.add(transacaoCliente);
    }

    public void adicionarTransacaoServidor(TransacaoServidor transacaoServidor) {
        this.transacaoServidores.add(transacaoServidor);
    }

    public synchronized boolean debitar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public synchronized boolean creditar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "numero='" + numero + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", saldo=" + saldo +
                ", transacaoClientes=" + transacaoClientes +
                ", transacaoServidores=" + transacaoServidores +
                '}';
    }
}
