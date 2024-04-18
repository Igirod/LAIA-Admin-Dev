package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceCalendarDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceClientDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceDirectionDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceMerchantDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceAddressInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceArticleInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceReservationDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.utils.LongDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.UserNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceReservationService;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface InvoiceService.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private MailSenderService mailSenderService;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private InvoiceProductCriteriaRepository invoiceProductCriteriaRepository;

   @Autowired
   private InvoiceReservationService invoiceReservationService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceStatus(Long id, String status) {
      invoiceJpaRepository.updateInvoiceStatus(id, status);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceNote(Long id, String status) {
      invoiceJpaRepository.updateInvoiceNote(id, status);
      return 1;
   }

   @Override
   public InvoiceDTO getInvoiceById(Long invoiceId) {
      // var order = invoiceJpaRepository.findById(invoiceId).orElse(null);
      // if (order == null)
      // throw new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND);
      // var listProducts =
      // invoiceProductCriteriaRepository.findInvoiceProductsByInvoiceId(invoiceId).stream()
      // .map(InvoiceProductDTO::new)
      // .collect(Collectors.toList());
      return null;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoicePaymentDTO updateInvoiceVoucher(MultipartFile voucher, Long invoiceId) {
      var voucherUrl = firebaseStorageService.uploadFile(voucher, "invoice-voucher-" + invoiceId.toString(),
            "vouchers");
      invoiceJpaRepository.updateVoucherByInvoiceId(voucherUrl, invoiceId);
      // * Se utiliza este tipo de DTO para no crear uno nuevo.
      return new InvoicePaymentDTO(voucherUrl, null, null);
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public LongDTO createAdminSellInvoice(Long userId, String email, String phone, String name, String lastName,
         Long merchantId, List<InvoiceArticleInputDTO> articles, InvoiceAddressInputDTO address,
         Long batchId, String reservation, String reservationType, String message) {
      Invoice newInvoice = new Invoice();
      User invoiceUser = null;
      Long existUserId = null;
      newInvoice.setMerchantId(merchantId);
      Map<String, Object> merchantAtributtes = userJpaRepository.findMerchantEmailAndSlugByUserId(merchantId);
      setInvoiceMerchantAtributtes(newInvoice, merchantId,
            (merchantAtributtes.get("mer_slug") != null ? merchantAtributtes.get("mer_slug").toString() : null),
            (merchantAtributtes.get("mer_phone") != null ? merchantAtributtes.get("mer_phone").toString() : null),
            (merchantAtributtes.get("mer_email") != null ? merchantAtributtes.get("mer_email").toString() : null));
      if (userId == null) {
         if (email != null) {
            existUserId = userJpaRepository.existByUserEmail(email);
         }
      } else {
         existUserId = userJpaRepository.existByUserId(userId);
      }
      invoiceUser = createInvoiceUser(existUserId, email, phone, name, lastName);
      setInvoiceAddressAtributes(newInvoice, (address == null) ? null : address);
      setInvoiceUserAtributtes(newInvoice, invoiceUser);
      try {
         newInvoice.setCreatedAt(DateUtils.getCurrentDate());
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(DateUtils.convertStringToDateWithoutTime(reservation));
         newInvoice.setCalendarDay(calendar.get(Calendar.DAY_OF_MONTH));
         newInvoice.setCalendarMonth(calendar.get(Calendar.MONTH) + 1);
         newInvoice.setCalendarYear(calendar.get(Calendar.YEAR));
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha de la reserva.");
      }
      newInvoice.setMessage((message == null) ? null : message);
      newInvoice.setMerchantTitle(
            (merchantAtributtes.get("mer_slug") == null) ? null : merchantAtributtes.get("mer_slug").toString());
      newInvoice.setDirectionType(reservationType);
      Map<String, String> batchAtributtes = batchJpaRepository.findFromTimeAndToTimeById(batchId);
      newInvoice.setCalendarFrom(batchAtributtes.get("CAST(from_time AS CHAR)"));
      newInvoice.setCalendarTo(batchAtributtes.get("CAST(to_time AS CHAR)"));
      newInvoice.setStatus(Status.INITIAL);
      newInvoice.setVoucher(null);
      newInvoice.setConfirm(0);
      List<Product> products = null;
      if (articles == null) {
         products = new ArrayList<Product>();
         newInvoice.setTotal(0.0);
         newInvoice.setCountArticles(0);
      } else {
         products = productJpaRepository
               .findAllById(articles.stream().map(InvoiceArticleInputDTO::getProductId).collect(Collectors.toList()));
         newInvoice.setTotal(products.stream().map(product -> {
            return product.getPrice() * articles.stream()
                  .filter(article -> article.getProductId().equals(product.getId())).findFirst().get().getQuantity();
         }).reduce(0.0, Double::sum));
         newInvoice.setCountArticles(products.size());
      }
      Long newInvoiceId = invoiceJpaRepository.save(newInvoice).getId();
      List<InvoiceProduct> invoiceProducts = products.stream().map(product -> {
         InvoiceProduct invoiceProduct = new InvoiceProduct();
         invoiceProduct.setId(new InvoiceProductId(newInvoiceId, product.getId()));
         invoiceProduct.setArticleTitle((product.getTitle() == null) ? null : product.getTitle());
         invoiceProduct.setArticlePrice((product.getPrice().toString() == null) ? null : product.getPrice());
         invoiceProduct.setArticleUnit((product.getTypeOfSale() == null) ? null : product.getTypeOfSale());
         invoiceProduct.setArticleMedia((product.getFrontPage() == null) ? null : product.getFrontPage());
         invoiceProduct.setArticleCount(articles.stream()
               .filter(article -> article.getProductId().equals(product.getId())).findFirst().get().getQuantity());
         return invoiceProduct;
      }).collect(Collectors.toList());
      invoiceProductJpaRepository.saveAll(invoiceProducts);
      alertUser(invoiceUser, newInvoiceId, merchantAtributtes.get("mer_email").toString());
      return new LongDTO(newInvoiceId);
   }

   /**
    * Este método tiene la obligación de asignar los atributos de la dirección de
    * la factura.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param newInvoice
    * @param address
    */
   private void setInvoiceAddressAtributes(Invoice newInvoice, InvoiceAddressInputDTO address) {
      if (address == null) {
         newInvoice.setDirectionType(null);
         newInvoice.setDirectionCity(null);
         newInvoice.setDirectionCountry(null);
         newInvoice.setDirectionLat(null);
         newInvoice.setDirectionLng(null);
         newInvoice.setDirectionNote(null);
         newInvoice.setDirectionNumber(null);
         newInvoice.setDirectionRef(null);
         newInvoice.setDirectionStreet(null);
      } else {
         newInvoice.setDirectionCity((address.getCity() == null) ? null : address.getCity());
         newInvoice.setDirectionCountry((address.getCountry() == null) ? null : address.getCountry());
         newInvoice.setDirectionLat((address.getLat() == null) ? null : address.getLat());
         newInvoice.setDirectionLng((address.getLng() == null) ? null : address.getLng());
         newInvoice.setDirectionNote((address.getNote() == null) ? null : address.getNote());
         newInvoice.setDirectionNumber((address.getNumber() == null) ? null : address.getNumber());
         newInvoice.setDirectionRef((address.getRef() == null) ? null : address.getRef());
         newInvoice.setDirectionStreet((address.getStreet() == null) ? null : address.getStreet());
      }
   }

   /**
    * Este método privado tiene la obligación de crear un usuario o buscar un
    * usuario para la factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param existUserId
    * @param email
    * @param phone
    * @return User
    */
   private User createInvoiceUser(Long existUserId, String email, String phone,
         String name, String surname) {
      // ! Usuario registrado en el sistema.
      if (existUserId != null) {
         User user = userJpaRepository.findUserById(existUserId);
         if (user == null) {
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND);
         }
         return user;
      }
      // ! Usuario no registrado en el sistema.
      else {
         if (email != null) {
            User newUser = new User();
            newUser.setId(null);
            newUser.setMail((email == null) ? null : email);
            newUser.setPhone((phone == null) ? null : phone);
            newUser.setName((name == null) ? null : name);
            newUser.setLastName((surname == null) ? null : surname);
            newUser.setDiferrentCreation(1);
            return userJpaRepository.save(newUser);
         }
         // ! Usuario no registrado en el sistema y sin correo electronico.
         else {
            User newUser = new User();
            newUser.setId(null);
            newUser.setMail(null);
            newUser.setPhone((phone == null) ? null : phone);
            newUser.setName((name == null) ? null : name);
            newUser.setLastName((surname == null) ? null : surname);
            return newUser;
         }
      }
   }

   /**
    * Este método privado tiene la obligación de asignar los atributos del usuario
    * en la factura.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param newInvoice
    * @param invoiceUser
    */
   private void setInvoiceUserAtributtes(Invoice newInvoice, User invoiceUser) {
      newInvoice.setClientName((invoiceUser.getName() == null) ? null : invoiceUser.getName());
      newInvoice.setClientSurname((invoiceUser.getLastName() == null) ? null : invoiceUser.getLastName());
      newInvoice.setClientEmail((invoiceUser.getMail() == null) ? null : invoiceUser.getMail());
   }

   /**
    * Este método privado tiene la obligación de asignar las propiedades del
    * merchant en la factura.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param merchantTitle
    * @param merchantPhone
    * @param merchantEmail
    */
   private void setInvoiceMerchantAtributtes(Invoice newInvoice, Long merchantId, String merchantTitle,
         String merchantPhone, String merchantEmail) {
      newInvoice.setMerchantId(merchantId);
      newInvoice.setMerchantTitle((merchantTitle == null) ? null : merchantTitle);
      newInvoice.setMerchantPhone((merchantPhone == null) ? null : merchantPhone);
      newInvoice.setMerchantEmail((merchantEmail == null) ? null : merchantEmail);
   }

   /**
    * Este método privado tiene la obligación de alertar al usuario y al
    * comerciante sobre la factura generada.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param user
    * @param invoiceId
    * @param merchantId
    */
   private void alertUser(User user, Long invoiceId, String merchantEmail) {
      if (user.getMail() != null) {
         // ! Aviso comprador.
         try {
            mailSenderService.sendUserOrder(new MailDTO(user.getMail(), "Factura generada", null, null, invoiceId));
            // ! Aviso vendedor.
            mailSenderService.sendUserOrder(new MailDTO(merchantEmail,
                  "Compra generada", null, null, invoiceId));
         } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo.");
         }
      }
   }

   @Override
   public InvoiceReservationDTO gAdminSellInvoiceReservation(Long merchantId, List<Long> invoiceArticlesIds) {
      return invoiceReservationService.getInvoiceReservation(merchantId, invoiceArticlesIds);
   }

   @Override
   public CompleteInvoiceDTO gAdminSellInvoice(Long invoiceId, Long userId) {
      CompleteInvoiceDTO completeInvoiceDTO = new CompleteInvoiceDTO();
      Optional<Invoice> invoice = invoiceJpaRepository.findById(invoiceId);
      if (invoice.isEmpty()) {
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      }
      if (invoice.get().getMerchantId() != userId) {
         completeInvoiceDTO.setOperation(0);
         return completeInvoiceDTO;
      }
      completeInvoiceDTO.setCli(new CompleteInvoiceClientDTO(invoice.get()));
      completeInvoiceDTO.setMerchant(new CompleteInvoiceMerchantDTO(invoice.get()));
      completeInvoiceDTO.setArticles(invoiceProductCriteriaRepository.findInvoiceProductsByInvoiceId(invoiceId).stream()
            .map(CompleteInvoiceArticleDTO::new).collect(Collectors.toList()));
      completeInvoiceDTO.setCalendar(new CompleteInvoiceCalendarDTO(invoice.get()));
      completeInvoiceDTO.setDirection(new CompleteInvoiceDirectionDTO(invoice.get()));
      completeInvoiceDTO.setMessage((invoice.get().getMessage() == null) ? null : invoice.get().getMessage());
      completeInvoiceDTO.setOperation(1);
      return completeInvoiceDTO;
   }
}
