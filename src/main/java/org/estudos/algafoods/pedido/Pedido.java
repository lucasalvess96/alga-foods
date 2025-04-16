package org.estudos.algafoods.pedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "foods")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    private BigDecimal subtotal;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "data_criacao_pedido")
    private LocalDateTime dataCriacaoPedido;

    @Column(name = "data_confirmacao_pedido")
    private LocalDateTime dacaConfirmacaoPedido;

    @Column(name = "data_entrega_pedido")
    private LocalDateTime dataEntregaPedido;

    @Column(name = "data_cancelamento_pedido")
    private LocalDateTime dataCancelamentoPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public LocalDateTime getDataCriacaoPedido() {
        return dataCriacaoPedido;
    }

    public void setDataCriacaoPedido(LocalDateTime dataCriacaoPedido) {
        this.dataCriacaoPedido = dataCriacaoPedido;
    }

    public LocalDateTime getDacaConfirmacaoPedido() {
        return dacaConfirmacaoPedido;
    }

    public void setDacaConfirmacaoPedido(LocalDateTime dacaConfirmacaoPedido) {
        this.dacaConfirmacaoPedido = dacaConfirmacaoPedido;
    }

    public LocalDateTime getDataEntregaPedido() {
        return dataEntregaPedido;
    }

    public void setDataEntregaPedido(LocalDateTime dataEntregaPedido) {
        this.dataEntregaPedido = dataEntregaPedido;
    }

    public LocalDateTime getDataCancelamentoPedido() {
        return dataCancelamentoPedido;
    }

    public void setDataCancelamentoPedido(LocalDateTime dataCancelamentoPedido) {
        this.dataCancelamentoPedido = dataCancelamentoPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", taxaFrete=" + taxaFrete +
                ", dataCriacaoPedido=" + dataCriacaoPedido +
                ", dacaConfirmacaoPedido=" + dacaConfirmacaoPedido +
                ", dataEntregaPedido=" + dataEntregaPedido +
                ", dataCancelamentoPedido=" + dataCancelamentoPedido +
                '}';
    }
}
