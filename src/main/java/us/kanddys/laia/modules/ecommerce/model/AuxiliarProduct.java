package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Table(name = "aux_products")
@Entity
public class AuxiliarProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "aux_product_id")
   private Long auxProductId;
   @Column(name = "front_page")
   private String frontPage;
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "title")
   private String title;
   @Column(name = "price")
   private Double price;
   @Column(name = "stock")
   private Integer stock;
   @Column(name = "type_of_stock")
   private String typeOfStock;
   @Column(name = "hashtag")
   private String hashtag;
   @Column(name = "manufacturing_type")
   private String manufacturingType;
   @Column(name = "manufacturing_time")
   private Integer manufacturingTime;
   @Column(name = "segment_title")
   private String segmentTitle;
   @Column(name = "segment_description")
   private String segmentDescription;
   @Column(name = "segment_media")
   private String segmentMedia;
   @Column(name = "invenstment_note")
   private String invenstmentNote;
   @Column(name = "invenstment_amount")
   private Double invenstmentAmount;
   @Column(name = "invenstment_title")
   private String invenstmentTitle;
   @Column(name = "question_title")
   private String questionTitle;
   @Column(name = "question_type")
   private String questionType;
   @Column(name = "question_limit")
   private Integer questionLimit;
   @Column(name = "question_required")
   private Integer questionRequired;
   @Column(name = "category_title")
   private String categoryTitle;
   @Column(name = "type_of_price")
   private String typeOfPrice;

   public AuxiliarProduct() {
   }
}
