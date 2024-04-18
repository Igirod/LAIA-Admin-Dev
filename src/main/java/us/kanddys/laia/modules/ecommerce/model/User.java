package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "name")
   private String name;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "email")
   private String mail;
   @Column(name = "password")
   private String password;
   @Column(name = "phone")
   private String phone;
   @Column(name = "image")
   private String image;
   @Column(name = "first")
   private Integer first;
   @Column(name = "mer_phone")
   private Integer merchantPhone;
   @Column(name = "mer_email")
   private String merchantEmail;
   @Column(name = "mer_message")
   private Integer merchantMessage;
   @Column(name = "mer_image")
   private String merchantImage;
   @Column(name = "mer_slug")
   private String merchantSlug;
   @Column(name = "type_shipment")
   private String typeShipment;
   @Column(name = "different_creation")
   private Integer diferrentCreation;

   public User() {
   }

   public User(Boolean invoice) {
      super();
      this.password = "cocodrilo";
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param name
    * @param lastName
    * @param mail
    * @param password
    * @param phone
    */
   public User(String name, String lastName, String mail, String password,
         String phone, String image) {
      super();
      this.id = null;
      this.name = name;
      this.lastName = lastName;
      this.mail = mail;
      this.password = password;
      this.phone = phone;
      this.image = image;
      this.first = 0;
   }

   public User(Boolean login, String mail) {
      super();
      this.mail = (mail == null ? null : mail);
      this.password = "cocodrilo";
   }
}
