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
@Table(name = "collections")
@Entity
public class LibraryCollection {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "library_id")
   private Long libraryId;
   @Column(name = "miniature_header")
   private String miniatureHeader;
   @Column(name = "miniature_title")
   private String miniatureTitle;
   @Column(name = "miniature_subtitle")
   private String miniatureSubtitle;
   @Column(name = "title")
   private String title;
   @Column(name = "subtitle")
   private String subtitle;
   @Column(name = "conf")
   private Integer conf;
   @Column(name = "ordering")
   private String ordering;
   @Column(name = "asc_dsc")
   private Integer ascDsc;

   public LibraryCollection() {
   }
}
