package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Buyer;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de un comprador.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class BuyerDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String name;
   @JsonProperty
   private String surname;
   @JsonProperty
   private String email;
   @JsonProperty
   private String phone;
   @JsonProperty
   private Integer count;
   @JsonProperty
   private Integer countArticle;
   @JsonProperty
   private Integer countArticleTotal;
   @JsonProperty
   private Double totalMax;
   @JsonProperty
   private Double total;
   @JsonProperty
   private String date;
   @JsonProperty
   private String media;
   @JsonProperty
   private Integer operation;

   public BuyerDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param buyer
    */
   public BuyerDTO(Buyer buyer, Integer operation) {
      super();
      this.id = (buyer.getId() != null) ? buyer.getId() : null;
      this.merchantId = (buyer.getMerchantId() != null) ? buyer.getMerchantId() : null;
      this.name = (buyer.getName() != null) ? buyer.getName() : null;
      this.surname = (buyer.getSurname() != null) ? buyer.getSurname() : null;
      this.email = (buyer.getEmail() != null) ? buyer.getEmail() : null;
      this.phone = (buyer.getPhone() != null) ? buyer.getPhone() : null;
      this.count = (buyer.getCount() != null) ? buyer.getCount() : null;
      this.date = (buyer.getDate() != null) ? DateUtils.convertDateToStringWithoutTime(buyer.getDate()) : null;
      this.media = (buyer.getMedia() != null) ? buyer.getMedia() : null;
      this.operation = operation;
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param map<StringObject>
    */
   public BuyerDTO(Map<String, Object> buyerAtributtes) {
      super();
      this.id = (buyerAtributtes.get("id") != null ? (Long) buyerAtributtes.get("id") : null);
      this.merchantId = (buyerAtributtes.get("merchantId") != null ? (Long) buyerAtributtes.get("merchantId") : null);
      this.name = (buyerAtributtes.get("name") != null ? (String) buyerAtributtes.get("name") : null);
      this.surname = (buyerAtributtes.get("surname") != null ? (String) buyerAtributtes.get("surname") : null);
      this.email = (buyerAtributtes.get("email") != null ? (String) buyerAtributtes.get("email") : null);
      this.phone = (buyerAtributtes.get("phone") != null ? (String) buyerAtributtes.get("phone") : null);
      this.count = (buyerAtributtes.get("count") != null ? (Integer) buyerAtributtes.get("count") : null);
      this.total = (buyerAtributtes.get("total") != null ? (Double) buyerAtributtes.get("total") : null);
      this.countArticle = (buyerAtributtes.get("count_article") != null ? (Integer) buyerAtributtes.get("count_article")
            : null);
      this.countArticleTotal = (buyerAtributtes.get("count_article_total") != null
            ? (Integer) buyerAtributtes.get("count_article_total")
            : null);
      this.totalMax = (buyerAtributtes.get("total_max") != null ? (Double) buyerAtributtes.get("total_max") : null);
      this.date = (buyerAtributtes.get("date") != null
            ? buyerAtributtes.get("date").toString()
            : null);
      this.media = (buyerAtributtes.get("media") != null ? (String) buyerAtributtes.get("media") : null);
   }

   public BuyerDTO(Buyer buyer) {
      super();
      this.id = (buyer.getId() != null) ? buyer.getId() : null;
      this.merchantId = (buyer.getMerchantId() != null) ? buyer.getMerchantId() : null;
      this.name = (buyer.getName() != null) ? buyer.getName() : null;
      this.surname = (buyer.getSurname() != null) ? buyer.getSurname() : null;
      this.email = (buyer.getEmail() != null) ? buyer.getEmail() : null;
      this.phone = (buyer.getPhone() != null) ? buyer.getPhone() : null;
      this.count = (buyer.getCount() != null) ? buyer.getCount() : null;
      this.date = (buyer.getDate() != null) ? DateUtils.convertDateToStringWithoutTime(buyer.getDate()) : null;
      this.media = (buyer.getMedia() != null) ? buyer.getMedia() : null;
      this.totalMax = (buyer.getTotal() != null) ? buyer.getTotal() : null;
   }
}
