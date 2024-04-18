package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductInputDTO;

/**
 * Esta clase representa un producto en el carrito de compras.
 * 
 * @author Igirod
 * @version 1.0.1
 */
@Entity
@Table(name = "orders_products")
@AllArgsConstructor
@Data
public class OrderProduct {

   @EmbeddedId
   private OrderProductId id;
   @Column(name = "quantity")
   private Integer quantity;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public OrderProduct() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param productId
    */
   public OrderProduct(Long productId, Long orderId, Product product) {
      super();
      this.id = new OrderProductId(productId, orderId);
      this.product = product;
      this.quantity = 1;
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param InvoiceProductInputDTO
    */
   public OrderProduct(OrderProductInputDTO orderProductInputDTO) {
      super();
      this.id = new OrderProductId(orderProductInputDTO.getInvoiceId(),
            orderProductInputDTO.getProductId());
      this.quantity = orderProductInputDTO.getQuantity();
   }
}
