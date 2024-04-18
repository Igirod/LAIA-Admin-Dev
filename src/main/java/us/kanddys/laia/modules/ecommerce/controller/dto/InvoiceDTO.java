package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta clase representa un data transfer object (DTO) para la factura.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
@Data
@AllArgsConstructor
public class InvoiceDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long clientId;
   @JsonProperty
   private String clientName;
   @JsonProperty
   private String clientSurname;
   @JsonProperty
   private String clientEmail;
   @JsonProperty
   private String clientMedia;
   @JsonProperty
   private String clientPhone;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String merchantTitle;
   @JsonProperty
   private String merchantPhone;
   @JsonProperty
   private String merchantEmail;
   @JsonProperty
   private String code;
   @JsonProperty
   private Double total;
   @JsonProperty
   private String message;
   @JsonProperty
   private Status status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private Integer calendarDay;
   @JsonProperty
   private Integer calendarMonth;
   @JsonProperty
   private Integer calendarYear;
   @JsonProperty
   private String calendarFrom;
   @JsonProperty
   private String calendarTo;
   @JsonProperty
   private String directionCode;
   @JsonProperty
   private String directionRef;
   @JsonProperty
   private String directionCountry;
   @JsonProperty
   private String directionCity;
   @JsonProperty
   private String directionStreet;
   @JsonProperty
   private String directionNumber;
   @JsonProperty
   private String directionLat;
   @JsonProperty
   private String directionLng;
   @JsonProperty
   private String directionNote;
   @JsonProperty
   private String directionType;
   @JsonProperty
   private String directionState;
   @JsonProperty
   private String createdAt;
   @JsonProperty
   private String updatedAt;
   @JsonProperty
   private Integer confirm;
   @JsonProperty
   private Integer countArticles;

   public InvoiceDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    * 
    * @param invoice
    * @param fromTime
    * @param toTime
    * @param products
    */
   public InvoiceDTO(Invoice invoice) {
      super();
      this.id = invoice.getId();
      this.clientId = (invoice.getClientId() == null ? 0 : invoice.getClientId());
      this.clientName = (invoice.getClientName() == null ? null : invoice.getClientName());
      this.clientSurname = (invoice.getClientSurname() == null ? null : invoice.getClientSurname());
      this.clientEmail = (invoice.getClientEmail() == null ? null : invoice.getClientEmail());
      this.clientMedia = (invoice.getClientMedia() == null ? null : invoice.getClientMedia());
      this.clientPhone = (invoice.getClientPhone() == null ? null : invoice.getClientPhone());
      this.merchantId = (invoice.getMerchantId() == null ? 0 : invoice.getMerchantId());
      this.merchantTitle = (invoice.getMerchantTitle() == null ? null : invoice.getMerchantTitle());
      this.merchantPhone = (invoice.getMerchantPhone() == null ? null : invoice.getMerchantPhone());
      this.merchantEmail = (invoice.getMerchantEmail() == null ? null : invoice.getMerchantEmail());
      this.code = (invoice.getCode() == null ? null : invoice.getCode());
      this.total = (invoice.getTotal() == null ? 0 : invoice.getTotal());
      this.message = (invoice.getMessage() == null ? null : invoice.getMessage());
      this.status = (invoice.getStatus() == null ? Status.PENDING : invoice.getStatus());
      this.voucher = (invoice.getVoucher() == null ? null : invoice.getVoucher());
      this.calendarDay = (invoice.getCalendarDay() == null ? 0 : invoice.getCalendarDay());
      this.calendarMonth = (invoice.getCalendarMonth() == null ? 0 : invoice.getCalendarMonth());
      this.calendarYear = (invoice.getCalendarYear() == null ? 0 : invoice.getCalendarYear());
      this.calendarFrom = (invoice.getCalendarFrom() == null ? null : invoice.getCalendarFrom());
      this.calendarTo = (invoice.getCalendarTo() == null ? null : invoice.getCalendarTo());
      this.directionCode = (invoice.getDirectionCode() == null ? null : invoice.getDirectionCode());
      this.directionRef = (invoice.getDirectionRef() == null ? null : invoice.getDirectionRef());
      this.directionCountry = (invoice.getDirectionCountry() == null ? null : invoice.getDirectionCountry());
      this.directionCity = (invoice.getDirectionCity() == null ? null : invoice.getDirectionCity());
      this.directionStreet = (invoice.getDirectionStreet() == null ? null : invoice.getDirectionStreet());
      this.directionNumber = (invoice.getDirectionNumber() == null ? null : invoice.getDirectionNumber());
      this.directionLat = (invoice.getDirectionLat() == null ? null : invoice.getDirectionLat());
      this.directionLng = (invoice.getDirectionLng() == null ? null : invoice.getDirectionLng());
      this.directionNote = (invoice.getDirectionNote() == null ? null : invoice.getDirectionNote());
      this.directionType = (invoice.getDirectionType() == null ? null : invoice.getDirectionType());
      this.directionState = (invoice.getDirectionState() == null ? null : invoice.getDirectionState());
      this.createdAt = DateUtils
            .convertDateToStringWithoutTime((invoice.getCreatedAt() == null ? null : invoice.getCreatedAt()));
      this.updatedAt = DateUtils
            .convertDateToStringWithoutTime((invoice.getUpdatedAt() == null ? null : invoice.getUpdatedAt()));
      this.confirm = (invoice.getConfirm() == null ? 0 : invoice.getConfirm());
      this.countArticles = (invoice.getCountArticles() == null ? 0 : invoice.getCountArticles());
   }
}
